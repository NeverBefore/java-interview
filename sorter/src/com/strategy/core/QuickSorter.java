package com.strategy.core;

import com.strategy.uitl.Sorter;

import java.util.Comparator;

/**
 * @author Admin
 * <p>
 * 快速排序
 * 快速排序是将序列划分为两个子序列，其中一个子序列的元素都小于轴距
 * 另一个子序列都大于或等于轴距，然后对子序列重复上面的方法
 * 直到子序列中只有一个元素为止。
 */
public class QuickSorter implements Sorter {

    @Override
    public <T extends Comparable<T>> void sort(T[] list) {
        quickSorter(list, 0, list.length - 1);
    }

    private <T extends Comparable<T>> void quickSorter(T[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSorter(list, first, pivotIndex - 1);
            quickSorter(list, pivotIndex, last);
        }
    }

    private <T extends Comparable<T>> int partition(T[] list, int first, int last) {
        T pivot = list[first];
        int low = first + 1;
        int high = last;
        while (high > low) {
            while (low <= high && list[low].compareTo(pivot) <= 0) {
                low++;
            }
            while (low <= high && list[high].compareTo(pivot) >= 0) {
                high--;
            }
            if (high > low) {
                T temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && list[high].compareTo(pivot) >= 0) {
            high--;
        }
        if (pivot.compareTo(list[high]) > 0) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return low;
        }
    }

    @Override
    public <T> void sort(T[] list, Comparator<T> comp) {
        quickSorter(list, 0, list.length - 1, comp);
    }

    private <T> void quickSorter(T[] list, int first, int last, Comparator<T> comp) {
        if (last > first) {
            int pivotIndex = partition(list, first, last, comp);
            quickSorter(list, first, pivotIndex - 1, comp);
            quickSorter(list, pivotIndex, last, comp);
        }
    }

    private <T> int partition(T[] list, int first, int last, Comparator<T> comp) {
        T pivot = list[first];
        int low = first + 1;
        int high = last;
        while (high > low) {
            while (low <= high && comp.compare(list[low], pivot) <= 0) {
                low++;
            }
            while (low <= high && comp.compare(list[high], pivot) >= 0) {
                high--;
            }
            if (high > low) {
                T temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && comp.compare(list[high], pivot) >= 0) {
            high--;
        }
        if (comp.compare(pivot, list[high]) > 0) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return low;
        }
    }
}
