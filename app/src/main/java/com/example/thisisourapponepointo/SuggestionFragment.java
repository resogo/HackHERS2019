package com.example.thisisourapponepointo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SuggestionFragment extends android.app.Fragment {
    View rootView;
    String suggestion;
    EditText suggestionTxt = rootView.findViewById(R.id.editText_suggestion);

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_suggestions, container, false);
        super.onCreate(savedInstanceState);

        Button submit = rootView.findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(suggestionTxt)) {
                    Toast.makeText(getActivity(), "Please provide a suggestion before submitting", Toast.LENGTH_LONG).show();
                } else {
                    suggestion =  String.valueOf(suggestionTxt.getText());

                    //Get user email from field in email spot
                    String email = MyApplication.eboard.getEmail();

                    //Email subject
                    String subject = "Suggestion/Comment via App";

                    String message = "One of your users has anonymously submitted the following comment: " + suggestion;

                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , email);
                    i.putExtra(Intent.EXTRA_SUBJECT, subject);
                    i.putExtra(Intent.EXTRA_TEXT   , message);
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }
}
