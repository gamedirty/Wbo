package com.sovnem.test;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by sovnem on 16/3/8.
 */
@Table(name = "user")
public class User {

    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "number")
    private String number;

    public User(String name, int age, String number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "User{" +
                "number='" + number + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
