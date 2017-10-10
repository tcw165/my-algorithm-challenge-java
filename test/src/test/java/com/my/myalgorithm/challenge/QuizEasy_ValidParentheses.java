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

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * <p/>
 * The brackets must close in the correct order, "()" and "()[]{}" are all
 * valid but "(]" and "([)]" are not.
 * <p/>
 * Reference:
 * <br/>
 * <ul>
 *     <li>LeetCode: https://leetcode.com/problems/valid-parentheses/description/</li>
 * </ul>
 */
public class QuizEasy_ValidParentheses {

    @Test
    public void test() throws Exception {
        Assert.assertEquals(true, isValid("()"));
        Assert.assertEquals(true, isValid("()[]{}"));

        Assert.assertEquals(false, isValid("]"));
        Assert.assertEquals(false, isValid("(]"));
        Assert.assertEquals(false, isValid("({["));
        Assert.assertEquals(false, isValid("([)]"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 89.70% on LeetCode.
     */
    private boolean isValid(String s) {
        Stack<Character> parens = new Stack<>();

        // Time complexity: O(n)
        for (int i = 0, size = s.length(); i < size; ++i) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                parens.push(c);
            } else if (c == ')' ||
                       c == ']' ||
                       c == '}') {
                if (parens.isEmpty()) {
                    return false;
                }

                if ((c == ')' && parens.peek() == '(') ||
                    (c == ']' && parens.peek() == '[') ||
                    (c == '}' && parens.peek() == '{')) {
                    parens.pop();
                } else {
                    return false;
                }
            }
        }

        return parens.size() == 0;
    }
}
