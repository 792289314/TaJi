package Service;

import Dao.diaryTable;
import Entity.Diary;

public class modifyDiaryManage {
    public static boolean ModifyDiary(Diary diary) {
        if (diary == null) return false;
        return new diaryTable().modifyDiary(diary);
    }

    public static boolean DeleteDiary(long id){
        return new diaryTable().deleteDiary(id);
    }
}
