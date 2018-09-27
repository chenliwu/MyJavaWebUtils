/**
 * 描述:
 *
 * @author chenlw
 * @create 2018-09-27 17:48
 */
public class Test {

    public static void main(String[] args){
        System.out.println(getFileSuffixName("测试文件名.jpg"));
    }

    /**
     * 获取文件扩展名  没有.号
     */
    private static String getFileSuffixName(String filename) {
        int index = filename.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        String result = filename.substring(index + 1);
        return result;
    }


}
