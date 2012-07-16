package com.fuzzydev.gitfit.cloud;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.fuzzydev.gitfit.structures.User;

public class CloudManager {

	private static CloudManager manager = null;
	private static Context currentContext;
	InputStream in = null;

	private CloudManager() {
	}

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

	class registerUserWithCloud extends AsyncTask<User, Void, Void> {

		@Override
		protected Void doInBackground(User... params) {
			User temp = params[0];
			HttpClient client = new DefaultHttpClient();
		    client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Custom user agent");

			HttpConnectionParams
					.setConnectionTimeout(client.getParams(), 50000); // Timeout
																		// Limit
			HttpResponse response;
			JSONObject json = new JSONObject();

			HttpPost post = new HttpPost(
					"http://gitfittemp.appspot.com/gitfitserverservlet");
			try {
				Log.v("firstName: ",temp.getFirstName());
				json.put("firstName", temp.getFirstName());
			} catch (JSONException e2) {
				e2.printStackTrace();
			}

			StringEntity se = null;
			try {
				se = new StringEntity(json.toString());
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			post.setEntity(se);
			try {
				response = client.execute(post);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				Log.v("StatusCode : ",statusCode + "");
				if (statusCode == 200) {
					Log.d("DEBUG", "status code = 200");
				} else {
					Log.e(CloudManager.class.toString(),
							"Failed");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
