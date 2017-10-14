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
 * Calculate the sum of two integers a and b, but you are not allowed to use
 * the operator + and -.
 * <p/>
 * Example:
 * <pre>
 *     Given a = 1 and b = 2, return 3.
 * </pre>
 * Reference:
 * <ul>
 * <li>https://leetcode.com/problems/sum-of-two-integers/description/</li>
 * <li>https://leetcode.com/problems/sum-of-two-integers/discuss/</li>
 * <li>https://www.cs.cornell.edu/~tomf/notes/cps104/twoscomp.html</li>
 * </ul>
 */
public class QuizEasy_SumOfTwoIntegers {

    @Test
    public void test() throws Exception {
        Assert.assertEquals(3, getSum(1, 2));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        // Suppose we're working with 8 bit quantities, sum of 3 plus 7:
        //
        //          carry =      111
        //              3 = 0000 0011
        //              7 = 0000 0111
        //
        // Use AND operator to find out what bits producing carry bits,
        //
        //              3 = 0000 0011
        //          AND 7 = 0000 0111
        //     carry bits = 0000 0011
        //
        // Use XOR operator to find the binary addition without carries,
        //
        //             3 = 0000 0011
        //         XOR 7 = 0000 0111
        //  add no carry = 0000 0100

        int sum = a;
        int carry = b;
        while (carry != 0) {
            int newCarry = (sum & carry) << 1;

            sum = sum ^ carry;
            carry = newCarry;
        }

        return sum;
    }

    /**
     * To get the two's complement negative notation of an integer, you write
     * out the number in binary. You then invert the digits, and add one to
     * the result.
     * <p/>
     * For example:
     * <pre>
     *      28 = 0001 1100
     *     ~28 = 1110 0011
     * ~28 + 1 = 1110 0100 <- This is -28.
     * </pre>
     * How it works?
     * <br/>
     * Check out the link here, https://www.cs.cornell.edu/~tomf/notes/cps104/twoscomp.html
     */
    private int negate(int x) {
        return ~x + 1;
    }
}
