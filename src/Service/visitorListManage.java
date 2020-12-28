package Service;

import Dao.diaryTable;
import Entity.DiaryAndUserAndClassify;

import java.sql.Timestamp;
import java.util.ArrayList;

public class visitorListManage {
    public static ArrayList<DiaryAndUserAndClassify> getAllDiaryByDate(String time){
        return new diaryTable().getAllDiaryByDate(time);
    }
}
