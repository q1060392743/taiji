package cn.com.taiji.taiji.controller;

import cn.com.taiji.taiji.bean.User;
import cn.com.taiji.taiji.utils.Result;
import cn.com.taiji.taiji.utils.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author SongChong created by9:29 2018/11/29 0029
 * 用户控制层
 */
@SessionAttributes(value = "userList", types = List.class)
@Controller
public class UserController {
    private List<User> userList = new ArrayList<>();

    /**
     * 将用户集合加入session
     *
     * @param model 模型
     * @return java.lang.String html文件名
     */
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("userList", userList);
        return "list";
    }

    /**
     * 添加一个所有属性为空的用户到request，传入前端
     *
     * @param model 模型
     * @return html文件名
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    /**
     * 接收前端的用户信息，进行添加或修改
     *
     * @param model 模型
     * @param user 前端传入的用户
     * @param bindingResult 参数验证
     * @param file 前端传入的头像文件
     * @return html文件名
     */
    @PostMapping("/add")
    public String add(Model model, @Valid User user, BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile file) {
        //如果参数有误，重新输入
        if (bindingResult.hasErrors()) {
            return "add";
        } else {
            if (file != null) {
                //上传文件的存放路径
                String path = "E://sc//taiji//src//main//resources//static//img//";

                //获取上传文件的文件名
                String fileName = file.getOriginalFilename();

                if (!fileName.isEmpty()) {
                    //如果没有path目录，创建它
                    File targetFile = new File(path);
                    if (!targetFile.exists()) {
                        System.out.println("创建" + targetFile.isDirectory());
                        System.out.println(targetFile.mkdirs());
                    }

                    try {
                        file.transferTo(new File(path + fileName));
                        //设置文件存放路径
                        user.setFileUrl("/img/" + fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //根据用户的id是否为空决定实现新增还是修改
        if (user.getId().isEmpty()) {
            //设置id为随机
            user.setId(UUID.randomUUID().toString());
            userList.add(user);
            model.addAttribute("userList", userList);
        } else {
            //删除用户集合中与传入用户id相同的用户
            userList.removeIf(user1 -> user.getId().equals(user1.getId()));
            //将传入的用户加入用户集合
            userList.add(user);
            model.addAttribute("userList", userList);
        }
        return "list";
    }

    /**
     * 回显被修改用户的信息至前端
     *
     * @param id 被修改用户的id
     * @param model 模型
     * @return html文件名
     */
    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable String id, Model model) {
        userList.forEach(user -> {
            if (user.getId().equals(id)) {
                model.addAttribute(user);
            }
        });
        return "add";
    }

    /**
     * 根据传入id删除用户
     * @param id 被删除用户的id
     * @return 执行结果的信息
     */
    @ResponseBody
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        userList.removeIf(user -> user.getId().equals(id));
        return ResultUtils.Success("删除成功！");
    }
}
