package com.java.interview.java.design.behavioral;

import java.util.*;

/**
 * 迭代器模式 (Iterator Pattern)
 *
 * 定义：迭代器模式是一种行为型设计模式，它提供一种方法来顺序访问聚合对象中的各个元素，
 * 而又不暴露其内部表示。迭代器模式将数据结构的遍历功能从业务逻辑中分离出来，
 * 使得遍历算法的变化不会影响数据结构的实现。
 *
 * 主要角色：
 * 1. 抽象迭代器(Iterator)：定义访问和遍历元素的接口
 * 2. 具体迭代器(Concrete Iterator)：实现抽象迭代器接口，跟踪遍历过程中的当前位置
 * 3. 聚合(Aggregate)：定义创建相应迭代器对象的接口
 * 4. 具体聚合(Concrete Aggregate)：实现创建相应迭代器的接口
 *
 * 主要特点：
 * 1. 支持以不同的方式遍历一个聚合对象
 * 2. 迭代器简化了聚合的接口
 * 3. 在同一个聚合上可以有多个遍历
 * 4. 在迭代器模式中，增加新的聚合类和迭代器类都很方便
 *
 * 应用场景：
 * - 访问聚合对象的内容而不暴露其内部表示
 * - 需要为聚合对象提供多种遍历方式
 * - 为遍历不同的聚合结构提供一个统一的接口
 */
public class IteratorPattern {

    /**
     * 抽象迭代器接口 - 定义访问和遍历元素的接口
     */
    interface Iterator<T> {
        boolean hasNext();
        T next();
        void remove();
    }

    /**
     * 聚合接口 - 定义创建迭代器对象的接口
     */
    interface Aggregate<T> {
        Iterator<T> createIterator();
    }

    /**
     * 自定义书籍集合类 - 具体聚合
     */
    static class BookCollection implements Aggregate<Book> {
        private List<Book> books = new ArrayList<>();
        private String collectionName;

        public BookCollection(String collectionName) {
            this.collectionName = collectionName;
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public void removeBook(Book book) {
            books.remove(book);
        }

        public int size() {
            return books.size();
        }

        @Override
        public Iterator<Book> createIterator() {
            return new BookIterator();
        }

        /**
         * 具体迭代器 - 实现抽象迭代器接口
         */
        private class BookIterator implements Iterator<Book> {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < books.size();
            }

            @Override
            public Book next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("没有更多元素");
                }
                return books.get(currentIndex++);
            }

