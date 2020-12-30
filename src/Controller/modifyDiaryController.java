package Controller;


import Entity.Diary;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import Service.modifyDiaryManage;

@Controller
public class modifyDiaryController {

    // 更新日记信息
    @RequestMapping("/TaJiMain/modifyDiary.do")
    public void modifyDiary(HttpServletRequest request,
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
        PrintWriter out = response.getWriter();

        if (userId == null) {
            out.write("error");
        } else {
            Diary diary = null;
            if (modifyDiaryManage.ModifyDiary(diary)) {
                out.write("ok");
            } else {
                out.write("error");
            }
        }
    }

    // 删除日记
    // 突然想起来 数据库里diary的id属性被我设成自增的 就不需要userId了
    @RequestMapping("/TaJiMain/deleteDiary.do")
    public void deleteDiary(@RequestBody String strJSON, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        JSONObject json = JSONObject.fromObject(strJSON);

        long diaryId = Long.parseLong(json.get("diaryId").toString());
        if (modifyDiaryManage.DeleteDiary(diaryId)) out.write("ok");
        else out.write("error");

    }
}
