package me.zhengjie.modules.pricelibaray.rest;

import com.alibaba.excel.EasyExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.pricelibaray.domain.GoodsPriceIT;
import me.zhengjie.modules.pricelibaray.service.GoodsPriceITService;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceITExcel;
import me.zhengjie.modules.pricelibaray.service.listener.GoodsPriceITListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 13:00
 **/
@Api(tags = "应用：价格库导入")
@RestController
@RequestMapping("/api/goodspricelibiarayexport")
@RequiredArgsConstructor
public class GoodsPriceLibiarayImportController {

    @Autowired
    private GoodsPriceITService goodsPriceITService;

    @ApiOperation("上传价格库文件")
    @PostMapping
    public ResponseEntity<Object> create(@RequestParam String type, @RequestParam("file") MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), GoodsPriceITExcel.class,new GoodsPriceITListener(goodsPriceITService))
                .sheet()
                .headRowNumber(1)
                .doRead();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
