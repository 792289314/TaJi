package Entity;

public class Classify {
    private long Id;
    private String Name;
    private boolean Flag;// 公开 / 私密
    private String Color;
    private long Cnt;


    public Classify(long id, String name, boolean flag, String color) {
        Id = id;
        Name = name;
        this.Flag = flag;
        Color = color;
    }

    public Classify(long id, String name, boolean flag, String color, long cnt) {
        Id = id;
        Name = name;
        this.Flag = flag;
        Color = color;
        this.Cnt = cnt;
    }

    public Classify(long cid) {
        this.Id = cid;
    }

    public Classify(String classifyName, String classifyColor) {
        Name = classifyName;
        Color = classifyColor;
    }

    public long getCnt() {
        return Cnt;
    }

    public void setCnt(long cnt) {
        this.Cnt = cnt;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isFlag() {
        return Flag;
    }

    public void setFlag(boolean flag) {
        this.Flag = flag;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
