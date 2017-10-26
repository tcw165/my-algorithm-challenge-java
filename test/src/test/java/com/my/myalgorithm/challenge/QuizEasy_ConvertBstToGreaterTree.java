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

/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every
 * key of the original BST is changed to the original key plus sum of all keys
 * greater than the original key in BST.
 * <p/>
 * For example:
 * <pre>
 * Input: The root of a Binary Search Tree like this:
 *               5
 *             /   \
 *            2     13
 * Output: The root of a Greater Tree like this:
 *               18
 *             /   \
 *           20     13
 * </pre>
 * References:
 * <ul>
 * <li>https://leetcode.com/problems/convert-bst-to-greater-tree/description/</li>
 * </ul>
 */
public class QuizEasy_ConvertBstToGreaterTree {

    @Test
    public void test() {
        verify(new int[]{18, 20, 13},
               convertBST(createTree(5, 2, 13)));
        verify(new int[]{45, 65, 66, 58, 62, 52, 37, 14, 27},
               convertBST(createTree(8, 3, 10, 1, 6, 14, 4, 7, 13)));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 15% on LeetCode.
     */
    public TreeNode convertBST(TreeNode root) {

        // Given a tree for example:
        //           8
        //        /    \
        //       3      10
        //     /  \       \
        //    1    6      14
        //       /  \     /
        //      4   7   13
        //
        // The problem is essentially done by traversing the tree in right-to-
        // left-in-order. The order we convert the node is like:
        //
        //           8 (4)
        //         /     \
        //       3 (8)    10 (3)
        //    /   \          \
        //  1 (9)  6 (6)       14 (1)
        //       /   \        /
        //      4 (7) 7 (5)  13 (2)
        //

        convertBstInOrder(root, 0);

        return root;
    }

    public int convertBstInOrder(TreeNode node,
                                 int successor) {
        if (node == null) return 0;

        // Find new successor in the right tree.
        if (node.right != null) {
            successor = convertBstInOrder(node.right, successor);
        }

        node.val += successor;
        successor = node.val;

        // Find the predecessor in the left tree.
        if (node.left != null) {
            successor = convertBstInOrder(node.left, successor);
        }

        return successor;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private void verify(int[] expect,
                        TreeNode actual) {
        // Traverse the tree in in-order and verify the values.
        int[] index = new int[] {0};

        verifyLeft2RightPreOrder(expect, index, actual);
    }

    private void verifyLeft2RightPreOrder(int[] expect,
                                          int[] currentIndex,
                                          TreeNode actual) {
        Assert.assertEquals(expect[currentIndex[0]], actual.val);
        ++currentIndex[0];

        if (actual.left != null) {
            verifyLeft2RightPreOrder(expect, currentIndex, actual.left);
        }

        if (actual.right != null) {
            verifyLeft2RightPreOrder(expect, currentIndex, actual.right);
        }
    }

    private TreeNode createTree(int... vals) {
        if (vals.length == 0) return null;

        final TreeNode root = new TreeNode(vals[0]);
        for (int i = 1; i < vals.length; ++i) {
            addNodeToTree(root, vals[i]);
        }

        return root;
    }

    private void addNodeToTree(TreeNode root, int val) {
        if (root == null) {
            throw new IllegalArgumentException(
                "Given root is null.");
        }

        // Traverse the tree to find the parent node for the given value.
        TreeNode parent = null;
        TreeNode node = root;
        while (node != null) {
            if (val < node.val) {
                parent = node;
                node = node.left;
            } else {
                parent = node;
                node = node.right;
            }
        }

        // Add node.
        if (val < parent.val) {
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
