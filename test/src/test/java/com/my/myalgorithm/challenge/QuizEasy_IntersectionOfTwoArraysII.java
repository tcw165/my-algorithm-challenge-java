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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p/>
 * Example:
 * <pre>
 *     Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * </pre>
 * Reference:
 * <ul>
 * <li>https://leetcode.com/problems/intersection-of-two-arrays-ii/description/</li>
 * </ul>
 */
public class QuizEasy_IntersectionOfTwoArraysII {

    @Test
    public void test() throws Exception {
        testify(new int[]{2, 2},
                intersect(new int[]{1, 2, 2, 1},
                          new int[]{2, 2}));
        testify(new int[]{1, 2},
                intersect(new int[]{1, 2, 2, 1},
                          new int[]{0, 2, 0, 1}));
    }

    public void testify(int[] expect, int[] actual) {
        if (expect != null && actual != null) {
            if (expect.length != actual.length) {
                Assert.assertTrue(false);
            }

            Arrays.sort(expect);
            Arrays.sort(actual);
            for (int i = 0, size = expect.length; i < size; ++i) {
                int num = expect[i];
                Assert.assertEquals(num, actual[i]);
            }
        } else if (expect == null && actual == null) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    /**
     * Beats 60% on LeetCode.
     */
    private int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;

        final Map<Integer, Integer> map = new HashMap<>();
        int[] shorter;
        int[] longer;

        // Create a lookup table by putting every single numbers of shorter
        // array in.
        // Time complexity: O(n).
        if (nums1.length > nums2.length) {
            longer = nums1;
            shorter = nums2;
        } else {
            longer = nums2;
            shorter = nums1;
        }
        for (int num : shorter) {
            if (map.containsKey(num)) {
                int count = map.get(num);
                map.put(num, ++count);
            } else {
                map.put(num, 1);
            }
        }

        // Iterate through the longer array and look up if the number exists in
        // the lookup table. If yes, subtract the counter by 1 until it reaches
        // 0. Repeat it until the lookup table is empty or the intersection size
        // is equal to shorter array size.
        // Time complexity: O(m).
        int size = 0;
        int[] intersection = new int[shorter.length];
        for (int num : longer) {
            if (map.containsKey(num)) {
                int count = map.get(num);

                intersection[size++] = num;

                if (--count == 0) {
                    map.remove(num);
                } else {
                    map.put(num, count);
                }
            }

            if (map.isEmpty() ||
                size >= shorter.length) {
                break;
            }
        }

        int[] ret = new int[size];
        System.arraycopy(intersection, 0, ret, 0, size);

        return ret;
    }
}
