import fillers.Fillers;
import org.junit.Before;
import org.junit.Test;
import sorters.MergeQuickSort;

import static org.junit.Assert.assertArrayEquals;

public class MergeQuickSortTest {

    private int[] array;
    private MergeQuickSort mergeQuickSort = new MergeQuickSort();

    @Before
    public void generateSortedArrayTest(){
        array = Fillers.genSortedArray(50);
    }

    @Test(timeout = 1000)
    public void testTimeout(){
        mergeQuickSort.sort(array);
    }

    @Test
    public void testRelevant(){
        int[]bufArray = array;
        mergeQuickSort.sort(array);

        assertArrayEquals(bufArray, array);
    }

    @Test
    public void testCorrectSort(){
        int[] arrayRef = {0, 1, 2, 3, 4, 5};
        int[] array = {1, 3, 0, 2, 5, 4};

        mergeQuickSort.sort(array);

        assertArrayEquals(arrayRef, array);
    }
}
