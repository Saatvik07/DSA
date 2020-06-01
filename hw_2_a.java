import java.io.*;
import java.util.*;
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

class Node_1 {
    int a, time;

    public Node_1(int a, int time) {
        this.a = a;
        this.time = time;
    }
}

class myComparator_1 implements Comparator<Node_1> {
    public int compare(Node_1 x, Node_1 y) {
        return x.time - y.time;
    }
}

public class hw_2_a {
    static int ans;

    static class Node {
        int a, b, days_diff;

        public Node(int a, int b, int days_diff) {
            this.a = a;
            this.b = b;
            this.days_diff = days_diff;
        }
    }

    /*
     * static void helper(ArrayList<Node> Arr, int beg, int last, int mid) { int n_l
     * = mid - beg + 1, n_r = last - mid; ArrayList<Node> temp_l = new
     * ArrayList<Node>(); ArrayList<Node> temp_r = new ArrayList<Node>(); int x = 0,
     * y = 0, Arr_index = beg; for (int i = 0; i < n_l; i++) {
     * temp_l.add(Arr.get(beg + i)); } for (int i = 0; i < n_r; i++) {
     * temp_r.add(Arr.get(mid + 1 + i)); } while (x < n_l && y < n_r) { if
     * (temp_l.get(x).days_diff < temp_r.get(y).days_diff) { Arr.set(Arr_index,
     * temp_l.get(x)); x++; } else { Arr.set(Arr_index, temp_r.get(y)); y++; }
     * Arr_index++; } while (x < n_l) { Arr.set(Arr_index, temp_l.get(x)); x++;
     * Arr_index++; } while (y < n_r) { Arr.set(Arr_index, temp_r.get(y)); y++;
     * Arr_index++; }
     * 
     * }
     * 
     * static void recursive(ArrayList<Node> Arr, int beg, int last) { int mid; if
     * (beg < last) { mid = (beg + last) / 2; recursive(Arr, beg, mid);
     * recursive(Arr, mid + 1, last); helper(Arr, beg, last, mid); } }
     */

    static void make_set_individual(int value, int[] parent_arr, int size[]) {
        parent_arr[value] = value;
        size[value] = 1;
    }

    static int find_set_val(int value, int[] parent_arr) {
        if (value == parent_arr[value]) {
            return value;
        } else
            return find_set_val(parent_arr[value], parent_arr);
    }

    static int union(int value1, int value2, int[] parent_arr, int[] size) {
        int value1_parent = find_set_val(value1, parent_arr);
        int value2_parent = find_set_val(value2, parent_arr);
        if (size[value1_parent] < size[value2_parent]) {
            int temp = value1_parent;
            value1_parent = value2_parent;
            value2_parent = temp;
        }
        parent_arr[value2_parent] = value1_parent;
        if (size[value1_parent] == size[value2_parent])
            size[value1_parent]++;
        return value1_parent;
    }

    public static void sssp(ArrayList<ArrayList<Node_1>> adjList, int source, int n, int[] update, int[] included,
            PriorityQueue<Node_1> p_queue, int number_sets) {
        /*
         * for (int i = 1; i <= n - 1; i++) { int to_be_added = greedy(update, included,
         * n); included[to_be_added] = 1; for (adjNode child : adjList.get(to_be_added))
         * { // System.out.println("Parent: " + to_be_added + "Child: " + child.a + //
         * "Distance: " + child.d); if (included[child.a] == 0 && update[to_be_added] +
         * child.d < update[child.a] && update[to_be_added] != Integer.MAX_VALUE) {
         * update[child.a] = update[to_be_added] + child.d; int[] time_stamps =
         * time.get(child.a); if (time_stamps.length != 0) update[child.a] =
         * escape_demons(update[child.a], time_stamps); } } }
         */
        int ctr = 0;
        while (p_queue.size() > 0) {
            /*
             * System.out.println("Init"); Iterator<adjNode> itr3 = p_queue.iterator();
             * while (itr3.hasNext()) { adjNode a = itr3.next(); System.out.println(a.a +
             * " " + a.d); }
             */

            Node_1 extractedNode = p_queue.poll();

            int to_be_added = extractedNode.a;
            // System.out.println("Index to be added: " + to_be_added);
            included[to_be_added] = 1;
            for (Node_1 child : adjList.get(to_be_added)) {
                int next_vert = child.a;
                // System.out.println("Child: " + child.a);
                if (child.time + update[to_be_added] < update[next_vert] && update[to_be_added] != Integer.MAX_VALUE
                        && included[child.a] == 0) {
                    // System.out.println("F");
                    if (child.time != 0) {
                        number_sets--;
                    }
                    update[next_vert] = update[to_be_added] + child.time;
                    Node_1 updated = new Node_1(child.a, update[next_vert]);
                    if (number_sets == 0 && ctr == 0) {
                        ctr = 1;
                        ans = next_vert;
                    }
                    // System.out.println("Number of sets left:" + number_sets);
                    p_queue.add(updated);
                }
                /*
                 * System.out.println("Exit"); Iterator<adjNode> itr4 = p_queue.iterator();
                 * while (itr4.hasNext()) { adjNode b = itr4.next(); System.out.println(b.a +
                 * " " + b.d); }
                 */
            }

        }
    }

