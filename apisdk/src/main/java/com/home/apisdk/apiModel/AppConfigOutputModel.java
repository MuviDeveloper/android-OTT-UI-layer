package com.home.apisdk.apiModel;

public class AppConfigOutputModel {
    /*{
        "code": 200,
            "status": "Ok",
            "msg: "success", // Message should be translated according to the language code
        "force_update_version": "1.2.0" // If there is no value, the key will return empty string instead of null.
    }*/

    String code;
    String status;
    String msg;
    String force_update_version;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getForce_update_version() {
        return force_update_version;
    }

    public void setForce_update_version(String force_update_version) {
        this.force_update_version = force_update_version;
    }

}
