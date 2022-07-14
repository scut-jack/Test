package com.demo.algorithm.swordoffer;

import java.util.*;

/**
 * @author jack
 * @date 2020/2/14-14:30
 */
public class AlgorithmTest {

    /*题11：旋转数组的最小数字*/
    static int Min(int[] array) {
        int p1 = 0, p2 = array.length - 1, Mid = p1;
        if (array == null) return -1;
        //二分查找法效率更高
        while (array[p1] >= array[p2]) {
            if (p2 == p1 + 1) {
                Mid = p2;
                break;
            }
            Mid = (p1 + p2) / 2;
            //特殊情况，此时不能用二分查找了只能顺序找
            //{1,0,1,1,1}和{1,1,1,0,1}
            if (array[p1] == array[Mid] && array[Mid] == array[p2]) {
                int temp = array[p1];
                for (int i = p1 + 1; i <= p2; i++)
                    if (temp > array[i]) temp = array[i];
                return temp;
            }
            if (array[Mid] >= array[p1])
                p1 = Mid;
            else if (array[Mid] <= array[p2])
                p2 = Mid;
        }
        return array[Mid];
    }

    /*题12：矩阵中的路径*/
    /*回溯法解题*/
    static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows < 1 || cols < 1 || str == null) return false;
        boolean[] visited = new boolean[matrix.length];//标志位，初始化为false
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                //循环遍历二维数组，找到起点等于str第一个元素的值，
                // 再递归判断四周是否有符合条件的----回溯法
                if (hasPathCore(matrix, rows, cols, str, row, col, 0, visited))
                    return true;
            }
        }
        return false;
    }

    static boolean hasPathCore(char[] matrix, int rows, int cols, char[] str,
                               int row, int col, int len, boolean[] visited) {
        //递归终止条件
        if (row < 0 || col < 0 || row >= rows || col >= cols ||
                matrix[row * cols + col] != str[len] || visited[row * cols + col] == true)
            return false;
        //若len已经到达str末尾了，说明之前的都已经匹配成功了，直接返回true即可
        if (len == str.length - 1) return true;
        visited[row * cols + col] = true;
        //回溯，递归寻找，每次找到了就给k加一，找不到，还原
        if (hasPathCore(matrix, rows, cols, str, row - 1, col, len + 1, visited) ||
                hasPathCore(matrix, rows, cols, str, row + 1, col, len + 1, visited) ||
                hasPathCore(matrix, rows, cols, str, row, col - 1, len + 1, visited) ||
                hasPathCore(matrix, rows, cols, str, row, col + 1, len + 1, visited))
            return true;
        //走到这，说明这一条路不通，还原，再试其他的路径
        visited[row * cols + col] = false;
        return false;
    }

    /*题13：机器人的运动范围，回溯法*/
    static int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        return countingSteps(threshold, rows, cols, 0, 0, visited);
    }

    static int countingSteps(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
        //递归结束条件
        if (row < 0 || col < 0 || row >= rows || col >= cols ||
                visited[row][col] || bitSum(row) + bitSum(col) > threshold)
            return 0;
        visited[row][col] = true;
        return countingSteps(threshold, rows, cols, row - 1, col, visited) +
                countingSteps(threshold, rows, cols, row + 1, col, visited) +
                countingSteps(threshold, rows, cols, row, col - 1, visited) +
                countingSteps(threshold, rows, cols, row, col + 1, visited) + 1;
    }

    static int bitSum(int t) {//位和函数
        int count = 0;
        while (t != 0) {
            count += t % 10;
            t /= 10;
        }
        return count;
    }

    /*题14：剪绳子，动态规划，贪心算法*/
    static int cutRope(int n) {
        //贪心算法实现，用数学归纳法求f(n)
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int count = 0;
        if (n > 4) {
            count = n / 3;
        }
        if (n % 3 == 1)
            return ((int) Math.pow(3, count - 1)) * 2 * 2;
        else if (n % 3 == 2) {
            return ((int) Math.pow(3, count)) * 2;
        } else {
            return (int) Math.pow(3, count);
        }
    }
    /*总结：像动态规划以及贪心算法这些问题，其本质都是数学问题；
    若可以用数学公式进行建模，代码就会比较简单了*/

    /*题16：数值的整数次方，不得使用库函数*/
    /*本题主要涉及错误处理方式（三种）：返回值、全局变量、异常*/
    static double Power(double base, int exponent) {
        if (Double.doubleToLongBits(base) == 0.0 && exponent <= 0) {
            return -1;//表示此输入错误
        }
        if (Double.doubleToLongBits(base) == 0.0 && exponent > 0) {
            return 0.0;//输入的底数为0时输出结果也为0
        }
        double result = 1.0;
        if (exponent == 0) return 1.0;
        else if (exponent > 0) {
//            for (int i = 0; i < exponent; i++) {
//                result *= base;
//            }
            return PowerCore(base, exponent);
        } else {
            return 1.0 / PowerCore(base, -exponent);
        }
    }

    static double PowerCore(double base, int exponent) {
        //递归方法实现数值整数次方,复杂度变为log(n)相比于循环n次计算整数次方提高了
        if (exponent == 1) return base;
        if (exponent == 0) return 1;
        double result = PowerCore(base, exponent >> 1);
        result *= result;
        if ((exponent & 1) == 1)
            result *= base;
        return result;
    }

    /*题17：打印从1到最大的n位数*/
    /*其实是大数问题，当打印的数超过int类型数据的长度时如何处理*/
    /*一般两种处理：将大数看成数组或者字符串，这里要用数组模拟数字的+1进位过程*/
    static void PrintNumbers(int n) {
        if (n <= 0) return;
        int[] number = new int[n];
        while (!increment(number)) {
            boolean isBegin0 = true;//设置一个标志位，使得开头的0不打印
            for (int i = 0; i < number.length; i++) {
                if (isBegin0 && number[i] != 0)
                    isBegin0 = false;
                if (!isBegin0)
                    System.out.print(number[i]);
            }
            System.out.println();
        }
    }

    static boolean increment(int[] number) { //使用数组实现对数进行+1操作
        if (number.length < 1) return false;
        boolean isOverFlow = false;//最高位产生进位标志
        int carry = 0;//进位位
        for (int i = number.length - 1; i >= 0; i--) {
            int sum = number[i] + carry;
            if (i == number.length - 1) sum += 1;//最低位加一
            if (sum >= 10) {//判断一个位的和是否满足进位条件
                if (i == 0) isOverFlow = true;//最高位产生进位
                else {//普通位产生进位
                    carry = 1;
                    number[i] = 0;
                    sum = 0;
                }
            } else {//若最低位加一之后没有进位，则整个数组+1操作完成
                number[i] = sum;
                break;
            }
        }
        return isOverFlow;
    }

    /*上一题拓展：递归实现全排列，可用于解决上一题数字打印*/
    static void print(int[] num, int index) {
        //从0开始打印到n位数的最大值
        if (index == num.length) {
            for (int i = 0; i < num.length; i++) {
                System.out.print(num[i]);
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < 10; i++) {
            num[index] = i;
            print(num, index + 1);
        }
    }

    /*题39：数组中出现次数超过一半的数字*/
    /*注意：如果有不改变数组的要求时，一般要考虑使用指针！*/
    static int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) return 0;
        int pre = array[0];//记录前一个值
        int count = 1;//记录前一个值出现的次数
        for (int i = 1; i < array.length; i++) {
            if (pre == array[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    pre = array[i];
                    count = 1;
                }
            }
        }//for循环结束后pre会指向出现次数超过一半的数（数组存在次数超过一半的数时）
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == pre) num++;
        }
        if (num > (array.length >> 1))//如果有超过一半，则输出该数字
            return pre;
        return 0;//否则输出0，表示错误
    }

    /*题40：最小的K个数*/
    /*注意：这种做法会改变数组，平均时间复杂度为O(n)*/
    static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k > input.length || input == null || k < 1) return list;//排除错误情况
        int start = 0;
        int end = input.length - 1;
        int partition = Partition(input, start, end);
        while (partition != k - 1) {
            if (partition > k - 1) {
                end = partition - 1;
                partition = Partition(input, start, end);
            } else {
                start = partition + 1;
                partition = Partition(input, start, end);
            }
        }
        for (int i = 0; i <= partition; i++) {
            list.add(input[i]);
        }
        return list;
    }

    static int Partition(int[] arr, int start, int end) {//快排中的定位函数
        int p = arr[start];
        while (start < end) {
            while (start < end && arr[end] >= p) end--;
            int temp = arr[start];//交换
            arr[start] = arr[end];
            arr[end] = temp;
            while (start < end && arr[start] <= p) start++;
            int temp1 = arr[start];//交换
            arr[start] = arr[end];
            arr[end] = temp1;
        }
        return start;
    }

    /*解法二：用一个大顶堆来存储k个数，接下来每个数比堆顶大则弹出堆顶进行压入，否则不压入*/
    static ArrayList<Integer> GetLeastNumbers_Solution1(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k > input.length || input == null || k < 1) return list;//排除错误情况
        //java中的优先队列是基于堆实现的
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {//反序，让最大值在队列最前面
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < input.length; i++) {
            if (maxHeap.size() != k) {
                maxHeap.offer(input[i]);
            } else if (maxHeap.peek() > input[i]) {
                maxHeap.poll();
                maxHeap.offer(input[i]);
            }
        }
        for (Integer integer : maxHeap) {
            list.add(integer);
        }
        return list;
    }

    /*题41：数据流中的中位数*/
    /*注意：由于数据由一个数据流中读出，因而数据的数目随着时间的变化而增加*/
    /*解题思路：用大顶堆存小的数，用小顶堆存大的数；则大顶堆的堆顶和小顶堆的堆顶是中位数*/
    class MidNumber {
        /*用例[5,2,3,4,1,6,7,0,8]对应输出应该为:"5.00 3.50 3.00 3.50 3.00 3.50 4.00 3.50 4.00 "*/
        int count = 0;//记录数据流奇数个还是偶数个
        PriorityQueue<Integer> Min = new PriorityQueue<>();//优先队列实现小顶堆
        PriorityQueue<Integer> Max = new PriorityQueue<>(new Comparator<Integer>() {//大顶堆
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        void Insert(Integer num) {
            if (count % 2 == 0) {//插入最小堆
                Max.add(num);//防止插入最小堆的数小于最大堆的堆顶
                int temp = Max.poll();
                Min.add(temp);
            } else {//插入最大堆
                Min.add(num);
                int temp1 = Min.poll();
                Max.add(temp1);
            }
            count++;
        }

        Double GetMedian() {//要注意Double的开头是大写的D，为double的包装类
            if (count % 2 == 0) {
                return new Double((Max.peek() + Min.peek())) / 2;
            } else {
                return new Double(Min.peek());
            }
        }
    }

    /*题42：连续子数组的最大和*/
    /*思路：利用动态规划的思想，按动态规划的公式来求解*/
    static int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) return 0;//返回0表示输入错误
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int Max = array[0];
        for (int i = 1; i < array.length; i++) {
            //辅助数组存当前值之前的最大和
            int newMax = dp[i - 1] + array[i];
            if (newMax > array[i]) {
                dp[i] = newMax;
            } else {
                dp[i] = array[i];
            }
            if (dp[i] > Max) Max = dp[i];
        }
        return Max;
    }

    /*题48：最长不含重复字符的子字符串，计算该子字符串的长度
     *在"arabcacfr"中，最长不含重复的子字符串是"acfr"长度为4*/
    /*动态规划法：定义函数f(i)为：以第i个字符为结尾的不含重复字符的子字符串的最大长度。
     *（1）当第i个字符之前未出现过，则有：f(i)=f(i-1)+1
     *（2）当第i个字符之前出现过，记该字符与上次出现的位置距离为d
     * 　1）如果d<=f(i-1)，则有f(i)=d；
     * 　2）如果d>f(i-1)，则有f(i)=f(i-1)+1；*/
    static void LongestSubstringWithoutDuplication() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {//hasNext()等待键盘输入，按回车键结束输入并返回true
            String str = sc.next();//表示 按回车键之前输入的字符串
            int[] pos = new int[26];//用来存储每个字符上次出现在字符串中位置的下标
            int preLength = 0;//f(i-1)
            int curLength = 0;//f(i)
            int maxLength = 0;
            for (int i = 0; i < pos.length; i++)
                pos[i] = -1;
            for (int i = 0; i < str.length(); i++) {
                int letterNumber = str.charAt(i) - 'a';
                if (pos[letterNumber] < 0 || i - pos[letterNumber] > preLength) {
                    curLength = preLength + 1;
                } else {
                    curLength = i - pos[letterNumber];
                }
                pos[letterNumber] = i;
                if (curLength > maxLength)
                    maxLength = curLength;
                preLength = curLength;
            }
            System.out.println(maxLength);
        }
    }

    /*题43:1~n整数中1出现的次数,1~13中包含1的数字有1、10、11、12、13因此共出现6次*/
    /*k = n % (i * 10)
     *count(i) = (n / (i * 10)) * i + (if(k > i * 2 - 1) i else if(k < i) 0 else k - i + 1)*/
    static int NumberOf1Between1AndN_Solution(int n) {
        /*int count = 0;
        while (n != 0) {
            if (n % 10 == 1)
                count++;
            n = n / 10;
        }*/
        String s = String.valueOf(n);//将数字转换为字符串
        int len = s.length();//求数字的位数
        int count = 0;//计数变量
        for (int i = 0; i < len; i++) {
            int j = (int) Math.pow(10, i);
            count += (n / (10 * j)) * j + Count(n, j);//计算个，十，百...位含1的情况
        }
        return count;
    }

    static int Count(int n, int i) {//辅助函数，计算尾部含1的情况
        if (n % (10 * i) > 2 * i - 1)
            return i;
        else if (n % (10 * i) < i)
            return 0;
        else return n % (10 * i) - i + 1;
    }

    /*题44：数字序列中某一位的数字*/
    /*012345678910111213...从0开始计数，第5位是5，第13位是1，求任意第n位对应的数字*/
    static int DigitAtIndex(int index) {
        if (index < 0)
            return -1;
        int digits = 1;//数字位数
        for (; ; ) {//循环直到找到index位于m位数字之中后退出
            int numbers = countInt(digits);//有digits这么多位的数字个数
            if (index < numbers * digits)//例如：找到位置1001位于3位数的位置，返回
                return digitAtIndex(index, digits);//此时传入的index为减去前面小于m位的数占的那些位置后的
            index -= digits * numbers;
            digits++;
        }
    }

    static int countInt(int digits) {//返回m位的数字的个数
        //输入2返回两位数10~99的个数90，输入3返回三位数100~999的个数900
        if (digits == 1) return 10;
        return 9 * (int) Math.pow(10, digits - 1);
    }

    static int beginNumber(int digits) {//求m位数的第一个数字，例如第一个三位数为100
        if (digits == 1) return 0;
        return (int) Math.pow(10, digits - 1);
    }

    static int digitAtIndex(int index, int digits) {
        int number = beginNumber(digits) + index / digits;//计算出index位置对应的数字（这个数字可能占好几位，index对应其中一位）
        int indexFromRight = digits - index % digits;//计算余数，余数为0则是第一位，余数为1则是第二位
        for (int i = 1; i < indexFromRight; i++) {
            number /= 10;
        }
        return number % 10;
    }

    /*题45：把数组排成最小的数*/
    /*例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323*/
    /*本题也属于大数问题，一般解决大数问题就是将数字转化为字符串*/
    static String PrintMinNumber(int[] numbers) {
        String s = "";
        if (numbers == null) return s;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String str1 = o1 + "" + o2;
                String str2 = o2 + "" + o1;
                return str1.compareTo(str2);
            }
        });
        for (int j : list) {
            s += j;
        }
        return s;
    }

    /*题46：把数字翻译成字符串*/
    /*给定一个数字，我们按照如下规则把它翻译为字符串：
     *0翻译成"a"，1翻译成"b"，……，11翻译成"l"，……，25翻译成"z"。
     *一个数字可能有多个翻译。例如12258有5种不同的翻译，
     *它们分别"bccfi", "bwfi", "bczi", "mcfi" 和"mzi" 。
     *请编程实现一个函数用来计算一个数字有多少种不同的翻译方法。*/
    /*用f(i)来表示从第i位开始的不同翻译数目，可以得到有：
     *f(i)=f(i+1)+g(i,i+1)*f(i+2)。i和i+1位数字拼起来在10~25范围内时g(i,i+1)的值为1，否则为0。*/
    static int getTranslationCount(int number) {
        if (number < 0) return 0;
        String str = String.valueOf(number);
        int[] counts = new int[str.length()];
        for (int i = str.length() - 1; i >= 0; i--) {
            if (i == str.length() - 1) {
                counts[i] = 1;
            } else {
                counts[i] = counts[i + 1];
                if (canBeTrans(str, i)) {
                    if (i == str.length() - 2)
                        counts[i] += 1;
                    else
                        counts[i] += counts[i + 2];
                }
            }
        }
        return counts[0];
    }

    static boolean canBeTrans(String sNumber, int i) {
        int i1 = sNumber.charAt(i) - '0';
        int i2 = sNumber.charAt(i + 1) - '0';
        int num = i1 * 10 + i2;
        if (num >= 10 && num <= 25) {
            return true;
        }
        return false;
    }

    /*题47：礼物的最大价值*/
    /*此题题意是：在二维数组中找出一条路径，使得路径上结点值之和最大，要求是从(0,0)出发只能向下或向右走；
     *递归实现此题代码如下：根据公式f(i,j)=Max(f(i-1,j),f(i,j-1))+value(i,j),f(i,j)表示在(i,j)时路径最大值*/
    static int getMaxValue_solution(int[][] arr, int rows, int cols) {//传入坐标(rows,cols)
        if (arr == null) return -1;//用返回值-1 报传入空指针的错误
        if (rows == 0 && cols > 0)
            return arr[0][cols] + getMaxValue_solution(arr, 0, --cols);
        else if (cols == 0 && rows > 0)
            return arr[rows][0] + getMaxValue_solution(arr, --rows, 0);
        if (rows == 0 && cols == 0)
            return arr[0][0];
        int row = rows - 1, col = cols - 1;
        /*注意，如果在getMaxValue_solution的形参中传入--rows或者--cols
         *前面的rows自减1之后再传到第二个函数中，发生错误！
         *因此用row=rows-1来传*/
        return arr[rows][cols] + Math.max(getMaxValue_solution(arr, row, cols), getMaxValue_solution(arr, rows, col));
    }

    /*题47迭代实现*/
    /*对辅助空间进行优化，辅助数组不用和m*n的二维数组一样大，只需要保存上一层的最大值就可以*/
    static int getMaxValue_solution1(int[][] values) {
        if (values == null || values.length <= 0 || values[0].length <= 0)
            return 0;
        int rows = values.length;
        int cols = values[0].length;
//      int[][] maxValue=new int[rows][cols];
        int[] maxValue = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int left = 0;
                int up = 0;
                if (i > 0)
//                  up=maxValue[i-1][j];
                    up = maxValue[j];
                if (j > 0)
//                  left=maxValue[i][j-1];
                    left = maxValue[j - 1];
//              maxValue[i][j]=Math.max(up, left)+values[i][j];
                maxValue[j] = Math.max(up, left) + values[i][j];
            }
        }
