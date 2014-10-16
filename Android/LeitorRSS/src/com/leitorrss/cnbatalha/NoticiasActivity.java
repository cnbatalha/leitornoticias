package com.leitorrss.cnbatalha;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.ObjectInputStream;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.leitorrss.model.cnbatalha.item;
import com.leitorrss.model.cnbatalha.rssNoticia;
import com.listview.cnbatalha.*;
import com.thoughtworks.xstream.XStream;

public class NoticiasActivity extends Activity {

	// ListView
	public static ListView lvNoticias;
	public static Context noticiaActivityContext;

	public FileInputStream fis = null;

	// Testa se está ativo fragment usando em Tablets
	public boolean IsFragment() {
		return false; // (findViewById(R.id.fragment_categorias) != null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticias);

		noticiaActivityContext = this;
		
		Intent intent = getIntent();

		// carregando parametros
		String urlNoticia = intent.getStringExtra(LeitorRSS.URL_NOTICIA);
		String ctgTitulo =  intent.getStringExtra(LeitorRSS.CATEGORIA_NOTICIA);
				
		lvNoticias = (ListView) findViewById(R.id.lvNoticias);
		 		
		TextView txtPrincipal = (TextView) findViewById(R.id.txvCategoria);
		txtPrincipal.setText(ctgTitulo);	
		
		// chamando thread
		new HttpGetUriTask().execute(urlNoticia);

		/*
		XStream xstream = new XStream();
		xstream.processAnnotations(rssNoticia.class);
		xstream.autodetectAnnotations(false);

		try {

			String xmlStream = params.getString("xml");
			rssNoticia rss = (rssNoticia) xstream.fromXML(xmlStream);

			for (item i : rss.channel.items) {
				while (i.title.contains("\t"))
					i.title = i.title.replace("\t", "");

				while (i.title.contains("\n"))
					i.title = i.title.replace("\n", "");
			}

			TextView txtPrincipal = (TextView) findViewById(R.id.txvCategoria);
			txtPrincipal.setText(rss.getchannel().getCategory());

		

			AdapterListView adp = new AdapterListView(this, rss.channel.items);

			lvNoticias.setAdapter(adp);

		} catch (Exception ex) {
			System.out.print("Serialization Read Error : " + ex.getMessage());
			ex.printStackTrace();
		} */

		lvNoticias.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				// Categoria selecionada
				item itm = (item) arg0.getItemAtPosition(arg2);

				String link = itm.getLink();

				while (link.contains("\t"))
					link = link.replace("\t", "");

				while (link.contains("\n"))
					link = link.replace("\n", "");

				// Representa o texto digitado pelo usuário que vamos abrir
				Uri url = Uri.parse(link);

				Intent intencaoDeAbrirNavegador = new Intent(
						Intent.ACTION_VIEW, url);

				// Enviando a mensagem para o sistema operacional pedido para
				// abrir o Browser
				startActivity(intencaoDeAbrirNavegador);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.noticias, menu);
		return true;
	}

}
