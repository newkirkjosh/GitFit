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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefSets() {
		return defSets;
	}

	public void setDefSets(String defSets) {
		this.defSets = defSets;
	}

	public String getDefReps() {
		return defReps;
	}

	public void setDefReps(String defReps) {
		this.defReps = defReps;
	}

	public String getmSets() {
		return mSets;
	}

	public void setmSets(String mSets) {
		this.mSets = mSets;
	}

	public String getmReps() {
		return mReps;
	}

	public void setmReps(String mReps) {
		this.mReps = mReps;
	}

	public String getDefTimeLimit() {
		return defTimeLimit;
	}

	public void setDefTimeLimit(String defTimeLimit) {
		this.defTimeLimit = defTimeLimit;
	}

	public String getmTimeLimit() {
		return mTimeLimit;
	}

	public void setmTimeLimit(String mTimeLimit) {
		this.mTimeLimit = mTimeLimit;
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
