package com.example.activiti.demo.activiti.mock;

import java.util.*;

/**
 * @author cgh
 * @create 2023-09-21
 * mock数据，简历人员和部门的关系并设置部门领导
 */
public class MockUserAndDepartment {

    public static List<UserData> getUserList() {
        UserData u1 = new UserData("A001", "乔峰", "dev_I");
        UserData u2 = new UserData("A002", "洪七公", "dev_I");
        UserData u11 = new UserData("A003", "郭靖", "dev_I");
        UserData u22 = new UserData("A004", "长老", "dev_I");

        UserData u3 = new UserData("B001", "段誉", "prod_I");
        UserData u4 = new UserData("B002", "王语嫣", "prod_I");

        UserData u5 = new UserData("C001", "虚竹", "market_I");
        UserData u6 = new UserData("C002", "李秋水", "market_I");
        UserData u77 = new UserData("C003", "无崖子", "market_I");
        UserData u78 = new UserData("C004", "梅兰竹菊", "market_I");
        return Arrays.asList(u1, u2, u3, u4, u5, u6, u11, u22, u77, u78);
    }

    public static List<Department> getDepartmentList() {
        Department devDepartment = new Department("dev_I", "开发1部", "A001", new ArrayList<UserData>());
        Department prodDocument = new Department("prod_I", "产品1部", "B001", new ArrayList<UserData>());
        Department testDepartment = new Department("market_I", "市场1部", "u5", new ArrayList<UserData>());
        return Arrays.asList(devDepartment, prodDocument, testDepartment);
    }


    //查看部门-用户 对应关系
    public static Map<String, Department> getUserOfDepartmentMap() {
        List<Department> departmentList = getDepartmentList();
        List<UserData> userList = getUserList();

        HashMap<String, Department> departMap = new HashMap<>();
        for (Department department : departmentList) {
            departMap.put(department.getDepart_id(), department);
        }

        for (UserData userData : userList) {
            Department department = departMap.get(userData.getDepart_id());
            if (department != null) {
                department.getUserList().add(userData);
            }
        }
        return departMap;
    }

    @Override
    public String toString() {
        Map<String, Department> userDepartmentList = getUserOfDepartmentMap();
        userDepartmentList.forEach(
                (k, v) -> {
                    System.out.println("department key: " + k + "department: " + v.getDepart_name() + " " + v.getUserList());
                }
        );
        return "----------end";
    }

    //提供根据用户id，返回部门所在领导
    public String queryDepartManager(String userId) {
        List<UserData> userList = getUserList();
        String departmentId = "";
        for (UserData userData : userList) {
            if (userId.equals(userData.getUser_id())) {
                departmentId = userData.getDepart_id();
            }
        }

        Map<String, Department> userOfDepartmentMap = getUserOfDepartmentMap();
        Department department = userOfDepartmentMap.get(departmentId);
        if (department != null) {
            return department.getDepart_manager_id();
        } else {
            throw new RuntimeException("该用户未设置主管");
        }
    }
    //根据用户id，得到用户名
    public String queryUsername(String userId){
        List<UserData> userList = getUserList();
        for (UserData userData : userList) {
            if (userId.equals(userData.getUser_id())){
                return userData.getUsername();
            }
        }
        return "";
    }



}
