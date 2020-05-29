package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    // private static  final  String URL = "http://localhost:8001";
    private static  final  String URL = "http://CLOUD-PAYMENT-SERVICE/";
    @Resource
    private RestTemplate restTemplate;



    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return  restTemplate.postForObject(URL + "payment/create", payment, CommonResult.class);

    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id")Long id ){
        return  restTemplate.getForObject(URL + "/payment/get/"+ id , CommonResult.class);

    }

}
