import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;


public class BubbleSortComparator<T extends Comparable<T>> {

    // array of integers
    public Integer[] intArray;
    public Integer[] intSortedArray;
    public Integer[] intReverseSortedArray;

    // array of strings
    private String[] stringArray;
    private String[] stringSortedArray;
    private String[] stringReverseSortedArray;



    private void initIntArrays(final int length) {
        intArray = new Integer[length];
        // initialize intArray with random numbers
        for (int i = 0; i < length; i++) {
            intArray[i] = (int) (Math.random() * 100);
        }

        intSortedArray = Arrays.copyOf(intArray, intArray.length);
        Arrays.sort(intSortedArray);

        intReverseSortedArray = Arrays.copyOf(intSortedArray, intSortedArray.length);
        Collections.reverse(Arrays.asList(intReverseSortedArray));
    }


    private void initStringArrays(final int length, final int stringLength) {
        stringArray = new String[length];
        // initialize stringArray with random strings
        for (int i = 0; i < length; i++) {
            stringArray[i] = this.randomString(stringLength);
        }

        stringSortedArray = Arrays.copyOf(stringArray, stringArray.length);
        Arrays.sort(stringSortedArray);

        stringReverseSortedArray = Arrays.copyOf(stringSortedArray, stringSortedArray.length);
        Collections.reverse(Arrays.asList(stringReverseSortedArray));
    }


