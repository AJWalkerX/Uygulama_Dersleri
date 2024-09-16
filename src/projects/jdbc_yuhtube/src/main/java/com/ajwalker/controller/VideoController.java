package com.ajwalker.controller;

import com.ajwalker.dto.response.DtoVideoThumbnail;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;
import com.ajwalker.service.VideoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoController {
    private final static VideoService videoService = VideoService.getInstance();
    private static VideoController instance;

    private VideoController(){
    }

    public static VideoController getInstance() {
        if (instance == null){
            instance = new VideoController();
        }
        return instance;
    }

    public boolean save(Video video) {
        return videoService.save(video);
    }

    public boolean update(Video video) {
        return videoService.update(video);
    }

    public boolean delete(Long id) {
        return videoService.delete(id);
    }

    public List<Video> findAll() {
        return videoService.findAll();
    }

    public Optional<Video> findById(Long id) {
        return videoService.findById(id);
    }
    
    public List<DtoVideoThumbnail> showAllVideos() {
	    try {
		   return videoService.showAllVideos();
	    }
	    catch (Exception e) {
            System.out.println("could not show all videos(controller)..." + e.getMessage());
            return new ArrayList<>();
	    }
    }
    
    public List<DtoVideoThumbnail> showByName(String videoTitle) {
        try {
            return videoService.showByName(videoTitle);
        }
        catch(Exception e){
            System.out.println("could not show the videos by title(controller)..." + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    
    public List<DtoVideoThumbnail> showMyVideos(User user) {
        try{
            return videoService.showMyVideos(user);
        }
        catch (Exception e) {
            System.out.println("Could not show your videos(controller)..." + e.getMessage());
            return new ArrayList<>();
        }
    }
}