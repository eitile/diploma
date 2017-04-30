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
import ua.nure.pricetag.dto.BarcodeType;
import ua.nure.pricetag.entity.PriceTag;
import ua.nure.pricetag.entity.PriceTagBatch;
import ua.nure.pricetag.service.BatchService;

import java.math.BigInteger;

@RestController
@RequestMapping("/batch")
@EnableWebMvc
public class BatchController {

    @Autowired
    private BatchService service;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(method = RequestMethod.POST)
    public String createBatch(@RequestBody PriceTagBatch priceTagBatch) {
        try {
            return mapper.writeValueAsString(service.createBatch(priceTagBatch));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/{batchId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBatch(@PathVariable(name = "batchId") BigInteger batchId) {
        service.deleteBatch(batchId);
    }

    @RequestMapping(value = "/{batchId}", method = RequestMethod.GET)
    public String getBatch(@PathVariable(name = "batchId") BigInteger batchId) {
        try {
            return mapper.writeValueAsString(service.getBatch(batchId));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/store/{storeId}", method = RequestMethod.GET)
    public String getBatchesForStore(@PathVariable(name = "storeId") BigInteger storeId) {
        try {
            return mapper.writeValueAsString(service.getBatchesForStore(storeId));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/batch/{batchId}/pricetag", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPriceTagToBatch(@PathVariable(name = "batchId") BigInteger batchId,
                                   @RequestBody PriceTag priceTag) {
        priceTag.setBatchId(batchId);
        service.addPriceTagToBatch(priceTag);
    }

    @RequestMapping(value = "/{batchId}/print-barcode", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void printBatchBarcode(@PathVariable(name = "batchId") BigInteger batchId) {
        service.printBatch(batchId, BarcodeType.BARCODE);
    }

    @RequestMapping(value = "/{batchId}/print-qrcode", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void printBatchQRCode(@PathVariable(name = "batchId") BigInteger batchId) {
        service.printBatch(batchId, BarcodeType.QR_CODE);
    }

}
