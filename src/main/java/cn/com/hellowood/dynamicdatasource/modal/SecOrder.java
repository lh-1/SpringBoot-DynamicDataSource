package cn.com.hellowood.dynamicdatasource.modal;


import lombok.Data;

import java.util.Date;

@Data
public class SecOrder {

    private Long orderId;
    private String userCode;
    private Long secId;
    private Integer num;
    private Date createTime;
    private Date finishTime;
    private String status;

}
