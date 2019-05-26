package com.maskleo.basic.collection;

import java.util.*;

public class Test03 {

    public static void main(String[] args) {
        int num = 33;
        Map<Integer, List<Pair>> cache = new HashMap<>();
        getMinGroup(num, cache);
        for (Map.Entry<Integer, List<Pair>> entry : cache.entrySet()) {
            //0,2 1,0 1,0  25 4 4  16 16 1
            System.out.println("key:" + entry.getKey() + ",val:" + entry.getValue());
        }
    }

    private static class Pair {
        int a;
        int b;
        int remain;

        public Pair(int a, int b, int remain) {
            this.a = a;//4的a次方
            this.b = b;//5的b次方
            this.remain = remain;//n-4^a*5^b
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "a=" + a +
                    ", b=" + b +
                    ", remain=" + remain +
                    '}';
        }
    }

    /**
     * 我的解题思路是，穷举得出单个4^a*5^b最逼近n的a,b组合，然后对余数进一步递归
     * 代码里X,Y两处递归的含义是，比如说33，4^2*5^0 余19，4^1*5^1 余13，4^0*5^2 余8，对于19，13，8这三个
     * 剩下来的数，我们需要继续处理，得到最短的组合
     * 其实最优解是用dp，但是我对算法也不了解，期待小明给出dp的解法
     *
     * @param n
     * @param cache 在操作过程中会反复递归到同一个数，此种情况下，不需要重复去求，用map缓存起来，时间换空间
     * @return
     */
    public static List<Pair> getMinGroup(int n, Map<Integer, List<Pair>> cache) {
        List<Pair> l = cache.get(n);
        if (l != null) {
            return l;
        }
        /**
         * n < 4的情况下，直接返回n个4^0*5^0
         */
        if (n < 4) {
            List<Pair> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(new Pair(0, 0, 0));
            }
            cache.put(n, list);
            return list;
        }
        int result = 0;
        TreeSet<List<Pair>> set = new TreeSet<>(new Comparator<List<Pair>>() {
            @Override
            public int compare(List<Pair> o1, List<Pair> o2) {
                return o1.size() - o2.size();
            }
        });
        A:
        for (int a = 0; ; a++) {
            result = (int) Math.pow(4, a);
            if (result == n) {
                Pair pair = new Pair(a, 0, 0);
                set.add(new ArrayList<Pair>() {{
                    add(pair);
                }});
                break;
            } else if (result > n) {

                Pair pair = new Pair(a - 1, 0, (n - (int) Math.pow(4, a - 1)));
                List<Pair> list = new ArrayList<>();
                list.add(pair);
                //X
                list.addAll(getMinGroup(pair.remain, cache));
                set.add(list);
                break A;
            }
            for (int b = 1; ; b++) {
                int result1 = result * (int) Math.pow(5, b);
                if (result1 < n) {
                    continue;
                } else if (result1 == n) {
                    Pair pair = new Pair(a, b, 0);
                    List<Pair> list = new ArrayList<>();
                    list.add(pair);
                    set.add(list);
                    break A;
                } else {
                    Pair pair = new Pair(a, b - 1, (int) (n - result1 / 5));
                    List<Pair> list = new ArrayList<>();
                    list.add(pair);
                    //Y
                    list.addAll(getMinGroup(pair.remain, cache));
                    set.add(list);
                    continue A;
                }
            }
        }
        cache.put(n, set.first());
        return set.first();
    }

    /**
     *      * 我的解题思路是，穷举得出单个4^a*5^b最逼近n的a,b组合，然后对余数进一步递归
     *      * 代码里X,Y两处递归的含义是，比如说33，4^2*5^0 余19，4^1*5^1 余13，4^0*5^2 余8，对于19，13，8这三个
     *      * 剩下来的数，我们需要继续处理，得到最短的组合
     *      * 其实最优解是用dp，但是我对算法也不了解，期待小明给出dp的解法
     *      *
     *      * @param n
     *      * @param cache 在操作过程中会反复递归到同一个数，此种情况下，不需要重复去求，用map缓存起来，时间换空间
     *      * @return
     *      */

    /**
     *
     *

    public static List<List<Pair>> getMinGroup(long n, Map<Long, List<List<Pair>>> cache) {
     *List<List<Pair>> l = cache.get(n);
     *if (l != null) {
     *return l;
     *}
     *         /**
         *          * n < 4的情况下，直接返回n个4^0*5^0
         *
     *if (n < 4L) {
     *List<Pair> list = new ArrayList<>();
     *for (int i = 0; i < n; i++) {
     *list.add(new Pair(0, 0));
     *}
     *List<List<Pair>> lists = cache.get(n);
     *if (lists == null) {
     *lists = new ArrayList<>();
     *cache.put(n, lists);
     *}
     *lists.add(list);
     *return lists;
     *}
     *long result = 0;
     *TreeMap<Integer, List<List<Pair>>> map = new TreeMap<>();
     *A:
     *for (int a = 0; ; a++) {
     *result = LongMath.pow(4L, a);
     *if (result == n) {
     *Pair pair = new Pair(a, 0);
     *map.put(1, new ArrayList<List<Pair>>() {{
     *add(new ArrayList<Pair>() {{
     *add(pair);
     *}});
     *}});
     *break;
     *} else if (result > n) {
     *Pair pair = new Pair(a - 1, 0);
     *                 //X
     *Long remain = n - LongMath.pow(4L, a - 1);
     *List<List<Pair>> lists = cache.get(remain);
     *if (lists == null) {
     *lists = getMinGroup(remain, cache);
     *}
     *for (List<Pair> pairList : lists) {
     *ArrayList<Pair> arrayList = new ArrayList<>();
     *arrayList.add(pair);
     *arrayList.addAll(pairList);
     *List<List<Pair>> lists1 = map.get(arrayList.size());
     *if (lists1 == null) {
     *lists1 = new ArrayList<>();
     *map.put(arrayList.size(), lists1);
     *}
     *lists1.add(arrayList);
     *}
     *break A;
     *}
     *for (int b = 1; ; b++) {
     *long result1 = result * LongMath.pow(5, b);
     *if (result1 < n) {
     *continue;
     *} else if (result1 == n) {
     *Pair pair = new Pair(a, b);
     *List<Pair> list = new ArrayList<>();
     *list.add(pair);
     *map.put(1, new ArrayList<List<Pair>>() {{
     *add(new ArrayList<Pair>() {{
     *add(pair);
     *}});
     *}});
     *break A;
     *} else {
     *                     /**
                     *                      * 此处优化比较关键，因为如果b是1，b-1=0,那么进Y处递归执行的内容
                     *                      * 与外层循环执行的内容是相同的
                     *
     *if (b > 1) {
     *Pair pair = new Pair(a, b - 1);
     *                         //Y
     *Long remain = n - result1 / 5;
     *List<List<Pair>> lists = cache.get(remain);
     *if (lists == null) {
     *lists = getMinGroup(remain, cache);
     *}
     *for (List<Pair> pairList : lists) {
     *ArrayList<Pair> arrayList = new ArrayList<>();
     *arrayList.add(pair);
     *arrayList.addAll(pairList);
     *List<List<Pair>> lists1 = map.get(arrayList.size());
     *if (lists1 == null) {
     *lists1 = new ArrayList<>();
     *map.put(arrayList.size(), lists1);
     *}
     *lists1.add(arrayList);
     *}
     *}
     *break;
     *}
     *}
     *}
     *List<List<Pair>> resultLists = map.pollFirstEntry().getValue();
     *cache.put(n, resultLists);
     *return resultLists;
     *}
     */
}
