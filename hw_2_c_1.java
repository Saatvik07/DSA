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

public class hw_2_c_1 {
    static class particle_node {
        int parent, pos_neg, size;

        public particle_node(int parent, int pos_neg, int size) {
            this.parent = parent;
            this.pos_neg = pos_neg;
            this.size = size;
        }
    }

    static long gcd(long x, long y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }

    static void print_ans(int a, int b, long velocity, ArrayList<particle_node> setList, long[] arr_special_val) {
        if (setList.get(a).pos_neg != setList.get(b).pos_neg) {
            System.out.print("-");
        }
        long numerator = velocity * arr_special_val[a];
        long denominator = arr_special_val[b];
        long divide = gcd(numerator, denominator);
        System.out.println(numerator / divide + "/" + denominator / divide);

    }

    static void make_set_individual(int value, ArrayList<particle_node> setList) {
        particle_node n_node = new particle_node(value, 1, 1);
        setList.add(n_node);
    }

    static particle_node find_set_val(int value, ArrayList<particle_node> setList) {
        int copy = value;
        while (copy != setList.get(copy).parent) {
            copy = setList.get(copy).parent;
        }
        int copy_1 = value;
        // System.out.println(copy_1 + " " + copy + " " + value);
        while (copy_1 != copy) {
            int parent_1 = setList.get(copy_1).parent;
            setList.get(copy_1).parent = copy;
            copy_1 = parent_1;
        }
        return setList.get(value);
    }

    static void union(int value1, int value2, ArrayList<particle_node> setList, ArrayList<ArrayList<Integer>> adjList,
            int[] blocked_particles) {
        particle_node v1_parent = find_set_val(value1, setList);
        // System.out.println("Value1:" + value1 + " Parent:" + v1_parent.parent +
        // "Parity: " + v1_parent.pos_neg);
        particle_node v2_parent = find_set_val(value2, setList);
        // System.out.println("Value1:" + value2 + " Parent:" + v2_parent.parent +
        // "Parity: " + v2_parent.pos_neg);
        int value1_parent = v1_parent.parent, value2_parent = v2_parent.parent;
        if (setList.get(value1_parent).size < setList.get(value2_parent).size) {
            setList.get(value2_parent).size += setList.get(value1_parent).size;
            setList.get(value1_parent).parent = value2_parent;
            if (blocked_particles[value1_parent] == 1)
                blocked_particles[value2_parent] = 1;
            else {
                boolean different = false;
                int pos_neg_difference = setList.get(value2).pos_neg / setList.get(value2).pos_neg;
                if (setList.get(value1).pos_neg == setList.get(value2).pos_neg)
                    different = true;
                if (different)
                    setList.get(value1_parent).pos_neg = setList.get(value1_parent).pos_neg * (-pos_neg_difference);

                for (int child : adjList.get(value1_parent)) {
                    if (different)
                        setList.get(child).pos_neg = setList.get(child).pos_neg * (-pos_neg_difference);

                }
            }
            adjList.get(value2_parent).add(value1_parent);
            adjList.get(value2_parent).addAll(adjList.get(value1_parent));
        } else {
            setList.get(value1_parent).size += setList.get(value2_parent).size;
            setList.get(value2_parent).parent = value1_parent;
            if (blocked_particles[value2_parent] == 1)
                blocked_particles[value1_parent] = 1;
            else {
                boolean different = false;
                int pos_neg_difference = setList.get(value2).pos_neg / setList.get(value2).pos_neg;
                if (setList.get(value1).pos_neg == setList.get(value2).pos_neg)
                    different = true;
                if (different)
                    setList.get(value2_parent).pos_neg = setList.get(value2_parent).pos_neg * (-pos_neg_difference);

                for (int child : adjList.get(value2_parent)) {
                    if (different)
                        setList.get(child).pos_neg = setList.get(child).pos_neg * (-pos_neg_difference);
                }
            }
            adjList.get(value1_parent).add(value2_parent);
            adjList.get(value1_parent).addAll(adjList.get(value2_parent));
        }
    }

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int n = Reader.nextInt();
        int q = Reader.nextInt();
        long[] arr_special_val = new long[n + 1];
        int[] blocked_particles = new int[n + 1];
        ArrayList<particle_node> setList = new ArrayList<particle_node>(n + 1);
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(n + 1);
        for (int i = 0; i <= n; i++) {
            ArrayList<Integer> init = new ArrayList<Integer>();
            adjList.add(init);
        }
        setList.add(new particle_node(0, 0, 0));
        for (int i = 1; i <= n; i++) {
            arr_special_val[i] = Reader.nextInt();
            make_set_individual(i, setList);
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
                if (find_set_val(a, setList).parent != find_set_val(b, setList).parent) {
                    /*
                     * for (int i = 0; i < setList.size(); i++) { System.out.println("Set: " + i +
                     * "Parent: " + setList.get(i).parent + "Parity: " + setList.get(i).pos_neg +
                     * "Size:" + setList.get(i)); } System.out.println("Calling Union");
                     */
                    union(a, b, setList, adjList, blocked_particles);

                } else {
                    if (setList.get(a).pos_neg == setList.get(b).pos_neg) {
                        blocked_particles[find_set_val(a, setList).parent] = 1;
                    }
                }

            } else {
                int a = Reader.nextInt();
                int b = Reader.nextInt();
                String vel = Reader.next();
                Long vel_a = Long.parseLong(vel);
                if (find_set_val(a, setList).parent != find_set_val(b, setList).parent
                        || blocked_particles[find_set_val(a, setList).parent] == 1
                        || blocked_particles[find_set_val(b, setList).parent] == 1) {
                    System.out.println("0");
                } else {
                    print_ans(a, b, vel_a, setList, arr_special_val);

                }
            }
        }
    }
}