package com.sovnem.data.bean;

import java.util.List;

/**
 *
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
}
