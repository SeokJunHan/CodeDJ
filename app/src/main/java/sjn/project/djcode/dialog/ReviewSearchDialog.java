package sjn.project.djcode.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import sjn.project.djcode.LoadedData;
import sjn.project.djcode.R;
import sjn.project.djcode.fragments.chatting.ChattingFragment;

public class ReviewSearchDialog extends Activity {

    private final int SEARCH_RESULT = 5555;
    private EditText review_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_review_search);

        review_search = findViewById(R.id.review_search);

        Button button = findViewById(R.id.review_search_btn);
        button.setOnClickListener( listener -> {
            Intent intent = new Intent();
            String keyword = review_search.getText().toString();
            intent.putExtra("search", keyword);
            setResult(SEARCH_RESULT, intent);
            finish();
        });
    }
}
