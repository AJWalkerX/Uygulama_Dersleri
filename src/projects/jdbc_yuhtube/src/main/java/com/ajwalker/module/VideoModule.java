package com.ajwalker.module;

import com.ajwalker.controller.VideoController;
import com.ajwalker.dto.response.DtoVideoThumbnail;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.ajwalker.module.MainMenu.choice;

public class VideoModule {
	private Optional<User> user;
	private Scanner scanner = new Scanner(System.in);
	private VideoController videoController = VideoController.getInstance();
	private List<DtoVideoThumbnail> videosToWatch = new ArrayList<DtoVideoThumbnail>();
	
	public Optional<User> videoModule(Optional<User> user) {
		this.user = user;
		int opt;
		do{
			System.out.println("""
					                   YuhTube
					                   Videos
					                   1. Show All Videos
					                   2. Filter By Title
					                   3. Show My Videos
					                   4. What's Trending
					                   5. Choose Video To Watch
					                   0. Go Back
					                   
					                   """);
			opt = mainMenuOptions(choice());
		}while(opt != 0);
		return user;
	}
	
	private int mainMenuOptions(int choice) {
		switch (choice){
			case 1:
				videosToWatch = showAllVideos();
				break;
			case 2:
				videosToWatch = filterByTitle();
				break;
			case 3:
				if (user.isEmpty()){
					System.out.println("You must log in to see your videos");
					user = new LoginMenu().loginModule();
					if(user.isEmpty()){
						System.out.println("Cannot see your videos since you have not logged in");
						break;
					}
				}
				videosToWatch = showMyVideos(user.get());
				break;
				case 4:
					// show what's trending
					break;
			case 5:
				chooseVideoToWatch();
				break;
			default:
				System.out.println("Invalid option!");
		}
		
		return choice;
	}
	
	private void chooseVideoToWatch() {
		int opt;
		do{
			System.out.println("Which video(ex: 1)");
			opt = choice();
			try{
				Long videoId = videosToWatch.get(opt - 1).getVideoId();
				Optional<Video> optVideo = videoController.findById(videoId);
				if (optVideo.isEmpty()){
					System.out.println("Video not found");
					return;
				}
				Video video = optVideo.get();
				new WatchModule().watchMenu(video, user);
			}
			catch (Exception e) {
				System.out.println("No such video (controller chosevideotowatch)..." + e.getMessage());
			}
			
		}while(opt != 0);
	}
	
	private List<DtoVideoThumbnail> showMyVideos(User user) {
		List<DtoVideoThumbnail> videos = videoController.showMyVideos(user);
		printVideos(videos);
		return videos;
	}
	
	private List<DtoVideoThumbnail> filterByTitle() {
		System.out.print("Apply filter> ");
		List<DtoVideoThumbnail> videos = videoController.showByName(scanner.nextLine());
		printVideos(videos);
		return videos;
	}
	
	private List<DtoVideoThumbnail> showAllVideos() {
		List<DtoVideoThumbnail> videos = videoController.showAllVideos();
		printVideos(videos);
		return videos;
	}
	
	private void printVideos(List<DtoVideoThumbnail> videos){
		System.out.println("####### VIDEOS #######");
		for (int i = 0; i < videos.size(); i++) {
			System.out.println((i + 1) + ". Video");
			System.out.println(videos.get(i));
			System.out.println("-  -  -   -   -   -   -   -  -   -   -   -   -   -   -   -\n");
		}
	}
}