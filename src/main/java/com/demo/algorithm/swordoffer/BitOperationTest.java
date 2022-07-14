package com.demo.algorithm.swordoffer;

/**
 * @author jack
 * @date 2020/2/14-21:40
 */
public class BitOperationTest {
    public static void main(String[] args) {
        String str = "BXX";
        System.out.println('X' - 64);
        System.out.println(StringToInt(str));
        System.out.println(NumberOf1(-127));
    }

    /*拓展：十进制数字用A~Z表示成二十六进制，A表示1，AA表示27*/
    static int StringToInt(String str) {
        int len = str.length();
        if (len <= 0) return -1;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += (str.charAt(i) - 64) * ((int) Math.pow(26, len - 1 - i));
        }
        return sum;
    }

    /*题15：二进制中1的个数*/
    static int NumberOf1(int n) {
        /*//右移会导致符号位的复制，选择左移,这是常规解法
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) count++;
            flag = flag << 1;
        }
        return count;*/
        //把一个数减去1再和原整数做与运算，会把该整数最右边的1变成0
        int count = 0;
        while (n != 0) {
            n = (n - 1) & n;
            count++;
        }
        return count;
    }

    /*题65：不用加减乘除做加法*/
    /*思路：位运算加法分两步：1.计算和，不带进位（可以用异或来运算）
     *2.计算进位（用与运算左移一位）3.计算和以及与运算的值的总和*/
    static int Add(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2;//计算不带进位的和
            num2 = (num1 + num2) << 1;//计算进位
            num1 = temp;
        }
        return num1;
    }
}
