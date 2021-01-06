package Entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Diary {
    long id;
    Classify classify;

    boolean flag;
    int weather;

    String text;
    Timestamp time;

    public Diary(Classify classify, long id, boolean flag, int weather, String text, Timestamp time) {
        this.id = id;
        this.classify = classify;
        this.flag = flag;
        this.weather = weather;
        this.text = text;
        this.time = time;
    }

    public Diary(long cid, boolean _flag, String _text, Timestamp _time, int _weather) {
        this.classify = new Classify(cid);
        this.flag = _flag;
        this.weather = _weather;
        this.text = _text;
        this.time = _time;
    }

    public Diary(Timestamp _time, String _text, int _weather, String classifyName, String classifyColor) {
        time = _time;
        text = _text;
        weather = _weather;
        classify = new Classify(classifyName, classifyColor);

    }

    public Diary() {

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

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
