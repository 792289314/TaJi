package Service;

import Dao.classifyTable;
import Dao.diaryTable;
import Dao.imageTable;
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

    public static boolean AddDiary(Diary diary,long id){
        return new diaryTable().AddDiary(diary,id);
    }

    public static Long getDiaryId(){
        return new diaryTable().getMaxDiaryId();
    }

    public static long calcCntOfDiaryByClassifyId(long userId,long classifyId){
        return new diaryTable().calcCntOfDiaryByClassifyId(userId,classifyId);
    }

    // 添加文件(虽然 写的是文件 但前端 设置了 只能是图片类型的)
    public static boolean addImg(long diaryId,String imgDir){
        return new imageTable().AddImg(diaryId, imgDir);
    }

    // 通过 diaryId 查找 文件列表
    public static ArrayList<String> findImgById(long diaryId){
        return new imageTable().findImgById(diaryId);
    }


}