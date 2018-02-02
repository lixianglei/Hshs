package proj.zs.com.hshs.model.bean;

import java.io.Serializable;

/**
 * Created by zsz on 2018/2/1.
 * 登录所需要的信息
 */

public class loginMsg implements Serializable {
    private String avatarPath;
    private String accountNum;
    private String littleName;
    private String memberRank;
    private int growths;
    private String memberIntegral;
    public String getAvatarPath() {
        return avatarPath;
    }
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
    public String getAccountNum() {
        return accountNum;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
    public String getLittleName() {
        return littleName;
    }
    public void setLittleName(String littleName) {
        this.littleName = littleName;
    }
    public String getMemberRank() {
        return memberRank;
    }
    public void setMemberRank(String memberRank) {
        this.memberRank = memberRank;
    }
    public int getGrowths() {
        return growths;
    }
    public void setGrowths(int growths) {
        this.growths = growths;
    }
    public String getMemberIntegral() {
        return memberIntegral;
    }
    public void setMemberIntegral(String memberIntegral) {
        this.memberIntegral = memberIntegral;
    }
}
