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

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Given a digit string, return all possible letter combinations that the number
 * could represent.
 * <br/>
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * <br/>
 * <pre>
 * [---]    [abc]    [def]
 *   1        2        3
 *
 * [ghi]    [jkl]    [mno]
 *   4        5        6
 *
 * [pqrs]   [tuv]    [wxyz]
 *   7        8        9
 * </pre>
 * <br/>
 * Reference:
 * <br/>
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class QuizMedium_LetterCombinationsOfPhoneNumber {

    @Test
    public void answer1() throws Exception {
        printLetters(letterCombinations("23"));
        printLetters(letterCombinations("238"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private char[][] mAlphabetDict = new char[][]{
        // 0
        {},
        // 1
        {},
        // 2
        {'a', 'b', 'c'},
        // 3
        {'d', 'e', 'f'},
        // 4
        {'g', 'h', 'i'},
        // 5
        {'j', 'k', 'l'},
        // 6
        {'m', 'n', 'o'},
        // 7
        {'p', 'q', 'r', 's'},
        // 8
        {'t', 'u', 'v'},
        // 9
        {'w', 'x', 'y', 'z'}
    };

    /**
     * Example:
     * <pre>
     * Given "23"
     *
     * "23" => [a,b,c], total 3 possibility => [a,b,c]
     *  ^
     *
     * "23" => [d,e,f], total 3 possibility => [ad,bd,cd] +
     *   ^                                     [ae,be,ce] +
     *                                         [af,bf,cf]
     * </pre>
     */
    private List<String> letterCombinations(String digits) {
        List<String> comb = new ArrayList<>();

        comb.add("");

        for (int i = 0; i < digits.length(); ++i) {
            int key = Character.getNumericValue(digits.charAt(i));
            char dict[] = mAlphabetDict[key];
            List<String> clone = new ArrayList<>();
            for (int j = 0; j < comb.size(); ++j) {
                for (char alphabet : dict) {
                    clone.add(comb.get(j).concat(String.valueOf(alphabet)));
                }
            }
            comb = clone;
        }

        return comb;
    }

    /**
     * The solution with highest votes.
     * https://discuss.leetcode.com/topic/8465/my-java-solution-with-fifo-queue
     */
//    public List<String> letterCombinations(String digits) {
//        LinkedList<String> ans = new LinkedList<String>();
//        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//        ans.add("");
//        for(int i =0; i<digits.length();i++){
//            int x = Character.getNumericValue(digits.charAt(i));
//            while(ans.peek().length()==i){
//                String t = ans.remove();
//                for(char s : mapping[x].toCharArray())
//                    ans.add(t+s);
//            }
//        }
//        return ans;
//    }

    private void printLetters(List<String> letters) {
        System.out.print("[");
        for (int i = 0; i < letters.size(); ++i) {
            System.out.print(letters.get(i));
            if (i < letters.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
