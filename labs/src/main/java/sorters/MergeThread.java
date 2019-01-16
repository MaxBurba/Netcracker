package sorters;

import sorters.abstractsorters.AbstractSorter;

import java.util.concurrent.Callable;

public class MergeThread implements  Callable<int[]> {

    private AbstractSorter sorter;
    private int[] array;

    public MergeThread(int[] array, AbstractSorter sorter){
        this.array = array;
        this.sorter = sorter;
    }

    @Override
    public int[] call() throws Exception {

        sorter.sort(array);

        return this.array;
    }
}
