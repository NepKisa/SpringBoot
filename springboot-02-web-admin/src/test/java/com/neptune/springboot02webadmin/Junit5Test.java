package com.neptune.springboot02webadmin;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Junit5功能类测试")
//@SpringBootTest
public class Junit5Test {

    @Disabled
    @DisplayName("测试DisPlayName注解")
    @Test
    void testDisplayName() {
        System.out.println(1);
    }

    @DisplayName("测试方法1")
    @Test
    void testDisplayName1() {
        System.out.println("test1....");
    }

    @DisplayName("测试方法2")
    @Test
    void testDisplayName2() {
        System.out.println("test2....");
    }

    /**
     * 规定方法超时时间。超出时间测试出异常
     *
     * @throws InterruptedException
     */
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() throws InterruptedException {
        System.out.println(233);
        Thread.sleep(6000);
    }

    @RepeatedTest(5)
    @Test
    void testRepeat() {
        System.out.println(66666);
    }

    @BeforeEach
    void testBeforeEach() {
        System.out.println("测试开始了");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("测试结束了");
    }

    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试开始了....");
    }

    @AfterAll
    static void testAfterAll() {
        System.out.println("所有测试结束了....");
    }

    /**
     * 断言:前面断言失败,后面的代码都不会执行
     * 每个断言都可自定义错误信息
     */
    @Test
    @DisplayName("simple assertion")
    public void simple() {
        assertEquals(3, 1 + 2, "业务逻辑计算失败");
        assertNotEquals(3, 1 + 1);

        assertNotSame(new Object(), new Object());
        Object obj = new Object();
        assertSame(obj, obj, "两个对象不一样");

        assertFalse(1 > 2);
        assertTrue(1 < 2);

        assertNull(null);
        assertNotNull(new Object());

        assertArrayEquals(new int[]{1, 2}, new int[]{2, 1}, "两数组不相等");
    }

    /**
     * 全部成功才算成功，有一个断言失败则失败
     */
    @Test
    @DisplayName("assert all")
    public void all() {
        assertAll("Math",
                () -> assertEquals(2, 1 + 1, "结果不为2"),
                () -> assertTrue(1 > 0, "结果不是true")
        );
    }

    @Test
    @DisplayName("异常测试")
    public void exceptionTest() {
        Assertions.assertThrows(ArithmeticException.class,
                //断定业务逻辑一定会出现异常
                () -> System.out.println(1 % 0), "业务逻辑竟然正常运行？");

    }

    @Test
    @DisplayName("超时测试")
    public void timeoutTest() {
        //如果测试方法时间超过1s将会异常
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }

    @Test
    @DisplayName("fail")
    public void shouldFail() {
        fail("This should fail");
    }
}
