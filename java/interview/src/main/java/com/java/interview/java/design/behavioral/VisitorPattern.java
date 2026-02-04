package com.java.interview.java.design.behavioral;

/**
 * 访问者模式 (Visitor Pattern)
 * 
 * 定义：访问者模式是一种行为型设计模式，它允许你在不改变集合元素的前提下，
 * 为一个元素集合定义新的操作。访问者模式将算法与对象结构分离。
 * 
 * 主要角色：
 * 1. 抽象访问者(Visitor)：声明访问者可以访问的元素操作
 * 2. 具体访问者(Concrete Visitor)：实现抽象访问者接口，定义对各元素的访问操作
 * 3. 抽象元素(Element)：声明接受访问者的accept方法
 * 4. 具体元素(Concrete Element)：实现accept方法，调用访问者的访问方法
 * 5. 对象结构(Object Structure)：包含元素集合，提供遍历元素的方法
 * 
 * 主要特点：
 * 1. 分离算法和对象结构：将操作从对象结构中分离出来
 * 2. 增加新操作：可以轻松添加新的操作而不修改现有代码
 * 3. 集中控制：访问者集中控制对对象结构的访问
 * 
 * 应用场景：
 * - 对象结构比较稳定，但经常需要在此结构上定义新的操作
 * - 需要对一个对象结构中的对象进行很多不同的并且不相关的操作
 * - 需要把数据结构和作用于数据结构上的操作解耦
 */
public class VisitorPattern {

    /**
     * 抽象访问者 - 员工访问者
     */
    interface EmployeeVisitor {
        void visit(Developer developer);
        void visit(Manager manager);
        void visit(Intern intern);
    }

    /**
     * 抽象元素 - 员工接口
     */
    interface Employee {
        void accept(EmployeeVisitor visitor);
        String getName();
        double getSalary();
        String getPosition();
    }

    /**
     * 具体元素 - 开发者
     */
    static class Developer implements Employee {
        private String name;
        private double salary;
        private int codeLines;

        public Developer(String name, double salary, int codeLines) {
            this.name = name;
            this.salary = salary;
            this.codeLines = codeLines;
        }

        @Override
        public void accept(EmployeeVisitor visitor) {
            visitor.visit(this);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public double getSalary() {
            return salary;
        }

        @Override
        public String getPosition() {
            return "Developer";
        }

        public int getCodeLines() {
            return codeLines;
        }
    }

    /**
     * 具体元素 - 经理
     */
    static class Manager implements Employee {
        private String name;
        private double salary;
        private int subordinates;

        public Manager(String name, double salary, int subordinates) {
            this.name = name;
            this.salary = salary;
            this.subordinates = subordinates;
        }

        @Override
        public void accept(EmployeeVisitor visitor) {
            visitor.visit(this);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public double getSalary() {
            return salary;
        }

        @Override
        public String getPosition() {
            return "Manager";
        }

        public int getSubordinates() {
            return subordinates;
        }
    }

    /**
     * 具体元素 - 实习生
     */
    static class Intern implements Employee {
        private String name;
        private double salary;
        private int durationInMonths;

        public Intern(String name, double salary, int durationInMonths) {
            this.name = name;
            this.salary = salary;
            this.durationInMonths = durationInMonths;
        }

        @Override
        public void accept(EmployeeVisitor visitor) {
            visitor.visit(this);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public double getSalary() {
            return salary;
        }

        @Override
        public String getPosition() {
            return "Intern";
        }

        public int getDurationInMonths() {
            return durationInMonths;
        }
    }

    /**
     * 具体访问者 - 薪资访问者
     */
    static class SalaryVisitor implements EmployeeVisitor {
        private double totalSalary = 0;

        @Override
        public void visit(Developer developer) {
            System.out.println("Developer " + developer.getName() + 
                             " salary: $" + developer.getSalary() + 
                             " (Code Lines: " + developer.getCodeLines() + ")");
            totalSalary += developer.getSalary();
        }

