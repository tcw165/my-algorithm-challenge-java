package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * <p>
 * For example,
 * <p>
 *   Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 *   Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 *   Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 *   answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * LeetCode: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class Quiz_LongestSubstringWithoutRepeatingCharacters {

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
        Assert.assertEquals(2, lengthOfLongestSubstring("aab"));
        Assert.assertEquals(3, lengthOfLongestSubstring("dvdf"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Use a sliding window as below:
     * <pre>
     * "abcabcbb" => window size = 1
     *  ^
     *  ^
     *
     * "abcabcbb" => window size = 2
     *  ^
     *   ^
     *
     * "abcabcbb" => window size = 3
     *  ^
     *    ^
     *
     * "abcabcbb" => window size = need to recalculate
     *  ^
     *     ^
     *
     * "abcabcbb" => window size = 3
     *   ^
     *     ^
     *
     * "abcabcbb" => window size = need to recalculate
     *   ^
     *      ^
     *
     * "abcabcbb" => window size = 3
     *    ^
     *      ^
     *
     * "abcabcbb" => window size = need to recalculate
     *    ^
     *       ^
     *
     * "abcabcbb" => window size = 3
     *     ^
     *       ^
     *
     * "abcabcbb" => window size = need to recalculate
     *     ^
     *        ^
     *
     * "abcabcbb" => window size = 2
     *       ^
     *        ^
     *
     * "abcabcbb" => window size = need to recalculate
     *       ^
     *         ^
     *
     * "abcabcbb" => window size = 1
     *         ^
     *         ^
     * </pre>
     *
     * Beats 55.63% on Leetcode.
     */
    private int lengthOfLongestSubstring(String s) {
        int size = s.length();

        if (size > 0) {
            Map<Character, Integer> map = new HashMap<>();
            int longestLength = 1;

            // Create a sliding window.
            int subStrStart = 0;
            int subStrEnd = 0;
            while (subStrEnd < size) {
                char c = s.charAt(subStrEnd);

                if (map.get(c) != null) {
                    // Move the new start to the right position.
                    int oldSubStrStart = subStrStart;

                    subStrStart = map.get(c) + 1;

                    // Update the visited characters table.
                    while (oldSubStrStart < subStrStart) {
                        map.remove(s.charAt(oldSubStrStart));
                        ++oldSubStrStart;
                    }
                }

                longestLength = Math.max(longestLength, subStrEnd - subStrStart + 1);

                map.put(s.charAt(subStrEnd), subStrEnd);
                ++subStrEnd;
            }

            return longestLength;
        } else {
            return 0;
        }
    }

    private void print(float[][] points) {

    }
}
