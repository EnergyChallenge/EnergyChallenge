package de.unikiel.klik.energychallenge.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import de.unikiel.klik.energychallenge.R;

public class CompleteActivityDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.sure_to_complete);
        builder.setPositiveButton(R.string.complete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //TODO do action -> delegate to Adapter

                Log.v("Dialog", "Ja called");
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog

                //TODO do action -> delegate to Adapter or maybe do nothing

                Log.v("Dialog", "Nein called");
            }
        });

        return builder.create();
    }

}
