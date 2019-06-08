package com.zzmall.api.controller;

import com.zzmall.api.common.constant.ApiConstant;
import com.zzmall.api.common.form.LoginForm;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.ResponseVO;
import com.zzmall.api.pojo.User;
import com.zzmall.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author Connor
 * @Date 2019/05/18 20:23
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.do")
    public ResponseVO<User> login(@Valid LoginForm form,
                                  BindingResult bindingResult,
                                  HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();

        User user = userService.login(form.getUsername(), form.getPassword());

        session.setAttribute(ApiConstant.LOGIN_PREFIX, user);

        return ResponseVO.success(user);
    }


    @RequestMapping(value = "/logout.do")
    public ResponseVO<User> logout(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();
        //清除用户信息
        session.removeAttribute(ApiConstant.LOGIN_PREFIX);

        return ResponseVO.success(ResponseMessage.USER_LOGOUT_SUCCESS);
    }

    @RequestMapping(value = "/test.do")
    public ResponseVO test(@NotBlank(message = "名称不能为空") String name, BindingResult bindingResult) {
        return ResponseVO.success(name);
    }


}
