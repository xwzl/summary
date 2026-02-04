package com.java.interview.java.design.behavioral;

/**
 * 模板方法模式 (Template Method Pattern)
 * 
 * 定义：模板方法模式是一种行为型设计模式，它在超类中定义了一个算法的框架，
 * 允许子类在不修改结构的情况下重定义算法的某些步骤。
 * 
 * 主要角色：
 * 1. 抽象类(Abstract Class)：定义算法的轮廓和框架，包含模板方法和基本方法
 * 2. 模板方法(Template Method)：定义算法的骨架，由基本方法组成
 * 3. 基本方法(Primitive Method)：算法的步骤，可以是抽象的或具体的
 * 4. 具体类(Concrete Class)：实现抽象类中定义的一个或多个抽象方法
 * 
 * 主要特点：
 * 1. 封装不变部分：将不变的行为移到父类
 * 2. 扩展可变部分：将可变的行为留给子类实现
 * 3. 行为控制：子类可以扩展父类的特定方法
 * 
 * 应用场景：
 * - 一次性实现算法的不变部分，并将可变部分留给子类实现
 * - 控制子类扩展，子类可以扩展父类的特定步骤
 * - 代码复用，减少重复代码
 */
public class TemplateMethodPattern {

    /**
     * 抽象类 - 饮料制作模板
     */
    static abstract class BeverageTemplate {
        // 模板方法 - 定义制作饮料的算法骨架
        public final void prepareRecipe() {
            boilWater();
            brew();
            pourInCup();
            addCondiments();
        }

        // 具体方法 - 烧水
        private void boilWater() {
            System.out.println("Boiling water");
        }

        // 具体方法 - 倒入杯中
        private void pourInCup() {
            System.out.println("Pouring into cup");
        }

        // 抽象方法 - 冲泡
        abstract void brew();

        // 抽象方法 - 添加调料
        abstract void addCondiments();

        // 钩子方法 - 子类可以选择性地重写
        boolean customerWantsCondiments() {
            return true; // 默认返回true
        }
    }

    /**
     * 具体类 - 咖啡制作
     */
    static class Coffee extends BeverageTemplate {
        @Override
        void brew() {
            System.out.println("Dripping Coffee through filter");
        }

        @Override
        void addCondiments() {
            System.out.println("Adding Sugar and Milk");
        }
    }

    /**
     * 具体类 - 茶制作
     */
    static class Tea extends BeverageTemplate {
        @Override
        void brew() {
            System.out.println("Steeping the tea");
        }

        @Override
        void addCondiments() {
            System.out.println("Adding Lemon");
        }
    }

    /**
     * 模板方法模式的高级应用 - 游戏框架
     */
    static class GameFrameworkExample {
        // 抽象类 - 游戏模板
        abstract static class GameTemplate {
            // 模板方法 - 游戏主循环
            public final void playGame() {
                initialize();
                startPlay();
                endPlay();
            }

            // 具体方法 - 初始化游戏
            private void initialize() {
                System.out.println("Game Initialized! Start playing.");
            }

            // 具体方法 - 结束游戏
            private void endPlay() {
                System.out.println("Game Finished!");
            }

            // 抽象方法 - 开始游戏
            abstract void startPlay();

            // 抽象方法 - 获取胜利者
            abstract String getWinner();

            // 钩子方法 - 是否需要显示分数
            boolean shouldShowScore() {
                return true;
            }

            // 模板方法 - 显示最终结果
            public final void showResult() {
                System.out.println("Winner: " + getWinner());
                if (shouldShowScore()) {
                    System.out.println("Final Score Displayed");
                }
            }
        }

        // 具体类 - 足球游戏
        static class FootballGame extends GameTemplate {
            private String winner;

            @Override
            void startPlay() {
                System.out.println("Football Game Started. Enjoy the game!");
                // 模拟游戏过程
                simulateGame();
            }

            @Override
            String getWinner() {
                return winner != null ? winner : "Unknown";
            }

            private void simulateGame() {
                // 简化模拟过程
                System.out.println("Football game in progress...");
                winner = "Team A";
            }

            @Override
            boolean shouldShowScore() {
                return true;
            }
        }

        // 具体类 - 国际象棋游戏
        static class ChessGame extends GameTemplate {
            private String winner;

            @Override
            void startPlay() {
                System.out.println("Chess Game Started. Enjoy the game!");
                // 模拟游戏过程
                simulateGame();
            }

            @Override
            String getWinner() {
                return winner != null ? winner : "Draw";
            }

            private void simulateGame() {
                // 简化模拟过程
                System.out.println("Chess game in progress...");
                winner = "Player 1";
            }

            @Override
            boolean shouldShowScore() {
                return false; // 象棋通常不显示分数
            }
        }
    }

