package com.khiancode.android.farmtogetherguide.fragment;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.khiancode.android.farmtogetherguide.MainActivity;
import com.khiancode.android.farmtogetherguide.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingFragment extends Fragment {


    @BindView(R.id.panelCreate)
    LinearLayout panelCreate;
    Unbinder unbinder;
    @BindView(R.id.btnMenu1)
    FrameLayout btnMenu1;
    @BindView(R.id.btnMenu2)
    FrameLayout btnMenu2;
    @BindView(R.id.btnMenu3)
    FrameLayout btnMenu3;
    @BindView(R.id.btnBack)
    FrameLayout btnBack;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
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

    @OnClick(R.id.btnBack)
    public void onViewClicked() {
    }

    @OnClick({R.id.btnMenu1, R.id.btnMenu2, R.id.btnMenu3, R.id.btnBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnMenu1:
                setFrame(new SeasonSettingFragment(),31);
                break;
            case R.id.btnMenu2:
               setFrame(new NotificationSettingFragment(),32);
                break;
            case R.id.btnMenu3:
                setFrame(new SoundSettingFragment(),33);
                break;
            case R.id.btnBack:

                break;
        }
    }

    private void setFrame(Fragment frame,int stat) {
        MediaPlayer.create(getActivity(), R.raw.widget_wood_select_3).start();
        ((MainActivity) getActivity()).setStat(stat);
        ((MainActivity) getActivity()).setFram(frame);
    }
}