    /**
     * Generate a random alphanumeric string.
     * @param length the length of the string
     * @return the random string
     */
    private String randomString(final int length) {
        final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            final int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    
    public double measureBubbleSortPassPerItem(Sorter<T> sorter, final T[] items, final int numberOfTimes, final int warmUp) {

        // skip the first few runs
        for (int i = 0; i < warmUp; i++) {
            sorter.sort(Arrays.copyOf(items, items.length));
        }

        // measure the start time
        long startTime = System.nanoTime();

        // run the sort for the specified number of times
        for (int i = 0; i < numberOfTimes; i++) {
            sorter.sort(Arrays.copyOf(items, items.length));
        }

        // measure the end time
        long endTime = System.nanoTime();


        long startCopyTime = System.nanoTime();
        for (int i = 0; i < numberOfTimes; i++) {
            Arrays.copyOf(items, items.length);
        }
        long endCopyTime = System.nanoTime();


        long sortTotalTime = endTime - startTime - (endCopyTime - startCopyTime);

        return (double) (sortTotalTime) / numberOfTimes;
    }

    private void printAsCSV(final String sortingAlgo, final String type, final String arr, final int arraySize, final double time) {
        System.out.println(sortingAlgo + "," + type + "," + arr + "," + arraySize + "," + time);
    }

    private static void logToCSV(final String file, final String sortingAlgo, final String type, final String arr, final int arraySize, final double time) {
        logToCSV(file, sortingAlgo + "," + type + "," + arr + "," + arraySize + "," + time);
    }

    private static void logToCSV(final String file, final String str) {
        try {
            final FileWriter fw = new FileWriter(file, true);
            fw.write(str + "\n");
            fw.close();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void compareWithInteger(final int length, final int numberOfTimes, final int skipFirstN, final String filename) {
        BubbleSortComparator<Integer> intComp = new BubbleSortComparator<Integer>();

        // algorithm to be tested
        BubbleSortPassPerItem<Integer> sorter1 = new BubbleSortPassPerItem<Integer>();
        BubbleSortUntilNoChange<Integer> sorter2 = new BubbleSortUntilNoChange<Integer>();
        BubbleSortWhileNeeded<Integer> sorter3 = new BubbleSortWhileNeeded<Integer>();

        // initialize the arrays
        intComp.initIntArrays(length);

        Sorter<Integer>[] sorters =  new Sorter[] {sorter1, sorter2, sorter3};
        Integer[][] arrays = new Integer[][] {intComp.intArray, intComp.intSortedArray, intComp.intReverseSortedArray};

        for (Sorter<Integer> sorter : sorters) {
            for (int i = 0; i < arrays.length; i++) {
                double time = intComp.measureBubbleSortPassPerItem(sorter, arrays[i], numberOfTimes, skipFirstN);
                logToCSV(filename, sorter.getClass().getSimpleName(), "Integer", i == 0 ? "random" : i == 1 ? "sorted" : "reverse", length, time);
                // intComp.printAsCSV(sorter.getClass().getSimpleName(), "Integer", i == 0 ? "random" : i == 1 ? "sorted" : "reverse", length, time);
            }
        }
    }



    public static void compareWithString(final int length, final int numberOfTimes, final int skipFirstN, final String filename) {
        BubbleSortComparator<String> strComp = new BubbleSortComparator<String>();

        // algorithm to be tested
        BubbleSortPassPerItem<String> sorter1 = new BubbleSortPassPerItem<String>();
        BubbleSortUntilNoChange<String> sorter2 = new BubbleSortUntilNoChange<String>();
        BubbleSortWhileNeeded<String> sorter3 = new BubbleSortWhileNeeded<String>();

        // initialize the arrays
        strComp.initStringArrays(length, 10);

        Sorter<String>[] sorters =  new Sorter[] {sorter1, sorter2, sorter3};
        String[][] arrays = new String[][] {strComp.stringArray, strComp.stringSortedArray, strComp.stringReverseSortedArray};

        for (Sorter<String> sorter : sorters) {
            for (int i = 0; i < arrays.length; i++) {
                double time = strComp.measureBubbleSortPassPerItem(sorter, arrays[i], numberOfTimes, skipFirstN);
                logToCSV(filename, sorter.getClass().getSimpleName(), "String", i == 0 ? "random" : i == 1 ? "sorted" : "reverse", length, time);
                // strComp.printAsCSV(sorter.getClass().getSimpleName(), "String", i == 0 ? "random" : i == 1 ? "sorted" : "reverse", length, time);
            }
        }
    }



    public static void main(String[] args) {

        final int N_TIMES = 1000;
        final int SKIP_FIRST_N = 100;
        final String FILE = "results.csv";

        // print the header
        // System.out.println("Algorithm,Type,Array,Size,Time");
        logToCSV(FILE, "Algorithm,Type,Array,Size,Time");
        //

        // compare the algorithms with integer arrays
        Integer[] sizes = new Integer[] {10, 100, 1000, 10000};
        for (Integer size : sizes) {
            compareWithInteger(size, N_TIMES, SKIP_FIRST_N, FILE);
        }

        // compare the algorithms with string arrays
        for (Integer size : sizes) {
            compareWithString(size, N_TIMES, SKIP_FIRST_N, FILE);
        }


























        // BubbleSortPassPerItem<Integer> intSorter = new BubbleSortPassPerItem<Integer>();
        // BubbleSortUntilNoChange<Integer> intSorter2 = new BubbleSortUntilNoChange<Integer>();
        // BubbleSortWhileNeeded<Integer> intSorter3 = new BubbleSortWhileNeeded<Integer>();

        // // test the int array
        // intCmop.initIntArrays(1000);

        // BubbleSortComparator.compareWithInteger(100, 100, 10);
        



        // BubbleSortComparator<String> stringCmop = new BubbleSortComparator<String>();
        // BubbleSortPassPerItem<String> stringSorter = new BubbleSortPassPerItem<String>();
        // BubbleSortUntilNoChange<String> stringSorter2 = new BubbleSortUntilNoChange<String>();
        // BubbleSortWhileNeeded<String> stringSorter3 = new BubbleSortWhileNeeded<String>();

        // // test the string array
        // stringCmop.initStringArrays(1000, 5, 10);

        // double resultString = stringCmop.measureBubbleSortPassPerItem(stringSorter, stringCmop.stringArray, 1000, 100);
        // System.out.println("The average time for 1000 string items is " + resultString + " nanoseconds");
        // double resultStringSorted = stringCmop.measureBubbleSortPassPerItem(stringSorter, stringCmop.stringSortedArray, 1000, 100);
        // System.out.println("The average time for 1000 sorted string items is " + resultStringSorted + " nanoseconds");
        // double resultStringReverseSorted = stringCmop.measureBubbleSortPassPerItem(stringSorter, stringCmop.stringReverseSortedArray, 1000, 100);
        // System.out.println("The average time for 1000 reverse sorted string items is " + resultStringReverseSorted + " nanoseconds");

        // double resultString2 = stringCmop.measureBubbleSortPassPerItem(stringSorter2, stringCmop.stringArray, 1000, 100);
        // System.out.println("The average time for 1000 string items is " + resultString2 + " nanoseconds");
        // double resultStringSorted2 = stringCmop.measureBubbleSortPassPerItem(stringSorter2, stringCmop.stringSortedArray, 1000, 100);
        // System.out.println("The average time for 1000 sorted string items is " + resultStringSorted2 + " nanoseconds");
        // double resultStringReverseSorted2 = stringCmop.measureBubbleSortPassPerItem(stringSorter2, stringCmop.stringReverseSortedArray, 1000, 100);
        // System.out.println("The average time for 1000 reverse sorted string items is " + resultStringReverseSorted2 + " nanoseconds");

        // double resultString3 = stringCmop.measureBubbleSortPassPerItem(stringSorter3, stringCmop.stringArray, 1000, 100);
        // System.out.println("The average time for 1000 string items is " + resultString3 + " nanoseconds");
        // double resultStringSorted3 = stringCmop.measureBubbleSortPassPerItem(stringSorter3, stringCmop.stringSortedArray, 1000, 100);
        // System.out.println("The average time for 1000 sorted string items is " + resultStringSorted3 + " nanoseconds");
        // double resultStringReverseSorted3 = stringCmop.measureBubbleSortPassPerItem(stringSorter3, stringCmop.stringReverseSortedArray, 1000, 100);
        // System.out.println("The average time for 1000 reverse sorted string items is " + resultStringReverseSorted3 + " nanoseconds");









        // Array of integers
        // Array of strings
        // Array of doubles
        // Array of objects (small objects)
        // Array of objects (large objects)
        // Array of duplicates (like an array of 1s or 2s)

        // for each of the above we can start with:
        // 1. Random array
        // 2. Sorted array
        // 3. Reverse sorted array


    }
}