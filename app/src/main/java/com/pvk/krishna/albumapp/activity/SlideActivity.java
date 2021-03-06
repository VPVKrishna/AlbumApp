package com.pvk.krishna.albumapp.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.fragment.CategoryFragment;
import com.pvk.krishna.albumapp.fragment.EditProfileFragment;
import com.pvk.krishna.albumapp.fragment.HomeFragment;


public class SlideActivity extends Activity {

    private SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.slide_options);

        replaceFragment(new HomeFragment(), false);
    }

    public void openSlidingDrawer(){
        if(!menu.isMenuShowing()){
            menu.showMenu();
        }
    }

    public void editProfile(View view){
        replaceFragment(new EditProfileFragment(), true);
        menu.showContent(true);
        Toast.makeText(getApplicationContext(), "EditProfile", Toast.LENGTH_SHORT).show();
    }

    public void createProject(View view){
        replaceFragment(new CategoryFragment(), true);
        menu.showContent(true);
        Toast.makeText(getApplicationContext(), "CreateProject", Toast.LENGTH_SHORT).show();
    }

    public void myOrders(View view){
        Toast.makeText(getApplicationContext(), "MyOrders", Toast.LENGTH_SHORT).show();
    }

    public void settings(View view){
        Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
    }

    public void help(View view){
        Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();
    }

    public void logout(View view){
        Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void replaceFragment(Fragment fragment, boolean isWantToBackStack) {

        FragmentManager fm = getFragmentManager();
        String simpleName = fragment.getClass().getSimpleName();
        Fragment frag = fm.findFragmentByTag(simpleName);

        if (frag != null) {
            fm.popBackStack(simpleName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment, simpleName);
        if (isWantToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        super.onDestroy();
    }
}
