package ankur.com.practiseapp1javamvp.home;

import ankur.com.practiseapp1javamvp.model.CityListResponse;

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getityListSuccess(CityListResponse cityListResponse);

}