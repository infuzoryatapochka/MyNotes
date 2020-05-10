package com.example.mynotes.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mynotes.fragments.AddFragment;
import com.example.mynotes.fragments.DeleteFragment;
import com.example.mynotes.fragments.ShowFragment;
import com.example.mynotes.fragments.UpdateFragment;

import java.util.ArrayList;
import java.util.List;

public class NotesPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 4;

    private List<Fragment> fragments = new ArrayList<>();
    private List<CharSequence> titles = new ArrayList<>();

    public NotesPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);

        fragments.add(new ShowFragment());
        fragments.add(new AddFragment());
        fragments.add(new DeleteFragment());
        fragments.add(new UpdateFragment());

        titles.add("Show");
        titles.add("Add");
        titles.add("Delete");
        titles.add("Update");
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return titles.get(i);
    }
}
