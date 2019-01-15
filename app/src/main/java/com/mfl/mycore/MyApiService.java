package com.mfl.mycore;

import com.mfl.core.net.HttpResult;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiService {

        @GET("OBDVECSP/appService/appTest")
        Flowable<HttpResult<UserBean>> sendVerify(@Query("account") String account,
                                                  @Query("password") String pwd);



//    //=============================================发送验证码===========================================
//    @GET("appIndividual/sendAuthCode.do")
////    @GET("OBDVECSP/appIndividual/sendAuthCode.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<String>> sendVerify(@Query("phoneNum") String phoneNum);
//
//
//
//    //=============================================登录===========================================
//    @GET("appIndividual/sendUser.do")
////    @GET("OBDVECSP/appIndividual/sendUser.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<UserInfo>> login(@Query("phoneNum") String phoneNum);//
//
//
//
//    //=============================================首页车辆列表带最近一次记录============================
//    @GET("appIndividual/preReportRecord.do")
////    @GET("OBDVECSP/appIndividual/preReportRecord.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<List<CarListHistory>>> getAllCarPreReport(@Query("userId") String userId);//
//
//    //=============================================app检查更新===========================================
//    @GET("appIndividual/appVersionUpdate.do")
////    @GET("OBDVECSP/appIndividual/appVersionUpdate.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<String>> checkAppUpdate(@Query("version") String versionName);
//
//
//
//    //=============================================db检查更新===========================================
//    @GET("appIndividual/dataUpdateOBD.do")
////    @GET("OBDVECSP/appIndividual/dataUpdateOBD.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<DataOfDBRequestBean>> checkDBUpdate(@Query("PID_db_version") String PIDversion,
//                                                            @Query("PIDS_db_version") String PIDsversion,
//                                                            @Query("faultLibrary_db_version") String vehicleFailureversion
//    );
//
//
//
//
//    //=============================================查询已绑定的车辆===========================================
//    @GET("appIndividual/selectVehicelList.do")
////    @GET("OBDVECSP/appIndividual/selectVehicelList.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<List<Car>>> queryBondedCar(@Query("userId") String userId);
//
//
//    //=============================================绑定车辆==========================================
//    @GET("appIndividual/boundVicle.do")
////    @GET("OBDVECSP/appIndividual/boundVicle.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<Car>> bondCar(@Query("userId") String userId,
//                                      @Query("carNumber") String carNum,
//                                      @Query("carType") String carType,
//                                      @Query("fuleType") String fuelType,
//                                      @Query("imgPath") String picUrl
//    );
//
//    @Headers({"url:normal"})
//    @POST("appIndividual/boundVicle.do")
////    @POST("OBDVECSP/appIndividual/boundVicle.do")
//    Flowable<HttpResult<Car>> bondCar(@Body Car appVehicle);
//
//    //=============================================查询是否可以绑定盒子==========================================
//    @GET("appIndividual/isBoundObdBox.do")
////    @GET("OBDVECSP/appIndividual/isBoundObdBox.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<String>> queryBondedBox(@Query("userId") String userId);//
//
//
//    //=============================================绑定盒子==========================================
//    @GET("appIndividual/boundObdBox.do")
////    @GET("OBDVECSP/appIndividual/boundObdBox.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<String>> bondBox(@Query("userId") String userId, @Query("boxNum") String boxId);//
//
//
//
//    //=============================================意见反馈==========================================
//    @GET("appIndividual/saveIdeaObd.do")
////    @GET("OBDVECSP/appIndividual/saveIdeaObd.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<String>> suggest(@Query("userId") String userId, @Query("ideaCont") String suggestContent);//
//
//    //=============================================提交报告==========================================
//    // 上传单个文件
//    @Multipart
//    @POST("appService/uploadProtocolReports.do")
////    @POST("OBDVECSP/appService/uploadProtocolReports.do")
//    Flowable<HttpResult<String>> uploadReport(@Part MultipartBody.Part file);
//
//    //=============================================历史记录简要==========================================
//    @GET("appIndividual/carHistoryList.do")
////    @GET("OBDVECSP/appIndividual/carHistoryList.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<List<HistoryHelper>>> getSimpleHistory(@Query("userId") String userId);//
//    //=============================================历史记录详细==========================================
//    @GET("appIndividual/historyReportRecord.do")
////    @GET("OBDVECSP/appIndividual/historyReportRecord.do")
//    @Headers({"url:normal"})
//    Flowable<HttpResult<String>> getDetailHistory(@Query("reportId") String reportId);//
//
//
//
//
//  //==========================================查询所有车辆品牌===========================================
//    @GET("car/brand")
//    @Headers({"url:car"})
//    Flowable<HttpResult<List<CarBrand>>> getBrand();
//
//    //=============================================查询某品牌下所有车型==========================================
//    @GET("car/carlist")
//    @Headers({"url:car"})
//    Flowable<HttpResult<List<CarType>>> getCarTypeByBrand(@Query("parentid") String id);







//    @GET("/car/brand")
//    @Headers({"url:car"})
//    Flowable<HttpResult<List<CarBrand>>> getType();

//    @GET("book/search")
//    Flowable<String> getDemo(@Query("q") String name,
//                             @Query("tag") String tag
//    );
//
//    @GET("book/search")
//    Flowable<String> getDemo(@QueryMap Map<String, String> options);
//
//
//    @GET("book/{id}")
//    Flowable<HttpResult<String>> getWithUrlDemo(@Path("id") String id);
//
//
//    @POST("downLoadAPP.do")
//    Flowable<String> checkUpdate(@Body RequestBody requestBody);  //请求上传的为json串
//
//
//    @FormUrlEncoded
//    @POST("downLoadAPP.do")  //检查更新
//    Flowable<String> checkUpdate(@Field("appVersionStr") String versionName);
//
//    @FormUrlEncoded
//    @POST("book/reviews")
//    Flowable<String>addReviews(@FieldMap Map<String, String> fields);
//
//
//    @FormUrlEncoded
//    @POST("book/reviews")
//    Flowable<String> addReviews(@Body Reviews reviews);
//
//      class Reviews {
//        public String book;
//        public String title;
//        public String content;
//        public String rating;
//    }
//
//
//    // 上传单个文件
//    @Multipart
//    @POST("upload")
//    Flowable<String> uploadFile(
//
//            @Part MultipartBody.Part file);
//
//    // 上传多个文件
//    @Multipart
//    @POST("upload")
//    Flowable<String> uploadMultipleFiles(
//
//            @Part MultipartBody.Part file1,
//            @Part MultipartBody.Part file2);
//
//    @Multipart
//    @POST("customerApp/uploadPic")
////  上传头像
//    Observable<HttpResult<String>> uploadHead(@Part MultipartBody.Part file);
}
