package ankur.com.practiseapp1javamvp.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ankur.com.practiseapp1javamvp.BaseApp;
import ankur.com.practiseapp1javamvp.R;
import ankur.com.practiseapp1javamvp.model.CityListData;
import ankur.com.practiseapp1javamvp.model.CityListResponse;
import ankur.com.practiseapp1javamvp.networking.Service;

public class HomeActivity extends BaseApp implements HomeView {

    private RecyclerView list;
    @Inject
    public Service service;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        HomePresenter presenter = new HomePresenter(service, this);
        presenter.getCityList();
    }

    public  void renderView(){
        setContentView(R.layout.item_home);
        list = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getityListSuccess(CityListResponse cityListResponse) {

        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), cityListResponse.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CityListData Item) {
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        list.setAdapter(adapter);

    }
}