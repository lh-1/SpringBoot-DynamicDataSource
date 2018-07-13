package cn.com.hellowood.dynamicdatasource.service;

import cn.com.hellowood.dynamicdatasource.mapper.SecProductDao;
import cn.com.hellowood.dynamicdatasource.modal.SecOrder;
import cn.com.hellowood.dynamicdatasource.modal.SecProduct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class SecProductService {

//    private static final Logger logger = LoggerFactory.getLogger(SecProductService.class);

    @Autowired
    private SecProductDao secProductDao;

    public Object execSec(Long secId, String tokenId) {
        SecProduct secProduct = secProductDao.findSecInfo(secId);
        if (secProduct != null && secProduct.getNum() > 0) {
            SecOrder order = new SecOrder();
            order.setSecId(secId);
            order.setUserCode(tokenId);
            order.setCreateTime(new Date());
            order.setFinishTime(new Date());
            order.setNum(1);
            order.setStatus("00");

            int count = secProductDao.decreaseSecNum(secId);
            if (count == 1) {
                secProductDao.execOrder(order);
                log.info(tokenId + "== success");
                return "success";
            } else {
                log.info(tokenId + "== no product decreaseSecNum");
                return "no product decreaseSecNum";
            }
        } else {
            log.info(tokenId + "== num is zero");
            return "no product";
        }
    }

}
