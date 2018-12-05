package sorters;

import sorters.abstractsorters.BubbleSorter;

/**
 * @author Burba
 *
 * <p>
 *     Contains method that sorts array via bubble sort algorithm with checking from end to begin.
 * </p>
 */
public class BubbleSortDown extends BubbleSorter {

    /**
     * Realization of sorting array via bubble sort algorithm with checking from end to begin.
     * <br>Uses {@link #swap(int[], int)} method from {@link BubbleSortDown}
     * @param array array which is needed to be sorted.
     */
    public void sort(int[] array) {

        for(int i = 0; i < array.length; i++){
            for(int j = array.length - 1; j > i; j--){
                if(array[j - 1] > array[j]){
                    swap(array, j);
                }
            }
        }
    }
}
