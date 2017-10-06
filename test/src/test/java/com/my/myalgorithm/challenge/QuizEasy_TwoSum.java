package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target. You may assume that each input would have exactly
 * one solution, and you may not use the same element twice.
 * <p>
 * For example,
 * <pre>
 *   Given nums = [2, 7, 11, 15], target = 9,
 *
 *   Because nums[0] + nums[1] = 2 + 7 = 9,
 *   return [0, 1].
 * </pre>
 * <p>
 * LeetCode: https://leetcode.com/problems/two-sum/description/
 */
public class QuizEasy_TwoSum {

    @Test
    public void test_sol3() {
        Assert.assertArrayEquals(new int[]{1, 2},
                                 twoSum_sol3(new int[]{3, 2, 4}, 6));
        Assert.assertArrayEquals(new int[]{0, 1},
                                 twoSum_sol3(new int[]{3, 3}, 6));
    }

    @Test
    public void test_sol2() {
        Assert.assertArrayEquals(new int[]{1, 2},
                                 twoSum_sol2(new int[]{3, 2, 4}, 6));
        Assert.assertArrayEquals(new int[]{0, 1},
                                 twoSum_sol2(new int[]{3, 3}, 6));
    }

    @Test
    public void test_sol1() {
        Assert.assertArrayEquals(new int[]{1, 2},
                                 twoSum_sol1(new int[]{3, 2, 4}, 6));
        // Won't pass.
        Assert.assertArrayEquals(new int[]{0, 1},
                                 twoSum_sol1(new int[]{3, 3}, 6));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #3 ////////////////////////////////////////////////////////////

    /**
     * Beats 73.89% on Leetcode.
     */
    private int[] twoSum_sol3(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        // Time complexity: O(n).
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int other = target - num;

            // Time complexity: O(1).
            if (map.containsKey(other)) {
                return new int[] {map.get(other), i};
            }
            map.put(nums[i], i);
        }

        return new int[0];
    }

    // Solution #2 ////////////////////////////////////////////////////////////

    /**
     * Beats 34.34% on Leetcode.
     */
    private int[] twoSum_sol2(int[] nums, int target) {
        // Time complexity: O(n).
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, size = nums.length; i < size; ++i) {
            int num = nums[i];
            if (map.containsKey(num)) continue;

            int other = target - num;
            int j = searchFromEnd(nums, other, i);
            if (j >= 0 && i != j) {
                return new int[] {i, j};
            }
        }

        return new int[0];
    }

    private int searchFromEnd(int[] nums, int target, int stopPosition) {
        for (int i = nums.length - 1; i >= 0; --i) {
            if (i <= stopPosition) return -1;
            if (nums[i] == target) {
                return i;
            }
        }

        return -1;
    }

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Cannot pass given [3, 3] on Leetcode.
     */
    private int[] twoSum_sol1(int[] nums, int target) {
        // Copy the input nums and prepare the indices map.
        Map<Integer, Integer> indicesMap = new HashMap<>();
        List<Integer> copyNums = new ArrayList<>();
        for (int i = 0, size = nums.length; i < size; ++i) {
            int num = nums[i];
            copyNums.add(num);
            indicesMap.put(num, i);
        }

        // Sort nums.
        Collections.sort(copyNums);

        // Time complexity: n * log(n)
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, size = copyNums.size(); i < size; ++i) {
            int num = copyNums.get(i);
            if (map.containsKey(num)) continue;

            int other = target - num;
            int j = Collections.binarySearch(copyNums, other);
            if (j >= 0 && i != j) {
                map.put(num, indicesMap.get(num));
                map.put(other, indicesMap.get(other));
            }
        }

        if (map.size() > 0) {
            int i = 0;
            int[] res = new int[map.size()];
            for (Integer index : map.values()) {
                res[i++] = index;
            }

            return res;
        } else {
            return new int[0];
        }
    }
}
