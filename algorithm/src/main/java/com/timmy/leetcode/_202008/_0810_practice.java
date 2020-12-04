package com.timmy.leetcode._202008;

import java.util.HashMap;
import java.util.Map;

public class _0810_practice {

    public static void main(String[] args) {
//        System.out.println("-----------------------------------------");
//        _0810_practice demo = new _0810_practice();
////        String result = demo.intToRoman(1994);
////        String result = demo.intToRoman(58);
////        String result = demo.intToRoman(458);
////        String result = demo.intToRoman(4);
//        String result = demo.intToRoman(40);
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        _0810_practice demo = new _0810_practice();
//        int result = demo.romanToInt("IX");
//        int result = demo.romanToInt("LVIII");
        int result = demo.romanToInt("MCMXCIV");
        System.out.println("result:" + result);
        System.out.println("-----------------------------------------");


    }

    /**
     * 13. 罗马数字转整数
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * <p>
     * 遍历s取出字符，根据字符查找对应的数值，然后增加
     * 输入: "IX"
     * 输出: 9
     */
    public int romanToInt(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> numMap = new HashMap<>();
        numMap.put('I', 1);
        numMap.put('V', 5);
        numMap.put('X', 10);
        numMap.put('L', 50);
        numMap.put('C', 100);
        numMap.put('D', 500);
        numMap.put('M', 1000);

        int result = 0;
        char[] chars = s.toCharArray();
        result += numMap.get(chars[chars.length - 1]);   //个位数
        for (int i = chars.length - 2; i >= 0; i--) {
            if (numMap.get(chars[i]) < numMap.get(chars[i + 1])) {
                result -= numMap.get(chars[i]);
            } else {
                result += numMap.get(chars[i]);
            }
        }
        return result;
    }

    /**
     * 整数转罗马数字
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 把罗马数组对应的所有的情况都找出来，然后除以取模数
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        if (num <= 0 || num >= 3999) {
            return "";
        }
        int[] numItems = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] luomaItems = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < numItems.length; i++) {
            int temp = num / numItems[i];
            while (temp > 0) {
                sb.append(luomaItems[i]);
                temp--;
            }
            num = num % numItems[i];
        }
        return sb.toString();
    }

    /**
     * 12. 整数转罗马数字
     * 思路：求出数组的千，百，十，个位数的数字，
     * 然后根据数字和倍数不同，转换成不同的罗马数字
     * <p>
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * <p>
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * <p>
     * 输入: 3
     * 输出: "III"
     * <p>
     * 输入: 9
     * 输出: "IX"
     * <p>
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     * <p>
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */

    private static StringBuilder sb = new StringBuilder();

    public String intToRoman2(int num) {
        sb = new StringBuilder();
        if (num >= 1000) {
            int qian = num / 1000;
            addNums3(qian);
            int bai = (num - qian * 1000) / 100;
            System.out.println("bai1:" + bai);
            bai = num % 1000 / 100;
            System.out.println("bai2:" + bai);
            addNums2(bai);
            int shi = num % 100 / 10;
            System.out.println("shi:" + shi);
            addNums1(shi);
            int ge = num % 10;
            System.out.println("ge:" + ge);
            addNums(ge);
        } else if (num >= 100) {
            int bai = num / 100;
            addNums2(bai);
            int shi = num % 100 / 10;
            addNums1(shi);
            int ge = num % 10;
            addNums(ge);
        } else if (num >= 10) {
            int shi = num / 10;
            addNums1(shi);
            int ge = num % 10;
            addNums(ge);
        } else {
            addNums(num);
        }
        return sb.toString();
    }

    /**
     * 1~9:I
     * 1~3: I II III
     * 4: IV
     * 5: V
     * 6~8: VI,VII,VIII
     * 9: IX
     * 10:X
     */
    private static void addNums(int ge) {
        switch (ge) {
            case 1:
            case 2:
            case 3:
                for (int i = 0; i < ge; i++) {
                    sb.append("I");
                }
                break;
            case 4:
                sb.append("IV");
                break;
            case 5:
                sb.append("V");
                break;
            case 6:
            case 7:
            case 8:
                sb.append("V");
                for (int i = 0; i < (ge - 5); i++) {
                    sb.append("I");
                }
                break;
            case 9:
                sb.append("IX");
                break;
            case 10:
                sb.append("X");
                break;
        }
    }

    /**
     * 1~9十
     * 1~3: X XX XXX
     * 4: LV
     * 5: L
     * 6~8: LI,LII,LIII
     * 9: XC
     */
    private static void addNums1(int shi) {
        switch (shi) {
            case 1:
            case 2:
            case 3:
                for (int i = 0; i < shi; i++) {
                    sb.append("X");
                }
                break;
            case 4:
                sb.append("XL");
                break;
            case 5:
                sb.append("L");
                break;
            case 6:
            case 7:
            case 8:
                sb.append("L");
                for (int i = 0; i < (shi - 5); i++) {
                    sb.append("X");
                }
                break;
            case 9:
                sb.append("XC");
                break;
        }
    }

    /**
     * 1-3百 C
     * 1～3： C CC CCC
     * 4: CD
     * 5: D
     * 6~8: DC DCC DCC
     * 9: CM
     */
    private static void addNums2(int bai) {
        switch (bai) {
            case 1:
            case 2:
            case 3:
                for (int i = 0; i < bai; i++) {
                    sb.append("C");
                }
                break;
            case 4:
                sb.append("CD");
                break;
            case 5:
                sb.append("D");
                break;
            case 6:
            case 7:
            case 8:
                sb.append("D");
                for (int i = 0; i < (bai - 5); i++) {
                    sb.append("C");
                }
                break;
            case 9:
                sb.append("CM");
                break;
        }
    }

    /**
     * 1-3千 M
     * 1～3： M MM MMM
     * 4: 不考虑
     */
    private static void addNums3(int qian) {
        for (int i = 0; i < qian; i++) {
            sb.append("M");
        }
    }
}
