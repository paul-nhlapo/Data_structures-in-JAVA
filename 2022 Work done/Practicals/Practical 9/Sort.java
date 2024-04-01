
// Name:Paul Nhlapo
// Student number:u18108378
import java.util.Arrays;

public class Sort {

	////// Implement the functions below this line //////

	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug) {

		// Your code here
		int mid;
		if (first < last) {
			mid = (first + last) / 2;
			mergesort(data, first, mid, debug);
			mergesort(data, mid + 1, last, debug);
			merge(data, first, last, debug);
		}

	}

	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug) {

		// Your code here
		int mid = (first + last) / 2;
		int it1 = 0, it2 = first, it3 = mid + 1;
		T[] temp = (T[]) new Comparable[last - first + 1];
		while (it2 <= mid && it3 <= last) {
			if (data[it2].compareTo(data[it3]) < 0) {
				temp[it1++] = data[it2++];
			} else {
				temp[it1++] = data[it3++];
			}
		}

		while (it2 <= mid) {
			temp[it1++] = data[it2++];
		}
		while (it3 <= last) {
			temp[it1++] = data[it3++];
		}

		// copy temp to original interval
		for (int i = first; i <= last; i++) {
			data[i] = temp[i - first];
		}

		// DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

	/********** COUNTING **********/
	public static void countingsort(int[] data, boolean debug) {

		// Your code here
		int i;
		int largest = data[0];
		int n = data.length;
		int[] tmp = new int[n];

		for (i = 1; i < n; i++) {

			if (largest < data[i]) {

				largest = data[i];
			}
		}

		int[] count = new int[largest + 1];

		for (i = 0; i <= largest; i++)
			count[i] = 0;
		for (i = 0; i < n; i++)
			count[data[i]]++;
		for (i = 1; i <= largest; i++)
			count[i] = count[i - 1] + count[i];
		for (i = n - 1; i >= 0; i--) {
			tmp[count[data[i]] - 1] = data[i];
			count[data[i]]--;
		}
		for (i = 0; i < n; i++)
			data[i] = tmp[i];

		// DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

}