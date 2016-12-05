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

import java.util.Locale;

/**
 * Define amazing number as: its value is less than or equal to its index. Given
 * a circular array, find the starting position, such that the total number
 * of amazing numbers in the array is maximized.
 * <br/>
 * If there are multiple positions, return the smallest one.
 * <br/>
 * Should get a solution with time complexity less than O(N^2).
 * <pre>
 *
 * Example 1: [0, 1, 2, 3]
 * Output: When starting point at position 0, all the elements in the array are
 * equal to its index. So all the numbers are amazing number.
 *
 * Example 2: [1, 0, 0]
 * Output: When starting point at position 1, the array becomes 0, 0, 1. All
 * the elements are amazing number.
 * </pre>
 * <br/>
 * Reference:
 * <br/>
 * https://www.careercup.com/question?id=6018738030641152
 */
public class Quiz_FindAmazingNumbers {

    @Test
    public void answer1() throws Exception {
        Assert.assertTrue(1 == findStartPosition(new int[]{1, 0, 0}));
        Assert.assertTrue(2 == findStartPosition(new int[]{2, 1, 0, 0}));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private int mIterations = 0;

    private int findStartPosition(int[] circularArray) {
        int start = 0;
        int maxAmazingSum = 0;

        // DEBUG: iterations.
        mIterations = 0;

        for (int i = 0; i < circularArray.length; ++i) {

            int amazingSum = 0;
            for (int j = 0; j < circularArray.length; ++j) {

                // DEBUG: iterations.
                ++mIterations;

                int k = j + i;
                if (k >= circularArray.length) {
                    k -= circularArray.length;
                }
                // Add up the amazing number.
                if (circularArray[k] <= j) {
                    amazingSum += circularArray[k];
                }
            }

            if (amazingSum > maxAmazingSum) {
                maxAmazingSum = amazingSum;
                start = i;
            }
        }

        System.out.println(String.format(Locale.ENGLISH,
                                         "Total %d iterations.",
                                         mIterations));

        return start;
    }
}
