package www.feicui.com.mvpdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import www.feicui.com.mvpdemo.interfacer.OperationView;
import www.feicui.com.mvpdemo.model.Phone;
import www.feicui.com.mvpdemo.model.PhoneFactoty;
import www.feicui.com.mvpdemo.presenter.PhonePresenter;
/**
 * mvp的demo
 *
 * */
public class MainActivity extends AppCompatActivity
implements OperationView{

    private Button mButton;
    private ListView mListView;
    private Toast toast;
    private PhonePresenter mPhonePresenter;
    private PhoneFactoty mPhoneFactoty;
    private ArrayAdapter<Phone> mPhoneAdapter;
    private ProgressDialog mProgressDialog;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        mButton = (Button) findViewById(R.id.btn_createPhone);
        mListView = (ListView) findViewById(R.id.listview);
        mTextView = (TextView) findViewById(R.id.tv_nophone);
        mPhoneFactoty = new PhoneFactoty();
        mPhoneFactoty.createPhone("xiaomi",1234);
        mPhonePresenter = new PhonePresenter(mPhoneFactoty, this);
        mPhoneAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mPhoneFactoty.getPhonesList());
        mListView.setAdapter(mPhoneAdapter);
        initEvent();
    }

    private void initEvent() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhonePresenter.addPhone(new Phone("nokia", (int)((Math.random()+1)*1000)));
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPhonePresenter.removePhone(position);
            }
        });
    }

    private void showToast(String str){
        if(toast == null){
            toast = Toast.makeText(this,str,Toast.LENGTH_SHORT);
        }else{
            toast.setText(str);
        }
        toast.show();
    }

    @Override
    public void showCreatingPhone() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("工厂正在生产手机");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    @Override
    public void showPhoneCountChange() {
        mPhoneAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoPhone() {
        if(mTextView != null){
            mTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showFactoryBusy() {
        showToast("工厂繁忙，请稍后再试！");
    }

    @Override
    public void showCreatedPhone() {
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
        showToast("新生产一台手机！");
        if(mTextView != null){
            mTextView.setVisibility(View.GONE);
        }

    }
}














