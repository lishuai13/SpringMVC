package entity;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    @NotEmpty(message="姓名不能为空")
    private String name;
    @Size(min =8, max =16,message = "长度必须大于8为且不超过16位")
    private String password;
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$",message = "请输入有效的电话号码")
    private String telephone;
    @Email(message = "请输入有效的邮箱地址")
    private String email;

    public User() {
    }

    public User(String name, String password, String telephone, String email) {
        this.name = name;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password=" + password +
                ", telephone=" + telephone +
                ", email='" + email + '\'' +
                '}';
    }
}
