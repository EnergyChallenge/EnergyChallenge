package de.unikiel.klik.energychallenge.fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.models.Team;
import de.unikiel.klik.energychallenge.models.User;
import de.unikiel.klik.energychallenge.tasks.GetProfileTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;


/*
    TODO

    Rename to ProfileFragment

 */

/* Fragment for the users own profile page */
public class ProfileFragment extends Fragment {

    private String type; // Must be 'user' or 'team'

    private int profileId;

    private GridLayout profileView;

    private LinearLayout progressIndicator;

    private TextView emptyProfileText;

    public ProfileFragment() {
    }

    /* Returns an new instance of this fragment */
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Swap in the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        if (getArguments() == null) {
            type = "user";
            profileId = 1; //TODO Set own user id
        } else {
            type = getArguments().getString("type");
            profileId = getArguments().getInt("id");
        }

        profileView = (GridLayout) view.findViewById(R.id.profile_container);
        progressIndicator = (LinearLayout) view.findViewById(R.id.progress_container);
        emptyProfileText = (TextView) view.findViewById(R.id.empty);


        loadProfileData();

        //TODO Load Image - in own Task!

        return view;
    }

    public void buildProfile(User user) {

        TextView nameView = (TextView) getView().findViewById(R.id.profil_name);
        TextView teamNameView = (TextView) getView().findViewById(R.id.profil_team_name);
        TextView instituteView = (TextView) getView().findViewById(R.id.profil_institute);
        TextView totalPointsView = (TextView) getView().findViewById(R.id.profil_total_points);
        TextView totalPointsDescriptionView = (TextView) getView().findViewById(R.id.profil_total_points_description);
        TextView rankingPositionView = (TextView) getView().findViewById(R.id.profil_ranking_position);
        ListView lastActivitiesView = (ListView) getView().findViewById(R.id.profile_last_activities);

        nameView.setText(user.getName());
        teamNameView.setText(user.getTeamName());
        instituteView.setText(user.getInstitute());
        totalPointsView.setText(Integer.toString(user.getPoints()));
        totalPointsDescriptionView.setText(user.getPoints() == 1 ? R.string.point : R.string.points);
        rankingPositionView.setText(Integer.toString(user.getPosition()) + ".");

        lastActivitiesView.setAdapter(new ArrayAdapter<String>(getActivity(),
                                            android.R.layout.simple_list_item_1,
                                            user.getLastActivities()));
        //TODO Change to own Adapter later



    }

    public void buildProfile(Team team) {

        TextView nameView = (TextView) getView().findViewById(R.id.profil_name);
        TextView totalPointsView = (TextView) getView().findViewById(R.id.profil_total_points);
        TextView totalPointsDescriptionView = (TextView) getView().findViewById(R.id.profil_total_points_description);
        TextView rankingPositionView = (TextView) getView().findViewById(R.id.profil_ranking_position);
        ListView teamMembersView = (ListView) getView().findViewById(R.id.profil_team_members);
        ListView lastActivitiesView = (ListView) getView().findViewById(R.id.profile_last_activities);

        nameView.setText(team.getName());
        totalPointsView.setText(Integer.toString(team.getPoints()));
        totalPointsDescriptionView.setText(team.getPoints() == 1 ? R.string.point : R.string.points);
        rankingPositionView.setText(Integer.toString(team.getPosition()) + ".");

        teamMembersView.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                team.getMembers()));

        lastActivitiesView.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                team.getLastActivities()));
        //TODO Change to own Adapter later

    }

    private void loadProfileData() {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            new GetProfileTask(this, profileView, progressIndicator, emptyProfileText).execute();
        } else {
            emptyProfileText.setText(R.string.no_network_connection);
            Toast.makeText(context, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }
}