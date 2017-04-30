package ua.nure.pricetag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "store_product_price")
public class StoreProductPrice implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "product_id")
    private BigInteger productId;

    @Column(name = "store_id")
    private BigInteger storeId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock_id")
    private BigInteger stockId;

    @OneToMany(mappedBy = "storeProduct")
    private Collection<PriceTag> priceTags;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public BigInteger getStoreId() {
        return storeId;
    }

    public void setStoreId(BigInteger storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigInteger getStockId() {
        return stockId;
    }

    public void setStockId(BigInteger stockId) {
        this.stockId = stockId;
    }

    public Collection<PriceTag> getPriceTags() {
        return priceTags;
    }

    public void setPriceTags(Collection<PriceTag> priceTags) {
        this.priceTags = priceTags;
    }
}
