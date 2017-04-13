package android.he.com.okgodemo.bean;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Person extends BaseBean {
    private String id;
    private String name;
    private int age;
    private Date birthday;

    public Person() {
    }

    public Person(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
