package com.example.monitorbebe.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.monitorbebe.lists.acordou.AcordouAdapter;
import com.example.monitorbebe.lists.acordou.AcordouListFragment;
import com.example.monitorbebe.lists.dormiu.DormiuAdapter;
import com.example.monitorbebe.lists.dormiu.DormiuListFragment;
import com.example.monitorbebe.lists.hoje.HojeAdapter;
import com.example.monitorbebe.lists.hoje.HojeListFragment;
import com.example.monitorbebe.lists.mamou.MamouAdapter;
import com.example.monitorbebe.lists.mamou.MamouListFragment;
import com.example.monitorbebe.lists.resumo.ResumoAdapter;
import com.example.monitorbebe.lists.resumo.ResumoListFragment;
import com.example.monitorbebe.lists.trocou.TrocouAdapter;
import com.example.monitorbebe.lists.trocou.TrocouListFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private HojeAdapter hojeAdapter;
    private DormiuAdapter dormiuAdapter;
    private AcordouAdapter acordouAdapter;
    private MamouAdapter mamouAdapter;
    private ResumoAdapter resumoAdapter;
    private TrocouAdapter trocouAdapter;

    public SectionsPagerAdapter(@NonNull FragmentManager fm, HojeAdapter hojeAdapter, DormiuAdapter dormiuAdapter, AcordouAdapter acordouAdapter, MamouAdapter mamouAdapter, ResumoAdapter resumoAdapter, TrocouAdapter trocouAdapter) {
        super(fm);
        this.hojeAdapter = hojeAdapter;
        this.dormiuAdapter = dormiuAdapter;
        this.acordouAdapter = acordouAdapter;
        this.mamouAdapter = mamouAdapter;
        this.resumoAdapter = resumoAdapter;
        this.trocouAdapter = trocouAdapter;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HojeListFragment().newInstance(hojeAdapter);
            case 1:
                return new DormiuListFragment().newInstance(dormiuAdapter);
            case 2:
                return new AcordouListFragment().newInstance(acordouAdapter);
            case 3:
                return new MamouListFragment().newInstance(mamouAdapter);
            case 4:
                return new TrocouListFragment().newInstance(trocouAdapter);
            case 5:
                return new ResumoListFragment().newInstance(resumoAdapter);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "H";
            case 1:
                return "D";
            case 2:
                return "A";
            case 3:
                return "M";
            case 4:
                return "T";
            case 5:
                return "R";
        }
        return null;
    }
}