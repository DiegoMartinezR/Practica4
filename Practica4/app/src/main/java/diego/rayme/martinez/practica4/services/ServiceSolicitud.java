package diego.rayme.martinez.practica4.services;

import java.util.List;

import diego.rayme.martinez.practica4.model.Solicitud;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceSolicitud {

    String API_BASE_URL = "http://practica4-diegomr.c9users.io";

    @GET("/api/solicitudes/")
    public Call<List<Solicitud>> getSolicitudes();

    @FormUrlEncoded
    @POST("/api/solicitudes/")
    Call<Solicitud> createSolicitud(@Field("correo") String correo,
                                    @Field("tipo") String tipo,
                                    @Field("razon") String razon);
    @Multipart
    @POST("/api/solicitudes/")
    Call<Solicitud> createSolicitudWithImage(
            @Part("correo") RequestBody correo,
            @Part("tipo") RequestBody tipo,
            @Part("razon") RequestBody razon,
            @Part MultipartBody.Part img
    );


}
