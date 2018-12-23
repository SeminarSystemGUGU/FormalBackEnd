package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.entity.CourseEntity;
import com.gugu.gugumodel.entity.ShareMessageEntity;
import com.gugu.gugumodel.entity.SimpleCourseEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.CourseMapper;
import com.gugu.gugumodel.mapper.KlassSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;


@Repository
public class CourseDao{
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;


    /**
     * 找出与用户相关的课程信息
     * @param userId
     * @param role
     * @return
     */
    public ArrayList<SimpleCourseEntity> findSimpleCourseEntityByUserId(Long userId,String role) {
        if(role.equals("ROLE_Teacher")){
            return courseMapper.findSimpleCourseEntityByTeacherId(userId);
        }else{
            return courseMapper.findSimpleCourseEntityByStudenId(userId);
        }
    }

    public Long newCourse(CourseEntity courseEntity){
        return courseMapper.newCourse(courseEntity);
    }

    public CourseEntity getCourseById(Long id) {
        return courseMapper.getCourseById(id);
    }

    public void deleteCourseById(Long id) throws NotFoundException {
        if(getCourseById(id)!=null) {
            courseMapper.deleteCourseById(id);
        }else{
            throw new NotFoundException("找不到该记录");
        }
    }


    public ArrayList<ShareMessageEntity> getSeminarShareMessage(Long courseId) {
        Long mainCourseId=courseMapper.getSeminarMainCourseId(courseId);
        SimpleCourseEntity mainCourse=courseMapper.getSimpleCourseById(mainCourseId);
        ArrayList<ShareMessageEntity> shareMessageEntities=new ArrayList<>();
        if(mainCourseId.equals(courseId)){
            ArrayList<ShareReceiveCourseEntity> shareRecieveCourseEntities=courseMapper.getSeminarRecieveCourses(courseId);
            for(int i=0;i<shareRecieveCourseEntities.size();i++){
                ShareMessageEntity shareMessageEntity=new ShareMessageEntity(mainCourse,shareRecieveCourseEntities.get(i),2);
                shareMessageEntities.add(shareMessageEntity);
            }
        }else{
            ShareReceiveCourseEntity shareReceiveCourseEntity =(ShareReceiveCourseEntity)courseMapper.getSimpleCourseById(courseId);
            shareReceiveCourseEntity.setShareId(courseMapper.getShareSeminarIdByCourse(mainCourseId,courseId));
            ShareMessageEntity shareMessageEntity=new ShareMessageEntity(courseMapper.getSimpleCourseById(mainCourseId), shareReceiveCourseEntity,2);
            shareMessageEntities.add(shareMessageEntity);
        }
        return shareMessageEntities;
    }

    public ArrayList<ShareMessageEntity> getTeamShareMessage(Long courseId) {
        Long mainCourseId=courseMapper.getTeamMainCourseId(courseId);
        SimpleCourseEntity mainCourse=courseMapper.getSimpleCourseById(mainCourseId);
        ArrayList<ShareMessageEntity> shareMessageEntities=new ArrayList<>();
        if(mainCourseId.equals(courseId)){
            ArrayList<ShareReceiveCourseEntity> shareRecieveCourseEntities=courseMapper.getTeamRecieveCourses(courseId);
            for(int i=0;i<shareRecieveCourseEntities.size();i++){
                ShareMessageEntity shareMessageEntity=new ShareMessageEntity(mainCourse,shareRecieveCourseEntities.get(i),1);
                shareMessageEntities.add(shareMessageEntity);
            }
        }else{
            ShareReceiveCourseEntity shareReceiveCourseEntity =(ShareReceiveCourseEntity)courseMapper.getSimpleCourseById(courseId);
            shareReceiveCourseEntity.setShareId(courseMapper.getShareTeamIdByCourse(mainCourseId,courseId));
            ShareMessageEntity shareMessageEntity=new ShareMessageEntity(courseMapper.getSimpleCourseById(mainCourseId), shareReceiveCourseEntity,1);
            shareMessageEntities.add(shareMessageEntity);
        }
        return shareMessageEntities;
    }

    /**
     * @author ljy
     * @param id
     * @return
     */
    public ArrayList<Long> getCourseIdByTeacherId(long id){
        return courseMapper.getCourseIdByTeacherId(id);
    }

    public boolean deleteSeminarShare(Long shareId){
        ShareApplicationEntity shareApplicationEntity=courseMapper.getSeminarShareApplicationById(shareId);
        if(shareApplicationEntity==null){
            return false;
        }
        if(courseMapper.getSeminarRecieveCourses(shareApplicationEntity.getMainCourseId()).size()==0) {
            courseMapper.deleteCourseSeminarMain(shareApplicationEntity.getMainCourseId());
        }
        courseMapper.deleteCourseSeminarMain(shareApplicationEntity.getSubCourseId());
        courseMapper.deleteSeminarShare(shareId);
        courseMapper.deleteAllSeminarByCourseId(shareApplicationEntity.getSubCourseId());
        return true;
    }

    public boolean deleteTeamShare(Long shareId){
        ShareApplicationEntity shareApplicationEntity=courseMapper.getTeamShareApplicationById(shareId);
        if(shareApplicationEntity==null){
            return false;
        }
        if(courseMapper.getTeamRecieveCourses(shareApplicationEntity.getMainCourseId()).size()==0) {
            courseMapper.deleteCourseTeamMain(shareApplicationEntity.getMainCourseId());
        }
        courseMapper.deleteCourseTeamMain(shareApplicationEntity.getSubCourseId());
        courseMapper.deleteTeamShare(shareId);
        courseMapper.deleteAllTeamByCourseId(shareApplicationEntity.getSubCourseId());
        return true;
    }

    public Long getTeacherIdByCourse(Long courseId){
        return courseMapper.getTeacherIdByCourse(courseId);
    }

    public void deleteAllSeminarByCourseId(Long courseId){
        klassSeminarMapper.deleteAllKlassSeminarByCourseId(courseId);
        courseMapper.deleteAllSeminarByCourseId(courseId);
    }

    /**
     * @author TYJ
     * 修改课程共享讨论课的状态
     * @param subCourseId
     * @param mainCourseId
     * @return
     */
    public int changeSeminarShareStatus(Long subCourseId,Long mainCourseId){
        return courseMapper.changeSeminarShareStatus(subCourseId,mainCourseId);
    }

    /**
     * @author TYJ
     * 修改课程共享分组的状态
     * @param subCourseId
     * @param mainCourseId
     * @return
     */
    public int changeTeamShareStatus(Long subCourseId,Long mainCourseId){
        return courseMapper.changeTeamShareStatus(subCourseId,mainCourseId);
    }

}
