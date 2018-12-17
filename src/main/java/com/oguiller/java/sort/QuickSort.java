package com.oguiller.java.sort;

/**
 * https://www.geeksforgeeks.org/quick-sort/
 * <p>
 *     is much more efficient than the bubble sort and insertion sort algorithms. The separation of the elements into
 *     two separate lists is O(n), and each recursive call happens on half of each list, resulting in O(n log n). This
 *     is an average performance. The worst case is still O(n2).
 * </p>
 */
public class QuickSort {

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver program
    public static void main(String args[]) {
        int arr[] = {5, 7, 2, 8};
        int n = arr.length;

        QuickSort ob = new QuickSort();
        ob.sort(arr, 0, n - 1);

        System.out.println("sorted array");
        printArray(arr);
    }

    /* The main function that implements QuickSort()
  arr[] --> Array to be sorted,
  low  --> Starting index,
  high  --> Ending index */
    void sort(int arr[], int low, int high) {
        System.out.printf("Sort low is %s and high is %s\n", low, high);
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);
//            printArray(arr);
            // Recursively sort elements before
            // partition and after partition

            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    int partition(int arr[], int low, int high) {
        System.out.println("Partition");
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
//            System.out.printf("IF PART: i is %s and j is %s => Pivot is %s Swapping: %s and %s \n", i, j, pivot,arr[i], arr[j]);
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
//    System.out.printf("i is %s => Pivot is %s Swapping: %s and %s \n", i, pivot,arr[i+1], arr[high]);
        int temp = arr[i + 1];
        arr[i + 1] = pivot;
        arr[high] = temp;

        return i + 1;
    }

}
