package com.ruoyi.web.controller.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lottery {

    @JsonProperty("sectionsNo999")
    private String sectionsNo999;//比分

    @JsonProperty("goalLine")
    private Integer goalLine;//让球数

    @JsonProperty("leagueName")
    private String leagueName;//联赛名称

    @JsonProperty("sectionsNo1")
    private String sectionsNo1;//半场比分

    @JsonProperty("leagueBackColor")
    private String leagueBackColor;//获胜颜色

    @JsonProperty("winFlag")
    private String winFlag;//胜利标识

    @JsonProperty("a")
    private String a;//客队赔率标识

    @JsonProperty("d")
    private String d;//平局赔率

    @JsonProperty("h")
    private String h;//主队赔率标识

    @JsonProperty("allHomeTeam")
    private String allHomeTeam;//主队名称

    @JsonProperty("allAwayTeam")
    private String allAwayTeam;//客队名称

    @JsonProperty("matchDate")
    private String matchDate;//比赛日期

    @JsonProperty("matchNumStr")
    private String matchNumStr;//比赛序号


    @JsonProperty("awayTeamId")
    private Integer awayTeamId;//客队id

    @JsonProperty("homeTeamId")
    private Integer homeTeamId;//主队id

    @JsonProperty("leagueId")
    private Integer leagueId;//联赛id


    private Integer bettingSingle;//
    private String poolStatus;//
    private String resultStatus;//

    public Integer getBettingSingle() {
        return bettingSingle;
    }

    public void setBettingSingle(Integer bettingSingle) {
        this.bettingSingle = bettingSingle;
    }

    public String getPoolStatus() {
        return poolStatus;
    }

    public void setPoolStatus(String poolStatus) {
        this.poolStatus = poolStatus;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getSectionsNo999() {
        return sectionsNo999;
    }

    public void setSectionsNo999(String sectionsNo999) {
        this.sectionsNo999 = sectionsNo999;
    }

    public Integer getGoalLine() {
        return goalLine;
    }

    public void setGoalLine(Integer goalLine) {
        this.goalLine = goalLine;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getSectionsNo1() {
        return sectionsNo1;
    }

    public void setSectionsNo1(String sectionsNo1) {
        this.sectionsNo1 = sectionsNo1;
    }

    public String getLeagueBackColor() {
        return leagueBackColor;
    }

    public void setLeagueBackColor(String leagueBackColor) {
        this.leagueBackColor = leagueBackColor;
    }

    public String getWinFlag() {
        return winFlag;
    }

    public void setWinFlag(String winFlag) {
        this.winFlag = winFlag;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getAllHomeTeam() {
        return allHomeTeam;
    }

    public void setAllHomeTeam(String allHomeTeam) {
        this.allHomeTeam = allHomeTeam;
    }

    public String getAllAwayTeam() {
        return allAwayTeam;
    }

    public void setAllAwayTeam(String allAwayTeam) {
        this.allAwayTeam = allAwayTeam;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchNumStr() {
        return matchNumStr;
    }

    public void setMatchNumStr(String matchNumStr) {
        this.matchNumStr = matchNumStr;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }
}