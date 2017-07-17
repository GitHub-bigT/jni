package ricoh.jnipractice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by bigT on 2017/7/14.
 */

public class HelloWorldActivity extends Activity {
    private TextView mTextView;
    static {
        System.loadLibrary("helloworld");
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloworld);
        initViews();
    }

    private void initViews() {
        mTextView = (TextView) findViewById(R.id.tv_helloworld);
        mTextView.setText(stringFromJNI());
    }

    private native String stringFromJNI();
}