            @Override
            public void remove() {
                if (currentIndex <= 0) {
                    throw new IllegalStateException("无法删除元素");
                }
                books.remove(--currentIndex);
            }
        }
    }

    /**
     * 书籍类 - 表示集合中的元素
     */
    static class Book {
        private String title;
        private String author;
        private double price;

        public Book(String title, String author, double price) {
            this.title = title;
            this.author = author;
            this.price = price;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public double getPrice() { return price; }

        @Override
        public String toString() {
            return "Book{title='" + title + "', author='" + author + "', price=" + price + "}";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Book book = (Book) obj;
            return Double.compare(book.price, price) == 0 &&
                   Objects.equals(title, book.title) &&
                   Objects.equals(author, book.author);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, author, price);
        }
    }

    /**
     * 双向迭代器接口 - 扩展基础迭代器，支持向前和向后遍历
     */
    interface BidirectionalIterator<T> extends Iterator<T> {
        boolean hasPrevious();
        T previous();
        void moveToFirst();
        void moveToLast();
    }

    /**
     * 双向书籍迭代器
     */
    static class BidirectionalBookIterator implements BidirectionalIterator<Book> {
        private List<Book> books;
        private int currentIndex = 0;

        public BidirectionalBookIterator(List<Book> books) {
            this.books = books;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < books.size();
        }

        @Override
        public Book next() {
            if (!hasNext()) {
                throw new NoSuchElementException("没有更多元素");
            }
            return books.get(currentIndex++);
        }

        @Override
        public void remove() {
            if (currentIndex <= 0) {
                throw new IllegalStateException("无法删除元素");
            }
            books.remove(--currentIndex);
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public Book previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("没有前面的元素");
            }
            return books.get(--currentIndex);
        }

        @Override
        public void moveToFirst() {
            currentIndex = 0;
        }

        @Override
        public void moveToLast() {
            currentIndex = books.size();
        }
    }

    /**
     * 过滤迭代器 - 在遍历时应用过滤条件
     */
    static class FilteredBookIterator implements Iterator<Book> {
        private Iterator<Book> iterator;
        private java.util.function.Predicate<Book> filter;
        private Book nextBook = null;
        private boolean nextBookReady = false;

        public FilteredBookIterator(Iterator<Book> iterator, java.util.function.Predicate<Book> filter) {
            this.iterator = iterator;
            this.filter = filter;
        }

        @Override
        public boolean hasNext() {
            if (nextBookReady) {
                return nextBook != null;
            }

            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (filter.test(book)) {
                    nextBook = book;
                    nextBookReady = true;
                    return true;
                }
            }
            nextBook = null;
            return false;
        }

        @Override
        public Book next() {
            if (!hasNext()) {
                throw new NoSuchElementException("没有更多符合条件的元素");
            }
            Book result = nextBook;
            nextBook = null;
            nextBookReady = false;
            return result;
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }

    /**
     * 组合迭代器 - 将多个迭代器组合成一个
     */
    static class CompositeIterator<T> implements Iterator<T> {
        private List<Iterator<T>> iterators;
        private int currentIteratorIndex = 0;

        public CompositeIterator(List<Iterator<T>> iterators) {
            this.iterators = iterators;
        }

        @Override
        public boolean hasNext() {
            while (currentIteratorIndex < iterators.size()) {
                if (iterators.get(currentIteratorIndex).hasNext()) {
                    return true;
                }
                currentIteratorIndex++;
            }
            return false;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("没有更多元素");
            }
            return iterators.get(currentIteratorIndex).next();
        }

        @Override
        public void remove() {
            iterators.get(currentIteratorIndex).remove();
        }
    }

    /**
     * 空安全迭代器 - 处理null值的安全迭代
     */
    static class SafeBookIterator implements Iterator<Book> {
        private Iterator<Book> iterator;
        private boolean skipNulls;

        public SafeBookIterator(Iterator<Book> iterator, boolean skipNulls) {
            this.iterator = iterator;
            this.skipNulls = skipNulls;
        }

        @Override
        public boolean hasNext() {
            if (!skipNulls) {
                return iterator.hasNext();
            }

            while (iterator.hasNext()) {
                if (iterator.next() != null) {
                    // 如果遇到非null元素，需要回退一步
                    // 这里简化处理，实际应用中可能需要更复杂的逻辑
                    break;
                }
            }
            return iterator.hasNext();
        }

        @Override
        public Book next() {
            Book book = iterator.next();
            if (skipNulls && book == null) {
                return next(); // 递归跳过null值
            }
            return book;
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }

    /**
     * 进阶用法：流式迭代器
     * 支持链式操作的迭代器
     */
    static class StreamBookIterator implements Iterator<Book> {
        private Iterator<Book> iterator;
        private java.util.function.Function<Book, Book> transformer;
        private java.util.function.Predicate<Book> filter;

        public StreamBookIterator(Iterator<Book> iterator) {
            this.iterator = iterator;
        }

        public StreamBookIterator filter(java.util.function.Predicate<Book> predicate) {
            this.filter = predicate;
            return this;
        }

        public StreamBookIterator map(java.util.function.Function<Book, Book> function) {
            this.transformer = function;
            return this;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Book next() {
            Book book = iterator.next();
            
            if (filter != null && !filter.test(book)) {
                return next(); // 跳过不符合条件的元素
            }
            
            if (transformer != null) {
                book = transformer.apply(book);
            }
            
            return book;
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }

    /**
     * 进阶用法：并发安全迭代器
     * 在多线程环境下安全的迭代器
     */
    static class ConcurrentBookIterator implements Iterator<Book> {
        private final List<Book> books;
        private final Object lock = new Object();
        private volatile int currentIndex = 0;

        public ConcurrentBookIterator(List<Book> books) {
            this.books = new ArrayList<>(books); // 创建副本以保证一致性
        }

        @Override
        public boolean hasNext() {
            synchronized (lock) {
                return currentIndex < books.size();
            }
        }

        @Override
        public Book next() {
            synchronized (lock) {
                if (!hasNext()) {
                    throw new NoSuchElementException("没有更多元素");
                }
                return books.get(currentIndex++);
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("并发迭代器不支持删除操作");
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 迭代器模式示例 ===\n");

        // 创建书籍集合
        BookCollection fictionBooks = new BookCollection("小说类");
        fictionBooks.addBook(new Book("《百年孤独》", "加西亚·马尔克斯", 45.0));
        fictionBooks.addBook(new Book("《1984》", "乔治·奥威尔", 32.0));
        fictionBooks.addBook(new Book("《哈利·波特》", "J.K.罗琳", 58.0));

        BookCollection techBooks = new BookCollection("技术类");
        techBooks.addBook(new Book("《设计模式》", "GoF", 89.0));
        techBooks.addBook(new Book("《Java编程思想》", "Bruce Eckel", 128.0));
        techBooks.addBook(new Book("《重构》", "Martin Fowler", 75.0));

        System.out.println("1. 基础迭代器示例:");
        // 使用迭代器遍历小说类书籍
        Iterator<Book> fictionIterator = fictionBooks.createIterator();
        System.out.println("小说类书籍列表:");
        while (fictionIterator.hasNext()) {
            Book book = fictionIterator.next();
            System.out.println("  " + book);
        }

        System.out.println("\n2. 双向迭代器示例:");
        BidirectionalBookIterator bidirectionalIterator = 
            new BidirectionalBookIterator(fictionBooks.createIterator().getClass().getSimpleName().equals("BookIterator") ? 
            fictionBooks.books : fictionBooks.books);
        
        // 创建双向迭代器
        BidirectionalBookIterator biIterator = new BidirectionalBookIterator(fictionBooks.books);
        System.out.println("双向遍历小说类书籍:");
        
        // 向前遍历
        System.out.println("向前遍历:");
        while (biIterator.hasNext()) {
            System.out.println("  " + biIterator.next());
        }
        
        // 向后遍历
        System.out.println("\n向后遍历:");
        while (biIterator.hasPrevious()) {
            System.out.println("  " + biIterator.previous());
        }

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n3. 过滤迭代器示例:");
        // 创建价格高于50的书籍过滤器
        FilteredBookIterator expensiveBookIterator = new FilteredBookIterator(
            techBooks.createIterator(),
            book -> book.getPrice() > 70
        );
        
        System.out.println("价格高于70的技术书籍:");
        while (expensiveBookIterator.hasNext()) {
            System.out.println("  " + expensiveBookIterator.next());
        }

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n4. 组合迭代器示例:");
        // 将小说类和技术类书籍的迭代器组合起来
        List<Iterator<Book>> iterators = Arrays.asList(
            fictionBooks.createIterator(),
            techBooks.createIterator()
        );
        CompositeIterator<Book> compositeIterator = new CompositeIterator<>(iterators);
        
        System.out.println("所有书籍 (组合迭代器):");
        int count = 1;
        while (compositeIterator.hasNext()) {
            System.out.println(count++ + ". " + compositeIterator.next());
        }

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n5. 流式迭代器示例:");
        // 使用流式迭代器进行链式操作
        StreamBookIterator streamIterator = new StreamBookIterator(fictionBooks.createIterator())
            .filter(book -> book.getTitle().contains("《"))
            .map(book -> new Book(
                book.getTitle().toUpperCase(), 
                book.getAuthor(), 
                book.getPrice()
            ));
        
        System.out.println("经过过滤和转换的小说类书籍:");
        while (streamIterator.hasNext()) {
            System.out.println("  " + streamIterator.next());
        }

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n6. 并发安全迭代器示例:");
        // 创建并发安全迭代器
        ConcurrentBookIterator concurrentIterator = new ConcurrentBookIterator(techBooks.books);
        
        System.out.println("并发安全迭代器遍历技术类书籍:");
        while (concurrentIterator.hasNext()) {
            System.out.println("  " + concurrentIterator.next());
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n迭代器模式的关键特性:");
        System.out.println("1. 提供统一的遍历接口");
        System.out.println("2. 隐藏聚合对象的内部结构");
        System.out.println("3. 支持多种遍历方式");
        System.out.println("4. 支持并发安全遍历");
        System.out.println("5. 可以实现过滤、映射等高级功能");
        System.out.println("6. 支持组合多个迭代器");
        System.out.println("7. 便于扩展新的遍历算法");
    }
}