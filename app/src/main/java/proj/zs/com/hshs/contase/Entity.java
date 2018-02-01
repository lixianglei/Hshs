package proj.zs.com.hshs.contase;

import com.google.gson.annotations.SerializedName;
/**
 * Created by zsz on 2018/1/30.
 */

public class Entity {
    @SerializedName("Code")
    private String code;
    @SerializedName("AfterSaleType")
    private AfterSaleType afterSaleType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AfterSaleType getAfterSaleType() {
        return afterSaleType;
    }

    public void setAfterSaleType(AfterSaleType afterSaleType) {
        this.afterSaleType = afterSaleType;
    }
}
