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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume
 * that the maximum length of s is 1000.
 * <br/>
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * <p/>
 * For example:
 * <pre>
 *     Input: "babad"
 *     Output: "bab"
 *     Note: "aba" is also a valid answer.
 *
 *     Input: "cbbd"
 *     Output: "bb"
 * </pre>
 * References:
 * <ul>
 * <li>https://leetcode.com/problems/longest-palindromic-substring/description/</li>
 * </ul>
 */
public class QuizMedium_LongestPalindromicSubstring {

    @Test
    public void test_Sol1() {
        verify(new String[]{"bab", "aba"},
               longestPalindrome_Sol1("babad"));
        verify(new String[]{"ccabbbacc"},
               longestPalindrome_Sol1("babadcctcaabbbbbccabbbacc"));
        verify(new String[]{"xrcrx"},
               longestPalindrome_Sol1("cyyoacmjwjubfkzrrbvquqkwhsxvmytmjvbborrtoi" +
                                      "yotobzjmohpadfrvmxuagbdczsjuekjrmcwyaovpio" +
                                      "gspbslcppxojgbfxhtsxmecgqjfuvahzpgprscjwwu" +
                                      "twoiksegfreortttdotgxbfkisyakejihfjnrdngkw" +
                                      "jxeituomuhmeiesctywhryqtjimwjadhhymydlsmcp" +
                                      "ycfdzrjhstxddvoqprrjufvihjcsoseltpyuaywgio" +
                                      "cfodtylluuikkqkbrdxgjhrqiselmwnpdzdmpsvbfi" +
                                      "mnoulayqgdiavdgeiilayrafxlgxxtoqskmtixhbyj" +
                                      "ikfmsmxwribfzeffccczwdwukubopsoxliagenzwkb" +
                                      "iveiajfirzvngverrbcwqmryvckvhpiioccmaqoxgm" +
                                      "bwenyeyhzhliusupmrgmrcvwmdnniipvztmtklihob" +
                                      "bekkgeopgwipihadswbqhzyxqsdgekazdtnamwzbit" +
                                      "wfwezhhqznipalmomanbyezapgpxtjhudlcsfqondo" +
                                      "iojkqadacnhcgwkhaxmttfebqelkjfigglxjfqegxp" +
                                      "cawhpihrxydprdgavxjygfhgpcylpvsfcizkfbqzdn" +
                                      "mxdgsjcekvrhesykldgptbeasktkasyuevtxrcrxmi" +
                                      "ylrlclocldmiwhuizhuaiophykxskufgjbmcmzpogp" +
                                      "myerzovzhqusxzrjcwgsdpcienkizutedcwrmowwol" +
                                      "ekockvyukyvmeidhjvbkoortjbemevrsquwnjoaikh" +
                                      "bkycvvcscyamffbjyvkqkyeavtlkxyrrnsmqohyyqx" +
                                      "zgtjdavgwpsgpjhqzttukynonbnnkuqfxgaatpilrr" +
                                      "xhcqhfyyextrvqzktcrtrsbimuokxqtsbfkrgoiznh" +
                                      "iysfhzspkpvrhtewthpbafmzgchqpgfsuiddjkhnwc" +
                                      "hpleibavgmuivfiorpteflholmnxdwewj"));
    }

    @Test
    public void test_Sol2() {
        verify(new String[]{"bab", "aba"},
               longestPalindrome_Sol2("babad"));
        verify(new String[]{"ccabbbacc"},
               longestPalindrome_Sol2("babadcctcaabbbbbccabbbacc"));
        verify(new String[]{"xrcrx"},
               longestPalindrome_Sol2("cyyoacmjwjubfkzrrbvquqkwhsxvmytmjvbborrtoi" +
                                      "yotobzjmohpadfrvmxuagbdczsjuekjrmcwyaovpio" +
                                      "gspbslcppxojgbfxhtsxmecgqjfuvahzpgprscjwwu" +
                                      "twoiksegfreortttdotgxbfkisyakejihfjnrdngkw" +
                                      "jxeituomuhmeiesctywhryqtjimwjadhhymydlsmcp" +
                                      "ycfdzrjhstxddvoqprrjufvihjcsoseltpyuaywgio" +
                                      "cfodtylluuikkqkbrdxgjhrqiselmwnpdzdmpsvbfi" +
                                      "mnoulayqgdiavdgeiilayrafxlgxxtoqskmtixhbyj" +
                                      "ikfmsmxwribfzeffccczwdwukubopsoxliagenzwkb" +
                                      "iveiajfirzvngverrbcwqmryvckvhpiioccmaqoxgm" +
                                      "bwenyeyhzhliusupmrgmrcvwmdnniipvztmtklihob" +
                                      "bekkgeopgwipihadswbqhzyxqsdgekazdtnamwzbit" +
                                      "wfwezhhqznipalmomanbyezapgpxtjhudlcsfqondo" +
                                      "iojkqadacnhcgwkhaxmttfebqelkjfigglxjfqegxp" +
                                      "cawhpihrxydprdgavxjygfhgpcylpvsfcizkfbqzdn" +
                                      "mxdgsjcekvrhesykldgptbeasktkasyuevtxrcrxmi" +
                                      "ylrlclocldmiwhuizhuaiophykxskufgjbmcmzpogp" +
                                      "myerzovzhqusxzrjcwgsdpcienkizutedcwrmowwol" +
                                      "ekockvyukyvmeidhjvbkoortjbemevrsquwnjoaikh" +
                                      "bkycvvcscyamffbjyvkqkyeavtlkxyrrnsmqohyyqx" +
                                      "zgtjdavgwpsgpjhqzttukynonbnnkuqfxgaatpilrr" +
                                      "xhcqhfyyextrvqzktcrtrsbimuokxqtsbfkrgoiznh" +
                                      "iysfhzspkpvrhtewthpbafmzgchqpgfsuiddjkhnwc" +
                                      "hpleibavgmuivfiorpteflholmnxdwewj"));
    }

