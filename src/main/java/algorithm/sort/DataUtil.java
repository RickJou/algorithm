package algorithm.sort;

/**
 * 选择排序
 */
public class DataUtil {
    public static int[] getRandomIntArr(int arrLength) {
        int[] arr = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        DataUtil.consolePrint(arr);
        System.out.println("------------------------------");
        return arr;
    }

    public static int[] getIntArr() {
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        DataUtil.consolePrint(arr);
        System.out.println("------------------------------");
        return arr;
    }

    public static void consolePrint(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void consolePrint(int[] arr, int count) {
        System.out.print(String.format("第%s轮排序:", (count + 1)));
        consolePrint(arr);
    }

    public static boolean checkIsSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
