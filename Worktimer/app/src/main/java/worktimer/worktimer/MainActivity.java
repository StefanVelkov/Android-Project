package worktimer.worktimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment chooseTeamFragment = MainFragment.instance();
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_layout, chooseTeamFragment).commit();
    }
}
