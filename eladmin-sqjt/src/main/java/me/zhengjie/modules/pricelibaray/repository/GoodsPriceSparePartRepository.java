package me.zhengjie.modules.pricelibaray.repository;

import me.zhengjie.modules.pricelibaray.domain.GoodsPriceSparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GoodsPriceSparePartRepository
        extends JpaRepository<GoodsPriceSparePart, Long>,
        JpaSpecificationExecutor<GoodsPriceSparePart> {
}
