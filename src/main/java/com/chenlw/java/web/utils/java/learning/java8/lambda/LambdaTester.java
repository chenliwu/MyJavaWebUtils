package com.chenlw.java.web.utils.java.learning.java8.lambda;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-09 17:23
 */
public class LambdaTester {

    public static void main(String args[]) {
        test1();
    }

    /**
     * 使用 Lambda 表达式需要注意以下两点：
     * 1、Lambda 表达式主要用来定义行内执行的方法类型接口，例如，一个简单方法接口。
     * 在例子中，我们使用各种类型的Lambda表达式来定义MathOperation接口的方法。然后我们定义了sayMessage的执行。
     * 2、Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力。
     */
    public static void test1() {
        LambdaTester tester = new LambdaTester();

        /**
         * 以下这四个定义，相当于实现接口方法。可以针对同一个参数实现不同的功能。
         * 这种写法类似ES6的箭头函数语法
         */
        // 类型声明：
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));


        // 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        //用花括号
        GreetingService greetService3 = (message) -> {
            System.out.println("Hello " + message);
        };


        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
        greetService3.sayMessage("Google11");
    }


    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }


}
