package www.feicui.com.mvpdemo.presenter;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import www.feicui.com.mvpdemo.interfacer.OperationView;
import www.feicui.com.mvpdemo.interfacer.TaskPresenter;
import www.feicui.com.mvpdemo.model.Phone;
import www.feicui.com.mvpdemo.model.PhoneFactoty;

/**
 * Author: dlw on 2016/10/27 12:24
 * Email: dailongshao@126.com
 */
public class PhonePresenter implements TaskPresenter {
    private PhoneFactoty mPhoneFactory;
    private OperationView mOperationView;
    private static final long createPhoneTime = 3000;
    private static final int msgWhat = 0x102;
    private Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mPhoneFactory.addPhone((Phone)msg.obj);
            mOperationView.showCreatedPhone();
            mOperationView.showPhoneCountChange();
        }
    };

    public PhonePresenter(@NonNull PhoneFactoty phoneFactory, @NonNull OperationView operationView) {
        mPhoneFactory = phoneFactory;
        mOperationView = operationView;
    }

    @Override
    public void addPhone(@NonNull Phone phone) {
       //mOperationView.showPhoneCountChange();
        if(mHandler.hasMessages(msgWhat)){
            mOperationView.showFactoryBusy();
            return;
        }
        Message message = new Message();
        message.what = msgWhat;
        message.obj = phone;
        mHandler.sendMessageDelayed(message,createPhoneTime);
        mOperationView.showCreatingPhone();
    }

    @Override
    public void removePhone(Phone phone) {
        mPhoneFactory.removePhone(phone);
        mOperationView.showPhoneCountChange();
        if(mPhoneFactory.getPhoneCount() <= 0){
            ArrayList<Phone> list = mPhoneFactory.getPhonesList();
            if(list.isEmpty()){
                mOperationView.showNoPhone();
            }
        }
    }

    @Override
    public void removePhone(int index) {
        mPhoneFactory.removePhone(index);
        mOperationView.showPhoneCountChange();
        if(mPhoneFactory.getPhoneCount() <= 0){
            mOperationView.showNoPhone();
        }
    }
}
