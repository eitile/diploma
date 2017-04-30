package ua.nure.pricetag.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.pricetag.entity.PriceTag;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PriceTagRepository extends CrudRepository<PriceTag, BigInteger> {

    PriceTag findOneByStoreProductProductId(BigInteger productId);

    List<PriceTag> findByBatchId(BigInteger batchId);

    @Query("SELECT pt.batchId FROM PriceTag pt WHERE pt.storeProduct.storeId = :storeId")
    List<BigInteger> findBatchIdsByStore(@Param("storeId") BigInteger storeId);

}
