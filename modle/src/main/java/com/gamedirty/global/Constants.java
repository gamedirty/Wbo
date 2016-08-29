package com.gamedirty.global;

/**
 * Created by sovnem on 16/1/1.
 */
public class Constants {
    public static final String APP_KEY = "1541309697";//appkey
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";//重定向URL
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";//权限相关


    private static final String BASE_URL = "https://api.weibo.com/2/";//基础URL
    /**
     * 微博相关接口
     */
    public static String getPublic_timeline = BASE_URL + "statuses/public_timeline.json";//获取最新的公共微博
    public static String getFriends_timeline = BASE_URL + "statuses/friends_timeline.json";//获取当前登录用户及其所关注用户的最新微博
    public static String getHome_timeline = BASE_URL + "statuses/home_timeline.json";//获取当前登录用户及其所关注用户的最新微博
    public static String getGetFriends_timelineids = BASE_URL + "statuses/friends_timeline/ids.json";//获取当前登录用户及其所关注用户的最新微博的ID
    public static String getUser_timeline = BASE_URL + "statuses/user_timeline.json";//获取用户发布的微博
    public static String getUser_timelineids = BASE_URL + "statuses/user_timeline/ids.json";//获取用户发布的微博的ID
    public static String getTimeline_batch = BASE_URL + "statuses/timeline_batch.json";//批量获取指定的一批用户的微博列表
    public static String getRepost_timeline = BASE_URL + "statuses/repost_timeline.json";//返回一条原创微博的最新转发微博
    public static String getRepost_timelineids = BASE_URL + "statuses/repost_timeline/ids.json";//获取一条原创微博的最新转发微博的ID
    public static String getMentions = BASE_URL + "statuses/mentions.json";//获取@当前用户的最新微博
    public static String getMentionsids = BASE_URL + "statuses/mentions/ids.json";//获取@当前用户的最新微博的ID
    public static String getBilateral_timeline = BASE_URL + "statuses/bilateral_timeline.json";//获取双向关注用户的最新微博
    public static String getTimelineByid = BASE_URL + "statuses/show.json";//根据ID获取单条微博信息
    public static String getTimelineByids = BASE_URL + "statuses/show_batch.json";//根据微博ID批量获取微博信息
    public static String querymid = BASE_URL + "statuses/querymid.json";//通过id获取mid
    public static String queryid = BASE_URL + "statuses/queryid.json";//通过mid获取id
    public static String getCount = BASE_URL + "statuses/count.json";//批量获取指定微博的转发数评论数
    public static String gotoOfid = BASE_URL + "statuses/go.json";//根据ID跳转到单条微博页
    public static String getEmotions = BASE_URL + "emotions.json";//获取官方表情
    public static String repost = BASE_URL + "statuses/repost.json";//转发一条微博信息
    public static String destroy = BASE_URL + "statuses/destroy.json";//删除微博信息
    public static String update = BASE_URL + "statuses/update.json";//发布一条微博信息
    public static String upload = BASE_URL + "statuses/upload.json";//上传图片并发布一条微博
    public static String upload_url_text = BASE_URL + "statuses/upload_url_text.json";//发布一条微博同时指定上传的图片或图片url
    public static String filterTimeline = BASE_URL + "statuses/filter/create.json";//屏蔽某条微博
    public static String filterUser = BASE_URL + "statuses/mentions/shield.json";//屏蔽某个@我的微博及后续由其转发引起的@提及

