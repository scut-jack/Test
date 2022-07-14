package com.demo.algorithm.swordoffer;

/**
 * @author jack
 * @date 2020/2/14-12:30
 */
public class RecursiveTest {
    public static void main(String[] args) {
        System.out.println(Fibonacci(5));
    }

    /*题十：求斐波那契数列的第n项*/
    static int Fibonacci(int n) {
        //递归实现，效率低重复运算多次
        /*if (n <= 1) return n;
        return Fibonacci(n - 1) + Fibonacci(n - 2);*/
        //迭代实现，效率提高（也可用一个数组来暂存已经计算过的值）
        int[] res = {0, 1};
        if (n <= 1) return res[n];
        int N = 0, N1 = 1, N2 = 0;//用三个指针实现
        for (int i = 2; i <= n; i++) {
            N = N1 + N2;
            N2 = N1;
            N1 = N;
        }
        return N;
    }
    /*拓展：青蛙跳台阶问题，一只青蛙一次可以跳上1级台阶或2级台阶；求该蛙跳上n级台阶有多少种跳法*/
    /*推导公式跳法F(n)=F(n-1)+F(n-2)，然后还是一个斐波那契数列*/
}
