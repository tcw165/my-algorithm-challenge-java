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

import android.util.Range;
import android.util.Size;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * <p/>
 * Reference:
 * <p/>
 * GeeksForGeeks: http://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
 */
public class QuizHard_InPlaceCcwRotateSquareMatrixBy90Degrees {

    @Test
    public void test() throws Exception {
        // Just make sure the two square matrix with exactly same values are
        // the same.
        Assert.assertEquals(new SquareMatrix(1, 2, 3, 4),
                            new SquareMatrix(1, 2, 3, 4));
        Assert.assertNotEquals(new SquareMatrix(1, 2, 3, 4),
                               new SquareMatrix(2, 3, 4, 5));

        // Rotate by 90 degrees.
        Assert.assertEquals(new SquareMatrix(2, 4,
                                             1, 3),
                            rotateMatrix(new SquareMatrix(1, 2,
                                                          3, 4)));
        Assert.assertEquals(new SquareMatrix(3, 6, 9,
                                             2, 5, 8,
                                             1, 4, 7),
                            rotateMatrix(new SquareMatrix(1, 2, 3,
                                                          4, 5, 6,
                                                          7, 8, 9)));
        Assert.assertEquals(new SquareMatrix(4, 8, 12, 16,
                                             3, 7, 11, 15,
                                             2, 6, 10, 14,
                                             1, 5,  9, 13),
                            rotateMatrix(new SquareMatrix( 1,  2,  3,  4,
                                                           5,  6,  7,  8,
                                                           9, 10, 11, 12,
                                                          13, 14, 15, 16)));
        Assert.assertEquals(new SquareMatrix(7, 14, 21, 28, 35, 42, 49,
                                             6, 13, 20, 27, 34, 41, 48,
                                             5, 12, 19, 26, 33, 40, 47,
                                             4, 11, 18, 25, 32, 39, 46,
                                             3, 10, 17, 24, 31, 38, 45,
                                             2,  9, 16, 23, 30, 37, 44,
                                             1,  8, 15, 22, 29, 36, 43),
                            rotateMatrix(new SquareMatrix( 1,  2,  3,  4,  5,  6,  7,
                                                           8,  9, 10, 11, 12, 13, 14,
                                                          15, 16, 17, 18, 19, 20, 21,
                                                          22, 23, 24, 25, 26, 27, 28,
                                                          29, 30, 31, 32, 33, 34, 35,
                                                          36, 37, 38, 39, 40, 41, 42,
                                                          43, 44, 45, 46, 47, 48, 49)));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats ?% on LeetCode.
     */
    private SquareMatrix rotateMatrix(SquareMatrix matrix) {
        if (matrix == null) return null;

        // Doing layers by layers, for example:
        //
        // [1]  2  [3]
        //  4   5   6
        // [7]  8  [9]
        //
        //      |
        //      V
        //
        // [3]  2  [9]
        //  4   5   6
        // [1]  8  [7]
        //
        //      |
        //      V
        //
        //  3  [2]  9
        // [4]  5  [6]
        //  1  [8]  7
        //
        //      |
        //      V
        //
        //  3  [6]  9
        // [2]  5  [8]
        //  1  [4]  7
        //
        //      |
        //      V
        //
        //  7   6   1
        //  2  [5]  8
        //  9   4   3

        // Time complexity: O(n), where n is the number of elements in the
        // matrix.
        int layer = 0;
        while (layer < matrix.size / 2) {
            final int from = layer;
            final int to = matrix.size - layer - 1;
            final int range = to - from;

            int i = 0;
            while (i < range) {
                // Cache left-top.
                int temp = matrix.valueAt(from + i, from);

                // Move right-top to left-top.
                matrix.setValueAt(
                    from + i, from,
                    matrix.valueAt(to, from + i));
                // Move right-bottom to right-top.
                matrix.setValueAt(
                    to, from + i,
                    matrix.valueAt(to - i, to));
                // Move left-bottom to right-bottom.
                matrix.setValueAt(
                    to - i, to,
                    matrix.valueAt(from, to - i));
                // Move left-top to left-bottom.
                matrix.setValueAt(from, to - i, temp);

                ++i;
            }

            ++layer;
        }

        return matrix;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class SquareMatrix {

        final int[] vals;
        final int size;

        SquareMatrix(int... vals) {
            this.size = (int) Math.sqrt(vals.length);
            this.vals = vals;
        }

        SquareMatrix(SquareMatrix other) {
            this.size = other.size;
            this.vals = new int[other.vals.length];
            System.arraycopy(other.vals, 0,
                             this.vals, 0, this.vals.length);
        }

        int valueAt(int col, int row) {
            if (col >= size || row >= size) {
                throw new IllegalArgumentException("Index out of bound.");
            }

            return this.vals[row * size + col];
        }

        void setValueAt(int col, int row, int val) {
            if (col >= size || row >= size) {
                throw new IllegalArgumentException("Index out of bound.");
            }

            this.vals[row * size + col] = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SquareMatrix that = (SquareMatrix) o;

            if (size != that.size) return false;
            return Arrays.equals(vals, that.vals);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(vals);
            result = 31 * result + size;
            return result;
        }
    }
}
