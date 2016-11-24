/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.curl.mqtt.util;

import net.sf.json.JSONObject;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author ShiYun, 2016/01/19
 */
public abstract class CMD {

    private String id;
    private String item;

    private String DID;
    private String SID;

    private String cmd;


    public CMD() {
        String id = UUID.randomUUID().toString();
        this.id = id;
    }

    public String getTopic() {
        return DID + "/" + SID + "/" + cmd + "/";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDID() {
        return DID;
    }

    public void setDID(String DID) {
        this.DID = DID;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public static String toPString(CMD cmd) {
        JSONObject object = JSONObject.fromObject(cmd);
        object.remove("DID");
        object.remove("SID");
        object.remove("cmd");
        //2016-03-10 remove topic from the message
        object.remove("topic");
        switch (object.getString("item")) {
            case "wireless":
                if (object.containsKey("channel")) {
                    String channel = object.getString("channel");
                    object.remove("channel");
                    object.put("channel_2.4", channel);
                }   if (object.containsKey("channel5")) {
                    String channel = object.getString("channel5");
                    object.remove("channel5");
                    object.put("channel_5", channel);
            }   break;
            case "shell":
                String command=object.getString("command");
                object.remove("command");
                object.put("cmd", command);
                break;
        }
        return object.toString(4);
    }

    public static String toJSON(CMD cmd) {
        JSONObject object = JSONObject.fromObject(cmd);
        object.remove("DID");
        object.remove("SID");
        object.remove("cmd");
         switch (object.getString("item")) {
            case "wireless":
                if (object.containsKey("channel")) {
                    String channel = object.getString("channel");
                    object.remove("channel");
                    object.put("channel_2.4", channel);
                }   if (object.containsKey("channel5")) {
                    String channel = object.getString("channel5");
                    object.remove("channel5");
                    object.put("channel_5", channel);
            }   break;
            case "shell":
                String command=object.getString("command");
                object.remove("command");
                object.put("cmd", command);
                break;
        }
        object.put("mac", cmd.getDID());
        Date d=new Date();
        object.put("timestamp",d.getTime());
        return object.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CMD other = (CMD) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
