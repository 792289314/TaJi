package Controller;

import Entity.Classify;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class classifyManagementController {
    // 添加新的分类
    @RequestMapping("/TaJiMain/addClassify.do")
    public void addClassify(@RequestBody String strJSON,
                            //HttpServletRequest request,
                            HttpSession session,
                            HttpServletResponse response) {
        Object userId = session.getAttribute("id");
        if (userId == null) {
            // 存在会话过期的可能性 但是好像因为有filter过滤器的存在 好像 不写也没事啊
        } else {
            JSONObject json = JSONObject.fromObject(strJSON);
            // 根据json 直接生成实体
            // 导入的 Bean.jar 终于派上用场了 :)
            Classify classify = (Classify) JSONObject.toBean(json, Classify.class);

        }
    }
}
