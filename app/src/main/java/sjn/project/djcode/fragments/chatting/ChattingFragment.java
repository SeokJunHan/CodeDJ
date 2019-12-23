package sjn.project.djcode.fragments.chatting;

import android.R.layout;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.net.URISyntaxException;

import sjn.project.djcode.LoadedData;
import sjn.project.djcode.R;
import sjn.project.djcode.dialog.ChatInputIdDialog;
import sjn.project.djcode.value_objects.ChatData;

import static sjn.project.djcode.R.id.chat_message;
import static sjn.project.djcode.R.layout.fragment_chatting;

public class ChattingFragment extends Fragment {

    private Context mContext;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private String CHAT_NAME;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(fragment_chatting, container, false);
        listView = root.findViewById(chat_message);
        mContext = root.getContext();

        EditText editText = (EditText) root.findViewById(R.id.chat_input);
        Button button = (Button) root.findViewById(R.id.button);
        try {
            Intent intent = Intent.getIntentOld("chatId");
            CHAT_NAME = intent.getStringExtra("chatId");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        button.setOnClickListener((view) -> {
            ChatData chatData = new ChatData(CHAT_NAME, editText.getText().toString());  // 유저 이름과 메세지로 chatData 만들기
            databaseReference.child("message").push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
            editText.setText("");
        });
        if(LoadedData.ChatID == null) {
            Intent intent = new Intent(getContext(), ChatInputIdDialog.class);
            startActivity(intent);
        }
        else{

        }

        return root;
    }
    private void addMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        ChatData chatData = dataSnapshot.getValue(ChatData.class);
        adapter.add(chatData.getUserName() + " : " + chatData.getMessage());
    }

    private void removeMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        ChatData chatData = dataSnapshot.getValue(ChatData.class);
        adapter.remove(chatData.getUserName() + " : " + chatData.getMessage());
    }

    private void openChat(String chatName) {
        // 리스트 어댑터 생성 및 세팅
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.fragment_chatting, R.id.chat_message);
        listView.setAdapter(adapter);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        databaseReference.child("chat").child(chatName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addMessage(dataSnapshot, adapter);
                Log.e("LOG", "s:"+s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                removeMessage(dataSnapshot, adapter);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}