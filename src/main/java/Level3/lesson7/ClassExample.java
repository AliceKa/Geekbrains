package Level3.lesson7;

public class ClassExample {

    @Test(priority = 3)
    public void Test3() {
        System.out.println("Test3");
    }

    @Test(priority = 1)
    public void Test1() {
        System.out.println("Test1");
    }

    @Test(priority = 2)
    public void Test2() {
        System.out.println("Test2");
    }

    @Test(priority = 5)
    public void Test5() {
        System.out.println("Test5");
    }

    @BeforeSuite
    public void BeforeSuite() {
        System.out.println("BeforeSuite");
    }

    @Test(priority = 4)
    public void Test4() {
        System.out.println("Test4");
    }

    @Test(priority = 6)
    public void Test6() {
        System.out.println("Test6");
    }

    @Test(priority = 8)
    public void Test8() {
        System.out.println("Test8");
    }

    @Test(priority = 9)
    public void Test9() {
        System.out.println("Test9");
    }

    @AfterSuite
    public void AfterSuite() {
        System.out.println("AfterSuite");
    }

    @Test(priority = 7)
    public void Test7() {
        System.out.println("Test7");
    }
    @Test(priority = 10)
    public void Test10() {
        System.out.println("Test10");
    }
}
