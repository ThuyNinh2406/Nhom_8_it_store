package com.example.editandroid.adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.editandroid.R;
import com.example.editandroid.model.Loaisp;
import com.example.editandroid.model.SanPhamMoi;
import com.example.editandroid.model.SanPhamMoiModel;
import com.example.editandroid.retrofit.ApiBanHang;
import com.example.editandroid.retrofit.RetrofitClient;
import com.example.editandroid.ultil.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    LoaispAdapter loaispAdapter;
    List<Loaisp> mangloaisp;
   CompositeDisposable compositeDisposable = new CompositeDisposable();
   ApiBanHang apiBanHang;
   List<SanPhamMoi> mangSpMoi;
   SanPhamMoiAdapter spAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanHang= RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);

        AnhXa();
        ActionBar();

        if(isConnected(this)){
            ActionViewFlipper();
        getLoaiSanPham();
            getSpMoi();
        }
        else{
            Toast.makeText(getApplicationContext(),"không có kết nối internet", Toast.LENGTH_SHORT).show();

        }

    }

    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if(sanPhamMoiModel.isSuccess()){
                                mangSpMoi = sanPhamMoiModel.getResult();
                                spAdapter = new SanPhamMoiAdapter(getApplicationContext(),mangSpMoi);
                                recyclerViewmanhinhchinh.setAdapter(spAdapter);
                            }

                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không thể kết nối với server"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));

    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getloaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if(loaiSpModel.isSuccess()){
                                mangloaisp = loaiSpModel.getResult();
                                loaispAdapter = new LoaispAdapter(getApplicationContext(),mangloaisp);
                                listViewmanhinhchinh.setAdapter(loaispAdapter);
                            }
                        }
                ));

    }


    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://images.fpt.shop/unsafe/fit-in/1200x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2022/4/30/637869589738830187_F-C1_1200x300.png");
        mangquangcao.add("https://images.fpt.shop/unsafe/fit-in/1200x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2022/5/30/637895318671566801_F-C1_1200x300.png");
        mangquangcao.add("https://images.fpt.shop/unsafe/fit-in/1200x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2022/5/26/637891757782724394_F-C1_1200x300.png");
        mangquangcao.add("https://images.fpt.shop/unsafe/fit-in/1200x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2022/4/30/637869592058369899_F-C1_1200x300.png");
        mangquangcao.add("https://images.fpt.shop/unsafe/fit-in/1200x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2022/4/25/637864735735607617_F-C1_1200x300.png");
        mangquangcao.add("https://images.fpt.shop/unsafe/fit-in/1200x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2022/5/26/637891757782724394_F-C1_1200x300.png");
        mangquangcao.add("https://images.fpt.shop/unsafe/fit-in/1200x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2022/5/30/637895318671566801_F-C1_1200x300.png");
        mangquangcao.add("https://images.fpt.shop/unsafe/fit-in/1200x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/12/14/637750929820398764_F-C1_1200x300.png");


        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView= new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper= findViewById(R.id.viewlipper);
        recyclerViewmanhinhchinh= findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(this,2);
        recyclerViewmanhinhchinh.setLayoutManager(layoutManager);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        navigationView= findViewById(R.id.navigationview);
        listViewmanhinhchinh= findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = findViewById(R.id.drawerlayout);
        //Khởi tạo list
        mangloaisp = new ArrayList<>();
        mangSpMoi = new ArrayList<>();
        //khởi tạo adapter
        //loaispAdapter = new LoaispAdapter(getApplicationContext(),mangloaisp);
        //listViewmanhinhchinh.setAdapter(loaispAdapter);
    }
    //Kết nối để lấy dữ liệu
    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if((wifi != null && wifi.isConnected()) ||(mobile!= null && mobile.isConnected())){
            return  true;
        }
        else {
            return false;
        }
    }
    @Override
    protected  void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }
}