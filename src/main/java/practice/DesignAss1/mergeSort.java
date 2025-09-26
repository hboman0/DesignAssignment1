package practice.DesignAss1;

public class mergeSort {
    private static final int CutOff = 10;

    public static int currentDepth = 0;
    public static int maxDepth = 0;

    public static void resetDepth() {
        currentDepth = 0;
        maxDepth = 0;
    }

    public static void mergeSort(int[] arr) {
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (right - left <= CutOff) {
            insertionSort(arr, left, right);
            return;
        }

        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, buffer, left, mid);
            mergeSort(arr, buffer, mid + 1, right);
            merge(arr, buffer, left, mid, right);
        }

        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;

        if (right - left <= CutOff) {
            insertionSort(arr, left, right);
            currentDepth--;
            return;
        }

        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, buffer, left, mid);
            mergeSort(arr, buffer, mid + 1, right);
            merge(arr, buffer, left, mid, right);
        }

        currentDepth--;
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            buffer[k++] = arr[i++];
        }
        while (j <= right) {
            buffer[k++] = arr[j++];
        }
        for (int m = left; m <= right; m++) {
            arr[m] = buffer[m];
        }
    }
}
