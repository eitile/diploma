package ua.nure.pricetag.design;


import ua.nure.pricetag.dto.ProductInfoDto;

import java.math.BigInteger;
import java.util.Optional;

public final class DesignResolver {

    public static Design getBarcodeDesign(ProductInfoDto dto) {
        switch (Optional.ofNullable(dto.getDesignId()).orElse(BigInteger.ONE).intValue()) {
            case 1:
                return new ClassicDesign();
            case 2:
                return new StoreSpecialDesign();
            case 3:
                return new HotOfferDesign();
            case 4:
                return new NewProductDesign();
            default:
                return new ClassicDesign();
        }
    }

}
