package Controller;

import Entity.Classify;
import Entity.Diary;
import Entity.DiaryAndClassify;
import Service.diaryMainManage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;


@Controller
public class diaryMainController extends HttpServlet {

    // 获取用户分类
    // Filter监管的文件夹TaJiMain的html里调用的Servlet
    @RequestMapping("/TaJiMain/getClassify.do")
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
            Classify allDiary = new Classify(0, "全部", false, "#7DCC67");
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

    // 获取用户全部的日记记录
    @RequestMapping("/TaJiMain/getUserDiary.do")
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


    // json中的数据 data: {'classifyId', 'diaryFlag', 'diaryText', 'diaryTime', 'diaryWeather'}
    // 咳咳 方法是有点麻烦 先提取json然后才分割转换 主要是类没有设计好... 没办法直接变成实体
    @RequestMapping("/TaJiMain/addDiary.do")
    public void AddDiary(HttpServletRequest request,
                         HttpSession session,
                         HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        BufferedReader rd = request.getReader();
        String strJSON = "", tmp;
        while ((tmp = rd.readLine()) != null) {
            strJSON += tmp;
        }
        System.out.println(strJSON);

        Object userId = session.getAttribute("id");
        JSONObject json = JSONObject.fromObject(strJSON);
        long cid = Long.parseLong(json.get("classifyId").toString());
        // 得到的 json.get("classifyId") 是个 Integer（Object）类型 需要强制转换 才能够变成long
        boolean dflag = Boolean.parseBoolean(json.get("diaryFlag").toString());// java不支持 0变false js倒是支持
        String dtext = (String) json.get("diaryText");
        long tim = Long.parseLong(json.get("diaryTime").toString());
        Timestamp dtime = new Timestamp(tim);
        // 得到的 json.get("diaryTime") 是 Long 类型的时间（所以要先转换成long）
        // 也不能直接转换成tiemstamp（所以根据时间long新建实体）
        int dweather = Integer.parseInt(json.get("diaryWeather").toString());
        PrintWriter out = response.getWriter();
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
    }

    @RequestMapping("/TaJiMain/Exit.do")
    public void exit(HttpSession session) {
        session.invalidate();
    }

    // 剩下的Servlet在 modifyDiaryController.java 里
    // 两个页面合并了： diaryMain.html 与 modifyDiary.html 合并到 diaryMain.html 里了

}
