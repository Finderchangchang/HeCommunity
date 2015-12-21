package liuliu.he.community.model;

/**
 * 商品信息
 * Created by Administrator on 2015/12/10.
 * id : 785
 * name : 小蒙香-火锅底料
 * image : http://img.hesq.com.cn/fresh/upload/product/20151030/1446187383029.jpg
 * feature : 火锅底料
 * sales : 46
 * stock : 100
 * price : 3.5
 * priceSales : 3.5
 * isSales : false
 * isRecom : false
 * isNew : false
 * isLimit : false
 * isRush : false
 * isArea : false
 * isPresent : false
 * isDrive : false
 */


public class GoodModel {
    private String id;
    private String name;
    private String image;
    private String feature;
    private String sales;
    private String stock;
    private String price;
    private String priceSales;
    private boolean isSales;
    private boolean isRecom;
    private boolean isNew;
    private boolean isLimit;
    private boolean isRush;
    private boolean isArea;
    private boolean isPresent;
    private boolean isDrive;

//    public GoodModel(String name, String image) {
//        this.name = name;
//        this.image = image;
//    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceSales() {
        return priceSales;
    }

    public void setPriceSales(String priceSales) {
        this.priceSales = priceSales;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setIsSales(boolean isSales) {
        this.isSales = isSales;
    }

    public void setIsRecom(boolean isRecom) {
        this.isRecom = isRecom;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public void setIsLimit(boolean isLimit) {
        this.isLimit = isLimit;
    }

    public void setIsRush(boolean isRush) {
        this.isRush = isRush;
    }

    public void setIsArea(boolean isArea) {
        this.isArea = isArea;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public void setIsDrive(boolean isDrive) {
        this.isDrive = isDrive;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getFeature() {
        return feature;
    }

    public String getSales() {
        return sales;
    }

    public String getStock() {
        return stock;
    }

    public boolean isSales() {
        return isSales;
    }

    public boolean isRecom() {
        return isRecom;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isLimit() {
        return isLimit;
    }

    public boolean isRush() {
        return isRush;
    }

    public boolean isArea() {
        return isArea;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public boolean isDrive() {
        return isDrive;
    }
}
