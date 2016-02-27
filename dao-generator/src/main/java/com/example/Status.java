package com.example;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sovnem on 16/2/27.
 */
public class Status {


    public String created_at;
    public long id;
    public String mid;
    public String idstr;
    public String text;
    public int textLength;
    public int source_allowclick;
    public int source_type;
    public String source;
    public boolean favorited;
    public boolean truncated;
    public String in_reply_to_status_id;
    public String in_reply_to_user_id;
    public String in_reply_to_screen_name;
    public String thumbnail_pic;
    public String bmiddle_pic;
    public String original_pic;
    public Object geo;

    public UserEntity user;
    public int reposts_count;
    public int comments_count;
    public int attitudes_count;
    public boolean isLongText;
    public int mlevel;

    public VisibleEntity visible;
    public int biz_feature;
    public int userType;
    public String cardid;

    public List<PicUrlsEntity> pic_urls;
    public List<?> darwin_tags;
    public List<?> hot_weibo_tags;


    public static class UserEntity {
        public long id;
        public String idstr;
        @SerializedName("class")
        public int classX;
        public String screen_name;
        public String name;
        public String province;
        public String city;
        public String location;
        public String description;
        public String url;
        public String profile_image_url;
        public String cover_image;
        public String cover_image_phone;
        public String profile_url;
        public String domain;
        public String weihao;
        public String gender;
        public int followers_count;
        public int friends_count;
        public int pagefriends_count;
        public int statuses_count;
        public int favourites_count;
        public String created_at;
        public boolean following;
        public boolean allow_all_act_msg;
        public boolean geo_enabled;
        public boolean verified;
        public int verified_type;
        public String remark;
        public int ptype;
        public boolean allow_all_comment;
        public String avatar_large;
        public String avatar_hd;
        public String verified_reason;
        public String verified_trade;
        public String verified_reason_url;
        public String verified_source;
        public String verified_source_url;
        public int verified_state;
        public int verified_level;
        public String verified_reason_modified;
        public String verified_contact_name;
        public String verified_contact_email;
        public String verified_contact_mobile;
        public boolean follow_me;
        public int online_status;
        public int bi_followers_count;
        public String lang;
        public int star;
        public int mbtype;
        public int mbrank;
        public int block_word;
        public int block_app;
        public int credit_score;
        public int user_ability;
        public String cardid;
        public int urank;
    }

    public static class VisibleEntity {
        public int type;
        public int list_id;
    }

    public static class PicUrlsEntity {
        public String thumbnail_pic;
    }
}
