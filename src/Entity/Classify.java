package Entity;

public class Classify {
    private long Id;
    private String Name;
    private boolean flag;// 公开 / 私密
    private String Color;


    public Classify(long id, String name, boolean flag, String color) {
        Id = id;
        Name = name;
        this.flag = flag;
        Color = color;
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
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
