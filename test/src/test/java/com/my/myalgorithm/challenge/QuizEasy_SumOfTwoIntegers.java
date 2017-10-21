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
    public void allPositives() throws Exception {
        Assert.assertEquals(3, getSum(1, 2));
        Assert.assertEquals(9, getSum(5, 4));
    }

    @Test
    public void allNegatives() throws Exception {
        Assert.assertEquals(-4, getSum(-1, -3));
        Assert.assertEquals(-12, getSum(-5, -7));
    }

    @Test
    public void onePositiveAndOneNegative() throws Exception {
        Assert.assertEquals(4, getSum(-1, 5));
        Assert.assertEquals(145, getSum(152, -7));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        // Tips:
        // Use AND operator to find out what bits producing carry bits,
        //
        //              3 = 0011
        //          AND 7 = 0111
        //     carry bits = 0011
        //
        // Use XOR operator to find the binary addition without carries,
        //
        //             3 = 0011
        //         XOR 7 = 0111
        //  add no carry = 0100

        // Suppose we're working with 8 bit quantities, sum of 3 plus 7:
        // Goal:  0011
        //      + 0111
        //
        //
        //        0011         0011
        //    AND 0111     XOR 0111
        //    --------     --------
        //    <<1 0011         0100 ---. sum without carries
        //    --------                 |
        //        0110 ----------------+ carries are still present...
        //                             |
        //                             |
        // Goal:  0110 <---------------'
        //      + 0100
        //
        //        0110         0110
        //    AND 0100     XOR 0100
        //    --------     --------
        //    <<1 0100         0010 ---. sum without carries
        //    --------                 |
        //        1000 ----------------+ carries are still present...
        //                             |
        //                             |
        // Goal:  1000 <---------------'
        //      + 0010
        //
        //        1000         1000
        //    AND 0010     XOR 0010
        //    --------     --------
        //    <<1 0000         1010 ---> sum without carries
        //    --------
        //        0000 ----------------> NO carries anymore, so the answer is
        //                               the sum without carries.
        //
        // Ans:   1010

        int sumWithoutCarries = a ^ b;
        int carries = (a & b) << 1;
        while (carries != 0) {
            int oldSum = sumWithoutCarries;

            sumWithoutCarries = oldSum ^ carries;
            carries = (oldSum & carries) << 1;
        }

        return sumWithoutCarries;
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
