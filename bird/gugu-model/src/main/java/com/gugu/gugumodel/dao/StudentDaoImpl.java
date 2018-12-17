package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.StudentMapper;
import com.gugu.gugumodel.mapper.TeacherMapper;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import com.gugu.gugumodel.pojo.vo.StudentBasicInforVO;

import java.util.List;


import java.util.ArrayList;

/**
 * @author ljy
 */
@Component
public class StudentDaoImpl implements StudentDao {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public void deleteStudentById(Long id){
        studentMapper.deleteStudentById(id);
    }

    @Override
    public ArrayList<StudentEntity> getMembersExceptLeader(Long teamId) {
        ArrayList<StudentEntity> members=studentMapper.getMembers(teamId);
        StudentEntity leader=studentMapper.getLeader(teamId);
        members.remove(leader);
        return members;
    }

    @Override
    public StudentEntity getLeader(Long teamId) {
        return studentMapper.getLeader(teamId);
    }

    @Override
    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId) {
        ArrayList<StudentEntity> studentEntities=studentMapper.getStudentWithoutTeam(courseId);
        StudentEntity studentEntity=studentMapper.getStudentById(studentId);
        studentEntities.remove(studentEntity);
        return studentEntities;
    }


    @Override
    public ArrayList<StudentEntity> searchStudent(String identity){
        return studentMapper.searchStudent(identity);
    }

    @Override
    public ArrayList<StudentEntity> getStudents(){
       return studentMapper.getStudents();
    }

    @Override
    public void resetStudentPassword(Long studentId){
        studentMapper.resetStudentPassword(studentId);
    }

    @Override
    public void changeStudentInformation(StudentEntity studentEntity){
        studentMapper.changeStudentInformation(studentEntity);
    }

    public StudentEntity getStudentById(Long studentId){
        return studentMapper.getStudentById(studentId);
    }

    public void changePassword(String password,Long studentId){
        studentMapper.changePassword(password,studentId);
    }
    public void changeEmail(String email,Long studentId){
        studentMapper.changeEmail(email,studentId);
    }
}
