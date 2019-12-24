package sjn.project.djcode.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import sjn.project.djcode.BusProvider;
import sjn.project.djcode.R;
import sjn.project.djcode.SendDataEvent;

public class ChatInputIdDialog extends Activity {
    private EditText userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_chat_input_id);

        userid = (EditText) findViewById(R.id.chat_input_id);

        Button button = findViewById(R.id.chat_input_btn);
        button.setOnClickListener( listener -> {
            String id = userid.getText().toString();
            BusProvider.getInstance().post(new SendDataEvent(id));
            finish();
        });
    }
}
