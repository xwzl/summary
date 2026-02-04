package com.java.interview.java.design.behavioral;

import java.util.HashMap;
import java.util.Map;

/**
 * 解释器模式 (Interpreter Pattern)
 *
 * 定义：解释器模式是一种行为型设计模式，它为语言中的每个符号定义了一个类，
 * 用来解析语言中的句子。解释器模式主要用于解决某一特定类型的问题发生的频率足够高，
 * 以至于需要将其转化为一种简单的语言或规则系统来表示。
 *
 * 主要角色：
 * 1. 抽象表达式(Expression)：声明一个抽象的解释操作
 * 2. 终结符表达式(Terminal Expression)：实现与文法中的终结符相关联的解释操作
 * 3. 非终结符表达式(Nonterminal Expression)：实现与文法中的非终结符相关联的解释操作
 * 4. 上下文(Context)：包含解释器之外的一些全局信息
 * 5. 客户端(Client)：构建表示该文法规则定义的语言中一个特定的句子的抽象语法树
 *
 * 主要特点：
 * 1. 容易改变和扩展文法：因为该模式使用类来表示文法规则，可以使用继承来改变或扩展该文法
 * 2. 容易实现文法：定义抽象语法树中各个节点的类的实现大体类似
 * 3. 复杂的文法难以维护：当文法变得复杂时，类的数量会急剧增长
 *
 * 应用场景：
 * - 编程语言的编译器
 * - 正则表达式匹配器
 * - SQL解析器
 * - 规则引擎
 * - 数学表达式计算器
 */
public class InterpreterPattern {

    /**
     * 上下文类 - 存储变量值的环境
     */
    static class Context {
        private Map<String, Integer> variables = new HashMap<>();

        public void setVariable(String name, int value) {
            variables.put(name, value);
        }

        public int getVariable(String name) {
            Integer value = variables.get(name);
            if (value == null) {
                throw new RuntimeException("变量 " + name + " 未定义");
            }
            return value;
        }
    }

    /**
     * 抽象表达式接口 - 定义解释操作
     */
    interface Expression {
        int interpret(Context context);
    }

    /**
     * 终结符表达式 - 变量表达式
     * 代表文法中的终结符，通常是变量或常量
     */
    static class VariableExpression implements Expression {
        private String name;

        public VariableExpression(String name) {
            this.name = name;
        }

        @Override
        public int interpret(Context context) {
            return context.getVariable(name);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * 终结符表达式 - 常量表达式
     * 代表文法中的常量值
     */
    static class ConstantExpression implements Expression {
        private int value;

        public ConstantExpression(int value) {
            this.value = value;
        }

        @Override
        public int interpret(Context context) {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /**
     * 非终结符表达式 - 加法表达式
     * 代表文法中的非终结符，组合其他表达式
     */
    static class AddExpression implements Expression {
        private Expression left;
        private Expression right;

        public AddExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            return left.interpret(context) + right.interpret(context);
        }

        @Override
        public String toString() {
            return "(" + left + " + " + right + ")";
        }
    }

    /**
     * 非终结符表达式 - 减法表达式
     */
    static class SubtractExpression implements Expression {
        private Expression left;
        private Expression right;

        public SubtractExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            return left.interpret(context) - right.interpret(context);
        }

        @Override
        public String toString() {
            return "(" + left + " - " + right + ")";
        }
    }

    /**
     * 非终结符表达式 - 乘法表达式
     */
    static class MultiplyExpression implements Expression {
        private Expression left;
        private Expression right;

        public MultiplyExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            return left.interpret(context) * right.interpret(context);
        }

        @Override
        public String toString() {
            return "(" + left + " * " + right + ")";
        }
    }

    /**
     * 非终结符表达式 - 除法表达式
     */
    static class DivideExpression implements Expression {
        private Expression left;
        private Expression right;

        public DivideExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            int divisor = right.interpret(context);
            if (divisor == 0) {
                throw new ArithmeticException("除数不能为零");
            }
            return left.interpret(context) / divisor;
        }

