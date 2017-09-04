package tool.excelpoi;

/**
 * Created by 陈冬 on 2017/5/11.
 */
public class TaxStatistics {
    private int id;
    private String sid;
    private String serviceunits;
    private String date;
    private int dealnumber;
    private int instocknumber;
    private int totaldevices;
    private Double pay;
    private Double bankcard;
    private Double wechat;
    private Double alipay;
    private Double otherincome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getServiceunits() {
        return serviceunits;
    }

    public void setServiceunits(String serviceunits) {
        this.serviceunits = serviceunits;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDealnumber() {
        return dealnumber;
    }

    public void setDealnumber(int dealnumber) {
        this.dealnumber = dealnumber;
    }

    public int getInstocknumber() {
        return instocknumber;
    }

    public void setInstocknumber(int instocknumber) {
        this.instocknumber = instocknumber;
    }

    public int getTotaldevices() {
        return totaldevices;
    }

    public void setTotaldevices(int totaldevices) {
        this.totaldevices = totaldevices;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Double getBankcard() {
        return bankcard;
    }

    public void setBankcard(Double bankcard) {
        this.bankcard = bankcard;
    }

    public Double getWechat() {
        return wechat;
    }

    public void setWechat(Double wechat) {
        this.wechat = wechat;
    }

    public Double getAlipay() {
        return alipay;
    }

    public void setAlipay(Double alipay) {
        this.alipay = alipay;
    }

    public Double getOtherincome() {
        return otherincome;
    }

    public void setOtherincome(Double otherincome) {
        this.otherincome = otherincome;
    }

    @Override
    public String toString() {
        return "TaxStatistics{" +
                "id=" + id +
                ", sid='" + sid + '\'' +
                ", serviceunits='" + serviceunits + '\'' +
                ", date='" + date + '\'' +
                ", dealnumber=" + dealnumber +
                ", instocknumber=" + instocknumber +
                ", totaldevices=" + totaldevices +
                ", pay=" + pay +
                ", bankcard=" + bankcard +
                ", wechat=" + wechat +
                ", alipay=" + alipay +
                ", otherincome=" + otherincome +
                '}';
    }
}
