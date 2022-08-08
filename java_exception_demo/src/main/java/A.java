/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description:
 */
public class A {
    public void functionA(){
        try {
            int i = 1 / 0;
            System.out.println("functionA");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("a Exception");
        }
    }
}
