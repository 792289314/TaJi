package Entity;

import java.sql.Timestamp;

// 又一个搞崩了的实体类
public class DiaryAndUserAndClassify {
    Diary diary;// 里面自带classify
    User user;

    //uname, dtime, dtext, dweather, cname, ccolor

    public DiaryAndUserAndClassify(String userName,
                                   String dtext, Timestamp dtime, int dweather,
                                   String cname, String ccolor) {
        user = new User(userName);
        diary = new Diary(dtime, dtext, dweather, cname, ccolor);
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
