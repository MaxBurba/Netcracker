package sorters;

import sorters.abstractsorters.MergeSorter;

/**
 * @author Burba
 *
 * <p>
 *     Contains method that sorts array with Merge Sort algorithm and {@link BubbleSortDown}.
 * </p>
 */
public class MergeBubbleDown extends MergeSorter {

    /**
     * Realization of sorting array via Merge Sort algorithm, where initial array is subdivided
     * into two parts, each of which is sorted with {@link BubbleSortDown#sort(int[])}. The parts are further
     * merged with {@link #mergeArrays(int[], int[], int[])} method from {@link MergeSorter}.
     * @param array array which is needed to be sorted.
     */
    public void sort(int[] array) {

        BubbleSortDown bubbleSortDown = new BubbleSortDown();

        invoke(array, bubbleSortDown);
    }
}
