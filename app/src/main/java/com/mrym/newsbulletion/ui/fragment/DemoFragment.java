package com.mrym.newsbulletion.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrym.newsbulletion.R;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DemoFragment extends Fragment {

    @Bind(R.id.fragment_demo_rl)
    XRecyclerView fragmentDemoRl;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
//    TextView title = (TextView) view.findViewById(R.id.item_title);
//    title.setText(String.valueOf(position));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
