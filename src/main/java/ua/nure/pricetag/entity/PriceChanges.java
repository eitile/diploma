package ua.nure.pricetag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "price_changes")
public class PriceChanges implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "type")
    private BigInteger priceChangesType;

    @Column(name = "value")
    private BigDecimal value;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getPriceChangesType() {
        return priceChangesType;
    }

    public void setPriceChangesType(BigInteger priceChangesType) {
        this.priceChangesType = priceChangesType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
