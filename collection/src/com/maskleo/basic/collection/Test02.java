package com.maskleo.basic.collection;

import java.util.ArrayList;
import java.util.List;

public class Test02 {

    public static void main(String[] args) {
        int num = 555;
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        def(num, lists, list, 0);
        System.out.println(lists);
        System.out.println(list);
    }

    public static void def(int n, List<List<Integer>> lists, List<Integer> list, int m) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        if (sum == n) {
            lists.add(new ArrayList<>(list));
        }
        if (sum < n) {
            if (m == 0) {
                for (int i = 0; i < 5; i++) {
                    m = 1;
                    List<Integer> list4 = new ArrayList<>(list);
                    list4.add((int) Math.pow(4, i));
                    def(n, lists, list4, m);
                    List<Integer> list5 = new ArrayList<>(list);
                    list5.add((int) Math.pow(5, i));
                    def(n, lists, list5, m);
                }
            } else {
                for (int i = 1; i < 5; i++) {
                    List<Integer> list4 = new ArrayList<>(list);
                    list4.add((int) Math.pow(4, i));
                    def(n, lists, list4, m);
                    List<Integer> list5 = new ArrayList<>(list);
                    list5.add((int) Math.pow(5, i));
                    def(n, lists, list5, m);
                }
            }
        }
    }

    /**
     * public static void main(String[] args) {
     *         List<Integer> list = new ArrayList<>();
     *         List<List<Integer>> lists = new ArrayList<>();
     *         long startTime = System.currentTimeMillis();
     *         def(Integer.MAX_VALUE, lists, list);
     *         System.out.println(System.currentTimeMillis() - startTime +": ms");
     *         for (int i = 0; i < lists.size(); i++) {
     *             for (int j = 0; j < lists.get(i).size(); j++) {
     *                 System.out.print(lists.get(i).get(j) + " ");
     *             }
     *             System.out.println();
     *         }
     *     }
     *
     *     static int min = Integer.MAX_VALUE;
     *
     *     public static void def(int n, List<List<Integer>> lists, List<Integer> list) {
     *         int sum = 0;
     *         for (int i = 0; i < list.size(); i++) {
     *             sum += list.get(i);
     *         }
     *         if (sum == n) {
     *             min = list.size();
     *             lists.add(list);
     *         }
     *         if (sum < n && list.size() < min) {
     *             int i = 16;
     *             while (sum + Math.pow(4, i) > n) {
     *                 i--;
     *             }
     *             List<Integer> list4 = new ArrayList<>(list);
     *             list4.add((int) Math.pow(4, i));
     *             def(n, lists, list4);
     *             i = 15;
     *             while (sum + Math.pow(5, i) > n) {
     *                 i--;
     *             }
     *             List<Integer> list5 = new ArrayList<>(list);
     *             list5.add((int) Math.pow(5, i));
     *             def(n, lists, list5);
     *         }
     *     }
     */
}
