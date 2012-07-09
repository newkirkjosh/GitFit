package com.fuzzydev.gitfit.structures;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable{

	private String name;
	private String type;
	private String defSets;
	private String defReps;
	private String mSets;
	private String mReps;
	private String defTimeLimit;
	private String mTimeLimit;
	
	public Exercise(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(type);
		dest.writeString(defSets);
		dest.writeString(defReps);
		dest.writeString(mSets);
		dest.writeString(mReps);
		dest.writeString(defTimeLimit);
		dest.writeString(mTimeLimit);
	}
	
	public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
		
		@Override
		public Exercise[] newArray(int size) {
			return new Exercise[size];
		}
		
		@Override
		public Exercise createFromParcel(Parcel source) {
			return new Exercise(source);
		}
	};
	
	private void readFromParcel(Parcel in) {
		this.name = in.readString();
		this.type = in.readString();
		this.defSets = in.readString();
		this.defReps = in.readString();
		this.mSets = in.readString();
		this.mReps = in.readString();
		this.defTimeLimit = in.readString();
		this.mTimeLimit = in.readString();
	}
}
