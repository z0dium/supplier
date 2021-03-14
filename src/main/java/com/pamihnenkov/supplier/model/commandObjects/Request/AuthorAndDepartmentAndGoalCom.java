package com.pamihnenkov.supplier.model.commandObjects.Request;

public class AuthorAndDepartmentAndGoalCom {

    private String goal;
    private String author;
    private String department;

    public AuthorAndDepartmentAndGoalCom(String goal, String author, String department) {
        this.goal = goal;
        this.author = author;
        this.department = department;
    }

    public String getGoal() {
        return goal;
    }

    public String getAuthor() {
        return author;
    }

    public String getDepartment() {
        return department;
    }
}
