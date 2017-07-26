package org.unhack.chemistryeasy.ui.adaptors;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.unhack.chemistryeasy.ui.fragments.PeriodicTableFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by unhack on 6/23/16.
 */
public class MixedPagerAdapter extends FragmentStatePagerAdapter {
    private final List<PeriodicTableFragment> mFragments = new ArrayList<PeriodicTableFragment>();

    public MixedPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public void addFragment(PeriodicTableFragment fragment) {
        fragment.setId(this.mFragments.size());
        this.mFragments.add(fragment);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return this.mFragments.get(position);
    }

    @Override
    public CharSequence  getPageTitle(int id){
        return String.valueOf(this.mFragments.get(id).getTabId());
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void NavigateToTab(int tabId){
        Bundle data = new Bundle();
        Message msg = new Message();
        //data.putInt(MainActivity.TABNUMBER, tabId);
        msg.setData(data);
        //MainActivity.mSwipeHandler.sendMessage(msg);
    }

    public void CoolNavigateToTab(int tabId, String tabIdKey, Handler handlerToNotify, boolean clearFragments){
        if (clearFragments){
            this.clearFragments();
        }
        this.notifyDataSetChanged();
        Bundle data = new Bundle();
        Message msg = new Message();
        data.putInt(tabIdKey,tabId);
        msg.setData(data);
        handlerToNotify.sendMessage(msg);
    }

    public void clearFragments(){
        PeriodicTableFragment buf = mFragments.get(0);
        this.mFragments.clear();
        this.addFragment(buf);
        this.notifyDataSetChanged();
    }

}
