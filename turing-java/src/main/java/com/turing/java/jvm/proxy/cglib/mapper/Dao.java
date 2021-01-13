package com.turing.java.jvm.proxy.cglib.mapper;

public class Dao implements IDao {

    @Override
    public void select() {
        System.out.println("select 1 from dual:");
        insert();
    }

    @Override
    public void insert() {
        System.out.println("insert into ...");
    }

    public final void delete() {
        System.out.println("delete from ...");
    }
}
