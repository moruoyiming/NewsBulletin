package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.mrym.newsbulletion.utils.ViewHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：SigmaClassBoard
 * 类描述：
 * 创建人：jianruilin
 * 创建时间：12/26/2016 11:31 AM
 * 修改备注：
 */
public abstract class BaseRecyclerViewAdapter<E> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;

    private int mDuration = 300;

    private Interpolator mInterpolator = new LinearInterpolator();

    private int mLastPosition = -1;

    private boolean isFirstOnly = true;

    protected List<E> mList = new ArrayList<>();

    private Map<Integer, onInternalClickListener<E>> canClickItem;

    public BaseRecyclerViewAdapter(Context context) {

        this.mContext = context;
    }

    public List<E> getmList() {
        return mList;
    }

    public void setmList(List<E> list) throws Exception {
        if (list == null) {
            return;
        }
        if (list.size() > 0) {
            this.mList.clear();
            this.mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void add(E e) throws Exception {
        this.mList.add(0, e);
        Log.d("BaseRecycler", "mList:" + mList.size());
        notifyItemInserted(0);
    }

    public void addLast(E e) throws Exception {
        this.mList.add(e);
        notifyItemInserted(mList.size() - 1);
    }

    public void update(E e, int fromPosition, int toPosition) throws Exception {
        this.mList.remove(fromPosition);
        this.mList.add(toPosition, e);
        if (fromPosition == toPosition) {
            notifyItemChanged(fromPosition);
        } else {
            notifyItemRemoved(fromPosition);
            notifyItemInserted(toPosition);
        }
        //notifyItemRangeChanged(fromPosition, toPosition);
    }

    public void update(E e, int fromPosition) throws Exception {
        update(e, fromPosition, fromPosition);
    }

    public void update(E e) throws Exception {
        Log.d("tag", "列表：" + mList.toString());
        int fromPosition = this.mList.indexOf(e);
        update(e, fromPosition);
    }

    public void updateLast(E e) throws Exception {
//        int fromPosition = this.mList.indexOf(e);
        int num = mList.size() - 1;
        update(e, num, num);
    }

    public void remove(E e) throws Exception {
        int position = mList.indexOf(e);
        remove(position);
    }

    public void remove(int position) throws Exception {
        this.mList.remove(position);
        notifyItemRemoved(position);
    }


    public void addAll(List<E> list) throws Exception {
        if (list == null) {
            return;
        }
        if (list.size() > 0) {
            this.mList.clear();
            this.mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            if (holder != null) {
                Log.d("BaseRecyclerView", "position---------->" + position + "mList length:" + mList.size());
                addInternalClickListener(holder.itemView, position, mList.get(position));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    private void addInternalClickListener(final View itemV, final Integer position, final E valuesMap) {
        try {
            if (canClickItem != null) {
                for (Integer key : canClickItem.keySet()) {
                    View inView = itemV.findViewById(key);
                    final onInternalClickListener<E> listener = canClickItem.get(key);
                    if (inView != null && listener != null) {
                        inView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.OnClickListener(itemV, v, position, valuesMap);
                            }
                        });

                        inView.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                listener.OnLongClickListener(itemV, v, position,
                                        valuesMap);
                                return true;
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnInViewClickListener(Integer key, onInternalClickListener<E> onClickListener) {
        try {
            if (canClickItem == null)
                canClickItem = new HashMap<>();
            canClickItem.put(key, onClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface onInternalClickListener<T> {
        void OnClickListener(View parentV, View v, Integer position,
                             T values);

        void OnLongClickListener(View parentV, View v, Integer position,
                                 T values);
    }

    public static class onInternalClickListenerImpl<T> implements onInternalClickListener<T> {
        @Override
        public void OnClickListener(View parentV, View v, Integer position, T values) {

        }

        @Override
        public void OnLongClickListener(View parentV, View v, Integer position, T values) {

        }
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setStartPosition(int start) {
        mLastPosition = start;
    }

    public void setFirstOnly(boolean firstOnly) {
        isFirstOnly = firstOnly;
    }

    protected void animate(RecyclerView.ViewHolder holder, int position) {
        try {
            if (!isFirstOnly || position > mLastPosition) {
                for (Animator anim : getAnimators(holder.itemView)) {
                    anim.setDuration(mDuration).start();
                    anim.setInterpolator(mInterpolator);

                }
                mLastPosition = position;
            } else {
                ViewHelper.clear(holder.itemView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract Animator[] getAnimators(View view);
}
