import fillers.Fillers;
import org.junit.Before;
import org.junit.Test;
import sorters.ArraysSort;

import static org.junit.Assert.assertArrayEquals;

public class ArraysSortTest {

    private int[] array;
    private ArraysSort arraysSort = new ArraysSort();

    @Before
    public void generateSortedArrayTest(){
        array = Fillers.genSortedArray(20);
    }

    @Test(timeout = 1000)
    public void testArraysSortTimeout(){
        arraysSort.sort(array);
    }

    @Test
    public void testRelevantSort(){
        int[]bufArray = array;
        arraysSort.sort(array);

        assertArrayEquals(bufArray, array);
    }

    @Test
    public void testCorrectSort(){
        int[] arrayRef = {0, 1, 2, 3, 4, 5};
        int[] array = {1, 3, 0, 2, 5, 4};

        arraysSort.sort(array);

        assertArrayEquals(arrayRef, array);
    }
}
