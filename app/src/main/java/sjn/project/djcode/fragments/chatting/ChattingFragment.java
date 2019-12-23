package sjn.project.djcode.fragments.chatting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import sjn.project.djcode.LoadedData;
import sjn.project.djcode.R;
import sjn.project.djcode.dialog.ChatInputIdDialog;

public class ChattingFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chatting, container, false);

        // LoadedData.ChatID 이거 사용

        if(LoadedData.ChatID == null) {
            Intent intent = new Intent(getContext(), ChatInputIdDialog.class);
            startActivity(intent);
        }

        return root;
    }
}