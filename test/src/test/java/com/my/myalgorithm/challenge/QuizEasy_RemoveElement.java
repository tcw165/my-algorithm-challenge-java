package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Given an array and a value, remove all instances of that value in place and
 * return the new length. Do not allocate extra space for another array, you
 * must do this in place with constant memory. The order of elements can be
 * changed. It doesn't matter what you leave beyond the new length.
 * <p/>
 * For example,
 * <pre>
 *   Given input array nums = [3,2,2,3], val = 3
 *   Your function should return length = 2, with the first two elements of nums
 *   being 2.
 * </pre>
 * References:
 * <ul>
 *     <li>LeetCode: https://leetcode.com/problems/remove-element/description/</li>
 * </ul>
 */
public class QuizEasy_RemoveElement {

    @Test
    public void test_Sol2() {
        Assert.assertEquals(0, removeElement_Sol2(new int[]{4, 4}, 4));
        Assert.assertEquals(1, removeElement_Sol2(new int[]{4, 5}, 4));
        Assert.assertEquals(2, removeElement_Sol2(new int[]{2, 3, 3}, 2));
        Assert.assertEquals(2, removeElement_Sol2(new int[]{3, 2, 3, 3, 3, 2, 3}, 3));
        Assert.assertEquals(4, removeElement_Sol2(new int[]{3 , 2 , 3 , 3 , 3 , 2 , 3 , 4, 5}, 3));
    }

    @Test
    public void test_Sol1() {
        Assert.assertEquals(0, removeElement_Sol1(new int[]{4, 4}, 4));
        Assert.assertEquals(1, removeElement_Sol1(new int[]{4, 5}, 4));
        Assert.assertEquals(2, removeElement_Sol1(new int[]{2, 3, 3}, 2));
        Assert.assertEquals(2, removeElement_Sol1(new int[]{3, 2, 3, 3, 3, 2, 3}, 3));
        Assert.assertEquals(4, removeElement_Sol1(new int[]{3 , 2 , 3 , 3 , 3 , 2 , 3 , 4, 5}, 3));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #2 ////////////////////////////////////////////////////////////

    /**
     * Beats 52.89% on LeetCode.
     */
    private int removeElement_Sol2(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        // Sort array.
        // Time complexity: O(n * log(n))
        Arrays.sort(nums);

        // Given array:
        // [ 3 , 2 , 3 , 3 , 3 , 2 , 3 , 4 , 5 ]
        // Remove 3.
        //
        // Sort the array,
        // [ 2 , 2 , 3 , 3 , 3 , 3 , 3 , 4 , 5 ]
        //
        // [ 2 , 2 , 3 , 3 , 3 , 3 , 3 , 4 , 5 ]
        //   i
        //       j
        //
        // [ 2 , 2 , 3 , 3 , 3 , 3 , 3 , 4 , 5 ]
        //       i
        //           j
        //
        // [ 2 , 2 , 3 , 3 , 3 , 3 , 3 , 4 , 5 ]
        //           i
        //               j
        //
        // [ 2 , 2 , 3 , 3 , 3 , 3 , 3 , 4 , 5 ]
        //           i
        //                               j
        //
        // [ 2 , 2 ,(4), 3 , 3 , 3 , 3 , 4 , 5 ]
        //           i
        //                               j
        //
        // [ 2 , 2 , 4 , 3 , 3 , 3 , 3 , 4 , 5 ]
        //               i
        //                               j
        //
        // [ 2 , 2 , 4 ,(5), 3 , 3 , 3 , 4 , 5 ]
        //               i
        //                                   j
        //
        // [ 2 , 2 , 4 , 5 , 3 , 3 , 3 , 4 , 5 ]
        //                   i
        //                                   j

        // Find the first element matching the value.
        // Time complexity: O(n).
        int i = Arrays.binarySearch(nums, val);
        while (i >= 0) {
            int prev = i - 1;
            if (prev >= 0 && nums[prev] == val) {
                --i;
            } else {
                break;
            }
        }
        // No val in the array.
        if (i < 0) return nums.length;

        // Take i as the slow runner and j as the fast runner to move the
        // elements forward.
        // Time complexity: O(n).
        for (int j = i + 1; j < nums.length; ++j) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }

        return i;
    }

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 22% on LeetCode.
     */
    private int removeElement_Sol1(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        // Given array:
        // [ 3 , 2 , 3 , 3 , 3 , 2 , 3 , 4 , 5 ]
        // Remove 3.
        //
        // [ 3 , 2 , 3 , 3 , 3 , 2 , 3 , 4 , 5 ]
        //   i
        //   j
        //
        // [ 3 , 2 , 3 , 3 , 3 , 2 , 3 , 4 , 5 ]
        //   i
        //       j
        //
        // [(2), 2 , 3 , 3 , 3 , 2 , 3 , 4 , 5 ]
        //       i
        //       j
        //
        // [ 2 , 2 , 3 , 3 , 3 , 2 , 3 , 4 , 5 ]
        //       i
        //           j
        //
        // skip 3 by advancing pointer j until j is pointing to value not equal
        // to 3.
        //
        // [ 2 , 2 , 3 , 3 , 3 , 2 , 3 , 4 , 5 ]
        //       i
        //                       j
        //
        // [ 2 ,(3), 3 , 3 , 3 , 2 , 3 , 4 , 5 ]
        //           i
        //                           j
        //
        // [ 2 , 3 , 3 , 3 , 3 , 2 , 3 , 4 , 5 ]
        //           i
        //                               j
        //
        // [ 2 , 3 ,(4), 3 , 3 , 2 , 3 , 4 , 5 ]
        //               i
        //                               j
        //
        // [ 2 , 3 , 4 , 3 , 3 , 2 , 3 , 4 , 5 ]
        //               i
        //                                   j
        //
        // [ 2 , 3 , 4 ,(5), 3 , 2 , 3 , 4 , 5 ]
        //                   i
        //                                   j

        // Take i as the slow runner and j as the fast runner to move the
        // elements forward.
        // Time complexity: O(n).
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }

        return i;
    }
}
