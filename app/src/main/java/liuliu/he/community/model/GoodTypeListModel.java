package liuliu.he.community.model;

import java.util.List;

/**
 * Created by Administrator on 2015/12/9.
 */
public class GoodTypeListModel {
    private String id;
    private int bid;
    private String name;
    private String image;
    private boolean isPreferential;
    private boolean isPresent;
    /**
     * id : 15
     * bid : 0
     * name : 精选大米
     * image : http://img.hesq.com.cn/fresh/upload/category/20150718/1437182353094.jpg
     * isPreferential : false
     * isPresent : false
     */

    private List<SmallEntity> small;

    public void setId(String id) {
        this.id = id;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIsPreferential(boolean isPreferential) {
        this.isPreferential = isPreferential;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public void setSmall(List<SmallEntity> small) {
        this.small = small;
    }

    public String getId() {
        return id;
    }

    public int getBid() {
        return bid;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public boolean isIsPreferential() {
        return isPreferential;
    }

    public boolean isIsPresent() {
        return isPresent;
    }

    public List<SmallEntity> getSmall() {
        return small;
    }

    public static class SmallEntity {
        private String id;
        private int bid;
        private String name;
        private String image;
        private boolean isPreferential;
        private boolean isPresent;

        public void setId(String id) {
            this.id = id;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setIsPreferential(boolean isPreferential) {
            this.isPreferential = isPreferential;
        }

        public void setIsPresent(boolean isPresent) {
            this.isPresent = isPresent;
        }

        public String getId() {
            return id;
        }

        public int getBid() {
            return bid;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public boolean isIsPreferential() {
            return isPreferential;
        }

        public boolean isIsPresent() {
            return isPresent;
        }
    }
}
