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
     * Used to swap two elements in array.
     * @param array array in which two elements are needed to be swapped.
     * @param j index to understand which elements are needed to be swapped.
     */
    public void swap(int[] array, int j){
        int temp = array[j - 1];
        array[j - 1] = array[j];
        array[j] = temp;
    }
}
