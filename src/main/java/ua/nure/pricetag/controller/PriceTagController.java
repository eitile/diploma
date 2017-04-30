package ua.nure.pricetag.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.nure.pricetag.entity.PriceTag;
import ua.nure.pricetag.service.PriceTagService;

import java.math.BigInteger;

@RestController
@RequestMapping("/pricetag")
@EnableWebMvc
public class PriceTagController {

    @Autowired
    private PriceTagService service;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createPriceTag(PriceTag priceTag) {
        service.createPriceTag(priceTag);
    }

    @RequestMapping(value = "/{pricetagId}", method = RequestMethod.GET)
    public String getPriceTag(@PathVariable(name = "pricetagId") BigInteger pricetagId) {
        try {
            return mapper.writeValueAsString(service.getPriceTag(pricetagId));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public String getPriceTagByProduct(@PathVariable(name = "productId") BigInteger productId) {
        try {
            return mapper.writeValueAsString(service.getPriceTagByProduct(productId));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/{pricetagId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePriceTag(@PathVariable(name = "pricetagId") BigInteger pricetagId) {
        service.deletePriceTag(pricetagId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePriceTag(@RequestBody PriceTag priceTag) {
        service.updatePriceTag(priceTag);
    }

    @RequestMapping(value = "/batch/{batchId}", method = RequestMethod.GET)
    public String getPriceTagsByBatchNumber(@PathVariable(name = "batchId") BigInteger batchId) {
        try {
            return mapper.writeValueAsString(service.getPriceTagsByBatchNumber(batchId));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
