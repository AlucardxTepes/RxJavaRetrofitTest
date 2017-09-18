package ado.com.ember.rxretrofittest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Alucard on 18-Sep-17.
 */

public interface ApiService {
  
  @GET("android/jsonarray")
  Observable<List<AndroidVersion>> findAll();
}
