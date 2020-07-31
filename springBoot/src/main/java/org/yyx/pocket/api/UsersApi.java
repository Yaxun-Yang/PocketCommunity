package org.yyx.pocket.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yyx.pocket.domain.ResponseTemplate;
import org.yyx.pocket.domain.Users;
import org.yyx.pocket.service.UsersService;

import java.io.File;


@RestController
@RequestMapping("/users")
public class UsersApi {

    @Autowired
    private UsersService usersService;

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
    public ResponseTemplate insertUser(@RequestBody JSONObject req)
    {
        Users users = fillUsers(req);

        JSONObject data = new JSONObject();
        data.put("userId", usersService.insertUser(users));

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
    public ResponseTemplate updateUser(@RequestBody JSONObject req)
    {

        Users users = fillUsers(req);
        users.setUserId(req.getString("userId"));

        usersService.updateUser(users);

        return ResponseTemplate.builder()
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
