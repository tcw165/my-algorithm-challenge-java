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
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * <br/>
 * <pre>
 *
 *   11110
 *   11010
 *   11000
 *   00000
 *
 * Answer: 1
 *
 *   11000
 *   11000
 *   00100
 *   00011
 *
 * Answer: 3
 * </pre>
 * Reference:
 * <br/>
 * https://leetcode.com/problems/number-of-islands/
 */
public class Quiz_NumberOfIslands {

    @Test
    public void answer1() throws Exception {
        assertTrue(numIslands(new char[][]{
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        }) == 1);
        System.out.println(String.format(Locale.ENGLISH, "%d iteration.", mIterationCount));

        assertTrue(numIslands(new char[][]{
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        }) == 3);
        System.out.println(String.format(Locale.ENGLISH, "%d iteration.", mIterationCount));

        assertTrue(numIslands(new char[][]{
            {'1', '1', '0', '1', '1', '1', '0', '1'},
            {'1', '1', '1', '1', '0', '1', '0', '1'},
            {'0', '1', '0', '0', '0', '1', '1', '0'},
            {'1', '1', '0', '1', '1', '1', '1', '0'},
            {'1', '0', '0', '1', '1', '0', '0', '0'},
            {'1', '1', '0', '1', '1', '1', '0', '0'},
            {'0', '0', '1', '1', '1', '0', '1', '1'},
            {'1', '1', '0', '1', '1', '0', '1', '0'}
        }) == 4);
        System.out.println(String.format(Locale.ENGLISH, "%d iteration.", mIterationCount));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private int mIterationCount = 0;

    private int numIslands(char[][] grid) {
        List<Island> islands = new ArrayList<>();

        // DEBUG: Accumulation.
        mIterationCount = 0;

        for (int y = 0; y < grid.length; ++y) {
            for (int x = 0; x < grid[y].length; ++x) {
                if (Character.getNumericValue(grid[y][x]) == 0) continue;

                boolean isNew = true;
                for (Island island : islands) {
                    // DEBUG: Accumulation.
                    ++mIterationCount;

                    if (island.contains(x, y)) {
                        isNew = false;
                        break;
                    }
                }
                if (isNew) {
                    islands.add(floodFillIsland(new Island(),
                                                grid,
                                                x,
                                                y));
                }

                // DEBUG: Accumulation.
                ++mIterationCount;
            }
        }

        return islands.size();
    }

    private Island floodFillIsland(Island island,
                                   char[][] grid,
                                   int x,
                                   int y) {
        // Mark the position is part of the island.
        island.add(x, y);

        // Do BFS.
        Stack<Loc> todo = new Stack<>();

        // Check right one.
        int right = x + 1;
        if (right < grid[y].length &&
            Character.getNumericValue(grid[y][right]) > 0 &&
            !island.contains(right, y)) {
            todo.push(new Loc(right, y));
        }
        // Check bottom one.
        int bottom = y + 1;
        if (bottom < grid.length &&
            Character.getNumericValue(grid[bottom][x]) > 0 &&
            !island.contains(x, bottom)) {
            todo.push(new Loc(x, bottom));
        }
        // Check left one.
        int left = x - 1;
        if (left >= 0 &&
            Character.getNumericValue(grid[y][left]) > 0 &&
            !island.contains(left, y)) {
            todo.push(new Loc(left, y));
        }
        // Check top one.
        int top = y - 1;
        if (top >= 0 &&
            Character.getNumericValue(grid[top][x]) > 0 &&
            !island.contains(x, top)) {
            todo.push(new Loc(x, top));
        }

        while (!todo.isEmpty()) {
            // DEBUG: Accumulation.
            ++mIterationCount;

            Loc next = todo.pop();
            floodFillIsland(island, grid, next.x, next.y);
        }

        return island;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class Island {
        Set<Loc> area = new HashSet<>();

        void add(int x, int y) {
            area.add(new Loc(x, y));
        }

        boolean contains(int x, int y) {
            return area.contains(new Loc(x, y));
        }
    }

    private static class Loc {

        int x;
        int y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Loc loc = (Loc) o;

            if (x != loc.x) return false;
            return y == loc.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
