package cn.com.hellowood.dynamicdatasource.modal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SecProduct {

    private long secId;
    @ApiModelProperty("主键id")
    private long id;
    @ApiModelProperty("产品名称")
    private String name;
    @ApiModelProperty("产品价格")
    private long price;
    private Integer num;
    private Date startDate;
    private Date endDate;
    private Date finishTime;

}
