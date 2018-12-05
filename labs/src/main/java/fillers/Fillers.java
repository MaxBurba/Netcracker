package fillers;

import analyzer.Analyzer;

import java.util.Random;

/**
 * @author Burba
 *
 * <p>
 *     Contains static methods of generating arrays.
 *     <br>Every method is marked with {@code @FillerAnnotation}.
 * </p>
 * @see FillerAnnotation
 */
public class Fillers {

    private static final int RANGE = 10;
    private static final int RAND_RANGE = 300;

    /**
     * <p>
     *     Generates sorted array.
     *     <br>Marked with custom {@code @FillerAnnotation}
     *     to find this method in {@link Analyzer#analyze()}.
     * </p>
     * @param size size of array to generate.
     * @return generated array.
     */
    @FillerAnnotation
    public static int[] genSortedArray(int size){

        Random random = new Random();

        if(size <= 0) throw new IllegalArgumentException("Size can't be less than 1");

        int[] array = new int[size];

        for(int i = 1; i < size; i++){
            array[i] = array[i - 1] + random.nextInt(RANGE);
        }
        return array;
    }

    /**
     * <p>
     *     Generates sorted array with random element in the end.
     *     <br>Marked with custom {@code @FillerAnnotation}
     *     to find this method in {@link Analyzer#analyze()}.
     * </p>
     * @param size size of array to generate.
     * @return generated array.
     */
    @FillerAnnotation
    public static int[] genArrayWithElement(int size){

        Random random = new Random();

        if(size <= 0) throw new IllegalArgumentException("Size can't be less than 1");

        int[] array = new int[size];

        for(int i = 1; i < size; i++){
            array[i] = array[i - 1] + random.nextInt(RANGE);
        }
        array[size - 1] = random.nextInt(10);

        return array;
    }

    /**
     * <p>
     *     Generates array sorted in descending order.
     *     <br>Marked with custom {@code @FillerAnnotation}
     *     to find this method in {@link Analyzer#analyze()}.
     * </p>
     * @param size size of array to generate.
     * @return generated array.
     */
    @FillerAnnotation
    public static int[] genReversedArray(int size){

        Random random = new Random();

        if(size <= 0) throw new IllegalArgumentException("Size can't be less than 1");

        int[] array = new int[size];

        for(int i = 1; i < size; i++){
            array[i] = array[i - 1] + random.nextInt(RANGE);
        }
        for(int i = 0; i < size / 2; i++){
            int temp = array[i];
            array[i] = array[size - i - 1];
            array[size - i - 1] = temp;
        }
        return array;
    }

    /**
     * <p>
     *     Generates random array.
     *     <br>Marked with custom {@code @FillerAnnotation}
     *     to find this method in {@link Analyzer#analyze()}.
     * </p>
     * @param size size of array to generate.
     * @return generated array.
     */
    @FillerAnnotation
    public static int[] genRandomArray(int size){

        Random random = new Random();

        if(size <= 0) throw new IllegalArgumentException("Size can't be less than 1");

        int[] array = new int[size];

        for(int i = 0; i < size; i++){
            array[i] = random.nextInt(RAND_RANGE);
        }
        return array;
    }
}
