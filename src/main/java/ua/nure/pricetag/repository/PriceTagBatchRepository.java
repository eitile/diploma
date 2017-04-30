package ua.nure.pricetag.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.pricetag.entity.PriceTagBatch;

import java.math.BigInteger;

@Repository
public interface PriceTagBatchRepository extends CrudRepository<PriceTagBatch, BigInteger> {

}
