/*imports*/
import com.google.gson.Gson;
import dao.*;
import exception.ApiException;
import models.*;
import org.sql2o.*;
import java.util.*;

import static spark.Spark.*;

public class App {
          public static void main(String[] args){
              /*instances to perform operations*/
              sql2oDepartmentDao departmentObj;
              sql2oNewsDao newsObj;
              sql2oUserDao usersObj;
              Connection conn;
              Gson gson = new Gson(); /*convert obj to java from json and back*/
              String connectionString ="";
              Sql2o sql2o = new Sql2o("");

              /*Connections*/
              departmentObj = new sql2oDepartmentDao(sql2o);
              usersObj= new sql2oUserDao(sql2o);
              newsObj = new sql2oNewsDao(sql2o);
              conn = sql2o.open();

              /*C*/
              post("/departments/new", "application/json", (request, response) -> {
                  Departments department = gson.fromJson(request.body(), Departments.class);
                  departmentObj.add(department);
                  response.status(201);
                  return gson.toJson(department);
              }
              );

              get("/departments", "application/json", (req, res) -> {
                  res.type("application/json");
                  System.out.println(departmentObj.getAll());
                  if(departmentObj.getAll().size() > 0){
                      return gson.toJson(
                              departmentObj.getAll());
                  }
                  else {
                      return "{\"message\":\"No departments are currently in the database.\"}";
                  }
              }
              );

              get("/departments/:id", "application/json", (req, res) -> {
                  int departmentId = Integer.parseInt(req.params("id"));
                  Departments departmentToFind = departmentObj.findById(departmentId);
                  if (departmentToFind == null){
                      throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
                  }
                  res.type("application/json");
                  return gson.toJson(departmentToFind);
              }
              );

              /*Get news by  department Id*/
              get("/departments/:id/news", "application/json", (req, res) -> {
                  int departmentId = Integer.parseInt(req.params("id"));

                  Departments departmentToFind = departmentObj.findById(departmentId);
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
              post("/users/new", "application/json", (req, res) -> {
                  Users user = gson.fromJson(req.body(), Users.class);
                  usersObj.add(user);
                  res.status(201);
                  res.type("application/json");
                  return gson.toJson(user);
              }
              );

            //filters
              after((req, res) ->{
                  res.type("application/json");
              });
              /*Exception*/
      }
}