        @Override
        public void visit(Manager manager) {
            System.out.println("Manager " + manager.getName() + 
                             " salary: $" + manager.getSalary() + 
                             " (Subordinates: " + manager.getSubordinates() + ")");
            totalSalary += manager.getSalary();
        }

        @Override
        public void visit(Intern intern) {
            System.out.println("Intern " + intern.getName() + 
                             " salary: $" + intern.getSalary() + 
                             " (Duration: " + intern.getDurationInMonths() + " months)");
            totalSalary += intern.getSalary();
        }

        public double getTotalSalary() {
            return totalSalary;
        }
    }

    /**
     * 具体访问者 - 绩效访问者
     */
    static class PerformanceVisitor implements EmployeeVisitor {
        @Override
        public void visit(Developer developer) {
            double performance = developer.getCodeLines() * 0.01; // 简化绩效计算
            System.out.println("Developer " + developer.getName() + 
                             " performance score: " + performance);
        }

        @Override
        public void visit(Manager manager) {
            double performance = manager.getSubordinates() * 0.5; // 简化绩效计算
            System.out.println("Manager " + manager.getName() + 
                             " performance score: " + performance);
        }

        @Override
        public void visit(Intern intern) {
            double performance = intern.getDurationInMonths() * 0.2; // 简化绩效计算
            System.out.println("Intern " + intern.getName() + 
                             " performance score: " + performance);
        }
    }

    /**
     * 具体访问者 - 晋升访问者
     */
    static class PromotionVisitor implements EmployeeVisitor {
        @Override
        public void visit(Developer developer) {
            if (developer.getCodeLines() > 1000) {
                System.out.println("Developer " + developer.getName() + 
                                 " is eligible for promotion to Senior Developer");
            } else {
                System.out.println("Developer " + developer.getName() + 
                                 " needs more experience for promotion");
            }
        }

        @Override
        public void visit(Manager manager) {
            if (manager.getSubordinates() > 5) {
                System.out.println("Manager " + manager.getName() + 
                                 " is eligible for promotion to Senior Manager");
            } else {
                System.out.println("Manager " + manager.getName() + 
                                 " needs to manage more people for promotion");
            }
        }

        @Override
        public void visit(Intern intern) {
            if (intern.getDurationInMonths() > 6) {
                System.out.println("Intern " + intern.getName() + 
                                 " is eligible for conversion to full-time employee");
            } else {
                System.out.println("Intern " + intern.getName() + 
                                 " needs to complete internship period");
            }
        }
    }

    /**
     * 对象结构 - 公司
     */
    static class Company {
        private java.util.List<Employee> employees = new java.util.ArrayList<>();

        public void addEmployee(Employee employee) {
            employees.add(employee);
        }

        public void accept(EmployeeVisitor visitor) {
            for (Employee employee : employees) {
                employee.accept(visitor);
            }
        }

        public java.util.List<Employee> getEmployees() {
            return employees;
        }
    }

    /**
     * 访问者模式的高级应用 - 文件系统
     */
    static class FileSystemExample {
        // 抽象访问者 - 文件系统访问者
        interface FileSystemVisitor {
            void visit(File file);
            void visit(Directory directory);
        }

        // 抽象元素 - 文件系统元素
        interface FileSystemElement {
            void accept(FileSystemVisitor visitor);
            String getName();
            int getSize();
        }

        // 具体元素 - 文件
        static class File implements FileSystemElement {
            private String name;
            private int size;

            public File(String name, int size) {
                this.name = name;
                this.size = size;
            }

            @Override
            public void accept(FileSystemVisitor visitor) {
                visitor.visit(this);
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public int getSize() {
                return size;
            }
        }

        // 具体元素 - 目录
        static class Directory implements FileSystemElement {
            private String name;
            private java.util.List<FileSystemElement> children = new java.util.ArrayList<>();

            public Directory(String name) {
                this.name = name;
            }

            public void addElement(FileSystemElement element) {
                children.add(element);
            }

