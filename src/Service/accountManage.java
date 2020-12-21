package Service;

import Dao.userTable;
import Entity.User;

// 登陆界面 相关的 操作
public class accountManage {


    public static User login(String Email, String Password) {
        return new userTable().getUserByEmailAndPassword(Email, Password);
    }

    //  通过邮箱 查看是否有用户存在
    // 如果存在 true
    // 否则 false
    public static boolean isExistenceByEmail(String Email) {
        return new userTable().isExistenceByEmail(Email);
    }

    public static User register(String Name, String Email, String Password) {
        User user = new userTable().register(Name, Email, Password);
        if (user == null) return null;
        // 为新的用户创建 '全部' '未分类' 两个标签;
        return user;
    }
}
