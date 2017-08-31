package com.hxl.jiazai;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshLayout pull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pull = (PullToRefreshLayout) findViewById(R.id.pull);
        pull.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        // 千万别忘了告诉控件刷新完毕了哦！
                        pull.refreshFinish(PullToRefreshLayout.SUCCEED);

                        if (NetWorkUsefulUtils.getActiveNetwork(MainActivity.this)) {
                            Toast.makeText(MainActivity.this, "刷新", Toast.LENGTH_SHORT).show();
                            pull.refreshFinish(PullToRefreshLayout.SUCCEED);
                        } else {
                            Toast.makeText(MainActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                            pull.refreshFinish(PullToRefreshLayout.FAIL);
                        }
                    }
                }.sendEmptyMessageDelayed(0, 500);

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        // 千万别忘了告诉控件刷新完毕了哦！
                        pull.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        Toast.makeText(MainActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
                    }
                }.sendEmptyMessageDelayed(0, 500);

            }
        });
    }
}
