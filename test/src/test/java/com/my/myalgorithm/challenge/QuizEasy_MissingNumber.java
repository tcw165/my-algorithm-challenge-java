//  Copyright Oct 2017-present boyw165@gmail.com
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.

package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * <br/>
 * Your algorithm should run in linear runtime complexity. Could you implement
 * it using only constant extra space complexity?
 * <p/>
 * For example:
 * <pre>
 *     Given nums = [0, 1, 3] return 2.
 * </pre>
 * References:
 * <ul>
 * <li>https://leetcode.com/problems/missing-number/description/</li>
 * </ul>
 */
public class QuizEasy_MissingNumber {

    @Test
    public void test() {
        Assert.assertEquals(1, missingNumber(new int[]{0}));
        Assert.assertEquals(0, missingNumber(new int[]{1}));
        Assert.assertEquals(2, missingNumber(new int[]{0, 1, 3}));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 42% on LeetCode.
     */
    private int missingNumber(int[] nums) {
        if (nums == null) return 0;

        // Sort the array.
        Arrays.sort(nums);

        int current = 0;
        for (int num : nums) {
            if (current != num) {
                break;
            } else {
                ++current;
            }
        }

        return current;
    }
}
