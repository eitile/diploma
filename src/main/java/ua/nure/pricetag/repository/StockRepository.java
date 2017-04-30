package ua.nure.pricetag.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.pricetag.entity.Stock;

import java.math.BigInteger;

@Repository
public interface StockRepository extends CrudRepository<Stock, BigInteger> {
}
