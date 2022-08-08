/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description:
 */
public class B {
    public void functionB() {
        try {
            A a = new A();
            a.functionA(); // a.functionA内部异常，在内部被捕获
            System.out.println("functionB");
        } catch (Exception e) {
            System.out.println("b Exception");
            e.printStackTrace();
        }
    }
}
