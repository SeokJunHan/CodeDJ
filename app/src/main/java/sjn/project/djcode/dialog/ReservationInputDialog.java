package sjn.project.djcode.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sjn.project.djcode.LoadedData;
import sjn.project.djcode.R;
import sjn.project.djcode.value_objects.Reservation;

public class ReservationInputDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final int RESULT_OK = 5252;

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_reservation_input);

        int idx = getIntent().getIntExtra("idx", -1);
        if (idx == -1) {
            Toast.makeText(getApplicationContext(), "오류가 발생했습니다.", Toast.LENGTH_LONG).show();
        }

        Button button = findViewById(R.id.reservation_input_btn);
        button.setOnClickListener( listener -> {
            EditText et1 = findViewById(R.id.reservation_input_id);
            EditText et2 = findViewById(R.id.reservation_input_tel_no);

            String id = et1.getText().toString();
            String tel_no = et2.getText().toString();

            if(id.trim().equals("")) {
                Toast.makeText(getApplicationContext(), "아이디를 입력해 주세요.", Toast.LENGTH_LONG).show();
                return;
            }
            else if (tel_no.trim().equals("")) {
                Toast.makeText(getApplicationContext(), "전화번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                return;
            }

            else {
                Reservation reservation = new Reservation(id, tel_no);
                Intent intent = new Intent();
                intent.putExtra("idx", idx);
                intent.putExtra("reservation", reservation);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
