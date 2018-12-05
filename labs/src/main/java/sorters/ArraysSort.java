package sorters;

import sorters.abstractsorters.AbstractSorter;

import java.util.Arrays;

/**
 * @author Burba
 * <p>
 *     Contains method that sorts array with the help of <b>Arrays</b>}.
 * </p>
 */
public class ArraysSort extends AbstractSorter {

    /**
     * Realization of sorting array with the use of {@link Arrays#sort(int[])}.
     * @param array array which is needed to be sorted.
     */
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
