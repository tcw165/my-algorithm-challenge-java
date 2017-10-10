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
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be
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
 * <br/>
 * <ul>
 *     <li>LeetCode: https://leetcode.com/problems/roman-to-integer/description/</li>
 *     <li>Wiki: https://en.wikipedia.org/wiki/Roman_numerals</li>
 * </ul>
 */
public class QuizEasy_RomanToInteger {

    @Test
    public void test() throws Exception {
        Assert.assertEquals(1, romanToInt("I"));
        Assert.assertEquals(2, romanToInt("II"));
        Assert.assertEquals(3, romanToInt("III"));
        Assert.assertEquals(4, romanToInt("IV"));
        Assert.assertEquals(5, romanToInt("V"));
        Assert.assertEquals(6, romanToInt("VI"));
        Assert.assertEquals(7, romanToInt("VII"));
        Assert.assertEquals(8, romanToInt("VIII"));
        Assert.assertEquals(9, romanToInt("IX"));
        Assert.assertEquals(10, romanToInt("X"));
        Assert.assertEquals(621, romanToInt("DCXXI"));
        Assert.assertEquals(1954, romanToInt("MCMLIV"));
        Assert.assertEquals(1990, romanToInt("MCMXC"));
        Assert.assertEquals(2014, romanToInt("MMXIV"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 56.26% on LeetCode.
     */
    private int romanToInt(String s) {
        // To avoid confusing and hard to read numbers with four characters
        // repeated in succession (such as IIII or XXXX), subtractive notation
        // is used.
        // Traverse through the string.
        // Time complexity: O(n).
        int sum = 0;
        for (int i = 0, size = s.length(); i < size; ++i) {
            int num = romanToIntInternal(s.charAt(i));

            int j = i + 1;
            if (j < size) {
                int nextNum = romanToIntInternal(s.charAt(j));

                if (num < nextNum) {
                    sum -= num;
                } else {
                    sum += num;
                }
            } else {
                sum += num;
            }
        }

        return sum;
    }

    private int romanToIntInternal(char c) {
        if (c == 'I') {
            return 1;
        } else if (c == 'V') {
            return 5;
        } else if (c == 'X') {
            return 10;
        } else if (c == 'L') {
            return 50;
        } else if (c == 'C') {
            return 100;
        } else if (c == 'D') {
            return 500;
        } else if (c == 'M') {
            return 1000;
        } else {
            throw new IllegalArgumentException("Not a roman integer");
        }
    }
}
