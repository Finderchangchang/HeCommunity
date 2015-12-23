package liuliu.he.community.control.help;

import java.util.List;

/**
 * 帮助中心
 * Created by Administrator on 2015/12/10.
 */
public interface IHelpView {
    void loadTop(List<String> list);//加载顶部item

    void loadBottom(List<String> list);//加载底部需要显示的文本信息
}
