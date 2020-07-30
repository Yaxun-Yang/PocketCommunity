package org.yyx.pocket.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yyx.pocket.domain.ResponseTemplate;
import org.yyx.pocket.domain.Users;
import org.yyx.pocket.service.UsersService;



@RestController
@RequestMapping("/users")
public class UsersApi {

    @Autowired
    private UsersService usersService;

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

        //图片需要经过额外处理
        users.setAvatar(req.getString("avatar"));

        users.setCommunity(req.getString("community"));
        users.setIdCartNum(req.getString("idCartNum"));
        users.setPhoneNumber(req.getString("phoneNumber"));

        return users;
    }
}
