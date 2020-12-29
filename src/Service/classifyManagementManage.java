package Service;

import Dao.classifyTable;
import Entity.Classify;

// 哈哈哈哈哈哈 我也知道这个名字巨长
public class classifyManagementManage {
    public static boolean AddClassify(long id, Classify classify) {
        return new classifyTable().addClassify(id, classify);
    }
}
