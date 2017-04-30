package ua.nure.pricetag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.pricetag.entity.Product;
import ua.nure.pricetag.entity.StoreProductPrice;
import ua.nure.pricetag.repository.ProductRepository;
import ua.nure.pricetag.repository.StoreProductPriceRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreProductPriceRepository storeProductPriceRepository;

    public List<Product> findProductsInStore(BigInteger storeId) {
        return storeProductPriceRepository.findProductsInStore(storeId)
                .stream()
                .map(productRepository::findOne)
                .collect(Collectors.toList());
    }

    public List<Product> findProductsByName(String name) {
        return productRepository.findAllByName(name);
    }

    public List<Product> findProductsByDescription(String description) {
        return productRepository.findAllByDescription(description);
    }

    public Product findProductById(BigInteger productId) {
        return productRepository.findOne(productId);
    }

    @Transactional
    public void addProductToStore(Product product, BigInteger storeId, BigDecimal productPrice) {
        StoreProductPrice item = new StoreProductPrice();
        item.setPrice(productPrice);
        item.setProductId(product.getId());
        item.setStoreId(storeId);
        storeProductPriceRepository.save(item);
    }

    @Transactional
    public void removeProductFromStore(BigInteger productId, BigInteger storeId) {
        storeProductPriceRepository.deleteProductFromStore(productId, storeId);
    }

    @Transactional
    public void changeProduct(Product product) {
        productRepository.save(product);
    }
}
