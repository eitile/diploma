package ua.nure.pricetag.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.pricetag.entity.Product;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, BigInteger> {

    List<Product> findAllByName(String name);

    List<Product> findAllByDescription(String description);

}
