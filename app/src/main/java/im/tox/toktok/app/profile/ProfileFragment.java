package im.tox.toktok.app.profile;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import im.tox.toktok.R;
import im.tox.toktok.app.AppWork;
import im.tox.toktok.app.CompatUtil;
import im.tox.toktok.app.simple_dialogs.SimpleShareDialogDesign;
import im.tox.toktok.app.simple_dialogs.SimpleStatusDialogDesign;
import im.tox.toktok.app.simple_dialogs.SimpleTextDialogDesign;

public final class ProfileFragment extends Fragment {

    @Override
    public CoordinatorLayout onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        super.onCreate(savedState);
        CoordinatorLayout view = (CoordinatorLayout) inflater.inflate(R.layout.activity_profile, container, false);

        getActivity().getWindow().setStatusBarColor(Color.parseColor("#2b000000"));

        view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        Toolbar toolbar = view.findViewById(R.id.profile_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_menu);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final  TextView toxid=  view.findViewById(R.id.tv_tox_id);
        byte [] key =AppWork.getInstance().getTox().getSelfAddress();
        String keyStr= AppWork.bytesToHexStr(key);
        toxid.setText(keyStr);
        final DrawerLayout drawer = getActivity().findViewById(R.id.home_layout);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        RelativeLayout mShareIDButton = view.findViewById(R.id.profile_share_id);
        mShareIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleShareDialogDesign dial = new SimpleShareDialogDesign(getActivity(), new SimpleShareDialogDesign.CopyListener() {
                    @Override
                    public void onCopy() {
                        try {
                            //获取剪贴板管理器
                            ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                            // 创建普通字符型ClipData
                            String copyStr = toxid.getText().toString();
                            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
                            // 将ClipData内容放到系统剪贴板里。
                            cm.setPrimaryClip(mClipData);
                            Toast.makeText(getActivity(), R.string.copy_successful, Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Toast.makeText(getActivity(), R.string.copy_failed, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dial.show();
            }
        });

        TextView mChangeNickname = view.findViewById(R.id.profile_change_nickname);
        mChangeNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleTextDialogDesign dial = new SimpleTextDialogDesign(
                        getActivity(),
                        getResources().getString(R.string.profile_nickname),
                        CompatUtil.getColor(getResources(), R.color.homeColorToolbar),
                        R.drawable.ic_person_black_48dp,
                        getResources().getString(R.string.sample_user_name),
                        null
                );
                dial.show();
            }
        });

        TextView mChangeStatusMessage = view.findViewById(R.id.profile_change_status_text);
        mChangeStatusMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleTextDialogDesign dial = new SimpleTextDialogDesign(
                        getActivity(),
                        getResources().getString(R.string.profile_status_message),
                        CompatUtil.getColor(getResources(), R.color.homeColorToolbar),
                        R.drawable.ic_person_black_48dp,
                        getResources().getString(R.string.sample_status_message),
                        null
                );
                dial.show();
            }
        });

        TextView mChangeStatus = view.findViewById(R.id.profile_change_status);
        mChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleStatusDialogDesign dial = new SimpleStatusDialogDesign(getActivity(), 0);
                dial.show();
            }
        });

        TextView mChangeReject = view.findViewById(R.id.profile_change_reject_call);
        mChangeReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RejectedCallMessages.class));
            }
        });

        return view;
    }

}
