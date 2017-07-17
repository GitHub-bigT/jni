package ricoh.jnipractice.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import ricoh.jnipractice.R;
import ricoh.jnipractice.publicnative.PublicNative;

/**
 * Created by bigT on 2017/7/17.
 */

public class PublicNativeActivity extends Activity {
    private TextView mTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        initViews();
    }

    private void initViews() {
        mTextView = (TextView) findViewById(R.id.tv_public);
        mTextView.setText(PublicNative.getStringJNI());
    }
}
