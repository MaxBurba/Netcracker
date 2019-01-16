package sorters.abstractsorters;

import sorters.MergeThread;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author Burba
 *
 * <p>
 *     Used by merge sorts to merge two sorted parts.
 * </p>
 */
public abstract class MergeSorter extends AbstractSorter {

    /**
     * Merges two arrays into one.
     * @param leftArray first array.
     * @param rightArray second array.
     * @param mergedArray array into which the result of merge is written.
     */
    private void mergeArrays(int[] leftArray, int[] rightArray, int[] mergedArray){
        int i = 0;
        int j = 0;
        int k = 0;

        int leftLength = leftArray.length;
        int rightLength = rightArray.length;

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

    private Queue<int[]> chunkArray(int[] array, int chunkNumber){

        Queue<int[]> arrayParts = new LinkedList<>();

        int chunkSize = array.length / chunkNumber;
        int chunkAlterSize = chunkSize + 1;
        int alterCounter = array.length % chunkNumber;
        int offset = alterCounter;

        for(int i = 0; i < chunkNumber; i++){
            if(alterCounter > 0){
                arrayParts.add(chunk(array, chunkAlterSize, i, 0));
                alterCounter--;
            } else {
                arrayParts.add(chunk(array, chunkSize, i, offset));
            }
        }
        return arrayParts;
    }

    private int[] chunk(int[] array, int size, int index, int offset){
        int start = index * size + offset;

        int[] temp = new int[size];

        System.arraycopy(array, start, temp, 0, size);

        return temp;
    }

    protected void invoke(int[] array, AbstractSorter sorter){

        final int NUM_OF_TASKS = Runtime.getRuntime().availableProcessors();

        Queue<int[]> arrayParts = chunkArray(array, NUM_OF_TASKS);

        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_TASKS);
        List<Future<int[]>> futures = new ArrayList<>();

        for(int i = 0; i < NUM_OF_TASKS; i++){
            Future<int[]> future = executor.submit(new MergeThread(arrayParts.remove(), sorter));
            futures.add(future);
        }

        int[] leftArray = {};
        int[] tempArray = {};
        int[] emptyArray = {};

        for(Future<int[]> future : futures){
            try {
                int[] rightArray = future.get();
                tempArray = new int[leftArray.length + rightArray.length];

                mergeArrays(leftArray, rightArray, tempArray);
                leftArray = tempArray;

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        mergeArrays(tempArray, emptyArray, array);
        executor.shutdown();
    }
}
