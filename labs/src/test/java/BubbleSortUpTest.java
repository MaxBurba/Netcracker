import fillers.Fillers;
import org.junit.Before;
import org.junit.Test;
import sorters.BubbleSortUp;

import static org.junit.Assert.assertArrayEquals;

public class BubbleSortUpTest {

    private int[] array;
    private BubbleSortUp bubbleSortUp = new BubbleSortUp();

    @Before
    public void generateSortedArrayTest(){
        array = Fillers.genSortedArray(20);
    }

    @Test(timeout = 1000)
    public void testTimeout(){
        bubbleSortUp.sort(array);
    }

    @Test
    public void testRelevantSort(){
        int[]bufArray = array;
        bubbleSortUp.sort(array);

        assertArrayEquals(bufArray, array);
    }

    @Test
    public void testCorrectSort(){
        int[] arrayRef = {0, 1, 2, 3, 4, 5};
        int[] array = {1, 3, 0, 2, 5, 4};

        bubbleSortUp.sort(array);

        assertArrayEquals(arrayRef, array);
    }
}
