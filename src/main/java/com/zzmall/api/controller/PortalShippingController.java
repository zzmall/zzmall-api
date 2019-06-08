package com.zzmall.api.controller;

import com.zzmall.api.common.form.PageForm;
import com.zzmall.api.common.form.ShippingForm;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.ResponseVO;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.Shipping;
import com.zzmall.api.pojo.User;
import com.zzmall.api.service.ShippingService;
import com.zzmall.api.util.BeanUtils;
import com.zzmall.api.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Connor
 * @Date 2019/05/21 19:48
 */
@RestController
@RequestMapping("/shipping")
public class PortalShippingController {


    @Autowired
    private ShippingService shippingService;


    /**
     * 添加地址
     *
     * @param form
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/add.do")
    public ResponseVO<Map<String, Integer>> add(@Valid ShippingForm form, BindingResult result, HttpServletRequest request) {
        Map<String, Integer> map = new HashMap<>();

        //获取当前登陆用户
        User user = SecurityUtil.isLogin(request.getSession());

        //如果userId为null的话 默认为当前用户id
        if (form.getUserId() == null) {
            form.setUserId(user.getId());
        }

        //检验表单
        if (result.hasErrors()) {
            throw new ApiException(result.getFieldError().getDefaultMessage());
        }

        //检验传入的用户名是否是当前登陆的用户
        if (form.getUserId() != user.getId()) {
            throw new ApiException(ResponseMessage.USER_LOGIN_ID_FAIL);
        }

        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        Integer shippingId = shippingService.add(shipping);
        map.put("shippingId", shippingId);
        return ResponseVO.success(map);
    }

    /**
     * 删除地址
     *
     * @param shippingId
     * @param request
     * @return
     */
    @RequestMapping("/del.do")
    public ResponseVO delete(Integer shippingId, HttpServletRequest request) {
        User user = SecurityUtil.isLogin(request.getSession());
        shippingService.delete(shippingId, user.getId());
        return ResponseVO.success(ResponseMessage.SHIPPING_DELETE_SUCCESS);
    }

    /**
     * 更新地址
     *
     * @param form
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/update.do")
    public ResponseVO update(@Valid ShippingForm form, BindingResult result, HttpServletRequest request) {
        //获取当前登陆用户
        User user = SecurityUtil.isLogin(request.getSession());

        form.setUserId(user.getId());

        //检验表单
        if (result.hasErrors()) {
            throw new ApiException(result.getFieldError().getDefaultMessage());
        }

        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shippingService.update(shipping);
        return ResponseVO.success(ResponseMessage.SHIPPING_UPDATE_SUCCESS);
    }

    /**
     * 选中查看具体的地址
     *
     * @param shippingId
     * @param request
     * @return
     */
    @RequestMapping("/select.do")
    public ResponseVO<Shipping> select(Integer shippingId, HttpServletRequest request) {

        User user = SecurityUtil.isLogin(request.getSession());
        Shipping shipping = shippingService.getShippingById(shippingId, user.getId());
        return ResponseVO.success(shipping);
    }

    /**
     * 地址列表
     *
     * @param form
     * @param request
     * @return
     */
    @RequestMapping("/list.do")
    public ResponseVO<Page<Shipping>> select(PageForm form, HttpServletRequest request) {

        User user = SecurityUtil.isLogin(request.getSession());

        Page<Shipping> pages = shippingService.list(
                PageRequest.of(form.getPageNum() - 1, form.getPageSize()),
                user.getId()
        );

        return ResponseVO.success(pages);
    }




}
