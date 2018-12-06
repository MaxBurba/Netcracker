import fillers.Fillers;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FillersTest {
    @Test(expected = IllegalArgumentException.class)
    public void testGenSortedArrayException() {
        int[] array = Fillers.genSortedArray(-4);
    }

    @Test
    public void testGenSortedArrayNotNull(){
        int[] array = Fillers.genSortedArray(10);
        assertNotNull(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenReversedArrayException() {
        int[] array = Fillers.genReversedArray(0);
    }

    @Test
    public void testGenReversedArrayNotNull(){
        int[] array = Fillers.genReversedArray(10);
        assertNotNull(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenArrayWithElementException() {
        int[] array = Fillers.genArrayWithElement(-4);
    }

    @Test
    public void testGenArrayWithElementNotNull(){
        int[] array = Fillers.genArrayWithElement(10);
        assertNotNull(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenRandomArrayException() {
        int[] array = Fillers.genSortedArray(-4);
    }

    @Test
    public void testGenRandomArrayNotNull(){
        int[] array = Fillers.genSortedArray(10);
        assertNotNull(array);
    }
}
