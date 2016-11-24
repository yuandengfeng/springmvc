package com.mqtt.curl.mqtt.exe;

/**
 * Created by ShiYun on 2016/2/4.
 */
public class CMD_EXE_script extends CMD_EXE {

    private String content;
    private String log_opt;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLog_opt() {
        return log_opt;
    }

    public void setLog_opt(String log_opt) {
        this.log_opt = log_opt;
    }

    public CMD_EXE_script() {
        super();
        this.setItem("script");
    }

    public CMD_EXE_script(String content, String log_opt) {
        this();
        this.content = content;
        this.log_opt = log_opt;
    }
}
