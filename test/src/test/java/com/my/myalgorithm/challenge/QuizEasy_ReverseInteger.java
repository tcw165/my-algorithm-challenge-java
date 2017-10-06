package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Reverse digits of an integer.
 * <p>
 * For example,
 * <pre>
 *   x = 123, return 321
 *   x = -123, return -321
 * </pre>
 * <p>
 * LeetCode: https://leetcode.com/problems/reverse-integer/description/
 */
public class QuizEasy_ReverseInteger {

    @Test
    public void test() {
        Assert.assertEquals(0, reverse(0));
        Assert.assertEquals(111, reverse(111));
        Assert.assertEquals(321, reverse(123));
        Assert.assertEquals(-123, reverse(-321));
        Assert.assertEquals(7654321, reverse(1234567));
        Assert.assertEquals(0, reverse(1534236469));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 10% on Leetcode.
     */
    private int reverse(int x) {
        if (x == 0) return 0;

        long reverse = 0;
        long num = x;
        while (num != 0) {
            int digit = (int) (num % 10);
            num /= 10;

            reverse = reverse * 10 + digit;

            if (reverse > Integer.MAX_VALUE ||
                reverse < Integer.MIN_VALUE) {
                return 0;
            }
        }

        return (int) reverse;
    }

    private void print(float[][] points) {
    }
}
