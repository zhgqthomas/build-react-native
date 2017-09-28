package com.demo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Map;

import javax.annotation.Nullable;

public class MyViewManager extends SimpleViewManager<ImageView> {

    private static String TAG = MyViewManager.class.getName();

    private ThemedReactContext mContext;
    private ImageView mView;

    private Handler mTimeHandler = new Handler(Looper.getMainLooper());

    private Runnable mRunable = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "Runnable View id : " + mView.getId());
            WritableMap map = Arguments.createMap();
            map.putString("method", "Runnable");
            mContext.getJSModule(RCTEventEmitter.class)
                    .receiveEvent(mView.getId(), "onMapPress", map);
        }
    };

    @Override
    public String getName() {
        return "RCTCustomView";
    }

    @Override
    protected ImageView createViewInstance(ThemedReactContext reactContext) {
        mContext = reactContext;
        mView = new ImageView(reactContext);
        mView.setBackgroundColor(Color.BLUE);
        Log.d(TAG, "create view id: " + mView.getId());
        return mView;
    }

    @Override
    protected void addEventEmitters(final ThemedReactContext reactContext, ImageView view) {
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                WritableMap argu = Arguments.createMap();
                argu.putString("method", "View Click");
                mContext.getJSModule(RCTEventEmitter.class)
                        .receiveEvent(mView.getId(), "onMapPress", argu);
                Log.d(TAG, "view click View id : " + mView.getId());
            }
        });

        WritableMap map = Arguments.createMap();
        map.putString("method", "addEventEmitters");
        mContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(mView.getId(), "onMapPress", map);
        Log.d(TAG, "addEventEmitters View id : " + mView.getId());
    }

    @Nullable
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onMapPress", MapBuilder.of("registrationName", "onMapPress")
        );
    }
}
