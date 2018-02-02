package proj.zs.com.hshs.model.bean;

import java.io.Serializable;

/**
 * Created by zsz on 2018/2/1.
 * 找回密码的信息
 */

public class foundPassWord implements Serializable {
    private String phoneNumber; //手机号
    private String vlidationNum; //验证码
    private String passWord; //密码
    private String repetyPassWord; //确认密码
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getVlidationNum() {
        return vlidationNum;
    }
    public void setVlidationNum(String vlidationNum) {
        this.vlidationNum = vlidationNum;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getRepetyPassWord() {
        return repetyPassWord;
    }
    public void setRepetyPassWord(String repetyPassWord) {
        this.repetyPassWord = repetyPassWord;
    }
}
