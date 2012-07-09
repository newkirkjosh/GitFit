package com.fuzzydev.gitfit.structures;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import com.fuzzydev.gitfit.structures.Exercise;

public class Workout implements Parcelable{

	private String ID;
	private String name;
	private ArrayList<Exercise> exercises;
	
	public Workout(){
		exercises = new ArrayList<Exercise>();
	}
	
	private Workout(Parcel in){
		readFromParcel(in);
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(ArrayList<Exercise> exercises) {
		this.exercises = exercises;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(ID);
		dest.writeString(name);
		dest.writeList(exercises);
	}

	public static final Parcelable.Creator<Workout> CREATOR = new Creator<Workout>() {
			
		@Override
		public Workout[] newArray(int size) {
			return new Workout[size];
		}
		
		@Override
		public Workout createFromParcel(Parcel source) {
			return new Workout(source);
		}
	};
	
	private void readFromParcel(Parcel in){
		this.ID = in.readString();
		this.name = in.readString();
		in.readTypedList(exercises, Exercise.CREATOR);
	}
}
