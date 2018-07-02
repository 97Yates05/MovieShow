package com.example.yangchenhui.movieshow;

import java.io.Serializable;
import java.util.List;

public class MovieBean implements Serializable{

    /**
     * id : 1
     * rating : {"max":10,"average":8.4,"stars":"45","min":0}
     * types : ["动作","科幻","冒险"]
     * title : 银河护卫队2
     * description : 漫威影业最新力作《银河护卫队2》带着全新劲爆好听的“劲歌金曲第二辑”回归大银幕！银河护卫队在本集中穿越宇宙，继续外太空的史诗冒险之旅。他们必须共同作战，守护彼此；同时要解开“星爵”彼得·奎尔的身世之谜。旧日敌人变为盟友，漫画中深受喜爱的角色也会现身，对护卫队出手援助。漫威电影宇宙则将持续扩张，进入新纪元！
     * casts : [{"name":"克里斯·普拉特"},{"name":"佐伊·索尔达娜"},{"name":"戴夫·巴蒂斯塔"}]
     * directors : [{"name":"詹姆斯·古恩"}]
     * year : 2017
     * imageUrl : http://www.imooc.com/data/movie/p2455261804.webp
     */

    private String id;
    private RatingBean rating;
    private String title;
    private String description;
    private String year;
    private String imageUrl;
    private List<String> types;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;

    @Override
    public String toString() {
        return "MovieBean{" +
                "id='" + id + '\'' +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", types=" + types +
                ", casts=" + casts +
                ", directors=" + directors +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public static class RatingBean implements Serializable {
        /**
         * max : 10
         * average : 8.4
         * stars : 45
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class CastsBean implements Serializable {
        /**
         * name : 克里斯·普拉特
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DirectorsBean implements Serializable {
        /**
         * name : 詹姆斯·古恩
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
