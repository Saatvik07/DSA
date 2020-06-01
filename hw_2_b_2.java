import java.lang.*;
import java.util.*;

class adjNode {
    int a;
    long d;

    public adjNode(int a, long d) {
        this.a = a;
        this.d = d;
    }
}

class myComparator implements Comparator<adjNode> {
    public int compare(adjNode x, adjNode y) {
        return Long.compare(x.d, y.d);
    }
}

public class hw_2_b_2 {
    static PriorityQueue<adjNode> p_queue;

    public static long escape_demons(long val, int[] time_stamps) {
        int start = 0, end = time_stamps.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (time_stamps[mid] < val) {
                start = mid + 1;
            } else if (time_stamps[mid] > val) {
                end = mid - 1;
            } else {
                val = val + 1;
                start = 0;
                end = time_stamps.length - 1;
            }
        }
        return val;
    }

    /*
     * public static int greedy(int[] update, int[] included, int n) { int min =
     * Integer.MAX_VALUE; int min_index = -1; for (int i = 1; i <= n; i++) { if
     * (included[i] == 0 && update[i] <= min) { min = update[i]; min_index = i; } }
     * // System.out.println("Index to be added: " + min_index); return min_index; }
     */

    public static void sssp(ArrayList<ArrayList<adjNode>> adjList, int source, int n, HashMap<Integer, int[]> time,
            long[] update, int[] included) {
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
        while (p_queue.size() > 0) {
            /*
             * System.out.println("Init"); Iterator<adjNode> itr3 = p_queue.iterator();
             * while (itr3.hasNext()) { adjNode a = itr3.next(); System.out.println(a.a +
             * " " + a.d); }
             */

            adjNode extractedNode = p_queue.poll();

            int to_be_added = extractedNode.a;
            // System.out.println("Index to be added: " + to_be_added);
            included[to_be_added] = 1;
            for (adjNode child : adjList.get(to_be_added)) {
                int next_vert = child.a;
                // System.out.println("Child: " + child.a);
                if (child.d + update[to_be_added] < update[next_vert] && update[to_be_added] != Long.MAX_VALUE
                        && included[child.a] == 0) {
                    // System.out.println("F");
                    update[next_vert] = update[to_be_added] + child.d;
                    adjNode updated = new adjNode(child.a, update[next_vert]);
                    p_queue.add(updated);
                    int[] time_stamps = time.get(child.a);
                    if (time_stamps.length != 0 && child.a != n)
                        update[child.a] = escape_demons(update[child.a], time_stamps);
                }
                /*
                 * System.out.println("Exit"); Iterator<adjNode> itr4 = p_queue.iterator();
                 * while (itr4.hasNext()) { adjNode b = itr4.next(); System.out.println(b.a +
                 * " " + b.d); }
                 */

            }

        }
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        ArrayList<ArrayList<adjNode>> adjList = new ArrayList<ArrayList<adjNode>>();
        long[] update = new long[n + 1];
        int[] included = new int[n + 1];
        p_queue = new PriorityQueue<adjNode>(n, new myComparator());
        for (int i = 1; i <= n; i++) {
            update[i] = Long.MAX_VALUE;
            included[i] = 0;

        }
        for (int i = 0; i <= n; i++) {
            ArrayList<adjNode> init = new ArrayList<adjNode>();
            adjList.add(init);
        }
        for (int k = 0; k < m; k++) {
            int a = input.nextInt();
            int b = input.nextInt();
            int time = input.nextInt();
            adjNode new_node_1 = new adjNode(a, time);
            adjNode new_node_2 = new adjNode(b, time);
            adjList.get(a).add(new_node_2);
            adjList.get(b).add(new_node_1);
            /*
             * for (int i = 1; i <= n; i++) { System.out.print(i + ": "); for (int j = 0; j
             * < adjList.get(i).size(); j++) { System.out.print(adjList.get(i).get(j).a +
             * " " + adjList.get(i).get(j).d + "     "); } } System.out.println();
             */
        }

        HashMap<Integer, int[]> time = new HashMap<Integer, int[]>();
        for (int i = 1; i <= n; i++) {
            int k = input.nextInt();
            int[] arr = new int[k];
            for (int j = 0; j < k; j++) {
                arr[j] = input.nextInt();
            }
            time.put(i, arr);
        }
        long a = 0;
        update[1] = escape_demons(a, time.get(1));
        for (int i = 1; i <= n; i++) {
            adjNode n_node = new adjNode(i, update[i]);
            p_queue.add(n_node);
        }
        sssp(adjList, 1, n, time, update, included);
        for (int i = 1; i <= n; i++) {
            System.out.print(update[i] + " ");
        }
        /*
         * if (update[n] == Long.MAX_VALUE) { System.out.println("-1"); } else {
         * System.out.println(update[n]); }
         */

    }
}