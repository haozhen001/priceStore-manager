package me.zhengjie.modules.pricelibaray.service.impl;

import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.pricelibaray.domain.GoodsPriceIT;
import me.zhengjie.modules.pricelibaray.repository.GoodsPriceITRepository;
import me.zhengjie.modules.pricelibaray.service.GoodsPriceITService;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceITCriteria;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceITExcel;
import me.zhengjie.modules.pricelibaray.service.transform.GoodsPriceITTransform;
import me.zhengjie.modules.system.service.UserService;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 12:00
 **/
@Service
@Transactional
public class GoodsPriceITServiceImpl implements GoodsPriceITService {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsPriceITRepository goodsPriceITRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGoodsPriceIT(GoodsPriceIT goodsPriceIT) {
        Long id = goodsPriceIT.getId();
        if(!Objects.equals(null,id)){
            GoodsPriceIT goodsPriceITDB =
                    goodsPriceITRepository.findById(id).orElseThrow(BadRequestException::new);
//            goodsPriceITDB.setMarketPrice(goodsPriceIT.getMarketPrice());
            BeanUtils.copyProperties(goodsPriceIT,goodsPriceITDB);
            goodsPriceITRepository.save(goodsPriceITDB);
        }else{
            goodsPriceITRepository.save(goodsPriceIT);
        }
    }

    @Override
    public void deleteGoodsPriceIT(Long id) {
        goodsPriceITRepository.deleteById(id);
    }

    @Override
    public void deleteGoodsPriceIT(Set<Long> id) {
        goodsPriceITRepository.deleteByIds(id);
    }

    @Override
    public Map<String, Object> findGoodsPriceITByDescript(String name) {
        Map<String, Object> map = new HashMap<>();
        List<GoodsPriceIT> list = goodsPriceITRepository
                .findByGoodsDescribeAndDelflagOrderByEffecticeStartDate(name,0);
        List<String> dateList = new ArrayList<>();
        List<BigDecimal> priceList = new ArrayList<>();
        List<BigDecimal> elecPriceList = new ArrayList<>();
        List<BigDecimal> marketPriceList = new ArrayList<>();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if(!Objects.equals(null,list) && !Objects.equals(0,list.size())){
            for (GoodsPriceIT it: list){
                String startDate = sdf.format(it.getEffecticeStartDate());
                String endDate = sdf.format(it.getEffecticeEndDate());
                BigDecimal price = it.getNoTaxPrice();
                BigDecimal elecPrice = it.getElecPrice();
                BigDecimal marketPrice = it.getMarketPrice();
                dateList.add(startDate);
                dateList.add(endDate);
                priceList.add(price);
                priceList.add(price);
                elecPriceList.add(elecPrice);
                elecPriceList.add(elecPrice);
                marketPriceList.add(marketPrice);
                marketPriceList.add(marketPrice);
            }
        }
        map.put("date",dateList);
        map.put("price",priceList);
        map.put("elecPrice",elecPriceList);
        map.put("marketPrice",marketPriceList);
        return map;
    }

    @Override
    public Map<String,Object> findGoodsPriceITWithPage(GoodsPriceITCriteria criteria,
                                               Pageable pageable) {
        Page<GoodsPriceIT> page = goodsPriceITRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page);
    }

    @Override
    public List<GoodsPriceIT> findGoodsPriceITs(GoodsPriceITCriteria criteria) {
        return goodsPriceITRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
    }

    @Override
    public void download(HttpServletResponse response) throws IOException {
            List<String> titles = Arrays
                    .asList("类型","等级","标的描述",
                            "供应商","单位","未税单价","税率","地区","驻场",
                            "市场价","市场行情","生效起始时间","生效截止时间");
            FileUtil.downloadExcelTemplate(titles, response);
    }

    @Override
    public void download(List<GoodsPriceIT> goodsPriceITs, HttpServletResponse response) throws IOException {
                List<Map<String, Object>> list = new ArrayList<>();
        for(GoodsPriceIT goodsPriceIT : goodsPriceITs){
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("类型",goodsPriceIT.getType());
            map.put("等级",goodsPriceIT.getLevel());
            map.put("标的描述",goodsPriceIT.getGoodsDescribe());
            map.put("供应商",goodsPriceIT.getSupplier());
            map.put("单位",goodsPriceIT.getUnit());
            map.put("未税单价",goodsPriceIT.getNoTaxPrice());
            map.put("税率",goodsPriceIT.getTaxRate());
            map.put("地区",goodsPriceIT.getRegion());
            map.put("驻场",goodsPriceIT.getSite());
            map.put("市场价",goodsPriceIT.getMarketPrice());
            map.put("市场行情",goodsPriceIT.getMarketSource());
            map.put("生效起始时间",goodsPriceIT.getEffecticeStartDate());
            map.put("生效截止时间",goodsPriceIT.getEffecticeEndDate());
            list.add(map);
        };

        FileUtil.downloadExcel(list, response);
    }

    @Async
    @Override
    public void saveExcelData(List<GoodsPriceITExcel> goodsPriceITExcels) {
        List<GoodsPriceIT> goodsPriceITS = GoodsPriceITTransform.ExcelToEntity(goodsPriceITExcels);
        goodsPriceITRepository.saveAll(goodsPriceITS);
    }
}
