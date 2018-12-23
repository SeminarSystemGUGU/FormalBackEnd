package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.TeamValidEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ljy
 */
@Mapper
@Repository
public interface TeamValidRequestMapper {

     void teamValidRequest(TeamValidEntity teamValidEntity);

     Long getTeamValidStatus(Long teamId);

     /**
      * 教师获得组队申请信息
      * @param teacherId
      * @return
      */
     ArrayList<TeamValidEntity> getTeamRequestList(Long teacherId);

     /**
      * 根据ID获取组队申请信息
      * @param requestId
      * @return
      */
     TeamValidEntity getTeamRequestById(Long requestId);

     /**
      * 修改组队申请的状态
      * @param requestId
      * @param status
      * @return
      */
     void changeTeamRequestStatus(Long requestId,Byte status);
}
