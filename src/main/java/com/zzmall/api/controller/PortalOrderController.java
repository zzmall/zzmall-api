package com.zzmall.api.controller;

import com.zzmall.api.common.form.PageForm;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.OrderItemVO;
import com.zzmall.api.common.vo.OrderVO;
import com.zzmall.api.common.vo.ResponseVO;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.User;
import com.zzmall.api.service.OrderService;
import com.zzmall.api.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;

/**
 * @Author Connor
 * @Date 2019/05/21 21:25
 */
@RestController
@RequestMapping("/order")
public class PortalOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create.do")
    public ResponseVO<OrderVO> create(Integer shippingId, HttpServletRequest request) {
        //获取登陆用户的信息
        User user = SecurityUtil.isLogin(request.getSession());
        if (shippingId == null) {
            throw new ApiException(ResponseMessage.SHIPPING_ID_NULL);
        }
        OrderVO orderVO = orderService.create(shippingId, user.getId());
        return ResponseVO.success(orderVO);
    }

    @RequestMapping("/get_order_cart_product.do")
    public ResponseVO<OrderItemVO> getOrderCartProduct(BigInteger orderNo, HttpServletRequest request) {
        User user = SecurityUtil.isLogin(request.getSession());
        if (orderNo == null) {
            throw new ApiException(ResponseMessage.ORDER_ID_NULL);
        }
        OrderItemVO orderItemVO = orderService.getOrderProduct(orderNo, user.getId());
        return ResponseVO.success(orderItemVO);
    }

    @RequestMapping("/list.do")
    public ResponseVO<Page<OrderVO>> list(@Valid PageForm form, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            throw new ApiException(result.getFieldError().getDefaultMessage());
        }
        User user = SecurityUtil.isLogin(request.getSession());
        Page<OrderVO> page = orderService.list(
                PageRequest.of(form.getPageNum() - 1, form.getPageSize()),
                user.getId()
        );
        return ResponseVO.success(page);
    }

    @RequestMapping("/detail.do")
    public ResponseVO<OrderVO> detail(BigInteger orderNo, HttpServletRequest request) {
        User user = SecurityUtil.isLogin(request.getSession());
        OrderVO detail = orderService.detail(orderNo, user.getId());
        return ResponseVO.success(detail);
    }

    @RequestMapping("/cancel.do")
    public ResponseVO cancel(BigInteger orderNo, HttpServletRequest request) {
        User user = SecurityUtil.isLogin(request.getSession());
        orderService.cancel(orderNo, user.getId());
        return ResponseVO.success();
    }


}
