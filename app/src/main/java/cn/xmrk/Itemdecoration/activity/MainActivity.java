package cn.xmrk.Itemdecoration.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.xmrk.Itemdecoration.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_linear_v;
    private Button btn_linear_h;
    private Button btn_grid_v;
    private Button btn_grid_h;
    private Button btn_stagger_v;
    private Button btn_stagger_h;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusColor();
        initView();
    }


    public void setStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void initView() {
        btn_linear_v = (Button) findViewById(R.id.btn_linear_v);
        btn_linear_h = (Button) findViewById(R.id.btn_linear_h);
        btn_grid_v = (Button) findViewById(R.id.btn_grid_v);
        btn_grid_h = (Button) findViewById(R.id.btn_grid_h);
        btn_stagger_v = (Button) findViewById(R.id.btn_stagger_v);
        btn_stagger_h = (Button) findViewById(R.id.btn_stagger_h);


        btn_linear_v.setOnClickListener(this);
        btn_linear_h.setOnClickListener(this);
        btn_grid_v.setOnClickListener(this);
        btn_grid_h.setOnClickListener(this);
        btn_stagger_v.setOnClickListener(this);
        btn_stagger_h.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_linear_v:
                RecyclerViewActivity.startRecyclerViewActivity(this, RecyclerViewActivity.LINEAR_VERTICAL);
                break;
            case R.id.btn_linear_h:
                RecyclerViewActivity.startRecyclerViewActivity(this, RecyclerViewActivity.LINEAR_HORIZONTAL);
                break;
            case R.id.btn_grid_v:
                RecyclerViewActivity.startRecyclerViewActivity(this, RecyclerViewActivity.GRID_VERTICAL);
                break;
            case R.id.btn_grid_h:
                RecyclerViewActivity.startRecyclerViewActivity(this, RecyclerViewActivity.GRID_HORIZONTAL);
                break;
            case R.id.btn_stagger_v:
                RecyclerViewActivity.startRecyclerViewActivity(this, RecyclerViewActivity.STAGGER_VERTICAL);
                break;
            case R.id.btn_stagger_h:
                RecyclerViewActivity.startRecyclerViewActivity(this, RecyclerViewActivity.STAGGER_HORIZONTAL);
                break;
        }
    }
}
