package huffmantree;

import java.util.PriorityQueue;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname HuffmanTree
 * @Description TODO
 * @Date 2019/12/30 16:39
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
    }

    public static Node createHuffmanTree(int[] arr) {
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        for (int valve : arr) {
            nodes.add(new Node(valve));
        }
        while (nodes.size() > 1) {
            Node leftNode = nodes.poll();
            Node rightNode = nodes.poll();
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.add(parent);
        }
        return nodes.poll();
    }
}

//为了让Node对象使用Collections集合排序，必须要让Node实现Comparable接口
class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
