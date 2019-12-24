package sjn.project.djcode;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import sjn.project.djcode.value_objects.ChatData;

public class ChatAdapter extends ArrayAdapter<List<ChatData>> {

    private List<ChatData> list;
    Context context;

    public ChatAdapter(@NonNull Context context, int resource, @NonNull List<ChatData> objects) {
        super(context, resource);
        list = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_chat, null, true);
        TextView username = convertView.findViewById(R.id.item_username);
        TextView item_message = convertView.findViewById(R.id.item_message);
        username.setText(list.get(position).getUserName());
        item_message.setText(list.get(position).getMessage());
        if(list.get(position).getSystemMsg() == true) {
            username.setGravity(Gravity.CENTER_HORIZONTAL);
            username.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        else if(username.getText().toString().equals(LoadedData.ChatID)) {
            username.setGravity(Gravity.RIGHT);
            username.setGravity(Gravity.RIGHT);
        }

        return convertView;
    }

    public void add(String message, String username) {
        list.add(new ChatData(message, username));
        notifyDataSetChanged();
    }

    public void claer() {
        list.clear();
    }
}