    /**
     * 评论相关接口
     */
    public static String getCommentsOfweibo = BASE_URL + "comments/show.json";//获取某条微博的评论列表
    public static String getCommentsFromme = BASE_URL + "comments/by_me.json";//我发出的评论列表
    public static String getCommentsTome = BASE_URL + "comments/to_me.json";//我收到的评论列表
    public static String getAllComments = BASE_URL + "comments/timeline.json";//获取用户发送及收到的评论列表
    public static String getCommentsMentionme = BASE_URL + "comments/mentions.json";//获取@到我的评论
    public static String getCommentsBatch = BASE_URL + "comments/show_batch.json";//批量获取评论内容
    public static String createAcomment = BASE_URL + "comments/create.json";//评论一条微博
    public static String deleteAComment = BASE_URL + "comments/destroy.json";//删除一条评论
    public static String deleteComments = BASE_URL + "comments/destroy_batch.json";//批量删除评论
    public static String replyComments = BASE_URL + "comments/reply.json";//回复一条评论
    /**
     * 用户相关接口
     */
    public static String getUserinfo = BASE_URL + "users/show.json";//获取用户信息
    public static String getUserinfoOfdomain = BASE_URL + "users/domain_show.json";//通过个性域名获取用户信息
    public static String getUserCounts = BASE_URL + "users/counts.json";//批量获取用户的粉丝数、关注数、微博数
    /**
     * 用户关系相关接口
     */
    public static String getFriends = BASE_URL + "friendships/friends.json";//获取用户的关注列表
    public static String getCommonFriends = BASE_URL + "friendships/friends/in_common.json";//获取共同关注人列表
    public static String getBilateralFriends = BASE_URL + "friendships/friends/bilateral.json";//获取双向关注列表
    public static String getBilateralFriendsids = BASE_URL + "friendships/friends/bilateral/ids.json";//获取双向关注UID列表
    public static String getFriendsids = BASE_URL + "friendships/friends/ids.json";//获取用户关注对象UID列表
    public static String getFollowers = BASE_URL + "friendships/followers.json";//获取用户粉丝列表
    public static String getFollowersids = BASE_URL + "friendships/followers/ids.json";//获取用户粉丝UID列表
    public static String getActiveFollowers = BASE_URL + "friendships/followers/active.json";//获取用户优质粉丝列表
    public static String getFlowersOffriendsChain = BASE_URL + "friendships/friends_chain/followers.json";//获取我的关注人中关注了指定用户的人
    public static String getFriendship = BASE_URL + "friendships/show.json";//获取两个用户关系的详细情况
    public static String createFriendshipof = BASE_URL + "friendships/create.json";//关注某用户
    public static String cancleFriendshipof = BASE_URL + "friendships/destroy.json";//取消关注某用户
    /**
     * 账号相关接口
     */
    public static String getSchoolList = BASE_URL + "account/profile/school_list.json";//获取所有学校列表
    public static String getApiRatelimit = BASE_URL + "account/rate_limit_status.json";//获取当前用户API访问频率限制
    public static String getUserEmail = BASE_URL + "account/profile/email.json";//获取用户的联系邮箱
    public static String getUID = BASE_URL + "account/get_uid.json";//OAuth授权之后获取用户UID
    /**
     * 收藏相关接口
     */
    public static String getFavorites = BASE_URL + "favorites.json";//获取当前用户的收藏列表
    public static String getFavoritesids = BASE_URL + "favorites/ids.json";//获取当前用户的收藏列表的ID
    public static String getAFavorite = BASE_URL + "favorites/show.json";//获取单条收藏信息
    public static String getFavoritesBytag = BASE_URL + "favorites/by_tags.json";//获取当前用户某个标签下的收藏列表
    public static String getFavoriteTags = BASE_URL + "favorites/tags.json";//当前登录用户的收藏标签列表
    public static String getFavoritesIdsBytag = BASE_URL + "favorites/by_tags/ids.json";//获取当前用户某个标签下的收藏列表的ID
    public static String addFavorite = BASE_URL + "favorites/create.json";//添加收藏
    public static String deleteFavorite = BASE_URL + "favorites/destroy.json";//删除收藏
    public static String deleteFavorites = BASE_URL + "favorites/destroy_batch.json";//批量删除收藏
    public static String updateFavoriteTags = BASE_URL + "favorites/tags/update.json";//更新收藏标签
    public static String updateFavoriteTag = BASE_URL + "favorites/tags/update_batch.json";//更新当前用户所有收藏下的指定标签
    public static String deleteFavoriteTag = BASE_URL + "favorites/tags/destroy_batch.json";//删除当前用户所有收藏下的指定标签
    /**
     * 搜索相关接口
     */
    public static String searchUser = BASE_URL + "search/suggestions/users.json";//搜用户搜索建议
    public static String searchSchool = BASE_URL + "search/suggestions/schools.json";//搜学校搜索建议
    public static String searchCompanies = BASE_URL + "search/suggestions/companies.json";//搜公司搜索建议
    public static String searchApplications = BASE_URL + "search/suggestions/apps.json";//搜应用搜索建议
    public static String searchSuggestions = BASE_URL + "search/suggestions/at_users.json";//@联想搜索
    /**
     * 提醒
     */
    public static String getUnreadcount = BASE_URL + "remind/unread_count.json";//获取某个用户的各种消息未读数
    public static String clearUnreadCount = BASE_URL + "remind/set_count.json";//对当前登录用户某一种消息未读数进行清零
    /**
     * 短连接
     */
    public static String shortenUrl = BASE_URL + "short_url/shorten.json";//长链转短链
    public static String expandUrl = BASE_URL + "short_url/expand.json";//短链转长链
    public static String getShortSharecount = BASE_URL + "short_url/share/counts.json";//获取短链接在微博上的微博分享数
    public static String getShortUrlContent = BASE_URL + "short_url/share/statuses.json";//获取包含指定单个短链接的最新微博内容
    public static String getShortUrlcommentcount = BASE_URL + "short_url/comment/counts.json";//获取短链接在微博上的微博评论数
    public static String getShortUrlComment = BASE_URL + "short_url/comment/comments.json";//获取包含指定单个短链接的最新微博评论

