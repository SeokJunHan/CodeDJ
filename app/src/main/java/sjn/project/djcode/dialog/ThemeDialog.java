package sjn.project.djcode.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import sjn.project.djcode.R;
import sjn.project.djcode.value_objects.Theme;

public class ThemeDialog extends Activity {

    TextView title, content;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_theme);

        title = findViewById(R.id.theme_title);
        content = findViewById(R.id.theme_content);
        imageView = findViewById(R.id.theme_img);

        Theme theme = (Theme) getIntent().getSerializableExtra("theme");
        title.setText(theme.getName());
        content.setText(theme.getDesc());
        Glide.with(getApplicationContext())
                .load(theme.getImg())
                .into(imageView);

        Button button = findViewById(R.id.theme_button);
        button.setOnClickListener( listener -> {
            Intent intent = new Intent(getApplicationContext(), ReservationDialog.class);
            intent.putExtra("theme", theme);
            startActivity(intent);
        });
    }
}
