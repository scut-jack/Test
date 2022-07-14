package com.demo.algorithm.swordoffer;

import java.util.*;

/**
 * @author jack
 * @date 2020/2/11-17:43
 */
public class ArrayTest {

    /*题一：找出数组中重复的数字*/
    static boolean ReNumber(int[] arr, int[] duplication) {
        /*利用hash表来解决问题，复杂的为O(n)，空间复杂度为O(n)
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])){
                duplication[0]=arr[i];
                return true;
            }
            else set.add(arr[i]);
        }
        return false;*/
        //通过数组下标来实现，思路是将值和下标放在同一位置（因为要求是长度n的数组存储的值在0~n-1）
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 || arr[i] > arr.length - 1) return false;
        }
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i) {
                if (arr[i] == arr[arr[i]]) {
                    duplication[0] = arr[i];
                    return true;
                } else {//交换
                    int temp = arr[i];
                    arr[i] = arr[temp];
                    arr[temp] = temp;
                }
            }
        }
        return false;
    }

    /*题二：不修改数组找出重复的数字*/
    static int ReNumber1(int[] arr) {
        /*新建一个暂存数组，空间复杂度O(n)，复杂度O(n)
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (temp[arr[i]] != arr[i])
                temp[arr[i]] = arr[i];
            else return arr[i];
        }
        return -1;*/
        //复杂度O(nlogn)，空间复杂度O(1)
        if (arr == null) return -1;
        int start = 1, end = arr.length - 1;//这个不是位置，是数值！！
        while (end >= start) {
            int mid = (start + end) / 2;
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                //数清start到mid数值范围的数有多少个
                if (arr[i] >= start && arr[i] <= mid) count++;
            }
            if (end == start) {
                if (count > 1) return start;
                else break;
            }
            //如果个数大于mid，则分别从另一个范围继续计算
            if (count > (mid - start + 1)) end = mid;
            else start = mid + 1;
        }
        return -1;
    }

    /*题三：矩阵数值递增排列（左到右，上到下），找出特定值*/
    static boolean FindMatrix(int[][] matrix, int row, int col, int number, int[] position) {
        //递归方式实现二维数组查找，并且通过position数组将位置传出
        //递归查找的起点从右上角开始，传入右上角最后一个坐标(0,matrix[0].length-1)
        if (matrix == null) return false;
        //矩阵的行坐标，矩阵的列坐标
        if (row >= matrix.length || col < 0) return false;//查找数组范围越界
        if (number == matrix[row][col]) {
            position[0] = row;
            position[1] = col;
            return true;
        }
        if (number > matrix[row][col]) {
            return FindMatrix(matrix, row + 1, col, number, position);
        } else {
            return FindMatrix(matrix, row, col - 1, number, position);
        }

    }

    static boolean FindMatrix1(int[][] matrix, int number) {
        //迭代方式实现二维数组查找，从右上角开始找
        int row = 0;//行坐标
        int col = matrix[0].length - 1;//列坐标
        while (matrix != null && row <= matrix.length - 1 && col >= 0) {
            if (number == matrix[row][col]) return true;
            else if (number < matrix[row][col]) {
                col--;
            } else if (number > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }

    /*题21：调整数组顺序使奇数位于偶数前面*/
    static void reOrderArray(int[] array) {
        if (array == null) return;
        int pre = 0, last = array.length - 1;
        while (pre < last) {
            while (pre < last && (array[pre] & 1) != 0) pre++;//pre指向奇数则自增
            while (pre < last && (array[last] & 1) == 0) last--;//last指向偶数则自减
            if (pre < last)
                swap(array, pre, last);
        }
    }

    static void swap(int[] array, int pre, int last) {
        int temp = array[last];
        array[last] = array[pre];
        array[pre] = temp;
    }

    /*题29：顺时针打印矩阵，输入一个矩阵，按从外向里以顺时针顺序打印*/
    static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null) return list;
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (true) {
            //打印最上面的行
            for (int i = left; i <= right; i++) {
                list.add(matrix[up][i]);
            }
            up++;
            if (up > down) break;
            //打印最右边的列
            for (int i = up; i <= down; i++) {
                list.add(matrix[i][right]);
            }
            right--;
            if (right < left) break;
            //打印最下面的行
            for (int i = right; i >= left; i--) {
                list.add(matrix[down][i]);
            }
            down--;
            if (down < up) break;
            //打印最左边的列
            for (int i = down; i >= up; i--) {
                list.add(matrix[i][left]);
            }
            left++;
            if (left > right) break;
        }
        return list;
    }

    /*题38：字符串的排列*/
    /*解题思路：先固定第一个位置，通过交换判断第一个位置有几种；再迭代的思路去看第二个位置...*/
    /*题目比较难，理解较困难！*/
    static ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        char[] chars = str.toCharArray();//将字符串转化为字符数组来处理
        if (str != null) {
            PermutationCore(chars, 0, list);
            Collections.sort(list);//排序，满足题中按字典序打印的要求
        }
        return list;
    }

    static void PermutationCore(char[] chars, int i, ArrayList<String> list) {
        if (i == chars.length - 1) {
            list.add(String.valueOf(chars));
            return;
        } else {
            Set<Character> charSet = new HashSet<Character>();//出现重复的字符时就无需交换了
            for (int j = i; j < chars.length; j++) {
                if (j == i || !charSet.contains(chars[j])) {
                    charSet.add(chars[j]);
                    swap(chars, i, j);
                    PermutationCore(chars, i + 1, list);
                    swap(chars, j, i);
                }
            }
        }
    }

    static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    /*题53：在排序数组中查找数字*/
    /*一、数字在排序数组中出现的次数*/
    /*思路：出现排序数组，说明二分查找可以尝试；
    暴力法：一次遍历O(n)或者二分查找到之后在该位置向前向后遍历
    优化：利用二分查找，一次二分查找到连续数字的头部，一次二分查找到连续数字的尾部*/
    static int NumberOfK(int[] arr, int k) {
        if (arr == null) return -1;
        int first = GetFirstK(arr, 0, arr.length - 1, k);
        int last = GetLastK(arr, 0, arr.length - 1, k);
        if (first > -1 && last > -1)
            return last - first + 1;
        return 0;//没有找到
    }

    static int GetFirstK(int[] arr, int start, int end, int k) {
        if (arr == null) return -1;
        if (start > end) return -1;
        int mid = (start + end) >> 1;
        if (k == arr[mid]) {
            // 此时如果中点恰恰是数组的起始位置，说明前面没有元素，则直接返回该位置
            if ((mid > 0 && arr[mid - 1] != k) || mid == 0)
                return mid;
            else
                end = mid - 1;
        } else if (k > arr[mid]) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
        return GetFirstK(arr, start, end, k);
    }

    static int GetLastK(int[] arr, int start, int end, int k) {//返回-1说明没有找到
        if (arr == null) return -1;
        if (start > end) return -1;
        int mid = (start + end) >> 1;
        if (k == arr[mid]) {
            // 此时如果中点恰恰是数组的起始位置，说明前面没有元素，则直接返回该位置
            if ((mid < arr.length - 1 && arr[mid + 1] != k) || mid == arr.length - 1)
                return mid;
            else
                start = mid + 1;
        } else if (k > arr[mid]) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
        return GetLastK(arr, start, end, k);
    }

    /*题53：在排序数组中查找数字*/
    /*二、0~n-1中缺失的数字*/
    /*一串数长度为n-1，其范围在0~n-1且不重复，找出缺的那一个数字*/
    static int GetMissingNumber(int[] arr, int start, int end) {
        if (arr == null) return -1;
        if (start > end) return -1;
        int mid = (start + end) >> 1;
        if (mid == 0 || mid == arr.length || (arr[mid] == mid + 1 && arr[mid - 1] == mid - 1)) {
            return mid;
        } else if (arr[mid] == mid) {
            start = mid + 1;
        } else if (arr[mid] == mid + 1) {
            end = mid - 1;
        }
        return GetMissingNumber(arr, start, end);
    }

    static int GetMissingNumber1(int[] arr) {//迭代实现,传入length为数组长度
        if (arr == null) return -1;
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (arr[mid] != mid) {
                if (mid == 0 || arr[mid - 1] == mid - 1)
                    return mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (start == arr.length)//前面都不缺，只缺最后一个
            return arr.length;
        return -1;
    }

    /*题53：在排序数组中查找数字*/
    /*三、数组中数值和下标相等的元素*/
    static int GetNumberSameAsIndex(int[] arr) {
        if (arr == null) return -1;
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (arr[mid] == mid) {
                return mid;
            } else if (arr[mid] > mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /*题57：和为s的两个数字*/
    /*题一、输入一个递增数字和一个数字s，在数组中查找两个数的和正好为s，输出任意一对即可*/
    /*思路：利用两个指针*/
    static int[] FindSum(int[] numbers, int s) {
        if (numbers == null) return null;
        int fast = 0, last = numbers.length - 1;
        while (fast < last) {
            if (numbers[fast] + numbers[last] == s) {
                int[] a1 = {numbers[fast], numbers[last]};
                return a1;
            } else if (numbers[fast] + numbers[last] > s) {
                last--;
            } else {
                fast++;
            }
        }
        return null;
    }

    /*题57：和为s的两个数字*/
    /*题二、和为s的连续正数序列；例如输入15，打印出3个连续序列1~5,4~6,7~8*/
    static int[][] FindContinousSequence(int sum) {
        if (sum <= 2) return null;
        int start = 1, end = 2;
        int[][] A = new int[20][];//预设20，满足返回的序列数目小于20的情况
        int j = 0;
        while (start < (sum + 1) / 2) {//这里是因为序列最小包含两个数，而一个数就大概等于两个中位数之和
            int sum1 = (start + end) * (end - start + 1) / 2;
            if (sum1 == sum) {
                int[] a12 = new int[end - start + 1];
                for (int i = start; i <= end; i++) {
                    a12[i - start] = i;
                }
                A[j++] = a12;//把满足的序列加入大输出序列之中
                end++;
            } else if (sum1 < sum) {
                end++;
            } else {
                start++;
            }
        }
        return A;
    }

    /*本题测试代码：
    int[][] sum1 = ArrayTest.FindContinousSequence(15);
        int k;
        for (k = 0; k < sum1.length; k++) {
            if (sum1[k] == null) break;
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < sum1[i].length; j++) {
                System.out.print(sum1[i][j] + " ");
            }
            System.out.println();
        }
        运行结果：
        1 2 3 4 5
        4 5 6
        7 8
        */
    /*题59：队列的最大值，滑动窗口*/
    /*题一、给定一个数组和滑动窗口，请找出所有滑动窗口里的最大值*/
    /*暴力法：窗口移动，并且从每个窗口找出最大值，复杂度O(nk)*/
    static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        return null;
    }
    /*暴力解法测试代码：
    int[] a = {2, 3, 4, 2, 6, 2, 5, 1};
        int[] b = new int[6];
        int start = 0, window = 3;
        for (int i = 0; i <= a.length - window; i++) {
            int temp = a[i];
            for (int j = i; j < i + window; j++) {
                if (a[j] > temp)
                    temp = a[j];
            }
            b[i] = temp;
        }
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        运行结果：4 4 6 6 6 5
        */

    /*题63：股票的最大利润*/
    /*｛9,11,8,5,7,12,16,14｝在价格为5时买入，价格为16时卖出利润最大为11*/
    /*思路：定义函数diff(i)为当卖出价为数组中第i个数字时可能获得的最大利润*/
    static int MaxDiff(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int min = prices[0];
        int maxDiff = prices[1] - min;
        for (int i = 2; i < prices.length; i++) {
            if (prices[i - 1] < min)
                min = prices[i - 1];
            int currentDiff = prices[i] - min;
            if (currentDiff > maxDiff)
                maxDiff = currentDiff;
        }
        return maxDiff;//返回最大利润
    }

    public static void main(String[] args) {
        /*int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(GetFirstK(arr, 0, arr.length - 1, 3));
        System.out.println(GetLastK(arr, 0, arr.length - 1, 3));
        System.out.println(NumberOfK(arr, 3));
        int[] a1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(GetMissingNumber1(a1));*/
        int[] a = {1, 2, 4, 7, 11, 15};
        int[] ints = FindSum(a, 15);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }

}
