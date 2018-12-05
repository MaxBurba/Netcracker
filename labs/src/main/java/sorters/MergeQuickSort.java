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
     * merged with {@link #mergeArrays(int[], int[], int, int, int[])} method from {@link MergeSorter}.
     * @param array array which is needed to be sorted.
     */
    public void sort(int[] array) {

        QuickSort quickSort = new QuickSort();

        int[] leftArray;
        int[] rightArray;

        int low = 0;
        int high = array.length - 1;

        // Check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;

            leftArray = Arrays.copyOfRange(array, 0, middle + 1);
            rightArray = Arrays.copyOfRange(array, middle + 1, array.length);

            quickSort.sort(leftArray);
            quickSort.sort(rightArray);

            // Combine them both
            mergeArrays(leftArray, rightArray, leftArray.length, rightArray.length, array);
        }
    }
}
