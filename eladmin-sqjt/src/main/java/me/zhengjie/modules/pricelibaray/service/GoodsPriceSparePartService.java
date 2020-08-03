package me.zhengjie.modules.pricelibaray.service;

import me.zhengjie.modules.pricelibaray.domain.GoodsPriceSparePart;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceSparePartCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GoodsPriceSparePartService {

    Map<String,Object> findGoodsPriceSparePart(GoodsPriceSparePartCriteria criteria,
                                               Pageable pageable);

    List<GoodsPriceSparePart> findGoodsPriceSparePart(GoodsPriceSparePartCriteria criteria);


    void download(List<GoodsPriceSparePart> goodsPriceSpareparts,
                  HttpServletResponse response) throws IOException;
}
