package ua.nure.pricetag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.pricetag.entity.PriceTag;
import ua.nure.pricetag.repository.PriceTagRepository;

import java.math.BigInteger;
import java.util.List;

@Service
public class PriceTagService {

    @Autowired
    private PriceTagRepository repository;

    public void createPriceTag(PriceTag priceTag) {
        repository.save(priceTag);
    }

    public PriceTag getPriceTag(BigInteger pricetagId) {
        return repository.findOne(pricetagId);
    }

    public PriceTag getPriceTagByProduct(BigInteger productId) {
        return repository.findOneByStoreProductProductId(productId);
    }

    public void deletePriceTag(BigInteger pricetagId) {
        repository.delete(pricetagId);
    }

    public void updatePriceTag(PriceTag priceTag) {
        repository.save(priceTag);
    }

    public List<PriceTag> getPriceTagsByBatchNumber(BigInteger batchId) {
        return repository.findByBatchId(batchId);
    }

}
