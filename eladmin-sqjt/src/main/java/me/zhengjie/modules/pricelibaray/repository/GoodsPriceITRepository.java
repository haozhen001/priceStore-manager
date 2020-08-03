package me.zhengjie.modules.pricelibaray.repository;

import me.zhengjie.modules.pricelibaray.domain.GoodsPriceIT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 11:51
 **/
public interface GoodsPriceITRepository
        extends JpaRepository<GoodsPriceIT, Long>,
        JpaSpecificationExecutor<GoodsPriceIT> {

    @Modifying
    @Query(value = "update goods_price_it set delflag = 1 where id=?1" ,nativeQuery = true)
    void deleteById(String id);

    @Modifying
    @Query(value = "update GoodsPriceIT it set it.delflag = 1 where it.id=:ids")
    void deleteByIds(Set<Long> ids);


    List<GoodsPriceIT> findByGoodsDescribeAndDelflagOrderByEffecticeStartDate(String goodsDescribe,Integer delflag);

}
