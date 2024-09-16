package com.ajwalker.module;

import com.ajwalker.controller.LikeController;
import com.ajwalker.entity.Like;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;

import java.util.Optional;

public class WatchModule {
	private LikeController likeController = LikeController.getInstance();
	private Video video;
	private Optional<User> user;
	
	public void watchMenu(Video video, Optional<User> user){
		this.video = video;
		this.user = user;
		int opt;
		do{
			System.out.printf("### %s ###\n", video.getTitle()); // videonun kaç like ve dislike'ı var
			// yazdırabilirsin burada
			System.out.println("""
					                   1. Watch video
					                   2. Read Description
					                   3. Like video
					                   4. Comment video
					                   5. Read Comments
					                   0. Go Back
					                   """); // 6. abone ol
			opt = MainMenu.choice();
			watchMenuOptions(opt);
		}while(opt != 0);
	}
	
	private int watchMenuOptions(int opt) {
		switch (opt){
			case 1:
				System.out.println(video.getContent());
				break;
			case 2:
				System.out.println(video.getDescription());
				break;
			case 3:
				if (user.isEmpty()){
					System.out.println("You have to log in to like a video");
					user = new LoginMenu().loginModule();
					if (user.isEmpty()){
						System.out.println("Failed attempt to login(watchmodule.watchmenuoptions)");
						break;
					}
				}
				likeController.likeTheVideo(video, user.get());
				break;
			default:
				System.out.println("Invalid option in watchmenuoptions");
		}
		return opt;
	}
}