package com.shenzhen.xutilsdemo.db;


import org.xutils.db.annotation.Table;


@Table(name = "person")
public class Person {

    /*
    * 注解属性
    name (String)       :  表名称
    isMainKey (boolean) :  是否为主键
    autoGen (boolean)   :  是否自增(默认: false)
    proprety (String)   :  是否为空(默认: NOT NULL)
    * */
    //默认的构造方法必须写出，如果没有，这张表是创建不成功的
    public Person() {

    }
    public Person(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "ID", isMainKeyId = true, autoGen = true)
    public int id;
    @Column(name = "NAME")
    public String name;
    @Column(name = "AGE")
    public int age; // 年龄
    @Column(name = "SEX")
    public String sex; // 性别

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }


}
