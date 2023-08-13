package com.wangzhixiong.ajax.pojo;

public class User
{
    private String id;
    private String userName;
    private String password;
    private String birthday;
    private Double salary;

    public User()
    {
    }

    public User(String id, String userName, String password, Double salary, String birthday)
    {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
        this.salary = salary;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public Double getSalary()
    {
        return salary;
    }

    public void setSalary(Double salary)
    {
        this.salary = salary;
    }
}
