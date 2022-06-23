package com.example.editandroid.retrofit;

import com.example.editandroid.model.LoaiSpModel;
import com.example.editandroid.model.SanPhamMoiModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiBanHang {

    @GET("getloaisp.php")
    Observable<LoaiSpModel> getloaiSp();
    @GET("getspmoi.php")
    Observable<SanPhamMoiModel> getSpMoi();

}
