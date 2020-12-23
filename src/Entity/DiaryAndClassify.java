package Entity;

import java.sql.Time;
import java.sql.Timestamp;

// 由于在 实体包含实体 的情况下 转换为json会报错
// 因此 额外增加这个类 (太菜了 不会解决。。。)
// ps: 这个问题解决了 是因为时间类的问题 Time不能用只能用TimeStamp 但是我懒得改了( ´ ▽ ` )ﾉ
public class DiaryAndClassify {
    private long classifyId;
    private String classifyName;
    private boolean classifyFlag;// 公开0 / 私密1
    private String classifyColor;

    private long diaryId;
    boolean diaryFlag; // 公开0 / 私密1
    int diaryWeather;
    String diaryText;
    Timestamp diaryTime;
    //String diaryTime;


    public DiaryAndClassify(long classifyId, String classifyName,
                            boolean classifyFlag, String classifyColor,
                            long diaryId, boolean diaryFlag, int diaryWeather,
                            String diaryText, Timestamp diaryTime) {
        this.classifyId = classifyId;
        this.classifyName = classifyName;
        this.classifyFlag = classifyFlag;
        this.classifyColor = classifyColor;
        this.diaryId = diaryId;
        this.diaryFlag = diaryFlag;
        this.diaryWeather = diaryWeather;
        this.diaryText = diaryText;

        this.diaryTime = diaryTime;
        //this.diaryTime = diaryTime.toString();
        //提取前10位 获得星期

    }



    public DiaryAndClassify(long classifyId, String classifyName,
                            boolean classifyFlag, String classifyColor,
                            long diaryId, boolean diaryFlag, int diaryWeather,
                            String diaryText, String diaryTime) {
        this.classifyId = classifyId;
        this.classifyName = classifyName;
        this.classifyFlag = classifyFlag;
        this.classifyColor = classifyColor;
        this.diaryId = diaryId;
        this.diaryFlag = diaryFlag;
        this.diaryWeather = diaryWeather;
        this.diaryText = diaryText;
        this.diaryTime = Timestamp.valueOf(diaryTime);
    }

    public long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(long classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public boolean isClassifyFlag() {
        return classifyFlag;
    }

    public void setClassifyFlag(boolean classifyFlag) {
        this.classifyFlag = classifyFlag;
    }

    public String getClassifyColor() {
        return classifyColor;
    }

    public void setClassifyColor(String classifyColor) {
        this.classifyColor = classifyColor;
    }

    public long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(long diaryId) {
        this.diaryId = diaryId;
    }

    public boolean isDiaryFlag() {
        return diaryFlag;
    }

    public void setDiaryFlag(boolean diaryFlag) {
        this.diaryFlag = diaryFlag;
    }

    public int getDiaryWeather() {
        return diaryWeather;
    }

    public void setDiaryWeather(int diaryWeather) {
        this.diaryWeather = diaryWeather;
    }

    public String getDiaryText() {
        return diaryText;
    }

    public void setDiaryText(String diaryText) {
        this.diaryText = diaryText;
    }

    /*public Timestamp getDiaryTime() {
        return Timestamp.valueOf(diaryTime);
    }

    public void setDiaryTime(Timestamp diaryTime) {
        this.diaryTime = diaryTime.toString();
    }*/

   /* public String getDiaryTime() {
        return diaryTime;
    }

    public void setDiaryTime(Timestamp diaryTime) {
        this.diaryTime = diaryTime.toString();
    }*/

    public Timestamp getDiaryTime() {
        return diaryTime;
    }

    public void setDiaryTime(Timestamp diaryTime) {
        this.diaryTime = diaryTime;
    }
}
