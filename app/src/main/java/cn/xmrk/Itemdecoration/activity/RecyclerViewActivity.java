package cn.xmrk.Itemdecoration.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import cn.xmrk.Itemdecoration.R;
import cn.xmrk.Itemdecoration.adapter.RecyclerAdapter;
import cn.xmrk.Itemdecoration.decoration.SpacesItemDecoration;
import cn.xmrk.Itemdecoration.pojo.AdapterInfo;

/**
 * 作者：请叫我百米冲刺 on 2016/12/6 下午1:27
 * 邮箱：mail@hezhilin.cc
 */

public class RecyclerViewActivity extends AppCompatActivity {

    public static final int LINEAR_VERTICAL = 1;
    public static final int LINEAR_HORIZONTAL = 2;
    public static final int GRID_VERTICAL = 3;
    public static final int GRID_HORIZONTAL = 4;
    public static final int STAGGER_VERTICAL = 5;
    public static final int STAGGER_HORIZONTAL = 6;

    private int type;

    private RecyclerView rv_content;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;

    public static void startRecyclerViewActivity(Activity activity, int type) {
        Intent intent = new Intent(activity, RecyclerViewActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        setStatusColor();
        initView();
        chooseLayoutManager();
        initRvContent();
    }

    public void setStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private List<AdapterInfo> getAdapterInfos() {
        List<AdapterInfo> infos = new ArrayList<>();
        AdapterInfo info = null;
        for (int i = 0; i < 40; i++) {
            info = new AdapterInfo();
            if ((type == STAGGER_VERTICAL || type == STAGGER_HORIZONTAL) && i % 3 == 0) {
                info.message = "";
            } else {
                info.message = "item" + (i + 1);
            }
            infos.add(info);
        }
        return infos;
    }


    private void chooseLayoutManager() {
        switch (type) {
            case LINEAR_VERTICAL:
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                break;
            case LINEAR_HORIZONTAL:
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                break;
            case GRID_VERTICAL:
                mLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
                final GridLayoutManager manager = (GridLayoutManager) mLayoutManager;
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return position % (manager.getSpanCount() + 1) == 0 ? manager.getSpanCount() : 1;
                    }
                });
                break;
            case GRID_HORIZONTAL:
                mLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false);
                break;
            case STAGGER_VERTICAL:
                mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                break;
            case STAGGER_HORIZONTAL:
                mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
                break;
        }
    }

    private void initRvContent() {
        mAdapter = new RecyclerAdapter(getAdapterInfos());
        rv_content.setAdapter(mAdapter);
        rv_content.setLayoutManager(mLayoutManager);

        //添加ItemDecoration，item之间的间隔
        int leftRight = dip2px(15);
        int topBottom = dip2px(15);

        rv_content.addItemDecoration(new SpacesItemDecoration(leftRight, topBottom));
        //rv_content.addItemDecoration(new SpacesItemDecoration(dip2px(1), dip2px(1), Color.BLUE));
    }

    public int dip2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    private void initView() {
        type = getIntent().getExtras().getInt("type");
        rv_content = (RecyclerView) findViewById(R.id.rv_content);
    }
}
