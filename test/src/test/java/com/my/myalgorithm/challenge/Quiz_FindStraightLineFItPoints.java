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
import java.util.Locale;
import java.util.Stack;

/**
 * A museum was represented by a square matrix that was filled with O, G, and W
 * where O represented open space, G represented guards, and W represented walls.
 * Write a function that accepts the square matrix and returns another square
 * matrix where all of the O"s in the matrix are replaced with the number of
 * how many spaces they are away from a guard, without being able to go through
 * any walls.
 * <br/>
 * Reference:
 * <br/>
 * https://www.careercup.com/question?id=5722862468988928
 */
public class Quiz_FindStraightLineFItPoints {

    @Test
    public void answer1() throws Exception {
        // O O O
        // O W W
        // G O G
        //
        //   to
        //
        // 2 3 4
        // 1 W W
        // G 1 G
        String[][] input = new String[][]{
            {"O", "O", "O"},
            {"O", "W", "W"},
            {"G", "O", "G"}};
        String[][] output = markOpenedSpace(input);
        System.out.println("Input:");
        print(input);
        System.out.println("Output:");
        print(output);
        System.out.println(String.format(Locale.ENGLISH, "%d iteration.\n", mIterationCount));

        // O O O O
        // O W O G
        // O W O O
        // O G O O
        //
        //   to
        //
        // 4 3 2 1
        // 3 W 1 G
        // 2 W 2 1
        // 1 G 1 2
        input = new String[][]{
            {"O", "O", "O", "O"},
            {"O", "W", "O", "G"},
            {"O", "W", "O", "O"},
            {"O", "G", "O", "O"}};
        output = markOpenedSpace(input);
        System.out.println("Input:");
        print(input);
        System.out.println("Output:");
        print(output);
        System.out.println(String.format(Locale.ENGLISH, "%d iteration.\n", mIterationCount));

        // O W G O
        // O W W O
        // O W O O
        // O O O G
        //
        //   to
        //
        // 6 W G 1
        // 5 W W 2
        // 4 W 2 1
        // 3 2 1 G
        input = new String[][]{
            {"O", "W", "G", "O"},
            {"O", "W", "W", "O"},
            {"O", "W", "O", "O"},
            {"O", "O", "O", "G"}};
        output = markOpenedSpace(input);
        System.out.println("Input:");
        print(input);
        System.out.println("Output:");
        print(output);
        System.out.println(String.format(Locale.ENGLISH, "%d iteration.\n", mIterationCount));

        // O O G G G
        // O W O O O
        // O W G W O
        // O W W W O
        // O O O O O
        //
        //    to
        //
        // 2 1 G G G
        // 3 W 1 1 1
        // 4 W G W 2
        // 5 W W W 3
        // 6 7 6 5 4
        input = new String[][]{
            {"O", "O", "G", "G", "G"},
            {"O", "W", "O", "O", "O"},
            {"O", "W", "G", "W", "O"},
            {"O", "W", "W", "W", "O"},
            {"O", "O", "O", "O", "O"}};
        output = markOpenedSpace(input);
        System.out.println("Input:");
        print(input);
        System.out.println("Output:");
        print(output);
        System.out.println(String.format(Locale.ENGLISH, "%d iteration.\n", mIterationCount));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private int mIterationCount = 0;

    private static final String OPEN = String.valueOf("O");
    private static final String GUARD = String.valueOf("G");

    private String[][] markOpenedSpace(String[][] museum) {
        String[][] clone = new String[museum.length][museum.length];
        List<Position> guardPosList = new ArrayList<>();

        // DEBUG: Init the iteration counter.
        mIterationCount = 0;

        // Clone the space and find the "G".
        // Time complexity: O(n^2)
        for (int y = 0; y < museum.length; ++y) {
            for (int x = 0; x < museum.length; ++x) {
                // DEBUG: Accumulate the iteration counter.
                ++mIterationCount;

                clone[y][x] = museum[y][x];

                if (museum[y][x].equals(GUARD)) {
                    guardPosList.add(new Position(x, y));
                }
            }
        }

        // Search from a "G" to another "G".
        // Time complexity: maximum O(n^2)
        for (Position pos : guardPosList) {
            findGuard(clone, pos.x, pos.y, 0);
        }

        return clone;
    }

    private int findGuard(String[][] museum,
                          int x,
                          int y,
                          int step) {
        // Scan if the guard is adjacent. If so, stop the searching.
        int right = x + 1 < museum.length ? x + 1 : x;
        int left = x - 1 >= 0 ? x - 1 : x;
        int bottom = y + 1 < museum.length ? y + 1 : y;
        int top = y - 1 >= 0 ? y - 1 : y;
        if ((right != x && // right.
             museum[y][x + 1].equals(GUARD)) ||
            (bottom != y && // bottom.
             museum[y + 1][x].equals(GUARD)) ||
            (left != x &&  // left.
             museum[y][x - 1].equals(GUARD)) ||
            (top != y && // top.
             museum[y - 1][x].equals(GUARD))) {
            // There is a GUARD around, correct the suggested step away from
            // the GUARD.
            step = 1;
        }

        if (!museum[y][x].equals(GUARD)) {
            museum[y][x] = Integer.toString(step);
        }

        Stack<Position> todo = new Stack<>();
        // Try right one.
        if (right != x &&
            museum[y][right].equals(OPEN)) {
            todo.push(new Position(right, y));
        }
        // Try bottom one.
        if (bottom != y &&
            museum[bottom][x].equals(OPEN)) {
            todo.push(new Position(x, bottom));
        }
        // Try left one.
        if (left != x &&
            museum[y][left].equals(OPEN)) {
            todo.push(new Position(left, y));
        }
        // Try top one.
        if (top != y &&
            museum[top][x].equals(OPEN)) {
            todo.push(new Position(x, top));
        }

        while (!todo.isEmpty()) {
            Position next = todo.pop();

            // Compare the returned step with current step.
            // If the returned step is less than the current step, it means
            // that there is a shorter path and the current step should be
            // adjusted.
            int stepAwayFromGuard = findGuard(museum, next.x, next.y, step + 1);
            if (step > stepAwayFromGuard) {
                museum[y][x] = Integer.toString(stepAwayFromGuard);
                step = stepAwayFromGuard;
            }
        }

        // DEBUG: Accumulate the iteration counter.
        ++mIterationCount;

        return step + 1;
    }

    private void print(String[][] space) {
        for (int y = 0; y < space.length; ++y) {
            for (int x = 0; x < space.length; ++x) {
                System.out.print(space[y][x]);

                if (x < space.length - 1) {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (x != position.x) return false;
            return y == position.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
