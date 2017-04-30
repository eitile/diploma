package ua.nure.pricetag.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.pricetag.entity.StoreProductPrice;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface StoreProductPriceRepository extends CrudRepository<StoreProductPrice, BigInteger> {

    @Query("SELECT spp.productId FROM StoreProductPrice spp WHERE spp.storeId = :storeId")
    List<BigInteger> findProductsInStore(@Param("storeId") BigInteger storeId);

    @Modifying
    @Query("DELETE FROM StoreProductPrice spp WHERE spp.storeId = :storeId AND spp.productId = :productId")
    void deleteProductFromStore(@Param("productId") BigInteger productId, @Param("storeId") BigInteger storeId);

}
