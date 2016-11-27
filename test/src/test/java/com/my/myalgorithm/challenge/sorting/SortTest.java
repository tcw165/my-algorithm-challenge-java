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

package com.my.myalgorithm.challenge.sorting;

import com.my.myalgorithm.challenge.sorting.InsertionSort;
import com.my.myalgorithm.challenge.sorting.MergeSort;
import com.my.myalgorithm.challenge.sorting.QuickSort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SortTest {

    @Test
    public void insertionSortTest1() throws Exception {
        List<Integer> list = new ArrayList<>();

        list.add(5);
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(1);
        list.add(3);

        // Sort the elements in the ascending order.
        InsertionSort.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });

        for (int i = 1; i < list.size(); ++i) {
            assertTrue("Ascending order", list.get(i) > list.get(i - 1));
        }

        // Sort the elements in the descending order.
        InsertionSort.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return lhs - rhs;
            }
        });

        for (int i = 1; i < list.size(); ++i) {
            assertTrue("Descending order", list.get(i) < list.get(i - 1));
        }
    }

    @Test
    public void mergeSortTest1() throws Exception {
        List<Integer> list = new ArrayList<>();

        list.add(5);
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(1);
        list.add(3);

        // Sort the elements in the ascending order.
        MergeSort.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });

        for (int i = 1; i < list.size(); ++i) {
            assertTrue("Ascending order", list.get(i) > list.get(i - 1));
        }

        // Sort the elements in the descending order.
        MergeSort.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return lhs - rhs;
            }
        });

        for (int i = 1; i < list.size(); ++i) {
            assertTrue("Descending order", list.get(i) < list.get(i - 1));
        }
    }

    @Test
    public void quickSortTest1() throws Exception {
        List<Integer> list = new ArrayList<>();

        list.add(7);
        list.add(5);
        list.add(2);
        list.add(4);
        list.add(8);
        list.add(6);
        list.add(1);
        list.add(9);
        list.add(3);

        // Sort the elements in the ascending order.
        QuickSort.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });

        for (int i = 1; i < list.size(); ++i) {
            assertTrue("Ascending order", list.get(i) > list.get(i - 1));
        }

        // Sort the elements in the descending order.
        QuickSort.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return lhs - rhs;
            }
        });

        for (int i = 1; i < list.size(); ++i) {
            assertTrue("Descending order", list.get(i) < list.get(i - 1));
        }
    }
}
