package sorters;

import sorters.abstractsorters.MergeSorter;

import java.util.Arrays;

/**
 * @author Burba
 *
 * <p>
 *     Contains method that sorts array with Merge Sort algorithm and {@link QuickSort}.
 * </p>
 */
public class MergeQuickSort extends MergeSorter {

    /**
     * Realization of sorting array via Merge Sort algorithm, where initial array is subdivided
     * into two parts, each of which is sorted with {@link QuickSort#sort(int[])}. The parts are further
     * merged with {@link #mergeArrays(int[], int[], int[])} method from {@link MergeSorter}.
     * @param array array which is needed to be sorted.
     */
    public void sort(int[] array) {

        QuickSort quickSort = new QuickSort();

        invoke(array, quickSort);
    }
}
