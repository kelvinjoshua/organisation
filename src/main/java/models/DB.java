package models;
import org.sql2o.*;

public class DB {
  //public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/department_news", "bale","kelvin23");
  String connectionString ="jdbc:postgresql://ec2-54-164-22-242.compute-1.amazonaws.com:5432/dbq1sgh4qbl6of";
  Sql2o sql2o = new Sql2o(connectionString, "zleejguknelkym","22f5f90071136259e8d6804c932d9b2e2c36e299bf1884534418e9be31072739");
}