//      return maxValue[rows-1][cols-1];
        return maxValue[cols - 1];
    }

    /*题49：丑数*/
    /*把只包含质因子2、3和5的数称作丑数（Ugly Number）
     *习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数*/
    static int getUglyNumber(int index) {
        if (index <= 0) return 0;
        int number = 0;
        int uglyFound = 0;
        while (uglyFound < index) {
            ++number;
            if (IsUgly(number))
                uglyFound++;
        }
        return number;
    }

    static boolean IsUgly(int number) {//判断一个树是否为丑数
        while (number % 2 == 0)
            number /= 2;
        while (number % 3 == 0)
            number /= 3;
        while (number % 5 == 0)
            number /= 5;
        return number == 1 ? true : false;
    }

    /*题49算法优化：用空间换时间，构造一个丑数数组，
     *从乘2，乘3，乘5三个队列中选出最小的丑数加入丑数数组，
     * 三个队列操作可以用三个指针来代替*/
    static int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        int t2 = 0, t3 = 0, t5 = 0;
        while (arr.size() < index) {
            int m2 = arr.get(t2) * 2;
            int m3 = arr.get(t3) * 3;
            int m5 = arr.get(t5) * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            arr.add(min);
            if (min == m2) t2++;
            if (min == m3) t3++;
            if (min == m5) t5++;
        }
        return arr.get(arr.size() - 1);
    }

    /*题51：数组中的逆序对；
     *在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对*/
    /*暴力法：两个for循环遍历
     * 第二种方法：归并排序的思想*/
    static int count = 0;//全局变量

    static int InversePairs(int[] array) {
        Msort(array, 0, array.length - 1);
        return count;
    }

    static void Msort(int[] arr, int low, int high) {
        if (low == high) return;
        int mid = (low + high) >> 1;
        Msort(arr, low, mid);
        Msort(arr, mid + 1, high);
        Merge(arr, low, mid, high);
    }

    static void Merge(int[] arr, int low, int mid, int high) {//合并两个有序的数组，注意必须有序
        int[] temp = new int[high - low + 1];
        int p1 = low, p2 = mid + 1, t = 0;
        while (p1 <= mid && p2 <= high) {
            if (arr[p1] > arr[p2]) {
                temp[t++] = arr[p2++];
                count += mid - p1 + 1;//计算逆序对，若p1指针对应的值比p2对应的大，则p1指针后面的全部比p2大
            } else {
                temp[t++] = arr[p1++];
            }
        }
        //长短不一情况，没比较完的补齐
        while (p1 <= mid) temp[t++] = arr[p1++];
        while (p2 <= high) temp[t++] = arr[p2++];
        for (int i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        /*System.out.println(NumberOf1Between1AndN_Solution(21));
        System.out.println(DigitAtIndex(1001));
        int[] num = {12, 42, 315, 99};
        System.out.println(PrintMinNumber(num));*/
        /*int[][] aaa = {{1, 10, 3, 8}, {12, 2, 9, 6}, {5, 7, 4, 11}, {3, 7, 16, 5}};
        int[][] aa1 = {{1, 2}, {3, 4}};
        System.out.println(aa1[0][0] + " " + aa1[1][1]);
        System.out.println(getMaxValue_solution1(aaa));
        int a = 3, b = 4;
        int a1 = --a, b1 = --b;
        int a2 = a - 1, b2 = b - 1;
        System.out.println(a1 + "  " + b1);
        System.out.println(a2 + "  " + b2);
        int[] r = {7, 5, 6, 4, 1};
        System.out.println(InversePairs(r));*/
        System.out.println(getTranslationCount(12258));
        LongestSubstringWithoutDuplication();
    }
}
