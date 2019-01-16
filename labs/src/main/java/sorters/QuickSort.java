package sorters;

import sorters.abstractsorters.BubbleSorter;

/**
 * @author Burba
 *
 * <p>
 *     Contains methods that sorts array via Quick Sort.
 * </p>
 */
public class QuickSort extends BubbleSorter {

    /**
     * Calls {@link #quickSort(int, int, int[])} method, passes to it
     * the lowest and the highest indexes and link to the
     * array that is needed to be sorted.
     * @param array array which is needed to be sorted.
     */
    public void sort(int[] array) {

        quickSort(0, array.length - 1, array);
    }

    /**
     * Recursively calls itself, realizing Quick Sort algorithm in this way.
     * @param low lowest index of array.
     * @param high highest index of array.
     * @param array array which is needed to be sorted.
     */
    private void quickSort(int low, int high, int[] array) {

        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = array[low + (high - low) / 2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (array[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (array[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the array.
            // As we are done we can increase i and j
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quickSort(low, j, array);
        if (i < high)
            quickSort(i, high, array);
    }
}
