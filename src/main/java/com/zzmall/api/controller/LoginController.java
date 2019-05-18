package com.zzmall.api.controller;

import com.zzmall.api.common.constant.ApiConstant;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.ResponseVO;
import com.zzmall.api.pojo.User;
import com.zzmall.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Connor
 * @Date 2019/05/18 20:23
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ResponseVO<User> login(String username, String password,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) {
        HttpSession session = httpServletRequest.getSession();

        User user = userService.login(username, password);

        session.setAttribute(ApiConstant.LOGIN_PREFIX, user);

        return ResponseVO.success(user);
    }


    @RequestMapping(value = "/logout.do")
    public ResponseVO<User> logout(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) {

        HttpSession session = httpServletRequest.getSession();
        //清除用户信息
        session.removeAttribute(ApiConstant.LOGIN_PREFIX);

        return ResponseVO.success(ResponseMessage.USER_LOGOUT_SUCCESS);
    }



}
