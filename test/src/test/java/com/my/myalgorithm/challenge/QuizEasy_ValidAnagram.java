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
 * Given two strings s and t, write a function to determine if t is an anagram
 * of s.
 * <p/>
 * Example:
 * <pre>
 *     s = "anagram", t = "nagaram", return true.
 *     s = "rat", t = "car", return false.
 * </pre>
 * Reference:
 * <ul>
 * <li>https://leetcode.com/problems/valid-anagram/description/</li>
 * </ul>
 */
public class QuizEasy_ValidAnagram {

    @Test
    public void test() throws Exception {
        Assert.assertEquals(true, isAnagram("anagram", "nagaram"));

        Assert.assertEquals(false, isAnagram("rat", "car"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    /**
     * Beats 45% on LeetCode.
     */
    private boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;

        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();

        if (ss.length == tt.length) {
            Arrays.sort(ss);
            Arrays.sort(tt);

            for (int i = 0, size = ss.length; i < size; ++i) {
                if (ss[i] != tt[i]) return false;
            }

            return true;
        } else {
            return false;
        }
    }
}
