package api;

public class SucsessReg {
    private Integer id;
    private String token;
public SucsessReg(){}
    public SucsessReg(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
