import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to compare the performance of the three sorting algorithms:
 * BubbleSortPassPerItem, BubbleSortUntilNoChange and BubbleSortWhileNeeded.
 * @author Alessandro Gobbetti, Bojan Lazarevski
 */
public class BubbleSortComparator<T extends Comparable<T>> {

    // --------------------------------------------------------------------------------------------------------------------------------
    // GENERATE ARRAYS
    // --------------------------------------------------------------------------------------------------------------------------------
    /**
     * Generates a list of 3 integer arrays:
     * 1. Randomly generated array;
     * 2. Sorted array;
     * 3. Reverse sorted array.
     * @param length the number of elements in the array.
     * @return a list of 3 integer arrays.
     */
    private List<Integer[]> createIntArrays(final int length) {
        Integer[] intArray = new Integer[length];
        // initialize intArray with random numbers
        for (int i = 0; i < length; i++) {
            intArray[i] = (int) (Math.random() * 100);
        }

        Integer[] intSortedArray = Arrays.copyOf(intArray, intArray.length);
        Arrays.sort(intSortedArray);

        Integer[] intReverseSortedArray = Arrays.copyOf(intSortedArray, intSortedArray.length);
        Collections.reverse(Arrays.asList(intReverseSortedArray));

        // return a list of all arrays
        return Arrays.asList(intArray, intSortedArray, intReverseSortedArray);
    }


    /**
     * Generates a list of 3 string arrays:
     * 1. Randomly generated array;
     * 2. Sorted array;
     * 3. Reverse sorted array.
     * @param length the number of elements in the array.
     * @return a list of 3 string arrays.
     */
    private List<Double[]> createDoubleArrays(final int length) {
        Double[] doubleArray = new Double[length];
        // initialize doubleArray with random numbers
        for (int i = 0; i < length; i++) {
            doubleArray[i] = Math.random() * 100;
        }

        Double[] doubleSortedArray = Arrays.copyOf(doubleArray, doubleArray.length);
        Arrays.sort(doubleSortedArray);

        Double[] doubleReverseSortedArray = Arrays.copyOf(doubleSortedArray, doubleSortedArray.length);
        Collections.reverse(Arrays.asList(doubleReverseSortedArray));

        // return a list of all arrays
        return Arrays.asList(doubleArray, doubleSortedArray, doubleReverseSortedArray);
    }


