import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @description: 单元测试类, Junit4使用
 * @date 2022/07/14
 */
public class Junit4Test {

    /**
     * @description: 标记一个测试方法，必须用【public void】修饰
     */
    @Test
    public void test1() {
        System.out.println("hello test");
    }

    /**
     * @description: @Ignore注解以及断言使用
     */
    @Test
    //    @Ignore //和@Test一起使用，全局测试所有用例时忽略该条测试用例
    public void test2() {
        System.out.println("hello world");
        assertEquals(2, 1 + 1);// 断言值是否相等
        assertTrue(true);// 断言是否为真
        assertNotNull(null);// 断言是否为null
        assertNotNull("指定返回错误信息", null);// 断言是否为null，返回指定错误信息
        assertSame(new Object(), new Object());// 断言是否引用的同一个对象
        assertArrayEquals(new int[5], new int[5]);// 断言预期数组和结果数组是否相等
    }

    /**
     * @description: 异常测试，抛出期望异常则成功返回
     */
    @Test(expected = ArithmeticException.class)
    public void test3() {
        System.out.println("异常测试");
        int a = 0;
        int b = 1 / a;
    }

    /**
     * @description: 超时异常测试，单位是毫秒
     */
    @Test(timeout = 1000L)
    public void test4() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("超时异常");
    }

    /**
     * @description: 待补充，参数化测试，套件测试，指定测试顺序，条件测试【暂时未用到】
     */

    /**
     * @description: 在测试类中所有用例运行之前，运行一次该方法，必须用【public static void】修饰
     */
    @BeforeClass
    public static void testBeforeClass() {
        System.out.println("hello world!");
    }

    /**
     * @description: 在测试类中所有用例运行之后，运行一次该方法，必须用【public static void】修饰
     */
    @AfterClass
    public static void testAfterClass() {
        System.out.println("end");
    }

    /**
     * @description: 在测试类中每个用例运行之前，运行一次该方法，必须用【public void】修饰
     */
    @Before
    public void testBefore() {
        System.out.print("start...");
    }
}
