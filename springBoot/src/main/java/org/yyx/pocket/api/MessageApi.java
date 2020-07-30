package org.yyx.pocket.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yyx.pocket.domain.Message;
import org.yyx.pocket.domain.ResponseTemplate;
import org.yyx.pocket.persistence.UsersMapper;
import org.yyx.pocket.service.MessageService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageApi {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UsersMapper usersMapper;

    @PostMapping("/message")
    public ResponseTemplate insertMessage (@RequestBody JSONObject req)
    {
        Message message = new Message();
        message.setText(req.getString("text"));
        message.setUserId(req.getString("userId"));
        message.setMessageTimestamp(new Timestamp(System.currentTimeMillis()));

        JSONObject data = new JSONObject();
        data.put("messageId", messageService.insertMessage(message));

        return  ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/message")
    public ResponseTemplate deleteMessage (@RequestParam String messageId)
    {
        messageService.deleteMessage(messageId);
        return  ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PutMapping("/message")
    public ResponseTemplate updateMessage(@RequestBody JSONObject req)
    {
        Message message = messageService.getMessage(req.getString("messageId"));
        message.setText(req.getString("text"));

        messageService.updateMessage(message);

        return  ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/messageList")
    public ResponseTemplate getMessageList()
    {

        JSONArray data = new JSONArray();
        List<Message> messageList =messageService.getMessageList();
        for (int i=0;i<messageList.size();i++)
        {
            JSONObject messageShow = new JSONObject();
            messageShow.put("message", messageList.get(i));
            messageShow.put("user",usersMapper.getUser(messageList.get(i).getUserId()));
            data.add(messageShow);
        }

        return  ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }
}
