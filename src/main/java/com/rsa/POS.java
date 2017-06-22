package com.rsa;


/**
 * Created by Administrator on 2017/3/29.
 */
public class POS{
    private String sn;
    private int state = -1;
    private int type = 1;
    private String mac;
    private String ssid;
    private String channelPath;
    private String cid;
    private String pmac;
    private long time;


    public String getPmac() {
        return pmac;
    }

    public void setPmac(String pmac) {
        this.pmac = pmac;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }

    public String getChannelPath() {
        return channelPath;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCid() {
        return cid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
}