            @Override
            public void accept(FileSystemVisitor visitor) {
                visitor.visit(this);
                for (FileSystemElement element : children) {
                    element.accept(visitor);
                }
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public int getSize() {
                int totalSize = 0;
                for (FileSystemElement element : children) {
                    totalSize += element.getSize();
                }
                return totalSize;
            }

            public java.util.List<FileSystemElement> getChildren() {
                return children;
            }
        }

        // 具体访问者 - 文件大小访问者
        static class SizeVisitor implements FileSystemVisitor {
            private int totalSize = 0;

            @Override
            public void visit(File file) {
                System.out.println("File: " + file.getName() + ", Size: " + file.getSize() + " bytes");
                totalSize += file.getSize();
            }

            @Override
            public void visit(Directory directory) {
                System.out.println("Directory: " + directory.getName() + ", Size: " + directory.getSize() + " bytes");
            }

            public int getTotalSize() {
                return totalSize;
            }
        }

        // 具体访问者 - 搜索访问者
        static class SearchVisitor implements FileSystemVisitor {
            private String searchTerm;
            private java.util.List<FileSystemElement> foundElements = new java.util.ArrayList<>();

            public SearchVisitor(String searchTerm) {
                this.searchTerm = searchTerm.toLowerCase();
            }

            @Override
            public void visit(File file) {
                if (file.getName().toLowerCase().contains(searchTerm)) {
                    foundElements.add(file);
                    System.out.println("Found file: " + file.getName());
                }
            }

            @Override
            public void visit(Directory directory) {
                if (directory.getName().toLowerCase().contains(searchTerm)) {
                    foundElements.add(directory);
                    System.out.println("Found directory: " + directory.getName());
                }
            }

            public java.util.List<FileSystemElement> getFoundElements() {
                return foundElements;
            }
        }

        // 对象结构 - 文件系统
        static class FileSystem {
            private Directory root;

            public FileSystem(Directory root) {
                this.root = root;
            }

            public void accept(FileSystemVisitor visitor) {
                root.accept(visitor);
            }
        }
    }

    /**
     * 访问者模式的进一步应用 - 表达式求值
     */
    static class ExpressionEvaluatorExample {
        // 抽象访问者 - 表达式访问者
        interface ExpressionVisitor<T> {
            T visit(NumberExpression expression);
            T visit(AdditionExpression expression);
            T visit(MultiplicationExpression expression);
            T visit(SubtractionExpression expression);
        }

        // 抽象元素 - 表达式
        interface Expression {
            <T> T accept(ExpressionVisitor<T> visitor);
        }

        // 具体元素 - 数字表达式
        static class NumberExpression implements Expression {
            private int value;

            public NumberExpression(int value) {
                this.value = value;
            }

            @Override
            public <T> T accept(ExpressionVisitor<T> visitor) {
                return visitor.visit(this);
            }

            public int getValue() {
                return value;
            }
        }

        // 具体元素 - 加法表达式
        static class AdditionExpression implements Expression {
            private Expression left;
            private Expression right;

            public AdditionExpression(Expression left, Expression right) {
                this.left = left;
                this.right = right;
            }

            @Override
            public <T> T accept(ExpressionVisitor<T> visitor) {
                return visitor.visit(this);
            }

            public Expression getLeft() {
                return left;
            }

            public Expression getRight() {
                return right;
            }
        }

        // 具体元素 - 乘法表达式
        static class MultiplicationExpression implements Expression {
            private Expression left;
            private Expression right;

            public MultiplicationExpression(Expression left, Expression right) {
                this.left = left;
                this.right = right;
            }

            @Override
            public <T> T accept(ExpressionVisitor<T> visitor) {
                return visitor.visit(this);
            }

            public Expression getLeft() {
                return left;
            }

            public Expression getRight() {
                return right;
            }
        }

        // 具体元素 - 减法表达式
        static class SubtractionExpression implements Expression {
            private Expression left;
            private Expression right;

