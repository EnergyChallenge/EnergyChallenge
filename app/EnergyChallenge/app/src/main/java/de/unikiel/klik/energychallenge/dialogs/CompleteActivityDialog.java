package de.unikiel.klik.energychallenge.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import de.unikiel.klik.energychallenge.R;

public class CompleteActivityDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final int activityPosition = getArguments().getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.sure_to_complete);
        builder.setPositiveButton(R.string.complete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                // Delegate the onClick event to the listfragment
                ((DialogListener) getTargetFragment()).onDialogPositiveClick(activityPosition);

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the action -> Do nothing
            }
        });

        return builder.create();
    }

    public interface DialogListener {
        public void onDialogPositiveClick(int activityPosition);
    }

}
