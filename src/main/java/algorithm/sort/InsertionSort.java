package algorithm.sort;

/**
 * 插入排序
 */
public class InsertionSort {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++)     //n-1此扫描，依次向前插入n-1个元素
        {
            int temp = arr[i];       //每趟将a[i]插入到前面的排序子序列中
            int j;
            for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j];  //将前面较大的元素向后移动
            }
            arr[j + 1] = temp;      //temp值到达插入位置

            DataUtil.consolePrint(arr, i);
            if (DataUtil.checkIsSorted(arr)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = DataUtil.getIntArr();
        //int[] arr = DataUtil.getRandomIntArr(10);
        InsertionSort.sort(arr);
    }
}
