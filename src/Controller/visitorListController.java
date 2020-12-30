package Controller;

import Entity.DiaryAndUserAndClassify;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import Service.visitorListManage;

@Controller
public class visitorListController {
    // 根据日期 获取在指定日期 所有用户 在当天发表的 公开的日记记录
    @RequestMapping("/TaJiMain/getAllDiaryByDate.do")
    public void getAllDiaryByDate(@RequestBody String strJSON, // json { date }
                                  HttpServletResponse response) throws IOException {
        response.setContentType("text/html;  charset=utf-8");
        JSONObject json=JSONObject.fromObject(strJSON);
        String dateTime= (String) json.get("date");
        PrintWriter out = response.getWriter();
        if (dateTime == null) {

        } else {
            ArrayList<DiaryAndUserAndClassify> list =
                    visitorListManage.getAllDiaryByDate(dateTime);

            JSONArray jsons = JSONArray.fromObject(list);
            System.out.println(jsons);

            out.write(String.valueOf(jsons));
        }
    }
}
