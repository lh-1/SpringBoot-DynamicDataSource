package cn.com.hellowood.dynamicdatasource.controller;

import cn.com.hellowood.dynamicdatasource.common.CommonResponse;
import cn.com.hellowood.dynamicdatasource.common.ResponseUtil;
import cn.com.hellowood.dynamicdatasource.service.SecProductService;
import cn.com.hellowood.dynamicdatasource.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secController")
public class SecController {

    @Autowired
    private SecProductService secProductService;

    @RequestMapping(path = "/execSec/{secId}", method = RequestMethod.POST)
    public CommonResponse execSec(@PathVariable("secId") Long secId, @RequestHeader String tokenId) throws ServiceException {
        Object result = secProductService.execSec(secId, tokenId);
        return ResponseUtil.generateResponse(result);
    }

}
