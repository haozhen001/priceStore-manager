package me.zhengjie.modules.pricelibaray.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/30 17:47
 **/
public interface ImportGoodsPriceService {

    public void importGoodsPriceIT(MultipartFile file);
}
