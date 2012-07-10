package com.fuzzydev.gitfit.structures;

import android.widget.ImageView;

public class UpdateItem {
	
	private String name;
	private String imgUrl;
	private String workoutType;
	
	public UpdateItem(String name,String imgUrl, String workoutType){
		this.setImageUrl(imgUrl);
		this.setWorkoutType(workoutType);
		this.setName(name);
	}

	public String getWorkoutType() {
		return workoutType;
	}

	public void setWorkoutType(String workoutType) {
		this.workoutType = workoutType;
	}

	public String getImageUrl() {
		return imgUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imgUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
