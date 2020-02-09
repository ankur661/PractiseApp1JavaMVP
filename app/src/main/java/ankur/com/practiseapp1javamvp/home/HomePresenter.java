package ankur.com.practiseapp1javamvp.home;

import ankur.com.practiseapp1javamvp.model.CityListResponse;
import ankur.com.practiseapp1javamvp.networking.Service;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList() {
        view.showWait();

        Subscription subscription = service.getCityList(new Service.GetCityListCallback() {
            @Override
            public void onSuccess(CityListResponse cityListResponse) {
                view.removeWait();
                view.getityListSuccess(cityListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}