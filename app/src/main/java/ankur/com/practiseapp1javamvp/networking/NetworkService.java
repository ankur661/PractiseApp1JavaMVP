package ankur.com.practiseapp1javamvp.networking;

import ankur.com.practiseapp1javamvp.model.CityListResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("v1/city")
    Observable<CityListResponse> getCityList();

}