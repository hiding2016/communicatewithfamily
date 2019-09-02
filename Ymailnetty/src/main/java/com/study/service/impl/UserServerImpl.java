package com.study.service.impl;

import com.study.mapper.UsersMapper;
import com.study.pojo.Users;
import com.study.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


@Service
public class UserServerImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Users user = new Users();
        user.setUsername(username);
        Users result = usersMapper.selectOne(user);
        return result!=null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);

        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("useranme", username);
        criteria.andEqualTo("password", password);

        Users result = usersMapper.selectOneByExample(userExample);
        return result;
    }

    @Override
    public Users saveUser(Users user) {
        //todo 为每一个用户生成唯一的二维码
        user.setQrcode("");
        String userID = sid.nextShort();
        user.setId(userID);
        usersMapper.insert(user);
        return user;
    }
}
