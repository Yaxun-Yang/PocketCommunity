package org.yyx.pocket.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yyx.pocket.domain.Activity;
import org.yyx.pocket.domain.Notification;
import org.yyx.pocket.domain.ResponseTemplate;
import org.yyx.pocket.service.CommunityService;

import java.io.File;
//社区模块相关api
@RestController
@RequestMapping("/community")
public class CommunityApi {

    @Autowired
    private CommunityService communityService;


    //上传或修改社区活动图片
    @PostMapping("/picture")
    public ResponseTemplate uploadPicture(@RequestParam MultipartFile file , @RequestParam String activityId) throws Exception
    {
        File temp = new File(new File("D:/Temp/activity/a").getAbsolutePath()+"activity_"+activityId);
        System.out.println(temp.getAbsolutePath());
        System.out.println(activityId);
        if(!temp.exists())
        {
            temp.createNewFile();
        }
        file.transferTo(temp);
        communityService.updatePicture(activityId,"activity_"+activityId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    //新建一个社区活动
    @PostMapping("/activity")
    public ResponseTemplate insertActivity(@RequestBody JSONObject req)
    {
        Activity activity = new Activity();
        activity.setText(req.getString("text"));
        activity.setPicture("activity_default.png");

        JSONObject data = new JSONObject();
        data.put("activityId", communityService.insertActivity(activity));

        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    //新建一个通知
    @PostMapping("/notification")
    public ResponseTemplate insertNotification(@RequestBody JSONObject req)
    {
        Notification notification = new Notification();
        notification.setText(req.getString("text"));

        JSONObject data = new JSONObject();
        data.put("notificationId", communityService.insertNotification(notification));

        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    //删除一个社区活动
    @DeleteMapping("/activity")
    public ResponseTemplate deleteActivity(@RequestParam String activityId)
    {
        communityService.deleteActivity(activityId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    //删除一个通知
    @DeleteMapping("/notification")
    public ResponseTemplate deleteNotification(@RequestParam String notificationId)
    {
        communityService.deleteNotification(notificationId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    //修改社区活动文字
    @PutMapping("/activity")
    public ResponseTemplate updateActivity(@RequestBody JSONObject req)
    {
        String activityId = req.getString("activityId");
        String text = req.getString("text");

        communityService.updateActivity(activityId, text);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    //修改通知
    @PutMapping("/notification")
    public ResponseTemplate updateNotification(@RequestBody JSONObject req)
    {
        Notification notification = new Notification();
        notification.setNotificationId(req.getString("notificationId"));
        notification.setText(req.getString("text"));

        communityService.updateNotification(notification);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/threeActivityList")
    public ResponseTemplate getThreeActivityList()
    {
        JSONObject data = new JSONObject();
        data.put("threeActivityList", communityService.getThreeActivityList());
        return  ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    //获取社区活动列表
    @GetMapping("/activityList")
    public ResponseTemplate getActivityList()
    {
        JSONObject data = new JSONObject();
        data.put("activityList", communityService.getActivityList());
        return  ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    //获取通知列表
    @GetMapping("/notificationList")
    public ResponseTemplate getNotificationList()
    {
        JSONObject data = new JSONObject();
        data.put("notificationList", communityService.getNotificationList());
        return  ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }
}
