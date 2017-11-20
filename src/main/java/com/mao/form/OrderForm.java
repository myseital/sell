package com.mao.form;

import com.mao.exception.SellException;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import sun.security.rsa.RSASignature;

/**
 * Created by myseital  on 2017/11/20.
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号码必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
