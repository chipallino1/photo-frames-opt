package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.MailDto;
import com.ramkiopt.main.dto.OrdersDto;
import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.orders.OrdersService;
import com.ramkiopt.main.services.app.users.UsersService;
import com.ramkiopt.main.services.utils.MailSender;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/mail")
public class MailController {
    private final MailSender mailSender;
    private final BaseResponseService responseService;
    private final UsersService<UsersDto> usersService;
    private final OrdersService<OrdersDto> ordersService;

    public MailController(MailSender mailSender,
                          BaseResponseService responseService,
                          UsersService<UsersDto> usersService,
                          OrdersService<OrdersDto> ordersService) {
        this.mailSender = mailSender;
        this.responseService = responseService;
        this.usersService = usersService;
        this.ordersService = ordersService;
    }

    @PostMapping("/sendOrderCreated")
    public ResponseEntity send(@RequestBody MailDto mailDto, Principal principal) {
        mailSender.sendOrderCreated("New order from " + principal.getName(),
                "\t" + mailDto.getSubject() + "\n\n" + mailDto.getMessage());
        return responseService.createResponseEntity(true, HttpStatus.OK);
    }

    @GetMapping("/mailConfirm")
    public ResponseEntity sendConfirm(@RequestParam("orderId") Long orderId,
                                      @RequestParam("userId") Long userId) {
        UsersDto usersDto = usersService.get(userId);
        OrdersDto ordersDto = ordersService.get(orderId);
        ordersDto.setOrderStatus("confirmed");
        ordersService.update(orderId, ordersDto);

        mailSender.sendOrderConfirmed(usersDto.getEmail(), "Order confirmed",
                "Hi, " + usersDto.getFirstName() + ". Your orders confirmed!");
        return responseService.createResponseEntity(true, HttpStatus.OK);
    }
}