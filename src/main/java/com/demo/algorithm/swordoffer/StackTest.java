package com.demo.algorithm.swordoffer;

import java.util.Stack;

/**
 * @author jack
 * @date 2020/2/14-11:43
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        NewStack news = new NewStack();
        news.stack2 = stack;
        System.out.println(news.stack2);
    }

    /*题九：用两个栈实现队列，实现其压入、弹出方法*/
    static Stack<Integer> stack1 = new Stack<>();
    static Stack<Integer> stack2 = new Stack<>();

    //进队列压入stack1栈
    static void push(int node) {
        stack1.push(node);
    }

    //出队列判断：
    //1.若stack2不为空，从stack2弹出
    //2.若stack2为空，从stack1弹出压入到stack2
    static int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    /*拓展：两个队列实现一个栈，《剑指offer(第二版)》p72*/

    /*题30：包含min函数的栈*/
    /*定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数；
     * 在该栈中调用min,pop,push方法的复杂度均为O(1)*/
    /*解题思路：每次压栈都把最小元素压入辅助栈，就能保证辅助栈的栈顶一直是最小元素*/
    static class NewStack {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();//辅助栈

        void push(int value) {
            stack1.push(value);
            //将较小的元素压入辅助栈
            if (stack2.isEmpty()) {
                stack2.push(value);
            } else {
                if (value <= stack2.peek()) {
                    stack2.push(value);
                } else {
                    stack2.push(stack2.peek());
                }
            }
        }

        int pop() {
            stack2.pop();
            return stack1.pop();
        }

        int min() {
            return stack2.peek();
        }
    }

    /*题31：栈的压入、弹出序列*/
    /*解题思路，即将压入弹出过程通过程序写出来，程序相当于实际操作*/
    static boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null) return false;
        if (pushA.length != popA.length) return false;
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
