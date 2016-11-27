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

package com.my.myalgorithm.challenge.tree;

import java.util.HashSet;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {

    protected Node<T> mRoot = null;

    protected boolean mIsHeightDirty = true;

    protected int mSize = 0;
    protected int mMinHeight = 0;
    protected int mMaxHeight = 0;

    public BinarySearchTree() {
        // DO NOTHING.
    }

    public int size() {
        return size(false);
    }

    public int size(boolean isCalc) {
        if (isCalc) {
            mSize = 0;
            if (mRoot != null) {
                Node<T> n = mRoot;
                HashSet<Node<T>> visited = new HashSet<>();
                Stack<Node<T>> s = new Stack<>();

                // Stack the starting node.
                s.push(n);
                while (!s.isEmpty()) {
                    if (n.left != null && !visited.contains(n.left)) {
                        n = n.left;
                        s.push(n);
                    } else if (n.right != null && !visited.contains(n.right)) {
                        n = n.right;
                        s.push(n);
                    } else {
                        // Add the visited set.
                        n = s.pop();
                        visited.add(n);

                        // Go the the parent.
                        n = n.parent;
                    }
                }

                mSize = visited.size();
            }
        }

        return mSize;
    }

    public int minHeight() {
        if (!mIsHeightDirty) return mMinHeight;

        calcHeight();

        return mMinHeight;
    }

    public int maxHeight() {
        if (!mIsHeightDirty) return mMaxHeight;

        calcHeight();

        return mMaxHeight;
    }

    public boolean isBalanced() {
        return mRoot != null &&
               maxHeight() - minHeight() < 2;
    }

    public void add(T key) {
        if (key == null) return;

        if (mRoot == null) {
            mRoot = new Node<>(key, null);

            ++mSize;

            // Calculating the maxHeight is complicated, do it later.
            mIsHeightDirty = true;
        } else {
            Node<T> x = mRoot;

            while (true) {
                if (key.compareTo(x.key) < 0) {
                    if (x.left == null) {
                        x.left = new Node<>(key);
                        x.left.parent = x;

                        ++mSize;

                        // Calculating the maxHeight is complicated, do it later.
                        mIsHeightDirty = true;
                        break;
                    } else {
                        x = x.left;
                    }
                } else if (key.compareTo(x.key) > 0) {
                    if (x.right == null) {
                        x.right = new Node<>(key);
                        x.right.parent = x;

                        ++mSize;

                        // Calculating the maxHeight is complicated, do it later.
                        mIsHeightDirty = true;
                        break;
                    } else {
                        x = x.right;
                    }
                } else {
                    // The key is already present in the tree, so do nothing.
                    break;
                }
            }
        }
    }

    public void remove(T key) {
        // TODO: Finish it.
        // Find the node by the given key.
        Node<T> target = find(mRoot, key);
        if (target != null) {
            if (target.left == null) {
                // The node has no left child, replace it with the right child.
                transplant(target, target.right);
            } else if (target.right == null) {
                // The node has no right child, replace it with the left child.
                transplant(target, target.left);
            } else {
                // The node has left and right children.
                // Let's find its successor because there's no intermediate key
                // in between the target key and the successor key.
                // (Of course, the predecessor is fine too).
                Node<T> successor = findSuccessor(target);
                if (successor.parent.equals(target)) {
                    // e.g.
                    //          /
                    //         5      <- The target to be removed.
                    //       /   \
                    //     1      [8] <- The successor of the target.
                    //   /  \       \    (must not have left children)
                    //  0    2       13
                    transplant(target, successor);

                    // Still need to take care of the n's children.
                    successor.left = target.left;
                    target.left.parent = successor;
                } else {
                    // e.g.
                    //          /
                    //         5          <- The target to be removed.
                    //       /   \
                    //     1       8
                    //   /  \     /  \
                    //  0    2  [6]  13   <- [6] is the successor of the target.
                    //            \          (must not have left children)
                    //             7
                    transplant(successor, successor.right);
                    transplant(target, successor);

                    // Still need to take care of the n's children.
                    target.left.parent = successor;
                    target.right.parent = successor;
                    successor.left = target.left;
                    successor.right = target.right;
                }
            }

            // Clear all the obsolete reference.
            target.left = target.right = target.parent = null;
            // Update the height.
            mIsHeightDirty = true;
            // Update the size.
            --mSize;
            if (mSize < 0) {
                throw new IllegalStateException("The size must not be negative.");
            }
        }
    }

    public boolean contains(T key) {
        return find(mRoot, key) != null;
    }

    public T min() {
        Node<T> m = min(mRoot);

        if (m != null) {
            return m.key;
        } else {
            return null;
        }
    }

    public T max() {
        Node<T> m = max(mRoot);

        if (m != null) {
            return m.key;
        } else {
            return null;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    protected void calcHeight() {
        mMaxHeight = 0;
        mMinHeight = mSize;

        if (mRoot != null) {
            Node<T> n = mRoot;
            HashSet<Node<T>> visited = new HashSet<>();
            Stack<Node<T>> s = new Stack<>();

            // Stack the starting node.
            s.push(n);
            while (!s.isEmpty()) {
                if (n.left != null && !visited.contains(n.left)) {
                    n = n.left;
                    s.push(n);
                } else if (n.right != null && !visited.contains(n.right)) {
                    n = n.right;
                    s.push(n);
                } else {
                    // Calc the max and min maxHeight.
                    if (n.left == null && n.right == null) {
                        mMaxHeight = Math.max(mMaxHeight, s.size());
                        mMinHeight = Math.min(mMinHeight, s.size());
                    }

                    // Add the visited set.
                    n = s.pop();
                    visited.add(n);

                    // Go the the parent.
                    n = n.parent;
                }
            }
        }

        mIsHeightDirty = false;
    }

    /**
     * The successor of a node is the the node with the smallest key greater
     * than the given key.
     */
    protected Node<T> findSuccessor(Node<T> node) {
        if (node == null) return null;

        // If the right children is present.
        if (node.right != null) {
            return min(node.right);
        }

        // The node might be one of the left children of an ancestor node, so
        // we need to traverse along the way up.
        Node<T> p = node.parent;
        while (p != null && node == p.right) {
            node = p;
            p = p.parent;
        }

        return p;
    }

    /**
     * The predecessor of a node is the node with the greatest key smaller
     * than the given key.
     */
    protected Node<T> findPredecessor(Node<T> node, T key) {
        if (node == null) return null;

        // If the left children is present.
        if (node.left != null) {
            return max(node.left);
        }

        // The node might be one of the right children of an ancestor node, so
        // we need to traverse along the way up.
        Node<T> p = node.parent;
        while (p != null && node == p.left) {
            node = p;
            p = p.parent;
        }

        return p;
    }

    /**
     * Brutally replace {@code from} with {@code to} no matter whether
     * {@code from} has one or two children.
     */
    protected void transplant(Node<T> from, Node<T> to) {
        if (from == null) return;

        if (from.parent == null) {
            // The "from" must be a root node;
            mRoot = to;
        } else if (from.equals(from.parent.left)) {
            // If "from" is its parent's left children.
            from.parent.left = to;
        } else if (from.equals(from.parent.right)) {
            // If "from" is its parent's right children.
            from.parent.right = to;
            from.left = null;
            from.right = null;
        }

        if (to != null) {
            to.parent = from.parent;
        }
    }

    protected Node<T> find(Node<T> node, T key) {
        if (node == null || key == null) return null;

        while (node != null) {
            if (key.equals(node.key)) {
                break;
            } else if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return node;
    }

    protected Node<T> min(Node<T> node) {
        if (node == null) return null;

        Node<T> n = node;
        while (n.left != null) {
            n = n.left;
        }

        return n;
    }

    protected Node<T> max(Node<T> node) {
        if (node == null) return null;

        Node<T> n = node;
        while (n.right != null) {
            n = n.right;
        }

        return n;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    protected static class Node<T extends Comparable<?>> {
        T key = null;

        Node<T> parent = null;

        Node<T> left = null;
        Node<T> right = null;

        Node(T key) {
            this(key, null);
        }

        Node(T key, Node<T> parent) {
            this.key = key;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            return key != null ?
                key.equals(node.key) :
                node.key == null;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }
}
