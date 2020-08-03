package me.zhengjie.modules.pricelibaray.service;

import me.zhengjie.modules.pricelibaray.domain.GoodsPriceIT;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceITCriteria;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceITExcel;
import me.zhengjie.modules.system.service.dto.UserDto;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GoodsPriceITService {

    void saveGoodsPriceIT(GoodsPriceIT goodsPriceIT);

    void deleteGoodsPriceIT(Long id);

    void deleteGoodsPriceIT(Set<Long> id);

    Map<String, Object> findGoodsPriceITByDescript(String name);

    Map<String,Object> findGoodsPriceITWithPage(GoodsPriceITCriteria criteria,
                                        Pageable pageable);

    List<GoodsPriceIT> findGoodsPriceITs(GoodsPriceITCriteria criteria);

    void download(HttpServletResponse response) throws IOException;

    void download(List<GoodsPriceIT> goodsPriceITs,
                  HttpServletResponse response) throws IOException;

    void saveExcelData(List<GoodsPriceITExcel> goodsPriceITExcels);
}
