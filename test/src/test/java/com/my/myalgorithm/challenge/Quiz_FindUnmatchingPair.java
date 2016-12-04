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
import java.util.List;
import java.util.Stack;

import static junit.framework.Assert.assertTrue;

/**
 * Given two pre-order traversal arrays of two binary search tree respectively,
 * find first pair of non-matching leaves.
 * <br/>
 * Follow Up: If they are general binary trees instead of BSTs, could you solve
 * it? give out your reason.
 * <br/>
 * <pre>
 * 1)
 *               30
 *              / +-----+
 *            13        43
 *           / \       / \
 *          8  15    38  50
 *                  / \
 *                31  40
 *
 * 2)
 *               30
 *              / +-----+
 *            13        42
 *           / \       / \
 *          8  16    38  50
 *                  / \
 *                31  41
 *
 * array 1: [30,13,8,15,43,38,31,40,50]
 * array 2: [30,13,8,16,42,38,31,41,50]
 *                 ^  ^        ^  ^  ^
 * </pre>
 * <br/>
 * <br/>
 * Reference:
 * <br/>
 * https://www.careercup.com/question?id=5692379756494848
 */
public class Quiz_FindUnmatchingPair {

    @Test
    public void answer1() throws Exception {
        NonMatchPair nonMatchPair = findFirstNonMatchPair(new int[]{30, 13, 8, 15, 43, 38, 31, 40, 50},
                                             new int[]{30, 13, 8, 16, 42, 38, 31, 41, 50});
        assertTrue(nonMatchPair.first == 15 && nonMatchPair.second == 16);
    }

    @Test
    public void answer2() throws Exception {

    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private NonMatchPair findFirstNonMatchPair(int[] tree1,
                                               int[] tree2) {
        NonMatchPair pair = null;
        // Find the leafs.
        List<Integer> leafs1 = getLeafs(tree1);
        List<Integer> leafs2 = getLeafs(tree2);

        int len = Math.min(leafs1.size(), leafs2.size());
        for (int i = 0; i < len; ++i) {
            if (!leafs1.get(i).equals(leafs2.get(i))) {
                // Found!
                pair = new NonMatchPair(leafs1.get(i),
                                        leafs2.get(i));
                break;
            }
        }
        if (pair == null &&
            leafs1.size() != leafs2.size()) {
            if (leafs1.size() > leafs2.size()) {
                pair = new NonMatchPair(leafs1.get(leafs2.size()),
                                        -1);
            } else {
                pair = new NonMatchPair(leafs2.get(leafs1.size()),
                                        -1);
            }
        }

        return pair;
    }

    private List<Integer> getLeafs(int[] inOrderTree) {
        int counter = 0;
        Stack<Integer> lookupStack = new Stack<>();
        List<Integer> leafs = new ArrayList<>();

        lookupStack.push(inOrderTree[0]);
        for (int i = 1; i < inOrderTree.length; ++i) {
            Integer current = inOrderTree[i];
            if (current > lookupStack.lastElement()) {
                // Must found a leaf.
                leafs.add(lookupStack.pop());
                // Search for the new root and renew the stack starting with
                // the new root.
                while (!lookupStack.isEmpty() &&
                       current > lookupStack.lastElement()) {
                    lookupStack.pop();
                    // Accumulate the counter.
                    ++counter;
                }
            }
            // Add to the lookup stack.
            lookupStack.push(inOrderTree[i]);
            // Accumulate the counter.
            ++counter;
        }
        // Last one must be a leaf too.
        leafs.add(inOrderTree[inOrderTree.length - 1]);

        System.out.print("[");
        for (int i = 0; i < inOrderTree.length; ++i) {
            System.out.print(inOrderTree[i]);
            if (i < inOrderTree.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("] => ");
        System.out.println(String.format("%s takes %s iteration",
                                         leafs,
                                         counter));

        return leafs;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class NonMatchPair {

        int first;
        int second;

        NonMatchPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
