package algorithm.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            DataUtil.consolePrint(arr, i);

            if (DataUtil.checkIsSorted(arr)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = DataUtil.getIntArr();
        BubbleSort.sort(arr);
    }
}
