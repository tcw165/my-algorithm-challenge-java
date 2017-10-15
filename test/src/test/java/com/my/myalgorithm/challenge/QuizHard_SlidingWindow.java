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
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position.
 * <p/>
 * For example,
 * <pre>
 *   Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 *   Window position                Max
 *   -----------------------------------
 *   [1  3  -1] -3  5  3  6  7       3
 *    1 [3  -1  -3] 5  3  6  7       3
 *    1  3 [-1  -3  5] 3  6  7       5
 *    1  3  -1 [-3  5  3] 6  7       5
 *    1  3  -1  -3 [5  3  6] 7       6
 *    1  3  -1  -3  5 [3  6  7]      7
 *
 *   Therefore, return the max sliding window as [3,3,5,5,6,7].
 * </pre>
 * <p/>
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for
 * non-empty array.
 * <p/>
 * References:
 * <ul>
 * <li>https://leetcode.com/problems/min-stack/description/</li>
 * </ul>
 */
public class QuizHard_SlidingWindow {

    @Test
    public void test() {
        Assert.assertArrayEquals(
            new int[]{3, 3, 5, 5, 6, 7},
            maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 42% on LeetCode.
     */
    private int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];

        // Because there is a window, we need a store storing the indices in
        // the descending order of the number the index pointing to, and the
        // store size is up to k.
        //
        // For example: Given k = 3
        //
        // nums = [  1,  3, -1, -3,  5,  3,  6,  7]
        //           i
        // idxs = [  0]
        // maxs =         [  0,  0,  0,  0,  0,  0]
        //
        //
        // nums = [  1,  3, -1, -3,  5,  3,  6,  7]
        //               i
        // idxs = [  1]
        // maxs =         [  0,  0,  0,  0,  0,  0]
        //
        //
        // nums = [  1,  3, -1, -3,  5,  3,  6,  7]
        //                   i
        // idxs = [  1,  2]
        // maxs =         [  3,  0,  0,  0,  0,  0]
        //
        //
        // nums = [  1,  3, -1, -3,  5,  3,  6,  7]
        //                       i
        // idxs = [  1,  2,  3]
        // maxs =         [  3,  3,  0,  0,  0,  0]
        //
        //
        // nums = [  1,  3, -1, -3,  5,  3,  6,  7]
        //                           i
        // idxs = [  4]
        // maxs =         [  3,  3,  5,  0,  0,  0]
        //
        //
        // nums = [  1,  3, -1, -3,  5,  3,  6,  7]
        //                               i
        // idxs = [  4,  5]
        // maxs =         [  3,  3,  5,  5,  0,  0]
        //
        //
        // nums = [  1,  3, -1, -3,  5,  3,  6,  7]
        //                                   i
        // idxs = [  6]
        // maxs =         [  3,  3,  5,  5,  6,  0]
        //
        //
        // nums = [  1,  3, -1, -3,  5,  3,  6,  7]
        //                                       i
        // idxs = [  7]
        // maxs =         [  3,  3,  5,  5,  6,  7]

        int size = nums.length;
        int[] maxs = new int[size - k + 1];
        int maxIndex = 0;

        // Deque is a queue also a stack and I use it to store the indices.
        // The size is up to k.
        Deque<Integer> indices = new ArrayDeque<>();
        for (int i = 0; i < size; ++i) {
            // Remove indices outside the window.
            while (!indices.isEmpty() &&
                   indices.peekFirst() < i - k + 1) {
                indices.pollFirst();
            }

            // Remove smaller numbers inside the window, always keep the
            // index of the biggest number, the index of the second biggest
            // number and so on.
            while (!indices.isEmpty() &&
                   nums[i] > nums[indices.peekLast()]) {
                indices.pollLast();
            }

            // Cache the index.
            indices.addLast(i);

            // Update the maximum list.
            if (i >= k - 1) {
                maxs[maxIndex++] = nums[indices.peek()];
            }
        }

        return maxs;
    }
}
