package com.demo.algorithm.sort;

/**
 * @author jack
 * @date 2020/1/6-14:11
 */
public class MergeSortTest {
    public static int count = 0;

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 1, 34, 7, 5, 9, 11, 20};
        int[] arr = {364, 637, 341, 406, 747, 995, 234, 971, 571, 219, 993, 407,
                416, 366, 315, 301, 601, 650, 418, 355, 460, 505, 360, 965,
                516, 648, 727, 667, 465, 849, 455, 181, 486, 149, 588, 233,
                144, 174, 557, 67, 746, 550, 474, 162, 268, 142, 463, 221, 882,
                576, 604, 739, 288, 569, 256, 936, 275, 401, 497, 82, 935, 983,
                583, 523, 697, 478, 147, 795, 380, 973, 958, 115, 773, 870,
                259, 655, 446, 863, 735, 784, 3, 671, 433, 630, 425, 930, 64,
                266, 235, 187, 284, 665, 874, 80, 45, 848, 38, 811, 267, 575};
        Msort(arr, 0, arr.length - 1);
//        Merge(arr,0,(arr.length-1)/2,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("*****");
        System.out.println(count);
    }

    public static void Msort1(int[] SR, int low, int high) {
        if (low == high) return;
        int mid = (low + high) / 2;
        Msort(SR, low, mid);
        Msort(SR, mid + 1, high);
        Merge(SR, low, mid, high);
    }

    public static void Merge1(int[] SR, int low, int mid, int high) {
        int[] arr = new int[high - low + 1];
        int p1 = low, p2 = mid + 1, i = 0;
        while (p1 <= mid && p2 <= high) {
            if (SR[p1] > SR[p2]) {
                arr[i++] = SR[p2++];
                count += mid - p1 + 1;
            } else {
                arr[i++] = SR[p1++];
            }
        }
        while (p1 <= mid) arr[i++] = SR[p1++];
        while (p2 <= high) arr[i++] = SR[p2++];
        for (i = 0; i < arr.length; i++) {
            SR[low + i] = arr[i];
        }

    }

    public static void Msort(int[] arr, int low, int high) {
        if (low == high) return;
        int mid = (low + high) / 2;
        Msort(arr, low, mid);
        Msort(arr, mid + 1, high);
        Merge(arr, low, mid, high);
    }

    public static void Merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = 0, p1 = low, p2 = mid+1;
        while (p1 <= mid && p2 <= high) {
            if (arr[p1] > arr[p2]) {
                temp[i++] = arr[p2++];
                count = (count + (mid - p1 + 1)) % 100000007;
            } else {
                temp[i++] = arr[p1++];
            }
        }
        while (p1 <= mid) temp[i++] = arr[p1++];
        while (p2 <= high) temp[i++] = arr[p2++];
        for (i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
    }
}
