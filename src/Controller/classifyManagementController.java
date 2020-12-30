package Controller;

import Entity.Classify;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.classifyManagementManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class classifyManagementController {
    // 挑选除了 全部、未分类 以外的 所有用户个人分类信息 （不过数据库里本来就没有全部这个分类...）
    @RequestMapping("/TaJiMain/getAllClassifiesExceptUnClassified.do")
    public void getAllClassifiesExceptUnClassified(HttpSession session,
                                                   HttpServletResponse response) throws IOException {
        response.setContentType("text/html;  charset=utf-8");
        Object userId = session.getAttribute("id");
        PrintWriter out = response.getWriter();
        if (userId != null) {
            long id = Long.parseLong(userId.toString());
            ArrayList<Classify> list = classifyManagementManage.getAllClassifiesExceptUnClassified(id);
            JSONArray jsons = JSONArray.fromObject(list);
            System.out.println(jsons);
            out.write(String.valueOf(jsons));
        }


    }


    // 添加新的分类
    @RequestMapping("/TaJiMain/addClassify.do")
    public void addClassify(@RequestBody String strJSON,
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
            // 使用toBean生成实体时 需要确保
            //      1. 实体的所有成员变量均是小写 首字母大写 会导致生成的实体的成员变量null
            //      2. 需要注意 所有变量的 set/get 函数均已生成 如果变量为boolean 需要将isXXX 改为 getXXX
            Classify classify = (Classify) JSONObject.toBean(json, Classify.class);
            long id = Long.parseLong(userId.toString());
            if (classifyManagementManage.AddClassify(id, classify)) {
                out.write("ok");
            } else {
                out.write("error");
            }
        }
    }


    // 修改分类信息
    @RequestMapping("/TaJiMain/modifyClassify.do")
    public void modifyClassify(@RequestBody String strJSON,
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
            } else {
                out.write("error");
            }
        }
    }
}
