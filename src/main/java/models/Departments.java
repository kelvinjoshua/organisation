package models;

import dao.DepartmentDao;

import java.util.List;
import java.util.Objects;
public class Departments {
    private String name;
    private String description;
    private int user;
    private int id;

    /*Constructor*/
    public Departments(String name, String description, int user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    /*Getters*/
    public String getName() {
        return  name;
    };
    public int getId() {
        return id;
    }
    public String getDescription(){
        return description;
    }

    public int getUser() {
        return user;
    }

}
