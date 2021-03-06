package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.guguuser.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

/**
 * @author ren
 */
@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;
    @Autowired
    KlassStudentDao klassStudentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    EmailUtil emailUtil;

    /**
     * 获取小组成员除了组长
     * @param teamId
     * @return
     */
    public ArrayList<StudentEntity> getMembers(Long teamId) {
        return studentDao.getMembersExceptLeader(teamId);
    }

    /**
     * 获取同班小组成员
     * @param teamId
     * @return
     */
    public ArrayList<Long> getKlassMember(Long teamId,Long courseId) {
        return studentDao.getKlassMember(teamId,courseId);
    }

    /**
     * 获取组长的数据
     * @param teamId
     * @return
     */
    public StudentEntity getLeader(Long teamId) {
        return studentDao.getLeader(teamId);
    }

    /**
     * 获取除了自己之外的未组队同学
     * @param courseId
     * @param studentId
     * @return
     */
    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId) {
        return studentDao.getStudentWithoutTeamInCourse(courseId,studentId);
    }

    /**
     * 获取自己在当前课程下的小组id
     * @param courseId
     * @param studentId
     * @return
     */
    public Long getTeamId(Long courseId,Long studentId){
        return klassStudentDao.getTeamId(studentId,courseId);
    }

    /**
     * 激活学生账号
     */
    public boolean activeStudent(StudentEntity studentEntity){
        return studentDao.activeStudent(studentEntity);
    }

    /**
     * 根据账号获取id
     */
    public Long getIdByAccount(String account){
        return studentDao.getStudentByAccount(account);
    }


    /**
     * 根据account搜索学生
     * @return
     */
    public ArrayList<StudentEntity> searchStudent(String account){
        return studentDao.searchStudent(account);
    }

    /**
     * 根据id获取学生所在小组
     * @return
     */
    public Long getStudentTeam(Long studentId,Long classId){
        return studentDao.getStudentTeam(studentId,classId);
    }

    /**
     * 根据id获取学生
     */
    public StudentEntity getStudentById(Long studentId){
        return studentDao.getStudentById(studentId);
    }

    /**
     * 查询该学生是否在该班级下
     */
    public boolean checkCourse(Long courseId,Long studentId){
        return studentDao.checkCourse(courseId,studentId);
    }
}
