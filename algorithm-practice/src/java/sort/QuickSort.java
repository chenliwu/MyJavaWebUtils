package sort;

/**
 * 快速排序算法
 *
 * @author chenlw
 * @since 2019/09/07
 */
public class QuickSort {

    public static void main(String[] args) {
        int [] data = {5,6,4,2,1,7,9,8};
        quickSort(data,0,data.length-1);
        for(int i = 0;i<data.length;i++){
            System.out.print(data[i]+ "  ");
        }
        System.out.println();
    }

    public static void quickSort(int[] data, int low, int high) {
        int i, pivotkey, j;
        if (low < high) {
            // 以数组的第一个元素为基准（枢轴元素）进行划分
            pivotkey = data[low];
            i = low;
            j = high;
            while (i < j) {
                // 首先从j所指位置起向前搜索，找到第一个关键字小于pivotkey的记录与枢轴记录互相交换
                while (i < j && data[j] >= pivotkey) {
                    j--;
                }
                // 比枢轴元素小者移到低下标端
                if (i < j) {
                    data[i++] = data[j];
                }
                while (i < j && data[i] <= pivotkey) {
                    i++;
                }
                if (i < j) {
                    // 比枢轴元素大者移到高下标端
                    data[j--] = data[i];
                }
            }
            // 枢轴元素移动到正确的位置
            data[i] = pivotkey;

            // 对前半个字表快速排序
            quickSort(data, low, i - 1);
            // 对后半个字表快速排序
            quickSort(data, i + 1, high);
        }

    }


}
