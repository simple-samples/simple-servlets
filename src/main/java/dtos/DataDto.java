package dtos;

public class DataDto {
    private String string;
    private Integer integer;
    private boolean bool;

    public DataDto() {

    }

    public DataDto(String s, Integer i, boolean b) {
        this.string = s;
        this.integer = i;
        this.bool = b;
    }

    public String getString() {
        return string;
    }

    public Integer getInteger() {
        return integer;
    }

    public boolean isBool() {
        return bool;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

//    public String toJSON() {
//        return "{\"Data\": {\"string\":\"" + string + "\", \"integer\":" + integer + ", \"bool\": " + bool + "} }";
//    }
}
