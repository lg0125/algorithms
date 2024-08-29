package com.chris.algorithms.templates.sort;

public class HeapSort {
    public static int[] sortArray(int[] arr) {
        heapsort(arr);
        return arr;
    }

    private static void heapsort(int[] arr) {
        int n = arr.length;

        buildMaxHeap(arr, n);

        for(int k = n - 1; k >= 1; --k) {
            swap(arr, 0, k);
            
            maxHeapify(arr, 0, --n);
        }
    }

    private static void buildMaxHeap(int[] arr, int n) {
        for(int k = n / 2; k >= 0; --k)
            maxHeapify(arr, k, n);
    }

    private static void maxHeapify(int[] arr, int i, int n) {
        int l = i * 2 + 1;
        int r = i * 2 + 2;

        int lgt = i;
        if (l < n && arr[l] > arr[lgt])
            lgt = l;
        if (r < n && arr[r] > arr[lgt])
            lgt = r;

        if (lgt != i) {
            swap(arr, i, lgt);

            maxHeapify(arr, lgt, n);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
