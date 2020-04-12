package hanalyst.application.hanalystclub.Model;

public class Defense {
    private String code, desc;
    private int value;

    public Defense(String code, String desc, int value) {
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
}
