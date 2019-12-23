package sjn.project.djcode.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import sjn.project.djcode.R;
import sjn.project.djcode.value_objects.Reservation;
import sjn.project.djcode.value_objects.Theme;

public class ReservationDialog extends Activity {

    final int RESULT_OK = 5252; // 예약 다이얼로그로부터 응답을 받음.

    String date;
    TextView title, genre, difficulty;
    Button btn_cancel;

    String[] times = {"10:20", "11:30", "12:40", "13:50", "15:00", "16:10",
            "17:20", "18:30", "19:40", "20:50", "22:00", "23:10"};

    int[] btn_id = {R.id.reservation_button1, R.id.reservation_button2, R.id.reservation_button3,
            R.id.reservation_button4, R.id.reservation_button5, R.id.reservation_button6,
            R.id.reservation_button7, R.id.reservation_button8, R.id.reservation_button9,
            R.id.reservation_button10, R.id.reservation_button11, R.id.reservation_button12,};

    Reservation[] reservations = new Reservation[times.length];

    Theme theme;

    FirebaseDatabase database;
    DatabaseReference dataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_reservation);

        theme = (Theme) getIntent().getSerializableExtra("theme");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(calendar.getTime());

        title = findViewById(R.id.reservation_title);
        genre = findViewById(R.id.reservation_genre);
        difficulty = findViewById(R.id.reservation_difficulty);
        btn_cancel = findViewById(R.id.reservation_cancel);

        title.setText(theme.getName());
        genre.setText(theme.getGenre());
        difficulty.setText(String.valueOf(theme.getDifficulty()));

        database = FirebaseDatabase.getInstance();
        dataref = database.getReference("reservation").child(theme.getName());

        btn_cancel.setOnClickListener(e -> {
            finish();
        });
        LoadReservationData();
    }

    private void LoadReservationData() {

        dataref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(date)) {
                    for (int i = 0; i < times.length; i++) {
                        Reservation item = dataSnapshot.child(date).child(String.valueOf(i)).getValue(Reservation.class);
                        reservations[i] = item;
                        Button btn = findViewById(btn_id[i]);
                        // 예약이 없는 경우
                        if (item.getName().equals("")) {
                            btn.setText(times[i] + "\n예약 가능");

                            final int idx = i;
                            btn.setOnClickListener(e -> {
                                Intent intent = new Intent(getApplicationContext(), ReservationInputDialog.class);
                                intent.putExtra("idx", idx);
                                startActivityForResult(intent, RESULT_OK);
                            });
                        }
                        // 예약이 있는 경우
                        else {
                            btn.setBackgroundColor(getResources().getColor(R.color.content));
                            btn.setText(times[i] + "\n예약 완료");
                            btn.setOnClickListener(e -> {
                                Toast.makeText(getApplicationContext(), "이미 예약이 완료된 시간대 입니다.", Toast.LENGTH_LONG).show();
                            });
                        }
                    }
                } else {
                    Reservation emptyResrvation = new Reservation("", "");
                    for (int i = 0; i < times.length; i++) {
                        dataref.child(date).child(String.valueOf(i)).setValue(emptyResrvation);
                        LoadReservationData();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int idx = data.getIntExtra("idx", -1);
            if (idx == -1)
                Toast.makeText(getApplicationContext(), "에러가 발생했습니다.", Toast.LENGTH_LONG).show();
            Reservation reservation = (Reservation) data.getSerializableExtra("reservation");
            dataref.child(date).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Toast.makeText(getApplicationContext(), "예약이 완료되었습니다.", Toast.LENGTH_LONG).show();
                    dataref.child(date).child(String.valueOf(idx)).setValue(reservation);
                    LoadReservationData();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


}
