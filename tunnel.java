import java.util.*;
import java.lang.*;
import java.io.*;

public class tunnel {
    ArrayList<normal_node>[] two_d_arr_tunnels;
    node[] list_tunnels;
    int[] tunnels_supply;
    int parent_arr[][];
    long dp_table[][];
    long[] level;
    int[] height;
    long[] first_supply_node;

    static class node {
        int a, b, dist;
        boolean supply;

        public node(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
            this.supply = false;
        }
    }

    static class normal_node {
        int destination, distance, path_no;
        boolean supply;

        public normal_node(int dest, int dis, boolean s, int p) {
            this.destination = dest;
            this.distance = dis;
            this.supply = s;
            this.path_no = p;
        }
    }

    public tunnel(int n) {
        two_d_arr_tunnels = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            two_d_arr_tunnels[i] = new ArrayList<normal_node>();
        }
        list_tunnels = new node[n];
        tunnels_supply = new int[n];
        parent_arr = new int[n + 1][18];
        dp_table = new long[n + 1][19];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 18; j++) {
                parent_arr[i][j] = -1;
                dp_table[i][j] = -1;
            }
        }
        for (int i = 0; i <= n; i++) {
            dp_table[i][18] = -1;
        }
        level = new long[n + 1];
        height = new int[n + 1];
        height[0] = 0;
        first_supply_node = new long[n + 1];
    }

    static int value = 1;

    public static void dfs(tunnel network, int x, int parent, long sum, long min) {
        network.height[x] = network.height[parent] + 1;
        network.parent_arr[x][0] = parent;
        network.level[x] = sum;
        for (normal_node child : network.two_d_arr_tunnels[x]) {
            if (child.destination == parent)
                continue;
            if (child.supply == true && child.distance < min) {
                min = child.distance;
                network.dp_table[x][0] = min;
                network.first_supply_node[x] = min;
            }
            dfs(network, child.destination, x, sum + child.distance, (long) Math.pow(10, 18));
        }

    }

    public static void pre(tunnel network, int n) {
        for (int i = 1; i < 18; i++) {
            for (int j = 1; j <= n; j++) {
                if (network.parent_arr[j][i - 1] != -1)
                    network.parent_arr[j][i] = network.parent_arr[network.parent_arr[j][i - 1]][i - 1];
            }
        }
    }

    public static void pre_1(tunnel network, int n, int[] arr, int e) {
        for (int i = 1; i <= n; i++) {
            if (network.first_supply_node[i] == 0 && arr[i] != 1)
                network.first_supply_node[i] = (long) Math.pow(10, 18);
            if (arr[i] == 1)
                network.dp_table[i][0] = 0;
            else if (network.dp_table[i][0] == -1) {
                network.dp_table[i][0] = (long) Math.pow(10, 18);
            }
        }
        for (int i = 1; i < 19; i++) {
            for (int j = 1; j <= n; j++) {
                if (network.parent_arr[j][i - 1] != -1) {
                    network.dp_table[j][i] = Math.min(network.dp_table[network.parent_arr[j][i - 1]][i - 1]
                            + network.level[j] - network.level[network.parent_arr[j][i - 1]],
                            network.dp_table[j][i - 1]);
                }
            }
        }
    }

    public static int log2(int n) {
        for (int i = 0; i < 18; i++) {
            if (n < Math.pow(2, i)) {
                return i - 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int s = input.nextInt();
        int q = input.nextInt();
        int e = input.nextInt();
        tunnel network = new tunnel(n);
        for (int i = 1; i <= n - 1; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            int d = input.nextInt();
            node n_node = new node(a, b, d);
            network.list_tunnels[i] = n_node;
        }
        int[] supply_tunnels = new int[n + 1];
        for (int i = 1; i <= s; i++) {
            int t = input.nextInt();
            supply_tunnels[t] = 1;

        }
        for (int i = 1; i <= n - 1; i++) {
            node curr_node = network.list_tunnels[i];
            normal_node w = new normal_node(curr_node.a, curr_node.dist, curr_node.supply, i);
            if (supply_tunnels[curr_node.a] == 1) {
                w.supply = true;
            }
            normal_node x = new normal_node(curr_node.b, curr_node.dist, curr_node.supply, i);
            if (supply_tunnels[curr_node.b] == 1) {
                x.supply = true;
            }
            network.two_d_arr_tunnels[curr_node.a].add(x);
            network.two_d_arr_tunnels[curr_node.b].add(w);
        }
        dfs(network, e, 0, 0, (long) Math.pow(10, 18));
        pre(network, n);
        pre_1(network, n, supply_tunnels, e);
        /*
         * for (int i = 1; i <= n; i++) { System.out.print(i + ": "); for (int j = 0; j
         * <= 18; j++) { System.out.print(network.dp_table[i][j] + " "); }
         * System.out.println(); }
         */

        /*
         * for (int i = 0; i < entry.size(); i++) { System.out.println((i + 1) +
         * ": Entry=" + entry.get(i) + " Exit=" + exit.get(i)); } for (int l = 0; l < q;
         * l++) { int x = input.nextInt(); int y = input.nextInt(); /* for (int i = 1; i
         * < n + 1; i++) { if (two_d_arr_tunnels[i] != null) { System.out.print(i +
         * ": "); for (int j = 0; j < two_d_arr_tunnels[i].size(); j++) { normal_node t
         * = two_d_arr_tunnels[i].get(j); if (t != null) {
         * System.out.println(t.destination + " " + t.distance + t.supply + " " +
         * t.path_no); } } } }
         * 
         * }
         */
        for (int i = 0; i < q; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            node deleted = network.list_tunnels[x];
            int small, shorter = -1;
            if (network.height[deleted.a] > network.height[deleted.b]) {
                small = network.height[y] - network.height[deleted.a];
                shorter = deleted.a;
            } else {
                small = network.height[y] - network.height[deleted.b];
                shorter = deleted.b;
            }
            int v = y;
            if (small > 0) {
                for (int j = 0; j < 18; j++) {
                    if (((small >> j) & 1) == 1) {
                        v = network.parent_arr[v][j];
                    }
                }
            }
            long min = (long) Math.pow(10, 18);
            if (v == shorter) {
                int j;
                if (small == 0)
                    j = small;
                else
                    j = log2(small) + 1;
                while (j >= 0) {
                    if (network.dp_table[y][j] < min) {
                        min = network.dp_table[y][j];
                    }
                    j--;
                }
            } else {
                for (int j = 18; j >= 0; j--) {
                    if (network.dp_table[y][j] < min) {
                        min = network.dp_table[y][j];
                    }
                }
            }
            if (y == e) {
                System.out.println("escaped");
            } else {
                if (min == -1) {
                    System.out.println("escaped");
                } else if (min == (long) Math.pow(10, 18)) {
                    System.out.println("oo");
                } else {
                    System.out.println(min);
                }
            }
        }

    }
}