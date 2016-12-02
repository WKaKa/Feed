package wanka.com.immerse;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initStyle(3);
    }
    private void initStyle(int i) {
        if(i == 1){
            //第一种，去掉action bar 和状态栏 （无任何操作效果）
        View decorView = getWindow().getDecorView();//getWindow().getDecorView()方法获取到了当前界面的DecorView
        //然后调用它的setSystemUiVisibility()方法来设置系统UI元素的可见性。其中，SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏。另外，根据Android的设计建议，ActionBar是不应该独立于状态栏而单独显示的，因此状态栏如果隐藏了，我们同时也需要调用ActionBar的hide()方法将ActionBar也进行隐藏。
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        }else if(i ==2){
            //第二种 UI Flag 透明背景的状态栏
            if (Build.VERSION.SDK_INT >= 21) {
                //效果是只有5.0及以上系统才支持
                View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }

            }
        }else if(i == 3){
            //第三种 隐藏底部的导航栏
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);

        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    //第四种 重写 方法，达到真正的沉浸式效果；
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

}
