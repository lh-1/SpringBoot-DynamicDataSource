package cn.com.hellowood.dynamicdatasource.mapper;

import cn.com.hellowood.dynamicdatasource.modal.SecOrder;
import cn.com.hellowood.dynamicdatasource.modal.SecProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SecProductDao {

    SecProduct findSecInfo(@Param("secId") long secId);

    int decreaseSecNum(long secId);

    void execOrder(SecOrder secOrder);

}
