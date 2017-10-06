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
 * A palindrome is a word, phrase, number, or other sequence of characters
 * which reads the same backward or forward, such as "madam" or "kayak".
 * Given a string, determine if it is a palindrome, considering only alphanumeric
 * characters and ignoring cases.
 * <br/>
 * <pre>
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * </pre>
 * Reference:
 * <br/>
 * https://leetcode.com/problems/valid-palindrome/
 */
public class QuizEasy_ValidPalindrome {

    @Test
    public void test() throws Exception {
        Assert.assertEquals(true, isPalindrome("madam"));
        Assert.assertEquals(true, isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertEquals(false, isPalindrome("race a car"));
        Assert.assertEquals(false, isPalindrome("9;8'4P?X:1Q8`dOfJuJXD6FF,8;`Y4! YBy'Wb:ll;;`;\"JI0c2uvD':!LAk:s\"!'0.!2B55.T1VI?00Du?1,l``RFsc?Y?9vD5 K'3'1b!N8hn:'l. R:9:o`m1r.M2mrJ?`Wjv1`G6i6G`1vjW`?Jrm2M.r1m`o:::R .l':nh8N!b1'3'K 5Dv9?Y?csFR``l,1?uD00?IV1T.55B2!.0'!\"s:kAL!:'Dvu2c0IJ\";`;;ll9bW'yBY !4Y`;8,FF6DXJuJfOd`8Q1:X?P4'8;9"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    /**
     * Beats 94% on LeetCode.
     */
    private boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() <= 1) return true;

        // Time complexity: O(n)
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char left = s.charAt(i);
            char right = s.charAt(j);

            if (!isAlphanumericLetter(left)) {
                ++i;
                continue;
            }
            if (!isAlphanumericLetter(right)) {
                --j;
                continue;
            }

            if (Character.toLowerCase(left) !=
                Character.toLowerCase(right)) {
                return false;
            }

            ++i;
            --j;
        }

        return true;
    }

    private boolean isAlphanumericLetter(char c) {
        return (c >= '0' && c <= '9') ||
               (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z');
    }
}
