/*imports*/
import com.google.gson.Gson;
import dao.*;
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
              });

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
              });



            //filters
              after((req, res) ->{
                  res.type("application/json");
              });
      }
}
