package com.example.leo.activitystudy;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.leo.activitystudy.fragment.Right_2_Fragment;

public class SixActivity extends BaseActivity implements View.OnClickListener {

    private Button pbtnChangeFragment;
    Configuration mConfiguration ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.six_layout);

        pbtnChangeFragment = (Button)findViewById(R.id.pbtnOpenFragment);
        pbtnChangeFragment.setOnClickListener(this);
        mConfiguration = this.getResources().getConfiguration();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pbtnOpenFragment:
                int ori = mConfiguration.orientation;
                if (ori == mConfiguration.ORIENTATION_LANDSCAPE)//横屏
                {
                    Toast.makeText(SixActivity.this, "你是横屏", Toast.LENGTH_SHORT).show();
                    ChangedFragment();
                } else if (ori == mConfiguration.ORIENTATION_PORTRAIT)//竖屏
                {
                    Toast.makeText(SixActivity.this, "你是竖屏", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    private void ChangedFragment()
    {
        Right_2_Fragment fragment= new Right_2_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentLayoutRight,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
