package com.my.myalgorithm.challenge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, group anagrams together.
 * <p/>
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * <p/>
 * <pre>
 *  [
 *    ["ate", "eat","tea"],
 *    ["nat","tan"],
 *    ["bat"]
 *  ]
 * </pre>
 * Note: All inputs will be in lower-case.
 * <p/>
 * References:
 * <ul>
 *     <li>https://leetcode.com/problems/group-anagrams/description/</li>
 * </ul>
 */
public class QuizMedium_GroupAnagrams {

    @Test
    public void test1() {
        // Solution #1.
        print(groupAnagrams1(new String[]{"eat     eat",
                                          "tea eat",
                                          "tan",
                                          "ate",
                                          "nat",
                                          "bat"}));
    }

    @Test
    public void test2() {
        // Solution #2.
        print(groupAnagrams2(new String[]{"eat     eat",
                                          "tea eat",
                                          "tan",
                                          "ate",
                                          "nat",
                                          "bat"}));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    /**
     * Solution #1
     */
    private List<List<String>> groupAnagrams1(String[] strs) {
        // O(n).
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            final char[] rawChars = str.toCharArray();
            Arrays.sort(rawChars);

            final String key = String.valueOf(rawChars).replace(" ", "");

            if (!map.containsKey(key)) map.put(key, new ArrayList<String>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Solution #2
     */
    private List<List<String>> groupAnagrams2(String[] strs) {
        final int[] PRIMES = new int[]{2, 3, 5, 7, 11 ,13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 107};

        final Map<Integer, List<String>> map = new HashMap();
        for (String str : strs) {
            int key = 1;
            for (int i = 0, size = str.length(); i < size; ++i) {
                char c = str.charAt(i);
                if (c == ' ') continue;

                key *= PRIMES[c - 'a'];
            }

            if (map.get(key) == null) {
                map.put(key, new LinkedList<String>());
            }
            map.get(key).add(str);
        }

        return new LinkedList<>(map.values());
    }

    private void print(List<List<String>> groups) {
        if (groups == null) return;

        StringBuilder builder = new StringBuilder();
        for (int i = 0, size = groups.size(); i < size; ++i) {
            final List<String> group = groups.get(i);

            for (int j = 0, groupSize = group.size(); j < groupSize; ++j) {
                final String string = group.get(j);

                builder.append(string);
                if (j != groupSize - 1) {
                    builder.append(", ");
                }
            }

            builder.append("\n");
        }

        System.out.print(builder.toString());
    }
}
