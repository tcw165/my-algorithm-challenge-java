package com.my.myalgorithm.challenge;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BinarySearchTreeTest {

    @Test
    public void test1() throws Exception {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        // A tree should looks like the following.
        //           ___15___
        //          /        \
        //        _6          18
        //       /  \        /  \
        //    __3    7     17    20
        //   /   \    \    /
        //  2     4   13  16
        //            /
        //           9
        tree.add(15);
        tree.add(6);
        tree.add(18);
        tree.add(3);
        tree.add(7);
        tree.add(17);
        tree.add(20);
        tree.add(2);
        tree.add(4);
        tree.add(13);
        tree.add(16);
        tree.add(9);

        assertTrue("Root is 15", tree.mRoot.key.equals(15));
        assertTrue("Size should be 12", tree.size() == tree.size(true) && tree.size() == 12);
        assertTrue("Should contain 9", tree.contains(9));
        assertTrue("The max height should be 5", tree.maxHeight() == 5);
        assertTrue("The min height should be 3", tree.minHeight() == 3);
        assertTrue("The min should be 2", tree.min() == 2);
        assertTrue("The max should be 2", tree.max() == 20);

        tree.remove(6);
        //              ____15___
        //             /         \
        //          __7           18
        //         /   \         /  \
        //        3     13      17   20
        //       / \    /      /
        //      2   4  9      16
        assertTrue("Size becomes 11", tree.size() == tree.size(true) && tree.size() == 11);
        assertTrue("The left child of 15 is 7", tree.mRoot.left.key.equals(7));
        assertTrue("The max height is still 5", tree.maxHeight() == 4);
        assertTrue("The min height is still 3", tree.minHeight() == 3);

        tree.remove(15);
        //              ____16___
        //             /         \
        //          __7           18
        //         /   \         /  \
        //        3     13      17   20
        //       / \    /
        //      2   4  9
        assertTrue("Size becomes 10", tree.size() == tree.size(true) && tree.size() == 10);
        assertTrue("Root key becomes 16", tree.mRoot.key.equals(16));

        tree.remove(13);
        //              ____16___
        //             /         \
        //          __7           18
        //         /   \         /  \
        //        3     9      17   20
        //       / \
        //      2   4
        assertTrue("Size becomes 10", tree.size() == tree.size(true) && tree.size() == 9);

        tree.remove(16);
        //              ____17___
        //             /         \
        //          __7           18
        //         /   \            \
        //        3     9           20
        //       / \
        //      2   4
        assertTrue("Size becomes 10", tree.size() == tree.size(true) && tree.size() == 8);
        assertTrue("18 must not have left child", tree.find(tree.mRoot, 18).left == null);
    }

    @Test
    public void test2() throws Exception {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        // A tree should looks like the following.
        //                     _________18_________
        //                    /                    \
        //               ___10___                __24___
        //              /        \              /       \
        //          ___5_        17            21       30
        //         /     \      /  \          /  \      /
        //        1       7    15   19       20   22   28
        //       / \     /    /  \                 \
        //      0   2   6   14    16                23
        //           \
        //            3
        //             \
        //              4
        tree.add(18);
        tree.add(10);
        tree.add(24);
        tree.add(5);
        tree.add(17);
        tree.add(21);
        tree.add(30);
        tree.add(1);
        tree.add(7);
        tree.add(15);
        tree.add(19);
        tree.add(22);
        tree.add(28);
        tree.add(0);
        tree.add(2);
        tree.add(6);
        tree.add(14);
        tree.add(16);
        tree.add(20);
        tree.add(23);
        tree.add(3);
        tree.add(4);

        assertTrue("Root is 18", tree.mRoot.key.equals(18));
        assertTrue("Size should be 22", tree.size() == tree.size(true) && tree.size() == 22);
        assertTrue("The max height should be 7", tree.maxHeight() == 7);
        assertTrue("The min height should be 4", tree.minHeight() == 4);
        assertTrue("The min should be 0", tree.min() == 0);
        assertTrue("The max should be 30", tree.max() == 30);
    }
}
