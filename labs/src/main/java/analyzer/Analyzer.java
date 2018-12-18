package analyzer;

import com.google.common.collect.*;
import reflection.Reflection;
import fillers.FillerAnnotation;
import fillers.Fillers;
import sorters.abstractsorters.AbstractSorter;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Burba
 *
 * <p>
 *     This class contains methods to generate arrays, sort them and analyze duration of every sorter.
 * </p>
 */
public class Analyzer {

    private static final int ITERATIONS = 12;

    /**
     * <p>
     *     Calculates duration of sorting.
     * </p>
     *
     * @param sorter an object of certain sorter
     * @param array an array to be sorted
     * @return time of sorting in nanoseconds
     */
    private long getSortDuration(AbstractSorter sorter, int[] array) {
        long begin = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();

        return end - begin;
    }

    /**
     * <p>
     *     Uses methods from {@link Reflection} class to find all methods in {@link Fillers} class, that
     *     are marked with {@link FillerAnnotation} and find all classes extending {@link AbstractSorter}.
     * </p>
     *
     * <p>
     *     The method uses {@link #getSortDuration} method to calculate the duration of sort.
     *     All the found data, such as names of sorters, numbers of elements, for which
     *     those sorters act, and durations of every sort are placed to the Table. Formed Tables serve
     *     as V in the Map with appropriate {@link Fillers} methods serving as K.
     * </p>
     * @return Map with Fillers serving as key and Table as value for the key.
     */
    public Map<String, Table<String, Integer, Long>> analyze() {

        int[] array;
        long sortDuration;

        Reflection reflection = new Reflection();

        ArrayList<Method> fillerMethods = reflection.getFillers();
        ArrayList<AbstractSorter> sorters = reflection.getSorters();

        Map<String, Table<String, Integer, Long>> fillerMap = new HashMap<>();

        for (Method fillerMethod : fillerMethods) {
            Table<String, Integer, Long> table = HashBasedTable.create();

            for (int i = 0, size = 10; i < ITERATIONS; i++, size *= 2) {
                for (AbstractSorter sorter : sorters) {

                    array = reflection.invokeFiller(fillerMethod, size);
                    sortDuration = getSortDuration(sorter, array);

                    table.put(sorter.getClass().getSimpleName(), size, sortDuration);
                }
            }
            fillerMap.put(fillerMethod.getName(), table);
        }
        return fillerMap;
    }
}
