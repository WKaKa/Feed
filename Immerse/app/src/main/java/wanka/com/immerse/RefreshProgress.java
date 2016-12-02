package wanka.com.immerse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by fml on 2016/10/20 0020.
 * Project_Name:
 * Project_Introduction:
 * Modified_By:
 */
public class RefreshProgress extends ImageView {
    private Matrix m = new Matrix();
    //匀速加速器
    private LinearInterpolator lir = new LinearInterpolator();
    public RefreshProgress(Context context) {
        super(context);
    }

    public RefreshProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void changeAnimation(int num){
        m.reset();
        //
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.icon))
                .getBitmap();
        this.setImageBitmap(bitmap); //显示图像
        //
        m.setRotate(num);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),m,true);
        BitmapDrawable bd = new BitmapDrawable(newBitmap);
        this.setImageDrawable(bd); //显示新的图像

    }

    //控制动画
    public void Animation(){
        RotateAnimation rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //默认为0，为-1时一直循环动画
        rotate.setRepeatCount(0);
        //添加匀速加速器
        rotate.setInterpolator(lir);
        rotate.setDuration(1000);
        rotate.setFillAfter(true);
        this.startAnimation(rotate);
    }

}
