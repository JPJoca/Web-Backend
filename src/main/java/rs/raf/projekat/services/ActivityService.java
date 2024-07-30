package rs.raf.projekat.services;

import rs.raf.projekat.entities.Activity;
import rs.raf.projekat.repositories.activity.ActivityRepository;

import javax.inject.Inject;
import java.util.List;

public class ActivityService {

    @Inject
    private ActivityRepository activityRepository;

    public List<Activity> allActivities(){
        return activityRepository.allActivities();
    }

    public Activity findActivityById(int id){
        return activityRepository.findActivityById(id);
    }

    public Activity addActivity(Activity activity){
        return activityRepository.addActivity(activity);
    }

    public void deleteActivity(int id){
        activityRepository.deleteActivity(id);
    }
}