import java.lang.*;
import java.io.*;
import java.util.*;

class min_heap{
	static class heap {
		int[] heap_arr;
		int size = 0;

		heap(int n) {
			heap_arr = new int[n];
		}
	}

	public static void swap(heap binary_heap, int val_1, int val_2) {
		int temp = binary_heap.heap_arr[val_1];
		binary_heap.heap_arr[val_1] = binary_heap.heap_arr[val_2];
		binary_heap.heap_arr[val_2] = temp;
	}

	public static void heapify(heap binary_heap, int iterator_val) {
		int parent = (iterator_val - 1) / 2;
		if (binary_heap.heap_arr[parent] > 0) {
			if (binary_heap.heap_arr[parent] > binary_heap.heap_arr[iterator_val]) {
				int temp = binary_heap.heap_arr[parent];
				binary_heap.heap_arr[parent] = binary_heap.heap_arr[iterator_val];
				binary_heap.heap_arr[iterator_val] = temp;
				heapify(binary_heap, parent);
			}
		}
	}
	public static void heapify_min(heap binary_heap, int iterator_val) {
		int left_child = 2 * iterator_val + 1;
		int right_child = 2 * iterator_val + 2;
		if (left_child < binary_heap.size && right_child < binary_heap.size) {
			if (binary_heap.heap_arr[left_child] < binary_heap.heap_arr[iterator_val]
					&& binary_heap.heap_arr[right_child] < binary_heap.heap_arr[iterator_val]) {
				if (binary_heap.heap_arr[left_child] < binary_heap.heap_arr[right_child]) {
					swap(binary_heap, left_child, iterator_val);
					heapify_min(binary_heap, left_child);
				} else {
					swap(binary_heap, right_child, iterator_val);
					heapify_min(binary_heap, right_child);
				}
			} else if (binary_heap.heap_arr[left_child] < binary_heap.heap_arr[iterator_val]) {
				swap(binary_heap, left_child, iterator_val);
				heapify_min(binary_heap, left_child);
			} else if (binary_heap.heap_arr[right_child] < binary_heap.heap_arr[iterator_val]) {
				swap(binary_heap, right_child, iterator_val);
				heapify_min(binary_heap, right_child);
			}
		} else if (left_child < binary_heap.size && binary_heap.heap_arr[left_child] < binary_heap.heap_arr[iterator_val]) {
				swap(binary_heap, left_child, iterator_val);
				heapify_min(binary_heap, left_child);
		} else if (right_child < binary_heap.size && binary_heap.heap_arr[right_child] < binary_heap.heap_arr[iterator_val]){
				swap(binary_heap, right_child, iterator_val);
				heapify_min(binary_heap, right_child);
		}
	}

	public static void insert(heap binary_heap, int val) {
		binary_heap.size++;
		binary_heap.heap_arr[binary_heap.size - 1] = val;
		heapify(binary_heap, binary_heap.size - 1);
	}

	public static int extract_min(heap binary_heap) {
		int temp = binary_heap.heap_arr[binary_heap.size - 1];
		int min = binary_heap.heap_arr[0];
		binary_heap.heap_arr[binary_heap.size - 1] = binary_heap.heap_arr[0];
		binary_heap.heap_arr[0] = temp;
		binary_heap.heap_arr[binary_heap.size - 1] = 0;
		binary_heap.size--;
		heapify_min(binary_heap, 0);
		return min;

	}

	public static void main(String[] args) throws IOException {
		Reader input = new Reader();
		int n = input.nextInt();
		int arr[] = new int[n];
		int ans[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = input.nextInt();
		}
		heap binary_heap = new heap(n);
		for (int i = 0; i < n; i++) {
			if (i == 0 || i == 1) {
				ans[i] = -1;
				insert(binary_heap, arr[i]);
			} else {
				insert(binary_heap, arr[i]);
				int a = extract_min(binary_heap);
				int b = extract_min(binary_heap);
				int c = extract_min(binary_heap);
				ans[i] = a ^ b ^ c;
				insert(binary_heap, a);
				insert(binary_heap, b);
				insert(binary_heap, c);
			}
		}
		int q = input.nextInt();
		for (int i = 0; i < q; i++) {
			int num_q = input.nextInt();
			System.out.println(ans[num_q - 1]);
		}

	}
}
