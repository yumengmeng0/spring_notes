/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description:
 */
public class B1 {
    public void functionB1() {
        try {
            A1 a1 = new A1();
            a1.functionA1(); // a.functionA内部异常，在内部被捕获
            System.out.println("functionB1");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("b1 Exception");
        }
    }
}
