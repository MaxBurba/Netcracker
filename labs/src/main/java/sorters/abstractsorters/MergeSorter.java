package sorters.abstractsorters;

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
     * @param leftLength size of the first array.
     * @param rightLength size of the second array.
     * @param mergedArray array into which the result of merge is written.
     */
    public void mergeArrays(int[] leftArray, int[] rightArray, int leftLength, int rightLength, int[] mergedArray){
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
