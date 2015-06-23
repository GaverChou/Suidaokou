package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;

import com.gaverchou.suidaokou.adapter.WantGoGridAdapter;
import com.gaverchou.suidaokou.model.WantgoItem;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by mrgaver on 15-6-11.
 */
public class WangoDetail extends BaseActivity {
    @InjectView(R.id.wango_grid)
    GridView gridView;
    WantGoGridAdapter wantGoGridAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wantgo_detail_ayout);
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar.setBackgroundColor(mBaseColor);
        mToolbar.setTitle(imgId.length+"人想去");
        wantGoGridAdapter = new WantGoGridAdapter(getDummyData());
        wantGoGridAdapter.setDetail(true);
        gridView.setAdapter(wantGoGridAdapter);
    }

    static int[] imgId = new int[]{R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4,
            R.mipmap.img5, R.mipmap.img6, R.mipmap.img7, R.mipmap.img8, R.mipmap.img9, R.mipmap.img10};

    public static ArrayList<WantgoItem> getDummyData() {
        ArrayList<WantgoItem> items = new ArrayList<WantgoItem>();
        for (int i = 0; i < imgId.length; i++) {
            WantgoItem actionItem = new WantgoItem();
            actionItem.imgId = imgId[i];
            items.add(actionItem);
        }
        return items;
    }
}
