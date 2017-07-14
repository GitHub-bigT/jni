package ricoh.jnipractice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView main_rv;
    private List<String> featureList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        initViews();
    }

    private void initList() {
        featureList.add("hello world");
    }

    private void initViews() {
        main_rv = (RecyclerView) findViewById(R.id.main_rv);
        RvAdapter mAdapter = new RvAdapter();
        main_rv.setAdapter(mAdapter);
        main_rv.setLayoutManager(new LinearLayoutManager(this));
        main_rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    //hello world
                    case 0:
                        Toast.makeText(MainActivity.this,"hello world",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public RvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder vh = new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.rv_item, parent, false));
            return vh;
        }

        @Override
        public void onBindViewHolder(final RvAdapter.ViewHolder holder, int position) {
            holder.rv_item_tv.setText(featureList.get(position));
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
            return featureList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView rv_item_tv;

            public ViewHolder(View itemView) {
                super(itemView);
                rv_item_tv = (TextView) itemView.findViewById(R.id.rv_item_tv);
            }
        }
    }
}
