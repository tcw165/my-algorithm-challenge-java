package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given two binary strings, return their sum (also a binary string).
 * <p/>
 * For example,
 * <pre>
 *   a = "11"
 *   b = "1"
 *   Return "100".
 * </pre>
 * References:
 * <br/>
 * <ul>
 *     <li>LeetCode: https://leetcode.com/problems/add-binary/description/</li>
 * </ul>
 */
public class QuizEasy_AddTwoBitStrings {

    @Test
    public void test() {
        Assert.assertEquals("0", addBinarySolution2("0", "0"));
        Assert.assertEquals("1", addBinarySolution2("1", "0"));
        Assert.assertEquals("1", addBinarySolution2("0", "1"));
        Assert.assertEquals("10", addBinarySolution2("1", "1"));
        Assert.assertEquals("110", addBinarySolution2("11", "11"));
        Assert.assertEquals("101", addBinarySolution2("11", "10"));
        Assert.assertEquals("100", addBinarySolution2("11", "1"));
        Assert.assertEquals("100", addBinarySolution2("11", "1"));
        Assert.assertEquals("10000", addBinarySolution2("1111", "1"));
        Assert.assertEquals("1001", addBinarySolution2("1000", "1"));
        Assert.assertEquals("110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000",
                            addBinarySolution2("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
                                               "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #2 ////////////////////////////////////////////////////////////

    /**
     * Beats 89% on Leetcode.
     */
    private String addBinarySolution2(String a, String b) {
        StringBuilder sb = new StringBuilder(Math.max(a.length(), b.length()) + 1);
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';

            sb.append(sum % 2);
            carry = sum / 2;
        }

        if (carry != 0) sb.append(carry);

        return sb.reverse().toString();
    }

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 27% on Leetcode.
     */
    private String addBinarySolution1(String a, String b) {
        int size = Math.max(a.length(), b.length());
        StringBuilder builder = new StringBuilder(size);

        // Because converting the binary string to either integer or long might
        // overflow, I rather not to convert them for the calculation.
        char carry = '0';
        for (int i = 0; i < size; ++i) {
            // Get the least significant index, because we get the digit from
            // the right most char of the string.
            int lsIndexA = a.length() - i - 1;
            int lsIndexB = b.length() - i - 1;
            char digitA = lsIndexA >= 0 ? a.charAt(lsIndexA) : '0';
            char digitB = lsIndexB >= 0 ? b.charAt(lsIndexB) : '0';

            AddResult res = addCharDigit(digitA, digitB, carry);

            builder.append(res.digit);
            carry = res.carry;

            if (i == size - 1 && carry == '1') {
                builder.append(res.carry);
            }
        }

        return builder.reverse().toString();
    }

    /**
     * Add two digit, and return the new digit and carry.
     *
     * @param digitA '1' or '0'
     * @param digitB '1' or '0'
     * @return [digit, carry] array.
     */
    private AddResult addCharDigit(char digitA, char digitB, char carry) {
        //  A | B | carry
        // ---+---+------
        //  0 | 0 | 0      => 0
        //  0 | 1 | 0      => 1
        //  0 | 0 | 1      => 1
        //  0 | 1 | 1      => 10
        //  1 | 0 | 0      => 1
        //  1 | 0 | 1      => 10
        //  1 | 1 | 0      => 10
        //  1 | 1 | 1      => 11
        int sum = (digitA - '0') + (digitB - '0') + (carry - '0');

        return new AddResult(sum % 2 == 0 ? '0' : '1',
                             sum / 2 == 0 ? '0' : '1');
    }

    private void print(float[][] points) {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class AddResult {
        final char digit;
        final char carry;

        AddResult(char digit, char carry) {
            this.digit = digit;
            this.carry = carry;
        }
    }
}
