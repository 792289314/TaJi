package Controller;

import Entity.Classify;
import Entity.DiaryAndClassify;
import Service.diaryMainManage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class diaryMainController extends HttpServlet {


    @RequestMapping("/getClassify.do")
    // 获取用户分类
    public void getClassify(HttpSession session,
                            HttpServletResponse response) throws IOException {
        response.setContentType("text/html;  charset=utf-8");
        Object userId = session.getAttribute("id");

        PrintWriter out = response.getWriter();
        if (userId == null) {
            out.write("error");
        } else {
            long id = Integer.parseInt(userId.toString());

            ArrayList<Classify> classifies = diaryMainManage.getClassifyById(id);
            Classify allDiary = new Classify(0, "全部", false, "7DCC67");
            classifies.add(0, allDiary);//在开头加上 '全部' 分类
            // 统计每个分类下日记的的个数
            long allCnt = 0;
            for (int i = 1; i < classifies.size(); i++) {
                long cnt = diaryMainManage.calcCntOfDiaryByClassifyId(id,
                        classifies.get(i).getId());
                classifies.get(i).setCnt(cnt);
                allCnt += cnt;// 把每个小分类的个数加到 全部 中来
            }
            classifies.get(0).setCnt(allCnt);

            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < classifies.size(); i++) {
                JSONObject json = JSONObject.fromObject(classifies.get(i));
                jsonArray.add(json);
                System.out.println(json);
            }
            out.write(String.valueOf(jsonArray));
        }

    }

    @RequestMapping("/getUserDiary.do")
    public void getUserDiary(HttpSession session,
                             HttpServletResponse response) throws IOException {
        response.setContentType("text/html;  charset=utf-8");
        PrintWriter out = response.getWriter();
        Object id = session.getAttribute("id");
        if (id == null) {
            // 用户非法进入 可恶的偷渡者
            out.write("error");
        } else {
            long userId = Long.parseLong(id.toString());
            JSONArray jsonArray = new JSONArray();
            ArrayList<DiaryAndClassify> diaries = diaryMainManage.getUserDiaryAndClassify(userId);
            //ArrayList<Diary> diaries = diaryMainManage.getUserDiary(userId);
            // json识别不了java的Time类型 给我整吐了 改成TimeStamp就可以了
            for (int i = 0; i < diaries.size(); i++) {
                JSONObject json = JSONObject.fromObject(diaries.get(i));
                //System.out.println(json);
                jsonArray.add(json);
            }

            out.write(String.valueOf(jsonArray));
        }
    }


    /*  json中的数据
            data: {
                'classifyId': self.kindValue,
                //'userId': '',在 java后台session里
                'diaryFlag': '',// 分类flag | 谁可以看flag
                'diaryText': self.textarea1,
                'diaryTime': time,
                'diaryWeather': self.weatherValue
    }*/
    @RequestMapping("/addDiary.do")
    public void AddDiary(@RequestBody String strJSON,
                         HttpSession session,
                         HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=utf-8");

        //JSONObject json = JSONObject.fromObject(strJSON);
        System.out.println(strJSON);
/*
        Object userId = session.getAttribute("id");

        //System.out.println("id:" + userId.toString());
        JSONObject json = JSONObject.fromObject(strJSON);

        long cid = Long.parseLong(json.get("classifyId").toString());
        // 得到的 json.get("classifyId") 是个 Integer（Object）类型 需要强制转换 才能够变成long
        //System.out.println(cid);
        boolean dflag = Boolean.parseBoolean(json.get("diaryFlag").toString());// java不支持 0变false js倒是支持
        //System.out.println(dflag);
        String dtext = (String) json.get("diaryText");
        //System.out.println(dtext);
        long tim = Long.parseLong(json.get("diaryTime").toString());
        Timestamp dtime = new Timestamp(tim);
        // 得到的 json.get("diaryTime") 是 Long 类型的时间（所以要先转换成long）
        // 也不能直接转换成tiemstamp（所以根据时间long新建实体）
        //System.out.println(dtime);
        int dweather = Integer.parseInt(json.get("diaryWeather").toString());
        //System.out.println(dweather);
        PrintWriter out = response.getWriter();
        //out.write("error");
        if (userId == null) {
            out.write("error");
        } else {
            long id = Long.parseLong(userId.toString());

            Diary diary = new Diary(cid, dflag, dtext, dtime, dweather);
            if (diaryMainManage.AddDiary(diary, id)) {
                out.write("ok");
            } else {
                out.write("error");
            }
        }
        */


    }
}
