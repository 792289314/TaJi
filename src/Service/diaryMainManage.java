package Service;

import Dao.classifyTable;
import Dao.diaryTable;
import Entity.Classify;
import Entity.Diary;
import Entity.DiaryAndClassify;

import java.util.ArrayList;

public class diaryMainManage {
    public static ArrayList<Classify> getClassifyById(long id) {
        return new classifyTable().getClassifyById(id);
    }

    public static ArrayList<DiaryAndClassify> getUserDiaryAndClassify(long id){
        return new diaryTable().getUserDiaryAndClassify(id);
    }

    public static ArrayList<Diary> getUserDiary(long id){
        return new diaryTable().getUserDiary(id);
    }
}