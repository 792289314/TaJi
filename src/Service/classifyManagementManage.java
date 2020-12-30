package Service;

import Dao.classifyTable;
import Entity.Classify;

import java.util.ArrayList;

// 哈哈哈哈哈哈 我也知道这个名字巨长
public class classifyManagementManage {
    public static boolean AddClassify(long id, Classify classify) {
        return new classifyTable().addClassify(id, classify);
    }
    public static ArrayList<Classify> getAllClassifiesExceptUnClassified(long id){
        return new classifyTable().getAllClassifiesExceptUnClassified(id);
    }
}
