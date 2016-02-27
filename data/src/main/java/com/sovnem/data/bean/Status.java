package com.sovnem.data.bean;

import java.util.List;

/**
 * 微博实体
 * Created by sovnem on 16/2/27.
 */
public class Status {

    public String created_at;
    public String id;
    public String mid;
    public String idstr;
    public String text;
    public String textLength;
    public String source_allowclick;
    public String source_type;
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

    public User user;
    public int reposts_count;
    public int comments_count;
    public int attitudes_count;
    public boolean isLongText;
    public String mlevel;

    public Visible visible;
    public long biz_feature;
    public String rid;
    public String userType;

    public Status retweeted_status;
    public List<PicUrl> pic_urls;
    public List<?> darwin_tags;
    public List<?> hot_weibo_tags;

    public static class Visible {
        public String type;
        public String list_id;
    }

    public static class PicUrl {
        public String thumbnail_pic;
    }
}
