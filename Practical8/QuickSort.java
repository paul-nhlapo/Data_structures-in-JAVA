public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        Comparable<T>[] resultingArr = new Comparable[arr.length];
        return recSort(arr, resultingArr);
    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] recSort(Comparable<T>[] arr, Comparable<T>[] resultingArr) {
        printArr(arr);
        if (arr.length > 1) {
            int pivotPoint = getPivotPoint(arr);
            Comparable<T> pivot = arr[pivotPoint];
            int lower = 0;
            int upper = arr.length - 1;
            for (int i = 0; i < arr.length; i++) {
                if (i == pivotPoint)
                    continue;
                if (arr[i].compareTo((T) pivot) < 0) {
                    resultingArr[lower] = arr[i];
                    lower++;
                } else {
                    resultingArr[upper] = arr[i];
                    upper--;
                }
            }
            // Put pivot in its correct position
            resultingArr[lower] = pivot;
            Comparable<T>[] left = new Comparable[lower];
            Comparable<T>[] right = new Comparable[arr.length - lower - 1];
            for (int i = 0; i < left.length; i++) {
                left[i] = resultingArr[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = resultingArr[i + lower + 1];
            }
            left = recSort(left, new Comparable[left.length]);
            right = recSort(right, new Comparable[right.length]);
            int index = 0;
            for (int i = 0; i < left.length; i++) {
                resultingArr[index] = left[i];
                index++;
            }
            resultingArr[index] = pivot;
            index++;
            for (int i = 0; i < right.length; i++) {
                resultingArr[index] = right[i];
                index++;
            }
        } else if (arr.length == 1) {
            resultingArr[0] = arr[0];
        }
        return resultingArr;
    }

    private int getPivotPoint(Comparable<T>[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        if (arr.length % 2 == 0)
            return (int) Math.floor(arr.length / 2) - 1;
        else
            return (int) Math.floor(arr.length / 2);
    }

}
