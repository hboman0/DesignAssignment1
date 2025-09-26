package practice.DesignAss1;

import java.util.Random;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Benchmark {
    private static final Random rand = new Random();
    private static сlosestPair closestPair;

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000, 10000};

        try (PrintWriter csv = new PrintWriter(new FileWriter("C:/Users/sovet/IdeaProjects/ADS/src/practice/DesignAss1/benchmark.csv"))) {
            csv.println("algorithm,n,time_ns,maxDepth");
            System.out.println("algorithm,n,time_ns,maxDepth");

            for (int n : sizes) {
                int[] base = randomArray(n);
                int[] arrMerge = Arrays.copyOf(base, base.length);
                int[] arrQuick = Arrays.copyOf(base, base.length);

                mergeSort.resetDepth();
                long t1 = System.nanoTime();
                mergeSort.mergeSort(arrMerge);
                long t2 = System.nanoTime();
                long mergeTime = t2 - t1;
                csv.printf("MergeSort,%d,%d,%d%n", n, mergeTime, mergeSort.maxDepth);
                System.out.printf("MergeSort,%d,%d,%d%n", n, mergeTime, mergeSort.maxDepth);

                quickSort.resetDepth();
                long t3 = System.nanoTime();
                quickSort.quickSort(arrQuick);
                long t4 = System.nanoTime();
                long quickTime = t4 - t3;
                csv.printf("QuickSort,%d,%d,%d%n", n, quickTime, quickSort.maxDepth);
                System.out.printf("QuickSort,%d,%d,%d%n", n, quickTime, quickSort.maxDepth);

                int k = rand.nextInt(n);
                int[] arrSelect = Arrays.copyOf(base, base.length);
                long t5 = System.nanoTime();
                int val = deterministicSelect.select(arrSelect, k);
                long t6 = System.nanoTime();
                Arrays.sort(base);
                int check = base[k];
                if (val != check) {
                    System.err.println("Select mismatch at n=" + n);
                }
                long selectTime = t6 - t5;
                csv.printf("DeterministicSelect,%d,%d,%d%n", n, selectTime, deterministicSelect.maxDepth);
                System.out.printf("DeterministicSelect,%d,%d,%d%n", n, selectTime, deterministicSelect.maxDepth);

                if (n <= 2000) {
                    closestPair.resetDepth();
                    double[][] points = randomPoints(n);
                    long t7 = System.nanoTime();
                    double dFast = closestPair.closestPair(points);
                    long t8 = System.nanoTime();
                    double dNaive = closestPair.closestPairNaive(points);
                    if (Math.abs(dFast - dNaive) > 1e-9) {
                        System.err.println("ClosestPair mismatch at n=" + n);
                    }
                    long closestTime = t8 - t7;
                    csv.printf("ClosestPair,%d,%d,%d%n", n, closestTime, closestPair.maxDepth);
                    System.out.printf("ClosestPair,%d,%d,%d%n", n, closestTime, closestPair.maxDepth);
                }
            }

            System.out.println("Все результаты сохранены в benchmark.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] randomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(1_000_000);
        }
        return arr;
    }

    private static double[][] randomPoints(int n) {
        double[][] pts = new double[n][2];
        for (int i = 0; i < n; i++) {
            pts[i][0] = rand.nextDouble();
            pts[i][1] = rand.nextDouble();
        }
        return pts;
    }
}
