package dao;
import models.*;
import org.sql2o.*;

import java.util.List;

public class sql2oDepartmentDao implements DepartmentDao {
    private final Sql2o sql2o;
    public sql2oDepartmentDao(Sql2o sql2o) {
            this.sql2o = sql2o;
    }


    @Override
    public void add(Departments department) {
        String sql = "INSERT INTO departments(name,description) VALUES(:name,:description)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true).addParameter("name",department.getName()).addParameter("description",department.getDescription()).executeUpdate().getKey();
          /*setters are important for our routes!!*/
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Departments> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departments").executeAndFetch(Departments.class);
        }
    }

    /*M-M,join table*/
    @Override
    public void addDepartmentToUsers(Departments departments, Users users) {
        String sql = "INSERT INTO departments_users(departmentId, userId) VALUES (:departmentId, :userId)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql).addParameter("departmentId", departments.getId()).addParameter("userId", users.getId()).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Users> getAllUsersByDept(int departmentid) {
        String sql = "SELECT * FROM users where department_id=:department_id";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql).addParameter("department_id", departmentid).executeAndFetch(Users.class);
        }
    }

    @Override
    public List<News> getNews(int departmentid) {
        String sql = "SELECT * FROM news where department_id=:department_id";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql).addParameter("department_id", departmentid).executeAndFetch(News.class);
        }
    }

    @Override
    public Departments findById(int id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM departments WHERE id = :id").addParameter("id", id).executeAndFetchFirst(Departments.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM departments WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    }

