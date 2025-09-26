package practice.DesignAss1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {

    @Test
    public void testSimpleArray() {
        int[] arr = {5, 3, 1, 4, 2};
        practice.DesignAss1.mergeSort.mergeSort(arr);
        assertArrayEquals(new int[]{1,2,3,4,5}, arr);
    }

    @Test
    public void testRandomArrays() {
        Random rand = new Random(42);
        for (int t=0; t<100; t++) {
            int n = rand.nextInt(200) + 1;
            int[] arr = new int[n];
            for (int i=0; i<n; i++) arr[i] = rand.nextInt(1000);
            int[] expected = arr.clone();
            Arrays.sort(expected);
            practice.DesignAss1.mergeSort.mergeSort(arr);
            assertArrayEquals(expected, arr);
        }
    }
}
