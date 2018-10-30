package com.khiancode.android.farmtogetherguide.fragment;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.khiancode.android.farmtogetherguide.MainActivity;
import com.khiancode.android.farmtogetherguide.R;
import com.khiancode.android.farmtogetherguide.adapter.AdapterShopList;
import com.khiancode.android.farmtogetherguide.model.ItemShopModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MenuOneFragment extends Fragment {


    @BindView(R.id.tabCrop)
    FrameLayout tabCrop;
    @BindView(R.id.tabTree)
    FrameLayout tabTree;
    @BindView(R.id.tabAnimal)
    FrameLayout tabAnimal;
    @BindView(R.id.tabPond)
    FrameLayout tabPond;
    @BindView(R.id.tabFlower)
    FrameLayout tabFlower;
    @BindView(R.id.btnBack)
    FrameLayout btnBack;
    Unbinder unbinder;
    @BindView(R.id.panelCreate)
    FrameLayout panelCreate;
    @BindView(R.id.imgCrop)
    ImageView imgCrop;
    @BindView(R.id.imgTree)
    ImageView imgTree;
    @BindView(R.id.imgAnimal)
    ImageView imgAnimal;
    @BindView(R.id.imgPond)
    ImageView imgPond;
    @BindView(R.id.imgFlower)
    ImageView imgFlower;
    @BindView(R.id.txtTabName)
    TextView txtTabName;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtVersion)
    TextView txtVersion;

    int viewLast;
    AdapterShopList adapter;

    public MenuOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_one, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setHasFixedSize(true);

        panelCreate.setVisibility(View.INVISIBLE);

        startView();

        setVersionName();

        return view;
    }

    private void setVersionName() {
        try {
            PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            String version = pInfo.versionName;
            txtVersion.setText("version "+version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void startView() {
        ((MainActivity) getActivity()).createSoundOpenPage(getActivity());

        panelCreate.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.BounceIn)
                .duration(600)
                .playOn(panelCreate);

        setShoptList(getCropData());

    }

    private void setShoptList(final ArrayList<ItemShopModel> model) {

        adapter = new AdapterShopList(getActivity(), model);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterShopList.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ((MainActivity) getActivity()).playSound(getActivity(), R.raw.widget_shop_select_3);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tabCrop, R.id.tabTree, R.id.tabAnimal, R.id.tabPond, R.id.tabFlower, R.id.btnBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tabCrop:
                if (viewLast != R.id.tabCrop) {
                    clickMenuChange(tabCrop, imgCrop);
                    txtTabName.setText("ผัก");
                    setShoptList(getCropData());
                }
                break;
            case R.id.tabTree:
                if (viewLast != R.id.tabTree) {
                    clickMenuChange(tabTree, imgTree);
                    txtTabName.setText("ต้นไม้");
                    setShoptList(getTreeData());
                }
                break;
            case R.id.tabAnimal:
                if (viewLast != R.id.tabAnimal) {
                    clickMenuChange(tabAnimal, imgAnimal);
                    txtTabName.setText("สัตว์");
                    setShoptList(getAnimalData());
                }
                break;
            case R.id.tabPond:
                if (viewLast != R.id.tabPond) {
                    clickMenuChange(tabPond, imgPond);
                    txtTabName.setText("ปลา");
                    setShoptList(getPondData());
                }
                break;
            case R.id.tabFlower:
                if (viewLast != R.id.tabFlower) {
                    clickMenuChange(tabFlower, imgFlower);
                    txtTabName.setText("ดอกไม้");
                    setShoptList(getFlowerData());
                }
                break;
            case R.id.btnBack:
                ((MainActivity) getActivity()).setFram(new HomeFragment());
                ((MainActivity) getActivity()).setStat(0);
                break;
        }
    }

    private void clickMenuChange(FrameLayout view, ImageView img) {

        tabCrop.setBackground(getResources().getDrawable(R.drawable.button_tab));
        tabTree.setBackground(getResources().getDrawable(R.drawable.button_tab));
        tabAnimal.setBackground(getResources().getDrawable(R.drawable.button_tab));
        tabPond.setBackground(getResources().getDrawable(R.drawable.button_tab));
        tabFlower.setBackground(getResources().getDrawable(R.drawable.button_tab));

        ((MainActivity) getActivity()).playSound(getActivity(), R.raw.widget_shop_select_3);
        view.setBackground(getResources().getDrawable(R.drawable.button_tab_selected));

        YoYo.with(Techniques.ZoomIn)
                .duration(200)
                .playOn(img);
        viewLast = view.getId();
    }

    private ArrayList<ItemShopModel> getCropData() {
        ArrayList<ItemShopModel> shopModels = new ArrayList<ItemShopModel>();
        shopModels.add(new ItemShopModel("", "crop/Agave.png"));
        shopModels.add(new ItemShopModel("", "crop/AloeVera.png"));
        shopModels.add(new ItemShopModel("", "crop/Artichoke.png"));
        shopModels.add(new ItemShopModel("", "crop/Asparagus.png"));
        shopModels.add(new ItemShopModel("", "crop/Barley.png"));
        shopModels.add(new ItemShopModel("", "crop/Bean.png"));
        shopModels.add(new ItemShopModel("", "crop/Beet.png"));
        shopModels.add(new ItemShopModel("", "crop/Blueberry.png"));
        shopModels.add(new ItemShopModel("", "crop/BlueGrape.png"));
        shopModels.add(new ItemShopModel("", "crop/Broccoli.png"));
        shopModels.add(new ItemShopModel("", "crop/Cabbage.png"));
        shopModels.add(new ItemShopModel("", "crop/Carrot.png"));
        shopModels.add(new ItemShopModel("", "crop/Cauliflower.png"));
        shopModels.add(new ItemShopModel("", "crop/CauliflowerCheddar.png"));
        shopModels.add(new ItemShopModel("", "crop/CauliflowerGraffiti.png"));
        shopModels.add(new ItemShopModel("", "crop/Chard.png"));
        shopModels.add(new ItemShopModel("", "crop/Chili.png"));
        shopModels.add(new ItemShopModel("", "crop/Corn.png"));
        shopModels.add(new ItemShopModel("", "crop/Cotton.png"));
        shopModels.add(new ItemShopModel("", "crop/CottonCandy.png"));
        shopModels.add(new ItemShopModel("", "crop/CrookneckPumpkin.png"));
        shopModels.add(new ItemShopModel("", "crop/EasterEgg.png"));
        shopModels.add(new ItemShopModel("", "crop/EggPlant.png"));
        shopModels.add(new ItemShopModel("", "crop/Garlic.png"));
        shopModels.add(new ItemShopModel("", "crop/Ginger.png"));
        shopModels.add(new ItemShopModel("", "crop/GinkgoBiloba.png"));
        shopModels.add(new ItemShopModel("", "crop/GreenGrape.png"));
        shopModels.add(new ItemShopModel("", "crop/GreenPepper.png"));
        shopModels.add(new ItemShopModel("", "crop/Kiwi.png"));
        shopModels.add(new ItemShopModel("", "crop/Leek.png"));
        shopModels.add(new ItemShopModel("", "crop/Lettuce.png"));
        shopModels.add(new ItemShopModel("", "crop/LombardyCabagge.png"));
        shopModels.add(new ItemShopModel("", "crop/Melon.png"));
        shopModels.add(new ItemShopModel("", "crop/MushroomParasol.png"));
        shopModels.add(new ItemShopModel("", "crop/MushroomRedPine.png"));
        shopModels.add(new ItemShopModel("", "crop/MushroomShiitake.png"));
        shopModels.add(new ItemShopModel("", "crop/MushroomWhite.png"));
        shopModels.add(new ItemShopModel("", "crop/Mussel.png"));
        shopModels.add(new ItemShopModel("", "crop/Onion.png"));
        shopModels.add(new ItemShopModel("", "crop/OnionRed.png"));
        shopModels.add(new ItemShopModel("", "crop/Pea.png"));
        shopModels.add(new ItemShopModel("", "crop/Peanut.png"));
        shopModels.add(new ItemShopModel("", "crop/Pineapple.png"));
        shopModels.add(new ItemShopModel("", "crop/Potato.png"));
        shopModels.add(new ItemShopModel("", "crop/Pumpkin.png"));
        shopModels.add(new ItemShopModel("", "crop/PumpkinHalloween.png"));
        shopModels.add(new ItemShopModel("", "crop/Raspberry.png"));
        shopModels.add(new ItemShopModel("", "crop/RedGrape.png"));
        shopModels.add(new ItemShopModel("", "crop/RedPepper.png"));
        shopModels.add(new ItemShopModel("", "crop/Romanesco.png"));
        shopModels.add(new ItemShopModel("", "crop/Rye.png"));
        shopModels.add(new ItemShopModel("", "crop/Shallot.png"));
        shopModels.add(new ItemShopModel("", "crop/Spinach.png"));
        shopModels.add(new ItemShopModel("", "crop/Strawberry.png"));
        shopModels.add(new ItemShopModel("", "crop/Tomato.png"));
        shopModels.add(new ItemShopModel("", "crop/TomatoBeefsteak.png"));
        shopModels.add(new ItemShopModel("", "crop/TomatoDark.png"));
        shopModels.add(new ItemShopModel("", "crop/TomatoYellow.png"));
        shopModels.add(new ItemShopModel("", "crop/Turnip.png"));
        shopModels.add(new ItemShopModel("", "crop/Wasabi.png"));
        shopModels.add(new ItemShopModel("", "crop/Watermelon.png"));
        shopModels.add(new ItemShopModel("", "crop/Wheat.png"));
        shopModels.add(new ItemShopModel("", "crop/YellowPepper.png"));
        shopModels.add(new ItemShopModel("", "crop/Zucchini.png"));
        shopModels.add(new ItemShopModel("", "crop/ZucchiniYellow.png"));
        return shopModels;
    }

    private ArrayList<ItemShopModel> getTreeData() {
        ArrayList<ItemShopModel> shopModels = new ArrayList<ItemShopModel>();
        shopModels.add(new ItemShopModel("", "tree/AlmondTree.png"));
        shopModels.add(new ItemShopModel("", "tree/AppleTree.png"));
        shopModels.add(new ItemShopModel("", "tree/ApricotTree.png"));
        shopModels.add(new ItemShopModel("", "tree/Avocado.png"));
        shopModels.add(new ItemShopModel("", "tree/Bamboo.png"));
        shopModels.add(new ItemShopModel("", "tree/BananaTree.png"));
        shopModels.add(new ItemShopModel("", "tree/CacaoTree.png"));
        shopModels.add(new ItemShopModel("", "tree/CherryTree.png"));
        shopModels.add(new ItemShopModel("", "tree/CoconutTree.png"));
        shopModels.add(new ItemShopModel("", "tree/Coffea.png"));
        shopModels.add(new ItemShopModel("", "tree/Cypress.png"));
        shopModels.add(new ItemShopModel("", "tree/DatePalm.png"));
        shopModels.add(new ItemShopModel("", "tree/DeadTree.png"));
        shopModels.add(new ItemShopModel("", "tree/DragonFruit.png"));
        shopModels.add(new ItemShopModel("", "tree/FigTree.png"));
        shopModels.add(new ItemShopModel("", "tree/LemonTree.png"));
        shopModels.add(new ItemShopModel("", "tree/LimeTree.png"));
        shopModels.add(new ItemShopModel("", "tree/Lychee.png"));
        shopModels.add(new ItemShopModel("", "tree/Mango.png"));
        shopModels.add(new ItemShopModel("", "tree/Mulberry.png"));
        shopModels.add(new ItemShopModel("", "tree/NectarineTree.png"));
        shopModels.add(new ItemShopModel("", "tree/OliveTree.png"));
        shopModels.add(new ItemShopModel("", "tree/OrangeTree.png"));
        shopModels.add(new ItemShopModel("", "tree/Papaya.png"));
        shopModels.add(new ItemShopModel("", "tree/PeachTree.png"));
        shopModels.add(new ItemShopModel("", "tree/PearTree.png"));
        shopModels.add(new ItemShopModel("", "tree/PomegranateTree.png"));
        shopModels.add(new ItemShopModel("", "tree/QuinceTree.png"));
        shopModels.add(new ItemShopModel("", "tree/Rice.png"));
        shopModels.add(new ItemShopModel("", "tree/Tamarind.png"));
        shopModels.add(new ItemShopModel("", "tree/TangerineTree.png"));
        shopModels.add(new ItemShopModel("", "tree/WalnutTree.png"));
        shopModels.add(new ItemShopModel("", "tree/YuzuTree.png"));
        return shopModels;
    }

    private ArrayList<ItemShopModel> getAnimalData() {
        ArrayList<ItemShopModel> shopModels = new ArrayList<ItemShopModel>();
        shopModels.add(new ItemShopModel("", "animal/Alpaca.png"));
        shopModels.add(new ItemShopModel("", "animal/Alpaca2.png"));
        shopModels.add(new ItemShopModel("", "animal/Chicken.png"));
        shopModels.add(new ItemShopModel("", "animal/Chicken2.png"));
        shopModels.add(new ItemShopModel("", "animal/ChickenBarnevelder.png"));
        shopModels.add(new ItemShopModel("", "animal/ChickenJersey.png"));
        shopModels.add(new ItemShopModel("", "animal/ChickenPlymouth.png"));
        shopModels.add(new ItemShopModel("", "animal/Cow.png"));
        shopModels.add(new ItemShopModel("", "animal/Cow2.png"));
        shopModels.add(new ItemShopModel("", "animal/CowAyrshire.png"));
        shopModels.add(new ItemShopModel("", "animal/CowBelted.png"));
        shopModels.add(new ItemShopModel("", "animal/DonkeyAlbino.png"));
        shopModels.add(new ItemShopModel("", "animal/DonkeyAndalus.png"));
        shopModels.add(new ItemShopModel("", "animal/DonkeyMammoth.png"));
        shopModels.add(new ItemShopModel("", "animal/DonkeyMiranda.png"));
        shopModels.add(new ItemShopModel("", "animal/DonkeyProvence.png"));
        shopModels.add(new ItemShopModel("", "animal/Duck.png"));
        shopModels.add(new ItemShopModel("", "animal/Duck2.png"));
        shopModels.add(new ItemShopModel("", "animal/Duck3.png"));
        shopModels.add(new ItemShopModel("", "animal/GoatBlack.png"));
        shopModels.add(new ItemShopModel("", "animal/GoatBlack.png"));
        shopModels.add(new ItemShopModel("", "animal/GoatBrownSpotted.png"));
        shopModels.add(new ItemShopModel("", "animal/GoatWhite.png"));
        shopModels.add(new ItemShopModel("", "animal/GoatWhiteSpotted.png"));
        shopModels.add(new ItemShopModel("", "animal/HorseAkhalTeke.png"));
        shopModels.add(new ItemShopModel("", "animal/HorseAmericanPaint.png"));
        shopModels.add(new ItemShopModel("", "animal/HorseAppaloosa.png"));
        shopModels.add(new ItemShopModel("", "animal/HorseArabian.png"));
        shopModels.add(new ItemShopModel("", "animal/HorseFriesian.png"));
        shopModels.add(new ItemShopModel("", "animal/HorseHolsteiner.png"));
        shopModels.add(new ItemShopModel("", "animal/Pig.png"));
        shopModels.add(new ItemShopModel("", "animal/Pig2.png"));
        shopModels.add(new ItemShopModel("", "animal/PigBentheim.png"));
        shopModels.add(new ItemShopModel("", "animal/PigHampshire.png"));
        shopModels.add(new ItemShopModel("", "animal/PigHereford.png"));
        shopModels.add(new ItemShopModel("", "animal/Rabbit.png"));
        shopModels.add(new ItemShopModel("", "animal/RabbitBlue.png"));
        shopModels.add(new ItemShopModel("", "animal/RabbitBrown.png"));
        shopModels.add(new ItemShopModel("", "animal/RabbitGray.png"));
        shopModels.add(new ItemShopModel("", "animal/RabbitHarlequin.png"));
        shopModels.add(new ItemShopModel("", "animal/RabbitTricolor.png"));
        shopModels.add(new ItemShopModel("", "animal/SheepBlack.png"));
        shopModels.add(new ItemShopModel("", "animal/SheepBlackFace.png"));
        shopModels.add(new ItemShopModel("", "animal/SheepBrown.png"));
        shopModels.add(new ItemShopModel("", "animal/SheepSpots.png"));
        shopModels.add(new ItemShopModel("", "animal/SheepWhite.png"));
        return shopModels;
    }

    private ArrayList<ItemShopModel> getPondData() {
        ArrayList<ItemShopModel> shopModels = new ArrayList<ItemShopModel>();
        shopModels.add(new ItemShopModel("", "pond/Angelfish.png"));
        shopModels.add(new ItemShopModel("", "pond/Anglerfish.png"));
        shopModels.add(new ItemShopModel("", "pond/Blowfish.png"));
        shopModels.add(new ItemShopModel("", "pond/Carp.png"));
        shopModels.add(new ItemShopModel("", "pond/Clownfish.png"));
        shopModels.add(new ItemShopModel("", "pond/Discus.png"));
        shopModels.add(new ItemShopModel("", "pond/Eel.png"));
        shopModels.add(new ItemShopModel("", "pond/HammerheadShark.png"));
        shopModels.add(new ItemShopModel("", "pond/IridescentShark.png"));
        shopModels.add(new ItemShopModel("", "pond/KoiFish.png"));
        shopModels.add(new ItemShopModel("", "pond/Muraena.png"));
        shopModels.add(new ItemShopModel("", "pond/Oyster.png"));
        shopModels.add(new ItemShopModel("", "pond/Piranha.png"));
        shopModels.add(new ItemShopModel("", "pond/RedScorpionfish.png"));
        shopModels.add(new ItemShopModel("", "pond/Salmon.png"));
        shopModels.add(new ItemShopModel("", "pond/Swordfish.png"));
        shopModels.add(new ItemShopModel("", "pond/TropicalFish.png"));
        shopModels.add(new ItemShopModel("", "pond/Trout.png"));
        shopModels.add(new ItemShopModel("", "pond/Tuna.png"));
        return shopModels;
    }

    private ArrayList<ItemShopModel> getFlowerData() {
        ArrayList<ItemShopModel> shopModels = new ArrayList<ItemShopModel>();
        shopModels.add(new ItemShopModel("", "flower/Cactus.png"));
        shopModels.add(new ItemShopModel("", "flower/Daisy.png"));
        shopModels.add(new ItemShopModel("", "flower/DaisyOrange.png"));
        shopModels.add(new ItemShopModel("", "flower/DaisyPink.png"));
        shopModels.add(new ItemShopModel("", "flower/DaisyYellow.png"));
        shopModels.add(new ItemShopModel("", "flower/HydrangeaBlue.png"));
        shopModels.add(new ItemShopModel("", "flower/HydrangeaPink.png"));
        shopModels.add(new ItemShopModel("", "flower/HydrangeaPurple.png"));
        shopModels.add(new ItemShopModel("", "flower/HydrangeaWhite.png"));
        shopModels.add(new ItemShopModel("", "flower/HydrangeaYellow.png"));
        shopModels.add(new ItemShopModel("", "flower/Lavender.png"));
        shopModels.add(new ItemShopModel("", "flower/LotusFlower.png"));
        shopModels.add(new ItemShopModel("", "flower/LuckyClover.png"));
        shopModels.add(new ItemShopModel("", "flower/Oleander.png"));
        shopModels.add(new ItemShopModel("", "flower/PassionFruit.png"));
        shopModels.add(new ItemShopModel("", "flower/Poppy.png"));
        shopModels.add(new ItemShopModel("", "flower/PoppyYellow.png"));
        shopModels.add(new ItemShopModel("", "flower/Rose.png"));
        shopModels.add(new ItemShopModel("", "flower/RosePink.png"));
        shopModels.add(new ItemShopModel("", "flower/RoseWhite.png"));
        shopModels.add(new ItemShopModel("", "flower/RoseYellow.png"));
        shopModels.add(new ItemShopModel("", "flower/Sunflower.png"));
        shopModels.add(new ItemShopModel("", "flower/TulipBlack.png"));
        shopModels.add(new ItemShopModel("", "flower/TulipPink.png"));
        shopModels.add(new ItemShopModel("", "flower/TulipRed.png"));
        shopModels.add(new ItemShopModel("", "flower/TulipWhite.png"));
        shopModels.add(new ItemShopModel("", "flower/TulipYellow.png"));
        shopModels.add(new ItemShopModel("", "flower/Venus.png"));
        return shopModels;
    }
}
