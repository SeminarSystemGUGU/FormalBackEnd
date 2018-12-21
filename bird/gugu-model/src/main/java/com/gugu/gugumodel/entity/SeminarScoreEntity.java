package com.gugu.gugumodel.entity;

/**
 * 讨论课成绩的实体类
 * @author ren
 */
public class SeminarScoreEntity {
    private Long klassSeminarId;
    private Long teamId;
    private float totalScore;
    private float presentationScore;
    private float questionScore;
    private float reportScore;

    public Long getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(Long klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public float getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(float presentationScore) {
        this.presentationScore = presentationScore;
    }

    public float getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(float questionScore) {
        this.questionScore = questionScore;
    }

    public float getReportScore() {
        return reportScore;
    }

    public void setReportScore(float reportScore) {
        this.reportScore = reportScore;
    }
}