package dao;
import models.*;

import java.util.List;

public interface DepartmentDao {

    void add (Departments department);
    List<Departments> getAll();
    /*many to many relationship*/
    void addDepartmentToUsers(Departments departments, Users users);
    List<Users> getAllUsersByDept(int departmentid);
    List<News> getNews(int departmentid);//get news by dept id
    Departments findById(int id);

    /*Delete*/
    void deleteById(int id);
    void clearAll();
}