    public void verify(String[] expect, String actual) {
        final Set<String> answers = new HashSet<>();

        Collections.addAll(answers, expect);

        Assert.assertTrue(answers.contains(actual));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #2 ////////////////////////////////////////////////////////////

    /**
     * Beats 9% on LeetCode.
     */
    private String longestPalindrome_Sol2(String s) {
        int n = s.length();
        String res = null;

        // For example: "babad"
        //
        // You have to visit every possible substring. And for every possible
        // substring, you need to traverse every single characters to tell
        // whether it can form a palindromic substring or not. For example:
        //
        //  b | a | b | a | d
        // ---+---+---+---+---
        //  +
        //  +---+
        //  +--(-)--+           "a" <------------------------------------------.
        //  +-----------+                                                      |
        //  +---------------+                                                  |
        //      +               You might notice there is a duplicated visit, "a".
        //      +---+
        //      +-------+
        //      +-----------+
        //          +
        //          +---+
        //          +-------+
        //              +
        //              +---+
        //                  +
        //
        // That leads this problem to a Dynamic Programming problem:
        //
        // dp(b, e) represents whether s(b ... e) can form a palindromic substring,
        // dp(b, e) is true when s(b) equals to s(e) and s(b+1 ... e-1) is a
        // palindromic substring.
        // When we found a palindrome, check if it's the longest one.
        //
        // According to the previous paradigm, we need a recursive function to
        // visit every possible substring and label the range as the substring is a
        // palindrome, and that's probably not efficient.
        //
        // The previous paradigm is more like a top-to-bottom traversal, and it
        // requires recursive calls, a visit lookup table and a palindromic
        // substring lookup table.
        //
        // If we do a bottom-to-up traversal, we only need a palindromic substring
        // lookup table.
        //
        // So let's change the order of traversing the elements to make it
        // efficient:
        //  b | a | b | a | d
        // ---+---+---+---+---
        //  +
        //      +              <-------------------------.
        //  +---+                                        |
        //          +                                    |
        //      +---+                                    |
        //  +--(-)--+          You have already visited "a" previously
        //              +
        //          +---+      <---------------------------.
        //      +-------+                                  |
        //  +-----------+                                  |
        //                  +                              |
        //              +---+                              |
        //          +-------+                              |
        //      +--(-----)--+  Another visited substring, "ba"
        //  +---------------+
        //
        // Time complexity O(n^2).
        boolean[][] dp = new boolean[n][n];

        for (int end = 0; end < n; ++end) {
            for (int start = end; start >= 0; --start) {
                dp[start][end] = (s.charAt(start) == s.charAt(end) &&
                                  (end - start < 3 || dp[start + 1][end - 1]));

                if (dp[start][end] &&
                    (res == null ||
                     end - start + 1 > res.length())) {
                    res = s.substring(start, end + 1);
                }
            }
        }

        return res;
    }

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats ?% on LeetCode.
     */
    private String longestPalindrome_Sol1(String s) {
        if (s == null) {
            return null;
        } else if (s.length() == 1) {
            return s;
        }

        // Search for all palindromes.
        // Time complexity: approximately n^3
        String longest = null;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = s.length(); j > i; --j) {
                // If the substring length is even smaller than the cache
                // longest one.
                if (longest != null &&
                    j - i + 1 < longest.length()) break;

                String ss = s.substring(i, j);
                if (isValidPalindrome(ss)) {
                    if (longest == null ||
                        ss.length() > longest.length()) {
                        longest = ss;
                    }
                }
            }
        }

        return longest;
    }

    private boolean isValidPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }

        return true;
    }
}