        @Override
        public String toString() {
            return "(" + left + " / " + right + ")";
        }
    }

    /**
     * 非终结符表达式 - 模运算表达式
     */
    static class ModuloExpression implements Expression {
        private Expression left;
        private Expression right;

        public ModuloExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            int divisor = right.interpret(context);
            if (divisor == 0) {
                throw new ArithmeticException("模运算的除数不能为零");
            }
            return left.interpret(context) % divisor;
        }

        @Override
        public String toString() {
            return "(" + left + " % " + right + ")";
        }
    }

    /**
     * 逻辑表达式 - 与运算
     */
    static class AndExpression implements Expression {
        private Expression left;
        private Expression right;

        public AndExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            return (left.interpret(context) != 0 && right.interpret(context) != 0) ? 1 : 0;
        }

        @Override
        public String toString() {
            return "(" + left + " AND " + right + ")";
        }
    }

    /**
     * 逻辑表达式 - 或运算
     */
    static class OrExpression implements Expression {
        private Expression left;
        private Expression right;

        public OrExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            return (left.interpret(context) != 0 || right.interpret(context) != 0) ? 1 : 0;
        }

        @Override
        public String toString() {
            return "(" + left + " OR " + right + ")";
        }
    }

    /**
     * 比较表达式 - 大于运算
     */
    static class GreaterThanExpression implements Expression {
        private Expression left;
        private Expression right;

        public GreaterThanExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            return left.interpret(context) > right.interpret(context) ? 1 : 0;
        }

        @Override
        public String toString() {
            return "(" + left + " > " + right + ")";
        }
    }

    /**
     * 比较表达式 - 小于运算
     */
    static class LessThanExpression implements Expression {
        private Expression left;
        private Expression right;

        public LessThanExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            return left.interpret(context) < right.interpret(context) ? 1 : 0;
        }

        @Override
        public String toString() {
            return "(" + left + " < " + right + ")";
        }
    }

    /**
     * 进阶用法：表达式解析器
     * 将字符串表达式解析为抽象语法树
     */
    static class ExpressionParser {
        private int pos = 0;
        private String expression;

        public Expression parse(String expression) {
            this.expression = expression.replaceAll("\\s+", ""); // 移除空格
            this.pos = 0;
            return parseExpression();
        }

        private Expression parseExpression() {
            Expression result = parseTerm();

            while (pos < expression.length()) {
                char op = expression.charAt(pos);
                if (op == '+' || op == '-') {
                    pos++;
                    Expression right = parseTerm();
                    if (op == '+') {
                        result = new AddExpression(result, right);
                    } else {
                        result = new SubtractExpression(result, right);
                    }
                } else {
                    break;
                }
            }

            return result;
        }

        private Expression parseTerm() {
            Expression result = parseFactor();

            while (pos < expression.length()) {
                char op = expression.charAt(pos);
                if (op == '*' || op == '/' || op == '%') {
                    pos++;
                    Expression right = parseFactor();
                    if (op == '*') {
                        result = new MultiplyExpression(result, right);
                    } else if (op == '/') {
                        result = new DivideExpression(result, right);
                    } else {
                        result = new ModuloExpression(result, right);
                    }
                } else {
                    break;
                }
            }

            return result;
        }

        private Expression parseFactor() {
            if (expression.charAt(pos) == '(') {
                pos++; // 跳过 '('
                Expression result = parseExpression();
                pos++; // 跳过 ')'
                return result;
            } else if (Character.isDigit(expression.charAt(pos))) {
                return parseNumber();
            } else {
                return parseVariable();
            }
        }

        private Expression parseNumber() {
            int start = pos;
            while (pos < expression.length() && Character.isDigit(expression.charAt(pos))) {
                pos++;
            }
            int number = Integer.parseInt(expression.substring(start, pos));
            return new ConstantExpression(number);
        }

        private Expression parseVariable() {
            int start = pos;
            while (pos < expression.length() && Character.isLetterOrDigit(expression.charAt(pos))) {
                pos++;
            }
            String varName = expression.substring(start, pos);
            return new VariableExpression(varName);
        }
    }

    /**
     * 进阶用法：缓存解释器
     * 缓存已解析的表达式以提高性能
     */
    static class CachedExpressionInterpreter {
        private Map<String, Expression> cache = new HashMap<>();
        private ExpressionParser parser = new ExpressionParser();

        public int interpret(String expression, Context context) {
            Expression cachedExpr = cache.get(expression);
            if (cachedExpr == null) {
                cachedExpr = parser.parse(expression);
                cache.put(expression, cachedExpr);
            }
            return cachedExpr.interpret(context);
        }
    }

    /**
     * 进阶用法：函数表达式
     * 支持内置函数的表达式解释器
     */
    static class FunctionExpression implements Expression {
        private String functionName;
        private Expression argument;

        public FunctionExpression(String functionName, Expression argument) {
            this.functionName = functionName;
            this.argument = argument;
        }

        @Override
        public int interpret(Context context) {
            int argValue = argument.interpret(context);
            switch (functionName.toLowerCase()) {
                case "abs":
                    return Math.abs(argValue);
                case "sqrt":
                    return (int) Math.sqrt(Math.abs(argValue)); // 避免负数开方
                case "square":
                    return argValue * argValue;
                case "neg":
                    return -argValue;
                default:
                    throw new IllegalArgumentException("未知函数: " + functionName);
            }
        }

        @Override
        public String toString() {
            return functionName + "(" + argument + ")";
        }
    }

    /**
     * 进阶用法：三元条件表达式
     * 支持条件判断的表达式
     */
    static class TernaryExpression implements Expression {
        private Expression condition;
        private Expression trueValue;
        private Expression falseValue;

        public TernaryExpression(Expression condition, Expression trueValue, Expression falseValue) {
            this.condition = condition;
            this.trueValue = trueValue;
            this.falseValue = falseValue;
        }

        @Override
        public int interpret(Context context) {
            return condition.interpret(context) != 0 ? 
                   trueValue.interpret(context) : 
                   falseValue.interpret(context);
        }

        @Override
        public String toString() {
            return "(" + condition + " ? " + trueValue + " : " + falseValue + ")";
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 解释器模式示例 ===\n");

        // 创建上下文环境
        Context context = new Context();
        context.setVariable("x", 10);
        context.setVariable("y", 5);
        context.setVariable("z", 3);

        System.out.println("1. 基础算术表达式示例:");
        // 构建表达式: (x + y) * z
        Expression expr1 = new MultiplyExpression(
            new AddExpression(
                new VariableExpression("x"),
                new VariableExpression("y")
            ),
            new VariableExpression("z")
        );
        System.out.println("表达式: " + expr1);
        System.out.println("结果: " + expr1.interpret(context)); // (10 + 5) * 3 = 45

        // 构建表达式: x - y + z
        Expression expr2 = new AddExpression(
            new SubtractExpression(
                new VariableExpression("x"),
                new VariableExpression("y")
            ),
            new VariableExpression("z")
        );
        System.out.println("\n表达式: " + expr2);
        System.out.println("结果: " + expr2.interpret(context)); // (10 - 5) + 3 = 8

        // 构建表达式: (x * y) / z
        Expression expr3 = new DivideExpression(
            new MultiplyExpression(
                new VariableExpression("x"),
                new VariableExpression("y")
            ),
            new VariableExpression("z")
        );
        System.out.println("\n表达式: " + expr3);
        System.out.println("结果: " + expr3.interpret(context)); // (10 * 5) / 3 = 16

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n2. 逻辑和比较表达式示例:");
        // 构建表达式: (x > y) AND (y > z)
        Expression expr4 = new AndExpression(
            new GreaterThanExpression(
                new VariableExpression("x"),
                new VariableExpression("y")
            ),
            new GreaterThanExpression(
                new VariableExpression("y"),
                new VariableExpression("z")
            )
        );
        System.out.println("表达式: " + expr4);
        System.out.println("结果: " + expr4.interpret(context)); // (10 > 5) AND (5 > 3) = 1 (true)

        // 构建表达式: (x < y) OR (y > z)
        Expression expr5 = new OrExpression(
            new LessThanExpression(
                new VariableExpression("x"),
                new VariableExpression("y")
            ),
            new GreaterThanExpression(
                new VariableExpression("y"),
                new VariableExpression("z")
            )
        );
        System.out.println("\n表达式: " + expr5);
        System.out.println("结果: " + expr5.interpret(context)); // (10 < 5) OR (5 > 3) = 1 (true)

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n3. 字符串表达式解析器示例:");
        ExpressionParser parser = new ExpressionParser();

        String exprStr1 = "x + y * z";
        Expression parsedExpr1 = parser.parse(exprStr1);
        System.out.println("解析表达式: " + exprStr1);
        System.out.println("抽象语法树: " + parsedExpr1);
        System.out.println("结果: " + parsedExpr1.interpret(context)); // 10 + 5 * 3 = 25

        String exprStr2 = "(x + y) * z";
        Expression parsedExpr2 = parser.parse(exprStr2);
        System.out.println("\n解析表达式: " + exprStr2);
        System.out.println("抽象语法树: " + parsedExpr2);
        System.out.println("结果: " + parsedExpr2.interpret(context)); // (10 + 5) * 3 = 45

        String exprStr3 = "x * y + z * 2";
        Expression parsedExpr3 = parser.parse(exprStr3);
        System.out.println("\n解析表达式: " + exprStr3);
        System.out.println("抽象语法树: " + parsedExpr3);
        System.out.println("结果: " + parsedExpr3.interpret(context)); // 10 * 5 + 3 * 2 = 56

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n4. 缓存解释器示例:");
        CachedExpressionInterpreter cachedInterpreter = new CachedExpressionInterpreter();
        
        // 第一次执行
        long startTime = System.currentTimeMillis();
        int result1 = cachedInterpreter.interpret("x * y + z", context);
        long firstTime = System.currentTimeMillis() - startTime;
        
        // 第二次执行（从缓存获取）
        startTime = System.currentTimeMillis();
        int result2 = cachedInterpreter.interpret("x * y + z", context);
        long secondTime = System.currentTimeMillis() - startTime;
        
        System.out.println("第一次执行: x * y + z = " + result1 + " (耗时: " + firstTime + "ms)");
        System.out.println("第二次执行: x * y + z = " + result2 + " (耗时: " + secondTime + "ms)");

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n5. 函数和三元表达式示例:");
        // 函数表达式: abs(x - y)
        Expression funcExpr = new FunctionExpression("abs", 
            new SubtractExpression(
                new VariableExpression("x"),
                new VariableExpression("y")
            )
        );
        System.out.println("函数表达式: " + funcExpr);
        System.out.println("结果: " + funcExpr.interpret(context)); // abs(10 - 5) = 5

        // 三元表达式: x > y ? x : y (取较大值)
        Expression ternaryExpr = new TernaryExpression(
            new GreaterThanExpression(
                new VariableExpression("x"),
                new VariableExpression("y")
            ),
            new VariableExpression("x"),  // x > y 时返回 x
            new VariableExpression("y")   // 否则返回 y
        );
        System.out.println("\n三元表达式: " + ternaryExpr);
        System.out.println("结果: " + ternaryExpr.interpret(context)); // x > y ? x : y = 10

        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n解释器模式的关键特性:");
        System.out.println("1. 定义了语言的文法表示");
        System.out.println("2. 用解释器实例来表示语言中的句子");
        System.out.println("3. 容易修改和扩展文法");
        System.out.println("4. 适合于文法简单的语言");
        System.out.println("5. 对于复杂文法会导致类膨胀");
        System.out.println("6. 可以实现表达式解析和缓存优化");
        System.out.println("7. 支持函数和复杂逻辑运算");
    }
}