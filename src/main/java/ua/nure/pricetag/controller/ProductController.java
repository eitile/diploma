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
import ua.nure.pricetag.entity.Product;
import ua.nure.pricetag.service.ProductService;

import java.math.BigDecimal;
import java.math.BigInteger;

@RestController
@RequestMapping("/product")
@EnableWebMvc
public class ProductController {

    @Autowired
    private ProductService service;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/store/{storeId}", method = RequestMethod.GET)
    public String findProductsInStore(@PathVariable(name = "storeId") BigInteger storeId) {
        try {
            return mapper.writeValueAsString(service.findProductsInStore(storeId));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public String findProductsByName(@PathVariable(name = "name") String name) {
        try {
            return mapper.writeValueAsString(service.findProductsByName(name));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/description/{description}", method = RequestMethod.GET)
    public String findProductsByDescription(@PathVariable(name = "description") String description) {
        try {
            return mapper.writeValueAsString(service.findProductsByDescription(description));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public String findProductById(@PathVariable(name = "productId") BigInteger productId) {
        try {
            return mapper.writeValueAsString(service.findProductById(productId));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/store/{storeId}/price/{price}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProductToStore(@PathVariable(name = "storeId") BigInteger storeId,
                                  @PathVariable(name = "price") BigDecimal price,
                                  @RequestBody Product product) {
        service.addProductToStore(product, storeId, price);
    }

    @RequestMapping(value = "/store/{storeId}/product/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductFromStore(@PathVariable(name = "storeId") BigInteger storeId,
                                       @PathVariable(name = "productId") BigInteger productId) {
        service.removeProductFromStore(productId, storeId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeProduct(@RequestBody Product product) {
        service.changeProduct(product);
    }

}