            public SubtractionExpression(Expression left, Expression right) {
                this.left = left;
                this.right = right;
            }

            @Override
            public <T> T accept(ExpressionVisitor<T> visitor) {
                return visitor.visit(this);
            }

            public Expression getLeft() {
                return left;
            }

            public Expression getRight() {
                return right;
            }
        }

        // 具体访问者 - 计算访问者
        static class CalculatorVisitor implements ExpressionVisitor<Integer> {
            @Override
            public Integer visit(NumberExpression expression) {
                return expression.getValue();
            }

            @Override
            public Integer visit(AdditionExpression expression) {
                return expression.getLeft().accept(this) + expression.getRight().accept(this);
            }

            @Override
            public Integer visit(MultiplicationExpression expression) {
                return expression.getLeft().accept(this) * expression.getRight().accept(this);
            }

            @Override
            public Integer visit(SubtractionExpression expression) {
                return expression.getLeft().accept(this) - expression.getRight().accept(this);
            }
        }

        // 具体访问者 - 打印访问者
        static class PrintVisitor implements ExpressionVisitor<String> {
            @Override
            public String visit(NumberExpression expression) {
                return String.valueOf(expression.getValue());
            }

            @Override
            public String visit(AdditionExpression expression) {
                return "(" + expression.getLeft().accept(this) + " + " + 
                       expression.getRight().accept(this) + ")";
            }

            @Override
            public String visit(MultiplicationExpression expression) {
                return "(" + expression.getLeft().accept(this) + " * " + 
                       expression.getRight().accept(this) + ")";
            }

            @Override
            public String visit(SubtractionExpression expression) {
                return "(" + expression.getLeft().accept(this) + " - " + 
                       expression.getRight().accept(this) + ")";
            }
        }
    }

    /**
     * 访问者模式的进一步应用 - 文档处理
     */
    static class DocumentProcessingExample {
        // 抽象访问者 - 文档访问者
        interface DocumentVisitor {
            void visit(TextDocument document);
            void visit(ImageDocument document);
            void visit(PdfDocument document);
        }

        // 抽象元素 - 文档
        interface Document {
            void accept(DocumentVisitor visitor);
            String getTitle();
            String getAuthor();
        }

        // 具体元素 - 文本文档
        static class TextDocument implements Document {
            private String title;
            private String author;
            private String content;

            public TextDocument(String title, String author, String content) {
                this.title = title;
                this.author = author;
                this.content = content;
            }

            @Override
            public void accept(DocumentVisitor visitor) {
                visitor.visit(this);
            }

            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getAuthor() {
                return author;
            }

            public String getContent() {
                return content;
            }
        }

        // 具体元素 - 图片文档
        static class ImageDocument implements Document {
            private String title;
            private String author;
            private String imagePath;
            private int width;
            private int height;

            public ImageDocument(String title, String author, String imagePath, int width, int height) {
                this.title = title;
                this.author = author;
                this.imagePath = imagePath;
                this.width = width;
                this.height = height;
            }

            @Override
            public void accept(DocumentVisitor visitor) {
                visitor.visit(this);
            }

            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getAuthor() {
                return author;
            }

            public String getImagePath() {
                return imagePath;
            }

            public int getWidth() {
                return width;
            }

            public int getHeight() {
                return height;
            }
        }

        // 具体元素 - PDF文档
        static class PdfDocument implements Document {
            private String title;
            private String author;
            private String filePath;
            private int pageCount;

            public PdfDocument(String title, String author, String filePath, int pageCount) {
                this.title = title;
                this.author = author;
                this.filePath = filePath;
                this.pageCount = pageCount;
            }

            @Override
            public void accept(DocumentVisitor visitor) {
                visitor.visit(this);
            }

            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getAuthor() {
                return author;
            }

            public String getFilePath() {
                return filePath;
            }

            public int getPageCount() {
                return pageCount;
            }
        }

