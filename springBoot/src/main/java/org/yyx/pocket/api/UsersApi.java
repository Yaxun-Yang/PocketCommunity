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


@RestController
@RequestMapping("/users")
public class UsersApi {

    @Autowired
    private UsersService usersService;

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

    @DeleteMapping("/user")
    public ResponseTemplate deleteUser(@RequestParam String userId)
    {
        usersService.deleteUser(userId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

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
