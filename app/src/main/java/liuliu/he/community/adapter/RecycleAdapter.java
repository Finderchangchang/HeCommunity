package liuliu.he.community.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liuliu on 2015/11/21   11:35
 * 通用的Recycle的适配器
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public abstract class RecycleAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {
    private Context mContext;
    List mDatas;
    int mLayoutId;
    RecycleViewHolder mHolder;

    public RecycleAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mHolder = new RecycleViewHolder(LayoutInflater.from(
                mContext).inflate(mLayoutId, parent, false), mContext);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        convert(holder, mDatas, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(RecycleViewHolder holder, List<T> t, int position);
}
