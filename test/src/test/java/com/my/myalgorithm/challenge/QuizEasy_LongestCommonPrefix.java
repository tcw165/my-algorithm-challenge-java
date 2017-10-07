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
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 * Reference:
 * <p/>
 * LeetCode: https://leetcode.com/problems/longest-common-prefix/description/
 */
public class QuizEasy_LongestCommonPrefix {

    @Test
    public void test() throws Exception {
        Assert.assertEquals("", longestCommonPrefix(new String[0]));
        Assert.assertEquals("123,,,", longestCommonPrefix(new String[]{
            "123,,,,,abc",
            "123,,,def",
            "123,,,,,,,hijk"
        }));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 37% on LeetCode.
     */
    private String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // Find out the minimum size of the given string.
        // Time complexity: O(n), where n is the length of the array.
        int minSize = Integer.MAX_VALUE;
        for (String str : strs) {
            minSize = Math.min(minSize, str.length());
        }

        final StringBuilder builder = new StringBuilder(minSize);

        // Time complexity: O(n * m), where n is the length of the array, the
        // m is the average length of the strings.
        int end = 0;
        while (end < minSize) {
            boolean isTheSame = true;
            char c = strs[0].charAt(end);
            for (int i = 1, size = strs.length; i < size; ++i) {
                if (strs[i].charAt(end) != c) {
                    isTheSame = false;
                    break;
                }
            }

            if (isTheSame) {
                builder.append(c);
                ++end;
            } else {
                break;
            }
        }

        return builder.toString();
    }
}
