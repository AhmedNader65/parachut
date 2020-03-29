package com.mrerror.parachut.NetWork;


import com.mrerror.parachut.Models.AllOffers.AllOffersModel;
import com.mrerror.parachut.Models.CategoryModel.CategoryModel;
import com.mrerror.parachut.Models.FastOrder.FastOrderModel;
import com.mrerror.parachut.Models.GetUserData;
import com.mrerror.parachut.Models.LogIn.UserLoginModel;
import com.mrerror.parachut.Models.OffersModel.OffersModel;
import com.mrerror.parachut.Models.Register.UserRegisterModel;
import com.mrerror.parachut.Models.SuperMarket.SuperMarketModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

//
//    @FormUrlEncoded
//    @POST("auth/register")
//    Call<Register> onRegester(
//            @Field("name") String name,
//            @Field("mobile") String mobile,
//            @Field("password") String password,
//            @Field("password_confirmation") String repassword
//    );
//
//


    @FormUrlEncoded
    @POST("fast")
    Call<FastOrderModel> onSendFastOrder(
            @Field("address") String address,
            @Field("mobile") String mobile,
            @Field("lat") String lat,
            @Field("long") String longetude,
            @Field("fast_order") String fast_order,
            @Header("Authorization") String Authorization);

    @GET("categories/mostcommon")
    Call<CategoryModel> onGetCategories(@Query("page") long page);


    @GET("categories")
    Call<com.mrerror.parachut.Models.AllCattegoryModel.CategoryModel> onGetAllCategories(@Query("page") long page);


    @GET("supermarket")
    Call<SuperMarketModel> onGetSuperMarkets(@Query("page") long page);

    @GET("allsupermarket")
    Call<com.mrerror.parachut.Models.AllSuperMarket.SuperMarketModel> onGetAllSuperMarkets(@Query("page") long page);


    @GET("products-offers/mostcommen")
    Call<OffersModel> onGetOffersModel(@Query("page") long page);

    @GET("products-offers")
    Call<AllOffersModel> onGetAllOffersModel(@Query("page") long page);


    //MH
    @GET("user")
    Call<GetUserData> onGetUserData(@Header("Authorization") String Authorization );

    @FormUrlEncoded
    @POST("update")
    Call<GetUserData> onUpdateUser(
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("email") String email,
            @Field("address") String address,
            @Field("lat") String lat,
            @Field("long") String longitude,
            @Header("Authorization") String Authorization
    );



    @FormUrlEncoded
    @POST("auth/register")
    Call<UserRegisterModel> onRegester(
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("address") String address,
            @Field("lat") String country_id,
            @Field("lang") String city_id,
            @Field("password") String password,
            @Field("password_confirmation") String repassword);

    @FormUrlEncoded
    @POST("auth/login")
    Call<UserLoginModel> onLogin(
            @Field("mobile") String mobile,
            @Field("password") String password
    );

//    @FormUrlEncoded
//    @POST("restaurant/profile/update")
//    Call<EdirProfileModel> OnUpdate(@Field("id") String id,
//                                    @Field("name") String name,
//                                    @Field("description") String description,
//                                    @Field("image") String image,
//                                    @Field("background") String background,
//                                    @Field("mobile") String mobile,
//                                    @Field("delivery_time") String delivery_time,
//                                    @Field("minimum") String minimum,
//                                    @Field("location") String location,
//                                    @Field("is_open") boolean is_open,
//                                    @Field("delivery_cost") String delivery_cost,
//                                    @Header("Authorization") String Authorization);
//    @GET("restaurant/profile")
//    Call<InfoRestrant> onGetMyProfile(@Header("Authorization") String Authorization);

