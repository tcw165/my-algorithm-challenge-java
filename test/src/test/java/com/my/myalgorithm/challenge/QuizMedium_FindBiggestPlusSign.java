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

import java.util.Locale;

import static junit.framework.Assert.assertTrue;

/**
 * Finding biggest plus sign "+" in a sparse matrix (matrix with elements 0
 * and 1)
 * <br/>
 * e.g
 * <pre>
 * the biggest plus sign for following matrix is located at (2,2), with length
 * 1 for each edge (Yes, each edge should have same length)
 *
 * 0 0 1 0 0 1 0
 * 1 0 1 0 1 0 1
 * 1 1 1 1 1 1 1
 * 0 0 1 0 0 0 0
 * 0 0 0 0 0 0 0
 * </pre>
 * Reference:
 * <br/>
 * https://www.careercup.com/question?id=5653583535013888
 */
public class QuizMedium_FindBiggestPlusSign {

    /**
     * Using BFS/DFS.
     */
    @Test
    public void test1() throws Exception {
        PlusSign maxSign;

        // Case 1.
        maxSign = findBiggestPlusSignBFS(new int[][]{{1, 0, 1, 1, 1, 1},
                                                     {0, 1, 1, 1, 1, 1},
                                                     {0, 1, 1, 0, 1, 1},
                                                     {0, 0, 1, 1, 1, 1},
                                                     {1, 0, 1, 1, 0, 0}});
        if (maxSign != null) {
            System.out.println(maxSign);
            assertTrue(maxSign.x == 2 && maxSign.y == 1 && maxSign.length == 1);
        }

        // Case 2.
        maxSign = findBiggestPlusSignBFS(new int[][]{{0, 0, 1, 0, 0, 1, 0},
                                                     {1, 0, 1, 0, 1, 0, 1},
                                                     {1, 1, 1, 1, 1, 1, 1},
                                                     {0, 0, 1, 0, 0, 0, 0},
                                                     {0, 0, 0, 0, 0, 0, 0}});
        if (maxSign != null) {
            System.out.println(maxSign);
            assertTrue(maxSign.x == 2 && maxSign.y == 2 && maxSign.length == 1);
        }
    }

    public PlusSign findBiggestPlusSignBFS(int[][] matrix) {
        // TODO: Find the center of the matrix.

        // TODO: Construct a 4-ary tree from the center so that the valid elements
        // TODO: located on the border are the leafs, and it may speed up a lot.

        // TODO: Do BFS to find the biggest plus sign.


        PlusSign maxSign = new PlusSign();

        // Skip the elements located on the border.
        for (int row = 1; row < matrix.length - 1; ++row) {
            for (int col = 1; col < matrix[row].length - 1; ++col) {
                // Do BFS.
                int length = findBiggestPlusSignBFS(matrix,
                                                    row,
                                                    col);

                if (length > maxSign.length) {
                    maxSign.x = col;
                    maxSign.y = row;
                    maxSign.length = length;
                }
            }
        }

        return maxSign;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private int findBiggestPlusSignBFS(int[][] matrix, int row, int col) {
        if (matrix[row][col] == 0) return 0;

        int h = matrix.length;
        int w = matrix[0].length;
        int hLen = Math.min(row, w - row) + 1;
        int wLen = Math.min(col, h - col) + 1;
        int len = Math.min(hLen, wLen);

        int maxLen = 0;
        for (int l = 1; l < len; ++l) {
            // Check horizontal and vertical direction.
            int left = matrix[row][col - l];
            int top = matrix[row - l][col];
            int right = matrix[row][col + l];
            int bottom = matrix[row + l][col];

            if (left > 0 && top > 0 && right > 0 && bottom > 0) {
                maxLen = l;
            } else {
                break;
            }
        }

        return maxLen;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    public static class PlusSign {
        int x;
        int y;
        int length;

        public PlusSign() {
            this.x = 0;
            this.y = 0;
            this.length = 0;
        }

        @Override
        public String toString() {
            return String.format(Locale.ENGLISH,
                                 "Max plus sign is at (%d,%d) with length=%d",
                                 this.x,
                                 this.y,
                                 this.length);
        }
    }
}
