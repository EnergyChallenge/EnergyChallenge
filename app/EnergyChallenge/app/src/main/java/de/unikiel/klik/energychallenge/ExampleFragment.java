package de.unikiel.klik.energychallenge;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ExampleFragment extends Fragment {

    public ExampleFragment() {
    }

    /* Returns an new instance of this fragment */
    public static ExampleFragment newInstance() {
        return new ExampleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Swap in the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_example, container, false);

        // Setting Listeners
        Button button = (Button) view.findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressed();
            }
        });

        return view;
    }

    public void buttonPressed() {

        Context context = getActivity().getApplicationContext();
        String inputText = ((EditText) getView().findViewById(R.id.edit_message)).getText().toString();
        Toast.makeText(context, inputText, Toast.LENGTH_SHORT).show();

    }

}