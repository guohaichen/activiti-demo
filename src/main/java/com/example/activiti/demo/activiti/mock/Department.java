package com.example.activiti.demo.activiti.mock;

import java.util.List;

/**
 * @author cgh
 * @create 2023-09-21
 */
public class Department {
    private String depart_id;
    //部门名称
    private String depart_name;
    //部门经理
    private String depart_manager_id;

    private List<UserData> userList;

    public Department(String depart_id, String depart_name, String depart_manager_id, List<UserData> userList) {
        this.depart_id = depart_id;
        this.depart_name = depart_name;
        this.depart_manager_id = depart_manager_id;
        this.userList = userList;
    }

    public List<UserData> getUserList() {
        return userList;
    }

    public void setUserList(List<UserData> userList) {
        this.userList = userList;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public String getDepart_manager_id() {
        return depart_manager_id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depart_id='" + depart_id + '\'' +
                ", depart_name='" + depart_name + '\'' +
                ", depart_manager_id='" + depart_manager_id + '\'' +
                ", userList=" + userList +
                '}';
    }
}
