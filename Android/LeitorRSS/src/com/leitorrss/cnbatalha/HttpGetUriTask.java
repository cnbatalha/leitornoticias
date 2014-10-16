package com.leitorrss.cnbatalha;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.leitorrss.model.cnbatalha.item;
import com.leitorrss.model.cnbatalha.rssNoticia;
import com.listview.cnbatalha.AdapterListView;
import com.thoughtworks.xstream.XStream;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.util.Log;

public class HttpGetUriTask extends AsyncTask<String, Integer, rssNoticia> {

	
	private ProgressDialog progressDlg;
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		
		try {
					
			progressDlg = ProgressDialog.show(LeitorRSS.context, "Download", "Atualizando Noticias");
			
		} catch (Exception e) {
			// TODO: handle exception
			 
		}	
			
		super.onPreExecute();
	}	
	
	@Override
	protected rssNoticia doInBackground(String... urls) {
	
		// TODO Auto-generated method stub
		String url = urls[0];
		String data = "";
		
		rssNoticia rss= null;
		
		HttpURLConnection httpUrlConnection = null;

		try {

			httpUrlConnection = (HttpURLConnection) new URL(url)
					.openConnection();

			InputStream in = new BufferedInputStream(
					httpUrlConnection.getInputStream());

			data = readStream(in);

		} catch (MalformedURLException exception) {
			Log.e("LeitorRSS", "MalformedURLException");
		} catch (IOException exception) {
			Log.e("LeitorRSS", "IOException");
		} finally {
			if (null != httpUrlConnection)
				httpUrlConnection.disconnect();
		}

		// processando lista de noticias
		XStream xstream = new XStream();
		xstream.processAnnotations(rssNoticia.class);
		xstream.autodetectAnnotations(false);

		try {

			String xmlStream = data;
			rss = (rssNoticia) xstream.fromXML(xmlStream);

			for (item i : rss.channel.items) {
				while (i.title.contains("\t"))
					i.title = i.title.replace("\t", "");

				while (i.title.contains("\n"))
					i.title = i.title.replace("\n", "");
			}


		} catch (Exception ex) {
			System.out.print("Serialization Read Error : " + ex.getMessage());
			ex.printStackTrace();
		} 		
		
		return rss;
	}
	
	@Override
	protected void onPostExecute(rssNoticia result) {
		// TODO Auto-generated method stub	
	
		AdapterListView adp = new AdapterListView( NoticiasActivity.noticiaActivityContext, result.channel.items);

		NoticiasActivity.lvNoticias.setAdapter(adp);
				
		progressDlg.dismiss();
		
		super.onPostExecute(result);
	}

	private String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuffer data = new StringBuffer("");
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}
		} catch (IOException e) {
			Log.e("LeitorRSS", "IOException");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data.toString();
	}
}
