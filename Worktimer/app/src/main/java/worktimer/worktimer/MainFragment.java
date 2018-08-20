package worktimer.worktimer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private FirebaseFirestore mDB = FirebaseFirestore.getInstance();
    private Button mButton;
    private TextView mTaskNameButton;
    private EditText mTaskName;
    private TextView mQuote;
    private List<String> quotes = new ArrayList<>();

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment instance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mDB.collection("quotes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                //Toast.makeText(getActivity(), document.getData().toString(), Toast.LENGTH_SHORT).show();
                                quotes.add(document.getData().toString());
                            }

                        }
                    }
                });
        mButton = view.findViewById(R.id.button);
        mButton.setOnClickListener(this);

        mTaskNameButton = view.findViewById(R.id.taskNameButton);

        mTaskName = view.findViewById(R.id.taskName);

        mQuote = view.findViewById(R.id.quote);

        return view;
    }

    @Override
    public void onClick(View view) {
        Random r = new Random();
        String chosenQuote = quotes.get(r.nextInt(quotes.size()));
        String[] chosenQuoteSplit = chosenQuote.split("=");
        mQuote.setText(chosenQuoteSplit[1].substring(0, chosenQuoteSplit[1].length() - 1));
        mTaskNameButton.setText(mTaskName.getText());
    }
}