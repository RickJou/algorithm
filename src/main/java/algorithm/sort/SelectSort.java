package algorithm.sort;

public class SelectSort {
    public static void sort(int[] arr) {
        int minIndex;
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {//交換
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

            DataUtil.consolePrint(arr, i);
            if (DataUtil.checkIsSorted(arr)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = DataUtil.getIntArr();
        //int[] arr = DataUtil.getRandomIntArr(10);
        SelectSort.sort(arr);
    }
}
