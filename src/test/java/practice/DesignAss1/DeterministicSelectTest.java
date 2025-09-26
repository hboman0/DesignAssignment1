package practice.DesignAss1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class DeterministicSelectTest {

    @Test
    public void testSelectRandom() {
        Random rand = new Random(42);
        for (int t=0; t<100; t++) {
            int n = rand.nextInt(200) + 5;
            int[] arr = new int[n];
            for (int i=0; i<n; i++) arr[i] = rand.nextInt(1000);
            int k = rand.nextInt(n);

            int[] copy = arr.clone();
            Arrays.sort(copy);
            int expected = copy[k];

            int actual = practice.DesignAss1.deterministicSelect.select(arr.clone(), k);
            assertEquals(expected, actual, "Mismatch at trial " + t);
        }
    }
}
