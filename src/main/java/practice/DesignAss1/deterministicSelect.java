package practice.DesignAss1;

public class deterministicSelect {

    public static int currentDepth = 0;
    public static int maxDepth = 0;

    public static void resetDepth() {
        currentDepth = 0;
        maxDepth = 0;
    }

    public static int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;

        if (left == right) {
            currentDepth--;
            return arr[left];
        }

        int pivotIndex = pivotIndex(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        int val;
        if (k == pivotIndex) {
            val = arr[k];
        } else if (k < pivotIndex) {
            val = select(arr, left, pivotIndex - 1, k);
        } else {
            val = select(arr, pivotIndex + 1, right, k);
        }

        currentDepth--;
        return val;
    }

    private static int pivotIndex(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n < 5) {
            return partition5(arr, left, right);
        }
        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            int median5 = partition5(arr, i, subRight);
            swap(arr, left + numMedians, median5);
            numMedians++;
        }
        int midOfMedians = left + (numMedians - 1) / 2;
        return selectIndex(arr, left, left + numMedians - 1, midOfMedians);
    }

    private static int selectIndex(int[] arr, int left, int right, int k) {
        if (left == right) return left;
        int pivot = pivotIndex(arr, left, right);
        pivot = partition(arr, left, right, pivot);
        if (k == pivot) return k;
        else if (k < pivot) return selectIndex(arr, left, pivot - 1, k);
        else return selectIndex(arr, pivot + 1, right, k);
    }

    private static int partition5(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return (left + right) / 2;
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, store);
                store++;
            }
        }
        swap(arr, store, right);
        return store;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}