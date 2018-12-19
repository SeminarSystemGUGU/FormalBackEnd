package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.CourseMapper;
import com.gugu.gugumodel.mapper.StudentMapper;
import com.gugu.gugumodel.mapper.TeamMapper;
import com.gugu.gugumodel.mapper.TeamValidRequestMapper;
import com.gugu.gugumodel.mapper.TeamValidRequestMapper;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import com.gugu.gugumodel.pojo.entity.TeamValidEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDaoImpl implements TeamDao {
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TeamValidRequestMapper teamValidRequestMapper;
    @Override
    public TeamEntity getTeamById(Long team_id) {
        return teamMapper.findTeamById(team_id);
    }

    @Override
    public void updateTeam(TeamEntity teamEntity){
        teamMapper.updateTeam(teamEntity);
    }

    @Override
    public void deleteStudentFromTeam(Long teamId){
        teamMapper.deleteStudentFromTeam(teamId);
    }

    @Override
    public void buildRelationStuAndTeam(Long studentId,TeamEntity teamEntity){
        teamMapper.buildRelationStuAndTeam(studentId,teamEntity);
    }

    @Override
    public void deleteTeam(Long teamId){
        teamMapper.deleteTeam(teamId);
    }

    @Override
    public void deleteStudentTeamRelation(Long teamId){
        teamMapper.deleteStudentTeamRelation(teamId);
    }

    @Override
    public void addMember(Long teamId,Long studentId){
        TeamEntity teamEntity= teamMapper.findTeamById(teamId);
        teamMapper.addMember(teamEntity,studentId);
    }

    @Override
    public void removeMember(Long teamId,Long studentId){
        teamMapper.removeMember(teamId,studentId);
    }

    @Override
    public Long getTeacherIdByCourse(Long courseId){
       return courseMapper.getTeacherIdByCourse(courseId);
    }

    @Override
    public void teamValidRequest(TeamValidEntity teamValidEntity){
            teamValidRequestMapper.teamValidRequest(teamValidEntity);
    }

    @Override
    public Long getTeamValidStatus(Long teamId){
        return teamValidRequestMapper.getTeamValidStatus(teamId);
    }
}
