package com.home.apisdk.apiModel;

public class GetUserGroupOutputModel {
    String id = "";
    String group_name = "";
    String user_group_type = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getUser_group_type() {
        return user_group_type;
    }

    public void setUser_group_type(String user_group_type) {
        this.user_group_type = user_group_type;
    }

}
