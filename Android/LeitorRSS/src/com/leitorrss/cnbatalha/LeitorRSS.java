package com.leitorrss.cnbatalha;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.leitorrss.database.DataBaseHelper;
import com.leitorrss.model.cnbatalha.categoria;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LeitorRSS extends Activity {
		
	public static final String URL_NOTICIA = "URL_NOTICIA";
	public static final String CATEGORIA_NOTICIA = "CATEGORIA_NOTICIA";
	
	
	protected DataBaseHelper dbRss;
	protected SQLiteDatabase db;

	public ArrayList<categoria> Categorias;
	public ListView lvCategorais;

	private static ProgressDialog progressDialog;
	public static Context context;

	// Constrole de Dados do App
	public LeitorRSSData rssData;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leitor_rss);

		context = this;

		try {

			// Criando DataBase
			dbRss = new DataBaseHelper(this);

			db = dbRss.getReadableDatabase();

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "Erro " + e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}

		// TODO - Verificar melhor forma com asyncTask

		// StrictMode.ThreadPolicy policy = new
		// StrictMode.ThreadPolicy.Builder()
		// .permitAll().build();
		// StrictMode.setThreadPolicy(policy);
		
		
		AbastecendoListaCategorias();
	}

	public void showDialog(String mensagem) {
		progressDialog = ProgressDialog.show(this, "Download", mensagem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.leitor_rs, menu);
		return true;
	}

	private void AbastecendoListaCategorias() {

		// carregando Dados do APP
		rssData = new LeitorRSSData();

		// Inicializando Categorias
		Categorias = rssData.getCategorias();

		// link com componente no activate
		lvCategorais = (ListView) findViewById(R.id.lvCategorias);

		// link com Adapter
		ArrayAdapter<categoria> adapter = new ArrayAdapter<categoria>(this,
				android.R.layout.simple_list_item_1, Categorias);

		if (lvCategorais != null) {
			// setando conteudo
			lvCategorais.setAdapter(adapter);

			// Comando click da Categoria selecionada
			lvCategorais.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					categoria ctg = (categoria) lvCategorais
							.getItemAtPosition(arg2);

					Intent iNoticias = new Intent( LeitorRSS.this, NoticiasActivity.class );
					iNoticias.putExtra(URL_NOTICIA, ctg.url);
					iNoticias.putExtra(CATEGORIA_NOTICIA, ctg.titulo);
					
					startActivity(iNoticias);
					
					/*
					 * String resp = rssData.getFeeds(LeitorRSS.this, arg2);
					 * 
					 * if (resp != "") { // chamar tela }
					 */

				}
			});
		}
	}

}
