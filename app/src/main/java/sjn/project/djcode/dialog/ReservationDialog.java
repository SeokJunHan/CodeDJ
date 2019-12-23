package sjn.project.djcode.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

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

    TextView title, genre, difficulty;
    Button btn_cancel;

    String[] times = { "10:20", "11:30", "12:40", "13:50", "15:00", "16:10",
            "17:20", "18:30", "19:40", "20:50", "22:00", "23:10" };
    Reservation[] reservations = new Reservation[times.length];
    int[] btn_id = {R.id.reservation_button1, R.id.reservation_button2, R.id.reservation_button3,
            R.id.reservation_button4, R.id.reservation_button5, R.id.reservation_button6,
            R.id.reservation_button7, R.id.reservation_button8, R.id.reservation_button9,
            R.id.reservation_button10, R.id.reservation_button11, R.id.reservation_button12,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_reservation);

        Theme theme = (Theme) getIntent().getSerializableExtra("theme");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(calendar.getTime());
        System.out.println(date);

        title = findViewById(R.id.reservation_title);
        genre = findViewById(R.id.reservation_genre);
        difficulty = findViewById(R.id.reservation_difficulty);
        btn_cancel = findViewById(R.id.reservation_cancel);

        title.setText(theme.getName());
        genre.setText(theme.getGenre());
        difficulty.setText(String.valueOf(theme.getDifficulty()));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reservation = database.getReference("reservation").child(theme.getName());
        reservation.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(date)) {
                    for(int i=0; i<times.length; i++) {
                        Reservation item = dataSnapshot.child(date).child(String.valueOf(i)).getValue(Reservation.class);
                        reservations[i] = item;
                        Button btn = findViewById(btn_id[i]);
                        // 예약이 없는 경우
                        if(item.getName().equals("")) {
                            btn.setText(times[i]+"\n예약 가능");
                        }
                        // 예약이 있는 경우
                        else {
                            btn.setBackgroundColor(getResources().getColor(R.color.content));
                            btn.setText(times[i]+"\n예약 완료");
                        }
                    }
                }
                else {
                    Reservation emptyResrvation = new Reservation("", "");
                    for(int i=0; i<times.length; i++)
                        reservation.child(date).child(String.valueOf(i)).setValue(emptyResrvation);
                    //set empty button
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn_cancel.setOnClickListener(e -> {
            finish();
        });
    }
}
