package practice.DesignAss1;
import java.util.Random;
    public class quickSort {
        private static final Random rand = new Random();

        public static int currentDepth = 0;
        public static int maxDepth = 0;

        public static void resetDepth() {
            currentDepth = 0;
            maxDepth = 0;
        }

        public static void quickSort(int[] arr) {
            quickSort(arr, 0, arr.length - 1);
        }

        private static void quickSort(int[] arr, int left, int right) {
            currentDepth++;
            if (currentDepth > maxDepth) maxDepth = currentDepth;

            if (left < right) {
                int pivotIndex = left + rand.nextInt(right - left + 1);
                int pivotNewIndex = partition(arr, left, right, pivotIndex);

                quickSort(arr, left, pivotNewIndex - 1);
                quickSort(arr, pivotNewIndex + 1, right);
            }

            currentDepth--;
        }

        private static int partition(int[] arr, int left, int right, int pivotIndex) {
            int pivot = arr[pivotIndex];
            swap(arr, pivotIndex, right);
            int storeIndex = left;
            for (int i = left; i < right; i++) {
                if (arr[i] < pivot) {
                    swap(arr, i, storeIndex);
                    storeIndex++;
                }
            }
            swap(arr, storeIndex, right);
            return storeIndex;
        }

        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
