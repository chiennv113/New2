package com.example.anew.Retrofit;

import com.example.anew.Model.ModelAdd;
import com.example.anew.Model.ModelAddCallAndCustomerNew;
import com.example.anew.Model.ModelAddRemind;
import com.example.anew.Model.ModelCustomeFeelNew;
import com.example.anew.Model.ModelDeleteCall;
import com.example.anew.Model.ModelDeleteRemind;
import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCall;
import com.example.anew.Model.ModelListPhoneCallRemind.ModelListPhoneCallRemind;
import com.example.anew.Model.ModelLoadAllProduct;
import com.example.anew.Model.ModelLoadCity;
import com.example.anew.Model.ModelLoadCustomerType;
import com.example.anew.Model.ModelLoadObjCustomer;
import com.example.anew.Model.ModelLoadSourceCustomer;
import com.example.anew.Model.ModelLogin.Login;
import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.Model.ModelTKCuocGoiAdmin.ModelThongKeCuocGoiAdmin;
import com.example.anew.Model.ModelTKCuocgoiBanThan.ModelThongKeCuocGoiBanThan;
import com.example.anew.Model.ModelTKTheoDoHaiLongKH.ModelThongKeTheoDoHaiLongCuaKhachAdmin;
import com.example.anew.Model.ModelTKTheoNV.ModelThongKeTheoNVAdmin;
import com.example.anew.Model.ModelTKTheoTatcaDoHaiLong.ModelThongKeTheoTatCaDoHaiLongCuaKhachAdmin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceRetrofit {
    //    https://crm.phanmemninja.com/api/userapi

    //Login
    @POST("api/userapi")
    Call<Login> createUser(@Body Login login);


    //Search
    @POST("api/userapi")
    Call<Search> search(@Query("infocheck") String info,
                        @Query("option") String option,
                        @Header("Cookie") String cookie);

    //https://crm.phanmemninja.com/api/phoneapi

    //get Customer Feel
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<List<ModelCustomeFeelNew>> getFeel(@Field("option") String option);


    //Add Call
    @POST("api/phoneapi")
    Call<ModelAdd> add(@Query("option") String option,
                       @Query("customer_id") int customer_id,
                       @Query("content") String content,
                       @Query("customer_feel") String customer_feel,
                       @Header("Cookie") String cookie,
                       @Header("Content-Type") String type);


    //Load City
    @POST("api/userapi")
    @FormUrlEncoded
    Call<List<ModelLoadCity>> getCity(@Field("option") String option,
                                      @Header("Cookie") String cookie);

    //Load Customer type
    @POST("api/userapi")
    @FormUrlEncoded
    Call<List<ModelLoadCustomerType>> getCustomerType(@Field("option") String option,
                                                      @Header("Cookie") String cookie);

    //Load Obj Customer
    @POST("api/userapi")
    @FormUrlEncoded
    Call<List<ModelLoadObjCustomer>> getObjCustomer(@Field("option") String option,
                                                    @Header("Cookie") String cookie);

    //Load Source Customer
    @POST("api/userapi")
    @FormUrlEncoded
    Call<List<ModelLoadSourceCustomer>> getSourceCustomer(@Field("option") String option,
                                                          @Header("Cookie") String cookie);

    //Load All product
    @POST("api/productapi")
    @FormUrlEncoded
    Call<List<ModelLoadAllProduct>> getAllProduct(@Field("option") String option,
                                                  @Header("Cookie") String cookie);

    //Add Call and Customer new
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<ModelAddCallAndCustomerNew> addCallAndCus(@Field("option") String option,
                                                   @Field("phone1") String phone1,
                                                   @Field("address") String address,
                                                   @Field("birthday") String birthday,
                                                   @Field("city") String city,
                                                   @Field("content") String content,
                                                   @Field("customer_base") String customer_base,
                                                   @Field("customer_feel") String customer_feel,
                                                   @Field("customer_type") String customer_type,
                                                   @Field("email") String email,
                                                   @Field("fullname") String fullname,
                                                   @Field("note") String note,
                                                   @Field("skype") String skype,
                                                   @Field("software_needed") String software_needed,
                                                   @Field("status") String status,
                                                   @Field("zalo") String zalo,
                                                   @Header("Cookie") String cookie);

    //ListPhoneCall
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<List<ModelListPhoneCall>> getListPhoneCall(@Field("option") String option,
                                                    @Field("date_start") long date_start,
                                                    @Field("date_end") long date_end,
                                                    @Header("Cookie") String cookie);

    //Delete Call
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<ModelDeleteCall> del(@Field("option") String option,
                              @Field("id") int id,
                              @Header("Cookie") String cookie);

    //Add remind call
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<ModelAddRemind> addRemind(@Field("option") String option,
                                   @Field("content") String content,
                                   @Field("time") long time,
                                   @Field("call_to") int id,
                                   @Header("Cookie") String cookie);

    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<List<ModelListPhoneCallRemind>> getListRemind(@Field("option") String option,
                                                       @Header("Cookie") String cookie);

    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<ModelDeleteRemind> deleteRemind(@Field("option") String option,
                                         @Field("id") int id,
                                         @Header("Cookie") String cookie);

    //Thống kê theo tất cả độ hài lòng của khách
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<List<ModelThongKeTheoTatCaDoHaiLongCuaKhachAdmin>> tkTheoTatCaDoHaiLongCuaKhach(@Field("option") String option,
                                                                                         @Field("start") long start,
                                                                                         @Field("end") long end,
                                                                                         @Header("cookie") String cookie);

    //Thống kê theo độ hài lòng của khách
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<List<ModelThongKeTheoDoHaiLongCuaKhachAdmin>> tkTheoDoHaiLongCuaKhach(@Field("option") String option,
                                                                               @Field("start") long start,
                                                                               @Field("end") long end,
                                                                               @Field("customer_feel") int id,
                                                                               @Header("cookie") String cookie);

    //Thống kê theo nhân viên
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<List<ModelThongKeTheoNVAdmin>> tkTheoNV(@Field("option") String option,
                                                 @Field("start") long start,
                                                 @Field("end") long end,
                                                 @Header("cookie") String cookie);

    //Thống kê theo cuộc gọi
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<List<ModelThongKeCuocGoiAdmin>> tkTheoCuocGoi(@Field("option") String option,
                                                       @Field("start") long start,
                                                       @Field("end") long end,
                                                       @Header("cookie") String cookie);

    //Thống kê theo cuộc gọi bản thân
    @POST("api/phoneapi")
    @FormUrlEncoded
    Call<List<ModelThongKeCuocGoiBanThan>> tkTheoCuocGoiBanThan(@Field("option") String option,
                                                                @Field("start") long start,
                                                                @Field("end") long end,
                                                                @Header("cookie") String cookie);
}
