package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.ShareApplicationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Mapper
@Repository
public interface ShareSeminarMapper {

    /**
     * 获得共享讨论课信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getSeminarShareList(Long userId);


}