package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program that outputs the string representation of numbers from 1 to
 * n. But for multiples of three it should output “Fizz” instead of the number
 * and for the multiples of five output “Buzz”. For numbers which are multiples
 * of both three and five output “FizzBuzz”.
 * <p/>
 * For example,
 * <pre>
 *   n = 15,
 *   Return:
 *   [
 *       "1",
 *       "2",
 *       "Fizz",
 *       "4",
 *       "Buzz",
 *       "Fizz",
 *       "7",
 *       "8",
 *       "Fizz",
 *       "Buzz",
 *       "11",
 *       "Fizz",
 *       "13",
 *       "14",
 *       "FizzBuzz"
 *   ]
 * </pre>
 * References:
 * <p/>
 * LeetCode: https://leetcode.com/problems/fizz-buzz/description/
 */
public class QuizEasy_FizzBuzz {

    @Test
    public void test() {
        Assert.assertArrayEquals(
            new String[] {
                "1",
                "2",
                "Fizz",
                "4",
                "Buzz",
                "Fizz",
                "7",
                "8",
                "Fizz",
                "Buzz",
                "11",
                "Fizz",
                "13",
                "14",
                "FizzBuzz"
            },
            toStringArray(fizzBuzz(15)));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 64.48% on LeetCode.
     */
    private List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<>(n);

        for (int i = 1, fizz = 0, buzz = 0; i <= n; i++) {
            fizz++;
            buzz++;
            if (fizz == 3 && buzz == 5) {
                ret.add("FizzBuzz");
                fizz = 0;
                buzz = 0;
            } else if (fizz == 3) {
                ret.add("Fizz");
                fizz = 0;
            } else if (buzz == 5) {
                ret.add("Buzz");
                buzz = 0;
            } else {
                ret.add(String.valueOf(i));
            }
        }

        return ret;
    }

    private String[] toStringArray(List<String> list) {
        final String[] output = new String[list.size()];

        list.toArray(output);

        return output;
    }
}
