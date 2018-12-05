package sorters;

import java.util.Arrays;

public class Sorters {

    private int[] numbers;
    private int[] leftArray;
    private int[] rightArray;

    public void bubbleSortUp(int[] array){

        for(int i = 0; i < array.length; i++){
            for(int j = 1; j < (array.length - i); j++){
                if(array[j - 1] > array[j]){
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public void bubbleSortDown(int[] array){
        for(int i = 0; i < array.length; i++){
            for(int j = array.length - 1; j > i; j--){
                if(array[j - 1] > array[j]){
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public void quickSort(int[] array) {
        this.numbers = array;
        sort(0, array.length - 1);
    }

    private void sort(int low, int high) {

        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high - low) / 2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the array.
            // As we are done we can increase i and j
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            sort(low, j);
        if (i < high)
            sort(i, high);
    }

    private void swap(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public void sortWithArrays(int[] array){
        Arrays.sort(array);
    }

    public void mergeBubbleUp(int[] array){

        int low = 0;
        int high = array.length - 1;

        // Check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;

            leftArray = Arrays.copyOfRange(array, 0, middle + 1);
            rightArray = Arrays.copyOfRange(array, middle + 1, array.length);

            bubbleSortUp(leftArray);
            bubbleSortUp(rightArray);

            // Combine them both
            mergeArrays(leftArray, rightArray, leftArray.length, rightArray.length, array);
        }
    }

    public void mergeBubbleDown(int[] array){
        int low = 0;
        int high = array.length - 1;

        // Check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;

            leftArray = Arrays.copyOfRange(array, 0, middle + 1);
            rightArray = Arrays.copyOfRange(array, middle + 1, array.length);

            bubbleSortDown(leftArray);
            bubbleSortDown(rightArray);

            // Combine them both
            mergeArrays(leftArray, rightArray, leftArray.length, rightArray.length, array);
        }
    }

    public void mergeQuickSort(int[] array){
        int low = 0;
        int high = array.length - 1;

        // Check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;

            leftArray = Arrays.copyOfRange(array, 0, middle + 1);
            rightArray = Arrays.copyOfRange(array, middle + 1, array.length);

            quickSort(leftArray);
            quickSort(rightArray);

            // Combine them both
            mergeArrays(leftArray, rightArray, leftArray.length, rightArray.length, array);
        }
    }

    public void mergeWithArrays(int[] array){
        int low = 0;
        int high = array.length - 1;

        // Check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;

            leftArray = Arrays.copyOfRange(array, 0, middle + 1);
            rightArray = Arrays.copyOfRange(array, middle + 1, array.length);

            sortWithArrays(leftArray);
            sortWithArrays(rightArray);

            // Combine them both
            mergeArrays(leftArray, rightArray, leftArray.length, rightArray.length, array);
        }
    }

    private void mergeArrays(int[] leftArray, int[] rightArray, int leftLength, int rightLength, int[] mergedArray){
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftLength && j < rightLength){
            if(leftArray[i] < rightArray[j]){
                mergedArray[k++] = leftArray[i++];
            } else {
                mergedArray[k++] = rightArray[j++];
            }
        }
        while (i < leftLength){
            mergedArray[k++] = leftArray[i++];
        }
        while (j < rightLength){
            mergedArray[k++] = rightArray[j++];
        }
    }
}
