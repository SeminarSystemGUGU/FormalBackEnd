package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.pojo.entity.AdminEntity;
import java.util.ArrayList;

/**
 * @author TYJ
 */
public interface AdminDao {

    /**
     * 管理员登录
     * @param account
     * @return AdminEntity
     */
    AdminEntity adminLogin(String account);
}
