package cn.gzx.learn.activity;

import android.content.Intent;
import android.os.Bundle;
import cn.gzx.learn.Constants;
import cn.gzx.learn.R;
import cn.gzx.learn.base.BaseListActivity;

/**
 * Created by guozengxin on 2016/1/5.
 */
public class RecyclerViewListActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableHomeAsUp();
    }

    @Override
    protected void onItemClick(String content, int position) {
        Intent intent = new Intent(this, RecyclerViewDetailActivity.class);
        intent.putExtra(Constants.INTENT_KEY_INITIAL_VALUE, position);
        startActivity(intent);
    }

    @Override
    protected int contentArrayRes() {
        return R.array.optionRecyclerViewArray;
    }
}
