package org.lessons.lesson5.coupling.tight;

public class TightCouplingExample {
    public static void main(String[] args) {
        TestService t1 = new TestService();
        TestService2 t2 = new TestService2();
        TestService3 t3 = new TestService3();
    }
}
