package ado.com.ember.rxretrofittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  
  private static final String BASE_URL = "https://api.learn2crack.com/";
  private RecyclerView mRecyclerView;
  private CompositeDisposable mCompositeDisposable;
  private DataAdapter mAdapter;
  private ArrayList<AndroidVersion> mAndroidAVersionList;
  
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mCompositeDisposable = new CompositeDisposable();
    initRecyclerView();
    requestJson();
  }
  
  @Override
  protected void onDestroy() {
    super.onDestroy();
    if(mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.dispose();
    }
  }
  
  private void initRecyclerView() {
    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    mRecyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    mRecyclerView.setLayoutManager(layoutManager);
  }
  
  
  private void requestJson() {
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);
    
    mCompositeDisposable.add(apiService.findAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::initAdapter, this::showError)
    );
  
  }
  
  private void initAdapter(List<AndroidVersion> androidVersions) {
    mAndroidAVersionList = (ArrayList<AndroidVersion>) androidVersions;
    mAdapter = new DataAdapter(new ArrayList<>(androidVersions));
    mRecyclerView.setAdapter(mAdapter);
  }
  
  private void showError(Throwable throwable) {
    Toast.makeText(this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    throwable.printStackTrace();
  }
}
