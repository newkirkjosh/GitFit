package com.fuzzydev.gitfit.cloud;

import com.fuzzydev.gitfit.structures.User;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;


public class CloudManager {
	
	private static CloudManager manager = null;
	private static Context currentContext;
	InputStream in = null;

	private CloudManager() {}
	
	public static CloudManager getInstance(Context context) {
		if (manager == null)
			manager = new CloudManager();
		CloudManager.setCurrentContext(context);
		return manager;
	}

	public static Context getCurrentContext() {
		return currentContext;
	}

	public static void setCurrentContext(Context currentContext) {
		CloudManager.currentContext = currentContext;
	}
	
	public void registerUser(User user) {
		new registerUserWithCloud().execute(user);
	}
	
	class registerUserWithCloud extends AsyncTask<User,Void,Void>{

		@Override
		protected Void doInBackground(User... params) {
			User temp = params[0];
			 Looper.prepare(); //For Preparing Message Pool for the child Thread
             HttpClient client = new DefaultHttpClient();
             HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
             HttpResponse response;
             JSONObject json = new JSONObject();
             try{
                 HttpPost post = new HttpPost("http://gitfittemp.appspot.com/gitfitserver/GitFitServerServlet");
                 json.put("firstName", temp.getFirstName());
                 StringEntity se = new StringEntity( json.toString());  
                 se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                 post.setEntity(se);
                 response = client.execute(post);
                 /*Checking response */
                 String httpStatus = response.getStatusLine().toString();
                 Log.v("Response :", httpStatus);
             }
             catch(Exception e){
                 e.printStackTrace();
             }
             Looper.loop(); //Loop in the message queue
             return null;
         }
	}	
}
