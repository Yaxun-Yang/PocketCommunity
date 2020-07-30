package org.yyx.pocket.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yyx.pocket.domain.Activity;
import org.yyx.pocket.domain.Notification;
import org.yyx.pocket.domain.ResponseTemplate;
import org.yyx.pocket.service.CommunityService;

@RestController
@RequestMapping("/community")
public class CommunityApi {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/activity")
    public ResponseTemplate insertActivity(@RequestBody JSONObject req)
    {
        Activity activity = new Activity();
        activity.setText(req.getString("text"));
        activity.setPicture(req.getString("picture"));

        JSONObject data = new JSONObject();
        data.put("activityId", communityService.insertActivity(activity));

        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

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

    @DeleteMapping("/activity")
    public ResponseTemplate deleteActivity(@RequestParam String activityId)
    {
        communityService.deleteActivity(activityId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/notification")
    public ResponseTemplate deleteNotification(@RequestParam String notificationId)
    {
        communityService.deleteNotification(notificationId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

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
