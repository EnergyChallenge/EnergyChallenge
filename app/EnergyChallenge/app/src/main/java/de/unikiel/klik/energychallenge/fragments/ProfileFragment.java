package de.unikiel.klik.energychallenge.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.activities.ProfileActivity;
import de.unikiel.klik.energychallenge.adapters.PassiveArrayAdapter;
import de.unikiel.klik.energychallenge.models.Team;
import de.unikiel.klik.energychallenge.models.User;
import de.unikiel.klik.energychallenge.tasks.DownloadAvatarTask;
import de.unikiel.klik.energychallenge.tasks.GetTeamProfileTask;
import de.unikiel.klik.energychallenge.tasks.GetUserProfileTask;
import de.unikiel.klik.energychallenge.utils.CurrentUser;
import de.unikiel.klik.energychallenge.utils.NetworkX;

/* Fragment for the users own profile page */
public class ProfileFragment extends Fragment {

    private String type; // Must be 'user' or 'team'

    private int profileId;

    private boolean isCurrentUser = false;

    private LinearLayout profileView;

    private ImageView avatarView;

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
            profileId = new CurrentUser(getActivity().getApplicationContext()).getId();
            isCurrentUser = true;
        } else {
            type = getArguments().getString("type");
            profileId = getArguments().getInt("id");
        }

        profileView = (LinearLayout) view.findViewById(R.id.profile_container);
        progressIndicator = (LinearLayout) view.findViewById(R.id.progress_container);
        emptyProfileText = (TextView) view.findViewById(R.id.empty);
        avatarView = (ImageView) view.findViewById(R.id.profile_image);


        loadProfileData();

        return view;
    }

    public void buildProfile(User user) {

        TextView nameView = (TextView) getView().findViewById(R.id.profil_name);
        TextView teamNameView = (TextView) getView().findViewById(R.id.profil_team_name);
        TextView instituteView = (TextView) getView().findViewById(R.id.profil_institute);
        TextView totalPointsView = (TextView) getView().findViewById(R.id.profil_total_points);
        TextView totalPointsDescriptionView = (TextView) getView().findViewById(R.id.profil_total_points_description);
        TextView rankingPositionView = (TextView) getView().findViewById(R.id.profil_ranking_position);
        TextView lastActivitiesHeadlineView = (TextView) getView().findViewById(R.id.profile_last_activities_headline);
        ListView lastActivitiesView = (ListView) getView().findViewById(R.id.profile_last_activities);

        lastActivitiesHeadlineView.setVisibility(View.VISIBLE);
        lastActivitiesView.setVisibility(View.VISIBLE);

        nameView.setText(user.getName());
        teamNameView.setText(user.getTeamName());
        instituteView.setText(user.getInstitute());
        totalPointsView.setText(Integer.toString(user.getPoints()));
        totalPointsDescriptionView.setText(user.getPoints() == 1 ? R.string.point : R.string.points);
        rankingPositionView.setText(Integer.toString(user.getPosition()) + ".");

        lastActivitiesView.setAdapter(new PassiveArrayAdapter<String>(getActivity(),
                                            android.R.layout.simple_list_item_1,
                                            user.getLastActivities()));

        setActionBarTitle(user.getName());
    }

    public void buildProfile(Team team) {

        TextView nameView = (TextView) getView().findViewById(R.id.profil_name);
        TextView totalPointsView = (TextView) getView().findViewById(R.id.profil_total_points);
        TextView instituteView = (TextView) getView().findViewById(R.id.profil_institute);
        TextView totalPointsDescriptionView = (TextView) getView().findViewById(R.id.profil_total_points_description);
        TextView rankingPositionView = (TextView) getView().findViewById(R.id.profil_ranking_position);
        TextView teamMembersHeadlineView = (TextView) getView().findViewById(R.id.profil_team_members_headline);
        ListView teamMembersView = (ListView) getView().findViewById(R.id.profil_team_members);

        instituteView.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 0));
        teamMembersHeadlineView.setVisibility(View.VISIBLE);
        teamMembersView.setVisibility(View.VISIBLE);

        nameView.setText(team.getName());
        totalPointsView.setText(Integer.toString(team.getPoints()));
        totalPointsDescriptionView.setText(team.getPoints() == 1 ? R.string.point : R.string.points);
        rankingPositionView.setText(Integer.toString(team.getPosition()) + ".");

        teamMembersView.setAdapter(new PassiveArrayAdapter<String>(getActivity(),
                                        android.R.layout.simple_list_item_1,
                                        team.getMembers()));

        setActionBarTitle(team.getName());
    }

    private void loadProfileData() {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            if (type.equals("user")) {
                new GetUserProfileTask(context, profileId, this, profileView, progressIndicator, emptyProfileText).execute();
            } else if (type.equals("team")) {
                new GetTeamProfileTask(context, profileId, this, profileView, progressIndicator, emptyProfileText).execute();
            }
            new DownloadAvatarTask(profileId, avatarView).execute();
        } else {
            emptyProfileText.setText(R.string.no_network_connection);
            Toast.makeText(context, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }

    private void setActionBarTitle(String title) {
        if (!isCurrentUser) {
            ((ProfileActivity) getActivity()).setActionBarTitle(title);
        }
    }
}