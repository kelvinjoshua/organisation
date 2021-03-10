package models;

public class News {
    private int id;
    private int departmentid;
    private String content;
    private String description;

    public News(String content, String description, int departmentid) {
        this.content = content;
        this.description = description;
        this.departmentid = departmentid;
    }
    /*Getters*/
    public String getContent() {
        return content;
    }
    public String getDescription() {
        return description;
    }
    public int getDepartmentid() {
        return departmentid;
    }

    public int getId() {
        return id;
    }
    /*Setters-useful for our restful routing*/
    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public void setId(int id) {
        this.id = id;
    }
}
