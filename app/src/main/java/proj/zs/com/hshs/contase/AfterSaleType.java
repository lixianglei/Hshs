package proj.zs.com.hshs.contase;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zsz on 2018/1/30.
 */

public class AfterSaleType {
    @SerializedName("UnReceive")
    private String unReceive;
    @SerializedName("returnGoods")
    private String ReturnGoods;
    @SerializedName("ShopServiceTousu")
    private String ShopService;
    @SerializedName("ProductQuality")
    private String productQuality;
    @SerializedName("Invoice")
    private String invoice;
    @SerializedName("Other")
    private String other;

    public String getUnReceive() {
        return unReceive;
    }

    public void setUnReceive(String unReceive) {
        this.unReceive = unReceive;
    }

    public String getReturnGoods() {
        return ReturnGoods;
    }

    public void setReturnGoods(String returnGoods) {
        ReturnGoods = returnGoods;
    }

    public String getShopService() {
        return ShopService;
    }

    public void setShopService(String shopService) {
        ShopService = shopService;
    }

    public String getProductQuality() {
        return productQuality;
    }

    public void setProductQuality(String productQuality) {
        this.productQuality = productQuality;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
