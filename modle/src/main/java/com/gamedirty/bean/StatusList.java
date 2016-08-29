package com.gamedirty.bean;

import java.util.List;

/**
 * 微博列表
 * Created by sovnem on 16/2/27.
 */
public class StatusList {

    public boolean hasvisible;
    public String previous_cursor;
    public long next_cursor;
    public String total_number;
    public String interval;
    public String uve_blank;
    public long since_id;
    public long max_id;
    public String has_unread;

    public List<Status> statuses;
    public List<?> advertises;
    public List<?> ad;

    @Override
    public String toString() {
        return "StatusList{" +
                "hasvisible=" + hasvisible +
                ", previous_cursor='" + previous_cursor + '\'' +
                ", next_cursor=" + next_cursor +
                ", total_number='" + total_number + '\'' +
                ", interval='" + interval + '\'' +
                ", uve_blank='" + uve_blank + '\'' +
                ", since_id=" + since_id +
                ", max_id=" + max_id +
                ", has_unread='" + has_unread + '\'' +
                ", statuses=" + statuses +
                ", advertises=" + advertises +
                ", ad=" + ad +
                '}';
    }
}
