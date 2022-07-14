package com.demo.algorithm.swordoffer;

import java.util.ArrayList;

/**
 * @author jack
 * @date 2020/2/24-17:28
 */
public class CreateBinaryTree {
    /*构造一棵二叉树*/
    static int index = 0;//此值标记传入数组下标，不可缺

    /*以前序遍历的方法构造*/
    static TreeNode Init(int[] array, int i) {
        //一个用于构造二叉树的数组（前序遍历,0元素表示空结点）；数组下标
        if (array[index] == 0) {
//            ++index;
            return null;
        }
        TreeNode node = new TreeNode(array[index]);
        node.left = Init(array, ++index);
        node.right = Init(array, ++index);
        return node;
    }

    static void PreTraverse(TreeNode root, ArrayList<Integer> list) {
        if (root == null) return;
        list.add(root.val);//前序遍历递归实现，并按前序遍历顺序将结点值存入list
        PreTraverse(root.left, list);
        PreTraverse(root.right, list);


    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode();
        int[] arr = {1, 2, 0, 0, 3, 0, 0};
        TreeNode root = CreateBinaryTree.Init(arr, 0);
        ArrayList<Integer> list = new ArrayList<>();
        PreTraverse(root, list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
