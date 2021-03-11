package models;

import java.util.Objects;

public class Users {
    private int id;
    private String name;
   // private int departmentid;
    private String role;

    public Users(String name, String role) {
        this.name = name;
       // this.departmentid = departmentid;
        this.role = role;
    }
/*getters*/
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    /*
    public int getDepartmentId() {
        return departmentid;
    }*/
    public String getRole() {
        return role;
    }
    /*setters*/
    public void setRole(String role) {
        this.role = role;
    }
    public void setName(String userName) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    /*
    public void setDepartmentId(int departmentid) {
        this.departmentid = departmentid;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users user = (Users) o;//type cast
        return
                Objects.equals(name, user.name) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role);
    }
}
