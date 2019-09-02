package com.study.service;

import com.study.pojo.Users;

public interface UserService {
//    判断用户名是否存在
    public boolean queryUsernameIsExist(String username);

    public Users queryUserForLogin(String username, String password);

    public Users saveUser(Users users);
}
