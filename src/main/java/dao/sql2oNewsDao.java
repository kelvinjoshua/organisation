package dao;
import java.util.List;
import models.*;
import org.sql2o.*;

public class sql2oNewsDao  implements  NewsDao{
    /*Sql2o instance to use its parent class methods*/
    private final Sql2o sql2o;
    public sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (content, description, departmentid) VALUES (:content,:description, :departmentid)";
      /*bind  object -alternative way to pass drnamic parameters*/
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true).bind(news).executeUpdate().getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
/*Fetch object  with this unique id*/
    @Override
    public News findByiId(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news WHERE id = :id").addParameter("id", id).executeAndFetchFirst(News.class);
        }
    }

    @Override
    public List<News> getAllNews() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM news").executeAndFetch(News.class);
        }
    }
    /*one to many*/
    public List<News> getAllNewsByDepartment(int departmentId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news WHERE departmentid = :departmentid").addParameter("departmentid", departmentId).executeAndFetch(News.class);
        }
    }

    @Override
    public void updateNews(int id, String news_name, String news_content, int dpt_id) {

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void clearAll() {
        String sql = "DELETE from news";
        try (Connection con = sql2o.open()) {
                 con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
