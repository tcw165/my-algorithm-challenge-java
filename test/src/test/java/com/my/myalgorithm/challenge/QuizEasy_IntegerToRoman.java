// Copyright (c) 2016-present boyw165
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be
 * within the range from 1 to 3999.
 * <p/>
 * <pre>
 * Symbol | I   V   X    L    C     D      M
 * -------+---+---+---+----+-----+-----+------
 * Value  | 1   5  10   50   100   500  1,000
 * </pre>
 * I placed before V or X indicates one less, so four is IV (one less than five)
 * and nine is IX (one less than ten)
 * <p/>
 * X placed before L or C indicates ten less, so forty is XL (ten less than fifty)
 * and ninety is XC (ten less than a hundred)
 * <p/>
 * C placed before D or M indicates a hundred less, so four hundred is CD
 * (a hundred less than five hundred) and nine hundred is CM (a hundred less
 * than a thousand)[5]
 * <p/>
 * Example:
 * <pre>
 * "I" is 1
 * "II" is 2
 * "III" is 3
 * "IV" is 4
 * "V" is 5
 * "VI" is 6
 * "VII" and "IIIX" is 7
 * "VIII" and "IIX" is 8
 * "IX" is 9
 * "X" is 10
 * "XI" is 11
 * "XII" is 12
 * "XIII" is 13
 * "XIV" is 14
 * "DCXXI" is 621
 * </pre>
 * Reference:
 * <p/>
 * LeetCode: https://leetcode.com/problems/roman-to-integer/description/
 * <p/>
 * Wiki: https://en.wikipedia.org/wiki/Roman_numerals
 */
public class QuizEasy_IntegerToRoman {

    @Test
    public void test() throws Exception {
        Assert.assertEquals("I", intToRoman(1));
        Assert.assertEquals("II", intToRoman(2));
        Assert.assertEquals("III", intToRoman(3));
        Assert.assertEquals("IV", intToRoman(4));
        Assert.assertEquals("V", intToRoman(5));
        Assert.assertEquals("VI", intToRoman(6));
        Assert.assertEquals("VII", intToRoman(7));
        Assert.assertEquals("VIII", intToRoman(8));
        Assert.assertEquals("IX", intToRoman(9));
        Assert.assertEquals("X", intToRoman(10));
        Assert.assertEquals("DCXXI", intToRoman(621));
        Assert.assertEquals("MCMLIV", intToRoman(1954));
        Assert.assertEquals("MCMXC", intToRoman(1990));
        Assert.assertEquals("MMXIV", intToRoman(2014));
        Assert.assertEquals("MMMCMXCIX", intToRoman(3999));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 21% on LeetCode.
     */
    private String intToRoman(int num) {
        if (num <= 0 || num > 3999) {
            throw new IllegalArgumentException("Invalid given number");
        }

        final StringBuilder builder = new StringBuilder(10);

        // To avoid confusing and hard to read numbers with four characters
        // repeated in succession (such as IIII or XXXX), subtractive notation
        // is used.
        // Traverse through the number.
        // Time complexity: O(n).
        int radix = 1;
        while (num != 0) {
            int remain = num % 10;

            builder.insert(0, intToRomanInternal(remain, radix));

            num /= 10;
            radix *= 10;
        }

        return builder.toString();
    }

    private String intToRomanInternal(int num, int radix) {
        if (num == 0) return "";

        final StringBuilder builder = new StringBuilder(3);

        char one;
        char five;
        char ten;
        switch (radix) {
            case 1:
                one = 'I';
                five = 'V';
                ten = 'X';
                break;
            case 10:
                one = 'X';
                five = 'L';
                ten = 'C';
                break;
            case 100:
                one = 'C';
                five = 'D';
                ten = 'M';
                break;
            case 1000:
                one = 'M';
                five = '?';
                ten = '?';
                break;
            default:
                throw new IllegalArgumentException("Unsupported radix");
        }

        if (num == 1) {
            builder.append(one);
        } else if (num == 2) {
            builder.append(one)
                   .append(one);
        } else if (num == 3) {
            builder.append(one)
                   .append(one)
                   .append(one);
        } else if (num == 4) {
            builder.append(one)
                   .append(five);
        } else if (num == 5) {
            builder.append(five);
        } else if (num == 6) {
            builder.append(five)
                   .append(one);
        } else if (num == 7) {
            builder.append(five)
                   .append(one)
                   .append(one);
        } else if (num == 8) {
            builder.append(five)
                   .append(one)
                   .append(one)
                   .append(one);
        } else if (num == 9) {
            builder.append(one)
                   .append(ten);
        } else {
            throw new IllegalArgumentException("Not a roman integer");
        }

        return builder.toString();
    }
}
