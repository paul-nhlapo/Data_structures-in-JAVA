public class CountSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        // Do not alter given code nor add code above
        int[] count = formCountArr(arr);
        printArr(arr, count);
        int[] sumCount = sumCount(count);
        printArr(arr, sumCount);
        Comparable<T>[] res = new Comparable[arr.length]; // This array needs to be populated with the final result
        // Add code below this
        for (int i = 0; i < arr.length; i++) {
            res[sumCount[arr[i].hashCode()] - 1] = arr[i];
            sumCount[arr[i].hashCode()]--;
        }

        // Do not change the statement below
        printArr(res, sumCount);
        // Ensure only return statement is below this
        return res;

    }

    private int[] formCountArr(Comparable<T>[] arr) {
        // This function should form a count array of the passed in array as
        // described in the lecture notes.
        // The resulting array should be sized to the same size as the passed
        // in array.
        // Return the resulting array.
        // Add code below this

        if (arr == null)
            return null;
        if (arr.length == 0)
            return null;
        int[] res = new int[countArraySize(arr)];
        for (Comparable<T> t : arr) {
            res[t.hashCode()]++;
        }
        return res;

    }

    private int[] sumCount(int[] countArr) {
        // This function should form a cumulative frequency array of the
        // passed in countArr as described in the lecture notes.
        // Use the following formula to calculate the value for each index in
        // the resulting array (named result) :
        // ∀i ∈[1, countArr.length())
        // result[i] = result[i −1] + countArr[i]
        // The resulting array should be sized to the same size as the passed
        // in array.
        // Return the resulting array.

        if (countArr == null)
            return null;
        if (countArr.length == 0)
            return null;
        int[] res = new int[countArr.length];
        res[0] = countArr[0];
        for (int i = 1; i < countArr.length; i++) {
            res[i] = res[i - 1] + countArr[i];
        }
        return res;
    }

    private int countArraySize(Comparable<T>[] arr) {
        // This function should calculate the size of the countArray that is
        // used as described above.
        // To accomplish this you will need to find the element with the
        // biggest .hashCode() value
        // Return the value found in the point above.
        // Add code below this

        if (arr == null)
            return 0;
        if (arr.length == 0)
            return 0;
        int max = arr[0].hashCode();
        for (Comparable<T> t : arr) {
            if (t.hashCode() > max)
                max = t.hashCode();
        }
        return max + 1;

    }

    private void printArr(Comparable<T>[] arr, int[] count) {
        if (arr == null || count == null)
            System.out.println("NULL ARRAYS");

        String res = "[";
        for (Comparable<T> t : arr) {
            res += t + "{" + count[t.hashCode()] + "},";
        }
        if (res.length() > 0) {
            res = res.substring(0, res.length() - 1);
        }
        res += "]";
        System.out.println(res);
    }

}
