package de.unikiel.klik.energychallenge.fragments;

import de.unikiel.klik.energychallenge.tasks.GetRankingTask;
import de.unikiel.klik.energychallenge.tasks.GetTeamRankingTask;
import de.unikiel.klik.energychallenge.tasks.GetUserRankingTask;

public class UserRankingFragment extends RankingFragment {

    // Returns an new instance of this fragment
    public static UserRankingFragment newInstance() {
        return new UserRankingFragment();
    }

    @Override
    protected String getProfileType() {
        return "user";
    }

    @Override
    protected Class<? extends GetRankingTask> getGetRankingTaskClass() {
        return GetUserRankingTask.class;
    }
}
