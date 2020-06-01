import java.io.*;
import java.util.*;
import java.lang.*;

public class detective {

    static class max_heap {
        detective_node[] heap_arr;
        int size = 0;

        max_heap(int n) {
            heap_arr = new detective_node[n];
        }
    }

    public static void swap(max_heap heap, int val_1, int val_2) {
        detective_node temp = heap.heap_arr[val_1];
        heap.heap_arr[val_1] = heap.heap_arr[val_2];
        heap.heap_arr[val_2] = temp;
    }

    public static void heapify(max_heap heap, int iterator_val) {
        int parent = (iterator_val - 1) / 2;
        if (heap.heap_arr[parent].skill > 0) {
            if (heap.heap_arr[parent].skill < heap.heap_arr[iterator_val].skill) {
                detective_node temp = heap.heap_arr[parent];
                heap.heap_arr[parent] = heap.heap_arr[iterator_val];
                heap.heap_arr[iterator_val] = temp;
                heapify(heap, parent);
            }
        }
    }

    public static void heapify_max(max_heap heap, int iterator_val) {
        int left_child = 2 * iterator_val + 1;
        int right_child = 2 * iterator_val + 2;
        if (left_child < heap.size && right_child < heap.size) {
            if (heap.heap_arr[left_child].skill > heap.heap_arr[iterator_val].skill
                    && heap.heap_arr[right_child].skill > heap.heap_arr[iterator_val].skill) {
                if (heap.heap_arr[left_child].skill > heap.heap_arr[right_child].skill) {
                    swap(heap, left_child, iterator_val);
                    heapify_max(heap, left_child);
                } else {
                    swap(heap, right_child, iterator_val);
                    heapify_max(heap, right_child);
                }
            } else if (heap.heap_arr[left_child].skill > heap.heap_arr[iterator_val].skill) {
                swap(heap, left_child, iterator_val);
                heapify_max(heap, left_child);
            } else if (heap.heap_arr[right_child].skill > heap.heap_arr[iterator_val].skill) {
                swap(heap, right_child, iterator_val);
                heapify_max(heap, right_child);
            }
        } else if (left_child < heap.size && heap.heap_arr[left_child].skill > heap.heap_arr[iterator_val].skill) {
            swap(heap, left_child, iterator_val);
            heapify_max(heap, left_child);
        } else if (right_child < heap.size && heap.heap_arr[right_child].skill > heap.heap_arr[iterator_val].skill) {
            swap(heap, right_child, iterator_val);
            heapify_max(heap, right_child);
        }
    }

    public static void insert(max_heap heap, detective_node val) {
        heap.size++;
        heap.heap_arr[heap.size - 1] = val;
        heapify(heap, heap.size - 1);
    }

    public static detective_node extract_max(max_heap heap) {
        detective_node temp = heap.heap_arr[heap.size - 1];
        detective_node min = heap.heap_arr[0];
        heap.heap_arr[heap.size - 1] = heap.heap_arr[0];
        heap.heap_arr[0] = temp;
        heap.heap_arr[heap.size - 1] = new detective_node(0, 1);
        heap.size--;
        heapify_max(heap, 0);
        return min;

    }

    detective_node[] arr;

    public detective(int n) {
        arr = new detective_node[n];
    }

    static class detective_node {
        int skill, wage;
        double ratio;

        public detective_node(int skill, int wage) {
            this.skill = skill;
            this.wage = wage;
        }
    }

    public static void helper(detective list_Detective, int beg, int last, int mid) {
        int n_l = mid - beg + 1, n_r = last - mid;
        detective_node[] temp_l = new detective_node[n_l];
        detective_node[] temp_r = new detective_node[n_r];
        int x = 0, y = 0, Arr_index = beg;
        for (int i = 0; i < n_l; i++) {
            temp_l[i] = list_Detective.arr[beg + i];
        }
        for (int i = 0; i < n_r; i++) {
            temp_r[i] = list_Detective.arr[mid + 1 + i];
        }
        while (x < n_l && y < n_r) {
            if (temp_l[x].ratio < temp_r[y].ratio) {
                list_Detective.arr[Arr_index] = temp_l[x];
                x++;
            } else {
                list_Detective.arr[Arr_index] = temp_r[y];
                y++;
            }
            Arr_index++;
        }
        while (x < n_l) {
            list_Detective.arr[Arr_index] = temp_l[x];
            x++;
            Arr_index++;
        }
        while (y < n_r) {
            list_Detective.arr[Arr_index] = temp_r[y];
            y++;
            Arr_index++;
        }

    }

    public static void mergeSort(detective list_Detective, int beg, int last) {
        int mid;
        if (beg < last) {
            mid = (beg + last) / 2;
            mergeSort(list_Detective, beg, mid);
            mergeSort(list_Detective, mid + 1, last);
            helper(list_Detective, beg, last, mid);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        detective list_Detective = new detective(n);
        int k = input.nextInt();
        int[] arr_skills = new int[n];
        int[] arr_wage = new int[n];
        for (int i = 0; i < n; i++) {
            arr_skills[i] = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            arr_wage[i] = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            detective_node new_node = new detective_node(arr_skills[i], arr_wage[i]);
            list_Detective.arr[i] = new_node;
        }
        for (int i = 0; i < n; i++) {
            list_Detective.arr[i].ratio = (double) list_Detective.arr[i].wage / (double) list_Detective.arr[i].skill;
        }
        mergeSort(list_Detective, 0, n - 1);
        max_heap maximum_heap = new max_heap(k);
        double sum = 0, sum_skills = 0;
        double min = Math.pow(10, 9);
        for (int i = 0; i < k - 1; i++) {
            insert(maximum_heap, list_Detective.arr[i]);
            sum_skills += list_Detective.arr[i].skill;
        }
        for (int i = k - 1; i < n; i++) {
            if (list_Detective.arr[i].skill < maximum_heap.heap_arr[0].skill) {
                if ((list_Detective.arr[i].skill + sum_skills) * list_Detective.arr[i].ratio < min) {
                    min = (list_Detective.arr[i].skill + sum_skills) * list_Detective.arr[i].ratio;
                }
                detective_node a = extract_max(maximum_heap);
                sum_skills -= a.skill;
                sum_skills += list_Detective.arr[i].skill;
                insert(maximum_heap, list_Detective.arr[i]);
            } else {
                if ((list_Detective.arr[i].skill + sum_skills) * list_Detective.arr[i].ratio < min)
                    min = (list_Detective.arr[i].skill + sum_skills) * list_Detective.arr[i].ratio;
            }
        }

        System.out.println((int) Math.ceil(min));
    }
}