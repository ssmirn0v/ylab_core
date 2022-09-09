package lecture1.task2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] arr1 = new Integer[]{5, 6, 3, 2, 5, 1, 4, 9};
        Integer[] arr2 = new Integer[]{-1609, -4819, -1046, -2504, 763, 4041, -2492, 4566, -2864, -751, -875, 669, -3166};
        Double[] arr3 = new Double[]{37.859, 59.855, 97.778, 72.742, 57.803, 19.887, 47.409, 70.517, 62.287, 55.465};

        qs(arr1, 0, arr1.length-1);

        Integer[] arr2Copy = Arrays.copyOf(arr2, arr2.length);
        qs(arr2Copy, 0, arr2.length-1);
        Arrays.sort(arr2);

        Double[] arr3Copy = Arrays.copyOf(arr3, arr3.length);
        qs(arr3Copy, 0, arr3.length-1);
        Arrays.sort(arr3);

        assert Arrays.equals(arr1, new Integer[]{1,2,3,4,5,5,6,9}): "1st assert failed";
        assert Arrays.equals(arr2Copy, arr2): "2nd assert failed";
        assert Arrays.equals(arr3Copy, arr3): "3rd assert failed";

        System.out.println("All tests have been passed");


    }

    public static void qs(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int j = partition(arr, lo, hi);
        qs(arr, lo, j-1);
        qs(arr, j+1, hi);
    }

    public static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo;
        int j = hi+1;

        Comparable v = arr[lo];
        while (true) {
            while (arr[++i].compareTo(v) < 0) {
                if (i == hi) {
                    break;
                }
            }
            while (arr[--j].compareTo(v) > 0) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }

            exch(arr, i, j);
        }

        exch(arr, lo, j);
        return j;
    }

    public static void exch(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
