package ua.nure.pricetag.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.pricetag.entity.Store;

import java.math.BigInteger;

@Repository
public interface StoreRepository extends CrudRepository<Store, BigInteger> {
}
