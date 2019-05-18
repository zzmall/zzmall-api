package com.zzmall.api.controller.portal;

import com.zzmall.api.common.constant.ApiConstant;
import com.zzmall.api.common.form.AnswerForm;
import com.zzmall.api.common.form.RegisterForm;
import com.zzmall.api.common.form.ResetPasswordForm;
import com.zzmall.api.common.form.UserInfoForm;
import com.zzmall.api.common.type.ResponseCode;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.ResponseVO;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.User;
import com.zzmall.api.service.UserService;
import com.zzmall.api.util.EncryptUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author Connor
 * @Date 2019/05/18 22:46
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ResponseVO register(@Valid RegisterForm form, BindingResult result) {

        //校验表单
        if (result.hasErrors()) {
            throw new ApiException(result.getFieldError().getDefaultMessage());
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        userService.register(user);

        return ResponseVO.success();
    }

    @RequestMapping("/check_username.do")
    public ResponseVO checkUsername(String username) {
        if (StringUtil.isNullOrEmpty(username)) {
            throw new ApiException(ResponseMessage.USER_USERNAME_NULL);
        }

        User user = userService.getUserByUsername(username);
        if (user != null) {
            throw new ApiException(ResponseMessage.USER_REGISTER_FAIL);
        }
        return ResponseVO.success();
    }

    @RequestMapping("/get_user_info.do")
    public ResponseVO<User> getUserInfo(HttpServletRequest request) {

        //获取session
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ApiConstant.LOGIN_PREFIX);

        if (user == null) {
            throw new ApiException(ResponseCode.NOT_LOGIN.getCode(), ResponseMessage.USER_NOT_LOGIN.getMessage());
        }
        return ResponseVO.success(user);

    }

    @RequestMapping("/forget_get_question.do")
    public ResponseVO<String> forgetQuestion(String username) {

        if (StringUtil.isNullOrEmpty(username)) {
            throw new ApiException(ResponseMessage.USER_QUESTION_FAIL);
        }

        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new ApiException(ResponseMessage.USER_QUESTION_FAIL);
        }

        return ResponseVO.success(user.getQuestion());

    }

    @RequestMapping("/forget_check_answer.do")
    public ResponseVO<String> forgetCheckAnswer(@Valid AnswerForm form, BindingResult result, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (result.hasErrors()) {
            throw new ApiException(result.getFieldError().getDefaultMessage());
        }

        User user = userService.getUserByUsername(form.getUsername());

        if (user == null) {
            throw new ApiException(ResponseMessage.USER_FAIL);
        }

        if (!user.getAnswer().equals(form.getAnswer())) {
            throw new ApiException(ResponseMessage.USER_ANSWER_FAIL);
        }

        return ResponseVO.success(session.getId());
    }

    @RequestMapping("/forget_reset_password.do")
    public ResponseVO forgetResetPassword(@Valid ResetPasswordForm form, BindingResult result, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (result.hasErrors()) {
            throw new ApiException(result.getFieldError().getDefaultMessage());
        }

        if (!session.getId().equals(form.getForgetToken())) {
            throw new ApiException(ResponseMessage.TOKEN_FAIL);
        }

        User user = userService.getUserByUsername(form.getUsername());

        //将新密码赋值过去
        user.setPassword(form.getPasswordNew());

        //修改密码
        userService.update(user);

        return ResponseVO.success(ResponseMessage.USER_PASSWORD_UPDATE_SUCCESS);
    }

    @RequestMapping("/reset_password.do")
    public ResponseVO resetPassword(String passwordOld, String passwordNew, HttpServletRequest request) {

        if (!StringUtil.isNullOrEmpty(passwordOld)) {
            throw new ApiException(ResponseMessage.USER_PASSWORD_NULL);
        }

        if (!StringUtil.isNullOrEmpty(passwordNew)) {
            throw new ApiException(ResponseMessage.USER_PASSWORD_NULL);
        }

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ApiConstant.LOGIN_PREFIX);

        if (user == null) {
            throw new ApiException(ResponseCode.NOT_LOGIN.getCode(), ResponseMessage.USER_NOT_LOGIN.getMessage());
        }

        if (!user.getPassword().equals(EncryptUtil.md5(passwordOld))) {
            throw new ApiException(ResponseMessage.USER_PASSWORD_OLD_FAIL);
        }

        userService.update(user);

        return ResponseVO.success(ResponseMessage.USER_PASSWORD_UPDATE_SUCCESS);
    }

    @RequestMapping("/update_information.do")
    public ResponseVO updateInfo(@Valid UserInfoForm form, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            throw new ApiException(result.getFieldError().getDefaultMessage());
        }

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ApiConstant.LOGIN_PREFIX);

        if (user == null) {
            throw new ApiException(ResponseCode.NOT_LOGIN.getCode(), ResponseMessage.USER_NOT_LOGIN.getMessage());
        }

        BeanUtils.copyProperties(form, user);
        userService.update(user);

        return ResponseVO.success(ResponseMessage.USER_UPDATE_INFO_SUCCESS);
    }









}
