package com.ruoyi.project.system.domain.entity;

import lombok.Data;

import java.util.List;
@Data
public class OddsHistoryVo {
    private String awayTeamAllName;
    private String awayTeamAbbName;
    private String homeTeamAllName;
    private List<CrsListVo> crsList;
    private String homeTeamAbbName;
    private List<HhadListVo> hhadList;
    private List<TtgListVo> ttgList;
    private String leagueAbbName;
    private String leagueId;
    private List<SingleListVo> singleList;
    private String leagueAllName;
    private List<HadListVo> hadList;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private Long matchId;
    private List<HafuListVo> hafuList;

}
