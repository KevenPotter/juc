package com.example.juc;

@FunctionalInterface
interface Foo {
    void sayHello();

    default int mul(int x, int y) {
        return x * y;
    }

    static int div(int x, int y) {
        return x / y;
    }
}

@FunctionalInterface
interface Foo1 {
    int add(int x, int y);
}

/**
 * 1 函数式编程
 * int age = 23;
 * y = kx +1;
 * f(x) = kx + 1;
 * <p>
 * 函数式接口 interface
 * 拷贝小括号，写死右箭头，落地大括号
 */
public class LambdaExpressDemo02 {

    public static void main(String[] args) {
        Foo foo = () -> System.out.println("HelloWorld");
        foo.sayHello();
        System.out.println(foo.mul(1, 2));
        System.out.println(Foo.div(1, 2));
        Foo1 foo1 = (x, y) -> {
            System.out.println("come in add method");
            return x + y;
        };
        System.out.println(foo1.add(3, 5));
    }
}
