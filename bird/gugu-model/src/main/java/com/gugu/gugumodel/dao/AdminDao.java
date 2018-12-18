package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.AdminMapper;
import com.gugu.gugumodel.pojo.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Component
public class AdminDao {
    @Autowired
    AdminMapper adminMapper;

    /**
     * 管理员登录
     * @param account
     * @return AdminEntity
     */
    public AdminEntity adminLogin(String account){
        return adminMapper.adminLogin(account);
    }
}
