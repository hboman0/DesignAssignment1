package practice.DesignAss1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    public void testSimpleArray() {
        int[] arr = {5, 3, 1, 4, 2};
        practice.DesignAss1.quickSort.quickSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}
