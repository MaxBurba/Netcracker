package sorters;

import sorters.abstractsorters.BubbleSorter;

/**
 * @author Burba
 *
 * <p>
 *     Contains method that sorts array via bubble sort algorithm with checking begin to end.
 * </p>
 */
public class BubbleSortUp extends BubbleSorter {

    /**
     * Realization of sorting array via bubble sort algorithm with checking begin to end.
     * <br>Uses {@link #swap(int[], int)} method from {@link BubbleSortUp}.
     * @param array array which is needed to be sorted.
     */
    public void sort(int[] array) {

        for(int i = 0; i < array.length; i++){
            for(int j = 1; j < (array.length - i); j++){
                if(array[j - 1] > array[j]){
                    swap(array, j);
                }
            }
        }
    }
}
