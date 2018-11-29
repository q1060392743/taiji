package cn.com.taiji.taiji.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author SongChong created by9:29 2018/11/29 0029
 * 用户类
 */
@Component
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户id
    private String id;

    //用户姓名，不能为空
    @NotEmpty(message = "姓名不能为空")
    private String name;

    //用户年龄，大于等于18且小于等于100
    @Max(value = 100, message = "年龄太大！")
    @Min(value = 18, message = "您未成年")
    private int age;

    //用户手机号
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$", message = "手机号不符合格式")
    private String mobile;

    //用户邮箱
    @Email
    private String email;

    //用户头像存放路径
    private String fileUrl;

    public User() {
    }

    public User(String id, String name, int age, String mobile, String email, String fileUrl) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.email = email;
        this.fileUrl = fileUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mobile=" + mobile +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
