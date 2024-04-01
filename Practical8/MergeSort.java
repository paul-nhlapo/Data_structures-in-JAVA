public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    // @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        // Hint This function can be implemented as a one liner consisting of a return
        // and a function call
        Comparable<T>[] res = mergeSort(arr);
        return res;

    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] mergeSort(Comparable<T>[] arr) {
        // Do not change the position of this function.
        printArr(arr);
        // Add any code below
        if (arr == null || arr.length == 0)
            return arr;
        if (arr.length == 1)
            return arr;
        int mid = getMidPoint(0, arr.length);
        Comparable<T>[] left = new Comparable[mid];
        Comparable<T>[] right = new Comparable[arr.length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }
        Comparable<T>[] leftSorted = mergeSort(left);
        Comparable<T>[] rightSorted = mergeSort(right);
        Comparable<T>[] res = merge(leftSorted, rightSorted);
        return res;

    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] merge(Comparable<T>[] lh, Comparable<T>[] rh) {

        if (lh == null || rh == null)
            return null;

        if (lh.length == 0)
            return rh;
        if (rh.length == 0)
            return lh;
        if (lh.length == 1 && rh.length == 1) {
            if (lh[0].compareTo((T) rh[0]) < 0) {
                Comparable<T>[] res = new Comparable[2];
                res[0] = lh[0];
                res[1] = rh[0];
                return res;
            } else {
                Comparable<T>[] res = new Comparable[2];
                res[0] = rh[0];
                res[1] = lh[0];
                return res;
            }
        }

        int lhMid = getMidPoint(0, lh.length);
        int rhMid = getMidPoint(0, rh.length);
        Comparable<T>[] lhLeft = new Comparable[lhMid];
        Comparable<T>[] lhRight = new Comparable[lh.length - lhMid];
        Comparable<T>[] rhLeft = new Comparable[rhMid];
        Comparable<T>[] rhRight = new Comparable[rh.length - rhMid];
        for (int i = 0; i < lhMid; i++) {
            lhLeft[i] = lh[i];
        }
        for (int i = lhMid; i < lh.length; i++) {
            lhRight[i - lhMid] = lh[i];
        }
        for (int i = 0; i < rhMid; i++) {
            rhLeft[i] = rh[i];
        }
        for (int i = rhMid; i < rh.length; i++) {
            rhRight[i - rhMid] = rh[i];
        }
        Comparable<T>[] lhSorted = merge(lhLeft, lhRight);
        Comparable<T>[] rhSorted = merge(rhLeft, rhRight);
        Comparable<T>[] res = new Comparable[lhSorted.length + rhSorted.length];
        int lhIndex = 0;
        int rhIndex = 0;
        for (int i = 0; i < res.length; i++) {
            if (lhIndex >= lhSorted.length) {
                res[i] = rhSorted[rhIndex];
                rhIndex++;
            } else if (rhIndex >= rhSorted.length) {
                res[i] = lhSorted[lhIndex];
                lhIndex++;
            } else if (lhSorted[lhIndex].compareTo((T) rhSorted[rhIndex]) < 0) {
                res[i] = lhSorted[lhIndex];
                lhIndex++;
            } else {
                res[i] = rhSorted[rhIndex];
                rhIndex++;
            }
        }
        return res;
    }

    private int getMidPoint(int first, int last) {
        return (int) Math.floor((first + last) / 2);
    }

}
