package Controller;

import Entity.Classify;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.classifyManagementManage;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class classifyManagementController {
    // 添加新的分类
    @RequestMapping("/TaJiMain/addClassify.do")
    public void addClassify(@RequestBody String strJSON,
                            //HttpServletRequest request,
                            HttpSession session,
                            HttpServletResponse response) throws IOException {
        Object userId = session.getAttribute("id");
        PrintWriter out = response.getWriter();
        if (userId == null) {
            // 存在会话过期的可能性 但是好像因为有filter过滤器的存在 好像 不写也没事啊
        } else {
            JSONObject json = JSONObject.fromObject(strJSON);
            // 根据json 直接生成实体
            // 导入的 Bean.jar 终于派上用场了 :)
            // json转换实体时  json中的键值名必须与实体的变量名一摸一样
            Classify classify = (Classify) JSONObject.toBean(json, Classify.class);
            long id = Long.parseLong(userId.toString());
            if (classifyManagementManage.AddClassify(id, classify)) {
                out.write("ok");
            }else{
                out.write("error");
            }
        }
    }



    @RequestMapping("/TaJiMain/modifyClassify.do")
    public void modifyClassify(@RequestBody String strJSON,
                            //HttpServletRequest request,
                            HttpSession session,
                            HttpServletResponse response) throws IOException {
        Object userId = session.getAttribute("id");
        PrintWriter out = response.getWriter();
        if (userId == null) {
            // 存在会话过期的可能性 但是好像因为有filter过滤器的存在 好像 不写也没事啊
        } else {
            JSONObject json = JSONObject.fromObject(strJSON);
            // 根据json 直接生成实体
            // 导入的 Bean.jar 终于派上用场了 :)
            Classify classify = (Classify) JSONObject.toBean(json, Classify.class);
            long id = Long.parseLong(userId.toString());
            if (classifyManagementManage.AddClassify(id, classify)) {
                out.write("ok");
            }else{
                out.write("error");
            }
        }
    }
}
