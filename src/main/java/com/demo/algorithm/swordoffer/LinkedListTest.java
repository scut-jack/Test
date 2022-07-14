package com.demo.algorithm.swordoffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author jack
 * @date 2020/2/13-17:04
 */
public class LinkedListTest {
    public static void main(String[] args) {
        ListNode head = new ListNode(-1);//定义链表的头节点
        ListNode p = head;
        for (int i = 0; i < 10; i++) {
            p.next = new ListNode(i);
            p = p.next;
        }
        p = head;
        while (p != null) {
            System.out.print(p.val + "  ");
            p = p.next;
        }
        System.out.println();
        System.out.println(FindKthToTail(head, 12));
    }

    /*题六：从尾到头打印链表*/
    static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        //用栈来实现反向打印
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        if (listNode == null) return list;
        while (listNode != null) {
            stack.push(listNode.val);//入栈
            listNode = listNode.next;
        }
        while (!stack.isEmpty())
            list.add(stack.pop());
        return list;
    }

    /*题18：在O(1)时间内删除链表的结点*/
    /*给定单向链表的头指针和一个结点指针，定义一个函数在O(1)时间内删除该结点；
     * 遍历的办法肯定不行，用待删除结点的下一结点的值覆盖待删除结点的值，再删除其下一结点*/
    static ListNode DeleteNode(ListNode head, ListNode index) {
        if (head == null || index == null) return head;
        if (head.next == null && index == head) head = null;//有且只有一个结点
        if (index.next == null) {//index是链表的尾结点，删除index需从头遍历
            ListNode p = head;
            while (p.next != index) {
                p = p.next;
            }
            p.next = null;
        } else {
            ListNode temp = index.next;//暂存待删除结点的下一个结点
            index.val = temp.val;//删除了待删除结点的下一个结点后，要把下一结点的值给待删除结点
            index.next = temp.next;
        }
        return head;
    }

    /*题22：链表中倒数第K个结点*/
    /*本题要注意K的数值大于结点个数情况，以及倒数第0个情况*/
    static ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k == 0) return null;
        ListNode pre = head, end = head;
        for (int i = 1; i <= k - 1; i++) {
            if (end.next == null) return null;
            end = end.next;
        }
        while (end.next != null) {
            pre = pre.next;
            end = end.next;
        }
        return pre;
    }

    /*题23：链表中环的入口结点*/
    /*思路：首先判断是否有环，找出环中结点个数；然后同步走找出入口结点*/
    static ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode low = pHead, fast = pHead;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
            if (low == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        //执行到这里说明找到环了
        int count = 1;//用来对环中结点计数
        fast = fast.next;
        while (low != fast) {
            fast = fast.next;
            count++;
        }//退出循环时count值为环中结点个数
        low = pHead;
        fast = pHead;
        while (count-- != 0) {
            fast = fast.next;
        }//fast指针先走环中结点数那么多步
        while (low != fast) {
            low = low.next;
            fast = fast.next;
        }//然后一起走，相遇时在环的入口结点
        return low;
    }

    /*题24：反转链表，输入一个链表的头结点，反转该链表并输出反转后链表头结点*/
    static ListNode ReverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode p1 = head, p2 = p1.next, temp = null;
        p1.next = null;//设置链表的尾结点，不然会产生环链
        while (p2 != null) {//循环依次改变指针方向
            temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }
        return p1;
    }

    /*题25：合并两个排序的链表*/
    /*输入两个递增排序的链表，合并这两个链表并使新链表的结点依然递增排序*/
    /*合并操作都是这个思路，利用第三方暂存，原两两比较，结果放在第三方*/
    static ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        else if (list2 == null) return list1;
        ListNode Mhead = new ListNode(-1);//新建一个结点用来保存合并的链表
        ListNode NewList = Mhead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                Mhead.next = list1;
                list1 = list1.next;
            } else {
                Mhead.next = list2;
                list2 = list2.next;
            }
            Mhead = Mhead.next;
        }
        if (list1 == null) Mhead.next = list2;
        if (list2 == null) Mhead.next = list1;
        return NewList.next;
        /*拓展：合并思路的递归实现*/
    }

    /*题52：两个链表的第一个公共结点*/
    /*暴力法：遍历第一个链表的某一结点，并判断该结点是否在第二链表上面，复杂度O(mn)*/
    /*思路：两个链表碰到第一个公共结点之后，后面的结点全部相同；
     *相当于字母Y的两边不一样长，然后要找到交叉点；解决就是把长的舍去一部分，跟短的一起走*/
    /*思路二：还可以利用额外两个辅助栈，并把链表分别压入*/
    static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        /*方法1：实现思路，代码比较多
        int count1 = 0, count2 = 0;
        ListNode p1 = pHead1, p2 = pHead2;
        while (p1 != null) {
            p1 = p1.next;
            count1++;
        }
        while (p2 != null) {
            p2 = p2.next;
            count2++;
        }
        p1 = pHead1;
        p2 = pHead2;
        if (count1 == count2) {
            while (p1 != null && p2 != null) {
                if (p1 == p2) break;
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        } else if (count1 > count2) {
            int k = count1 - count2;
            while (k > 0) {
                p1 = p1.next;
                k--;
            }
            while (p1 != null && p2 != null) {
                if (p1 == p2) break;
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        } else {
            int k = count2 - count1;
            while (k > 0) {
                p2 = p2.next;
                k--;
            }
            while (p1 != null && p2 != null) {
                if (p1 == p2) break;
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }*/
        ListNode p1 = pHead1, p2 = pHead2;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
            if (p1 != p2) {
                if (p1 == null) p1 = pHead2;//长短不一情况，到尾时调到另一链表的头结点
                if (p2 == null) p2 = pHead1;
            }
        }
        return p1;
    }

    /*题62：圆圈中最后剩下的数字，约瑟夫环问题*/
    /*0, 1, …, n-1这n个数字排成一个圆圈，
     *从数字0开始每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。*/
    static int lastRemaining(int n, int m) {
        if (n < 1 || m < 1) return -1; //出错
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++)
            list.add(i);
        int removeIndex = 0;
        while (list.size() > 1) {
            removeIndex = (removeIndex + m - 1) % list.size();
            list.remove(removeIndex);
        }
        return list.getFirst();
    }

    /*思路：我们把最后剩下的数字记为f(n,m)，
     * 则f(n,m) =（f(n-1,m)+m)%n ，且f(1,m) = 0*/
    static int lastRemaining1(int n, int m) {
        if (n < 1 || m < 1)
            return -1; //出错
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;  //这里是i不是n！！！
        }
        return last;
    }
}
