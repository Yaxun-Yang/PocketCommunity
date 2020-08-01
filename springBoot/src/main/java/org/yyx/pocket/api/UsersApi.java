package org.yyx.pocket.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yyx.pocket.domain.ResponseTemplate;
import org.yyx.pocket.domain.Users;
import org.yyx.pocket.service.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

//用户模块相关api
@RestController
@RequestMapping("/users")
public class UsersApi {

    @Autowired
    private UsersService usersService;

    //发送验证码
    @PostMapping("/verifyCode")
    public ResponseTemplate sendVerificationCode(@RequestParam String phoneNumber, HttpServletRequest httpServletRequest)
    {


        //此处仅为模拟短信发送
         String code = "188234";

        //此处为真实的短信发送
       // String code= SmsService.sendSms(phoneNumber);
        System.out.println("成功发送短信给"+phoneNumber+"，验证码为"+code);
        httpServletRequest.getSession().setAttribute("code",code);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();

    }

    //上传或者更新头像
    @PostMapping("/avatar")
    public ResponseTemplate uploadPicture(@RequestParam MultipartFile file , @RequestParam String userId) throws Exception
    {
        File temp = new File(new File("D:/Temp/avatar/a").getAbsolutePath()+"avatar_"+userId);
        System.out.println(temp.getAbsolutePath());
        System.out.println(userId);
        if(!temp.exists())
        {
            temp.createNewFile();
        }
        file.transferTo(temp);
        usersService.updateAvatar(userId,"avatar_"+userId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    //注册用户
    @PostMapping("/user")
    public ResponseTemplate insertUser(@RequestBody JSONObject req, HttpServletRequest httpServletRequest)
    {
        HttpSession session = httpServletRequest.getSession();
        String code =(String) session.getAttribute("code");
        session.setAttribute("code",null);

        String userCode = req.getString("verifyCode");

        JSONObject data = new JSONObject();

        if(code==null||!code.equals(userCode))
        {
            data.put("userId",null);
        }
        else {
            Users users = fillUsers(req);
            data.put("userId", usersService.insertUser(users));
        }

        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    //注销用户
    @DeleteMapping("/user")
    public ResponseTemplate deleteUser(@RequestParam String userId)
    {
        usersService.deleteUser(userId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    //修改用户信息
    @PutMapping("/user")
    public ResponseTemplate updateUser(@RequestBody JSONObject req, HttpServletRequest httpServletRequest)
    {

        HttpSession session = httpServletRequest.getSession();
        String code =(String) session.getAttribute("code");
        session.setAttribute("code",null);

        String userCode = req.getString("verifyCode");

        JSONObject data = new JSONObject();

        if(code==null||!code.equals(userCode))
        {
            data.put("userId",null);
        }
        else
        {
            Users users = fillUsers(req);
            String userId = req.getString("userId");
            users.setUserId(userId);

            usersService.updateUser(users);

            data.put("userId",userId);
        }
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    //通过id获取用户信息
    @GetMapping("/userInfo")
    public ResponseTemplate getUser(@RequestParam String userId)
    {
        JSONObject data = new JSONObject();
        data.put("user",usersService.getUser(userId));
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    //验证用户名密码的正确性
    @GetMapping("/user")
    public ResponseTemplate getUserByUsernameAndPassword(@RequestParam String username, @RequestParam String password)
    {
        JSONObject data = new JSONObject();
        data.put("user",usersService.getUsersByUsernameAndPassword(username, password));
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    //获取全部用户
    @GetMapping("/userList")
    public ResponseTemplate getUserList()
    {
        JSONObject data = new JSONObject();
        data.put("userList",usersService.getUserList());
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    //填充用户信息
    private Users fillUsers(JSONObject req)
    {

        Users users = new Users();
        users.setUsername(req.getString("username"));
        users.setPassword(req.getString("password"));
        users.setAddress(req.getString("address"));
        users.setAdmin(req.getString("admin"));
        users.setAvatar("avatar.png");
        users.setCommunity(req.getString("community"));
        users.setIdCartNum(req.getString("idCartNum"));
        users.setPhoneNumber(req.getString("phoneNumber"));
        return users;
    }
}
