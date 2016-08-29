package com.gamedirty.bean;

/**
 * 微博用户
 * Created by sovnem on 16/2/27.
 */
public class User {

    public String id;
    public String idstr;
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
    public Status status;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", idstr='" + idstr + '\'' +
                ", classX='" + classX + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", profile_image_url='" + profile_image_url + '\'' +
                ", cover_image='" + cover_image + '\'' +
                ", cover_image_phone='" + cover_image_phone + '\'' +
                ", profile_url='" + profile_url + '\'' +
                ", domain='" + domain + '\'' +
                ", weihao='" + weihao + '\'' +
                ", gender='" + gender + '\'' +
                ", followers_count=" + followers_count +
                ", friends_count=" + friends_count +
                ", pagefriends_count=" + pagefriends_count +
                ", statuses_count=" + statuses_count +
                ", favourites_count=" + favourites_count +
                ", created_at='" + created_at + '\'' +
                ", following=" + following +
                ", allow_all_act_msg=" + allow_all_act_msg +
                ", geo_enabled=" + geo_enabled +
                ", verified=" + verified +
                ", verified_type=" + verified_type +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", ptype=" + ptype +
                ", allow_all_comment=" + allow_all_comment +
                ", avatar_large='" + avatar_large + '\'' +
                ", avatar_hd='" + avatar_hd + '\'' +
                ", verified_reason='" + verified_reason + '\'' +
                ", verified_trade='" + verified_trade + '\'' +
                ", verified_reason_url='" + verified_reason_url + '\'' +
                ", verified_source='" + verified_source + '\'' +
                ", verified_source_url='" + verified_source_url + '\'' +
                ", verified_state=" + verified_state +
                ", verified_level=" + verified_level +
                ", verified_reason_modified='" + verified_reason_modified + '\'' +
                ", verified_contact_name='" + verified_contact_name + '\'' +
                ", verified_contact_email='" + verified_contact_email + '\'' +
                ", verified_contact_mobile='" + verified_contact_mobile + '\'' +
                ", follow_me=" + follow_me +
                ", online_status=" + online_status +
                ", bi_followers_count=" + bi_followers_count +
                ", lang='" + lang + '\'' +
                ", star=" + star +
                ", mbtype=" + mbtype +
                ", mbrank=" + mbrank +
                ", block_word=" + block_word +
                ", block_app=" + block_app +
                ", credit_score=" + credit_score +
                ", user_ability=" + user_ability +
                ", urank=" + urank +
                '}';
    }
}
