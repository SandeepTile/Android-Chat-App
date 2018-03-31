package com.example.sandy.androidchatapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionPagerAdapter extends FragmentPagerAdapter{

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                RequestFragment requestFragment=new RequestFragment();
                return requestFragment;

            case 1:
                ChatsFragment chatsFragment=new ChatsFragment();
                return chatsFragment;


            case 2:
                FriendsFragment friendsFragment=new FriendsFragment();
                return friendsFragment;

                default:
                    return null;

        }


    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){     // For Naming to the Tabs
        switch (position){

            case 0:
                return "REQUESTS";

            case 1:
                return "CHATS";

            case 2:
                return "FRIENDS";

                default:
                    return null;
        }
    }
}