//
//    @FormUrlEncoded
//    @POST("{url}")
//    Call<SendDataOther> sendMsg(@Path(value = "url", encoded = true) String url,
//                                @Field("name") String name,
//                                @Field("phone") String phone,
//                                @Field("subject") String subject,
//                                @Field("message") String message
//    );
//
//
//
//    @GET("categories/{id}/products")
//    Call<GetProductFromMain> onGetProductFromHOme(@Path(value = "id", encoded = true) String productId);
//
//    @GET("products/{id}")
//    Call<DetailsItemModel> onGetProductDetails(@Path(value = "id", encoded = true) String productId);
//
//
//    @GET("categories")
//    Call<GetCatigoryMain> onGetCategory();
//
//    @FormUrlEncoded
//    @POST("address")
//    Call<GetUserAddress> addNewAddress(
//            @Field("address_title") String address_title,
//            @Field("city") String city,
//            @Field("neighborhood") String neighborhood,
//            @Field("street_no") String street_no,
//            @Field("building_no") String building_no,
//            @Field("flat_no") String flat_no,
//            @Field("apartment_no") String address_signs,
//            @Field("notes") String notes,
//            @Header("Authorization") String Authorization)
//            ;

//    @FormUrlEncoded
//    @POST("update")
//    Call<UpgateDataUser> onUpdateUser(
//            @Field("name") String name,
//            @Field("mobile") String mobile,
//            @Field("email") String email,
//            @Field("old_password") String old_password,
//            @Field("password") String password,
//            @Header("Authorization") String Authorization)
//            ;


//    @GET("user")
//    Call<GetDataUser> onGetUser(@Header("Authorization") String Authorization);


//        @GET("address")
//        Call<GetAllAddress> onGetAllAddress(@Header("Authorization") String Authorization);

//    @GET("category/{categoryId}/{country_id}")
//    Call<SubCategoryIcon> onGetSubCategoriesIcons(@Path(value = "categoryId", encoded = true) String category_id, @Path(value = "country_id", encoded = true) String country_id, @Header("Authorization") String header);
//
//
//    @GET("subcategory/{categoryId}/{country_id}")
//    Call<ProductSubSubModel> onGetSubSubCategoriesProducts(@Path(value = "categoryId", encoded = true) String category_id, @Path(value = "country_id", encoded = true) String country_id,
//                                                           @Header("Authorization") String header);
//
//
//
//    @GET("product/{productId}")
//    Call<DetailsItemModel> onGetDetailsProducts(@Path(value = "productId", encoded = true) String productId, @Header("Authorization") String header);
//
//
//    @GET("iconsforproduct/{productId}")
//    Call<ListMyIcon> onGetPicIconsGridProduct(@Path(value = "productId", encoded = true) String productId);
//
// @GET("products/{country_id}")
// Call<CategoryProductsByIdCountryModel> getCategoryByCountry(@Path(value = "country_id", encoded = true) String country_id,
//                                                             @Header("Authorization") String header);
//    @GET("starredproducts/{country_id}")
//    Call<CategoryProductsByIdCountryModel> getPerfictProByCountry(@Path(value = "country_id", encoded = true) String country_id,
//                                                                  @Header("Authorization") String header);
//
//    @GET("getuserimage/{userID}")
//    Call<BaseResponce> getUserPic(@Path(value = "userID", encoded = true) int userID);
//
// @GET("countries")
// Call<List<CountriesModel>> onGetCountriesIcons();
//


//    @GET("restaurant/orders")
//    Call<AllOrdersModel> getAllOrders(@Header("Authorization") String header);
//
//    @GET("restaurant/orders/pending")
//    Call<AllOrdersModel> getpendingOrders(@Header("Authorization") String header);
//
//    @GET("restaurant/orders/delivering")
//    Call<AllOrdersModel> getAcceptOrders(@Header("Authorization") String header);
//
//    @GET("restaurant/orders/finished")
//    Call<AllOrdersModel> getfinishedOrders(@Header("Authorization") String header);
//
//    @FormUrlEncoded
//    @POST("auth/restaurants/login")
//    Call<UserLoginModel> onLogin(
//            @Field("mobile") String mobile,
//            @Field("password") String password, @Query("lang") String lang
//    );
//
//    @POST("restaurant/update/open-time")
//    Call<BaseResponse> onChangeState(
//            @Header("Authorization") String header);
//
//
//    //@Headers("Content-Type: application/json")
//
//    @GET("restaurant/orders/{orderproductid}/product/{prodcutid}")
//    Call<OrderDetailsModel>getDetails(@Path(value = "orderproductid", encoded = true) String orderproductid,
//                                      @Path(value = "prodcutid", encoded = true) String productID,
//                                      @Header("Authorization") String header);
//

