package com.demo.algorithm.sort;

/**
 * @author jack
 * @date 2019/9/28-17:01
 */
public class InterpolationSearchTest {
    public static void main(String[] args) {
        int[] a = {0, 1, 16, 24, 35, 47, 59, 62, 73, 88, 99};
        System.out.println(InterpolationSearch(a, 62));
    }

    /*插值查找，适用于于有序且分布较为均匀的数值情况*/
    public static int InterpolationSearch(int[] arr, int key) {
        int low, high, mid;
        low = 0;
        high = arr.length -1;
        while (low <= high) {
            mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]);
            /*插值，均匀分布的数值点，大概按比例找出key所在的范围*/
            if (key < arr[mid])
                high = mid - 1;
            else if (key > arr[mid])
                low = mid + 1;
            else
                return mid;
        }
        return 0;
    }
}
