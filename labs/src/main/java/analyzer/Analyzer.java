package analyzer;

import reflection.Reflection;
import fillers.FillerAnnotation;
import fillers.Fillers;
import sorters.abstractsorters.AbstractSorter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Burba
 *
 * <p>
 *     This class contains methods to generate arrays, sort them and analyze duration of every sorter.
 * </p>
 */
public class Analyzer {

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
     *     Uses methods from {@link Reflection} class to find all methods in {@link Fillers} class, that are marked by
     *     {@link FillerAnnotation} and find all classes extending {@link AbstractSorter}.
     * </p>
     *
     * <p>
     *     The method uses {@link #getSortDuration} method to calculate the duration of sort.
     * </p>
     */
    public void analyze() {

        int[] array;
        Reflection reflection = new Reflection();

        ArrayList<Method> fillerMethods = reflection.getFillers();
        ArrayList<AbstractSorter> sorters = reflection.getSorters();

        long sortDuration;

        for(Method fillerMethod : fillerMethods){
            for(int size = 10; size <= 60; size *= 2){
                for(AbstractSorter sorter : sorters){
                    array = reflection.invokeFiller(fillerMethod, size);
                    System.out.println(fillerMethod.getName());
                    System.out.println(sorter.getClass().getName());
                    System.out.println(Arrays.toString(array));
                    sortDuration = getSortDuration(sorter, array);
                    System.out.println(sortDuration);
                    System.out.println(Arrays.toString(array));
                }
            }
        }
    }
}
