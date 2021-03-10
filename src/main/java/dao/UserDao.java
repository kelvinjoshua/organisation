package dao;
import models.*;

import java.util.List;

public interface UserDao {
    /*Method signatures and CRUD*/
    void add(Users users);
    /*read*/
    List<Users> getAll();
    List<Users> getAllUsersByDepartment(int departmentId);
    Users  findById(int id);
    /*many to many relationship*/
    void addUsersToDepartment(Users users, Departments departments);

    /*delete*/
    void deleteById(int id);
    void clearAll();
}
