package Controller;

import Entity.User;
import Service.accountManage;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
// 一个界面一个Servlet
public class AccountController {
    @RequestMapping("/onLoginSubmit.do")
    // strJSON中的参数 { userEmail , passWord }
    public void onLoginSubmit(@RequestBody String strJSON,
                              HttpSession session,
                              HttpServletResponse response) throws IOException {
        System.out.println("当前登陆的用户：" + strJSON);

        response.setContentType("text/html;  charset=utf-8");
        JSONObject userObj = JSONObject.fromObject(strJSON);
        String userEmail = userObj.get("userEmail").toString();
        String userPassword = userObj.get("passWord").toString();
        User user = accountManage.login(userEmail, userPassword);
        PrintWriter out = response.getWriter();// try throws
        if (user == null) {// 用户不存在
            out.write("error");
        } else {
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            session.setAttribute("email", user.getEmail());
            out.write(user.getName());
        }
    }

    @RequestMapping("/onRegisterSubmit.do")
    // strJSON中的参数 {'userEmail', 'userName', 'passWord'}
    public void onRegisterSubmit(@RequestBody String strJSON,
                                 HttpSession session,
                                 HttpServletResponse response) throws IOException {
        response.setContentType("text/html;  charset=utf-8");

        System.out.println(strJSON);
        JSONObject userObj = JSONObject.fromObject(strJSON);
        String email = userObj.get("userEmail").toString();
        PrintWriter out = response.getWriter();
        // 查询邮箱是否存在
        if (accountManage.isExistenceByEmail(email)) {
            out.write("error");// 邮箱已存在
        } else {
            String name = userObj.get("userName").toString();
            String password = userObj.get("passWord").toString();
            User user = accountManage.register(name, email, password);
            if (user == null) {
                out.write("error");// 添加用户失败
            } else {
                // 给 新添加的用户 创建 第一个分类 未分类
                accountManage.createFirstClassify(user.getId());

                session.setAttribute("id", user.getId());
                session.setAttribute("name", user.getName());
                session.setAttribute("email", user.getEmail());
                out.write(user.getName());
            }
        }
    }

    // 查询用户邮箱是否存在
    @RequestMapping("/forgetEmail.do")
    public static void forgetEmail(@RequestBody String strSON,
                                   HttpServletResponse response) throws IOException {
        System.out.println(strSON);
        JSONObject json = JSONObject.fromObject(strSON);
        System.out.println(json);
        String email = json.getString("email");
        PrintWriter out = response.getWriter();
        if (accountManage.isExistenceByEmail(email)) {
            out.write("ok");
        } else {
            out.write("error");
        }
    }

    // 修改密码
    @RequestMapping("/forgetPassword.do")
    public static void forgetPassword(@RequestBody String strSON,
                                      HttpServletResponse response) throws IOException {
        JSONObject json = JSONObject.fromObject(strSON);
        String email = json.getString("email");
        String password = json.getString("password");
        PrintWriter out = response.getWriter();
        if (accountManage.modifyPassword(email, password)) {
            out.write("ok");
        } else {
            out.write("error");
        }
    }

}
