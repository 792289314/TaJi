package Entity;

public class Classify {
    private long id;
    private String name;
    private boolean flag;// 公开 / 私密
    private String color;
    private long cnt;

    public Classify(){}

    public Classify(long _id, String _name, boolean _flag, String _color) {
        id = _id;
        name = _name;
        flag = _flag;
        color = _color;
    }

    public Classify(long _id, String _name, boolean _flag, String _color, long _cnt) {
        id = _id;
        name = _name;
        flag = _flag;
        color = _color;
        cnt = _cnt;
    }

    public Classify(long cid) {
        id = cid;
    }

    public Classify(String classifyName, String classifyColor) {
        name = classifyName;
        color = classifyColor;
    }

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long _cnt) {
        this.cnt = _cnt;
    }

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean _flag) {
        this.flag = _flag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String _color) {
        color = _color;
    }
}
