package com.demo.algorithm.swordoffer;

import java.util.HashMap;

/**
 * @author jack
 * @date 2020/2/12-18:20
 */
public class StringTest {

    /*题四：将字符串中空格替换成%20*/
    static String ReplaceBlank(StringBuffer str) {
        //因为遍历后知道有多少空格及总需多少位置了，用以下方法；从后往前
        // 如果没法知道总的大小，可以再创建一个暂存空间，把两字串值复制到暂存字符串中
        if (str == null) return null;
        int lengthO = str.length();
        for (int i = 0; i < lengthO; i++) {
            //遍历一遍数出空格数目，因须插入%20，包含一个空格则串尾增加两个位置
            if (str.charAt(i) == ' ') str.append("  ");
        }
        int lengthN = str.length();//增加后串的长度
        int p1 = lengthO - 1, p2 = lengthN - 1;
        while (p1 != p2) {
            if (str.charAt(p1) == ' ') {
                p1--;//遇到空格，进行替换并且p1前移一位p2前移3位
                str.setCharAt(p2--, '0');
                str.setCharAt(p2--, '2');
                str.setCharAt(p2--, '%');
            }
            //未遇到空格，p1，p2同时往前移动
            str.setCharAt(p2--, str.charAt(p1--));
        }
        return str.toString();
    }

    /*题19：正则表达式匹配*/
    /*请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
     *而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
     *例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配*/
    static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) return false;
        return matchCore(str, 0, pattern, 0);
    }

    /*采用递归方法，分pattern串下一个字符是不是'*'来讨论*/
    static boolean matchCore(char[] str, int i, char[] pattern, int j) {
        if (i == str.length && j == pattern.length) return true;
        if (i != str.length && j == pattern.length) return false;//考虑串str长于串pattern情况！
        boolean next = j + 1 < pattern.length && pattern[j + 1] == '*';//判断pattern字符串下一个字符是不是'*'
        if (next) {
            if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.'))
                return matchCore(str, i, pattern, j + 2) || matchCore(str, i + 1, pattern, j);
            else
                return matchCore(str, i, pattern, j + 2);
        } else {
            if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.'))
                return matchCore(str, i + 1, pattern, j + 1);
            else
                return false;
        }
    }

    /*题20：表示数值的字符串*/
    /*使用正则表达式来判断*/
    static boolean isNumberic(String s) {
//        return s.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
        return s.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
    }

    /*题50：第一个只出现一次的字符*/
    /*暴力法：两个for循环，遍历某一个字符时，遍历其后面的字符看是否有与他相同的
     * 数组和字符串问题，很多时候可以用暴力法，简单*/
    static char FirstNotRepeatingChar(String str) {
        if (str == null || str.trim().length() == 0) {
            return '0';
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            //如果map已经有了某个字符关键字，则该字符关键字对应的value值要+1
            if (map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            } else {//如果map没有该字符关键字，则将其加入map
                map.put(str.charAt(i), 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1)
                return str.charAt(i);
        }
        return '0';
    }

    /*题50拓展：字符流中第一个只出现一次的字符
     * 《剑指offer第二版》P247*/

    /*题58：翻转字符串*/
    /*题一、翻转单词顺序。例如：输入"I am a student."，则输出"student. a am I" */
    static String ReverseSentence(String str) {
        int blank = -1;
        String s = "";
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) == ' ') {
                int nextblank = j;
                s += ReverseSentenceCore(str.substring(blank + 1, nextblank)) + " ";
                blank = nextblank;//指针的移动是本题的重点
            }
        }
        s += ReverseSentenceCore(str.substring(blank + 1, str.length()));
        return ReverseSentenceCore(s);
    }

    static String ReverseSentenceCore(String str) {
        char[] chars = str.toCharArray();
        int len = chars.length - 1;
        int fast = 0, last = len;
        char temp;
        while (fast < last) {
            temp = chars[fast];
            chars[fast] = chars[last];
            chars[last] = temp;
            fast++;
            last--;
        }
        return String.valueOf(chars);
    }

    /*题58：二、左旋转字符串；例如输入"abcdefg"和2，输出"cdefgab"*/
    /*解题思路：分别翻转前后两部分字符串，再对整个字符串进行翻转*/
    static String LeftRotateString(String str, int k) {
        int len = str.length();
        if (k > len || k <= 0) return "k is out of boundry!";
        if (k == len) return ReverseSentenceCore(str);
        String s = ReverseSentenceCore(str.substring(0, k));
        String s1 = ReverseSentenceCore(str.substring(k, len));
        String res = s + s1;
        return ReverseSentenceCore(res);
    }

    public static void main(String[] args) {
        /*StringBuffer str = new StringBuffer("we are happy");
        //string和stringbuffer的转换
        String s = new String("we are famliy !");
        StringBuffer buffer = new StringBuffer(s);
        System.out.println(ReplaceBlank(str));
        System.out.println(ReplaceBlank(buffer));
        System.out.println("*********");
        System.out.println(isNumberic("1.23e-10+1"));
*/
        String str = "aabcdefdabd";
        System.out.println(FirstNotRepeatingChar(str));
    }

}
