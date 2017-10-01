package com.my.myalgorithm.challenge;

import org.junit.Test;

import java.util.Locale;

/**
 * given 4 points, whose x and y coordinates are both integers. they are all
 * different. write a function to check if they form a square.
 * <p>
 * CareerCup: https://www.careercup.com/question?id=16425693
 */
public class Quiz_CheckGivenFourPointsFormRectangle {

    @Test
    public void givenPointsInCwOrder() {
        System.out.println(
            String.format(Locale.ENGLISH,
                          "%s a rectangle!",
                          (isRectangle(new float[][]{
                              new float[]{3f, 1f},
                              new float[]{3f, -2f},
                              new float[]{-1f, -2f},
                              new float[]{-1f, 1f},
                              }) ? "is" : "is NOT")));
    }

    @Test
    public void givenPointsInRandomOrder() {
        System.out.println(
            String.format(Locale.ENGLISH,
                          "%s a rectangle!",
                          (isRectangle(new float[][]{
                              new float[]{-1f, -2f},
                              new float[]{3f, -2f},
                              new float[]{3f, 1f},
                              new float[]{-1f, 1f},
                              }) ? "is" : "is NOT")));
    }

    @Test
    public void givenPointsNotFormARect() {
        System.out.println(
            String.format(Locale.ENGLISH,
                          "%s a rectangle!",
                          (isRectangle(new float[][]{
                              new float[]{-1f, -3f},
                              new float[]{6f, 1f},
                              new float[]{5f, -2f},
                              new float[]{-1f, 1f},
                              }) ? "is" : "is NOT")));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private boolean isRectangle(float[][] points) {
        if (points == null || points.length != 4) return false;

        System.out.println("Given points:");
        print(points);

        // Because the given points are in any order, we need to sort the points
        // in either CW or CCW order.
        //
        // For example:
        // 0 ---+ 2  , given:   [0, 1, 2, 3]
        // | \                 shortest
        // |  \                  +--+
        // |   \     , becomes: [0, 2, 1, 3]
        // |    \                +-----+
        // +     +               longest
        // 3      1
        float[][] sortedPoints = sortPoints(points);

        System.out.println("Sorted points:");
        print(sortedPoints);

        // If the four points form a rectangle, the corner 0 and 2 should be
        // 90 degrees.
        //
        // For example:
        // 0 ---> 1    0 ---> 3
        // |      ^    |      ^
        // |      | or |      |
        // v      |    v      |
        // 3 <--- 2    1 <--- 2
        final float[] sortedP0 = sortedPoints[0];
        final float[] sortedP1 = sortedPoints[1];
        final float[] sortedP2 = sortedPoints[2];
        final float[] sortedP3 = sortedPoints[3];

        // Time complexity: O(1).
        return dotProduct(new float[]{sortedP3[0] - sortedP0[0],
                                      sortedP3[1] - sortedP0[1]},
                          new float[]{sortedP1[0] - sortedP0[0],
                                      sortedP1[1] - sortedP0[1]}) == 0f &&
               dotProduct(new float[]{sortedP3[0] - sortedP2[0],
                                      sortedP3[1] - sortedP2[1]},
                          new float[]{sortedP1[0] - sortedP2[0],
                                      sortedP1[1] - sortedP2[1]}) == 0f;
    }

    private float[][] sortPoints(float[][] points) {
        // Time complexity: n.
        final float[][] sortedPoints = new float[points.length][];
        for (int i = 0, size = points.length; i < size; ++i) {
            sortedPoints[i] = new float[]{
                points[i][0],
                points[i][1]
            };
        }

        // Time complexity: log(1).
        final float[] p0 = points[0];
        final float[] p1 = points[1];
        float min = (p1[0] - p0[0]) * (p1[0] - p0[0]) +
                    (p1[1] - p0[1]) * (p1[1] - p0[1]);
        float max = min;
        for (int i = 2; i < 4; ++i) {
            final float[] px = sortedPoints[i];
            final float dx = px[0] - p0[0];
            final float dy = px[1] - p0[1];
            final float dist = dx * dx + dy * dy;

            if (dist < min) {
                // Shortest distance from px to p0:
                float x = px[0];
                float y = px[1];

                px[0] = sortedPoints[1][0];
                px[1] = sortedPoints[1][1];

                sortedPoints[1][0] = x;
                sortedPoints[1][1] = y;

                min = dist;
            } else if (dist > max) {
                // Longest distance from px to p0:
                float x = px[0];
                float y = px[1];

                px[0] = sortedPoints[2][0];
                px[1] = sortedPoints[2][1];

                sortedPoints[2][0] = x;
                sortedPoints[2][1] = y;

                max = dist;
            }
        }

        return sortedPoints;
    }

    private float dotProduct(float[] v1, float[] v2) {
        return v1[0] * v2[0] + v1[1] * v2[1];
    }

    private void print(float[][] points) {
        for (float[] point : points) {
            System.out.println(String.format(Locale.ENGLISH,
                                             "(x=%.3f), y=%.3f",
                                             point[0], point[1]));
        }
    }
}
