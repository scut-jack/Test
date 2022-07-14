package com.demo.algorithm.sort;

/**
 * @author jack
 * @date 2019/10/15-21:46
 */
public class QuickSortTest {
    public static void main(String[] args) {
        int[] a = {2, 1, 7, 4, 6, 3, 9, 8, 5, 0};
        quickSort(a, 0, a.length-1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        int i, j;
        int temp;
        i = low;
        j = high;
        temp = arr[low];//取第一个元素为标准数据元素
        while (i < j) {
            //在数组的右端扫描
            while (i < j && temp <= arr[j]) j--;
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            //在数组的左端扫描
            while (i < j && arr[i] < temp) i++;
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = temp;
        if (low<i)quickSort(arr,low,i-1);//对左端子集合递归
        if (i<high)quickSort(arr,j+1,high);//对右端子集合递归
    }
}
