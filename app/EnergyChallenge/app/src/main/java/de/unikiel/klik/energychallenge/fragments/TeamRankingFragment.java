package de.unikiel.klik.energychallenge.fragments;

import de.unikiel.klik.energychallenge.tasks.GetRankingTask;
import de.unikiel.klik.energychallenge.tasks.GetTeamRankingTask;

public class TeamRankingFragment extends RankingFragment {

    // Returns an new instance of this fragment
    public static TeamRankingFragment newInstance() {
        return new TeamRankingFragment();
    }

    @Override
    protected String getProfileType() {
        return "team";
    }

    @Override
    protected Class<? extends GetRankingTask> getGetRankingTaskClass() {
        return GetTeamRankingTask.class;
    }
}
