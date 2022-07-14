package com.demo.algorithm.sort;

/**
 * @author jack
 * @date 2019/9/28-16:20
 */
public class BinarySearchTest {
    public static void main(String[] args) {
        int[] a = {0, 1, 16, 24, 35, 47, 59, 62, 73, 88, 99};
        System.out.println(BinarySearch(a, 99));
    }

    /*折半查找，适用于有序情况*/
    public static int BinarySearch(int[] arr, int key) {
        int low, high, mid;
        low = 0;
        high = arr.length-1;
        while (low <= high) {
            mid = (low + high) / 2;/*折半*/
            if (key < arr[mid])/*若查找值比中值小*/
                high = mid - 1;/*最高下标调整到中位下标小一位*/
            else if (key > arr[mid])
                low = mid + 1;
            else return mid;
        }
        return 0;
    }
}
