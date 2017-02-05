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

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given coins of different denominations and a total amount of money
 * amount. Write a function to compute the fewest number of coins that you need
 * to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 * <br/>
 * <pre>
 * Example 1:
 *   coins = [1, 2, 5], amount = 11
 *   return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 *   coins = [2], amount = 3
 *   return -1.
 * </pre>
 * Reference:
 * <br/>
 * https://leetcode.com/problems/coin-change/
 */
public class Quiz_CoinChange {

    @Test
    public void answer() throws Exception {
        Assert.assertEquals(16, coinChange(new int[]{1, 2, 5}, 14));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private Map<CoinAndChangePair, Integer> mLookupsTable = new HashMap<>();

    /**
     * The solutions can be partitioned into two sets:
     * <br/>
     * 1) There are those sets that do not contain any coin[i].
     * <br/>
     * 2) Those sets that contain at least one coin[i].
     * <br/>
     * <pre>
     * Example:
     *
     *     | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14
     * ----+---+---+---+---+---+---+---+---+---+---+----+----+----+----+----
     *   1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |  1 |  1 |  1 |  1 |  1
     *   2 | 1 | 1 | 2 | 2 | 3 | 3 | 4 | 4 | 5 | 5 |  6 |  6 |  7 |  7 |  8
     *   5 | 1 | 1 | 2 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 10 | 11 | 13 | 14 | 16
     * </pre>
     */
    private int coinChange(int[] coins, int change) {
        // Make sure the coins array is sorted.
        List<Integer> sortedCoins = new ArrayList<>();
        for (int coin : coins) {
            sortedCoins.add(coin);
        }
        Collections.sort(sortedCoins, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return lhs - rhs;
            }
        });

        // Formula: F(coins[i], change) = F(coins[i - 1], change) +
        //                                F(coins[i], change - coins[i])

        return -1;
    }

    private int coinChange2(int[] coins, int i, int change) {
        return -1;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class CoinAndChangePair {

        int coin;
        int amount;

        public CoinAndChangePair(int coin, int amount) {
            this.coin = coin;
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CoinAndChangePair that = (CoinAndChangePair) o;

            if (coin != that.coin) return false;
            return amount == that.amount;
        }

        @Override
        public int hashCode() {
            int result = coin;
            result = 31 * result + amount;
            return result;
        }

        public static CoinAndChangePair valueOf(int coin,
                                                int amount) {
            return new CoinAndChangePair(coin, amount);
        }
    }
}
