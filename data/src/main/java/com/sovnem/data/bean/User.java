package com.sovnem.data.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sovnem on 16/2/27.
 */
public class User {

    public String id;
    public String idstr;
    @SerializedName("class")
    public String classX;
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
    public int urank;
}