    /**
     * 模板方法模式的进一步应用 - 数据处理流程
     */
    static class DataProcessingExample {
        // 抽象类 - 数据处理模板
        abstract static class DataProcessorTemplate {
            // 模板方法 - 数据处理主流程
            public final void processData() {
                System.out.println("Starting data processing...");
                validateInput();
                if (isValid()) {
                    transformData();
                    saveData();
                    notifyCompletion();
                } else {
                    handleError();
                }
                System.out.println("Data processing completed.\n");
            }

            // 具体方法 - 输入验证
            private void validateInput() {
                System.out.println("Validating input data...");
                // 基本验证逻辑
                performValidation();
            }

            // 抽象方法 - 执行具体验证
            abstract void performValidation();

            // 抽象方法 - 转换数据
            abstract void transformData();

            // 抽象方法 - 保存数据
            abstract void saveData();

            // 钩子方法 - 是否有效
            boolean isValid() {
                return true;
            }

            // 具体方法 - 处理错误
            private void handleError() {
                System.out.println("Error: Invalid data. Processing aborted.");
            }

            // 具体方法 - 通知完成
            private void notifyCompletion() {
                System.out.println("Data processing completed successfully.");
            }
        }

        // 具体类 - 用户数据处理器
        static class UserDataProcessor extends DataProcessorTemplate {
            private boolean validationPassed = true;

            @Override
            void performValidation() {
                System.out.println("Validating user data format...");
                // 模拟验证过程
                validationPassed = true; // 假设验证通过
            }

            @Override
            void transformData() {
                System.out.println("Transforming user data...");
                // 执行用户数据转换
            }

            @Override
            void saveData() {
                System.out.println("Saving user data to database...");
                // 保存用户数据
            }

            @Override
            boolean isValid() {
                return validationPassed;
            }
        }

        // 具体类 - 产品数据处理器
        static class ProductDataProcessor extends DataProcessorTemplate {
            private boolean validationPassed = true;

            @Override
            void performValidation() {
                System.out.println("Validating product data format...");
                // 模拟验证过程
                validationPassed = true; // 假设验证通过
            }

            @Override
            void transformData() {
                System.out.println("Transforming product data...");
                // 执行产品数据转换
            }

            @Override
            void saveData() {
                System.out.println("Saving product data to catalog...");
                // 保存产品数据
            }

            @Override
            boolean isValid() {
                return validationPassed;
            }
        }
    }

    /**
     * 模板方法模式的进一步应用 - 报告生成
     */
    static class ReportGenerationExample {
        // 抽象类 - 报告生成模板
        abstract static class ReportGeneratorTemplate {
            // 模板方法 - 生成报告
            public final void generateReport() {
                System.out.println("Generating report...");
                collectData();
                formatData();
                createReport();
                exportReport();
                System.out.println("Report generation completed.\n");
            }

            // 具体方法 - 收集数据
            private void collectData() {
                System.out.println("Collecting data...");
                fetchData();
            }

            // 具体方法 - 格式化数据
            private void formatData() {
                System.out.println("Formatting data...");
                formatSpecificData();
            }

            // 抽象方法 - 获取数据
            abstract void fetchData();

            // 抽象方法 - 格式化特定数据
            abstract void formatSpecificData();

            // 抽象方法 - 创建报告
            abstract void createReport();

            // 抽象方法 - 导出报告
            abstract void exportReport();

            // 钩子方法 - 是否需要图表
            boolean includeCharts() {
                return false;
            }
        }

        // 具体类 - 销售报告生成器
        static class SalesReportGenerator extends ReportGeneratorTemplate {
            @Override
            void fetchData() {
                System.out.println("Fetching sales data from database...");
            }

            @Override
            void formatSpecificData() {
                System.out.println("Formatting sales data...");
            }

            @Override
            void createReport() {
                System.out.println("Creating sales report with tables and charts...");
            }

            @Override
            void exportReport() {
                System.out.println("Exporting sales report to PDF...");
            }

            @Override
            boolean includeCharts() {
                return true; // 销售报告需要图表
            }
        }

        // 具体类 - 用户活动报告生成器
        static class UserActivityReportGenerator extends ReportGeneratorTemplate {
            @Override
            void fetchData() {
                System.out.println("Fetching user activity data from logs...");
            }

            @Override
            void formatSpecificData() {
                System.out.println("Formatting user activity data...");
            }

            @Override
            void createReport() {
                System.out.println("Creating user activity report...");
            }

            @Override
            void exportReport() {
                System.out.println("Exporting user activity report to CSV...");
            }
        }
    }

