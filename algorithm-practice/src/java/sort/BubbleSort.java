package sort;

public class BubbleSort {

    public static void main(String[] args) {
        System.out.println("冒泡排序");
        int[] dataList = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        bubbleSort(dataList, dataList.length);
    }


    /**
     * @param dataList
     * @param length
     */
    public static void bubbleSort(int[] dataList, int length) {
        // 最多比较length -1轮
        for (int i = 0; i < length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < length - i - 1; j++) {
                if (dataList[j] > dataList[j + 1]) {
                    // 大的往后排，小的往前冒
                    int temp = dataList[j];
                    dataList[j] = dataList[j + 1];
                    dataList[j + 1] = temp;
                    // 有交换
                    flag = false;
                }
            }
            if (flag) {
                // 如果没有元素进行交换，则可以结束冒泡排序
                break;
            }
        }
        for (int i = 0; i < length; i++) {
            System.out.print(dataList[i] + " ");
        }
        System.out.println();

    }


}
