package hanalyst.application.hanalystclub.Model;

public class Attack {
    private String code, desc;
    private int value;

    public Attack(String code, String desc, int value) {
        this.code = code;
        this.desc = desc;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public int getValue() {
        return value;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
