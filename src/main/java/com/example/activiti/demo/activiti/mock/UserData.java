package com.example.activiti.demo.activiti.mock;

/**
 * @author cgh
 * @create 2023-09-19
 */
public class UserData {
    private String user_id;
    private String username;
    //所在部门
    private String depart_id;
    public UserData(String user_id, String username, String depart_id) {
        this.user_id = user_id;
        this.username = username;
        this.depart_id = depart_id;
    }

    public String getUsername() {
        return username;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public String getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", depart_id='" + depart_id + '\'' +
                '}';
    }
}
