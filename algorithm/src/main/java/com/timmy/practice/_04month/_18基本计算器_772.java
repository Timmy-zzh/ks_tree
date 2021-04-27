package com.timmy.practice._04month;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class _18基本计算器_772 {

    public static void main(String[] args) {
        _18基本计算器_772 demo = new _18基本计算器_772();
//        int res = demo.calculate("1 + 1 + 3");
//        int res = demo.calculate(" 6 +3-4 ");
//        int res = demo.calculate(" 5+5×2 ");
//        int res = demo.calculate(" 3-2×14/7+2 ");
        int res = demo.calculate(" 2×(5+5×2)/3+(6/2+8)");
        System.out.println("res:" + res);
    }

    /**
     * 2.4.添加左右括号表达式
     * -1。先将表达式字符串转成字符保存到队列中，然后遍历出队列，
     * -2。当遇到左括号时，需要先遍历括号里面的内容并计算结果，知道遇到右括号，并将结果保存到栈中，
     */
    private int calculate(String s) {
        System.out.println(s);
        char[] chars = s.toCharArray();
        Queue<Character> queue = new LinkedList<>();
        //1.所有字符保存到队列中
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                queue.offer(chars[i]);
            }
        }
        queue.offer('+');

        return calculate(queue);
    }

    private int calculate(Queue<Character> queue) {
        char sign = '+';
        int num = 0;//保存单个字符的数字
        Stack<Integer> stack = new Stack<>();
        //2。遍历队列，先进先出
        while (!queue.isEmpty()) {
            Character poll = queue.poll();
            System.out.println("----poll:" + poll);
            //2。1。如果遍历到的是数组
            if (Character.isDigit(poll)) {
                // (poll - '0')是当前数字，10*num 是前面的数字大小
                num = 10 * num + (poll - '0');
            } else if ('(' == poll) {//遇到左括号
                num = calculate(queue);
                System.out.println("()num:" + num);
            } else {
                //2.2.遍历到操作符
                System.out.println("sign:" + sign + " ,pop:" + (!stack.isEmpty() ? stack.peek() : "") + " ,num:" + num);
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-1 * num);
                } else if (sign == '×') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }

                System.out.println("===" + stack.toString());
                num = 0;
                sign = poll;

                if (sign == ')') {     //右括号
                    break;
                }
            }
        }
        System.out.println(stack.toString());
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    /**
     * 2.3. 增加乘法与除法操作符
     * -使用栈来保存每次处理好的数字
     * -如果遍历到的是+，- 操作符，则直接将操作符后面的数字入栈，-号操作符后面的数字取负数
     * -如果是*，/ 操作符，则取栈顶元素与操作符后面的数字进行运算，并将结果入栈
     * -最后将栈中所有元素出栈并相加，就是结果
     */
    private int calculate_v3(String s) {
        System.out.println(s);
        char[] chars = s.toCharArray();
        Queue<Character> queue = new LinkedList<>();

        //1.所有字符保存到队列中
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                queue.offer(chars[i]);
            }
        }
        queue.offer('+');
        char sign = '+';

        int num = 0;//保存单个字符的数字
        Stack<Integer> stack = new Stack<>();
        //2。遍历队列，先进先出
        while (!queue.isEmpty()) {
            Character poll = queue.poll();
            System.out.println("----poll:" + poll);
            //2。1。如果遍历到的是数组
            if (Character.isDigit(poll)) {
                // (poll - '0')是当前数字，10*num 是前面的数字大小
                num = 10 * num + (poll - '0');
            } else {
                //2.2.遍历到操作符
                System.out.println("sign:" + sign + " ,pop:" + (!stack.isEmpty() ? stack.peek() : "") + " ,num:" + num);
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-1 * num);
                } else if (sign == '×') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                System.out.println("===" + stack.toString());
                num = 0;
                sign = poll;
            }
        }
        System.out.println(stack.toString());
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    /**
     * 2.2.操作符增加-号，
     * -因为存在减号，所以新增一个变量sign表示结果是否+还是-
     * -在出队列过程中，都是在遇到操作符时，进行处理操作符前面遍历到的数字，变量sign记录的是前一个操作符
     * -所以在计算结果时，根据当前操作的的操作符是前一个，处理完后sign重新赋值
     */
    private int calculate_v2(String s) {
        char[] chars = s.toCharArray();
        Queue<Character> queue = new LinkedList<>();

        //1.所有字符保存到队列中
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                queue.offer(chars[i]);
            }
        }
        queue.offer('+');
        char sign = '+';

        int num = 0;//保存单个字符的数字
        int sum = 0;
        //2。遍历队列，先进先出
        while (!queue.isEmpty()) {
            Character poll = queue.poll();
            System.out.println("----poll:" + poll);
            //2。1。如果遍历到的是数组
            if (Character.isDigit(poll)) {
                // (poll - '0')是当前数字，10*num 是前面的数字大小
                num = 10 * num + (poll - '0');
            } else {
                //2.2.遍历到操作符
                if (sign == '+') {
                    sum += num;
                } else if (sign == '-') {
                    sum -= num;
                }
                num = 0;
                sign = poll;
            }
        }
        return sum;
    }

    /**
     * 1.理解题意
     * -输入一个字符串，字符串的内容是简单的表达式
     * --字符串内容包括：空格，非负整数，+,-,*,/ 四种操作符，和左右括号（，）
     * -现要实现计算器的功能，计算出表达式的最终结果
     * 2。解题思路
     * 2。1。-假设字符串当前只包含非负整数，+号，
     * -先将字符串转换成字符数组，遍历数组中的每个字符，使用队列保存所有的字符，（过滤调空字符）
     * -现在表达式的内容都在队列中，先进先出
     * -当遇到数字字符时，继续出队列，把所有的数字都遍历出来，并使用一个变量num进行保存
     * -当遇到+操作符时，需要将num的值加到sum中，并且num的值值为0，继续后面的队列字符获取
     * 3。边界和细节处理
     * -因为队列中最后一个操作符，后面还有数字，所以队列循环之后需要加上
     * --改进：为了在循环内处理所有操作，在队列添加字符时，最后添加操作符+
     */
    private int calculate_v1(String s) {
        char[] chars = s.toCharArray();
        Queue<Character> queue = new LinkedList<>();

        //1.所有字符保存到队列中
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                queue.offer(chars[i]);
            }
        }
        queue.offer('+');

        int num = 0;//保存单个字符的数字
        int sum = 0;
        //2。遍历队列，先进先出
        while (!queue.isEmpty()) {
            Character poll = queue.poll();
            System.out.println("----poll:" + poll);
            //2。1。如果遍历到的是数组
            if (Character.isDigit(poll)) {
                // (poll - '0')是当前数字，10*num 是前面的数字大小
                num = 10 * num + (poll - '0');
            } else {
                //2.2.遍历到+操作符
                sum += num;
                num = 0;
            }
        }
        return sum;
    }
    /**
     * LeetCode 第 772 题，基本计算器：实现一个基本的计算器来计算简单的表达式字符串。
     *
     * 说明：
     * 表达式字符串可以包含左括号 ( 和右括号 )，加号 + 和减号 -，非负整数和空格。
     * 表达式字符串只包含非负整数， +  -  *  / 操作符，左括号 ( ，右括号 ) 和空格。整数除法需要向下截断。
     *
     * 示例 1：
     * "1 + 1" = 2
     * " 6-4 / 2 " = 4
     * "2×(5+5×2)/3+(6/2+8)" = 21
     * "(2+6×3+5- (3×14/7+2)×5)+3" = -12
     */
}
