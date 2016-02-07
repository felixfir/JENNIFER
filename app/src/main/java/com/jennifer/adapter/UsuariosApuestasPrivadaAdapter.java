package com.jennifer.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jennifer.R;
import com.jennifer.connection.ServerConnection;
import com.jennifer.model.UsuarioApuestaPrivada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echessa on 7/24/15.
 */
public class UsuariosApuestasPrivadaAdapter extends RecyclerView.Adapter<UsuariosApuestasPrivadaAdapter.ViewHolder> {

    private List<UsuarioApuestaPrivada> items = new ArrayList<>();

    public UsuariosApuestasPrivadaAdapter() {
        DataFromWeb dataFromWeb = new DataFromWeb();
        dataFromWeb.execute();
    }

    private PopupWindow popUpWindow;
    private LayoutInflater layoutInflater;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.apuesta_row, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        UsuarioApuestaPrivada item = items.get(i);

        // Data Set
        viewHolder.name.setText(item.getName());

        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_info_apuestas, null);
                popUpWindow = new PopupWindow(container, 1000, 750, true);
                popUpWindow.showAtLocation(view, Gravity.NO_GRAVITY, 500, 500);

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popUpWindow.dismiss();
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cv;
        private final TextView name;

        ViewHolder(View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.cv);
            name = (TextView) v.findViewById(R.id.text);
        }
    }

    public class DataFromWeb extends AsyncTask<Void, Void, Boolean> {

        private final String URL = "http://excellentprogrammers.esy.es/Script/UsuarioApuestaPrivada/UsuarioApuestaPrivada.php";
        private final String TAG_ARRAY_PRIVADA = "apuesta_array";
        private final String TAG_ID = "id";
        private final String TAG_NAME = "name";
        private final String TAG_DESCRIPTION = "description";
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pDialog = new ProgressDialog((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
//            pDialog.setMessage("Cargando info. Por favor espere...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(false);
//            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... args) {
            StringBuilder params = new StringBuilder();
            JSONArray JSONArray;
            ServerConnection serverConnection = new ServerConnection();
            JSONObject json;
            JSONObject instance;
            UsuarioApuestaPrivada apuestaPrivada;

            params.append("type").append("=").append(1)
                    .append("id").append("=").append(1);

            json = serverConnection.makeHttpRequestPost(URL, params.toString());

            try {
                JSONArray = json.getJSONArray(TAG_ARRAY_PRIVADA);

                for (int i = 0; i < JSONArray.length(); i++) {
                    instance = JSONArray.getJSONObject(i);
                    apuestaPrivada = new UsuarioApuestaPrivada();
                    apuestaPrivada.setId(instance.getInt(TAG_ID));
                    apuestaPrivada.setName(instance.getString(TAG_NAME));
                    apuestaPrivada.setDescription(instance.getString(TAG_DESCRIPTION));
                    items.add(apuestaPrivada);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean file_url) {

        }
    }

}
