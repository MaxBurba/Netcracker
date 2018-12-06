import fillers.Fillers;
import org.junit.Before;
import org.junit.Test;
import sorters.BubbleSortDown;

import static org.junit.Assert.assertArrayEquals;

public class BubbleSortDownTest {

    private int[] array;
    private BubbleSortDown bubbleSortDown = new BubbleSortDown();

    @Before
    public void generateSortedArrayTest(){
        array = Fillers.genSortedArray(20);
    }

    @Test(timeout = 1000)
    public void testTimeout(){
        bubbleSortDown.sort(array);
    }

    @Test
    public void testRelevantSort(){
        int[]bufArray = array;
        bubbleSortDown.sort(array);

        assertArrayEquals(bufArray, array);
    }

    @Test
    public void testCorrectSort(){
        int[] arrayRef = {0, 1, 2, 3, 4, 5};
        int[] array = {1, 3, 0, 2, 5, 4};

        bubbleSortDown.sort(array);

        assertArrayEquals(arrayRef, array);
    }

    @Test
    public void testSwap(){
        int[] arrayRef = {0, 1, 2, 3, 4, 5};
        int[] array = {0, 2, 1, 3, 4, 5};

        bubbleSortDown.swap(array, 2);

        assertArrayEquals(arrayRef, array);
    }
}
