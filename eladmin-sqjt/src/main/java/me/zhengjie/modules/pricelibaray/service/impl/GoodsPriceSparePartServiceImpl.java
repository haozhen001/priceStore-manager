package me.zhengjie.modules.pricelibaray.service.impl;

import me.zhengjie.modules.pricelibaray.domain.GoodsPriceIT;
import me.zhengjie.modules.pricelibaray.domain.GoodsPriceSparePart;
import me.zhengjie.modules.pricelibaray.repository.GoodsPriceSparePartRepository;
import me.zhengjie.modules.pricelibaray.service.GoodsPriceSparePartService;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceSparePartCriteria;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 12:01
 **/
@Service
@Transactional
public class GoodsPriceSparePartServiceImpl
        implements GoodsPriceSparePartService {

    @Autowired
    private GoodsPriceSparePartRepository goodsPriceSparePartRepository;

    @Override
    public Map<String,Object> findGoodsPriceSparePart(
            GoodsPriceSparePartCriteria criteria, Pageable pageable) {
        Page<GoodsPriceSparePart> page = goodsPriceSparePartRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page);
    }

    @Override
    public List<GoodsPriceSparePart> findGoodsPriceSparePart(GoodsPriceSparePartCriteria criteria) {
        return  goodsPriceSparePartRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)) ;
    }

    @Override
    public void download(List<GoodsPriceSparePart> goodsPriceSpareparts, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        goodsPriceSpareparts.forEach(goodsPriceSparePart -> {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("大类",goodsPriceSparePart.getCategory());
            map.put("类别",goodsPriceSparePart.getType());
            map.put("标的描述",goodsPriceSparePart.getGoodsDescribe());
            map.put("单位",goodsPriceSparePart.getUnit());
            map.put("规格/描述",goodsPriceSparePart.getSpecificationDescribe());
            map.put("未税单价",goodsPriceSparePart.getNoTaxPrice());
            map.put("税率",goodsPriceSparePart.getTaxRate());
            map.put("供应商",goodsPriceSparePart.getSupplier());
            map.put("物料号",goodsPriceSparePart.getMaterialNumber());
            map.put("年月",goodsPriceSparePart.getEffecticeDate());
            map.put("电商价",goodsPriceSparePart.getElecPrice());
            map.put("市场价",goodsPriceSparePart.getMarketPrice());
            map.put("录入者",goodsPriceSparePart.getInputer());
            list.add(map);
        });
        FileUtil.downloadExcel(list, response);
    }
}
