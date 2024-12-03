package com.ruoyi.web.controller.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.compress.utils.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

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
    public static List<String> historyList() {
        List<String> list = Lists.newArrayList();
//        Document doc = getDocument("https://m.zhuying.com/fc3d/jhfx");
        Document doc = getDocument("https://www.sporttery.cn/jc/zqsgkj/");
        //https://www.sporttery.cn/jc/zqsgkj/
        JSONObject object = JSONObject.parseObject(doc.body().text());
        System.out.println(object);
        return list;
    }

    public static void main(String[] args) throws SQLException {
        Document doc = getDocument("https://webapi.sporttery.cn/gateway/jc/football/getMatchResultV1.qry?matchPage=1&matchBeginDate=2024-11-25&matchEndDate=2024-11-27&leagueId=&pageSize=100&pageNo=1&isFix=0&pcOrWap=1");
        //https://www.sporttery.cn/jc/zqsgkj/
     JSONObject object = JSONObject.parseObject(doc.body().text());
     JSONObject object1 = JSONObject.parseObject(object.get("value").toString());

     List<Lottery> list=JSONObject.parseArray(object1.get("matchResult").toString(),Lottery.class);

        list.stream().forEach(lottery -> {
            System.out.println(lottery);
        });

//        String url = "jdbc:mysql://cd-cdb-mksu3lvw.sql.tencentcdb.com:27330/ruoyi?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
//        String user = "root";
//        String password = "He_ni3314";
//        Connection conn = null;
//        String sql = "INSERT INTO tb_football(`sectionsNo999`,`goalLine`,`leagueName`,`sectionsNo1`,`leagueBackColor`,`winFlag`,`a`,`d`,`h`,'allHomeTeam','allAwayTeam','matchDate','matchNumStr','awayTeamId','homeTeamId','leagueId','common') VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        //历史奖地址 https://m.zhuying.com/api/lotapi/selectListV2
//        try {
//            //加载jdbc驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            //连接mysql
//            try {
//                conn = DriverManager.getConnection(url, user, password);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            //将自动提交关闭
//            conn.setAutoCommit(false);
//            //编写sql
//            PreparedStatement pstm = conn.prepareStatement(sql);
//
//            Document doc = getDocument("https://webapi.sporttery.cn/gateway/jc/football/getMatchResultV1.qry?matchPage=1&matchBeginDate=2024-11-25&matchEndDate=2024-11-27&leagueId=&pageSize=100&pageNo=1&isFix=0&pcOrWap=1");
//            //https://www.sporttery.cn/jc/zqsgkj/
//            JSONObject object = JSONObject.parseObject(doc.body().text());
//            JSONObject object1 = JSONObject.parseObject(object.get("value").toString());
//            System.out.println(object1.get("matchResult"));
//
//
//
//
//
////            pstm.setLong(1,qisu );
////            pstm.setInt(2, kjbai);
////            pstm.setInt(3, kjshi);
////            pstm.setInt(4, kjge);
////            pstm.setString(5, kjbai+""+kjshi+kjge);
////            pstm.setInt(6, getMaxtoMin(kjbai,kjshi,kjge));
//
//
//
//
//            Arrays.stream(qishu.split(",")).sorted(Comparator.reverseOrder()).forEach(i->{
//
//                Document doc = getDocument("https://m.zhuying.com/api/lotapi/detail/fc3d/"+i);
//                JSONObject object = JSONObject.parseObject(doc.body().text());
//
//                JSONObject objectDD = JSONObject.parseObject(object.get("data").toString());
//                // bai shi ge
//                Long qisu=Long.valueOf(objectDD.get("issue").toString());
//                System.out.println(qisu);
//                String [] kaiji=objectDD.get("tryNumbers").toString().split(",");
//                String [] kaijing=objectDD.get("units").toString().split(",");
//
//                Integer kjbai =Integer.valueOf(kaiji[0]);
//                Integer kjshi =Integer.valueOf(kaiji[1]);
//                Integer kjge =Integer.valueOf(kaiji[2]);
//
//                Integer bai =Integer.valueOf(kaijing[0]);
//                Integer shi =Integer.valueOf(kaijing[1]);
//                Integer ge =Integer.valueOf(kaijing[2]);
//
//                try {
//                    Set<Integer> onwtwo=new HashSet<>();
//                    pstm.setLong(1,qisu );
//                    pstm.setInt(2, kjbai);
//                    pstm.setInt(3, kjshi);
//                    pstm.setInt(4, kjge);
//                    pstm.setString(5, kjbai+""+kjshi+kjge);
//                    pstm.setInt(6, getMaxtoMin(kjbai,kjshi,kjge));
//                    getonwtwo(kjbai,onwtwo);
//                    getonwtwo(kjshi,onwtwo);
//                    getonwtwo(kjge,onwtwo);
//                    pstm.setInt(7, getMaxtoMinSum(kjbai,kjshi,kjge));
//                    pstm.setString(8, StringUtils.join(onwtwo.toArray(), ","));
//                    pstm.setInt(9, 0);
//                    onwtwo.clear();
//                    pstm.addBatch();
//                    pstm.setLong(1, qisu);
//                    pstm.setInt(2, bai);
//                    pstm.setInt(3, shi);
//                    pstm.setInt(4, ge);
//                    pstm.setString(5, bai+""+shi+ge);
//                    pstm.setInt(6, getMaxtoMin(bai,shi,ge));
//                    pstm.setInt(7, getMaxtoMinSum(bai,shi,ge));
//                    getonwtwo(bai,onwtwo);
//                    getonwtwo(shi,onwtwo);
//                    getonwtwo(ge,onwtwo);
//                    pstm.setString(8, StringUtils.join(onwtwo.toArray(), ","));
//                    pstm.setInt(9, 1);
//                    onwtwo.clear();
//                    pstm.addBatch();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            });
//            //添加到同一个批处理
//            pstm.executeBatch();
//            conn.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }



































}