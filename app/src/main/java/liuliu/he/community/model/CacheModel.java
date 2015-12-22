package liuliu.he.community.model;

import java.util.List;

/**
 * Created by Administrator on 2015/12/22.
 */
public class CacheModel {
    private int id;//无意义
    private String type;//
    private String json;//需要解析的json数据
    /**
     * return : OK
     * error :
     * data : [{"img":"http://img.hesq.com.cn/fresh/upload/ad/20151219/1450516566506.jpg","link":"../product/list.php?type=rush"},{"img":"http://img.hesq.com.cn/fresh/upload/ad/20151215/1450167262503.jpg","link":"../product/list.php?bid=28"},{"img":"http://img.hesq.com.cn/fresh/upload/ad/20151219/1450508687525.jpg","link":"../product/list.php?bid=82"},{"img":"http://img.hesq.com.cn/fresh/upload/ad/20151217/1450314188095.jpg","link":"../product/list.php?bid=87"},{"img":"http://img.hesq.com.cn/fresh/upload/ad/20151219/1450520293961.jpg","link":"../product/list.php?bid=69&sid=65"}]
     */
    private String returnX;
    private String error;
    /**
     * img : http://img.hesq.com.cn/fresh/upload/ad/20151219/1450516566506.jpg
     * link : ../product/list.php?type=rush
     */

    private List<DataEntity> data;

    public void setReturnX(String returnX) {
        this.returnX = returnX;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getReturnX() {
        return returnX;
    }

    public String getError() {
        return error;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String img;
        private String link;

        public void setImg(String img) {
            this.img = img;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImg() {
            return img;
        }

        public String getLink() {
            return link;
        }
    }
}
