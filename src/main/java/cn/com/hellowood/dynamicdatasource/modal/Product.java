package cn.com.hellowood.dynamicdatasource.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Product bean
 *
 * @author HelloWood
 * @date 2017-07-11 11:09
 * @Email hellowoodes@gmail.com
 */
@ApiModel("产品对象DTO")
public class Product implements Serializable {
    private static final long serialVersionUID = 1435515995276255188L;

    @ApiModelProperty("主键id")
    private long id;
    @ApiModelProperty("产品名称")
    private String name;
    @ApiModelProperty("产品价格")
    private long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
