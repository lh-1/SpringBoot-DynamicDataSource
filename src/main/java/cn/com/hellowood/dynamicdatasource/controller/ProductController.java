package cn.com.hellowood.dynamicdatasource.controller;

import cn.com.hellowood.dynamicdatasource.common.CommonResponse;
import cn.com.hellowood.dynamicdatasource.common.ResponseUtil;
import cn.com.hellowood.dynamicdatasource.configuration.comsuming.Consuming;
import cn.com.hellowood.dynamicdatasource.modal.Product;
import cn.com.hellowood.dynamicdatasource.service.ProductService;
import cn.com.hellowood.dynamicdatasource.utils.ServiceException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Product controller
 *
 * @author HelloWood
 * @date 2017-07-11 11:38
 * @Email hellowoodes@gmail.com
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Get product by id
     *
     * @param productId
     * @return
     * @throws ServiceException
     */
//    @GetMapping("/{id}")
    @Consuming
    @ApiOperation(value = "查询单个产品", notes = "通过id查询产品")
    @ApiParam(name = "id", value = "产品id", required = true)
    @RequestMapping(value = "/getProduct/{id}", method = RequestMethod.POST)
    public CommonResponse getProduct(@PathVariable("id") Long productId, @RequestHeader String tokenId) throws ServiceException {
        return ResponseUtil.generateResponse(productService.getProduct(productId, tokenId));
    }


    /**
     * Get all product
     *
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/getAllProduct", method = RequestMethod.POST)
    public CommonResponse getAllProduct() {
        return ResponseUtil.generateResponse(productService.getAllProduct());
    }

    /**
     * Update product by id
     *
     * @param productId
     * @param newProduct
     * @return
     * @throws ServiceException
     */
//    @PutMapping("/{id}")
    @RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.PUT)
    public CommonResponse updateProduct(@PathVariable("id") Long productId, @RequestBody Product newProduct) throws ServiceException {
        return ResponseUtil.generateResponse(productService.updateProduct(productId, newProduct));
    }

    /**
     * Delete product by id
     *
     * @param productId
     * @return
     * @throws ServiceException
     */
//    @DeleteMapping("/{id}")
    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
    public CommonResponse deleteProduct(@PathVariable("id") long productId) throws ServiceException {
        return ResponseUtil.generateResponse(productService.deleteProduct(productId));
    }

    /**
     * Save product
     *
     * @param newProduct
     * @return
     * @throws ServiceException
     */
//    @PostMapping
    @ApiOperation(value = "添加新产品")
    @ApiImplicitParam(name = "newProduct", value = "产品对象", dataType = "产品对象DTO", required = true)
    @ApiImplicitParams({@ApiImplicitParam(name = "1"), @ApiImplicitParam(name = "2")})
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public CommonResponse addProduct(@RequestBody Product newProduct) throws ServiceException {
        return ResponseUtil.generateResponse(productService.addProduct(newProduct));
    }
}
