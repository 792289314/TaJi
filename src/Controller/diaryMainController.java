package Controller;

import Entity.Classify;
import Entity.Diary;
import Entity.DiaryAndClassify;
import Entity.User;
import Service.diaryMainManage;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class diaryMainController extends HttpServlet {


    @RequestMapping("/getClassify.do")
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
                System.out.println(json);
                jsonArray.add(json);
            }

            out.write(String.valueOf(jsonArray));
        }
    }

    @RequestMapping("/adddiary.do")
    /*  json中的数据
            data: {
                'classifyId': self.kindValue,
                //'userId': '',在 java后台session里
                'diaryFlay': '',// 分类flag | 谁可以看flag
                'diaryText': self.textarea1,
                'diaryTime': time,
                'diaryWeather': self.weatherValue
    }*/
    public void adddiary(@RequestBody String strJSON, HttpSession session,
                         HttpServletResponse response) throws IOException {
        response.setContentType("text/html;  charset=utf-8");

        Object userId = session.getAttribute("id");
        JSONObject json = JSONObject.fromObject(strJSON);
        String cid = (String) json.get("classifyId");
        String dflag = (String) json.get("diaryFlay");
        String dtext = (String) json.get("diaryText");
        String dtime = (String) json.get("diaryTime");
        PrintWriter out = response.getWriter();
        if (userId == null) {
            out.write("error");
        } else {
            long id = Integer.parseInt(userId.toString());

            Diary diary = null;// 这里还没写完
            if (diaryMainManage.AddDiary(diary, id)) {
                out.write("ok");
            } else {
                out.write("error");
            }
        }

    }
}
