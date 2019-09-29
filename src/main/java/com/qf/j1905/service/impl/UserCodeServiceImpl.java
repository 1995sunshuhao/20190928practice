package com.qf.j1905.service.impl;

import com.qf.j1905.dao.UserCodeMapper;
import com.qf.j1905.domain.User;
import com.qf.j1905.service.UserCodeService;
import com.qf.j1905.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCodeServiceImpl implements UserCodeService {
    @Autowired
    private UserCodeMapper userCodeMapper;
    @Autowired
    private EmailUtils emailUtils;
    @Override
    public boolean sendCode(String userMail) {
        if (emailUtils.sendMail(userMail)!=null){
            return true;
        }else
            return false;
    }
}
