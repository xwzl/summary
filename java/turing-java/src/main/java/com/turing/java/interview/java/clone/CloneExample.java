package com.turing.java.interview.java.clone;

/**
 * 克隆对象
 *
 * @author xuweizhi
 * @since 2021/02/24 23:11
 */
@SuppressWarnings("all")
public class CloneExample implements Cloneable {

    private int baseType;

    private CloneInner cloneInner;

    public CloneExample() {
    }

    public CloneExample(int baseType, CloneInner cloneInner) {
        this.baseType = baseType;
        this.cloneInner = cloneInner;
    }


    public int getBaseType() {
        return baseType;
    }

    public void setBaseType(int baseType) {
        this.baseType = baseType;
    }

    public CloneInner getCloneInner() {
        return cloneInner;
    }

    public void setCloneInner(CloneInner cloneInner) {
        this.cloneInner = cloneInner;
    }

    /**
     * 以上抛出了 CloneNotSupportedException，这是因为 CloneExample 没有实现 Cloneable 接口。
     * <p>
     * 应该注意的是，clone() 方法并不是 Cloneable 接口的方法，而是 Object 的一个 protected 方法。
     * <p>
     * Cloneable 接口只是规定，如果一个类没有实现 Cloneable 接口又调用了 clone() 方法，就会抛出 CloneNotSupportedException。
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected CloneExample clone() throws CloneNotSupportedException {
        // 直接调用就是浅拷贝，原始对象与拷贝对象的引用类型引用同一个对象
        return (CloneExample) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        // testClone();
        CloneInner cloneInner = new CloneInner();
        CloneExample cloneExample = new CloneExample(10, cloneInner);
        // 拷贝对象和原始对象的引用类型引用同一个对象。
        CloneExample clone = cloneExample.clone();
        System.out.println(clone == cloneExample);
        System.out.println(clone.getCloneInner());
        System.out.println(cloneExample.getCloneInner());
    }

    // private static void testClone() throws CloneNotSupportedException {
    //     CloneInner cloneInner = new CloneInner();
    //     System.out.println(cloneInner.clone());
    // }

    /**
     * clone 方法被 protected 修饰，因此无法被直接调用，需要子类显示的去重写
     */
    static class CloneInner {
        private String msg;
        // @Override
        // protected Object clone() throws CloneNotSupportedException {
        //     return super.clone();
        // }
    }
}
