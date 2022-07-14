package com.demo.algorithm.swordoffer;

/**
 * @author jack
 * @date 2020/2/13-16:39
 */
public class MyTest {
    public static void main(String[] args) {
        System.out.println(LinkedListTest.lastRemaining1(4, 1));
    }


    static int MostReShow(int[] arr) {//找出一串数字中出现次数最多的数字
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] == arr[i])
                    count++;
            }
            temp[i] = count;
        }
        int tt = temp[0];
        int t2 = 0;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] > tt) {
                tt = temp[i];
                t2 = i;
            }
        }
        return arr[t2];
    }
}
