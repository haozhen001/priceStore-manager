package me.zhengjie.modules.pricelibaray.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.pricelibaray.domain.GoodsPriceIT;
import me.zhengjie.modules.pricelibaray.service.GoodsPriceITService;
import me.zhengjie.modules.pricelibaray.service.GoodsPriceSparePartService;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceITCriteria;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceSparePartCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 11:56
 **/
@Api(tags = "应用：价格库查询")
@RestController
@RequestMapping("/api/goodspricelibaray")
@RequiredArgsConstructor
public class GoodsPriceLibarayController {

    @Autowired
    private GoodsPriceITService goodsPriceITService;

    @Autowired
    private GoodsPriceSparePartService goodsPriceSparePartService;

    @GetMapping("/findGoodsPriceIT")
    public ResponseEntity<Object> findGoodsPriceIT(GoodsPriceITCriteria criteria,
                                                    Pageable pageable){
        return new ResponseEntity<>(goodsPriceITService.
                findGoodsPriceITWithPage(criteria,pageable), HttpStatus.OK);
    }
    @GetMapping("/findGoodsPriceSparePart")
    public ResponseEntity<Object> findGoodsPriceSparePart(GoodsPriceSparePartCriteria criteria,
                                                           Pageable pageable){
        return new ResponseEntity<>(goodsPriceSparePartService.
                findGoodsPriceSparePart(criteria,pageable), HttpStatus.OK);
    }


    @PutMapping("/findGoodsPriceIT")
    public ResponseEntity<Object> updateGoodsPriceIT(@RequestBody GoodsPriceIT goodsPriceIT){
        if(Objects.equals(null,goodsPriceIT) || Objects.equals(null,goodsPriceIT.getId())){
            throw  new BadRequestException("请传入价格库标识ID");
        }

        goodsPriceITService.saveGoodsPriceIT(goodsPriceIT);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<Object> saveGoodsPriceIT(@RequestBody GoodsPriceIT goodsPriceIT){
        goodsPriceITService.saveGoodsPriceIT(goodsPriceIT);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/queryGoodsPriceITByDescipt")
    public ResponseEntity<Object> queryGoodsPriceITByDescipt(@RequestParam("descript") String descript){
        if(Objects.equals(null,descript)){
            throw  new BadRequestException("请传入物品标的描述");
        }
        return new ResponseEntity<>(goodsPriceITService.findGoodsPriceITByDescript(descript),HttpStatus.OK);
    }

    @DeleteMapping("/findGoodsPriceIT")
    public ResponseEntity<Object> deleteGoodsPriceITById(@RequestBody Set<Long> ids){
        goodsPriceITService.deleteGoodsPriceIT(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findGoodsPriceIT/download")
    public void exportGoodsPriceIT(GoodsPriceITCriteria criteria, HttpServletResponse response) throws IOException {
            goodsPriceITService.download(goodsPriceITService.findGoodsPriceITs(criteria),response);
    }

    @GetMapping("/findGoodsPriceIT/downloadTemplate")
    public void exportGoodsPriceIT(HttpServletResponse response) throws IOException {
        goodsPriceITService.download(response);
    }

    @GetMapping("/findGoodsPriceSparePart/download")
    public void exportGoodsPriceSparePart(GoodsPriceSparePartCriteria criteria, HttpServletResponse response) throws IOException {
        goodsPriceSparePartService.download(goodsPriceSparePartService.findGoodsPriceSparePart(criteria),response);

    }

}
