package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.entity.strategy.*;
import com.gugu.gugumodel.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TeamDao{
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    KlassMapper klassMapper;
    @Autowired
    KlassStudentMapper klassStudentMapper;
    @Autowired
    TeamValidRequestMapper teamValidRequestMapper;
    @Autowired
    SeminarScoreMapper seminarScoreMapper;
    @Autowired
    StrategyMapper strategyMapper;

    /**
     * 根据teamid获取team的信息
     * @param team_id
     * @return
     */
    public TeamEntity getTeamById(Long team_id) {
        return teamMapper.findTeamById(team_id);
    }


    /**
     * 修改team的信息
     * @param teamEntity
     */
    public void updateTeam(TeamEntity teamEntity){
        teamMapper.updateTeam(teamEntity);
    }

    /**
     * 删除小组成员
     * @param teamId
     */
    public void deleteStudentFromTeam(Long teamId){
        teamMapper.deleteStudentFromTeam(teamId);
    }


    /**
     * 建立学生和小组之间的关系即新加成员
     * @param studentId
     * @param teamEntity
     */
    public void buildRelationStuAndTeam(Long studentId,TeamEntity teamEntity){
        teamMapper.buildRelationStuAndTeam(studentId,teamEntity.getId());
    }

    /**
     * 根据teamid删除小组信息
     * @param teamId
     */
    public void deleteTeam(Long teamId){
        teamMapper.deleteTeam(teamId);
    }

    /**
     * 删除学生和小组之间的关系
     * @param teamId
     */
    public void deleteStudentTeamRelation(Long teamId){
        teamMapper.deleteStudentTeamRelation(teamId);
    }


    /**
     * 新增成员，获取队伍人员变更后是否合法
     * @param teamId
     * @param studentId
     * @return
     */
    public Byte addMember(Long teamId,Long studentId){
       // TeamEntity teamEntity= teamMapper.findTeamById(teamId);
         klassStudentMapper.addMember(teamId,studentId);
         return teamMapper.getTeamStatusById(teamId);
    }

    /**
     * 移除小组成员
     * @param teamId
     * @param studentId
     * @return
     */
    public Byte removeMember(Long teamId,Long studentId){
        teamMapper.removeMember(teamId,studentId);
        return teamMapper.getTeamStatusById(teamId);
    }

    /**
     * 获取课程所属的老师的id
     * @param courseId
     * @return
     */
    public Long getTeacherIdByCourse(Long courseId){
       return courseMapper.getTeacherIdByCourse(courseId);
    }

    /**
     * 小组非法申请
     * @param teamValidEntity
     */
    public void teamValidRequest(TeamValidEntity teamValidEntity){
        teamValidRequestMapper.teamValidRequest(teamValidEntity);
    }

    /**
     * 新建小组申请
     * @param
     */
    public void setTeamStatus(Long teamId){
        teamMapper.setTeamStatus(teamId);
    }

    /**
     * 获取小组状态
     * @param teamId
     * @return
     */
    public Long getTeamValidStatus(Long teamId){
        return teamValidRequestMapper.getTeamValidStatus(teamId);
    }

    /**
     * 删除班级下的小组
     */
    public boolean deleteByKlassId(Long klassId){
        teamMapper.deleteByKlassId(klassId);
        return true;
    }

    /**@author ljy
     * 新建队伍,返回队伍id
     * @param
     * @return
     */
    public Long newTeam(ArrayList<StudentEntity> memberStudents, TeamEntity teamEntity){
        teamMapper.newTeam(teamEntity);
        teamMapper.newKlassTeam(teamEntity);
        for(int i=0;i<memberStudents.size();i++){
            klassStudentMapper.addMember(teamEntity.getId(),memberStudents.get(i).getId());
        }
        Long teamId=teamEntity.getId();
        return teamId;
    }

    /**
     * @author TYJ
     * 修改小组状态
     * @param teamId
     * @param status
     * @return
     */
    public int changeTeamStatus(Long teamId,Byte status){
        return teamMapper.changeTeamStatus(teamId,status);
    }

    /**
     * @author TYJ
     * 删除课程下的所有小组 ,数据库修改后新修改
     * @param courseId
     * @return
     */
    public void deleteAllTeamByCourseId(Long courseId){
        //获取课程下的所有小组
        ArrayList<TeamEntity> teamEntities=teamMapper.getAllTeamByCourseId(courseId);
        for(int i=0;i<teamEntities.size();i++){
            //删除klass_team表中的联系
            klassStudentMapper.removeKlassTeamRelation(teamEntities.get(i).getId());
            //删除team_student表中的联系
            klassStudentMapper.removeStudentTeamRelation(teamEntities.get(i).getId());
        }
        ArrayList<KlassEntity> klassList=klassMapper.getKlassByCourseId(courseId);
        for(int i=0;i<klassList.size();i++){
            teamMapper.deleteAllTeamByKlassId(klassList.get(i).getId());
        }
    }

    /**
     * @author TYJ
     * 根据课程获得所有小组
     * @param courseId
     * @return
     */
    public ArrayList<TeamEntity> getAllTeamByCourseId(Long courseId){
        return teamMapper.getAllTeamByCourseId(courseId);
    }

    /**
     * @author TYJ
     * 创建KlassTeam副本
     * @param klassId
     * @param teamId
     */
    public void createKlassTeam(Long klassId,Long teamId){
        teamMapper.createKlassTeam(klassId,teamId);
    }

    /**
     * @author TYJ
     * 获取当前所有的serial
     * @param klassId
     */
    public ArrayList<Byte> getSerial(Long klassId){
        return teamMapper.getSerial(klassId);
    }

    /**
     * @author ljy
     * 获取当前所有的serial
     * @param teamId
     * @param status
     */
    public void setStatus(Long teamId,Byte status){
        teamMapper.setStatus(teamId,status);
    }

    /**
     * 正式调用究极复杂递归的地方
     */
    public boolean teamIsLeagal(Long courseId,Long teamId){
        return isLegal("TeamStrategy",courseId,teamId);
    }
    /**
     * 究极无敌恐怖复杂递归调用的组队策略
     */
    public boolean isLegal(String strategy,Long id,Long teamId){
        switch (strategy){
            case "TeamStrategy":
                return teamStrategyIsLegal(id,teamId);
            case "TeamAndStrategy":
                return teamAndStrategyIsLegal(id,teamId);
            case "TeamOrStrategy":
                return teamOrStrategyIsLegal(id,teamId);
            case "ConflictCourseStrategy":
                return conflictCourseStrategyIsLegal(id,teamId);
            case "MemberLimitStrategy":
                return memberLimitStrategy(id,teamId);
            case "CourseMemberLimitStrategy":
                return courseMemberLimitStrategyIsLegal(id,teamId);
                default:break;
        }
        return false;
    }

    /**
     * 处理team_strategy
     * @param id
     * @return
     */
    public boolean teamStrategyIsLegal(Long id,Long teamId){
        ArrayList<TeamStrategyEntity> strategyEntity=strategyMapper.getTeamStrategy(id);
        for(TeamStrategyEntity teamStrategyEntity:strategyEntity){
            if(!isLegal(teamStrategyEntity.getStrategyName(),teamStrategyEntity.getStrategyId(),teamId)){
                return false;
            }
        }
        return true;
    }

    /**
     * 处理team_and_strategy
     * @param id
     * @return
     */
    public boolean teamAndStrategyIsLegal(Long id,Long teamId){
        ArrayList<TeamAndStrategyEntity> teamAndStrategyEntity=strategyMapper.getTeamAndStrategy(id);
        for(TeamAndStrategyEntity teamAndStrategyEntity1:teamAndStrategyEntity){
            if(!isLegal(teamAndStrategyEntity1.getStrategyName(),teamAndStrategyEntity1.getStrategyId(),teamId)){
                return false;
            }
        }
        return true;
    }

    /**
     * 处理team_or_strategy
     * @param id
     * @return
     */
    public boolean teamOrStrategyIsLegal(Long id,Long teamId){
        ArrayList<TeamOrStrategyEntity> teamOrStrategyEntity=strategyMapper.getTeamOrStrategy(id);
        for(TeamOrStrategyEntity teamOrStrategyEntity1:teamOrStrategyEntity){
            if(isLegal(teamOrStrategyEntity1.getStrategy(),teamOrStrategyEntity1.getStrategyId(),teamId)){
                return true;
            }
        }
        return false;
    }

    /**
     * 处理conflict_course_strategy的数据
     * @param id
     * @param teamId
     * @return
     */
    public boolean conflictCourseStrategyIsLegal(Long id,Long teamId){
        Long courseId=0L;
        ArrayList<ConflictCourseStrategy> conflictCourseStrategies=strategyMapper.getConflictCourseStrategy(id);
        ArrayList<StudentEntity> studentEntities=studentMapper.getMembers(teamId);
        for(StudentEntity studentEntity:studentEntities){
            ArrayList<SimpleCourseEntity> simpleCourseEntities=courseMapper.findSimpleCourseEntityByStudenId(studentEntity.getId());
            for(SimpleCourseEntity simpleCourseEntity:simpleCourseEntities){
                for(ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategies){
                    if(conflictCourseStrategy.getCourseId().equals(simpleCourseEntity.getId())){
                        if(courseId==0L){
                            courseId=conflictCourseStrategy.getCourseId();
                        }else if(!courseId.equals(conflictCourseStrategy.getCourseId())){
                            //System.out.println("conflictCourseStrategy"+id+"出错");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 处理course_member_limit_strategy的数据
     * @param id
     * @param teamId
     * @return
     */
    public boolean courseMemberLimitStrategyIsLegal(Long id,Long teamId){
        CourseMemberLimitStrategyEntity courseMemberLimitStrategyEntities=strategyMapper.getCourseMemberLimitStrategy(id);
        Integer count=0;
        ArrayList<StudentEntity> studentEntities=studentMapper.getMembers(teamId);
        for(StudentEntity studentEntity:studentEntities){
            if(klassStudentMapper.getKlassIdByCourseAndStudent(courseMemberLimitStrategyEntities.getCourseId(),studentEntity.getId())!=null){
                count++;
            }
        }
        if(count>=courseMemberLimitStrategyEntities.getMinMember()&&count<=courseMemberLimitStrategyEntities.getMaxMember()){
            return true;
        }else{
            //System.out.println("courseMemberLimitStrategy"+id+"出问题"+"人数为"+count);
            return false;
        }
    }

    /**
     * 处理member_limit_strategy
     * @param id
     * @param teamId
     * @return
     */
    public boolean memberLimitStrategy(Long id,Long teamId){
        MemberLimitStrategy memberLimitStrategy=strategyMapper.getMemberLimitStrategy(id);
        ArrayList<StudentEntity> studentEntities=studentMapper.getMembers(teamId);
        if(studentEntities.size()>=memberLimitStrategy.getMinMember()&&studentEntities.size()<=memberLimitStrategy.getMaxMember()){
            return true;
        }else {
            return false;
        }
    }

    /**ljy
     * 根据teamId获取组内学生id
     * @param teamId
     * @return
     */
    public ArrayList<Long> getStudentsByTeamId(Long teamId){
        return teamMapper.getStudentsByTeamId(teamId);
    }
}
