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
 * Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
 * <p/>
 * Example:
 * <pre>
 *     Input : X = "GeeksforGeeks", y = "GeeksQuiz"
 *     Output : 5
 *     The longest common substring is "Geeks" and is of
 *     length 5.
 *
 *     Input : X = "abcdxyz", y = "xyzabcd"
 *     Output : 4
 *     The longest common substring is "abcd" and is of
 *     length 4.
 *
 *     Input : X = "zxabcdezy", y = "yzabcdezx"
 *     Output : 6
 *     The longest common substring is "abcdez" and is of
 *     length 6.
 * </pre>
 * Reference:
 * <ul>
 * <li>http://www.geeksforgeeks.org/longest-common-substring/</li>
 * </ul>
 */
public class QuizHard_LongestCommonSubstring {

    @Test
    public void test_Sol1() throws Exception {
        //   G e e k s f o r G e e k s
        // G 1 0 0 0 0 0 0 0 1 0 0 0 0
        // e 0 2 0 0 0 0 0 0 0 2 1 0 0
        // e 0 1 3 0 0 0 0 0 0 1 3 0 0
        // k 0 0 0 4 0 0 0 0 0 0 0 4 0
        // s 0 0 0 0 5 0 0 0 0 0 0 0 5
        // Q 0 0 0 0 0 0 0 0 0 0 0 0 0
        // u 0 0 0 0 0 0 0 0 0 0 0 0 0
        // i 0 0 0 0 0 0 0 0 0 0 0 0 0
        // z 0 0 0 0 0 0 0 0 0 0 0 0 0
        Assert.assertEquals(5, longestCommonSubstring("GeeksforGeeks",
                                                      "GeeksQuiz"));

        //   a b c d x y z
        // x 0 0 0 0 1 0 0
        // y 0 0 0 0 0 2 0
        // z 0 0 0 0 0 0 3
        // a 1 0 0 0 0 0 0
        // b 0 2 0 0 0 0 0
        // c 0 0 3 0 0 0 0
        // d 0 0 0 4 0 0 0
        Assert.assertEquals(4, longestCommonSubstring("abcdxyz",
                                                      "xyzabcd"));

        //   a b x d x y z
        // x 0 0 1 0 1 0 0
        // y 0 0 0 0 0 2 0
        // z 0 0 0 0 0 0 3
        // a 1 0 0 0 0 0 0
        // b 0 2 0 0 0 0 0
        // c 0 0 0 0 0 0 0
        // d 0 0 0 1 0 0 0
        Assert.assertEquals(3, longestCommonSubstring("abxdxyz",
                                                      "xyzabcd"));

        //   z x a b c d e z y
        // y 0 0 0 0 0 0 0 0 1
        // z 1 0 0 0 0 0 0 1 0
        // a 0 0 1 0 0 0 0 0 0
        // b 0 0 0 2 0 0 0 0 0
        // c 0 0 0 0 3 0 0 0 0
        // d 0 0 0 0 0 4 0 0 0
        // e 0 0 0 0 0 0 5 0 0
        // z 1 0 0 0 0 0 0 6 0
        // x 0 2 0 0 0 0 0 0 0
        Assert.assertEquals(6, longestCommonSubstring("zxabcdezy",
                                                      "yzabcdezx"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    private int longestCommonSubstring(String x, String y) {
        // For example:
        // Given: a b x c x y z
        //    and x y z a b c
        //
        // The simple way is to consider all substrings of first string:
        // a b x c x y z
        // *
        //   *
        // * *
        //     *
        //   * *
        // * * *
        //       *
        //     * *
        //   * * *
        // * * * *
        //         *
        //       * *
        //     * * *
        //   * * * *
        // * * * * *
        //           *
        //         * *
        //       * * *
        //     * * * *
        //   * * * * *
        // * * * * * *
        //             *
        //           * *
        //         * * *
        //       * * * *
        //     * * * * *
        //   * * * * * *
        // * * * * * * *
        //
        // For every substring above, check if it is a substring in the second
        // string:
        //
        //       a b x c x y z
        //       | |
        // x y z a b c d
        //
        // a b x c x y z
        //     |
        //     x y z a b c d
        //
        //     a b x c x y z
        //           |
        // x y z a b c d
        //
        // a b x c x y z
        //         | | |
        //         x y z a b c d
        //
        // Thus time complexity is, O(n^2 * m)
        //
        // You might also notice there are some duplicate comparison, for example:
        // "ab" and "abx". We already check "ab" when checking "abx". We could
        // improve that by memorizing the result.
        // So essentially, it is a Dynamic Programming problem.
        //
        // T[n][m] represents length of common substring
        //
        //    | a | b | x | d | x | y | z
        // ---+---+---+---+---+---+---+---
        //  x | 0 | 0 | 1 | 0 | 1 | 0 | 0
        // ---+---+---+---+---+---+---+---
        //  y | 0 | 0 | 0 | 0 | 0 | 2 | 0
        // ---+---+---+---+---+---+---+---
        //  z | 0 | 0 | 0 | 0 | 0 | 0 | 3
        // ---+---+---+---+---+---+---+---
        //  a | 1 | 0 | 0 | 0 | 0 | 0 | 0
        // ---+---+---+---+---+---+---+---
        //  b | 0 | 2 | 0 | 0 | 0 | 0 | 0
        // ---+---+---+---+---+---+---+---
        //  c | 0 | 0 | 0 | 0 | 0 | 0 | 0
        // ---+---+---+---+---+---+---+---
        //
        // And the maximum number in the matrix is 3, where it is the length of
        // "xyz".

        final int n = x.length();
        final int m = y.length();
        final int[][] map = new int[n][m];
        int maxLength = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (x.substring(i, i + 1).equals(y.substring(j, j + 1))) {
                    // If the current characters are identical:
                    // a. 1 + length of previous common suffix.
                    // b. 1 if there is no previous common suffix.
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        map[i][j] = map[i - 1][j - 1] + 1;
                    } else {
                        map[i][j] = 1;
                    }
                } else {
                    // If the current characters are different.
                    map[i][j] = 0;
                }

                maxLength = Math.max(maxLength, map[i][j]);
            }
        }

        return maxLength;
    }
}
