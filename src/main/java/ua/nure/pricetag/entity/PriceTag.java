package ua.nure.pricetag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "price_tag")
public class PriceTag implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_product_id")
    private StoreProductPrice storeProduct;

    @Column(name = "design")
    private BigInteger designId;

    @Column(name = "batch_id")
    private BigInteger batchId;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getDesignId() {
        return designId;
    }

    public void setDesignId(BigInteger designId) {
        this.designId = designId;
    }

    public BigInteger getBatchId() {
        return batchId;
    }

    public void setBatchId(BigInteger batchId) {
        this.batchId = batchId;
    }

    public StoreProductPrice getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(StoreProductPrice storeProduct) {
        this.storeProduct = storeProduct;
    }
}
