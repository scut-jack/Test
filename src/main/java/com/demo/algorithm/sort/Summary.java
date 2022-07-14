package com.demo.algorithm.sort;

/**
 * @author jack
 * @date 2019/10/16-15:49
 */
public class Summary {
    public static void main(String[] args) {
        int[] arr = {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 1, 9, 8, 7, 6, 5, 4, 3, 2, 10};
//        int[] d = {6, 2};
        //shellSort(arr, d, 2);
        //insertSort(arr);
        //selectSort(arr);
//        quickSort(arr, 0, arr.length - 1);
        //dubbleSort(arr);
        Msort(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * @return a[]
     * @Author jack
     * @Date 2019/10/16
     * @Description 直接插入排序
     * @Param a[]
     */
    public static void insertSort(int[] a) {
        int j, temp;
        for (int i = 0; i < a.length - 1; i++) {
            temp = a[i + 1];
            j = i;
            while (j > -1 && temp <= a[j]) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }

    /**
     * @return a[]
     * @Author jack
     * @Date 2019/10/16
     * @Description 希尔排序/缩小增量排序
     * @Param a[], d[], numD
     */
    public static void shellSort(int[] a, int[] d, int numD) {
        //数列d是设置增量的，numD=d.length
        int j, span, temp;
        //此循环是设置增量
        for (int m = 0; m < numD; m++) {
            span = d[m];
            //此循环是第一个间距span中间的数
            for (int k = 0; k < d[m]; k++) {
                //此循环是间距为span的直接插入排序
                for (int i = k; i < a.length - span; i = i + span) {
                    temp = a[i + span];
                    j = i;
                    while (j > -1 && temp <= a[j]) {
                        a[j + span] = a[j];
                        j = j - span;
                    }
                    a[j + span] = temp;
                }
            }
        }
    }

    /**
     * @return a[]
     * @Author jack
     * @Date 2019/10/16
     * @Description 直接选择排序
     * @Param a[]
     */
    public static void selectSort(int[] a) {
        int temp, small;
        for (int i = 0; i < a.length - 1; i++) {
            small = i;
            //寻找最小数据元素的下标
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[small]) small = j;
            }
//            if (small != i) {//当最小元素的下标不为i时交换位置
//                temp = a[i];
//                a[i] = a[small];
//                a[small] = temp;
//            }
            //将不稳定的直接选择排序变成稳定的直接选择排序
            if (small != i) {
                temp = a[small];
                for (int k = small; k > i; k--) {
                    a[k] = a[k - 1];
                }
                a[i] = temp;
            }
        }
    }

    /**
     * @return
     * @Author jack
     * @Date 2019/10/17
     * @Description 堆排序(选择排序)
     * @Param int a[]
     */
    public static void heapSort(int[] a) {

    }

    /**
     * @return a[]
     * @Author jack
     * @Date 2019/10/17
     * @Description 冒泡排序
     * @Param a[]
     */
    public static void dubbleSort(int[] a) {
        int temp, j, flag = 1;
        //flag用于标记一次排序是否有交换，没有交换直接退出大循环（已排好）
        for (j = 1; flag == 1 && j < a.length; j++) {
            flag = 0;
            for (int i = 0; i < a.length - j; i++) {
                if (a[i] > a[i + 1]) {
                    flag = 1;
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
        }
    }

    /**
     * @return a[]
     * @Author jack
     * @Date 2019/10/17
     * @Description 快速排序
     * @Param a[], low, high
     */
    public static void quickSort(int[] a, int low, int high) {
        //low为数组最低下标low=0 ; high为数组最高下标high=a.length
        int i = low, j = high, temp;
        temp = a[low];//取第一个元素为标准数据元素
        //一次排序的出口是i=j ; 标准元素找到自己位置，且其左边元素比他小，右边比他大
        while (i < j) {
            while (i < j && temp <= a[j]) j--;//数组右边遍历，找到比temp小的位置跳出
            if (i < j) {
                a[i] = a[j];
                i++;
            }
            while (i < j && temp > a[i]) i++;//数组左边遍历，找到比temp大的位置跳出
            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        a[i] = temp;
        if (low < i) quickSort(a, low, i - 1);
        if (high > i) quickSort(a, i + 1, high);
    }

    /**
     * @return
     * @Author jack
     * @Date 2019/10/17
     * @Description 归并排序
     * @Param a[]
     */
    public static void Msort(int[] arr, int low, int high) {
        if (low == high) return;
        int mid = (low + high) / 2;
        Msort(arr, low, mid);
        Msort(arr, mid + 1, high);
        Merge(arr, low, mid, high);

    }

    //传入Merge的数组arr是有序的
    public static void Merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = 0, p1 = low, p2 = mid + 1;
        while (p1 <= mid && p2 <= high) {
            temp[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) temp[i++] = arr[p1++];
        while (p2 <= high) temp[i++] = arr[p2++];
        for (i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
    }
}
