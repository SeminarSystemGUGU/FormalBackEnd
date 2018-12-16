package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.KlassStudentDao;
import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ren
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;
    @Autowired
    KlassStudentDao klassStudentDao;

    @Override
    public ArrayList<StudentEntity> getMembers(Long courseId, Long studentId) {
        Long teamId=klassStudentDao.getTeamId(studentId,courseId);
        return studentDao.getMembersExceptLeader(teamId);
    }

    @Override
    public StudentEntity getLeader(Long courseId, Long studentId) {
        Long teamId=klassStudentDao.getTeamId(studentId,courseId);
        return studentDao.getLeader(teamId);
    }

    @Override
    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId) {
        return studentDao.getStudentWithoutTeamInCourse(courseId,studentId);
    }
}