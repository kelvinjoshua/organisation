package dao;
import models.*;
import java.util.*;
import org.sql2o.*;
public class sql2oUserDao implements UserDao {
    private final Sql2o sql2o;
    public sql2oUserDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Users user) {
        String sql = "INSERT INTO users(name,  role,) VALUES (:name, :role)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true).bind(user).executeUpdate().getKey();
            user.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Users> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users").executeAndFetch(Users.class);
        }
    }

    @Override
    public List<Users> getAllUsersByDepartment(int departmentId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Users.class);
        }
    }

    @Override
    public Users findById(int id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Users.class);
        }
    }

    @Override
    public void addUsersToDepartment(Users users, Departments departments) {
        String sql = "INSERT INTO departments_users(departmentId, userId) VALUES (:departmentId, :userId)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql).addParameter("departmentId", departments.getId()).addParameter("userId", users.getId()).throwOnMappingFailure(false).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

/*All users belonging to a department*/
public List<Departments> getAllDptBelongingToUsers(int userId) {
    ArrayList<Departments> allDepartments = new ArrayList<>();
    String joinQuery = "SELECT departmentId FROM departments_users WHERE userId =:userId";
    try(Connection conn = sql2o.open()){
        /*Store all  ids of departments*/
        List<Integer> allDepartmentIds = conn.createQuery(joinQuery)
                .addParameter("userId", userId).executeAndFetch(Integer.class);
        for(Integer department_id : allDepartmentIds){
            String departmentsQuery = "SELECT * FROM departments WHERE id=:department_id";
            /*Add user to  Alldepartments array */
            allDepartments.add(conn.createQuery(departmentsQuery)
                    .addParameter("department_id", department_id).executeAndFetchFirst(Departments.class));
        }
    }catch (Sql2oException ex){
        System.out.println(ex);
    }
    return allDepartments;
}

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from users WHERE id=:id";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql).addParameter("id", id).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from users";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
