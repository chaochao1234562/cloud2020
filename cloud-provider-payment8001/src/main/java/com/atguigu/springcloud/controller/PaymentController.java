package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentServiceImpl;
import com.netflix.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

   @Resource
   private PaymentServiceImpl paymentServiceImpl;

   @Value("${server.port}")
   private String serverPort;


   @Resource
   private DiscoveryClient discoveryClient;



   @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentServiceImpl.create(payment);
        log.info("***********插入结果：" + result);

        if(result > 0) {
         return    new  CommonResult(200, "成功 端口为：" + serverPort, result);
        } else {
            return   new  CommonResult(500, "失败", result);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult getPymengByid(@PathVariable("id") Long id ) {

        Payment result = paymentServiceImpl.getPaymentById(id);
        log.info("***********插入结果：" + result);

        if(result != null) {

            return    new  CommonResult(200, "成功", result);
        } else {
            return   new  CommonResult(500, "没有对应记录，查询id" + id, result);
        }

    }


}
