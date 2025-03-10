package com.ruoyi.project.system.domain.entity;

import lombok.Data;

import java.util.List;
@Data
public class MatchDataVo {
    private int isCancel;
    private OddsHistoryVo oddsHistory;
    private List<MatchResultListVo> matchResultList;
    private String sectionsNo999;
}