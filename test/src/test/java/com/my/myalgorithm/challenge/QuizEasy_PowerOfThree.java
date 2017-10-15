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
 * Given an integer, write a function to determine if it is a power of three.
 * <p/>
 * Example:
 * <pre>
 *     27 is a power of 3.
 * </pre>
 * Reference:
 * <ul>
 * <li>https://leetcode.com/problems/power-of-three/description/</li>
 * </ul>
 */
public class QuizEasy_PowerOfThree {

    @Test
    public void test_Sol1() throws Exception {
        Assert.assertEquals(true, isPowerOfThree_Sol1(1));
        Assert.assertEquals(true, isPowerOfThree_Sol1(3));
        Assert.assertEquals(true, isPowerOfThree_Sol1(9));
        Assert.assertEquals(true, isPowerOfThree_Sol1(27));
        Assert.assertEquals(true, isPowerOfThree_Sol1(81));

        Assert.assertEquals(false, isPowerOfThree_Sol1(45));
    }

    @Test
    public void test_Sol2() throws Exception {
        Assert.assertEquals(true, isPowerOfThree_Sol2(1));
        Assert.assertEquals(true, isPowerOfThree_Sol2(3));
        Assert.assertEquals(true, isPowerOfThree_Sol2(9));
        Assert.assertEquals(true, isPowerOfThree_Sol2(27));
        Assert.assertEquals(true, isPowerOfThree_Sol2(81));

        Assert.assertEquals(false, isPowerOfThree_Sol2(45));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #2 ////////////////////////////////////////////////////////////

    /**
     * Beats 87.64% on LeetCode.
     */
    private boolean isPowerOfThree_Sol2(int n) {
        // 1162261467 is 3^19, 3^20 is bigger than int
        return (n > 0 && 1162261467 % n == 0);
    }

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 62.24% on LeetCode.
     */
    private boolean isPowerOfThree_Sol1(int n) {
        if (n <= 0) return false;

        int divisor = 3;
        int num = n;
        while (num >= divisor) {
            if (num % divisor == 0) {
                num /= divisor;
            } else {
                return false;
            }
        }

        return num == 1 || num % divisor == 0;
    }
}
