package models;
import org.sql2o.*;

public class DB {
  // public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/department_news", "bale","kelvin23");
    String connectionString ="jdbc:postgresql://ec2-54-159-175-113.compute-1.amazonaws.com:5432/db3f6fp0tt5q0p";
    Sql2o sql2o = new Sql2o(connectionString, "vfouuxjniurlir","01aebc052cb6f50d8ebb2c321e70f6e168d06e8b43994bb320e5f34b08a7bd43");
}
