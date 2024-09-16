package com.ajwalker.service;

import com.ajwalker.dto.response.DtoVideoThumbnail;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;
import com.ajwalker.repository.VideoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VideoService {
    private final static VideoRepository videoRepository = VideoRepository.getInstance();
    private static VideoService instance;

    private VideoService(){
    }

    public static VideoService getInstance() {
        if (instance == null){
            instance = new VideoService();
        }
        return instance;
    }

    public boolean save(Video video) {
        return videoRepository.save(video);
    }

    public boolean update(Video video) {
        return videoRepository.update(video);
    }

    public boolean delete(Long id) {
        return videoRepository.delete(id);
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Optional<Video> findById(Long id) {
        return videoRepository.findById(id);
    }
    
    public List<DtoVideoThumbnail> showAllVideos() {
        List<Video> all = findAll();
        return videoToDto(all);
    }
    
    public List<DtoVideoThumbnail> showByName(String videoTitle) {
        List<Video> videos = videoRepository.findByTitle(videoTitle);
        return videoToDto(videos);
    }
    
    public List<DtoVideoThumbnail> videoToDto(List<Video> all){
        return all.stream().map(
                video -> {
                    Optional<User> optCreator = UserService.getInstance().findById(video.getCreator_id());
                    String creatorUsername;
                    if (optCreator.isPresent()) {
                        creatorUsername = optCreator.get().getUsername();
                        return new DtoVideoThumbnail(video.getTitle(), creatorUsername, video.getId());
                    }
                    else return null;
                }).toList();
    }
    
    public List<DtoVideoThumbnail> showMyVideos(User user) {
        List<Video> videos = videoRepository.findByCreatorId(user);
        return videoToDto(videos);
    }
}