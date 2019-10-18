package com.example.anew.Retrofit;

import com.example.anew.Model.ModelAdd;
import com.example.anew.Model.ModelAddCallAndCustomerNew;
import com.example.anew.Model.ModelCustomeFeelNew;
import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCall;
import com.example.anew.Model.ModelLoadAllProduct;
import com.example.anew.Model.ModelLoadCity;
import com.example.anew.Model.ModelLoadCustomerType;
import com.example.anew.Model.ModelLoadObjCustomer;
import com.example.anew.Model.ModelLoadSourceCustomer;
import com.example.anew.Model.ModelLogin.Login;
import com.example.anew.Model.ModelSearchCu.Search;

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
                        @Header("Cookie") String cookie,
                        @Header("Content-Type") String content);

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
                                              @Field("date-end") long date_end,
                                              @Header("Cookie") String cookie);

}
