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

import android.support.v4.view.animation.FastOutLinearInInterpolator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * <p/>
 * <pre>
 * 123454321 is a palindrome.
 * 123054321 is not a palindrome.
 * </pre>
 * References:
 * <p/>
 * LeetCode: https://leetcode.com/problems/palindrome-number/description/
 * <p/>
 * Other: http://mathworld.wolfram.com/PalindromicNumber.html
 */
public class QuizEasy_PalindromeNumber {

    @Test
    public void test_sol1() throws Exception {
        Assert.assertEquals(true, isPalindrome_sol1(0));
        Assert.assertEquals(true, isPalindrome_sol1(1));
        Assert.assertEquals(true, isPalindrome_sol1(123454321));

        Assert.assertEquals(false, isPalindrome_sol1(123054321));
        Assert.assertEquals(false, isPalindrome_sol1(-1));
        Assert.assertEquals(false, isPalindrome_sol1(-11));
        Assert.assertEquals(false, isPalindrome_sol1(-121));
        Assert.assertEquals(false, isPalindrome_sol1(-2147447412));
    }

    @Test
    public void test_sol2() throws Exception {
        Assert.assertEquals(true, isPalindrome_sol2(0));
        Assert.assertEquals(true, isPalindrome_sol2(1));
        Assert.assertEquals(true, isPalindrome_sol2(123454321));

        Assert.assertEquals(false, isPalindrome_sol2(123054321));
        Assert.assertEquals(false, isPalindrome_sol2(-1));
        Assert.assertEquals(false, isPalindrome_sol2(-11));
        Assert.assertEquals(false, isPalindrome_sol2(-121));
        Assert.assertEquals(false, isPalindrome_sol2(-2147447412));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #2 ////////////////////////////////////////////////////////////

    /**
     * Beats 35.77% on LeetCode.
     */
    private boolean isPalindrome_sol2(int x) {
        if (x < 0) return false;

        // Time complexity: O(n)
        int reverse = 0;
        int num = x;
        while (num != 0) {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }

        return x == reverse;
    }

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats ?% on LeetCode.
     */
    private boolean isPalindrome_sol1(int x) {
        if (x < 0) return false;

        List<Integer> digits = new ArrayList<>();

        // Time complexity: O(n)
        int num = x;
        while (num != 0) {
            digits.add(num % 10);
            num /= 10;
        }

        // Time complexity: O(n)
        int i = 0;
        int j = digits.size() - 1;
        while (i < j) {
            if (!digits.get(i).equals(digits.get(j))) {
                return false;
            }

            ++i;
            --j;
        }

        return true;
    }
}
