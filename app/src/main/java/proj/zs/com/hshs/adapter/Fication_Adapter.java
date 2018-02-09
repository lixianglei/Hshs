package proj.zs.com.hshs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import proj.zs.com.hshs.R;

/**
 * Created by zsz on 2018/2/9.
 */

public class Fication_Adapter extends RecyclerView.Adapter<Fication_Adapter.ViewHolder> {
    private Context mContext;
    private List<String> dataList = new ArrayList<>();


    public void addAllData(List<String> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public Fication_Adapter(Context context) {
        mContext = context;
    }

    @Override
    public Fication_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fication_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Fication_Adapter.ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.more_book);
        }
    }

}
