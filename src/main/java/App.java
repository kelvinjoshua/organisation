/*imports*/
import com.google.gson.*;
import dao.*;
import exception.ApiException;
import models.*;
import org.sql2o.*;
import java.util.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
          public static void main(String[] args){
            port(getHerokuAssignedPort());
              /*instances to perform operations*/
              sql2oDepartmentDao DepartmentDao;
              sql2oNewsDao newsObj;
              sql2oUserDao UserDao;
              Connection conn;
              Gson gson = new Gson(); /*convert obj to java from json and back*/
       String connectionString ="jdbc:postgresql://ec2-54-164-22-242.compute-1.amazonaws.com:5432/dbq1sgh4qbl6of";
         Sql2o sql2o = new Sql2o(connectionString, "zleejguknelkym","22f5f90071136259e8d6804c932d9b2e2c36e299bf1884534418e9be31072739");
         //Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/department_news", "bale","kelvin23");

              DepartmentDao = new sql2oDepartmentDao(sql2o);
              UserDao= new sql2oUserDao(sql2o);
              newsObj = new sql2oNewsDao(sql2o);
              conn = sql2o.open();


              /*C*/
              post("/users/new", "application/json", (req, res) -> {
                          Users user = gson.fromJson(req.body(), Users.class);
                          UserDao.add(user);
                          res.status(201);
                          res.type("application/json");
                          return gson.toJson(user);
                      }
              );
              /*All users from Users table*/
              get("/users", "application/json", (req, res) -> {
                  res.type("application/json");
                  return gson.toJson(UserDao.getAll());
              });
              post("/departments/new", "application/json", (request, response) -> {
                  Departments department = gson.fromJson(request.body(), Departments.class);
                  DepartmentDao.add(department);
                  response.status(201);
                  return gson.toJson(department);
              }
              );
              /*R*/
              get("/departments", "application/json", (req, res) -> {
                  res.type("application/json");
                      return gson.toJson(DepartmentDao.getAll());
              }
              );
              /*R*/
              get("/departments/:id", "application/json", (req, res) -> {
                  int departmentId = Integer.parseInt(req.params("id"));
                  Departments departmentToFind = DepartmentDao.findById(departmentId);
                  if (departmentToFind == null){
                      throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
                  }
                  res.type("application/json");
                  return gson.toJson(departmentToFind);
              }
              );

              /*Get news by  department Id
              get("/departments/:id/news", "application/json", (req, res) -> {
                  int departmentId = Integer.parseInt(req.params("id"));
                  Departments departmentToFind = DepartmentDao.findById(departmentId);
                  List<News> allNews;
                  if (departmentToFind == null){
                      throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
                  }
                  allNews = newsObj.getAllNewsByDepartment(departmentId);
                  res.type("application/json");
                  return gson.toJson(allNews);
              }
              );

                /*C*/

              /*Add user to department
              post("/departments/:id/users/new", "application/json", (req, res) -> {
                  int departmentId = Integer.parseInt(req.params("id"));
                  Users users = gson.fromJson(req.body(), Users.class);
                  users.setId(departmentId);/*Set  id of department*/
               /*   UserDao.add(users);
                  res.status(201);
                  res.type("application/json");
                  return gson.toJson(users);
              }
              ); */
            /*Get user by associated dept
              get("/users/:user_id/departments","application/json",(request, response) -> {
                  int user_id = Integer.parseInt(request.params("user_id"));
                  Users usersTofind = UserDao.findById(user_id);
                  if (usersTofind == null){
                      throw new Exception("User with that id does not exist");
                  }else if(UserDao.getAllDptBelongingToUsers(user_id).size() == 0){
                      return "{\"message\":\"User is not associated with any of the departments\"}";
                  }else {
                      return gson.toJson(UserDao.getAllDptBelongingToUsers(user_id));
                  }
              }
              );*/


              post("/news/new", "application/json", (req, res) -> {
                  res.type("application/json");
                  News news = gson.fromJson(req.body(), News.class);
                  newsObj.add(news);
                  res.status(201);
                  res.type("application/json");
                  return gson.toJson(news);
              });
              get("/news", "application/json", (req, res) -> {
                  res.type("application/json");
                  return gson.toJson(newsObj.getAllNews());
              });

              /*Add news to department
              post("/departments/:id/news/new","application/json",(request, response) -> {
                  int id = Integer.parseInt(request.params("id"));
                  News news = gson.fromJson(request.body(),News.class);
                  news.setId(id);
                  newsObj.add(news);
                  response.type("application/json");
                  response.status(201);
                  return gson.toJson(news);
              }
              );
            /*Get news of a certain department
              get("/departments/:id/departmentNews", "application/json", (request, response) -> {
                  int id = Integer.parseInt(request.params("id"));
                  Departments departmentToFind = DepartmentDao.findById(id);
                  return gson.toJson(newsObj.getAllNewsByDepartment(id)); /**/
             // });
            //filters
              after((req, res) ->{
                  res.type("application/json");
              });

              /*Exception*/
              exception(ApiException.class, (exc, req, res) -> {
                  ApiException err = (ApiException) exc;
                  Map<String, Object> jsonMap = new HashMap<>();
                  jsonMap.put("status", err.getStatusCode());
                  jsonMap.put("errorMessage", err.getMessage());
                  res.type("application/json"); //after does not run in case of an exception.
                  res.status(err.getStatusCode()); //set the status
                  res.body(gson.toJson(jsonMap));  //set the output.
              });
      }
}
