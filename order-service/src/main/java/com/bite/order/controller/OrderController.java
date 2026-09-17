package com.bite.order.controller;

import com.bite.order.entity.OrderInfo;
import com.bite.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public ResponseEntity<Long> createOrder(@RequestBody OrderInfo order){
        log.info("创建订单, order:{}", order);
        Long orderId = orderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    /**
     * 查询订单
     */
    @RequestMapping("/query")
    public ResponseEntity<OrderInfo> queryOrder(Long orderId) throws InterruptedException {
        log.info("查询订单, orderId:{}", orderId);
        OrderInfo order = orderService.queryOrderById(orderId);
        if (orderId%5==0){
            throw new RuntimeException("模拟异常");
        }
        if (orderId==9){
            Thread.sleep(2000);
        }
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    /**
     * 模拟慢sql
     */
    @RequestMapping("/slowQuery")
    public ResponseEntity<String> slowQueryOrder(Integer time){
        log.info("slow query, time:{}", time);
        orderService.slowQueryOrder(time);
        return ResponseEntity.status(HttpStatus.OK).body("slow query");
    }
}
