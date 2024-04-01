public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        printArr(arr);
        if (arr == null || arr.length == 0)
            return arr;

        Comparable<T>[] resultingArr = new Comparable[arr.length];
        return recSort(arr, resultingArr);
    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] recSort(Comparable<T>[] arr, Comparable<T>[] resultingArr) {
        printArr(arr);
        if (arr.length == 1) {
            resultingArr[0] = arr[0];
            return resultingArr;
        }

        int pivotPoint = getPivotPoint(arr);
        Comparable<T> pivot = arr[pivotPoint];
        int leftIndex = 0;
        int rightIndex = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            if (i == pivotPoint)
                continue;

            if (arr[i].compareTo((T) pivot) < 0) {
                resultingArr[leftIndex] = arr[i];
                leftIndex++;
            } else {
                resultingArr[rightIndex] = arr[i];
                rightIndex--;
            }
        }

        resultingArr[leftIndex] = pivot;

        Comparable<T>[] leftArr = new Comparable[leftIndex];
        Comparable<T>[] rightArr = new Comparable[arr.length - leftIndex - 1];

        for (int i = 0; i < leftIndex; i++) {
            leftArr[i] = resultingArr[i];
        }

        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = resultingArr[leftIndex + 1 + i];
        }

        Comparable<T>[] leftSortedArr = recSort(leftArr, new Comparable[leftIndex]);
        Comparable<T>[] rightSortedArr = recSort(rightArr, new Comparable[rightArr.length]);

        for (int i = 0; i < leftSortedArr.length; i++) {
            resultingArr[i] = leftSortedArr[i];
        }

        for (int i = 0; i < rightSortedArr.length; i++) {
            resultingArr[leftSortedArr.length + 1 + i] = rightSortedArr[i];
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
