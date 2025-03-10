package com.ruoyi.project.system.domain.entity;

import lombok.Data;

@Data
public class MatchResultListVo {
    private String lineStatus;
    private String poolTotals;
    private String code;
    private String oddsGoalLine;
    private String odds;//赔率
    private String combinationDesc;//进球数 、让球 、半全场、比分、胜负
    private Long poolId;
    private String refundStatus;
    private String oddsType;
    private Long matchId;
    private String combination;
    private String goalLine;
}
