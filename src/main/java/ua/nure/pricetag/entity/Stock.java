package ua.nure.pricetag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "stock")
public class Stock implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "price_changes")
    private BigInteger priceChangesKey;

    @Column(name = "design")
    private BigInteger designId;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPriceChangesKey() {
        return priceChangesKey;
    }

    public void setPriceChangesKey(BigInteger priceChangesKey) {
        this.priceChangesKey = priceChangesKey;
    }

    public BigInteger getDesignId() {
        return designId;
    }

    public void setDesignId(BigInteger designId) {
        this.designId = designId;
    }
}
