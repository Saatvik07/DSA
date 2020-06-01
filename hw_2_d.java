import java.util.*;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** Class for buffered reading int and double values */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream Reader) {
        reader = new BufferedReader(new InputStreamReader(Reader));
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            // TODO add check for eof if necessary
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}

class Queue_self {
    Node front;
    Node rear;
    int size = 0;

    static class Node {
        int d;
        Node n_link;

        Node(int d) {
            this.d = d;
            this.n_link = null;
        }
    }

    public static Queue_self insert(Queue_self q, int p) {
        Node n_node = new Node(p);
        if (q.front == null && q.rear == null) {
            q.front = n_node;
            q.rear = n_node;
            q.size++;
        } else {
            q.rear.n_link = n_node;
            q.rear = n_node;
            q.size++;
        }
        return q;
    }

    public static int delete(Queue_self q) {
        int val = -1;
        if (q.front == q.rear) {
            val = q.front.d;
            q.front = null;
            q.rear = null;
            q.size = 0;
        } else {
            val = q.front.d;
            q.front = q.front.n_link;
            q.size--;
        }
        return val;
    }
}

public class hw_2_d {
    static int bfs(ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> length_road, int[] infected, int start) {
        int farthest = 0;
        Queue_self bfs_queue = new Queue_self();
        bfs_queue.insert(bfs_queue, start);
        length_road.set(start, 0);
        while (bfs_queue.size > 0) {
            int curr = bfs_queue.delete(bfs_queue);
            if (infected[curr] == 1)
                farthest = curr;
            for (int child : adjList.get(curr)) {
                // System.out.println("Child: " + child);
                if (length_road.get(child) == Integer.MIN_VALUE) {
                    length_road.set(child, length_road.get(curr) + 1);
                    bfs_queue.insert(bfs_queue, child);
                }
            }

        }
        return farthest;
    }

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int n = Reader.nextInt();
        int infected_n = Reader.nextInt();
        int max_dist = Reader.nextInt();
        ArrayList<Integer> length_road = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        length_road.add(0);
        adjList.add(new ArrayList<Integer>());
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
            length_road.add(Integer.MIN_VALUE);
        }
        int[] infected = new int[n + 1];
        for (int i = 0; i < infected_n; i++) {
            int inf = Reader.nextInt();
            infected[inf] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            int a = Reader.nextInt();
            int b = Reader.nextInt();
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        int first_far = bfs(adjList, length_road, infected, 1);
        // System.out.println("Creed");
        for (int i = 0; i < n + 1; i++) {
            length_road.set(i, Integer.MIN_VALUE);
            // System.out.println("Value: " + i + " Distance_1: " + length_road.get(i));
        }
        int second_far = bfs(adjList, length_road, infected, first_far);
        int[] distance_first_far = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            distance_first_far[i] = length_road.get(i);
        }
        for (int i = 0; i < n + 1; i++) {
            length_road.set(i, Integer.MIN_VALUE);
            // System.out.println("Value: " + i + " Distance_1: " + length_road.get(i));
        }
        bfs(adjList, length_road, infected, second_far);
        int[] distance_second_far = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            distance_second_far[i] = length_road.get(i);
        }
        int ctr = 0;
        for (int i = 1; i < n + 1; i++) {
            if (distance_first_far[i] <= max_dist && distance_second_far[i] <= max_dist)
                ctr++;
        }
        System.out.println(ctr);
    }
}