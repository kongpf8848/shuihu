package com.github.kongpf8848.shuihu.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kongpf8848.shuihu.R;
import com.github.kongpf8848.shuihu.activity.IEvent;
import com.github.kongpf8848.shuihu.activity.PersonInfo;
import com.github.kongpf8848.shuihu.activity.SharedPreferencesHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;

import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonFragment extends Fragment implements IEvent {

    @BindView(R.id.personProfile)
    ImageView personProfile;
    @BindView(R.id.personName)
    TextView personName;
    private PersonInfo p;

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            p = (PersonInfo) bundle.getSerializable("data");
            refresh();
        }
    }

    public void refresh(){
        if (p != null) {
            try {
                personName.setText(p.getNick() + ":" + p.getName());
                String baseDir= SharedPreferencesHelper.getString(context,"config","baseDir","imgs1");
                InputStream is = context.getAssets().open(String.format("%s/%s",baseDir,p.getImage()));
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                if (bitmap != null) {
                    personProfile.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.slideTop)
    public void onDetail() {

        BottomSheetDialog dialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.botom_sheet, null);
        if (p != null) {
            Chip personNick = (Chip) view.findViewById(R.id.personNick);
            Chip personStar = (Chip) view.findViewById(R.id.personStar);
            Chip personSort = (Chip) view.findViewById(R.id.personSort);
            TextView personDesc = (TextView) view.findViewById(R.id.personDesc);
            personNick.setText(p.getNick());
            personStar.setText(p.getStar());
            personSort.setText(p.getSort());
            personDesc.setText(p.getDesc());
        }
        dialog.setContentView(view);
        dialog.show();


    }

    @Override
    public void onBaseDirChange() {
        refresh();
    }
}