    /**
     * Generates a list of 3 string arrays:
     * 1. Randomly generated array;
     * 2. Sorted array;
     * 3. Reverse sorted array .
     * @param length the number of elements in the array.
     * @return a list of 3 string arrays.
     */
    private List<String[]> createStringArrays(final int length, final int stringLength) {
        String[] stringArray = new String[length];
        // initialize stringArray with random strings
        for (int i = 0; i < length; i++) {
            stringArray[i] = this.randomString(stringLength);
        }

        String[] stringSortedArray = Arrays.copyOf(stringArray, stringArray.length);
        Arrays.sort(stringSortedArray);

        String[] stringReverseSortedArray = Arrays.copyOf(stringSortedArray, stringSortedArray.length);
        Collections.reverse(Arrays.asList(stringReverseSortedArray));

        // return a list of all arrays
        return Arrays.asList(stringArray, stringSortedArray, stringReverseSortedArray);
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


    /**
     * Generates a list of 3 DummySmallObject arrays:
     * 1. Randomly generated array;
     * 2. Sorted array;
     * 3. Reverse sorted array.
     * @param length the number of elements in the array.
     * @return a list of 3 DummySmallObject arrays.
     */
    private List<DummySmallObject[]> createDummySmallObjectArrays(final int length) {
        DummySmallObject[] dummySmallObjectArray = new DummySmallObject[length];
        // initialize dummySmallObjectArray with random numbers
        for (int i = 0; i < length; i++) {
            dummySmallObjectArray[i] = new DummySmallObject((int) (Math.random() * 100));
        }

        DummySmallObject[] dummySmallObjectSortedArray = Arrays.copyOf(dummySmallObjectArray, dummySmallObjectArray.length);
        Arrays.sort(dummySmallObjectSortedArray);

        DummySmallObject[] dummySmallObjectReverseSortedArray = Arrays.copyOf(dummySmallObjectSortedArray, dummySmallObjectSortedArray.length);
        Collections.reverse(Arrays.asList(dummySmallObjectReverseSortedArray));

        // return a list of all arrays
        return Arrays.asList(dummySmallObjectArray, dummySmallObjectSortedArray, dummySmallObjectReverseSortedArray);
    }



    /**
     * Generates a list of 3 DummyLargeObject arrays:
     * 1. Randomly generated array;
     * 2. Sorted array;
     * 3. Reverse sorted array.
     * @param length the number of elements in the array.
     * @return a list of 3 DummyLargeObject arrays.
     */
    private List<DummyLargeObject[]> createDummyLargeObjectArrays(final int length) {
        DummyLargeObject[] dummyLargeObjectArray = new DummyLargeObject[length];
        // initialize dummyLargeObjectArray with random DummySmallObjects
        for (int i = 0; i < length; i++) {
            int value = (int)(Math.random() * 100);
            List<String> strings = new ArrayList<String>(Collections.nCopies(100, this.randomString(100)));
            dummyLargeObjectArray[i] = new DummyLargeObject(value, strings);
        }

        DummyLargeObject[] dummyLargeObjectSortedArray = Arrays.copyOf(dummyLargeObjectArray, dummyLargeObjectArray.length);
        Arrays.sort(dummyLargeObjectSortedArray);

        DummyLargeObject[] dummyLargeObjectReverseSortedArray = Arrays.copyOf(dummyLargeObjectSortedArray, dummyLargeObjectSortedArray.length);
        Collections.reverse(Arrays.asList(dummyLargeObjectReverseSortedArray));

        // return a list of all arrays
        return Arrays.asList(dummyLargeObjectArray, dummyLargeObjectSortedArray, dummyLargeObjectReverseSortedArray);
    }








    // --------------------------------------------------------------------------------------------------------------------------------
    // MEASURE TIME
    // --------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Measure the time it takes to sort an array given a Sorter algorithm and an array.
     * The method will run the algorithm a warmUp number of times to warm up the JVM and then
     * run it again a numberOfTimes times to measure the time. 
     * @param sorter the sorter algorithm to use.
     * @param items the array to sort.
     * @param numberOfTimes the number of times to run the algorithm.
     * @param warmUp the number of times to run the algorithm before measuring the time.
     * @return the average time it takes to sort the array.
     */
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


    /**
     * The method creates 3 integer arrays (random, sorted, reverse sorted) and then
     * measures the time it takes to sort the 3 arrays using 3 Sorting algorithms:
     * 1. BubbleSortPassPerItem;
     * 2. BubbleSortUntilNoChange;
     * 3. BubbleSortWhileNeeded.
     * The method will run the algorithms a warmUp number of times to warm up the JVM and then
     * run them again a numberOfTimes times to measure the time.
     * The results will be saved in a given CSV file.
     * @param length the number of elements in each array.
     * @param numberOfTimes the number of times to run the algorithm.
     * @param skipFirstN the number of times to run the algorithm before measuring the time.
     * @param filename the name of the CSV file to save the results.
     */
    public static void compareWithInteger(final int length, final int numberOfTimes, final int skipFirstN, final String filename) {
        BubbleSortComparator<Integer> intComp = new BubbleSortComparator<Integer>();

        // algorithm to be tested
        Sorter<Integer> sorter1 = new BubbleSortPassPerItem<Integer>();
        Sorter<Integer> sorter2 = new BubbleSortUntilNoChange<Integer>();
        Sorter<Integer> sorter3 = new BubbleSortWhileNeeded<Integer>();
        List<Sorter<Integer>> sorters = new ArrayList<Sorter<Integer>>(List.of(sorter1, sorter2, sorter3));

        // initialize the arrays
        List<Integer[]> arrays = intComp.createIntArrays(length);

        for (Sorter<Integer> sorter : sorters) {
            for (int i = 0; i < arrays.size(); i++) {
                double time = intComp.measureBubbleSortPassPerItem(sorter, arrays.get(i), numberOfTimes, skipFirstN);
                logToCSV(filename, sorter.getClass().getSimpleName(), "Integer", i == 0 ? "random" : i == 1 ? "sorted" : "reverse", length, time);
            }
        }
    }

    /**
     * The method creates 3 arrays of doubles (random, sorted, reverse sorted) and then
     * measures the time it takes to sort the 3 arrays using 3 Sorting algorithms:
     * 1. BubbleSortPassPerItem;
     * 2. BubbleSortUntilNoChange;
     * 3. BubbleSortWhileNeeded.
     * The method will run the algorithms a warmUp number of times to warm up the JVM and then
     * run them again a numberOfTimes times to measure the time.
     * The results will be saved in a given CSV file.
     * @param length the number of elements in each array.
     * @param numberOfTimes the number of times to run the algorithm.
     * @param skipFirstN the number of times to run the algorithm before measuring the time.
     * @param filename the name of the CSV file to save the results.
     */
    public static void compareWithDouble(final int length, final int numberOfTimes, final int skipFirstN, final String filename) {
        BubbleSortComparator<Double> doubleComp = new BubbleSortComparator<Double>();

        // algorithm to be tested
        Sorter<Double> sorter1 = new BubbleSortPassPerItem<Double>();
        Sorter<Double> sorter2 = new BubbleSortUntilNoChange<Double>();
        Sorter<Double> sorter3 = new BubbleSortWhileNeeded<Double>();
        List<Sorter<Double>> sorters = new ArrayList<Sorter<Double>>(List.of(sorter1, sorter2, sorter3));

        // initialize the arrays
        List<Double[]> arrays = doubleComp.createDoubleArrays(length);

        for (Sorter<Double> sorter : sorters) {
            for (int i = 0; i < arrays.size(); i++) {
                double time = doubleComp.measureBubbleSortPassPerItem(sorter, arrays.get(i), numberOfTimes, skipFirstN);
                logToCSV(filename, sorter.getClass().getSimpleName(), "Double", i == 0 ? "random" : i == 1 ? "sorted" : "reverse", length, time);
            }
        }
    }


    /**
     * The method creates 3 arrays of strings of a given length (random, sorted, reverse sorted) and then
     * measures the time it takes to sort the 3 arrays using 3 Sorting algorithms:
     * 1. BubbleSortPassPerItem;
     * 2. BubbleSortUntilNoChange;
     * 3. BubbleSortWhileNeeded.
     * The method will run the algorithms a warmUp number of times to warm up the JVM and then
     * run them again a numberOfTimes times to measure the time.
     * The results will be saved in a given CSV file.
     * @param length the number of elements in each array.
     * @param numberOfTimes the number of times to run the algorithm.
     * @param skipFirstN the number of times to run the algorithm before measuring the time.
     * @param filename the name of the CSV file to save the results.
     * @param stringLength the length of the strings in the array.
     */
    public static void compareWithString(final int length, final int numberOfTimes, final int skipFirstN, final String filename, final int stringLength) {
        BubbleSortComparator<String> strComp = new BubbleSortComparator<String>();

        // algorithm to be tested
        Sorter<String> sorter1 = new BubbleSortPassPerItem<String>();
        Sorter<String> sorter2 = new BubbleSortUntilNoChange<String>();
        Sorter<String> sorter3 = new BubbleSortWhileNeeded<String>();
        List<Sorter<String>> sorters = new ArrayList<Sorter<String>>(List.of(sorter1, sorter2, sorter3));

        // initialize the arrays
        List<String[]> arrays = strComp.createStringArrays(length, stringLength);

        for (Sorter<String> sorter : sorters) {
            for (int i = 0; i < arrays.size(); i++) {
                double time = strComp.measureBubbleSortPassPerItem(sorter, arrays.get(i), numberOfTimes, skipFirstN);
                logToCSV(filename, sorter.getClass().getSimpleName(), "String", i == 0 ? "random" : i == 1 ? "sorted" : "reverse", length, time);
            }
        }
    }

    /**
     * The method creates 3 arrays of DummySmallObjects (random, sorted, reverse sorted) and then
     * measures the time it takes to sort the 3 arrays using 3 Sorting algorithms:
     * 1. BubbleSortPassPerItem;
     * 2. BubbleSortUntilNoChange;
     * 3. BubbleSortWhileNeeded.
     * The method will run the algorithms a warmUp number of times to warm up the JVM and then
     * run them again a numberOfTimes times to measure the time.
     * The results will be saved in a given CSV file.
     * @param length the number of elements in each array.
     * @param numberOfTimes the number of times to run the algorithm.
     * @param skipFirstN the number of times to run the algorithm before measuring the time.
     * @param filename the name of the CSV file to save the results.
     */
    public static void compareWithDummySmallObject(final int length, final int numberOfTimes, final int skipFirstN, final String filename) {
        BubbleSortComparator<DummySmallObject> dsoComp = new BubbleSortComparator<DummySmallObject>();

        // algorithm to be tested
        Sorter<DummySmallObject> sorter1 = new BubbleSortPassPerItem<DummySmallObject>();
        Sorter<DummySmallObject> sorter2 = new BubbleSortUntilNoChange<DummySmallObject>();
        Sorter<DummySmallObject> sorter3 = new BubbleSortWhileNeeded<DummySmallObject>();
        List<Sorter<DummySmallObject>> sorters = new ArrayList<Sorter<DummySmallObject>>(List.of(sorter1, sorter2, sorter3));

        // initialize the arrays
        List<DummySmallObject[]> arrays = dsoComp.createDummySmallObjectArrays(length);

        for (Sorter<DummySmallObject> sorter : sorters) {
            for (int i = 0; i < arrays.size(); i++) {
                double time = dsoComp.measureBubbleSortPassPerItem(sorter, arrays.get(i), numberOfTimes, skipFirstN);
                logToCSV(filename, sorter.getClass().getSimpleName(), "DummySmallObject", i == 0 ? "random" : i == 1 ? "sorted" : "reverse", length, time);
            }
        }
    }

    /**
     * The method creates 3 arrays of DummyLargeObjects (random, sorted, reverse sorted) and then
     * measures the time it takes to sort the 3 arrays using 3 Sorting algorithms:
     * 1. BubbleSortPassPerItem;
     * 2. BubbleSortUntilNoChange;
     * 3. BubbleSortWhileNeeded.
     * The method will run the algorithms a warmUp number of times to warm up the JVM and then
     * run them again a numberOfTimes times to measure the time.
     * The results will be saved in a given CSV file.
     * @param length the number of elements in each array.
     * @param numberOfTimes the number of times to run the algorithm.
     * @param skipFirstN the number of times to run the algorithm before measuring the time.
     * @param filename the name of the CSV file to save the results.
     */
    public static void compareWithDummyLargeObject(final int length, final int numberOfTimes, final int skipFirstN, final String filename) {
        BubbleSortComparator<DummyLargeObject> dloComp = new BubbleSortComparator<DummyLargeObject>();

        // algorithm to be tested
        Sorter<DummyLargeObject> sorter1 = new BubbleSortPassPerItem<DummyLargeObject>();
        Sorter<DummyLargeObject> sorter2 = new BubbleSortUntilNoChange<DummyLargeObject>();
        Sorter<DummyLargeObject> sorter3 = new BubbleSortWhileNeeded<DummyLargeObject>();
        List<Sorter<DummyLargeObject>> sorters = new ArrayList<Sorter<DummyLargeObject>>(List.of(sorter1, sorter2, sorter3));

        // initialize the arrays
        List<DummyLargeObject[]> arrays = dloComp.createDummyLargeObjectArrays(length);

        for (Sorter<DummyLargeObject> sorter : sorters) {
            for (int i = 0; i < arrays.size(); i++) {
                double time = dloComp.measureBubbleSortPassPerItem(sorter, arrays.get(i), numberOfTimes, skipFirstN);
                logToCSV(filename, sorter.getClass().getSimpleName(), "DummyLargeObject", i == 0 ? "random" : i == 1 ? "sorted" : "reverse", length, time);
            }
        }
    }



    // --------------------------------------------------------------------------------------------------------------------------------
    // LOGGING
    // --------------------------------------------------------------------------------------------------------------------------------

    /**
     * The method logs the results of the sorting algorithm to a CSV file.
     * @param file the name of the CSV file to save the results.
     * @param sortingAlgo the name of the sorting algorithm.
     * @param type the type of the array.
     * @param arr the type of the array (random, sorted, reverse sorted).
     * @param arraySize the size of the array.
     * @param time the time it took to sort the array.
     */
    private static void logToCSV(final String file, final String sortingAlgo, final String type, final String arr, final int arraySize, final double time) {
        logToCSV(file, sortingAlgo + "," + type + "," + arr + "," + arraySize + "," + time);
    }

    /**
     * The method logs the results of the sorting algorithm to a CSV file.
     * @param file the name of the CSV file to save the results.
     * @param str the line to be logged.
     */
    private static void logToCSV(final String file, final String str) {
        try {
            final FileWriter fw = new FileWriter(file, true);
            fw.write(str + "\n");
            fw.close();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * The method initializes the CSV file with the header.
     * @param file the name of the CSV file to save the results.
     */
    private static void initCSV(final String file) {
        // check if file exists and it is not empty
        File f = new File(file);
        if (f.exists() && f.length() > 0) {
            // ask user if he wants to overwrite the file
            System.out.print("\033[0;31m" + "File " + file + " already exists. Do you want to overwrite it? [y/(n)]: "+"\033[0m");
            try (Scanner sc = new Scanner(System.in)) { // wait for user response
                String answer = sc.nextLine();
                if (answer.equals("y") || answer.equals("Y")) {
                    System.out.println("\r\033[F\r\033[2K\rOverwriting file " + file + "...");
                    // delete the file
                    f.delete();
                    // recreate the file and write the header
                    logToCSV(file, "Algorithm,Type,Array,Size,Time");
                } else {
                    // exit program
                    System.out.println("Exiting program...");
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Error reading from console: " + e.getMessage());
            }
        } else {
            // create file and write header
            logToCSV(file, "sortingAlgo,type,arr,arraySize,time");
        }
    }


    // --------------------------------------------------------------------------------------------------------------------------------
    // MAIN
    // --------------------------------------------------------------------------------------------------------------------------------

    /**
     * The main method runs the sorting algorithms on different arrays of different sizes.
     * The results are saved in a CSV file. The file path can be passed as a command line argument.
     * If no file path is passed, the results will be saved in the file "./results.csv".
     * 
     * The program will run the algorithms 100 number of times to warm up the JVM and then
     * run them again a 1000 times to measure the time.
     * 
     * The program will run the algorithms on arrays of size 100, 1000, 10000, 100000.
     * 
     * The program will run the algorithms on arrays of type Integer, Double, String, DummySmallObject, DummyLargeObject.
     * 
     * The program will run the algorithms on random, sorted and reverse sorted arrays.
     * 
     * @param args the command line arguments.
     */
    public static void main(String[] args) {

        final int N_TIMES = 1000;
        final int SKIP_FIRST_N = 100;
        // read the filename from the arguments or use the default one
        final String FILE = args.length > 0 ? args[0] : "results.csv";
        Integer[] sizes = new Integer[] {10, 100, 1000, 10000};

        // print the header
        initCSV(FILE);

        // compare the algorithms with integer arrays
        System.out.print("Comparing algorithms with Integer arrays: ");
        for (Integer size : sizes) {
            System.out.print(size + " ");
            compareWithInteger(size, N_TIMES, SKIP_FIRST_N, FILE);
        }

        // compare the algorithms with double arrays
        System.out.print("\r\033[2K\rComparing algorithms with Double arrays: ");
        for (Integer size : sizes) {
            System.out.print(size + " ");
            compareWithDouble(size, N_TIMES, SKIP_FIRST_N, FILE);
        }

        // compare the algorithms with string arrays
        System.out.print("\r\033[2K\rComparing algorithms with String arrays: ");
        for (Integer size : sizes) {
            System.out.print(size + " ");
            compareWithString(size, N_TIMES, SKIP_FIRST_N, FILE, 10);
        }

        // compare the algorithms with dummy small object arrays
        System.out.print("\r\033[2K\rComparing algorithms with DummySmallObject arrays: ");
        for (Integer size : sizes) {
            System.out.print(size + " ");
            compareWithDummySmallObject(size, N_TIMES, SKIP_FIRST_N, FILE);
        }

        // compare the algorithms with dummy large object arrays
        System.out.print("\r\033[2K\rComparing algorithms with DummyLargeObject arrays: ");
        for (Integer size : sizes) {
            System.out.print(size + " ");
            compareWithDummyLargeObject(size, N_TIMES, SKIP_FIRST_N, FILE);
        }


        System.out.println("\r\033[2K\r\033[0;32m[✓] Done!\033[0m Results in " + "\033[0;34m" + FILE + "\033[0m");
    }
}

