package com.khiancode.android.farmtogetherguide.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.khiancode.android.farmtogetherguide.MainActivity;
import com.khiancode.android.farmtogetherguide.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SeasonSettingFragment extends Fragment {

    @BindView(R.id.panelCreate)
    LinearLayout panelCreate;
    Unbinder unbinder;

    public SeasonSettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_season_setting, container, false);
        unbinder = ButterKnife.bind(this, view);

        panelCreate.setVisibility(View.INVISIBLE);
        startView();

        return view;
    }

    private void startView() {
        ((MainActivity) getActivity()).createSoundOpenPage(getActivity());

        panelCreate.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.BounceIn)
                .duration(600)
                .playOn(panelCreate);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
