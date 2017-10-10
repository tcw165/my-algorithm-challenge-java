package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * <ul>
 *     <li>push(x) -- Push element x onto stack.
 *     <li>pop() -- Removes the element on top of the stack.
 *     <li>top() -- Get the top element.
 *     <li>getMin() -- Retrieve the minimum element in the stack.
 * </ul>
 * <p>
 * For example,
 * <pre>
 *   MinStack minStack = new MinStack();
 *   minStack.push(-2);
 *   minStack.push(0);
 *   minStack.push(-3);
 *   minStack.getMin();   --> Returns -3.
 *   minStack.pop();
 *   minStack.top();      --> Returns 0.
 *   minStack.getMin();   --> Returns -2.
 * </pre>
 * <p>
 * https://leetcode.com/problems/min-stack/description/
 */
public class QuizEasy_MinStack {

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        Assert.assertEquals(-3, minStack.getMin());

        minStack.pop();

        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.getMin());
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 23.87% on LeetCode.
     */
    private static class MinStack {

        private static final int CHUNK = 16;

        private int mSize = 0;
        private int[] mStore;

        private int[] mMinStore;

        public MinStack() {
            mStore = new int[CHUNK];
            mMinStore = new int[CHUNK];
        }

        public void push(int x) {
            if (mSize >= mStore.length) {
                int[] newStore = new int[mSize + CHUNK];
                int[] newMinStore = new int[mSize + CHUNK];

                System.arraycopy(mStore, 0, newStore, 0, mStore.length);
                System.arraycopy(mMinStore, 0, newMinStore, 0, mMinStore.length);

                mStore = newStore;
                mMinStore = newMinStore;
            }

            mStore[mSize] = x;

            if (mSize == 0) {
                mMinStore[mSize] = x;
            } else {
                mMinStore[mSize] = Math.min(mMinStore[mSize - 1], mStore[mSize]);
            }

            ++mSize;
        }

        public void pop() {
            if (mSize > 0) {
                --mSize;
            }
        }

        public int top() {
            if (mSize > 0) {
                return mStore[mSize - 1];
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public int getMin() {
            if (mSize > 0) {
                return mMinStore[mSize - 1];
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }
}
