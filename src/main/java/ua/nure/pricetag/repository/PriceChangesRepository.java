package ua.nure.pricetag.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.pricetag.entity.PriceChanges;

import java.math.BigInteger;

@Repository
public interface PriceChangesRepository extends CrudRepository<PriceChanges, BigInteger> {
}