    /**
     * 模板方法模式的进一步应用 - 认证流程
     */
    static class AuthenticationExample {
        // 抽象类 - 认证流程模板
        abstract static class AuthenticationTemplate {
            // 模板方法 - 认证流程
            public final boolean authenticate(String username, String password) {
                System.out.println("Starting authentication for user: " + username);
                
                if (!preAuthCheck(username)) {
                    System.out.println("Pre-authentication check failed");
                    return false;
                }
                
                if (validateCredentials(username, password)) {
                    postAuthActions(username);
                    System.out.println("Authentication successful for user: " + username);
                    return true;
                } else {
                    System.out.println("Authentication failed for user: " + username);
                    return false;
                }
            }

            // 钩子方法 - 预认证检查
            boolean preAuthCheck(String username) {
                System.out.println("Performing pre-authentication checks...");
                return true; // 默认通过
            }

            // 抽象方法 - 验证凭证
            abstract boolean validateCredentials(String username, String password);

            // 具体方法 - 认证后操作
            private void postAuthActions(String username) {
                System.out.println("Performing post-authentication actions...");
                logSuccessfulAuth(username);
                updateLastLoginTime(username);
            }

            // 具体方法 - 记录成功认证
            private void logSuccessfulAuth(String username) {
                System.out.println("Logging successful authentication for: " + username);
            }

            // 具体方法 - 更新最后登录时间
            private void updateLastLoginTime(String username) {
                System.out.println("Updating last login time for: " + username);
            }
        }

        // 具体类 - 数据库认证
        static class DatabaseAuthentication extends AuthenticationTemplate {
            @Override
            boolean validateCredentials(String username, String password) {
                System.out.println("Validating credentials against database...");
                // 简化验证逻辑
                return "admin".equals(username) && "password".equals(password);
            }
        }

        // 具体类 - LDAP认证
        static class LDAPAuthentication extends AuthenticationTemplate {
            @Override
            boolean validateCredentials(String username, String password) {
                System.out.println("Validating credentials against LDAP...");
                // 简化验证逻辑
                return username.contains("ldap") && password.length() >= 6;
            }

            @Override
            boolean preAuthCheck(String username) {
                System.out.println("Checking if user exists in LDAP directory...");
                return username != null && !username.isEmpty();
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 模板方法模式 (Template Method Pattern) 示例 ===\n");

        // 基础模板方法模式示例 - 饮料制作
        System.out.println("1. 基础模板方法模式示例 - 饮料制作:");
        System.out.println("Making coffee:");
        BeverageTemplate coffee = new Coffee();
        coffee.prepareRecipe();

        System.out.println("\nMaking tea:");
        BeverageTemplate tea = new Tea();
        tea.prepareRecipe();
        System.out.println();

        // 游戏框架示例
        System.out.println("2. 游戏框架示例:");
        GameFrameworkExample.GameTemplate football = new GameFrameworkExample.FootballGame();
        football.playGame();
        football.showResult();

        GameFrameworkExample.GameTemplate chess = new GameFrameworkExample.ChessGame();
        chess.playGame();
        chess.showResult();
        System.out.println();

        // 数据处理示例
        System.out.println("3. 数据处理示例:");
        DataProcessingExample.DataProcessorTemplate userDataProcessor = 
            new DataProcessingExample.UserDataProcessor();
        userDataProcessor.processData();

        DataProcessingExample.DataProcessorTemplate productDataProcessor = 
            new DataProcessingExample.ProductDataProcessor();
        productDataProcessor.processData();
        System.out.println();

        // 报告生成示例
        System.out.println("4. 报告生成示例:");
        ReportGenerationExample.ReportGeneratorTemplate salesReport = 
            new ReportGenerationExample.SalesReportGenerator();
        salesReport.generateReport();

        ReportGenerationExample.ReportGeneratorTemplate userActivityReport = 
            new ReportGenerationExample.UserActivityReportGenerator();
        userActivityReport.generateReport();
        System.out.println();

        // 认证流程示例
        System.out.println("5. 认证流程示例:");
        AuthenticationExample.AuthenticationTemplate dbAuth = 
            new AuthenticationExample.DatabaseAuthentication();
        dbAuth.authenticate("admin", "password");
        dbAuth.authenticate("user", "wrongpass");

        AuthenticationExample.AuthenticationTemplate ldapAuth = 
            new AuthenticationExample.LDAPAuthentication();
        ldapAuth.authenticate("ldap_user", "valid_password123");
        ldapAuth.authenticate("", "password");
        System.out.println();

        System.out.println("=== 模板方法模式的关键特性 ===");
        System.out.println("1. 封装不变部分: 将不变的行为移到父类");
        System.out.println("2. 扩展可变部分: 将可变的行为留给子类实现");
        System.out.println("3. 行为控制: 子类可以扩展父类的特定方法");
        System.out.println("4. 代码复用: 减少重复代码");
        System.out.println("5. 算法骨架: 定义了算法的执行框架");
        System.out.println("6. 钩子方法: 允许子类选择性地扩展算法步骤");
    }
}