        // 具体访问者 - 导出访问者
        static class ExportVisitor implements DocumentVisitor {
            @Override
            public void visit(TextDocument document) {
                System.out.println("Exporting text document: " + document.getTitle() + 
                                 " by " + document.getAuthor());
                System.out.println("Content preview: " + 
                                 document.getContent().substring(0, Math.min(50, document.getContent().length())) + "...");
            }

            @Override
            public void visit(ImageDocument document) {
                System.out.println("Exporting image document: " + document.getTitle() + 
                                 " by " + document.getAuthor());
                System.out.println("Image: " + document.getImagePath() + 
                                 " (" + document.getWidth() + "x" + document.getHeight() + ")");
            }

            @Override
            public void visit(PdfDocument document) {
                System.out.println("Exporting PDF document: " + document.getTitle() + 
                                 " by " + document.getAuthor());
                System.out.println("PDF: " + document.getFilePath() + 
                                 " (" + document.getPageCount() + " pages)");
            }
        }

        // 具体访问者 - 统计访问者
        static class StatisticsVisitor implements DocumentVisitor {
            private int textCount = 0;
            private int imageCount = 0;
            private int pdfCount = 0;

            @Override
            public void visit(TextDocument document) {
                textCount++;
                System.out.println("Text document: " + document.getTitle() + 
                                 " has " + document.getContent().split("\\s+").length + " words");
            }

            @Override
            public void visit(ImageDocument document) {
                imageCount++;
                System.out.println("Image document: " + document.getTitle() + 
                                 " is " + document.getWidth() + "x" + document.getHeight() + " pixels");
            }

            @Override
            public void visit(PdfDocument document) {
                pdfCount++;
                System.out.println("PDF document: " + document.getTitle() + 
                                 " has " + document.getPageCount() + " pages");
            }

            public void printStatistics() {
                System.out.println("\nDocument Statistics:");
                System.out.println("Text Documents: " + textCount);
                System.out.println("Image Documents: " + imageCount);
                System.out.println("PDF Documents: " + pdfCount);
                System.out.println("Total Documents: " + (textCount + imageCount + pdfCount));
            }
        }

        // 对象结构 - 文档管理系统
        static class DocumentManager {
            private java.util.List<Document> documents = new java.util.ArrayList<>();

            public void addDocument(Document document) {
                documents.add(document);
            }

