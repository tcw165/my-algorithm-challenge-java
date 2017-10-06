// Copyright (c) 2017-present boyw165
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
import org.junit.experimental.max.MaxCore;

/**
 * Given an integer array (index from 0 to n-1, where n is the size of this
 * array),find the longest increasing continuous subsequence (from left to
 * right) in this array.
 * <br/>
 * For example:
 * <pre>
 * [2 1] => 1
 * [1 2 10 3] => 3
 * [1 2 2 3 4] => 4
 * [5 3 1 2] => 2
 * </pre>
 * Reference:
 * <br/>
 * https://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/73088
 */
public class QuizMedium_LongestIncreasingContinuousSubsequence {

    @Test
    public void answer() throws Exception {
        Assert.assertEquals(3, LICS(new int[]{1, 2, 5}));
        Assert.assertEquals(3, LICS(new int[]{1, 2, 10, 3}));
        Assert.assertEquals(4, LICS(new int[]{1, 2, 2, 3, 4}));
        Assert.assertEquals(2, LICS(new int[]{5, 3, 1, 2}));
        Assert.assertEquals(5, LICS(new int[]{22, 1, 10, 11, 14, 15, 2, 2, 3,
                                              3, 3, 12, 13, 13, 8, 7, 21}));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    int LICS(int[] array) {
        if (array == null || array.length < 1) return 0;
        if (array.length == 1) return 1;

        // Local maximum.
        int lMax = 1;
        // Global maximum.
        int gMax = 1;
        for (int i = 0; i < array.length; ++i) {
            int prev = Math.max(0, i - 1);
            if (array[i] > array[prev]) {
                ++lMax;
            } else if (array[i] < array[prev]) {
                // If the [i] is less than the [end], advance the start value
                // and reset the duplicate.
                lMax = 1;
            }
            gMax = Math.max(gMax, lMax);
        }

        return gMax;
    }
}
