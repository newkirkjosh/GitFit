package com.fuzzydev.gitfit.cloud;

import android.content.Context;

/*
 *Network imports will use
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
*/
public class CloudManager {
	
	private static CloudManager manager = null;
	private static Context currentContext;

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

}
