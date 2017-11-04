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
 * Given a string S, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 * <p/>
 * Example:
 * <pre>
 *     Given "aacecaaa", return "aaacecaaa".
 *     Given "abcd", return "dcbabcd".
 * </pre>
 * Reference:
 * <ul>
 * <li>https://leetcode.com/problems/shortest-palindrome/description/</li>
 * </ul>
 */
public class QuizHard_ShortestPlaindrome {

    @Test
    public void test_Sol1() throws Exception {
        Assert.assertEquals("aaacecaaa", shortestPalindrome_Sol1("aacecaaa"));
        Assert.assertEquals("dcbabcd", shortestPalindrome_Sol1("abcd"));
        Assert.assertEquals("dcbbcd", shortestPalindrome_Sol1("bbcd"));
        Assert.assertEquals("axyxxyxa", shortestPalindrome_Sol1("xxyxa"));
        Assert.assertEquals("ababbabbbababbbabbaba", shortestPalindrome_Sol1("ababbbabbaba"));
    }

    @Test
    public void test_Sol2() throws Exception {
        Assert.assertEquals("aaacecaaa", shortestPalindrome_Sol2("aacecaaa"));
        Assert.assertEquals("dcbabcd", shortestPalindrome_Sol2("abcd"));
        Assert.assertEquals("dcbbcd", shortestPalindrome_Sol2("bbcd"));
        Assert.assertEquals("axyxxyxa", shortestPalindrome_Sol2("xxyxa"));
        Assert.assertEquals("ababbabbbababbbabbaba", shortestPalindrome_Sol2("ababbbabbaba"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #2 ////////////////////////////////////////////////////////////

    /**
     * Exceed time limit on LeetCode.
     */
    private String shortestPalindrome_Sol2(String s) {
        // Reference: https://leetcode.com/problems/shortest-palindrome/discuss/

        // TODO: Finish it.

        return s;
    }

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Exceed time limit on LeetCode.
     */
    private String shortestPalindrome_Sol1(String s) {
        if (s.length() == 1) return s;

        // The idea is to find the longest palindromic substring where it starts
        // from the position zero.
        //
        // For example:
        //   a a c e c a a a ---> begin coordinate
        // -+----------------
        // a|y
        // a|y y
        // c|n n y
        // e|n n n y
        // c|n n y n y
        // a|n y n n n y
        // a|y n n n n y y
        // a|n n n n n y y y
        // |
        // |
        // v
        // end coordinate

        int longest = 0;
        int longestBegin = -1;
        int longestEnd = -1;

        // Find out the longest palindromic substring.
        // Time complexity: O(n^2)
        final boolean[][] map = new boolean[s.length()][s.length()];
        for (int end = 0; end < s.length(); ++end) {
            for (int begin = end; begin >= 0; --begin) {
                final boolean identical = s.substring(begin, begin + 1)
                                           .equals(s.substring(end, end + 1));
                map[begin][end] = identical &&
                                  (end - begin < 3 ||
                                   map[begin + 1][end - 1]);

                if (map[begin][end] &&
                    begin == 0 &&
                    end - begin + 1 > longest) {
                    longest = end - begin + 1;
                    longestBegin = begin;
                    longestEnd = end;
                }
            }
        }

        if (longestBegin == 0) {
            // Time complexity: O(n)
            final String suffix = s.substring(longestEnd + 1);
            final StringBuilder builder = new StringBuilder(suffix);

            return builder.reverse().toString() + s;
        } else {
            // Find first non-repeated character.
            // Time complexity: O(n)
            int start = 0;
            for (int i = 0; i < s.length(); ++i) {
                if (!s.substring(i, i + 1).equals(s.substring(start, start + 1))) {
                    start = i;
                    break;
                }
            }
            final String suffix = s.substring(start);
            final StringBuilder builder = new StringBuilder(suffix);

            return builder.reverse().toString() + s;
        }
    }
}
