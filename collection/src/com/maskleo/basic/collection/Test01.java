package com.maskleo.basic.collection;

import java.util.*;

/**
 * num = 4^a*5^b           输出 [a,b]
 * 1 =  4^0*5^0            输出 [0,0]
 * 4 =  4^1*5^0            输出 [1,0]
 * 5 =  4^0*5^1            输出 [0,1]
 * 6 =  4^0*5^0 + 4^0*5^1  输出 [0,0] [0,1] 或 6 =  4^1*5^0 + 4^0*5^0 + 4^0*5^0  输出 [0,0] [0,0] [1,0] 但是前面的组合只有2个长度 最后只输出 最短的
 */
public class Test01 {

    private static Map<Integer, Integer[]> allNode = new TreeMap<>((o1, o2) ->
            o2 - o1
    );

    public static void main(String[] args) {
        int num = 21;
        findAllSubNode(num, findMaxAB(num));
        Node root = new Node(null, num);
        buildTree(root, num);
        List<Node> subMinNodeList = new ArrayList<>();
        findMinLevel(subMinNodeList, root);
        for (Node subNode : subMinNodeList) {
            print(subNode);
            System.out.println();
        }
    }

    /**
     * 打印树的结果
     *
     * @param node
     */
    static void print(Node node) {
        if (node.level == 0) {
            return;
        }
        System.out.print(Arrays.asList(allNode.get(node.val)) + " ");
        print(node.parent);
    }

    static int minLevel = -1;

    /**
     * 查找最小树
     *
     * @param subMinNodeList
     * @param currNode
     */
    static void findMinLevel(List<Node> subMinNodeList, Node currNode) {
        List<Node> list = currNode.subNode;
        if (list.size() == 0) {
            if (minLevel == -1) {
                minLevel = currNode.level;
                subMinNodeList.add(currNode);
            } else {
                if (minLevel == currNode.level) {
                    subMinNodeList.add(currNode);
                } else if (currNode.level < minLevel) {
                    minLevel = currNode.level;
                    subMinNodeList.clear();
                    subMinNodeList.add(currNode);
                }
            }
            return;
        }
        for (Node node : list) {
            findMinLevel(subMinNodeList, node);
        }
    }

    /**
     * 构建树
     *
     * @param parent
     * @param num
     */
    static void buildTree(Node parent, int num) {
        if (num == 0) return;
        List<Integer> subNodeValList = findSubNodeVal(num, parent.parent == null ? num : parent.val);
        for (Integer val : subNodeValList) {
            Node node = new Node(parent, val);
            parent.subNode.add(node);
            buildTree(node, num - val);
        }
    }

    /**
     * 查找子节点允许的值
     *
     * @param num
     * @param parentNum
     * @return
     */
    static List<Integer> findSubNodeVal(int num, int parentNum) {
        List<Integer> valList = new ArrayList<>();
        for (Integer val : allNode.keySet()) {
            if (val <= num && val <= parentNum) {
                valList.add(val);
            }
        }
        return valList;
    }

    /**
     * 遍历出所有的子节点数字
     *
     * @param num
     */
    static void findAllSubNode(int num, Integer[] largestIndex) {
        for (int i = largestIndex[0]; i >= 0; i--) {
            for (int j = largestIndex[1]; j >= 0; j--) {
                int t = new Double(Math.pow(4, i) * Math.pow(5, j)).intValue();
                if (t <= num) {
                    allNode.put(t, new Integer[]{
                            i, j
                    });
                }
            }
        }
    }

    /**
     * 得到A,B最大的值
     *
     * @param num
     * @return
     */
    static Integer[] findMaxAB(int num) {
        return new Integer[]{findLargestIndex(4, num), findLargestIndex(5, num)};
    }

    /**
     * 求出最大的指数
     *
     * @param d
     * @param num
     * @return
     */
    static int findLargestIndex(int d, int num) {
        if (num == 1) return 0;
        int largestIndex = 0;
        int t = 1;
        while (t < num) {
            t = t * d;
            largestIndex++;
        }
        return largestIndex - 1;
    }

    private static class Node {

        Node parent;

        int level;

        int val;

        List<Node> subNode = new ArrayList<>();

        public Node(Node parent, int val) {
            this.parent = parent;
            if (parent != null)
                this.level = parent.level + 1;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "level=" + level +
                    ", val=" + val +
                    ", subNode=" + subNode +
                    '}';
        }
    }

}
