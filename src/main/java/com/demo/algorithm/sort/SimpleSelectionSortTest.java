package com.demo.algorithm.sort;

/**
 * @author jack
 * @date 2019/9/30-17:17
 */
public class SimpleSelectionSortTest {
    public static void main(String[] args) {
        int[] arr = {9, 1, 5, 8, 3, 7, 4, 6, 2, 5, 9};
        SimpleSelectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /*简单选择排序*/
    public static void SimpleSelectionSort(int[] L) {
        for (int i = 0; i < L.length; i++) {
            int min = i;
            for (int j = i + 1; j < L.length; j++) {
                if (L[min] > L[j]) {/*找一个多余的空间，把比较之后的最小值放入其中*/
                    min = j;
                }
            }
            if (i != min) {
                int temp = L[i];
                L[i] = L[min];
                L[min] = temp;
            }
        }
    }
}
