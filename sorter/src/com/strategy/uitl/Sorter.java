package com.strategy.uitl;

import java.util.Comparator;

/**
 * @author Admin
 * <p>
 * 策略模式：将算法封装到具有共同接口的独立的类中使得它们可以相互替换
 * 排序器接口
 */
public interface Sorter {

    /**
     * 排序
     * @param list  待排序的数组
     * @param <T>
     */
    <T extends Comparable<T>> void sort(T[] list);

    /**
     * 排序
     *
     * @param list  待排序的数组
     * @param comp  比较两个对象的比较器
     * @param <T>
     */
    <T> void sort(T[] list, Comparator<T> comp);
}
