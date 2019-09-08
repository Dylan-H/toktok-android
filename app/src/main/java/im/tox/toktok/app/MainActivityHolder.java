package im.tox.toktok.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import im.tox.toktok.R;
import im.tox.toktok.app.event.ConnectionStatusEvent;
import im.tox.toktok.app.event.FriendRequestEvent;
import im.tox.toktok.app.main.HomeSearch;
import im.tox.toktok.app.main.MainFragment;
import im.tox.toktok.app.main.friends.SlideInContactsLayout;
import im.tox.toktok.app.profile.ProfileFragment;

public final class MainActivityHolder extends AppCompatActivity {

    final  String TAG ="MainActivityHolder";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 671;
    @Nullable
    private LinearLayout activeTab = null;
    @Nullable
    private SlideInContactsLayout activeContacts = null;
    @Nullable
    private HomeSearch activeSearch = null;

    @Nullable
    private TextView tvConnectstatus= null;

    @Nullable
    private ImageView ivConnnectstatus =null;

    private void requestPermission() {

        Log.i(TAG,"requestPermission");
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG,"checkSelfPermission");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.i(TAG,"shouldShowRequestPermissionRationale");
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            } else {
                Log.i(TAG,"requestPermissions");
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                         Toast.makeText(this, R.string.request_qupermission_fail_info, Toast.LENGTH_SHORT).show();

                } else {

                }

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
        EventBus.getDefault().register(MainActivityHolder.this);
        AppWork.getInstance().init();
        AppWork.getInstance().start();

        tvConnectstatus =this.findViewById(R.id.home_connect_status);
        ivConnnectstatus = this.findViewById(R.id.iv_connect_status);
        final LinearLayout chatsDrawerItem = this.findViewById(R.id.home_drawer_chats);
        final LinearLayout profileDrawerItem = this.findViewById(R.id.home_drawer_profile);

        chatsDrawerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeTab = changeTab(activeTab, chatsDrawerItem, "Chats", "", new FragmentFactory() {
                    @NonNull
                    @Override
                    public Fragment get() {
                        return new MainFragment();
                    }
                });
            }
        });

        profileDrawerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeTab = changeTab(activeTab, profileDrawerItem, "Profile", "Activity", new FragmentFactory() {
                    @NonNull
                    @Override
                    public Fragment get() {
                        return new ProfileFragment();
                    }
                });
            }
        });

        chatsDrawerItem.callOnClick();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void connectionEventBus(ConnectionStatusEvent event){
        switch (event.connectionStatus){
            case TOX_CONNECTION_TCP: {
                tvConnectstatus.setText(R.string.drawer_connection_connected_tcp);
                ivConnnectstatus.setColorFilter(Color.GREEN);
                Toast.makeText(this,R.string.drawer_connection_connected_tcp,Toast.LENGTH_SHORT);
            }
                break;
            case TOX_CONNECTION_UDP: {
                tvConnectstatus.setText(R.string.drawer_connection_connected_udp);
                ivConnnectstatus.setColorFilter(Color.GREEN);
                Toast.makeText(this,R.string.drawer_connection_connected_udp,Toast.LENGTH_SHORT);
            }
                break;
            case TOX_CONNECTION_NONE: {
                tvConnectstatus.setText(R.string.drawer_connection_connecting);
                ivConnnectstatus.setColorFilter(Color.GRAY);
                Toast.makeText(this,R.string.drawer_connection_connecting,Toast.LENGTH_SHORT);
            }
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void friendRequestEvent(FriendRequestEvent event){
        Log.i("Request friend",event.message);
//        AppWork.getInstance().getTox().addFriendNorequest(event.publickey);
//        AppWork.getInstance().getTox().updateSaveData();
    }


    @Override
    protected void onDestroy() {
        if (activeSearch != null) {
            getWindowManager().removeView(activeSearch);
            activeSearch = null;
        }

        if (activeContacts != null) {
            getWindowManager().removeView(activeContacts);
            activeContacts = null;
        }
        EventBus.getDefault().unregister(MainActivityHolder.this);
        super.onDestroy();
    }

    @NonNull
    private <T extends Fragment> LinearLayout changeTab(
            @Nullable LinearLayout oldTab,
            @NonNull LinearLayout newTab,
            String tag,
            String stackName,
            @NonNull FragmentFactory<T> newFragment
    ) {
        if (newTab != oldTab) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_frame, newFragment.get(), tag)
                    .addToBackStack(stackName)
                    .commit();
        }

        if (oldTab != null) {
            oldTab.setBackgroundResource(R.drawable.background_ripple);
        }

        newTab.setBackgroundResource(R.color.drawerBackgroundSelected);
        DrawerLayout homeLayout = this.findViewById(R.id.home_layout);
        homeLayout.closeDrawers();

        return newTab;
    }

    public void setAddContactPopup(SlideInContactsLayout contact) {
        activeContacts = contact;
    }

    public void setSearch(HomeSearch homeSearch) {
        activeSearch = homeSearch;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("Profile") != null) {
            getSupportFragmentManager().popBackStack("Activity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        if (activeContacts != null) {
            activeContacts.finish(new Runnable() {
                @Override
                public void run() {
                    getWindowManager().removeView(activeContacts);
                    activeContacts = null;
                }
            });
        }

        if (activeSearch != null) {
            activeSearch.finish(new Runnable() {
                @Override
                public void run() {
                    getWindowManager().removeView(activeSearch);
                    activeSearch = null;
                }
            });
        }
    }

    private interface FragmentFactory<T extends Fragment> {
        @NonNull
        T get();
    }
}
