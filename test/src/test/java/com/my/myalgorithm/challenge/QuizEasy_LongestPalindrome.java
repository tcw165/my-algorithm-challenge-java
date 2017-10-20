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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string which consists of lowercase or uppercase letters, find the
 * length of the longest palindromes that can be built with those letters.
 * <br/>
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * <p/>
 * For example:
 * <pre>
 *     Input:
 *     "abccccdd"
 *
 *     Output:
 *     7
 *
 *     Explanation:
 *     One longest palindrome that can be built is "dccaccd", whose length is 7.
 * </pre>
 * References:
 * <ul>
 * <li>https://leetcode.com/problems/longest-palindrome/description/</li>
 * </ul>
 */
public class QuizEasy_LongestPalindrome {

    @Test
    public void test() {
        Assert.assertEquals(1, longestPalindrome("a"));
        Assert.assertEquals(1, longestPalindrome("Aa"));
        Assert.assertEquals(1, longestPalindrome("AB"));
        Assert.assertEquals(7, longestPalindrome("abccccdd"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 37% on LeetCode.
     */
    private int longestPalindrome(String s) {
        if (s == null) return 0;
        if (s.length() == 1) return 1;

        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        final Set<Character> map = new HashSet<>();
        int longest = 0;
        for (char c : chars) {
            if (map.contains(c)) {
                longest += 2;
                map.remove(c);
            } else {
                map.add(c);
            }
        }

        if (map.isEmpty()) {
            return longest;
        } else {
            return longest + 1;
        }
    }
}
