package com.fuzzydev.gitfit.cloud;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.fuzzydev.gitfit.structures.User;
import com.google.gson.Gson;
public class CloudManager {

	private static CloudManager manager = null;
	private static Context currentContext;
	HttpParams params = new BasicHttpParams();
	InputStream in = null;
	Gson gson = new Gson();

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
			client.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
					"Custom user agent");

			HttpConnectionParams
					.setConnectionTimeout(client.getParams(), 50000); // Timeout
																		// Limit
			HttpResponse response = null;
			HttpPost post = new HttpPost(
					"http://gitfit1.appspot.com/gfregistrationservlet");
			
			String jsonObj = gson.toJson(temp);
			
			try {
				 StringEntity entity = new StringEntity(jsonObj);
	             entity.setContentType("application/json;charset=UTF-8");
                 post.setHeader("Accept", "application/json");
	 			 post.setEntity(entity);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
			try {
				response = client.execute(post);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				Log.v("StatusCode : ", statusCode + "");
				
				Header[] validationHeaders = response.getHeaders("xValidation");
				
				Log.v("size: ", validationHeaders.length + "");
				int validationCode = Integer.parseInt(validationHeaders[0].getValue());
				Log.v("ValidationCode : ", validationCode +"");
				
				
				if (statusCode == 200) {
					Log.d("DEBUG", "status code = 200");
				} else {
					Log.e(CloudManager.class.toString(), "Failed");
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
