package diego.rayme.martinez.practica4.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import diego.rayme.martinez.practica4.R;
import diego.rayme.martinez.practica4.adapter.SolicitudAdapter;
import diego.rayme.martinez.practica4.model.Solicitud;
import diego.rayme.martinez.practica4.services.ServiceGeneratorSolicitud;
import diego.rayme.martinez.practica4.services.ServiceSolicitud;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;
    public RecyclerView listaSolicitudes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaSolicitudes = findViewById(R.id.lista_solicitudes);
        listaSolicitudes.setLayoutManager(new LinearLayoutManager(this));
        listaSolicitudes.setAdapter(new SolicitudAdapter());
        initialize();
    }
    private void initialize() {

        ServiceSolicitud service = ServiceGeneratorSolicitud.createService(ServiceSolicitud.class);

        Call<List<Solicitud>> call = service.getSolicitudes();

        call.enqueue(new Callback<List<Solicitud>>() {
            @Override
            public void onResponse(Call<List<Solicitud>> call, Response<List<Solicitud>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Solicitud> solicitudes = response.body();
                        Log.d(TAG, "Solicitudes: " + solicitudes);

                        SolicitudAdapter adapter = (SolicitudAdapter) listaSolicitudes.getAdapter();
                        adapter.setSolicitudes(solicitudes);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Solicitud>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showRegister(View view){
        startActivityForResult(new Intent(this, RegisterActivity.class), REGISTER_FORM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTER_FORM_REQUEST) {
            // refresh data
            initialize();
        }
    }
}
