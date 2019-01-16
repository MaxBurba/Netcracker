package sorters.abstractsorters;

/**
 * @author Burba
 *
 *<p>
 *     Extends {@link AbstractSorter} class and contains {@link #swap(int[], int)}
 *     function, useally used in bubble sorts.
 *</p>
 */
public abstract class BubbleSorter extends AbstractSorter{

    /**
     * Is used to swap two elements in array. Needed in BubbleSorts.
     * @param array array in which two elements are needed to be swapped.
     * @param j index to understand which elements are needed to be swapped.
     */
    protected void swap(int[] array, int j){
        int temp = array[j - 1];
        array[j - 1] = array[j];
        array[j] = temp;
    }

    /**
     * Swaps to elements by their indexes. Is used for QuickSort.
     * @param array array in which two elements are needed to be swapped.
     * @param i first index of value to swap.
     * @param j second index of value to swap.
     */
    protected void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
