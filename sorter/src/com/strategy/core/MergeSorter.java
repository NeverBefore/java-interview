package com.strategy.core;

import com.strategy.uitl.Sorter;
import com.sun.scenario.effect.Merge;

import java.util.Comparator;

/**
 * @author Admin
 * <p>
 * 归并排序
 * 归并排序是建立在归并操作上的一种有效的排序算法
 * 先将待排序的序列划分成一个一个的元素，再进行两两归并
 */
public class MergeSorter implements Sorter {

    @Override
    public <T extends Comparable<T>> void sort(T[] list) {

        T[] temp = (T[]) new Comparable[list.length];
        mSort(list, temp, 0, list.length - 1);
    }

    private <T extends Comparable<T>> void mSort(T[] list, T[] temp, int low, int high) {
        if (low == high) {
            return;
        } else {
            int mid = low + ((high - low) >> 1);
            mSort(list, temp, low, mid);
            mSort(list, temp, mid + 1, high);
            merge(list, temp, low, mid + 1, high);
        }
    }

    private <T extends Comparable<T>> void merge(T[] list, T[] temp, int left, int right, int last) {
        int j = 0;
        int lowIndex = left;
        int mid = right - 1;
        int n = last - lowIndex + 1;
        while (left <= mid && right <= last) {
            if (list[left].compareTo(list[right]) < 0) {
                temp[j++] = list[left++];
            } else {
                temp[j++] = list[right];
            }
        }

        while (left <= mid) {
            temp[j++] = list[left++];
        }

        while (right <= last) {
            temp[j++] = list[right++];
        }
        for (j = 0; j < n; j++) {
            list[lowIndex + j] = temp[j];
        }

    }

    @Override
    public <T> void sort(T[] list, Comparator<T> comp) {
        T[] temp = (T[]) new Comparable[list.length];
        mSort(list, temp, 0, list.length - 1, comp);

    }

    private <T> void mSort(T[] list, T[] temp, int low, int high, Comparator<T> comp) {
        if (low == high) {
            return;
        } else {
            int mid = low + ((high - low) >> 1);
            mSort(list, temp, low, mid, comp);
            mSort(list, temp, mid + 1, high, comp);
            merge(list, temp, low, mid + 1, high, comp);
        }
    }

    private <T> void merge(T[] list, T[] temp, int left, int right, int last, Comparator<T> comp) {

        int j = 0;
        int lowIndex = left;
        int mid = right - 1;
        int n = last - lowIndex + 1;
        while (left <= mid && right <= last) {
            if (comp.compare(list[left], list[right]) < 0) {

                temp[j++] = list[left++];
            } else {
                temp[j++] = list[right];
            }
        }

        while (left <= mid) {
            temp[j++] = list[left++];
        }

        while (right <= last) {
            temp[j++] = list[right++];
        }
        for (j = 0; j < n; j++) {
            list[lowIndex + j] = temp[j];
        }
    }
}
