package sjn.project.djcode.fragments.chatting;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import sjn.project.djcode.ChatAdapter;
import sjn.project.djcode.LoadedData;
import sjn.project.djcode.R;
import sjn.project.djcode.value_objects.ChatData;
import sjn.project.djcode.value_objects.Review;

public class ChattingFragment extends Fragment {

    Context mContext;
    FirebaseDatabase database;
    DatabaseReference reference;

    List<ChatData> list;
    ListView listView;
    Button button;
    EditText input;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chatting, container, false);
        mContext = root.getContext();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Chatting");
        list = new ArrayList<>();

        listView = root.findViewById(R.id.chat_message);
        input = root.findViewById(R.id.chat_input);
        button = root.findViewById(R.id.chat_button);

        if(LoadedData.ChatID == null) {
            Random random = new Random();
            String ran_str = "";
            for (int i = 0; i < 5; i++) {
                if (random.nextBoolean()) {
                    ran_str += (char) ((random.nextInt(26)) + 97);
                } else {
                    ran_str += (random.nextInt(10));
                }
            }
            LoadedData.ChatID = "익명" + ran_str;
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatData chatData = new ChatData(LoadedData.ChatID, input.getText().toString());  // 유저 이름과 메세지로 chatData 만들기
                reference.push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
                input.setText("");
            }
        });

        list.clear();
        openChat(LoadedData.ChatID);
        return root;
    }

    private void openChat(String chatName) {

        ChatAdapter adapter = new ChatAdapter(mContext, 0, list);
        listView.setAdapter(adapter);
        reference.push().setValue(new ChatData("", LoadedData.ChatID + " 님이 입장했습니다.", true));

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    ChatData data = dataSnapshot.getValue(ChatData.class);
                    adapter.add(data.getMessage(), data.getUserName());
                    listView.setSelection(adapter.getCount() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}