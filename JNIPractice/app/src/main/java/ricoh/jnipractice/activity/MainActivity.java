package ricoh.jnipractice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ricoh.jnipractice.constants.Constants;
import ricoh.jnipractice.other.DividerItemDecoration;
import ricoh.jnipractice.R;

public class MainActivity extends Activity {
    private RecyclerView mRecycleView;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initList();
    }

    private void initList() {
        Log.i(Constants.TAG,"activity: init list");
        mList.add("hello world");
        mList.add("public native");
        mList.add("public native111");
        mList.add("public native222");
        mList.add("public native333");
        mList.add("public native444");
    }

    private void initViews() {
        mRecycleView = (RecyclerView) findViewById(R.id.main_rv);
        RvAdapter mAdapter = new RvAdapter();
        Log.i(Constants.TAG,"activity: new adapter");
        mRecycleView.setAdapter(mAdapter);
        Log.i(Constants.TAG,"activity: set adapter");
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        Log.i(Constants.TAG,"activity: set layout manager");
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        Log.i(Constants.TAG,"activity: set item decoration");
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    //hello world
                    case 0:
                        Intent intent1 = new Intent(MainActivity.this,HelloWorldActivity.class);
                        startActivity(intent1);
                        break;
                    //public native
                    case 1:
                        Intent intent2 = new Intent(MainActivity.this,PublicNativeActivity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });
        Log.i(Constants.TAG,"set listener");
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            Log.i(Constants.TAG,"adapter: setOnItemClickListener");
            this.onItemClickListener = onItemClickListener;
            Log.i(Constants.TAG,"adapter: set adapter");
        }

        @Override
        public RvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i(Constants.TAG,"adapter: onCreateViewHolder");
            ViewHolder vh = new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.rv_item, parent, false));
            Log.i(Constants.TAG,"adapter: new viewholder");
            return vh;
        }

        @Override
        public void onBindViewHolder(final RvAdapter.ViewHolder holder, int position) {
            Log.i(Constants.TAG,"adapter: onBindViewHolder");
            holder.rv_item_tv.setText(mList.get(position));
            Log.i(Constants.TAG,"adapter: set text");
            Log.i(Constants.TAG,"mmm"+mList.get(position));
            if (onItemClickListener != null) {
                holder.rv_item_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(v, holder.getLayoutPosition());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            Log.i(Constants.TAG,"adapter: getItemCount  ");
            return mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView rv_item_tv;

            public ViewHolder(View itemView) {
                super(itemView);
                Log.i(Constants.TAG,"adapter: ViewHolder construction");
                rv_item_tv = (TextView) itemView.findViewById(R.id.rv_item_tv);
            }
        }
    }
}
