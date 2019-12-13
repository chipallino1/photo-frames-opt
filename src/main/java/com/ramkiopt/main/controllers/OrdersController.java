package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.OrdersDto;
import com.ramkiopt.main.services.app.orders.OrdersService;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService<OrdersDto> ordersService;
    private final BaseResponseService responseService;

    public OrdersController(OrdersService<OrdersDto> ordersService, BaseResponseService responseService) {
        this.ordersService = ordersService;
        this.responseService = responseService;
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody OrdersDto ordersDto) {
        return responseService.createResponseEntity(ordersService.create(ordersDto), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateOrder(@RequestBody OrdersDto ordersDto) {
        return responseService.createResponseEntity(ordersService.update(ordersDto.getId(), ordersDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity readOrder(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(ordersService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(ordersService.delete(id), HttpStatus.OK);
    }
}
