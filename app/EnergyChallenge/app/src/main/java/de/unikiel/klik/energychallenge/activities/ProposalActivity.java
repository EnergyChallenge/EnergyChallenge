package de.unikiel.klik.energychallenge.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.models.Proposal;

public class ProposalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);


        Intent intent = getIntent();
        Proposal proposal = intent.getParcelableExtra("proposal");

        //TODO
        //Toast.makeText(this, "Author: " + proposal.getAuthor(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "1Komment: " + proposal.getComments().get(0).getText(), Toast.LENGTH_SHORT).show();

        //Prpo id = intent.getIntExtra("id", 0);



    }


}
