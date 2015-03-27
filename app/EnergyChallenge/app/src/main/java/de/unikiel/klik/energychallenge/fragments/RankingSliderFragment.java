package de.unikiel.klik.energychallenge.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.unikiel.klik.energychallenge.R;

/* TODO
 * Refactor names
 */

/* Fragment for viewing team and user rankings */
public class RankingSliderFragment extends Fragment {

    private FragmentPagerAdapter adapterViewPager;

    float actionBarElevation;

    public RankingSliderFragment() {
    }

    /* Returns an new instance of this fragment */
    public static RankingSliderFragment newInstance() {
        return new RankingSliderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_ranking_slider, container, false);

            ViewPager vpPager = (ViewPager) view.findViewById(R.id.vpPager);
            adapterViewPager = new MyPagerAdapter(getFragmentManager());
            vpPager.setAdapter(adapterViewPager);

            actionBarElevation = getActivity().getActionBar().getElevation();
            getActivity().getActionBar().setElevation(0);
            PagerTabStrip vpPagerHeader = (PagerTabStrip) view.findViewById(R.id.pager_header);
            vpPagerHeader.setElevation(actionBarElevation);

            return view;
    }

    @Override
    public void onDestroyView() {
        getActivity().getActionBar().setElevation(actionBarElevation);
        super.onDestroyView();
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private int numOfItems = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return numOfItems;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // User ranking fragment
                    return UserRankingFragment.newInstance();
                case 1: // Team ranking fragment
                    return TeamRankingFragment.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: // Title for user ranking
                    return getString(R.string.rankings_userTab);
                case 1: // Title for team ranking
                    return getString(R.string.rankings_teamsTab);
                default:
                    return null;
            }
        }

    }

}
