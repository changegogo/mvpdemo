package www.feicui.com.mvpdemo.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Author: dlw on 2016/10/27 12:03
 * Email: dailongshao@126.com
 * 手机工厂类，模拟model
 */
public class PhoneFactoty {
    private ArrayList<Phone> phonesList = new ArrayList<>();
    //添加手机
    public void addPhone(@NonNull Phone phone){
        phonesList.add(phone);
    }
    //移除手机
    public boolean removePhone(@NonNull Phone phone){
       return phonesList.remove(phone);
    }
    //通过索引移除手机
    public boolean removePhone(int index){
        if(index>=0 && index<phonesList.size()){
            phonesList.remove(index);
            return true;
        }else{
            return false;
        }
    }
    //生产手机
    public void createPhone(String name,int price){
        Phone phone = new Phone(name, price);
        phonesList.add(phone);
    }
    //获取手机集合
    public @NonNull ArrayList<Phone> getPhonesList(){
        return phonesList;
    }
    //获取手机的数量
    public int getPhoneCount(){
        return phonesList.size();
    }


}



















