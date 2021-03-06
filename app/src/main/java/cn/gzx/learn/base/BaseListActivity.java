package cn.gzx.learn.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.gzx.learn.R;
import cn.gzx.learn.utils.ArrayUtil;

/**
 * Created by guozengxin on 2016/1/5.
 */
public abstract class BaseListActivity extends BaseActivity {

    protected RecyclerView recyclerView;
    protected Toolbar toolbar;
    protected String[] contentArray;

    protected abstract int contentArrayRes();
    protected abstract void onItemClick(String content, int position);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_recyclerview);
        if (contentArrayRes() < 0) {
            finish();
        }
        contentArray = getResources().getStringArray(contentArrayRes());
        setupTooBar();
        setupRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTooBar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void enableHomeAsUp() {
        enableHomeAsUp(-1);
    }

    protected void enableHomeAsUp(int indicator) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (indicator > 0) {
            getSupportActionBar().setHomeAsUpIndicator(indicator);
        }
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new BaseListAdapter());
    }

    private class BaseListAdapter extends RecyclerView.Adapter<ViewHolder> {
        private LayoutInflater layoutInflater;
        public BaseListAdapter() {
            layoutInflater = LayoutInflater.from(BaseListActivity.this);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(layoutInflater.inflate(R.layout.list_item_round, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(contentArray[position]);
        }

        @Override
        public int getItemCount() {
            if (ArrayUtil.isValid(contentArray)) {
                return contentArray.length;
            }
            return 0;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = getLayoutPosition();
                    onItemClick(contentArray[layoutPosition], layoutPosition);
                }
            });
        }
    }
}
