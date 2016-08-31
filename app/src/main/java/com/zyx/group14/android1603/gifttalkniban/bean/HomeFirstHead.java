package com.zyx.group14.android1603.gifttalkniban.bean;

import java.util.List;

/**
 * Created by yixin on 2016/8/30.
 */
public class HomeFirstHead {


    /**
     * code : 200
     * data : {"banners":[{"ad_monitors":[],"channel":"all","id":703,"image_url":"http://img02.liwushuo.com/image/160830/9abtkez24.jpg-w720","order":260,"status":0,"target_id":1045453,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1045453","type":"post","webp_url":"http://img02.liwushuo.com/image/160830/9abtkez24.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":701,"image_url":"http://img01.liwushuo.com/image/160826/1hp4yso0d.jpg-w720","order":255,"status":0,"target_id":1045380,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1045380","type":"post","webp_url":"http://img01.liwushuo.com/image/160826/1hp4yso0d.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":698,"image_url":"http://img03.liwushuo.com/image/160826/dqqhdboq4.jpg-w720","order":249,"status":0,"target_id":null,"target_type":"url","target_url":"liwushuo:///page?url=https%3A%2F%2Fmeidian.play.m.jaeapp.com%2F%3Fiid%3D305816%26cpp%3D0&page_action=navigation&login=false&type=url","type":"url","webp_url":"http://img03.liwushuo.com/image/160826/dqqhdboq4.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":659,"image_url":"http://img01.liwushuo.com/image/160713/d82pyh6og.jpg-w720","order":231,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/160713/hsuo4fjil.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/160713/hsuo4fjil.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img03.liwushuo.com/image/160713/v9s8wtc53.jpg-w720","cover_webp_url":"http://img03.liwushuo.com/image/160713/v9s8wtc53.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1468390926,"id":333,"posts_count":7,"status":1,"subtitle":"我这么爱你，送礼物当然能送到你心坎里","title":"男友礼物","updated_at":1468391088},"target_id":333,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=333","type":"collection","webp_url":"http://img01.liwushuo.com/image/160713/d82pyh6og.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":684,"image_url":"http://img02.liwushuo.com/image/160809/hvegcoa1z.jpg-w720","order":220,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/160809/0vs8f6zch.jpg-w300","banner_webp_url":"http://img01.liwushuo.com/image/160809/0vs8f6zch.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img03.liwushuo.com/image/160809/fjbt7tn4a.jpg-w720","cover_webp_url":"http://img03.liwushuo.com/image/160809/fjbt7tn4a.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1470708135,"id":343,"posts_count":9,"status":0,"subtitle":"小户型居家指南","title":"拥有一个有温度的家，小小的满是爱","updated_at":1470710466},"target_id":343,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=343","type":"collection","webp_url":"http://img02.liwushuo.com/image/160809/hvegcoa1z.jpg?imageView2/2/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * ad_monitors : []
         * channel : all
         * id : 703
         * image_url : http://img02.liwushuo.com/image/160830/9abtkez24.jpg-w720
         * order : 260
         * status : 0
         * target_id : 1045453
         * target_type : url
         * target_url : liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1045453
         * type : post
         * webp_url : http://img02.liwushuo.com/image/160830/9abtkez24.jpg?imageView2/2/w/720/q/85/format/webp
         */

        private List<BannersBean> banners;

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class BannersBean {
            private String channel;
            private int id;
            private String image_url;
            private int order;
            private int status;
            private int target_id;
            private String target_type;
            private String target_url;
            private String type;
            private String webp_url;
            private List<?> ad_monitors;

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }
        }
    }
}