    /**
     * 公共服务
     */
    public static String getLocationByCode = BASE_URL + "common/code_to_location.json";//通过地址编码获取地址名称
    public static String getCitylist = BASE_URL + "common/get_city.json";//获取城市列表
    public static String getProvincelist = BASE_URL + "common/get_province.json";//获取省份列表
    public static String getCountryList = BASE_URL + "common/get_country.json";//获取国家列表
    public static String getTimezoneList = BASE_URL + "common/get_timezone.json";//获取时区配置表
    /**
     * 位置服务
     */
    public static String getPublicplaces = BASE_URL + "place/public_timeline.json";//获取公共的位置动态
    public static String getFriendplace = BASE_URL + "place/friends_timeline.json";//获取用户好友的位置动态
    public static String getUserplace = BASE_URL + "place/user_timeline.json";//获取某个用户的位置动态
    public static String getTimelineOfplace = BASE_URL + "place/poi_timeline.json";//获取某个位置地点的动态
    public static String getNearbyTimeline = BASE_URL + "place/nearby_timeline.json";//获取某个位置周边的动态
    public static String getTimelineDetail = BASE_URL + "place/statuses/show.json";//获取动态的详情
    public static String getLBSUserinfo = BASE_URL + "place/users/show.json";//获取LBS位置服务内的用户信息
    public static String getCheckinsplaces = BASE_URL + "place/users/checkins.json";//获取用户签到过的地点列表
    public static String getUserPhotolist = BASE_URL + "place/users/photos.json";//获取用户的照片列表
    public static String getUserComments = BASE_URL + "place/users/tips.json";//获取用户的点评列表
    public static String getTodolist = BASE_URL + "place/users/todos.json";//获取用户的todo列表
    public static String getPositionDetail = BASE_URL + "place/pois/show.json";//获取地点详情
    public static String getPlaceChecklist = BASE_URL + "place/pois/users.json";//获取在某个地点签到的人的列表
    public static String getPlaceTips = BASE_URL + "place/pois/tips.json";//获取地点点评列表
    public static String getPlacePhotos = BASE_URL + "place/pois/photos.json";//获取地点照片列表
    public static String getPlacesByprovince = BASE_URL + "place/pois/search.json";//按省市查询地点
    public static String getPositionClass = BASE_URL + "place/pois/category.json";//获取地点分类
    public static String getNearbyPosi = BASE_URL + "place/nearby/pois.json";//获取附近地点
    public static String getNearbyusers = BASE_URL + "place/nearby/users.json";//获取附近发位置微博的人
    public static String getNearbyPhotos = BASE_URL + "place/nearby/photos.json";//获取附近照片
    public static String getNearbyUserlist = BASE_URL + "place/nearby_users/list.json";//获取附近的人
    public static String addPlace = BASE_URL + "place/pois/create.json";//添加地点
    public static String checkinAtPlace = BASE_URL + "place/pois/add_checkin.json";//签到
    public static String addPhotoAtPlace = BASE_URL + "place/pois/add_photo.json";//添加照片
    public static String addTipAtPlace = BASE_URL + "place/pois/add_tip.json";//添加点评
    public static String addTodoAtPlace = BASE_URL + "place/pois/add_todo.json";//添加todo
    public static String addCostomPlace = BASE_URL + "place/nearby_users/create.json";//用户添加自己的位置
    public static String deleteCostomPlace = BASE_URL + "place/nearby_users/destroy.json";//用户删除自己的位置
    /**
     * 地理信息
     */
    public static String getLocationMapImage = BASE_URL + "location/base/get_map_image.json";//生成一张静态的地图图片
    public static String getGeoByIp = BASE_URL + "location/geo/ip_to_geo.json";//根据IP地址返回地理信息坐标
    public static String getGeoByAddress = BASE_URL + "location/geo/address_to_geo.json";//根据实际地址返回地理信息坐标
    public static String getAddressByGeo = BASE_URL + "location/geo/geo_to_address.json";//根据地理信息坐标返回实际地址
    public static String getGpsAfterOffset = BASE_URL + "location/geo/gps_to_offset.json";//根据GPS坐标获取偏移后的坐标
    public static String isGeoInChina = BASE_URL + "location/geo/is_domestic.json";//判断地理信息坐标是否是国内坐标
    public static String searchPoiByLocation = BASE_URL + "location/pois/search/by_location.json";//根据关键词按地址位置获取POI点的信息
    public static String searchPoiByGeo = BASE_URL + "location/pois/search/by_geo.json";//根据关键词按坐标点范围获取POI点的信息
    public static String searchPoiByArea = BASE_URL + "location/pois/search/by_area.json";//根据关键词按矩形区域获取POI点的信息
    public static String searchPois = BASE_URL + "location/pois/show_batch.json";//批量获取POI点的信息
    public static String addNewPoiinfo = BASE_URL + "location/pois/add.json";//提交一个新增的POI点信息
    public static String getLocationByNet = BASE_URL + "location/mobile/get_location.json";//根据移动基站WIFI等数据获取当前位置信息
    public static String getDriveRoute = BASE_URL + "location/line/drive_route.json";//根据起点与终点数据查询自驾车路线信息
    public static String getBusRoute = BASE_URL + "location/line/bus_route.json";//根据起点与终点数据查询公交乘坐路线信息
    public static String getBuslineBylocation = BASE_URL + "location/line/bus_line.json";//根据关键词查询公交线路信息
    public static String getBusstationByLocation = BASE_URL + "location/line/bus_station.json";//根据关键词查询公交站点信息
    public static String getCitycodes = BASE_URL + "location/citycode.json";//城市代码对应表
    public static String getBuscitycode = BASE_URL + "location/citycode_bus.json";//公交城市代码表
    public static String getCategory = BASE_URL + "location/category.json";//分类代码对应表
    public static String getErrorInfo = BASE_URL + "location/error2.json";//地理位置信息接口错误代码及解释
    /**
     * OAuth2
     */
    public static String getUserToken = BASE_URL + "oauth2/authorize.json";//请求用户授权Token
    public static String getAccessToken = BASE_URL + "oauth2/access_token.json";//获取授权过的Access
    public static String getTokeninfo = BASE_URL + "oauth2/get_token_info.json";//查询用户access_token的授权相关信息
    public static String getOath2Token = BASE_URL + "oauth2/get_oauth2_token.json";//OAuth1.0的Access Token更换至OAuth2.0的Access Token
    public static String cancleToken = BASE_URL + "OAuth2/revokeoauth2.json";//授权回收接口，帮助开发者主动取消用户的授权

}
