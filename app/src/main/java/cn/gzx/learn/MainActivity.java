package cn.gzx.learn;

import android.content.Intent;
import cn.gzx.learn.activity.RecyclerViewListActivity;
import cn.gzx.learn.activity.ToolBarActivity;
import cn.gzx.learn.base.BaseListActivity;
import cn.gzx.learn.utils.ToastUtil;

public class MainActivity extends BaseListActivity {

    private static final int POS_TOOLBAR = 0;
    private static final int POS_BLENDENT = 1;
    private static final int POS_RECYCLER_VIEW = 2;
    private static final int POS_CARD_VIEW = 3;
    private static final int POS_ANIMATION = 4;
    private static final int POS_GRID_LAYOUT = 5;

    @Override
    protected int contentArrayRes() {
        return R.array.contentArray;
    }

    @Override
    protected void onItemClick(String content, int position) {
        Intent intent = null;
        switch (position) {
            case POS_TOOLBAR:
                intent = new Intent(this, ToolBarActivity.class);
                break;
            case POS_RECYCLER_VIEW:
                intent = new Intent(this, RecyclerViewListActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        } else {
            ToastUtil.showToast(this, getResources().getString(R.string.comming_soon));
        }
    }
}
