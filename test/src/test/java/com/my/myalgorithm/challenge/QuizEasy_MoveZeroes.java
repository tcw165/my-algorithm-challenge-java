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
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * <p/>
 * Example:
 * <pre>
 *     given nums = [0, 1, 0, 3, 12],
 *     after calling your function, nums should be [1, 3, 12, 0, 0].
 * </pre>
 * Reference:
 * <ul>
 * <li>https://leetcode.com/problems/move-zeroes/description/</li>
 * </ul>
 */
public class QuizEasy_MoveZeroes {

    @Test
    public void test() throws Exception {
        Assert.assertArrayEquals(new int[]{1, 0},
                                 moveZeroes(new int[]{0, 1}));
        Assert.assertArrayEquals(new int[]{2, 1},
                                 moveZeroes(new int[]{2, 1}));
        Assert.assertArrayEquals(new int[]{1, 3, 12, 0, 0},
                                 moveZeroes(new int[]{0, 1, 0, 3, 12}));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 22% on LeetCode.
     */
    private int[] moveZeroes(int[] nums) {
        if (nums == null) return null;

        // i is the slow runner, j is the fast runner.
        //
        // For example:
        //  [  0 ,  1 ,  0 ,  3 , 12 ]
        //     i
        //     j
        //
        //  [  0 ,  1 ,  0 ,  3 , 12 ]
        //     i
        //          j
        //
        //  [ {1}, {0},  0 ,  3 , 12 ]
        //     i
        //          j
        //
        //  [  1 ,  0 ,  0 ,  3 , 12 ]
        //          i
        //               j
        //
        //  [  1 ,  0 ,  0 ,  3 , 12 ]
        //          i
        //                    j
        //
        //  [  1 , {3},  0 , {0}, 12 ]
        //          i
        //                    j
        //
        //  [  1 ,  3 ,  0 ,  0 , 12 ]
        //               i
        //                         j
        //
        //  [  1 ,  3 ,{12},  0 , {0}]
        //               i
        //                         j
        //
        //  [  1 ,  3 , 12 ,  0 ,  0 ]
        //                    i
        //                         j
        //
        // Time complexity: O(n).
        int i = 0;
        for (int j = 0, size = nums.length; j < size; ++j) {
            if (nums[j] != 0) {
                int num = nums[i];
                nums[i++] = nums[j];
                nums[j] = num;
            }
        }

        return nums;
    }
}
