package Entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Diary {
    //did,classify.cid 'cid', cname, cflag, ccolor, dflag,dweather,dtext,dtime
    long id;
    Classify classify;

    boolean flag;
    int weather;
    //Text text;
    String text;
    Timestamp time;

    public Diary(Classify classify,long id,  boolean flag, int weather, String text, Timestamp time) {
        this.id = id;
        this.classify = classify;
        this.flag = flag;
        this.weather = weather;
        this.text = text;
        this.time = time;
    }

    public Diary(long cid, boolean dflag, String dtext, Timestamp dtime, int dweather) {
        this.classify.setId(cid);
        this.flag = dflag;
        this.weather = dweather;
        this.text = dtext;
        this.time = dtime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Classify getClassify() {
        return classify;
    }

    public void setClassify(Classify classify) {
        this.classify = classify;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }
    /*public Time getTime(){
        return time.toLocalDateTime();
    }*/

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
