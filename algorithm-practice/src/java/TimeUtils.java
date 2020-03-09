/**
 * @author chenlw
 * @date 2020/03/02
 */
public class TimeUtils {

    public static void main(String[] args) {
        testTime();
    }

    public static void testTime() {
        try {
            long time1 = System.currentTimeMillis();
            Thread.sleep(10000);
            long time2 = System.currentTimeMillis();
            System.out.println("时间戳之差：" + (time2 - time1) / 1000 + "秒");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
