package com.leitorrss.cnbatalha;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.leitorrss.model.cnbatalha.categoria;

public class LeitorRSSData {

	private ArrayList<categoria> Categorias;

	public LeitorRSSData() {
		super();

		// abastecendo categorias
		carregarCategorias();

	}

	public void getItens(String loXML) {

	}

	private void carregarCategorias() {
		this.Categorias = new ArrayList<categoria>();

		Categorias.add(new categoria("Esporte",
				"http://tecnologia.uol.com.br/ultnot/index.xml"));
		Categorias.add(new categoria("Economia",
				"http://rss.uol.com.br/feed/economia.xml"));
		Categorias.add(new categoria("Tecnologia",
				"http://tecnologia.uol.com.br/ultnot/index.xml"));
		Categorias.add(new categoria("Economia",
				"http://rss.uol.com.br/feed/economia.xml"));
		Categorias.add(new categoria("Cinema",
				"http://cinema.uol.com.br/ultnot/index.xml"));
		Categorias.add(new categoria("Esporte",
				"http://esporte.uol.com.br/ultimas/index.xml"));
		Categorias.add(new categoria("Futebol",
				"http://esporte.uol.com.br/futebol/ultimas/index.xml"));
		Categorias.add(new categoria("Jogos",
				"http://jogos.uol.com.br/ultnot/index.xml"));
		Categorias.add(new categoria("Estilo",
				"http://estilo.uol.com.br/ultnot/index.xml"));
	}

	/**
	 * @return the categorias
	 */
	public ArrayList<categoria> getCategorias() {
		return Categorias;
	}

	public String getFeeds(Context ctx, int indexCategoria) {

		/* Verificando conexao com internet */
		if (testaConexao(ctx)) {

			String resp = null;

			// Categoria selecionada
			categoria ctg = Categorias.get(indexCategoria);

			// criando URI com URL da categoria selecionada
			HttpGet uri = new HttpGet(ctg.url);

			HttpClient client = new DefaultHttpClient();
			try {

				// ResponseHandler<String> responseHandler = new
				// BasicResponseHandler();
				HttpResponse response = client.execute(uri);
				// resp = client.execute(uri, responseHandler);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d("error", e.getLocalizedMessage());
				e.printStackTrace();
			}

			return resp;

			/*
			 * // passagem de parametros Bundle params = new Bundle();
			 * params.putString("xml", resp);
			 * 
			 * Intent intent = new Intent(); intent.setClass(ctx,
			 * NoticiasActivity.class);
			 * 
			 * // passagem de parametros intent.putExtras(params);
			 * 
			 * ctx.startActivity(intent);
			 */

		} else {
			Toast.makeText(ctx, "Sem conexão", Toast.LENGTH_LONG).show();
			return "";
		}

	}

	protected boolean testaConexao(Context ctx) {
		boolean retorno = true;

		try {

			ConnectivityManager cm = (ConnectivityManager) ctx
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (!cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
				if (!cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
						.isConnected()) {
					return false;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			// Toast.makeText(LeitorRSS.this, e.getMessage(),
			// Toast.LENGTH_SHORT).show();
			return false;
		}

		return retorno;
	}

}
