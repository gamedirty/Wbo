package com.sovnem.yoyoweibo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sina.weibo.sdk.openapi.models.Status;
import com.sovnem.data.utils.L;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.widget.ClickAbleImageView;
import com.sovnem.yoyoweibo.widget.MultiImageViewGroup;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * 微博adapter  展示微博内容  引用了别人微博的微博内容
 * Created by sovnem on 16/1/5.
 */
public class StatusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Status> statuses;//微博
    private Context context;

    public StatusAdapter(Context context, ArrayList statuses) {
        this.context = context;
        this.statuses = statuses;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(), R.layout.layout_status_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        L.i("当前显示的第几个:"+position);
        ViewHolder vh = (ViewHolder) holder;
        Status status = statuses.get(position);
        vh.setText(status.text);
        vh.setFrom(status.source);
        vh.setAuthor(status.user.name);
        vh.setDate(status.created_at);
        vh.setRepostNum(status.reposts_count);
        vh.setCommentsNum(status.comments_count);
        vh.setLikeNum(status.attitudes_count);
        vh.showHead(status.user.profile_image_url);
        if (status.pic_urls != null && status.pic_urls.size() > 0)
            vh.showImgs(status.pic_urls);
        Status extraStatus = status.retweeted_status;
        if (extraStatus != null) {
            vh.setExtraText(extraStatus.text);
            if (extraStatus.pic_urls != null && extraStatus.pic_urls.size() > 0) {
                vh.showExtraImgs(extraStatus.pic_urls);
            }
        }
    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAuthor//作者名字
                , tvDate,//发布时间
                tvFrom,//发布来源
                tvText,//作者发布的文字
                tvExtraText,//转发微博的文字
                tvReposts,//转发数
                tvComments,//评论数
                tvLike;//赞数
        private ImageView ivHead;//头像
        private MultiImageViewGroup imgs,//当前微博的图像
                extraImgs;//引用微博的图片
        private View extra;//引用微博的布局
        private View imgsLayout;//当前微博的图片
        private View extraImgLayout;//引用微博中的图片

        public ViewHolder(View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.textview_status_author);
            tvDate = (TextView) itemView.findViewById(R.id.textview_status_time);
            tvText = (TextView) itemView.findViewById(R.id.textview_status_text);
            tvFrom = (TextView) itemView.findViewById(R.id.textview_status_from);
            tvExtraText = (TextView) itemView.findViewById(R.id.textview_status_extratext);
            tvReposts = (TextView) itemView.findViewById(R.id.textview_status_repostnum);
            tvComments = (TextView) itemView.findViewById(R.id.textview_status_commentsnum);
            tvLike = (TextView) itemView.findViewById(R.id.textview_status_likenum);
            ivHead = (ImageView) itemView.findViewById(R.id.imageview_status_head);
            extra = itemView.findViewById(R.id.layout_status_extra);
            imgs = (MultiImageViewGroup) itemView.findViewById(R.id.imageviews_status_imgs);
            extraImgs = (MultiImageViewGroup) extra.findViewById(R.id.imageviews_status_extraimgs);
            imgsLayout = itemView.findViewById(R.id.layout_imgs);
            extraImgLayout = extra.findViewById(R.id.layout_extraimgs);
        }

        void setAuthor(String author) {
            tvAuthor.setText(author);
        }

        void setDate(String date) {

            String a = "Sun Nov 13 21:56:41 +0800 2011";
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);//MMM dd hh:mm:ss Z yyyy
            try {
                Date d = sdf.parse(a);
                sdf = new SimpleDateFormat("HH:mm:ss");
                date = sdf.format(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tvDate.setText(date);
        }

        void setFrom(String from) {
            tvFrom.setText(Html.fromHtml(from));
        }

        void setText(String text) {
            tvText.setText(text);
        }

        void setExtraText(String extraText) {
            extra.setVisibility(View.VISIBLE);
            tvExtraText.setText(extraText + "");
        }

        void setRepostNum(int n) {
            tvReposts.setText("" + n);
        }

        void setCommentsNum(int n) {
            tvComments.setText(n + "");

        }

        void setLikeNum(int n) {
            tvLike.setText(n + "");
        }

        void showHead(String imgUrl) {
            Picasso.with(context).load(imgUrl).into(ivHead);
        }


        /**
         * 展示当期那微博的 内容图片
         *
         * @param imgurls
         */
        void showImgs(ArrayList<String> imgurls) {
            imgsLayout.setVisibility(View.VISIBLE);
            showImg(imgs, imgurls);
        }

        /**
         * 展示引用微博的图片
         *
         * @param imgurls
         */
        void showExtraImgs(ArrayList<String> imgurls) {
            extraImgLayout.setVisibility(View.VISIBLE);
            showImg(extraImgs, imgurls);
        }

        /**
         * 在imageviewgroup中添加imageview 路径为imagurls
         *
         * @param imgs
         * @param imgurls
         */
        private void showImg(MultiImageViewGroup imgs, ArrayList<String> imgurls) {
            ClickAbleImageView iv;
            for (String url : imgurls) {
                iv = new ClickAbleImageView(context);
                imgs.addView(iv);
                Picasso.with(context).load(url).into(iv);
            }
        }

    }

}