            public void accept(DocumentVisitor visitor) {
                for (Document document : documents) {
                    document.accept(visitor);
                }
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 访问者模式 (Visitor Pattern) 示例 ===\n");

        // 基础访问者模式示例 - 员工管理系统
        System.out.println("1. 基础访问者模式示例 - 员工管理系统:");
        Company company = new Company();
        company.addEmployee(new Developer("Alice", 80000, 5000));
        company.addEmployee(new Manager("Bob", 120000, 10));
        company.addEmployee(new Developer("Charlie", 75000, 4500));
        company.addEmployee(new Intern("David", 20000, 6));

        System.out.println("Visiting employees with SalaryVisitor:");
        SalaryVisitor salaryVisitor = new SalaryVisitor();
        company.accept(salaryVisitor);
        System.out.println("Total salary: $" + salaryVisitor.getTotalSalary());

        System.out.println("\nVisiting employees with PerformanceVisitor:");
        PerformanceVisitor performanceVisitor = new PerformanceVisitor();
        company.accept(performanceVisitor);

        System.out.println("\nVisiting employees with PromotionVisitor:");
        PromotionVisitor promotionVisitor = new PromotionVisitor();
        company.accept(promotionVisitor);
        System.out.println();

        // 文件系统示例
        System.out.println("2. 文件系统示例:");
        FileSystemExample.Directory rootDir = new FileSystemExample.Directory("root");
        FileSystemExample.Directory docsDir = new FileSystemExample.Directory("documents");
        FileSystemExample.Directory picsDir = new FileSystemExample.Directory("pictures");

        docsDir.addElement(new FileSystemExample.File("report.txt", 1024));
        docsDir.addElement(new FileSystemExample.File("presentation.pptx", 2048));
        picsDir.addElement(new FileSystemExample.File("photo1.jpg", 5120));
        picsDir.addElement(new FileSystemExample.File("photo2.png", 3072));

        rootDir.addElement(docsDir);
        rootDir.addElement(picsDir);
        rootDir.addElement(new FileSystemExample.File("readme.txt", 256));

        FileSystemExample.FileSystem fileSystem = new FileSystemExample.FileSystem(rootDir);

        System.out.println("Calculating total size:");
        FileSystemExample.SizeVisitor sizeVisitor = new FileSystemExample.SizeVisitor();
        fileSystem.accept(sizeVisitor);
        System.out.println("Total size: " + sizeVisitor.getTotalSize() + " bytes");

        System.out.println("\nSearching for files containing 'photo':");
        FileSystemExample.SearchVisitor searchVisitor = new FileSystemExample.SearchVisitor("photo");
        fileSystem.accept(searchVisitor);
        System.out.println();

        // 表达式求值示例
        System.out.println("3. 表达式求值示例:");
        // 构建表达式: (3 + 5) * (2 - 1)
        ExpressionEvaluatorExample.Expression expr = 
            new ExpressionEvaluatorExample.MultiplicationExpression(
                new ExpressionEvaluatorExample.AdditionExpression(
                    new ExpressionEvaluatorExample.NumberExpression(3),
                    new ExpressionEvaluatorExample.NumberExpression(5)
                ),
                new ExpressionEvaluatorExample.SubtractionExpression(
                    new ExpressionEvaluatorExample.NumberExpression(2),
                    new ExpressionEvaluatorExample.NumberExpression(1)
                )
            );

        ExpressionEvaluatorExample.PrintVisitor printVisitor = new ExpressionEvaluatorExample.PrintVisitor();
        String expressionStr = expr.accept(printVisitor);
        System.out.println("Expression: " + expressionStr);

        ExpressionEvaluatorExample.CalculatorVisitor calcVisitor = new ExpressionEvaluatorExample.CalculatorVisitor();
        int result = expr.accept(calcVisitor);
        System.out.println("Result: " + result);
        System.out.println();

        // 文档处理示例
        System.out.println("4. 文档处理示例:");
        DocumentProcessingExample.DocumentManager docManager = 
            new DocumentProcessingExample.DocumentManager();
        
        docManager.addDocument(new DocumentProcessingExample.TextDocument(
            "Java Tutorial", "John Doe", "This is a comprehensive tutorial on Java programming..."));
        docManager.addDocument(new DocumentProcessingExample.ImageDocument(
            "Logo Design", "Jane Smith", "/images/logo.png", 800, 600));
        docManager.addDocument(new DocumentProcessingExample.PdfDocument(
            "Annual Report", "Company Inc.", "/docs/annual_report.pdf", 50));

        System.out.println("Exporting documents:");
        DocumentProcessingExample.ExportVisitor exportVisitor = 
            new DocumentProcessingExample.ExportVisitor();
        docManager.accept(exportVisitor);

        System.out.println("\nAnalyzing document statistics:");
        DocumentProcessingExample.StatisticsVisitor statsVisitor = 
            new DocumentProcessingExample.StatisticsVisitor();
        docManager.accept(statsVisitor);
        statsVisitor.printStatistics();

        System.out.println("\n=== 访问者模式的关键特性 ===");
        System.out.println("1. 分离算法和对象结构: 将操作从对象结构中分离出来");
        System.out.println("2. 增加新操作: 可以轻松添加新的操作而不修改现有代码");
        System.out.println("3. 集中控制: 访问者集中控制对对象结构的访问");
        System.out.println("4. 双访模式: 实现双分派机制");
        System.out.println("5. 扩展性: 新增访问者无需修改现有元素类");
        System.out.println("6. 灵活性: 同一元素集合可以有不同的访问者实现");
    }
}