    public static void main(String args[]) throws IOException {
        Reader.init(System.in);
        int t = Reader.nextInt();
        for (int x = 0; x < t; x++) {
            int n = Reader.nextInt();
            int l = Reader.nextInt();
            int k = Reader.nextInt();
            int[] parent_arr = new int[n + 1];
            int[] size = new int[n + 1];
            int[] open = new int[n + 1];
            ArrayList<ArrayList<Node_1>> adj = new ArrayList<ArrayList<Node_1>>();
            for (int i = 0; i <= n; i++) {
                ArrayList<Node_1> init = new ArrayList<Node_1>();
                adj.add(init);
            }
            for (int i = 1; i < n + 1; i++) {
                make_set_individual(i, parent_arr, size);
            }
            for (int i = 0; i < l; i++) {
                int source = Reader.nextInt();
                int destination = Reader.nextInt();
                union(source, destination, parent_arr, size);
                Node_1 n_node;
                n_node = new Node_1(destination, 0);
                adj.get(source).add(n_node);
                n_node = new Node_1(source, 0);
                adj.get(destination).add(n_node);

            }
            HashMap<Integer, Integer> num_sets = new HashMap<Integer, Integer>();
            int ctr = 0;
            for (int i = 1; i < parent_arr.length; i++) {
                if (!num_sets.containsKey(parent_arr[i])) {
                    ctr++;
                    num_sets.put(parent_arr[i], 1);
                }
            }
            /*
             * for (int i = 1; i <= n; i++) { System.out.print(parent_arr[i] + " "); }
             */
            // System.out.println("Total sets:" + ctr);
            ArrayList<Node> days = new ArrayList<Node>();
            for (int i = 0; i < k; i++) {
                int u = Reader.nextInt();
                int v = Reader.nextInt();
                int w = Reader.nextInt();
                Node new_node = new Node(u, v, w);
                days.add(new_node);
            }
            /*
             * for (int i = 1; i <= n; i++) { System.out.print(i + ": "); for (int j = 0; j
             * < adj.get(i).size(); j++) { System.out.print(adj.get(i).get(j).a + " " +
             * adj.get(i).get(j).time + "     "); } } System.out.println();
             */
            for (int i = 0; i < k; i++) {
                int u = days.get(i).a;
                int v = days.get(i).b;
                int w = days.get(i).days_diff;
                Node_1 n_node_1 = new Node_1(u, w);
                Node_1 n_node_2 = new Node_1(v, w);
                if (parent_arr[u] != parent_arr[v]) {
                    adj.get(u).add(n_node_2);
                    adj.get(v).add(n_node_1);
                }
            }

            int[] update = new int[n + 1];
            int[] included = new int[n + 1];
            PriorityQueue<Node_1> p_queue = new PriorityQueue<Node_1>(n, new myComparator_1());
            for (int i = 1; i <= n; i++) {
                if (i == 1)
                    update[i] = 0;
                else
                    update[i] = Integer.MAX_VALUE;
                included[i] = 0;
                Node_1 n_node = new Node_1(i, update[i]);
                p_queue.add(n_node);
            }
            sssp(adj, 1, n, update, included, p_queue, ctr - 1);
            /*
             * for (int i = 1; i < n + 1; i++) { System.out.print(parent_arr[i] + " "); }
             */
            System.out.println(update[ans]);
        }
    }
}