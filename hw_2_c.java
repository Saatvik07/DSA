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

public class hw_2_c {

    static long gcd(long x, long y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }

    static void print_ans(int a, int b, int velocity, int[] pos_neg, long[] arr_special_val) {
        if (pos_neg[a] != pos_neg[b]) {
            System.out.print("-");
        }
        long numerator = Math.abs(velocity * arr_special_val[a]);
        long denominator = Math.abs(arr_special_val[b]);
        long divide = gcd(numerator, denominator);
        long num = numerator / divide;
        long den = denominator / divide;
        System.out.println(num + "/" + den);
    }

    static void make_set_individual(int value, int[] parent_arr, int[] size, int[] pos_neg) {
        parent_arr[value] = value;
        size[value] = 1;
        pos_neg[value] = 1;
    }

    static int find_set_val(int value, int[] parent_arr) {
        if (value == parent_arr[value]) {
            return value;
        } else {
            return parent_arr[value] = find_set_val(parent_arr[value], parent_arr);
        }
    }

    static void union(int value1, int value2, int[] parent_arr, int[] size, int[] pos_neg,
            ArrayList<ArrayList<Integer>> adjList, int[] blocked_particles) {
        int v1_parent = find_set_val(value1, parent_arr);
        int v2_parent = find_set_val(value2, parent_arr);

        /*
         * for (int i = 1; i < parent_arr.length; i++) { System.out .println("Value: " +
         * i + "Parent: " + parent_arr[i] + "Parity: " + pos_neg[i] + "Size:" +
         * size[i]); }
         */
        if (size[v1_parent] < size[v2_parent]) {
            parent_arr[v1_parent] = v2_parent;
            size[v2_parent] += size[v2_parent];
            if (blocked_particles[v1_parent] == 1)
                blocked_particles[v2_parent] = 1;
            else {
                int pos_neg_difference = pos_neg[value2] / pos_neg[value1];
                pos_neg[v1_parent] = pos_neg[v1_parent] * (-pos_neg_difference);
                for (int i = 0; i < adjList.get(v1_parent).size(); i++) {
                    pos_neg[adjList.get(v1_parent).get(i)] *= (-pos_neg_difference);
                }
            }
            adjList.get(v2_parent).add(v1_parent);
            adjList.get(v2_parent).addAll(adjList.get(v1_parent));
        } else {
            parent_arr[v2_parent] = v1_parent;
            size[v1_parent] += size[v2_parent];
            if (blocked_particles[v2_parent] == 1) {
                blocked_particles[v1_parent] = 1;
            } else {
                int pos_neg_difference = pos_neg[value2] / pos_neg[value1];
                pos_neg[v2_parent] = pos_neg[v2_parent] * (-pos_neg_difference);
                for (int child : adjList.get(v2_parent)) {
                    pos_neg[child] = pos_neg[child] * (-pos_neg_difference);
                }
            }
            adjList.get(v1_parent).add(v2_parent);
            adjList.get(v1_parent).addAll(adjList.get(v2_parent));
        }
        /*
         * for (int i = 0; i < setList.size(); i++) { System.out.println("Value: " + i +
         * "Parent: " + setList.get(i).parent + "Parity: " + setList.get(i).pos_neg +
         * "Size:" + setList.get(i)); }
         */
    }

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int n = Reader.nextInt();
        int q = Reader.nextInt();
        long[] arr_special_val = new long[n + 1];
        int[] blocked_particles = new int[n + 1];
        int[] parent_arr = new int[n + 1];
        int[] size = new int[n + 1];
        int[] pos_neg = new int[n + 1];
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        adjList.add(new ArrayList<Integer>());
        for (int i = 1; i <= n; i++) {
            arr_special_val[i] = Reader.nextInt();
            make_set_individual(i, parent_arr, size, pos_neg);
            adjList.add(new ArrayList<Integer>());
        }
        for (int j = 0; j < q; j++) {
            int type = Reader.nextInt();
            if (type == 1) {
                int to_be_changed = Reader.nextInt();
                String change = Reader.next();
                Long changed = Long.parseLong(change);
                arr_special_val[to_be_changed] = changed;
            } else if (type == 2) {
                int a = Reader.nextInt();
                int b = Reader.nextInt();
                if (find_set_val(a, parent_arr) != find_set_val(b, parent_arr)) {
                    /*
                     * for (int i = 0; i < setList.size(); i++) { System.out.println("Set: " + i +
                     * "Parent: " + setList.get(i).parent + "Parity: " + setList.get(i).pos_neg +
                     * "Size:" + setList.get(i)); } System.out.println("Calling Union");
                     */
                    union(a, b, parent_arr, size, pos_neg, adjList, blocked_particles);
                    /*
                     * for (int i = 1; i < adjList.size(); i++) { System.out.print(i + " "); for
                     * (int k = 0; k < adjList.get(i).size(); k++) {
                     * System.out.print(adjList.get(i).get(k) + " "); } System.out.println(); }
                     */
                } else {
                    if (pos_neg[a] == pos_neg[b]) {
                        int parent = find_set_val(a, parent_arr);
                        blocked_particles[parent] = 1;
                    }
                }
            } else {
                int a = Reader.nextInt();
                int b = Reader.nextInt();
                int vel_a = Reader.nextInt();
                if (find_set_val(a, parent_arr) != find_set_val(b, parent_arr)
                        || blocked_particles[find_set_val(a, parent_arr)] == 1
                        || blocked_particles[find_set_val(b, parent_arr)] == 1) {
                    System.out.println("0");
                } else {
                    print_ans(a, b, vel_a, pos_neg, arr_special_val);
                }
            }
        }
    }
}