//
//
//    );
//
//
//
//    @FormUrlEncoded
//    @POST("login")
//    Call<UserLoginModel> onLogin(
//            @Field("email") String email,
//            @Field("password") String password)
//            ;
//
//
//    @FormUrlEncoded
//    @POST("login_fb")
//    Call<DataUser> onLogin_FaceBook(
//            @Field("name") String first_name,
//            @Field("email") String email,
//            @Field("id") String id)
//            ;
//
//
//    @FormUrlEncoded
//    @POST("login_gm")
//    Call<DataUser> onLogin_GMail(
//            @Field("name") String first_name,
//            @Field("email") String email,
//            @Field("id") String id)
//            ;
//
//    //@Headers("Content-Type: application/json")
//    @FormUrlEncoded
//    @POST("comment/{productID}")
//    Call<BaseResponce> addNewComment(@Path(value = "productID", encoded = true) String productID,
//                                     @Field("comment") String comment_send,
//                                     @Header("Authorization") String header);
//
//
//
//    @POST("favourite_add/{adID}")
//    Call<BaseResponce> addFavoritItem(@Path(value = "adID", encoded = true) int productID,
//                                      @Header("Authorization") String header);
//
//
//
//    @FormUrlEncoded
//    @POST("reply/{commentID}")
//    Call<ReplayWithBaseResponce> addNewReply(@Path(value = "commentID", encoded = true) String commentID,
//                                             @Field("reply") String reply_comment,
//                                             @Header("Authorization") String header);
//
//
//
//
//
//
//    @FormUrlEncoded
//    @POST("sendmessage")
//    Call<BaseResponce> addNewMessage(@Field("sender_id") int sender_id,
//                                     @Field("title") String title,
//                                     @Field("body") String body,
//                                     @Header("Authorization") String header);
//
//
//    @GET("myprofile")
//    Call<InformationUser> onGetInformationUser(@Header("Authorization") String header);
//
//
//    @GET("dataforuser/{userID}")
//    Call<DetailUserInfo> getDataForUser(@Path(value = "userID", encoded = true) int userID);
//
//
//
//    @GET("datawithfollow/{userID}")
//    Call<DetailUserInfo> getDataWithFollower(@Path(value = "userID", encoded = true) int userID,
//                                             @Header("Authorization") String header);
//
//
//    @GET("profiledata")
//    Call<DetailUserInfo> getDetailsInformationUser(@Header("Authorization") String header);
//
//
//
//
//    @GET("myads/{id}")
//    Call<List<MyAdsModel>> getMyAds(@Path(value = "id", encoded = true) int adsID);
//
//    @GET("myfavourites")
//    Call<List<FavoritModel>> getMyFavorits(@Header("Authorization") String header);
//
//    @GET("mymessages")
//    Call<MessagesModel> getMyMessage(@Header("Authorization") String header);
//
//
//    @GET("sellers/top")
//    Call<TopSeller> getSeller();
//
//    @GET("notifications")
//    Call<List<NotificationModel>> getMyNotifications(@Header("Authorization") String header);
//
//    @DELETE("deletenotifications/{id}")
//    Call<BaseResponce> deleteNotification(@Path(value = "id", encoded = true) String notiID,
//                                          @Header("Authorization") String header);
//
//
//
//    @DELETE("deletemsg/{msgID}")
//    Call<BaseResponce> deleteMessage(@Path(value = "msgID", encoded = true) int _id,
//                                     @Header("Authorization") String header);
//
//    @DELETE("deleteproduct/{productID}")
//    Call<BaseResponce> deletItem(@Path(value = "productID", encoded = true) int productID,
//                                 @Header("Authorization") String header);
//
//
//
//    @GET("message/{msgID}")
//    Call<MsgById> getMsgById(@Path(value = "msgID", encoded = true) int msgID,
//                             @Header("Authorization") String header);
//
//
//    @GET("type/{typeID}")
//    Call<InformationCars> getCarSpinners(@Path(value = "typeID", encoded = true) String typeID);
//
//    @GET("type/{typeID}")
//    Call<InformationLabtop> getLabtopSpinners(@Path(value = "typeID", encoded = true) String typeID);
//
//    @GET("type/{typeID}")
//    Call<InformationMobile> getMobileSpinners(@Path(value = "typeID", encoded = true) String typeID);
//
//
//    @GET("getbrands/{brandID}")
//    Call<ModelsCar> getModelSpinners(@Path(value = "brandID", encoded = true) String brandID);
//
//    @GET("   getsubcategories/{categoryID}")
//    Call<SubcategoriesOther> getsubcategories(@Path(value = "categoryID", encoded = true) String categoryID);
//
//
//    @GET("getprice/{productID}")
//    Call<Integer> getPriceAnyItem(@Path(value = "productID", encoded = true) String _id);
//
//
//    @GET("getfollowers/{typeID}/{userId}")
//    Call<List<FollowModel>> getFollowers(@Path(value = "typeID", encoded = true) int typeID,
//                                         @Path(value = "userId", encoded = true) int userId);
//
//
//    @FormUrlEncoded
//    @POST("follow")
//    Call<BaseResponce> addNewFrind(@Field("followingID") int user_id,
//                                   @Header("Authorization") String header);
//
//
//    @FormUrlEncoded
//    @POST("device_token")
//    Call<BaseResponce> getDeviceToken(@Field("device_token") String token,
//                                      @Header("Authorization") String header);
//
//
//
//    @POST("flag_product/{productID}")
//    Call<ReportModel> sendReport(@Path(value = "productID", encoded = true) int productID,
//                                 @Header("Authorization") String header);
//
//
//    @FormUrlEncoded
//    @POST("new/{id}")
//    Call<BaseResponce> addNewCar(@Path(value = "id", encoded = true) int typeId,
//                                 @Field("name_ar") String name_ar,
//                                 @Field("name_en") String name_en,
//                                 @Field("category_id") int category_id,
//                                 @Field("subcategory_id") int subcategory_id,
//                                 @Field("country_id") int country_id,
//                                 @Field("city_id") int city_id,
//                                 @Field("description_ar") String description_ar,
//                                 @Field("description_en") String description_en,
//                                 @Field("sale") int sale,
//                                 @Field("new") int newOrUsed,
//                                 @Field("warranty") int warranty,
//                                 @Field("picture") String picture,
//                                 @Field("tags") String tags,
//                                 @Field("images") String images,
//                                 @Field("date") String date,
//                                 @Field("price") String price,
//                                 @Field("brand_id") int brand_id,
//                                 @Field("color_id") int color_id,
//                                 @Field("transmission_id") int transmission_id,
//                                 @Field("cylinder_id") int cylinder_id,
//                                 @Field("wheel_id") int wheel_id,
//                                 @Field("kilometers") int kilometers,
//                                 @Field("year_id") int year_id,
//                                 @Field("doors") int doors,
//                                 @Field("type_id") int modelCar,
//                                 @Header("Authorization") String header);
//
//
//    @FormUrlEncoded
//    @POST("edit/{productID}")
//    Call<BaseResponce> editNewCar(@Path(value = "productID", encoded = true) int typeId,
//                                  @Field("name_ar") String name_ar,
//                                  @Field("name_en") String name_en,
//                                  @Field("category_id") int category_id,
//                                  @Field("subcategory_id") int subcategory_id,
//                                  @Field("country_id") int country_id,
//                                  @Field("city_id") int city_id,
//                                  @Field("description_ar") String description_ar,
//                                  @Field("description_en") String description_en,
//                                  @Field("sale") int sale,
//                                  @Field("new") int newOrUsed,
//                                  @Field("warranty") int warranty,
//                                  @Field("picture") String picture,
//                                  @Field("tags") String tags,
//                                  @Field("images") String images,
//                                  @Field("date") String date,
//                                  @Field("price") String price,
//                                  @Field("brand_id") int brand_id,
//                                  @Field("color_id") int color_id,
//                                  @Field("transmission_id") int transmission_id,
//                                  @Field("cylinder_id") int cylinder_id,
//                                  @Field("wheel_id") int wheel_id,
//                                  @Field("kilometers") int kilometers,
//                                  @Field("year_id") int year_id,
//                                  @Field("doors") int doors,
//                                  @Field("type_id") int modelCar,
//                                  @Header("Authorization") String header);
//
//    @FormUrlEncoded
//    @POST("new/{id}")
//    Call<BaseResponce> addNewLaptop(@Path(value = "id", encoded = true) int typeId,
//
//                                    @Field("name_ar") String name_ar,
//                                    @Field("name_en") String name_en,
//                                    @Field("category_id") int category_id,//2
//                                    @Field("subcategory_id") int subcategory_id,//2
//                                    @Field("country_id") int country_id,
//                                    @Field("city_id") int city_id,
//                                    @Field("description_ar") String description_ar,
//                                    @Field("description_en") String description_en,
//                                    @Field("sale") int sale,
//                                    @Field("new") int newOrUsed,
//                                    @Field("warranty") int warranty,
//                                    @Field("picture") String picture,
//                                    @Field("tags") String tags, @Field("images") String images,
//                                    @Field("date") String date,
//                                    @Field("price") String price,
//                                    @Field("brand_id") int brand_id,
//                                    @Field("operating_id") int operating_id,
//                                    @Field("cpu_id") int cpu_id,
//                                    @Field("storage_id") int storage_id,
//                                    @Field("graphic_id") int graphic_id,
//                                    @Field("ram_id") int ram_id,
//                                    @Field("year_id") int year_id,
//                                    @Field("screen_id") int screen_id,
//                                    @Field("type_id") int modelCar,
//                                    @Header("Authorization") String h9eader);
//
//
//
//
//
//    @FormUrlEncoded
//    @POST("edit/{productID}")
//    Call<BaseResponce> editNewLaptop(@Path(value = "productID", encoded = true) int typeId,
//
//                                     @Field("name_ar") String name_ar,
//                                     @Field("name_en") String name_en,
//                                     @Field("category_id") int category_id,//2
//                                     @Field("subcategory_id") int subcategory_id,//2
//                                     @Field("country_id") int country_id,
//                                     @Field("city_id") int city_id,
//                                     @Field("description_ar") String description_ar,
//                                     @Field("description_en") String description_en,
//                                     @Field("sale") int sale,
//                                     @Field("new") int newOrUsed,
//                                     @Field("warranty") int warranty,
//                                     @Field("picture") String picture,
//                                     @Field("tags") String tags, @Field("images") String images,
//                                     @Field("date") String date,
//                                     @Field("price") String price,
//                                     @Field("brand_id") int brand_id,
//                                     @Field("operating_id") int operating_id,
//                                     @Field("cpu_id") int cpu_id,
//                                     @Field("storage_id") int storage_id,
//                                     @Field("graphic_id") int graphic_id,
//                                     @Field("ram_id") int ram_id,
//                                     @Field("year_id") int year_id,
//                                     @Field("screen_id") int screen_id,
//                                     @Field("type_id") int modelCar,
//                                     @Header("Authorization") String h9eader);
//
//
//
//
//    @FormUrlEncoded
//    @POST("edit/{productID}")
//    Call<BaseResponce> EditNewStructure(@Path(value = "productID", encoded = true) int typeId,
//
//                                        @Field("name_ar") String name_ar,
//                                        @Field("name_en") String name_en,
//                                        @Field("category_id") int category_id,//2
//                                        @Field("subcategory_id") int subcategory_id,//2
//                                        @Field("country_id") int country_id,
//                                        @Field("city_id") int city_id,
//                                        @Field("description_ar") String description_ar,
//                                        @Field("description_en") String description_en,
//                                        @Field("sale") int sale,
//                                        @Field("new") int newOrUsed,
//                                        @Field("picture") String picture,
//                                        @Field("tags") String tags,
//                                        @Field("estate_id") int estate_id,
//                                        @Field("area") int area,
//                                        @Field("rooms") int rooms,
//                                        @Field("bathrooms") int bathrooms,
//                                        @Field("furniture") int furniture,
//                                        @Field("images") String images,
//                                        @Field("date") String date,
//                                        @Field("price") String price,
//                                        @Field("type_id") int modelCar,
//                                        @Header("Authorization") String h9eader);
//
//
//    @FormUrlEncoded
//    @POST("new/{id}")
//    Call<BaseResponce> addNewStructure(@Path(value = "id", encoded = true) int typeId,
//
//                                       @Field("name_ar") String name_ar,
//                                       @Field("name_en") String name_en,
//                                       @Field("category_id") int category_id,//2
//                                       @Field("subcategory_id") int subcategory_id,//2
//                                       @Field("country_id") int country_id,
//                                       @Field("city_id") int city_id,
//                                       @Field("description_ar") String description_ar,
//                                       @Field("description_en") String description_en,
//                                       @Field("sale") int sale,
//                                       @Field("new") int newOrUsed,
//                                       @Field("picture") String picture,
//                                       @Field("tags") String tags,
//                                       @Field("estate_id") int estate_id,
//                                       @Field("area") int area,
//                                       @Field("rooms") int rooms,
//                                       @Field("bathrooms") int bathrooms,
//                                       @Field("furniture") int furniture,
//                                       @Field("images") String images,
//                                       @Field("date") String date,
//                                       @Field("price") String price,
//                                       @Field("type_id") int modelCar,
//                                       @Header("Authorization") String h9eader);
//
//    @FormUrlEncoded
//    @POST("new/{id}")
//    Call<BaseResponce> addNewOther(@Path(value = "id", encoded = true) int typeId,
//
//                                   @Field("name_ar") String name_ar,
//                                   @Field("name_en") String name_en,
//                                   @Field("category_id") int category_id,//2
//                                   @Field("subcategory_id") int subcategory_id,//2
//                                   @Field("country_id") int country_id,
//                                   @Field("city_id") int city_id,
//                                   @Field("description_ar") String description_ar,
//                                   @Field("description_en") String description_en,
//                                   @Field("sale") int sale,
//                                   @Field("new") int newOrUsed,
//                                   @Field("picture") String picture,
//                                   @Field("tags") String tags,
//                                   @Field("estate_id") int estate_id,
//                                   @Field("images") String images,
//                                   @Field("date") String date,
//                                   @Field("price") String price,
//                                   @Field("type_id") int modelCar,
//                                   @Header("Authorization") String h9eader);
//
//
//    @FormUrlEncoded
//    @POST("edit/{productID}")
//    Call<BaseResponce> EditNewOther(@Path(value = "productID", encoded = true) int typeId,
//
//                                    @Field("name_ar") String name_ar,
//                                    @Field("name_en") String name_en,
//                                    @Field("category_id") int category_id,//2
//                                    @Field("subcategory_id") int subcategory_id,//2
//                                    @Field("country_id") int country_id,
//                                    @Field("city_id") int city_id,
//                                    @Field("description_ar") String description_ar,
//                                    @Field("description_en") String description_en,
//                                    @Field("sale") int sale,
//                                    @Field("new") int newOrUsed,
//                                    @Field("picture") String picture,
//                                    @Field("tags") String tags,
//                                    @Field("estate_id") int estate_id,
//                                    @Field("images") String images,
//                                    @Field("date") String date,
//                                    @Field("price") String price,
//                                    @Field("type_id") int modelCar,
//                                    @Header("Authorization") String h9eader);
//
//
//    @FormUrlEncoded
//    @POST("profile/edit")
//    Call<EditDataUser> editProfile(@Field("email") String email,
//                                   @Field("full_name_ar") String full_name_ar,
//                                   @Field("full_name_en") String full_name_en,
//                                   @Field("address_ar") String address_ar,
//                                   @Field("address_en") String address_en,
//                                   @Field("bio_ar") String bio_ar,
//                                   @Field("bio_en") String bio_en,
//                                   @Field("phone") String phone,
//                                   @Field("facebook") String facebook,
//                                   @Field("twitter") String twitter,
//                                   @Field("instagram") String instagram,
//                                   @Field("youtube") String youtube,
//                                   @Field("snapchat") String snapchat,
//                                   @Field("whatsapp") String whatsapp,
//                                   @Field("chosen_country_id") int chosen_country_id,
//                                   @Field("picture") String picture,
//                                   @Header("Authorization") String h9eader);
//
//
//
//   @GET("type/{typeID}")
//   Call<InformationOther> getOtherSpinners(@Path(value = "typeID", encoded = true) String typeID);
//
//
//
//    @FormUrlEncoded
//    @POST("edit/{productID}")
//    Call<BaseResponce> EditNewMobile(@Path(value = "productID", encoded = true) int typeId,
//
//                                     @Field("name_ar") String name_ar,
//                                     @Field("name_en") String name_en,
//                                     @Field("category_id") int category_id,//2
//                                     @Field("subcategory_id") int subcategory_id,//2
//                                     @Field("country_id") int country_id,
//
//
//                                     @Field("city_id") int city_id,
//                                     @Field("description_ar") String description_ar,
//                                     @Field("description_en") String description_en,
//
//                                     @Field("sale") int sale,
//                                     @Field("new") int newOrUsed,
//                                     @Field("warranty") int warranty,
//                                     @Field("picture") String picture,
//                                     @Field("tags") String tags,
//
//                                     @Field("images") String images,
//                                     @Field("date") String date,
//                                     @Field("price") String price,
//                                     @Field("brand_id") int brand_id,
//
//                                     @Field("operating_id") int operating_id,
//                                     @Field("battery_id") int battery_id,
//                                     @Field("storage_id") int storage_id,
//                                     @Field("sim_id") int sim_id,
//                                     @Field("ram_id") int ram_id,
//                                     @Field("camera_id") int camera_id,
//                                     @Field("screen_id") int screen_id,
//                                     @Field("type_id") int modelCar,
//                                     @Header("Authorization") String h9eader);
//    @FormUrlEncoded
//    @POST("new/{id}")
//    Call<BaseResponce> addNewMobile(@Path(value = "id", encoded = true) int typeId,
//
//                                    @Field("name_ar") String name_ar,
//                                    @Field("name_en") String name_en,
//                                    @Field("category_id") int category_id,//2
//                                    @Field("subcategory_id") int subcategory_id,//2
//                                    @Field("country_id") int country_id,
//
//
//                                    @Field("city_id") int city_id,
//                                    @Field("description_ar") String description_ar,
//                                    @Field("description_en") String description_en,
//
//                                    @Field("sale") int sale,
//                                    @Field("new") int newOrUsed,
//                                    @Field("warranty") int warranty,
//                                    @Field("picture") String picture,
//                                    @Field("tags") String tags,
//
//                                    @Field("images") String images,
//                                    @Field("date") String date,
//                                    @Field("price") String price,
//                                    @Field("brand_id") int brand_id,
//
//                                    @Field("operating_id") int operating_id,
//                                    @Field("battery_id") int battery_id,
//                                    @Field("storage_id") int storage_id,
//                                    @Field("sim_id") int sim_id,
//                                    @Field("ram_id") int ram_id,
//                                    @Field("camera_id") int camera_id,
//                                    @Field("screen_id") int screen_id,
//                                    @Field("type_id") int modelCar,
//                                    @Header("Authorization") String h9eader);
//
//    @GET("getcities/{countryID}")
//    Call<CityModel> onGetCitySpinner(@Path(value = "countryID", encoded = true) int countryID);
//
//
//
//    @POST("search/laptops")
//    @FormUrlEncoded
//    Call<List<SearchFilter>> searchAboutLabtob(
//            @Field("screen") int screen,
//            @Field("brand") int brand,
//            @Field("type") int type,
//            @Field("ram") int ram,
//            @Field("storage") int storage,
//            @Field("cpu") int cpu,
//            @Field("graphics") int graphics,
//            @Field("city") int city,
//            @Field("price_to") String price_to,
//            @Field("price_from") String price_from,
//            @Field("condition") int condition,
//            @Field("country_id") int country_id);
//
//
//
//
//
//
//
//
//
//
//
//
//    @POST("search/phones")
//    @FormUrlEncoded
//    Call<List<SearchFilter>> searchAboutMobile(
//            @Field("screen") int screen,
//            @Field("brand") int brand,
//            @Field("type") int type,
//            @Field("ram") int ram,
//            @Field("storage") int storage,
//            @Field("sim") int sim,
//            @Field("battery") int battery,
//            @Field("city") int city,
//            @Field("price_to") String price_to,
//            @Field("price_from") String price_from,
//            @Field("condition") int condition,
//            @Field("country_id") int country_id);
//
//
//
//
//
//
//
//
//
//
//
//
//
//    @POST("search/states")
//    @FormUrlEncoded
//    Call<List<SearchFilter>> searchAboutStructure(
//
//            @Field("estate") int estate,
//            @Field("furniture") int furniture,
//            @Field("area_from") String area_from,
//            @Field("area_to") String area_to,
//            @Field("price_to") String price_to,
//            @Field("price_from") String price_from,
//            @Field("rooms_from") String rooms_from,
//            @Field("rooms_to") String rooms_to,
//            @Field("condition") int condition,
//            @Field("country_id") int country_id);
//
//
//
//
//    @POST("searchall/{type}")
//    @FormUrlEncoded
//    Call<List<SearchFilter>> onGetResultSearch(@Path(value = "type", encoded = true) int type,
//                                               @Field("word") String word,
//                                               @Field("country_id") int country_id);
//
//
//    @POST("tag/{tag}")
//    @FormUrlEncoded
//    Call<List<SearchFilter>> onGetResultTags(@Path(value = "tag", encoded = true) String tag,
//                                             @Field("country_id") int country_id);
//
//
//
//    @POST("search/cars")
//    @FormUrlEncoded
//    Call<List<SearchFilter>> searchAboutCar(
//            @Field("subcategory") int subcategory,
//            @Field("brand") int brand,
//            @Field("type") int type,
//            @Field("color") int color,
//            @Field("transmission") int transmission,
//            @Field("cylinder") int cylinder,
//            @Field("wheel") int wheel,
//            @Field("city") int city,
//            @Field("year_from") String year_from,
//            @Field("year_to") String year_to,
//            @Field("price_to") String price_to,
//            @Field("price_from") String price_from,
//            @Field("kilometer_from") String kilometer_from,
//            @Field("kilometer_to") String kilometer_to,
//            @Field("condition") int condition,
//            @Field("country_id") int country_id);
//
//
//
//    @GET("type/{typeID}")
//    Call<InformationStructuer> getStructuerSpinners(@Path(value = "typeID", encoded = true) String typeID);
}
