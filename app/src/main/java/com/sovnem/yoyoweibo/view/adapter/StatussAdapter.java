package com.sovnem.yoyoweibo.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.widget.ClickAbleImageView;
import com.sovnem.yoyoweibo.widget.MultiImageViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


/**
 * 微博适配器
 * Created by sovnem on 16/1/21.
 */
public class StatussAdapter extends BaseAdapter {

    private SimpleDateFormat sdf, sdf1, sdf2;
    private List<Status> statuses;
    private Context context;
    private int screenW;
    private Drawable headBack;

    public StatussAdapter(Context context, List statuses) {
        this.statuses = statuses;
        this.context = context;
        sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        //MMM dd hh:mm:ss Z yyyy
        sdf1 = new SimpleDateFormat("HH:mm:ss");
        sdf2 = new SimpleDateFormat("MM月dd日 HH:mm:ss");
        screenW = context.getResources().getDisplayMetrics().widthPixels;
        headBack = new ColorDrawable(Color.parseColor("#dfdfdf"));
    }

    public void addNewStatus(ArrayList<Status> news) {
        statuses.addAll(0, news);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return statuses.size();
    }

    @Override
    public Object getItem(int position) {
        return statuses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (null == itemView) {
            itemView = View.inflate(context, R.layout.layout_status_item, null);
            vh = new ViewHolder();
            bindViewHolder(itemView, vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
            clearState(vh);
        }
        Status status = statuses.get(position);
        fillView(vh, status);
        return itemView;
    }

    /**
     * 如果view是被复用的则将这个view的状态归0
     *
     * @param vh
     */
    private void clearState(ViewHolder vh) {
        vh.extra.setVisibility(View.GONE);
        vh.imgsLayout.setVisibility(View.GONE);
        vh.imgs.removeAllViews();
        vh.extraImgLayout.setVisibility(View.GONE);
        vh.extraImgs.removeAllViews();
    }

    /**
     * 在view里填充数据
     *
     * @param vh
     * @param status
     */
    private void fillView(ViewHolder vh, Status status) {
        //主微博内容
        vh.tvText.setText(status.text + "");
        if (TextUtils.isEmpty(status.source)) {
            vh.tvFrom.setVisibility(View.GONE);
        } else {
            vh.tvFrom.setVisibility(View.VISIBLE);
            vh.tvFrom.setText("来自 " + Html.fromHtml(status.source));
        }
        vh.tvAuthor.setText(status.user.name);
        vh.tvDate.setText(traslateDate(status.created_at));
        vh.tvReposts.setText((status.reposts_count == 0) ? "转发" : (status
                .reposts_count + ""));
        vh.tvLike.setText((status.attitudes_count == 0) ? "赞" : ("" + status
                .attitudes_count));
        vh.tvComments.setText((status.comments_count == 0) ? "评论" : (status
                .comments_count + ""));
        Glide.with(context).load(status.user.profile_image_url).into(vh
                .ivHead);
        if (status.pic_urls != null && status.pic_urls.size() > 0) {//主微博带的图片
            vh.imgsLayout.setVisibility(View.VISIBLE);
            showImgs(vh.imgs, status.pic_urls, vh);
        }
        Status extraStatus = status.retweeted_status;
        if (extraStatus != null) {//如果是引用的别人的微博
            vh.extra.setVisibility(View.VISIBLE);
            vh.tvExtraText.setText(extraStatus.text);
            if (extraStatus.pic_urls != null && extraStatus.pic_urls.size() >
                    0) {
                vh.extraImgLayout.setVisibility(View.VISIBLE);
                showImgs(vh.extraImgs, extraStatus.pic_urls, vh);
            }
        }


    }

    /**
     * 在imgslayout中显示图片 图片来自pic_urls路径
     *
     * @param imgsLayout
     * @param pic_urls
     * @param vh
     */
    private void showImgs(MultiImageViewGroup imgsLayout, ArrayList<String>
            pic_urls, ViewHolder vh) {

        int count = pic_urls.size();
        ClickAbleImageView iv;
        for (int i = 0; i < count; i++) {
            String url = pic_urls.get(i);
            iv = vh.ivs.get(i);
            iv.setType(ClickAbleImageView.TYPE_DEFAULT);
            confirmImageType(iv, url);
            iv.setImageResource(-1);

            imgsLayout.addView(iv);
            url = url.replace("thumbnail", /*count == 1 ? "large" :*/ "large");

            Glide.with(context).load(url).asBitmap().dontAnimate().transform(new MyBitmapTransform(context, iv, count)).//
                    override(count == 1 ? (screenW / 3 * 2) : (screenW / 3), count == 1 ? (screenW / 3 * 2) : (screenW / 3)).//
                    thumbnail(0.5f).into(iv);
        }
    }

    private void confirmImageType(ClickAbleImageView iv, String url) {
        boolean isGif = url.toLowerCase().endsWith(".gif");
        iv.setType(isGif ? ClickAbleImageView.TYPE_GIF : ClickAbleImageView.TYPE_DEFAULT);
    }

    public void addOldStatuses(ArrayList<Status> statusList) {
        statuses.addAll(statusList);
        notifyDataSetChanged();
    }


    class MyBitmapTransform extends BitmapTransformation {
        ClickAbleImageView iv;
        int count;

        public MyBitmapTransform(Context context, ClickAbleImageView imageView, int count) {
            super(context);
            this.iv = imageView;
            this.count = count;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int
                outWidth, int outHeight) {
            int w = toTransform.getWidth();
            int h = toTransform.getHeight();
            if (count == 1&&h >= w * 4)
                iv.setType(ClickAbleImageView.TYPE_LONGIMAGE);
            Bitmap returnBm = toTransform;
            if (h > w) {
                returnBm = Bitmap.createBitmap(toTransform, 0, 0, w, w);
            }

            if (returnBm != toTransform) {
                toTransform.recycle();
            }

            return returnBm;
        }

        @Override
        public String getId() {
            return "crop";
        }
    }


    /**
     * 转换日期格式
     * 把准确地日期转换成显示时间
     * <p/>
     * <p/>
     * 与当前时间相比显示不同的文字
     * <p/>
     * 如果是10分钟以内▶️刚刚
     * <p/>
     * 1小时以内显示▶️多少分钟前
     * <p/>
     * 今天以内▶️多少小时前
     * <p/>
     * 昨天
     * 前天
     * <p/>
     * a月b日
     *
     * @param a
     * @return
     */
    private String traslateDate(String a) {
        String date = null;//'hh:mm:ss'并未区分哪一天
        try {
            Calendar now = GregorianCalendar.getInstance();
            Calendar createAt = GregorianCalendar.getInstance();
            Date d = sdf.parse(a);
            date = sdf1.format(d);
            createAt.setTime(d);
            long dis = System.currentTimeMillis() - d.getTime();//发布时间与now的间隔 毫秒

            int dayNow = now.get(Calendar.DAY_OF_YEAR);
            int dayCreateAt = createAt.get(Calendar.DAY_OF_YEAR);

            int dayDif = dayNow - dayCreateAt;

            int hNow = now.get(Calendar.HOUR_OF_DAY);
            int hCreateAt = createAt.get(Calendar.HOUR_OF_DAY);

            int hDif = hNow - hCreateAt;


            if (dis <= TENMINITES) {//10分钟以内返回‘刚刚’
                return "刚刚";
            } else if (dayDif > 0) {//如果是隔一天发布的
                if (dayDif <= 2) {
                    switch (dayDif) {
                        case 1:
                            return "昨天" + date;
                        case 2:
                            return "前天" + date;
                    }
                } else {//如果是前天以前发布的
                    return sdf2.format(d) + "";
                }
            } else {//今天以内 且发布时间间隔大于了10分钟
                if (dis < ONEHOUR) {//如果是一小时以内
                    return dis / ONEMINITE + "分钟前";
                } else {
                    return hDif + "小时前";
                }
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static final long ONEMINITE = 60 * 1000;
    private static final long TENMINITES = ONEMINITE * 10;
    private static final long ONEHOUR = 60 * 60 * 1000;

    /**
     * 绑定viewholder
     *
     * @param itemView
     * @param vh
     */
    private void bindViewHolder(View itemView, ViewHolder vh) {
        vh.tvAuthor = (TextView) itemView.findViewById(R.id
                .textview_status_author);
        vh.tvDate = (TextView) itemView.findViewById(R.id.textview_status_time);
        vh.tvText = (TextView) itemView.findViewById(R.id.textview_status_text);
        vh.tvFrom = (TextView) itemView.findViewById(R.id.textview_status_from);
        vh.tvExtraText = (TextView) itemView.findViewById(R.id
                .textview_status_extratext);
        vh.tvReposts = (TextView) itemView.findViewById(R.id
                .textview_status_repostnum);
        vh.tvComments = (TextView) itemView.findViewById(R.id
                .textview_status_commentsnum);
        vh.tvLike = (TextView) itemView.findViewById(R.id
                .textview_status_likenum);
        vh.ivHead = (ImageView) itemView.findViewById(R.id
                .imageview_status_head);
        vh.extra = itemView.findViewById(R.id.layout_status_extra);
        vh.imgs = (MultiImageViewGroup) itemView.findViewById(R.id
                .imageviews_status_imgs);
        vh.extraImgs = (MultiImageViewGroup) vh.extra.findViewById(R.id
                .imageviews_status_extraimgs);
        vh.imgsLayout = itemView.findViewById(R.id.layout_imgs);
        vh.extraImgLayout = vh.extra.findViewById(R.id.layout_extraimgs);
        vh.ivs = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            vh.ivs.add(new ClickAbleImageView(context));
        }
        itemView.setTag(vh);
    }


    class ViewHolder {
        TextView tvAuthor//作者名字
                , tvDate,//发布时间
                tvFrom,//发布来源
                tvText,//作者发布的文字
                tvExtraText,//转发微博的文字
                tvReposts,//转发数
                tvComments,//评论数
                tvLike;//赞数
        ImageView ivHead;//头像
        MultiImageViewGroup imgs,//当前微博的图像
                extraImgs;//引用微博的图片
        View extra;//引用微博的布局
        View imgsLayout;//当前微博的图片
        View extraImgLayout;//引用微博中的图片
        ArrayList<ClickAbleImageView> ivs;//用来缓存multiimageviewgroup里的imageview
    }


}
