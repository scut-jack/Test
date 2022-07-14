package com.demo.algorithm.sort;

/**
 * @author jack
 * @date 2019/9/30-15:40
 */
public class BubbleSortTest {
    public static void main(String[] args) {
        int[] arr = {9, 1, 5, 8, 3, 7, 4, 6, 2, 5,9};
        BubbleSort1(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /*冒泡排序*/
    public static void BubbleSort(int[] L) {
        for (int i = 0; i < L.length; i++) {
            for (int j = L.length - 2; j >= i; j--) {
                if (L[j] > L[j + 1]) {/*两两比较，小的往前放*/
                    int temp;
                    temp = L[j];
                    L[j] = L[j + 1];
                    L[j + 1] = temp;
                }
            }
        }
    }

    /*冒泡排序算法改进，避免因有序情况下进行的无意义循环排序*/
    public static void BubbleSort1(int[] L) {
        boolean flag = true;
        for (int i = 0; i < L.length && flag; i++) {
            flag = false;
            for (int j = L.length - 2; j >= i; j--) {
                if (L[j] > L[j + 1]) {/*两两比较，小的往前放*/
                    int temp;
                    temp = L[j];
                    L[j] = L[j + 1];
                    L[j + 1] = temp;
                    flag = true;
                }
            }
        }
    }
}
