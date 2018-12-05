package sorters.abstractsorters;

/**
 * @author Burba
 *
 * <p>
 *      An abstract class containing only an abstract method {@link #sort(int[])}.
 * </p>
 */
public abstract class AbstractSorter {

    /**
     * Should be used for implementation of sorting.
     * @param array array which is needed to be sorted.
     */
    public abstract void sort(int[] array);
}
