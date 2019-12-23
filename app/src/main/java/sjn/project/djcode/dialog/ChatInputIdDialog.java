package sjn.project.djcode.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import sjn.project.djcode.LoadedData;
import sjn.project.djcode.R;
import sjn.project.djcode.value_objects.Theme;

public class ChatInputIdDialog extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_chat_input_id);

        Button button = findViewById(R.id.chat_input_btn);
        button.setOnClickListener( listener -> {
            EditText editText = findViewById(R.id.chat_input_id);
            String id = editText.getText().toString();
            LoadedData.ChatID = id;
            finish();
            System.out.println("ID : " + LoadedData.ChatID);
        });
    }
}
