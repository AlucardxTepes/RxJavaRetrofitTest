package ado.com.ember.rxretrofittest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alucard on 18-Sep-17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
  
  private ArrayList<AndroidVersion> mAndroidVersionList;
  
  public DataAdapter(ArrayList<AndroidVersion> androidList) {
    mAndroidVersionList = androidList;
  }
  
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
    return new ViewHolder(view);
  }
  
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.mTvName.setText(mAndroidVersionList.get(position).getName());
    holder.mTvVersion.setText(mAndroidVersionList.get(position).getVer());
    holder.mTvApi.setText(mAndroidVersionList.get(position).getApi());
  }
  
  @Override
  public int getItemCount() {
    return mAndroidVersionList.size();
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder{
    
    private TextView mTvName,mTvVersion,mTvApi;
    public ViewHolder(View view) {
      super(view);
      mTvName = view.findViewById(R.id.tv_name);
      mTvVersion = view.findViewById(R.id.tv_version);
      mTvApi = view.findViewById(R.id.tv_api_level);
    }
  }
}