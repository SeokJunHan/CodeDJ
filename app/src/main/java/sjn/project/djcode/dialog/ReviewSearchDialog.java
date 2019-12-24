package sjn.project.djcode.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import sjn.project.djcode.BusProvider;
import sjn.project.djcode.R;
import sjn.project.djcode.SendDataEvent;

public class ReviewSearchDialog extends Activity {

    private EditText review_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_review_search);

        review_search = findViewById(R.id.review_search);

        Button button = findViewById(R.id.review_search_btn);
        button.setOnClickListener( listener -> {
            String keyword = review_search.getText().toString();
            BusProvider.getInstance().post(new SendDataEvent(keyword));
            finish();
        });
    }
}
