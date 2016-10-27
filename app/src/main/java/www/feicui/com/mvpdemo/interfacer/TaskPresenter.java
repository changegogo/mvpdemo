package www.feicui.com.mvpdemo.interfacer;

import www.feicui.com.mvpdemo.model.Phone;

/**
 * Author: dlw on 2016/10/27 12:23
 * Email: dailongshao@126.com
 */
public interface TaskPresenter {
    void addPhone(Phone phone);
    void removePhone(Phone phone);
    void removePhone(int index);
}
