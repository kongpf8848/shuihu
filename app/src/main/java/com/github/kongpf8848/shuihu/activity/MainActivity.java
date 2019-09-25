package com.github.kongpf8848.shuihu.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.github.kongpf8848.shuihu.R;
import com.github.kongpf8848.shuihu.adapter.FragmentStateAdapter;
import com.github.kongpf8848.shuihu.fragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.indexViewPage)
    ViewPager indexViewPage;
    @BindView(R.id.fastChange)
    AppCompatSeekBar fastChange;
    FragmentStateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        setSupportActionBar(toolbar);

        fastChange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    Toast.makeText(MainActivity.this, "第" + (progress + 1), Toast.LENGTH_SHORT).show();
                    indexViewPage.setCurrentItem(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < Constants.NAME_LIST.length; i++) {
            PersonFragment fragment = new PersonFragment();
            Bundle bundle = new Bundle();
            String desc = "原为山东省郓（yùn）城县押司，眉似卧蚕，眼似龙凤，唇红囗方，身材矮小，面目黝黑。后为梁山起义军领袖，在一百零八将中稳坐梁山泊第一把交椅，为三十六天罡（gāng）星之首的天魁星。因私放晁盖等人，被小妾阎婆惜捉住把柄，以至于杀了阎婆惜后连夜逃走。期间结交诸多英雄好汉，辗转周折上了梁山。并接受了九天玄女赠送的天书，之后带兵征讨祝家庄和高唐州。晁盖死后继任梁山第三任寨主。接受朝廷的招安，为了报效国家接连出征辽国、田虎、王庆、方腊等，屡立战功，被封为武德大夫，楚州安抚使兼兵马都总管。在本书结局被朝廷立庙，因为十分灵验，得到当地人民世世代代的供奉";
            PersonInfo p = new PersonInfo(Constants.NAME_LIST[i], Constants.NICK_LIST[i], Constants.STAR_LIST[i], Constants.SORT_LIST[i], desc, String.format("%d.jpg", i + 1));
            bundle.putSerializable("data", p);
            fragment.setArguments(bundle);
            list.add(fragment);
        }
        adapter = new FragmentStateAdapter(getSupportFragmentManager(), list);
        indexViewPage.setAdapter(adapter);
        indexViewPage.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                fastChange.setProgress(position);
            }
        });
        int index=SharedPreferencesHelper.getInt(this,"config","index",0);
        indexViewPage.setCurrentItem(index);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.indexMenuChange:
                onChange();
                break;
        }
        return true;
    }

    private void onChange() {

        String baseDir=SharedPreferencesHelper.getString(this,"config","baseDir","imgs1");
        if (baseDir.equals("imgs1")) {
            baseDir = "imgs0";
        } else {
            baseDir = "imgs1";
        }
        SharedPreferencesHelper.putString(this,"config","baseDir",baseDir);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for(int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if(fragment!=null &&fragment.isVisible()) {
                if(fragment instanceof IEvent){
                    ((IEvent)fragment).onBaseDirChange();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferencesHelper.putInt(this,"config","index",indexViewPage.getCurrentItem());
    }
}
