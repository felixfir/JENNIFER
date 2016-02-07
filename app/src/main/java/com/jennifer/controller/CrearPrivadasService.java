package com.jennifer.controller;

import android.app.Activity;
import android.os.AsyncTask;

import com.jennifer.connection.ServerConnection;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felix on 07/02/2016.
 */
public class CrearPrivadasService extends AsyncTask<String, String, String> {

    Activity activity;
    String titulo;
    String descripcion;
    String fecha;
    String tipoApuesta;
    Double premio;
    int contadorUsuarios;
    int contadorRespuestas;
    List<String> usuarios = new ArrayList<String>();
    List<String> respuestas = new ArrayList<String>();

    public CrearPrivadasService(Activity activity, String titulo, String descripcion, String fecha, String tipoApuesta, Double premio, List<String> usuarios, List<String> respuestas, int contadorUsuarios, int contadorRespuestas) {
        this.activity = activity;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipoApuesta = tipoApuesta;
        this.premio = premio;
        this.usuarios = usuarios;
        this.respuestas = respuestas;
        this.contadorUsuarios = contadorUsuarios;
        this.contadorRespuestas = contadorRespuestas;
    }

    @Override
    protected String doInBackground(String... args) {
        int i = 1;
        String users = "";
        String options = "";

        for (String row : usuarios) {
            users += row + "|";
            System.out.println("Nombre " + i + ": " + row);
            i++;
        }

        i = 1;

        for (String row : respuestas) {
            options += row + "|";
            System.out.println("Respuesta " + i + ": " + row);
            i++;
        }

        StringBuilder parametros = new StringBuilder();

        parametros.append("name").append("=").append(titulo).append("&")
                .append("description").append("=").append(descripcion).append("&")
                .append("dateTime").append("=").append(fecha).append("&")
                .append("typeBet").append("=").append(tipoApuesta).append("&")
                .append("award").append("=").append(premio).append("&")
                .append("users").append("=").append(users).append("&")
                .append("options").append("=").append(options);

        System.out.println("Users: " + users);
        System.out.println("Options: " + options);
        System.out.println("Parametros: " + parametros.toString());

        JSONObject json;
        ServerConnection serverConnection = new ServerConnection();

        json = serverConnection.makeHttpRequestPost("http://excellentprogrammers.esy.es/Script/Felix/PrivateMatches.php", parametros.toString());
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
