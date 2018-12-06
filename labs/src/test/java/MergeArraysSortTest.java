import fillers.Fillers;
import org.junit.Before;
import org.junit.Test;
import sorters.MergeArraysSort;

import static org.junit.Assert.assertArrayEquals;

public class MergeArraysSortTest {

    private int[] array;
    private int[] mergeArray = new int[10];
    private MergeArraysSort mergeArraysSort = new MergeArraysSort();

    @Before
    public void generateSortedArrayTest(){
        array = Fillers.genSortedArray(50);
    }

    @Test(timeout = 1000)
    public void testTimeout(){
        mergeArraysSort.sort(array);
    }

    @Test
    public void testRelevant(){
        int[]bufArray = array;
        mergeArraysSort.sort(array);

        assertArrayEquals(bufArray, array);
    }

    @Test
    public void testCorrectSort(){
        int[] arrayRef = {0, 1, 2, 3, 4, 5};
        int[] array = {1, 3, 0, 2, 5, 4};

        mergeArraysSort.sort(array);

        assertArrayEquals(arrayRef, array);
    }

    @Test
    public void testMerge(){
        int[] arrayRef = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] leftArray = {0, 1, 2, 3, 4};
        int[] rightArray = {5, 6, 7, 8, 9};

        mergeArraysSort.mergeArrays(leftArray, rightArray, 5, 5, mergeArray);

        assertArrayEquals(arrayRef, mergeArray);
    }
}
