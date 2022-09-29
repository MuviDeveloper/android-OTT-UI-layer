package player.activity;

import static com.home.vod.preferences.LanguagePreference.CANCEL_BUTTON;
import static com.home.vod.preferences.LanguagePreference.DEAFULT_CANCEL_BUTTON;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_RESUME;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_RESUME_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.RESUME;
import static com.home.vod.preferences.LanguagePreference.RESUME_MESSAGE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;

import player.utils.SensorOrientationChangeNotifier;


public class ResumePopupActivity extends Activity implements SensorOrientationChangeNotifier.Listener {

    LanguagePreference languagePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_playing);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainlayout);

        Intent playerIntent = new Intent();
        playerIntent.putExtra("yes", "1002");
        setResult(RESULT_OK, playerIntent);
        finish();

        LinearLayout popupLayout = (LinearLayout) findViewById(R.id.popupLayout);
        Button yesButton = (Button) findViewById(R.id.resume);
        Button cancelButton = (Button) findViewById(R.id.cancel);
        TextView resumeTitleTextView = (TextView) findViewById(R.id.resumeTitleTextView);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        resumeTitleTextView.setText(languagePreference.getTextofLanguage(RESUME_MESSAGE,DEFAULT_RESUME_MESSAGE));
        yesButton.setText(languagePreference.getTextofLanguage(RESUME,DEFAULT_RESUME));
        cancelButton.setText(languagePreference.getTextofLanguage(CANCEL_BUTTON,DEAFULT_CANCEL_BUTTON));

        Animation topTobottom = AnimationUtils.loadAnimation(this, R.anim.top_bottom);
        popupLayout.startAnimation(topTobottom);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerIntent = new Intent();
                playerIntent.putExtra("yes", "1002");
                setResult(RESULT_OK, playerIntent);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerIntent = new Intent();
                playerIntent.putExtra("yes", "1003");
                setResult(RESULT_OK, playerIntent);
                finish();
            }
        });
        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               /* Intent playerIntent = new Intent();
                playerIntent.putExtra("yes", "1003");
                setResult(RESULT_OK, playerIntent);
                finish();*/
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorOrientationChangeNotifier.getInstance(ResumePopupActivity.this).addListener(this);
    }

    @Override
    public void onOrientationChange(int orientation) {}
}
