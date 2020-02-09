package ankur.com.practiseapp1javamvp.networking;

import ankur.com.practiseapp1javamvp.model.CityListResponse;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCityList(final GetCityListCallback callback) {

        return networkService.getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CityListResponse>>() {
                    @Override
                    public Observable<? extends CityListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CityListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(CityListResponse cityListResponse) {
                        callback.onSuccess(cityListResponse);

                    }
                });
    }

    public interface GetCityListCallback{
        void onSuccess(CityListResponse cityListResponse);

        void onError(NetworkError networkError);
    }
}