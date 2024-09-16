package com.ajwalker.service;

import com.ajwalker.database.DatabaseHelper;
import com.ajwalker.entity.Like;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;
import com.ajwalker.repository.LikeRepository;
import com.ajwalker.utility.ICRUD;

import java.util.List;
import java.util.Optional;

public class LikeService {
    private static final LikeRepository likeRepository = LikeRepository.getInstance();
    private static LikeService instance;

    private LikeService() {}

    public static LikeService getInstance() {
        if (instance == null) {
            instance = new LikeService();
        }
        return instance;
    }

    public boolean save(Like like) {
        return likeRepository.save(like);
    }

    public boolean update(Like like) {
        return likeRepository.update(like);
    }

    public boolean delete(Long id) {
        return likeRepository.delete(id);
    }

    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    public Optional<Like> findById(Long id) {
        return likeRepository.findById(id);
    }
    
    public void likeTheVideo(Video video, User user) {
        Optional<Like> optLike = findByVideoAndUser(video, user);
        if (optLike.isPresent()) {
            Like like = optLike.get();
            like.setState(1);
            update(like);
        }
        else {
            save(new Like(user.getId(), video.getId(), 1));
        }
    }
    
    public Optional<Like> findByVideoAndUser(Video video, User user) {
        return likeRepository.findByVideoAndUserId(video.getId(), user.getId());
    }
}