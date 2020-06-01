import java.io.*;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

public class hw_2_a_1 {
    static class adjNode {
        int a, d;

        public adjNode(int a, int d) {
            this.a = a;
            this.d = d;
        }
    }

    public static long bfs(ArrayList<ArrayList<adjNode>> adjList, int[] visited, Queue<Integer> queue_vert, int curr) {
        if (visited[curr] == 0) {
            visited[curr] = 1;
        }
        long min = 0;
        for (adjNode child : adjList.get(curr)) {
            if (visited[child.a] == 0) {
                queue_vert.add(child.a);
                if (child.d > min) {
                    min = child.d;
                }
            }

        }
        queue_vert.poll();
        if (queue_vert.size() != 0) {
            return min + bfs(adjList, visited, queue_vert, queue_vert.peek());
        } else {
            return min;
        }

    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        for (int x = 0; x < t; x++) {
            int n = input.nextInt();
            int l = input.nextInt();
            int k = input.nextInt();
            int[] visited = new int[n + 1];
            ArrayList<ArrayList<adjNode>> adjList = new ArrayList<ArrayList<adjNode>>();
            ArrayList<adjNode> init = new ArrayList<adjNode>();
            Queue<Integer> queue_vert = new LinkedList<>();
            for (int i = 0; i <= n; i++) {
                adjList.add(init);
            }
            for (int i = 0; i < l; i++) {
                int source = input.nextInt();
                int destination = input.nextInt();
                adjNode new_node_1 = new adjNode(source, 0);
                adjNode new_node_2 = new adjNode(destination, 0);
                adjList.get(source).add(new_node_2);
                adjList.get(destination).add(new_node_1);
            }
            for (int i = 0; i < k; i++) {
                int u = input.nextInt();
                int v = input.nextInt();
                int w = input.nextInt();
                adjNode new_node_1 = new adjNode(u, w);
                adjNode new_node_2 = new adjNode(v, w);
                adjList.get(u).add(new_node_2);
                adjList.get(v).add(new_node_1);

            }
            queue_vert.add(1);
            long ans = bfs(adjList, visited, queue_vert, 1);
            System.out.println(ans);
        }
    }
}
1 2 4
1 8 8
2 8 11
2 3 8
8 9 7
8 7 1
3 9 2
9 7 6
3 4 7
3 6 4
7 6 2
4 6 14
4 5 9
6 5 10