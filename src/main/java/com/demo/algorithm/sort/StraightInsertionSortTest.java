package com.demo.algorithm.sort;

/**
 * @author jack
 * @date 2019/9/30-19:36
 */
public class StraightInsertionSortTest {
    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 8, 3, 7, 4, 6, 2, 5, 9};
        StraightInsertionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
/*直接插入排序*/
    public static void StraightInsertionSort(int[] L) {
        int j, temp;
        for (int i = 0; i < L.length - 1; i++) {
            /*主循环，开始以数组第一个数作为有序序列，然后以前两个作为有序序列...
            依次判断后面的每一个数进行插入*/
            temp = L[i + 1];
            j = i;
            while (j > -1 && temp <= L[j]) {
                /*将需要进行插入的数与有序子集最后一个数比较，倒数第二个比较...*/
                L[j + 1] = L[j];
                j--;
            }
            L[j + 1] = temp;/*找到合适位置后，插入此位置*/
        }
    }

}
