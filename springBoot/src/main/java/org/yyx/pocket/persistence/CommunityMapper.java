package org.yyx.pocket.persistence;


import org.springframework.stereotype.Repository;
import org.yyx.pocket.domain.Activity;
import org.yyx.pocket.domain.Notification;

import java.util.List;

@Repository
public interface CommunityMapper {
    void insertActivity(Activity activity);
    void insertNotification(Notification notification);
    void deleteActivity(String activityId);
    void deleteNotification(String notificationId);
    void updatePicture(String activityId, String picture);
    void updateActivity(String activityId, String text);
    void updateNotification(Notification notification);
    String getRecentActivity();
    String getRecentNotification();
    List<Activity>getThreeActivityList();
    List<Activity> getActivityList();
    List<Notification> getNotificationList();
}
