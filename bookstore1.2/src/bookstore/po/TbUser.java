package bookstore.po;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class TbUser {
    private String uid;
    //校验名称
    @Size(min=3,max=10,message="{user.length.error}")
    private String username;
    @Size(min=3,max=10,message="{passwrod.length.error}")
    private String password;
    @Email(message="{email.error}")
    private String email;

    private String code;

    private Boolean state;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}