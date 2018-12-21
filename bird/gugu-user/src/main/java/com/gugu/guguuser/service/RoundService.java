package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.RoundDao;
import com.gugu.gugumodel.entity.RoundEntity;
import com.gugu.gugumodel.entity.RoundScoreEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ren
 */
@Service
public class RoundService {
    @Autowired
    RoundDao roundDao;

    /**
     * 根据roundId获取详细信息
     * @param roundId
     * @return
     */
    public RoundEntity getMessageById(Long roundId){
        return roundDao.getMessageById(roundId);
    }

    /**
     * 根据roundid修改round信息
     */
    public void editRoundMessage(RoundEntity roundEntity) throws NotFoundException {
        roundDao.editRoundMessage(roundEntity);
    }
    /**
     * 新建round记录
     */
    public Long newRound(RoundEntity roundEntity){
        return roundDao.newRound(roundEntity);
    }
    /**
     * 获取一个轮次下所有小组的成绩
     */
    public ArrayList<RoundScoreEntity> getAllTeamScore(Long roundId){
        return roundDao.getAllTeamScore(roundId);
    }

    /**
     * 根据roundid和teamid获取成绩
     */
    public RoundScoreEntity getScoreByRoundAndTeam(Long roundId,Long teamId){
        return roundDao.getByRoundAndTeam(roundId,teamId);
    }

    /**
     * 修改round数据
     * @param roundScoreEntity
     * @throws NotFoundException
     */
    public void editRoundScore(RoundScoreEntity roundScoreEntity) throws NotFoundException {
        roundScoreEntity.setTotalScore((roundScoreEntity.getPresentationScore()+roundScoreEntity.getQuestionScore()+roundScoreEntity.getReportScore())/3);
        roundDao.editRoundScore(roundScoreEntity);
    }

    /**
     * 获取一个课程下所有的round
     */
    public ArrayList<RoundEntity> getRoundMessageByCourseId(Long courseId){
        return roundDao.getRoundMessageByCourseId(courseId);
    }
}