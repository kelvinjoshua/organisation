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
    /*Setters-useful for our restful routing*/
    public void setId(int id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departments)) return false;
        Departments test = (Departments) o;
        return user == test.user&&
                Objects.equals(name, test.name) &&
                Objects.equals(description, test.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, description, user);
    }
}
