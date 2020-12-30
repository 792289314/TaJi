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
    public void modifyDiary(@RequestBody String strJSON,
                            HttpSession session,
                            HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Object userId = session.getAttribute("id");
        PrintWriter out = response.getWriter();
        if (userId == null) {
            out.write("error");
        } else {
            JSONObject json = JSONObject.fromObject(strJSON);
            String diaryText = json.get("diaryText").toString();
            long diaryId = Long.parseLong(json.get("diaryId").toString());
            Diary diary = new Diary();
            diary.setId(diaryId);
            diary.setText(diaryText);
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
