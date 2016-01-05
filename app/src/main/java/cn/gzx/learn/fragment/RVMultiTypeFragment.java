package cn.gzx.learn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.gzx.learn.R;
import cn.gzx.learn.utils.ArrayUtil;

/**
 * Created by guozengxin on 2016/1/5.
 */
public class RVMultiTypeFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private String[] contentArray;

    private static final int TYPE_LEFT = 0;
    private static final int TYPE_RIGHT = 1;

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
        contentArray = getResources().getStringArray(R.array.multitypeArray);
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        setupActionBar(toolbar);
        enableHomeAsUp();
        setActionBarTitle(R.string.label__rv_multilayout);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MultiTypeAdapter());
    }

    private class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private LayoutInflater layoutInflater;

        public MultiTypeAdapter() {
            layoutInflater = LayoutInflater.from(getActivity());
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_LEFT) {
                return new LeftViewHolder(layoutInflater.inflate(R.layout.list_item_left, parent, false));
            } else {
                return new RightViewHolder(layoutInflater.inflate(R.layout.list_item_right, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder.getItemViewType() == TYPE_LEFT) {
                LeftViewHolder leftHolder = (LeftViewHolder) holder;
                leftHolder.leftView.setText(contentArray[position]);
            } else {
                RightViewHolder rightHolder = (RightViewHolder) holder;
                rightHolder.rightView.setText(contentArray[position]);
            }
        }

        @Override
        public int getItemCount() {
            if (ArrayUtil.isValid(contentArray)) {
                return contentArray.length;
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? TYPE_LEFT : TYPE_RIGHT;
        }
    }

    private class LeftViewHolder extends RecyclerView.ViewHolder {
        TextView leftView;

        public LeftViewHolder(View itemView) {
            super(itemView);
            leftView = (TextView) itemView.findViewById(R.id.leftTextView);
        }
    }

    private class RightViewHolder extends RecyclerView.ViewHolder {
        TextView rightView;

        public RightViewHolder(View itemView) {
            super(itemView);
            rightView = (TextView) itemView.findViewById(R.id.rightTextView);
        }
    }
}
