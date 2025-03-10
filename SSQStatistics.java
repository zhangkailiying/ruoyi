package com.ruoyi.project.system.domain.entity;

import com.alibaba.fastjson.JSONObject;

import com.ruoyi.project.system.entityVo.Lottery;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SSQStatistics {
    /**
     * @param url 访问路径
     * @return
     */
    public static Document getDocument(String url) {
        try {
            //5000是设置连接超时时间，单位ms
            return Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").ignoreContentType(true).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document getDocument(int page) {
        try {
            String url = "http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=3d&issueCount=&issueStart=&issueEnd=&dayStart=&dayEnd=&pageNo=" + page + "&pageSize=100&week=&systemType=PC";
            //5000是设置连接超时时间，单位ms
            return Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //
    public static List<Lottery> getLotterys(String startDate, String endDate) {
        String URL="https://webapi.sporttery.cn/gateway/jc/football/getMatchResultV1.qry?matchPage=1";
        URL+=("&matchBeginDate="+startDate);
        URL+=("&matchEndDate="+endDate);
        URL+=("&leagueId=&pageSize=500&pageNo=1&isFix=0&pcOrWap=1");
        Document doc = getDocument(URL);
        JSONObject object = JSONObject.parseObject(doc.body().text());
        JSONObject object1 = JSONObject.parseObject(object.get("value").toString());
        List<Lottery> list=JSONObject.parseArray(object1.get("matchResult").toString(),Lottery.class);
        return list;
    }

    public static void saveBatchLotterys( List<Lottery> list) {

        String url = "jdbc:mysql://cd-cdb-mksu3lvw.sql.tencentcdb.com:27330/ruoyi?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String user = "root";
        String password = "Zk5213344";
        Connection conn = null;
        String sql = "INSERT INTO tb_football(sectionsNo999,goalLine,leagueName,sectionsNo1,leagueBackColor,winFlag,a,d,h,allHomeTeam,allAwayTeam,matchDate,matchNumStr,awayTeamId,homeTeamId,leagueId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //历史奖地址 https://m.zhuying.com/api/lotapi/selectListV2
        try {
            //加载jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接mysql
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //将自动提交关闭
            conn.setAutoCommit(false);
            //编写sql
            PreparedStatement pstm = conn.prepareStatement(sql);

            list.stream().forEach(lottery -> {
                try {
                    pstm.setString(1,lottery.getSectionsNo999());
                    pstm.setString(2,lottery.getGoalLine().toString());
                    pstm.setString(3, lottery.getLeagueName());
                    pstm.setString(4, lottery.getSectionsNo1());
                    pstm.setString(5, lottery.getLeagueBackColor());
                    pstm.setString(6, lottery.getWinFlag());
                    pstm.setString(7, lottery.getA());
                    pstm.setString(8, lottery.getD());
                    pstm.setString(9, lottery.getH());
                    pstm.setString(10, lottery.getAllHomeTeam());
                    pstm.setString(11, lottery.getAllAwayTeam());
                    pstm.setString(12, lottery.getMatchDate());
                    pstm.setString(13, lottery.getMatchNumStr());
                    pstm.setInt(14, lottery.getAwayTeamId());
                    pstm.setInt(15, lottery.getHomeTeamId());
                    pstm.setInt(16, lottery.getLeagueId());
                    pstm.addBatch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            //添加到同一个批处理
            pstm.executeBatch();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public static void main(String[] args) throws SQLException {

       // String URL="https://webapi.sporttery.cn/jc/zqdz/index.html?showType=3&mid=2030237";
       Document doc = getDocument("https://webapi.sporttery.cn/gateway/jc/football/getFixedBonusV1.qry?clientCode=3001&matchId=2030237");
        //Document doc = getDocument("https://webapi.sporttery.cn/gateway/jc/football/getMatchResultV1.qry?matchPage=1&matchBeginDate=2025-03-05&matchEndDate=2025-03-07&leagueId=&pageSize=100&pageNo=1&isFix=0&pcOrWap=1");
        //https://www.sporttery.cn/jc/zqsgkj/
      //  Document doc = getDocument(URL);
        JSONObject object = JSONObject.parseObject(doc.body().text());
        JSONObject object1 = JSONObject.parseObject(object.get("value").toString());
//
        System.out.println(object1);
       // Document doc = getDocument("https://webapi.sporttery.cn/gateway/jc/football/getMatchResultV1.qry?matchPage=1&matchBeginDate=2024-11-25&matchEndDate=2024-11-27&leagueId=&pageSize=100&pageNo=1&isFix=0&pcOrWap=1");

    }

}