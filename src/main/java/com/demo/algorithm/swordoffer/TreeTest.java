package com.demo.algorithm.swordoffer;

import java.util.*;

/**
 * @author jack
 * @date 2020/2/13-17:27
 */
public class TreeTest {


    /*题七：重建二叉树，输入二叉树的前序遍历和中序遍历结果（以数组表示）*/
    static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        //递归实现
        if (pre.length == 0 || in.length == 0) return null;
        TreeNode node = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0])
                //copyOfRnge:将一个数组A，从下标from开始复制，复制到上标to(不包括 to)，生成一个新的数组B
                node.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
            else
                node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
        }
        return node;
    }

    /*题八：二叉树的下一个节点，给定一个二叉树和一个结点 找出中序遍历的下一个结点*/
    /*注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针 next*/
    static TreeNode GetNext(TreeNode node) {
        //方法一：先找到根结点，再中序遍历
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode p = node;
        while (p.next != null) {
            p = p.next;
        }//找出根结点
        InOrder(p, list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == node)
                return i == list.size() - 1 ? null : list.get(i + 1);
        }
        return null;
    }

    static void InOrder(TreeNode root, ArrayList<TreeNode> list) {//递归形式的中序遍历
        if (root != null) {
            InOrder(root.left, list);
            list.add(root);
            InOrder(root.right, list);
        }
    }

    static TreeNode GetNext1(TreeNode node) {
        //1.有右子树，下一结点是右子树中的最左结点
        if (node == null) return null;
        if (node.right != null) {
            TreeNode p = node.right;
            while (p.left != null)
                p = p.left;
            return p;
        }
        //2.无右子树，且结点是该结点父结点的左子树，则下一结点是该结点的父结点
        if (node.right == null && node.next.left == node)
            return node.next;
        //3.无右子树，且结点是该结点父结点的右子树,
        // 则我们一直沿着父结点追朔，直到找到某个结点是其父结点的左子树
        if (node.next != null) {
            TreeNode p = node.next;//父节点
            while (p.next != null && p.next.right == p) {
                p = p.next;
            }
            return p.next;
        }
        return null;
    }

    /*题26：树的子结构，输入两颗树A，B；判断B是不是A的子结构*/
    static boolean HasSubtree(TreeNode root1, TreeNode root2) {//前序遍历整棵二叉树
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val)
                result = judgeSubTree(root1, root2);
            if (!result)
                result = HasSubtree(root1.left, root2);
            if (!result)
                result = HasSubtree(root1.right, root2);
        }
        return result;
    }

    static boolean judgeSubTree(TreeNode r1, TreeNode r2) {//递归判断r2是不是r1的子结构
        if (r2 == null) return true;
        if (r1 == null) return false;
        if (r1.val != r2.val)
            return false;
        else
            return judgeSubTree(r1.left, r2.left) && judgeSubTree(r1.right, r2.right);
    }

    /*题27：二叉树的镜像*/
    /*解题思路就是前序遍历过程中顺便交换左右结点位置*/
    static void Mirror(TreeNode head) {
        PreTraverse(head);
    }

    static void PreTraverse(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            PreTraverse(root.left);
            PreTraverse(root.right);
        }
    }

    /*题28：对称的二叉树*/
    /*思路是判断二叉树的前序遍历和对称前序遍历结果是否相等*/
    static boolean isSymmetrical(TreeNode root) {
        return isSymmetrical(root, root);
    }

    static boolean isSymmetrical(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        return isSymmetrical(root1.left, root2.right)
                && isSymmetrical(root1.right, root2.left);
    }

    /*题32：从上到下打印二叉树*/
    /*就是二叉树的层序遍历，借用一个辅助队列来实现*/
    static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        TreeNode temp = null;
        while (!queue.isEmpty()) {
            temp = queue.removeFirst();
            list.add(temp.val);
            if (temp.left != null) queue.addLast(temp.left);
            if (temp.right != null) queue.addLast(temp.right);
        }
        return list;
    }

    /*拓展：分行的层序遍历，即每一层分一行来打印*/
    static void PrintFromTopToBottom1(TreeNode root) {
        if (root == null) return;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        TreeNode temp = null;
        int nextlevel = 0;
        int toBePrint = 1;
        while (!queue.isEmpty()) {
            temp = queue.removeFirst();
            System.out.print(temp.val + " ");
            if (temp.left != null) {
                queue.addLast(temp.left);
                ++nextlevel;
            }
            if (temp.right != null) {
                queue.addLast(temp.right);
                ++nextlevel;
            }
            --toBePrint;//当前打印的打印之后，数量自减1
            if (toBePrint == 0) {
                System.out.println();//分行
                toBePrint = nextlevel;
                nextlevel = 0;
            }
        }
    }

    /*拓展：按之字形状打印二叉树*/
    /*用两个栈来实现，其实循环等操作思路与层序遍历相似*/
    static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (pRoot == null) return list;
        Stack<TreeNode> stack1 = new Stack<>();//1存奇数层节点
        Stack<TreeNode> stack2 = new Stack<>();//2存偶数层节点
        stack1.push(pRoot);
        int layer = 1;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (layer % 2 != 0) {
                ArrayList<Integer> list1 = new ArrayList<>();
                while (!stack1.isEmpty()) {
                    TreeNode temp = stack1.pop();
                    list1.add(temp.val);
                    if (temp.left != null)
                        stack2.push(temp.left);
                    if (temp.right != null)
                        stack2.push(temp.right);
                }
                list.add(list1);
                layer++;
            } else {
                ArrayList<Integer> list1 = new ArrayList<>();
                while (!stack2.isEmpty()) {
                    TreeNode temp = stack2.pop();
                    list1.add(temp.val);
                    if (temp.right != null)
                        stack1.push(temp.right);
                    if (temp.left != null)
                        stack1.push(temp.left);
                }
                list.add(list1);
                layer++;
            }
        }
        return list;
    }

    /*题33：二叉搜索树的后序遍历序列，输入一个整数数组，判断该数组是不是二叉搜索树的后序遍历结果*/
    /*解题思路：后序遍历序列中，前一部分小于最后一点（根节点），后一部分大于最后结点（根节点）*/
    static boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null) return false;
        return Verify(sequence, 0, sequence.length - 1);
    }

    static boolean Verify(int[] sequence, int start, int end) {
        if (start >= end) return true;
        int i = start;
        while (sequence[i] < sequence[end]) i++;
        for (int j = i; j < end; j++) {
            if (sequence[j] < sequence[end]) return false;
        }
        return Verify(sequence, start, i - 1) && Verify(sequence, i, end - 1);
    }

    /*题34：二叉树中和为某一值的路径（路径是指从根节点到叶节点，不能是非叶节点）*/
    /*解题思路：其实本题本质为深度优先遍历，跟前序遍历类似，注意当一条路径遍历完之后要回溯*/
    static ArrayList<ArrayList<Integer>> listA = new ArrayList<>();
    static ArrayList<Integer> list = new ArrayList<>();//用ArrayList来实现一个栈的功能

    static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return listA;
        boolean isleaf = root.left == null && root.right == null;//判断是否为叶结点
        target -= root.val;
        list.add(root.val);
        if (isleaf && target == 0) {//遍历到了叶结点，并且路径和为target时添加到路劲集合listA中
            listA.add(new ArrayList<>(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        //回溯，因为递归遍历时前面的值都压入了栈；遍历到底之后要退一个结点重新再找路径
        list.remove(list.size() - 1);
        return listA;
    }

    /*题36：二叉搜索树与双向链表，输入一颗二叉搜索树，将该二叉搜索树转换成一个排序的双向链表*/
    /*解题思路二叉排序树的中序遍历为有序排列*/
    static TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        ArrayList<TreeNode> list = new ArrayList<>();
        MidTraverse(pRootOfTree, list);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
            list.get(i + 1).left = list.get(i);//设置指针，构造双向链表
        }
        return list.get(0);
    }

    static void MidTraverse(TreeNode root, ArrayList<TreeNode> list) {
        if (root == null) return;
        MidTraverse(root.left, list);
        list.add(root);
        MidTraverse(root.right, list);
    }/*拓展做法：在中序遍历递归中使用指针，而不用list，详解《剑指offer 第二版》P193*/

    /*题37：序列化二叉树*/
    /*二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
     *从而使得内存中建立起来的二叉树可以持久保存。
     *序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，
     *序列化的结果是一个字符串，序列化时通过某种符号表示空节点（#），
     *以 ！表示一个结点值的结束（value!）。
     *二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
     */
    static class SerializeTree {
        static int index = 0;
        static StringBuffer str = new StringBuffer("");

        static String Serialize(TreeNode root) {
            if (root == null) {
                return str.toString();
            }
            str.append(root.val).append("!");
            if (root.left != null)
                Serialize(root.left);
            else
                str.append("#!");
            if (root.right != null)
                Serialize(root.right);
            else
                str.append("#!");
            return str.toString();
        }

        static TreeNode Deserialize(String str) {
            if (str == null || str.length() == 0) return null;
            //将字符串"1!2!4!#!#!5!#!#!3!6!#!#!7!#!#!"以！为标志分割为字符串数组{"1","2","4","#"....}
            String[] split = str.split("!");
            return DeserializeCore(split, 0);
        }

        static TreeNode DeserializeCore(String[] str, int i) {
            if (str[index].equals("#")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.valueOf(str[index]));
            node.left = DeserializeCore(str, ++index);//++index到达下一个需要反序列化的值
            node.right = DeserializeCore(str, ++index);
            return node;
        }
    }

    /*题54：二叉搜索树的第K大节点*/
    /*给定一棵二搜索树，找出其中第K大的结点的值*/
    /*或者先用中序遍历出来放在数组里面，再从数组里面找*/
    static int KthNode(TreeNode root, int k) {//返回第K大结点的值
        if (root == null) return -1;
        return MidTraverse1(root, k);
    }

    static int MidTraverse1(TreeNode root, int k) {//有返回值的中序遍历
        int target = -1;
        if (root.left != null)
            target = MidTraverse1(root.left, k);
        if (target == -1) {
            if (k == 1)
                target = root.val;
            k--;
        }
        if (target == -1 && root.right != null)
            target = MidTraverse1(root.right, k);
        return target;
    }

    /*题55：二叉树的深度*/
    /*思路：根节点表示的树的深度就是其左右子树深度的较大值再加1*/
    static int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return (left > right) ? left + 1 : right + 1;
    }

    /*题55：二、平衡二叉树*/
    /*输入一棵树的根节点，判断该树是否为平衡二叉树*/
    static boolean IsBanlanced(TreeNode root) {
        if (root == null) return true;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        if (Math.abs(left - right) > 1)
            return false;
        return IsBanlanced(root.left) && IsBanlanced(root.right);
    }

    public static void main(String[] args) {
        /*int[] arr = {1, 2, 4, 0, 0, 0, 3, 5, 0, 0, 6, 0, 0};
        TreeNode root = CreateBinaryTree.Init(arr, 0);
        PrintFromTopToBottom1(root);
        System.out.println(SerializeTree.Serialize(root));
        String str = SerializeTree.Serialize(root);
        TreeNode root1 = SerializeTree.Deserialize(str);
        PrintFromTopToBottom1(root1);*/
        TreeNode root = new TreeNode(1);
        System.out.println(root.left == null);
    }

}
