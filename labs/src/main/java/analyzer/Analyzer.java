package analyzer;

import fillers.FillerAnnotation;
import fillers.Fillers;
import org.reflections.Reflections;
import sorters.abstractsorters.AbstractSorter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;
import java.util.Set;

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
     *     Uses <b>reflection</b> to find all methods in {@link Fillers} class, that are marked by
     *     {@link FillerAnnotation} and uses <i>org.reflections</i>
     *     library to find all classes extending {@link AbstractSorter}.
     * </p>
     *
     * <p>
     *     All founded fillers generate arrays that are further sorted by every sorting class, which is not abstract.
     *     The method uses {@link #getSortDuration} method to calculate the duration of sort.
     * </p>
     * <br>
     * @see <a href="https://github.com/ronmamo/reflections">org.reflections</a>
     */
    public void analyze() {

        int size;
        int[] array;
        int modifier;

        long sortDuration;

        Random random = new Random();

        Class fillersClass = Fillers.class;
        Method[] fillerMethods = fillersClass.getMethods();

        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> sortClasses = reflections.getSubTypesOf(AbstractSorter.class);

        for (int i = 0; i <= 3; i++) {

            size = random.nextInt(91) + 10;

            for (Method fillerMethod : fillerMethods) {
                FillerAnnotation fillerAnnotation = fillerMethod.getAnnotation(FillerAnnotation.class);
                if (fillerAnnotation != null) {

                    for (Class<? extends AbstractSorter> sortClass : sortClasses) {

                        modifier = sortClass.getModifiers();

                        if (!Modifier.isAbstract(modifier)) {
                            try {
                                array = (int[]) fillerMethod.invoke(fillersClass, size);
                                AbstractSorter sorter = sortClass.newInstance();
                                sortDuration = getSortDuration(sorter, array);
                            } catch (Exception exc) {
                                exc.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
