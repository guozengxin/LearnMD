package cn.gzx.learn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.*;
import android.text.Layout;
import android.view.*;
import android.widget.TextView;
import cn.gzx.learn.Constants;
import cn.gzx.learn.R;
import cn.gzx.learn.utils.CollectionUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by guozengxin on 2016/1/5.
 */
public class RVCommonFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private LinkedList<String> contentList;
    private CommonAdapter adapter;
    private boolean animated;

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.base_recyclerview, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        animated = getArguments().getBoolean(Constants.BUNDLE_KEY_ANIMATED, false);
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        setupActionBar(toolbar);
        setActionBarTitle(animated ? R.string.label__rv_animated : R.string.label__rv_multilayout);
        enableHomeAsUp();
        String[] contentArray = getResources().getStringArray(R.array.multitypeArray);
        contentList = new LinkedList<>(Arrays.asList(contentArray));
        recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(animated ? R.menu.menu_animated : R.menu.menu_multilayout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RecyclerView.LayoutManager layoutManager = null;
        switch (item.getItemId()) {
            case R.id.action_linearlayout:
                layoutManager = new LinearLayoutManager(getActivity());
                break;
            case R.id.action_gridlayout:
                layoutManager = new GridLayoutManager(getActivity(), 3);
                break;
            case R.id.action_staggerlayout:
                layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
                break;
            case R.id.action_add:
                contentList.add(1, generateItemString());
                adapter.notifyItemInserted(1);
                break;
            case R.id.action_delete:
                if (CollectionUtil.isValid(contentList)) {
                    contentList.removeFirst();
                    adapter.notifyItemRemoved(0);
                }
                break;
        }
        if (layoutManager != null) {
            recyclerView.setLayoutManager(layoutManager);
        }
        return super.onOptionsItemSelected(item);
    }

    private String generateItemString() {
        Random random = new Random();
        int randomInt = random.nextInt(100);
        return "new Item " + randomInt;
    }

    private class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> {

        private LayoutInflater layoutInflater;

        public CommonAdapter() {
            layoutInflater = LayoutInflater.from(getActivity());
        }

        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CommonViewHolder(layoutInflater.inflate(R.layout.list_item_round, parent, false));
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, int position) {
            holder.textView.setText(contentList.get(position));
        }

        @Override
        public int getItemCount() {
            if (CollectionUtil.isValid(contentList)) {
                return contentList.size();
            }
            return 0;
        }
    }

    private class CommonViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public CommonViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView;
        }
    }
}
