package im.tox.toktok.app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.nio.ByteBuffer;

import im.tox.jtoxcore.JTox;
import im.tox.jtoxcore.callbacks.CallbackHandler;
import im.tox.jtoxcore.callbacks.OnSelfConnectionStatusCallback;
import im.tox.toktok.R;
import im.tox.toktok.app.main.HomeSearch;
import im.tox.toktok.app.main.MainFragment;
import im.tox.toktok.app.main.friends.SlideInContactsLayout;
import im.tox.toktok.app.profile.ProfileFragment;

public final class MainActivityHolder extends AppCompatActivity {

    @Nullable
    private LinearLayout activeTab = null;
    @Nullable
    private SlideInContactsLayout activeContacts = null;
    @Nullable
    private HomeSearch activeSearch = null;
    private CallbackHandler callbackHandler = new CallbackHandler();

    public static byte[] hexStr2Byte(String hex) {
        ByteBuffer bf = ByteBuffer.allocate(hex.length() / 2);
        for (int i = 0; i < hex.length(); i++) {
            String hexStr = hex.charAt(i) + "";
            i++;
            hexStr += hex.charAt(i);
            byte b = (byte) Integer.parseInt(hexStr, 16);
            bf.put(b);
        }
        return bf.array();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread() {
            @Override
            public void run() {
                JTox tox= new JTox(callbackHandler);
                tox.invokeSelfConnectionStatus(true);
                tox.setName("hddtest");
                tox.setStatusMessage("hello");
                byte[] data= hexStr2Byte("DBC1A03F09ACE506B75579A2A70800212929BBB60462236FDE2141CAAC8D2672");
                tox.bootstrap("114.215.18.146",8057,data);
                while (true) {
                    tox.toxIterate();
                    try {
                        int t = tox.toxIterationInterval();
                        Thread.sleep(t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        callbackHandler.registerOnConnectionStatusCallback(new OnSelfConnectionStatusCallback() {
            @Override
            public void execute(int connectionstatus) {
                Log.d("callbackHandler", "execute: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast=Toast.makeText(MainActivityHolder.this,"Toast提示消息",Toast.LENGTH_SHORT    );
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });


            }
        });
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
