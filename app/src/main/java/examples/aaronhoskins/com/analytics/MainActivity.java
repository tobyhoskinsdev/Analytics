package examples.aaronhoskins.com.analytics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public static final String MIXPANEL_TOKEN = "06ee2b3fbd491c2a9be35cd883a34ec3";
    MixpanelAPI mixpanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Initialize the library with your
        // Mixpanel project token, MIXPANEL_TOKEN, and a reference
        // to your application context.
        mixpanel = MixpanelAPI.getInstance(this, MIXPANEL_TOKEN);
    }

    public void onClick(View view) {
        JSONObject props = new JSONObject();
        String value = "UNKNOWN";
        switch (view.getId()) {
            case R.id.btnClickEvent:
                value = "Action";
                break;
            case R.id.btnClickEventTwo:
                value = "Action 2";
                mixpanel.timeEvent("SleepTime");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mixpanel.track("SleepTime");

                break;
        }
        try {
            props.put("ButtonClicked", value);
            mixpanel.track("Plan Selected", props);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
