package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.TeamMapper;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class TeamDaoImpl implements TeamDao {
    @Autowired
    TeamMapper teamMapper;
    @Override
    public TeamEntity getTeamById(Long team_id) {
        return teamMapper.findTeamById(team_id);
    }
}
