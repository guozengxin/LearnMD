package cn.gzx.learn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import cn.gzx.learn.Constants;
import cn.gzx.learn.R;
import cn.gzx.learn.base.BaseActivity;
import cn.gzx.learn.fragment.BaseFragment;
import cn.gzx.learn.fragment.RVCommonFragment;
import cn.gzx.learn.fragment.RVDecorationFragment;
import cn.gzx.learn.fragment.RVMultiTypeFragment;

/**
 * Created by guozengxin on 2016/1/5.
 */
public class RecyclerViewDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        int type = getIntent().getIntExtra(Constants.INTENT_KEY_INITIAL_VALUE, -1);
        if (type < 0) {
            finish();
        }
        handleType(type);
    }

    private void handleType(int type) {
        BaseFragment fragment = null;
        Bundle bundle = new Bundle();
        switch (type) {
            case Constants.RV_MULTITYPE:
                fragment = new RVMultiTypeFragment();
                break;
            case Constants.RV_MULTILAYOUT:
                fragment = new RVCommonFragment();
                break;
            case Constants.RV_ANIMATION:
                fragment = new RVCommonFragment();
                bundle.putBoolean(Constants.BUNDLE_KEY_ANIMATED, true);
                break;
            case Constants.RV_DECORATOR:
                fragment = new RVDecorationFragment();
                break;
        }
        if (fragment != null) {
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }
    }
}
