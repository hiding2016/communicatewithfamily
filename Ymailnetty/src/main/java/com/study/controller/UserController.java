package com.study.controller;

import com.study.pojo.Users;
import com.study.pojo.vo.UsersVo;
import com.study.service.UserService;
import com.study.utils.MD5Utils;
import com.study.utils.YmailJsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("u")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/registOrLogin")
    public YmailJsonResult registOrLogin(@RequestBody Users user) throws Exception {
        if (StringUtils.isBlank(user.getUsername())|| StringUtils.isBlank(user.getPassword())){
            return YmailJsonResult.errorMsg("用户名或者密码不能为空！");
        }
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        Users userResult = null;
        if (usernameIsExist){
            //登陆
             userResult = userService.queryUserForLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));
            if (userResult == null){
                return YmailJsonResult.errorMsg("用户名或者密码不正确");
            }
        }else {
            //注册
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userResult = userService.saveUser(user);
        }
        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(userResult,usersVo);
        return YmailJsonResult.ok();
    }
}
