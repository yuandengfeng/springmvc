package com.mqtt.curl.mqtt.exe;

/**
 * Created by Administrator on 2016/1/20.
 */
public class CMD_EXE_shell extends CMD_EXE {

    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public CMD_EXE_shell() {
        super();
        this.setItem("shell");
    }

    public CMD_EXE_shell(String command) {
        this();
        this.command = command;
    }
}
