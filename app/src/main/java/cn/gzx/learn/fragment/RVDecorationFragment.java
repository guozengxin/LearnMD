package cn.gzx.learn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.gzx.learn.R;
import cn.gzx.learn.decoration.DividerItemDecoration;
import cn.gzx.learn.utils.ArrayUtil;
import cn.gzx.learn.utils.DrawableUtil;

import java.lang.reflect.Array;

/**
 * Created by guozengxin on 2016/1/5.
 */
public class RVDecorationFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private String[] contentArray;

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_decoration, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contentArray = getResources().getStringArray(R.array.multilayoutArray);
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        setupActionBar(toolbar);
        enableHomeAsUp();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(DrawableUtil.getDrawable(getActivity(), R.drawable.list_divider)));
        recyclerView.setAdapter(new DecorationAdapter());
    }

    private class DecorationAdapter extends RecyclerView.Adapter<DecorationViewHolder> {

        private  LayoutInflater layoutInflater;

        public DecorationAdapter() {
            layoutInflater = LayoutInflater.from(getActivity());
        }

        @Override
        public DecorationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DecorationViewHolder(layoutInflater.inflate(R.layout.list_item_rect, parent, false));
        }

        @Override
        public void onBindViewHolder(DecorationViewHolder holder, int position) {
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

    private class DecorationViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public DecorationViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}


