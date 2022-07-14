package com.demo.algorithm.sort;

/**
 * @author jack
 * @date 2019/9/17-20:52
 */
public class iterateTest {
    public static void main(String[] args) {
        System.out.println(fib(6));
        System.out.println(fib1(6));
    }
    //迭代方法实现斐波那契数列
    public static int fib(int n){
        if (n>=2){
            return fib(n-1)+fib(n-2);
        }else{
            return n;
        }
    }
    //递归方法实现斐波那契数列
    public static int fib1(int n){
        int temp0,temp1,temp2;
        if (n<2)return n;
        temp0=0;
        temp1=0;
        temp2=1;
        for (int i = 2; i <= n ; i++) {
            temp0=temp1+temp2;
            temp1=temp2;
            temp2=temp0;
        }
        return temp0;
    }
}
