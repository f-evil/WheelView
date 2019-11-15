package com.easy.wheelview;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;

import androidx.annotation.Nullable;

/**
 * @author fuyujie
 * @package: com.easy.customview.test9
 * @fileNmae WheelView
 * @date 2019/11/14 15:39
 * @describe
 * @org easylinking
 * @email f279259625@gmail.com
 */
public class WheelView extends View {

    private Context mContext;
    /**
     * View宽度
     */
    private int mWidth;
    /**
     * View宽度
     */
    private int mHeight;

    private int mWheelBoundWidth;
    private int mWheelBoundHeight;

    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private Paint mPaintGray;
    private Paint mPaintWhite;
    private Paint mPaintBlack;
    private Paint paint4;
    private Paint paint5;
    private LinearGradient linearGradientT;
    private LinearGradient linearGradientB;
    private LinearGradient linearGradientC;

    private AssetManager assetManager;
    private MediaPlayer player = null;

    public WheelView(Context context) {
        this(context, null);
    }

    public WheelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
    }

    private void init(Context context) {

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#000000"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.parseColor("#000000"));
        paint2.setStyle(Paint.Style.FILL);

        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setColor(Color.parseColor("#5A5A5A"));
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(12);

        paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint4.setColor(Color.parseColor("#929292"));
        paint4.setStyle(Paint.Style.FILL);

        paint5 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint5.setStyle(Paint.Style.FILL);

        mPaintBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBlack.setColor(Color.parseColor("#1F1F1F"));
        mPaintBlack.setStyle(Paint.Style.FILL);

        mPaintWhite = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintWhite.setColor(Color.parseColor("#D3D3D3"));
        mPaintWhite.setStyle(Paint.Style.FILL);

        mPaintGray = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintGray.setColor(Color.parseColor("#797979"));
        mPaintGray.setStyle(Paint.Style.FILL);

        player = new MediaPlayer();
        assetManager = getResources().getAssets();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        mWheelBoundHeight = (int) (mHeight * 0.9);
        mWheelBoundWidth = (int) (mWheelBoundHeight / 3.4);

        RadialGradient radialGradient
                = new RadialGradient(0, 0, mWheelBoundWidth * 4,
                new int[]{
                        Color.WHITE,
//                        Color.parseColor("#BCBCBC"),
//                        Color.parseColor("#727272"),
//                        Color.parseColor("#3C3C3C"),
                        Color.parseColor("#000000")
                }, null, Shader.TileMode.CLAMP);


        paint2.setShader(radialGradient);

        linearGradientT
                = new LinearGradient(0, 0, 0, -mWheelBoundHeight / 2,
                new int[]{
                        Color.parseColor("#1A000000"),
                        Color.parseColor("#CC000000"),
//                        Color.BLACK,
                }, new float[]{
                0f, 0.99f
        }, Shader.TileMode.CLAMP);

        linearGradientB
                = new LinearGradient(0, 0, 0, mWheelBoundHeight / 2,
                new int[]{
                        Color.parseColor("#1A000000"),
                        Color.parseColor("#CC000000"),
//                        Color.BLACK,
                }, new float[]{
                0f, 0.99f
        }, Shader.TileMode.CLAMP);

        linearGradientC
                = new LinearGradient(-mWheelBoundWidth / 2, 0, mWheelBoundWidth / 2, 0,
                new int[]{
//                        Color.BLACK,
                        Color.parseColor("#F5000000"),
                        Color.parseColor("#00000000"),
                        Color.parseColor("#F5000000"),
//                        Color.BLACK,
                }, new float[]{
                0.08f, 0.6f, 0.88f
        }, Shader.TileMode.CLAMP);

    }

    private float dy = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#171717"));

        canvas.save();

        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawRoundRect(-mWheelBoundWidth / 2, -mWheelBoundHeight / 2, mWheelBoundWidth / 2, mWheelBoundHeight / 2, mWheelBoundWidth * 0.1f, mWheelBoundWidth * 0.1f, paint);

        paint.setColor(Color.parseColor("#262626"));
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawRoundRect(-mWheelBoundWidth / 2, -mWheelBoundHeight / 2, mWheelBoundWidth / 2, mWheelBoundHeight / 2, mWheelBoundWidth * 0.1f, mWheelBoundWidth * 0.1f, paint);

        canvas.drawRoundRect((float) (-mWheelBoundWidth / 2 * 0.8), (float) (-mWheelBoundHeight / 2 * 0.95), (float) (mWheelBoundWidth / 2 * 0.8), (float) (mWheelBoundHeight / 2 * 0.95), mWheelBoundWidth * 0.1f, mWheelBoundWidth * 0.1f, paint2);

        canvas.drawRoundRect((float) (-mWheelBoundWidth / 2 * 0.95) / 2, (float) (-mWheelBoundHeight / 2 * 0.95), (float) (mWheelBoundWidth / 2 * 0.95) / 2, (float) (mWheelBoundHeight / 2 * 0.95), mWheelBoundWidth * 0.1f, mWheelBoundWidth * 0.1f, paint4);

        canvas.restore();

        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);


        if (dy % 3 == 0) {
            roll_type1(canvas);
        } else if (dy % 3 == 1) {
            roll_type2(canvas);
        } else {
            roll_type3(canvas);
        }

        canvas.restore();

        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawRoundRect(
                (float) (-mWheelBoundWidth / 2 * 0.95) / 2,
                (float) (-mWheelBoundHeight / 2 * 0.95),
                (float) (mWheelBoundWidth / 2 * 0.95) / 2,
                (float) (mWheelBoundHeight / 2 * 0.95),
                mWheelBoundWidth * 0.1f,
                mWheelBoundWidth * 0.1f,
                paint3);

        paint5.setShader(linearGradientC);
        canvas.drawRoundRect(
                (float) (-mWheelBoundWidth * 0.95) / 2,
                (float) (-mWheelBoundHeight / 2),
                (float) (mWheelBoundWidth * 0.95) / 2,
                (float) (mWheelBoundHeight / 2),
                mWheelBoundWidth * 0.1f, mWheelBoundWidth * 0.1f,
                paint5);

        paint5.setShader(linearGradientT);
        canvas.drawRoundRect(
                (float) (-mWheelBoundWidth * 0.95) / 2,
                (float) (-mWheelBoundHeight / 2),
                (float) (mWheelBoundWidth * 0.95) / 2,
                0, mWheelBoundWidth * 0.1f, mWheelBoundWidth * 0.1f,
                paint5);

        paint5.setShader(linearGradientB);
        canvas.drawRoundRect(
                (float) (-mWheelBoundWidth * 0.95) / 2,
                0,
                (float) (mWheelBoundWidth * 0.95) / 2,
                (float) (mWheelBoundHeight / 2),
                mWheelBoundWidth * 0.1f, mWheelBoundWidth * 0.1f,
                paint5);

        canvas.restore();
    }


    private void roll_type1(Canvas canvas) {

        float w = (float) (mWheelBoundWidth / 2 * 0.95 / 2 * 0.9);

        float v = (float) (mWheelBoundHeight / 2 * 0.95) / 20;
        float tBlack = (float) (v * 0.5);
        float cWhite = (float) (v * 0.2);
        float bGray = (float) (v * 0.3);

        float x = 0;

        for (int i = 0; i < 20; i++) {
            x = v * (i);

            //灰-白-黑
            for (int z = 0; z < 3; z++) {
                canvas.drawRect(-w, -bGray - x, w, 0 - x, mPaintGray);
                canvas.drawRect(-w, -bGray - cWhite - x, w, -bGray - x, mPaintWhite);
                canvas.drawRect(-w, -bGray - cWhite - tBlack - x, w, -bGray - cWhite - x, mPaintBlack);
            }
        }

        x = 0;

        //黑-白-灰
        for (int i = 0; i < 20; i++) {
            x = v * (i);
            for (int z = 0; z < 3; z++) {
                canvas.drawRect(-w, tBlack + x, w, 0 + x, mPaintBlack);
                canvas.drawRect(-w, +tBlack + cWhite + x, w, +tBlack + x, mPaintWhite);
                canvas.drawRect(-w, +bGray + cWhite + tBlack + x, w, +bGray + tBlack + x, mPaintGray);
            }
        }

    }

    private void roll_type2(Canvas canvas) {

        float w = (float) (mWheelBoundWidth / 2 * 0.95 / 2 * 0.9);

        float v = (float) (mWheelBoundHeight / 2 * 0.95) / 20;
        float tBlack = (float) (v * 0.5);
        float cWhite = (float) (v * 0.2);
        float bGray = (float) (v * 0.3);

        float x = 0;

        for (int i = 0; i < 20; i++) {
            x = v * (i);

            //白-黑-灰
            for (int z = 0; z < 3; z++) {
                canvas.drawRect(-w, -cWhite - x, w, 0 - x, mPaintWhite);
                canvas.drawRect(-w, -cWhite - tBlack - x, w, -cWhite - x, mPaintBlack);
                canvas.drawRect(-w, -cWhite - tBlack - bGray - x, w, -cWhite - tBlack - x, mPaintGray);
            }
        }

        x = 0;

        //灰-黑-白
        for (int i = 0; i < 20; i++) {
            x = v * (i);
            for (int z = 0; z < 3; z++) {
                canvas.drawRect(-w, +bGray + x, w, 0 + x, mPaintGray);
                canvas.drawRect(-w, +bGray + tBlack + x, w, bGray + x, mPaintBlack);
                canvas.drawRect(-w, +bGray + tBlack + cWhite + x, w, +bGray + tBlack + x, mPaintWhite);
            }
        }

    }

    private void roll_type3(Canvas canvas) {

        float w = (float) (mWheelBoundWidth / 2 * 0.95 / 2 * 0.9);

        float v = (float) (mWheelBoundHeight / 2 * 0.95) / 20;
        float tBlack = (float) (v * 0.5);
        float cWhite = (float) (v * 0.2);
        float bGray = (float) (v * 0.3);

        float x = 0;

        for (int i = 0; i < 20; i++) {
            x = v * (i);

            //黑-灰-白
            for (int z = 0; z < 3; z++) {
                canvas.drawRect(-w, -tBlack - x, w, 0 - x, mPaintBlack);
                canvas.drawRect(-w, -tBlack - bGray - x, w, -tBlack - x, mPaintGray);
                canvas.drawRect(-w, -tBlack - bGray - cWhite - x, w, -tBlack - bGray - x, mPaintWhite);
            }
        }

        x = 0;

        //黑-白-灰
        for (int i = 0; i < 20; i++) {
            x = v * (i);
            for (int z = 0; z < 3; z++) {
                canvas.drawRect(-w, +cWhite + x, w, 0 + x, mPaintWhite);
                canvas.drawRect(-w, +cWhite + bGray + x, w, +cWhite + x, mPaintGray);
                canvas.drawRect(-w, +tBlack + cWhite + bGray + x, w, +cWhite + bGray + x, mPaintBlack);
            }
        }

    }

    float startY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startY = event.getRawY();

                break;

            case MotionEvent.ACTION_MOVE:

                float rawY = event.getRawY();

                float dd = startY - rawY;

                if (Math.abs(dd) > 15) {
                    if (dd > 0) {
                        dy = dy + 1;
                    } else {
                        dy = dy - 1;
                    }

                    if (dy < 0) {
                        dy = 0;
                    }else {
                        playMusic();
                    }

                    if (mOnRollListener != null) {
                        mOnRollListener.onRollResult(dy);
                    }

                    startY = rawY;
                }


                invalidate();


                break;

            case MotionEvent.ACTION_UP:

                break;

            default:
                break;

        }


        return true;

    }

    private void playMusic() {

        try {
            player.reset();
            AssetFileDescriptor fileDescriptor = assetManager.openFd("11.mp3");
            player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getStartOffset());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OnRollListener mOnRollListener;

    public void setOnRollListener(OnRollListener onRollListener) {
        mOnRollListener = onRollListener;
    }

    public interface OnRollListener {

        void onRollResult(float num);

    }
}
