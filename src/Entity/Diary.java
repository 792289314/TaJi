package Entity;

import org.w3c.dom.Text;

import java.sql.Time;

public class Diary {
    //did,classify.cid 'cid', cname, cflag, ccolor, dflag,dweather,dtext,dtime
    long id;
    Classify classify;



    boolean flag;
    int weather;
    //Text text;
    String text;
    Time time;

    public Diary(Classify classify,long id,  boolean flag, int weather, String text, Time time) {
        this.id = id;
        this.classify = classify;
        this.flag = flag;
        this.weather = weather;
        this.text = text;
        this.time = time;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
