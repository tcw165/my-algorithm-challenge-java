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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertTrue;

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
        assertTrue(1 == findStartPositionByBrutalForce(new int[]{1, 0, 0}));
        assertTrue(2 == findStartPositionByBrutalForce(new int[]{2, 1, 0, 0}));
        assertTrue(0 == findStartPositionByBrutalForce(new int[]{4, 2, 8, 2, 4, 5, 3}));

        assertTrue(1 == findStartPositionByInterval(new int[]{1, 0, 0}));
        assertTrue(2 == findStartPositionByInterval(new int[]{2, 1, 0, 0}));
        assertTrue(0 == findStartPositionByInterval(new int[]{4, 2, 8, 2, 4, 5, 3}));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private int mIterations = 0;

    /**
     * Brutal force solution.
     */
    private int findStartPositionByBrutalForce(int[] circularArray) {
        int start = 0;
        int maxAmazingNum = 0;

        // DEBUG: iterations.
        mIterations = 0;

        for (int i = 0; i < circularArray.length; ++i) {

            int amazingNum = 0;
            for (int j = 0; j < circularArray.length; ++j) {

                // DEBUG: iterations.
                ++mIterations;

                int k = j + i;
                // Add up the amazing number.
                if (circularArray[k % circularArray.length] <= j) {
                    ++amazingNum;
                }
            }

            if (amazingNum > maxAmazingNum) {
                maxAmazingNum = amazingNum;
                start = i;
            }
        }

        System.out.println(String.format(Locale.ENGLISH,
                                         "Brutal force solution, total %d iterations.",
                                         mIterations));

        return start;
    }

    /**
     * Solution of finding the interval that maximize the amount of valid
     * amazing numbers.
     * <pre>
     *
     * Example: arr = [4,2,8,2,4,5,3]
     *
     * 1) if arr[0] can be used, the start index should be between [1..3].
     *   arr   = [4,2,8,2,4,5,3]
     *            ^
     *   index =  6,0,1,2,3,4,5
     *                 to
     *   index =  4,5,6,0,1,2,3
     *
     * 2) if arr[1] can be used, the start index should be between [2..6].
     *   arr   = [4,2,8,2,4,5,3]
     *              ^
     *   index =  5,6,0,1,2,3,4
     *                 to
     *   index =  1,2,3,4,5,6,0
     *
     * 3) arr[2] can NOT be used because it's greater than the array length.
     *
     * 4) if arr[3] can be used, the start index should be between [4..8].
     *   arr   = [4,2,8,2,4,5,3]
     *                  ^
     *   index =  3,4,5,6,0,1,2
     *                 to
     *   index =  6,0,1,2,3,4,5
     *
     * 5) if arr[4] can be used, the start index should be between [4..8].
     *   arr   = [4,2,8,2,4,5,3]
     *                    ^
     *   index =  2,3,4,5,6,0,1
     *                 to
     *   index =  0,1,2,3,4,5,6
     *
     * 6) if arr[5] can be used, the start index should be between [6..7].
     *   arr   = [4,2,8,2,4,5,3]
     *                      ^
     *   index =  1,2,3,4,5,6,0
     *                 to
     *   index =  0,1,2,3,4,5,6
     *
     * 7) if arr[6] can be used, the start index should be between [0..4].
     *   arr   = [4,2,8,2,4,5,3]
     *                        ^
     *   index =  0,1,2,3,4,5,6
     *                 to
     *   index =  4,5,6,0,1,2,3
     *
     * The problem becomes: "what is the interval that maximize the possibility
     * to have more amazing numbers?"
     *
     * index | 0 | 1 | 2 | 3 | 4 | 5 | 6
     * ------+---+---+---+---+---+---+---
     *       |   |###########|   |   |     if [0] can be used...
     * ------+---+---+---+---+---+---+---
     *       |   |   |###################  if [1] can be used...
     * ------+---+---+---+---+---+---+---
     *       |#######|   |   |   |   |     if [3] can be used...
     * ------+---+---+---+---+---+---+---
     *       |   |   |   |   |###########
     * ------+---+---+---+---+---+---+---
     *       |###|   |   |   |   |   |     if [4] can be used...
     * ------+---+---+---+---+---+---+---
     *       |   |   |   |   |   |#######
     * ------+---+---+---+---+---+---+---
     *       |###|   |   |   |   |   |     if [5] can be used...
     * ------+---+---+---+---+---+---+---
     *       |   |   |   |   |   |   |###
     * ------+---+---+---+---+---+---+---
     *       |###############|   |   |     if [6] can be used...
     * ------+---+---+---+---+---+---+---
     *
     * We got 9 intervals: [1..3], [2..6], [0..1], [4..6], [0..0], [5..6],
     *                     [0..0], [6..6], [0..3]
     *
     * We could solve it by having an array storing the number of all the begin
     * and end numbers.
     *
     *    Begin: [4,  1,  1, 0,  1, 1, 1]
     *    + end: [0, -2, -1, 0, -2, 0, 0]
     *   ---------------------------------
     *           [4, -1,  0, 0, -1, 1, 1]
     *            ^   ^   ^  ^   ^  ^  ^
     *            |   |   |  |   |  |  |
     *            |   |   |  |   |  |  +---- 4 left
     *            |   |   |  |   |  +------- 3 left
     *            |   |   |  |   +---------- 2 left
     *            |   |   |  +-------------- 3 left
     *            |   |   +----------------- 3 left
     *            |   +--------------------- 3 left
     *            +------------------------- 4 left <-- The answer!
     * </pre>
     */
    private int findStartPositionByInterval(int[] circularArray) {
        // DEBUG: iterations.
        mIterations = 0;

        // Find the interval for each element that can be used.
        // Complexity: O(n).
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < circularArray.length; ++i) {
            // DEBUG: iterations.
            ++mIterations;

            if (circularArray[i] >= circularArray.length) continue;

            int start = i + 1;
            int till = i + circularArray.length - circularArray[i];

            // We break the interval spanning over the array into two intervals
            // so that it's more easy to calculate the maximum overlapped
            // interval.
            if (start < circularArray.length &&
                till < circularArray.length) {
                intervals.add(new Interval(start, till));
            } else {
                if (start >= circularArray.length) {
                    intervals.add(new Interval(start % circularArray.length,
                                               till % circularArray.length));
                } else {
                    // Break it into two intervals.
                    intervals.add(new Interval(start, circularArray.length - 1));
                    intervals.add(new Interval(0, till % circularArray.length));
                }
            }
//            System.out.println(String.format(Locale.ENGLISH, "[%d..%d]", start, till));
        }

        // Use begin record and till record to get the maximum interval.
        // Complexity: O(n).
        int[] intervalRecord = new int[circularArray.length];
        for (Interval interval : intervals) {
            // DEBUG: iterations.
            ++mIterations;

            ++intervalRecord[interval.begin];
            if (interval.till + 1 < circularArray.length) {
                --intervalRecord[interval.till + 1];
            }
        }
        // Complexity: O(n).
        int start = 0;
        int max = 0;
        for (int i = 0, sum = 0; i < intervalRecord.length; ++i) {
            // DEBUG: iterations.
            ++mIterations;

            sum += intervalRecord[i];
            if (sum > max) {
                max = sum;
                start = i;
            }
        }

        // Time complexity:
        // O(n) +
        System.out.println(String.format(Locale.ENGLISH,
                                         "Interval solution, total %d iterations.",
                                         mIterations));

        return start;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class Interval {
        int begin;
        int till;

        Interval(int begin,
                 int till) {
            this.begin = begin;
            this.till = till;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Interval interval = (Interval) o;

            if (begin != interval.begin) return false;
            return till == interval.till;
        }

        @Override
        public int hashCode() {
            int result = begin;
            result = 31 * result + till;
            return result;
        }
    }
}
