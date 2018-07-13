package cn.com.hellowood.dynamicdatasource.controller;

import cn.com.hellowood.dynamicdatasource.service.SecProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/eureka")
public class EurekaController {

    @Autowired
    private SecProductService secProductService;

    @RequestMapping(value = "/findSecList/{secId}", method = RequestMethod.POST)
    public Object findSecList(@PathVariable("secId") Long secId){
        return secProductService.findSecInfo(secId);
    }

}
