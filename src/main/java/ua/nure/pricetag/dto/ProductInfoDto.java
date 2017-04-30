package ua.nure.pricetag.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductInfoDto {

    private String productName;
    private String productDescription;
    private String priceTagCode;
    private BigDecimal currentPrice;
    private String storeName;
    private BigInteger designId;
    private String stockName;
    private String storeAddress;

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public BigInteger getDesignId() {
        return designId;
    }

    public void setDesignId(BigInteger designId) {
        this.designId = designId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getPriceTagCode() {
        return priceTagCode;
    }

    public void setPriceTagCode(String priceTagCode) {
        this.priceTagCode = priceTagCode;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
