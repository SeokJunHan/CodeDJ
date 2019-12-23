package sjn.project.djcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import sjn.project.djcode.value_objects.Theme;

public class ThemeAdapter extends ArrayAdapter<HashMap<Integer, Theme>> {
    private List<Theme> items;
    Context context;

    public ThemeAdapter(Context context, int resource, List<Theme> objects){
        super(context, resource);
        items = objects;
        this.context = context;
    }

    public int getCount() { return items.size(); }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.theme_listview, null, true);
        ImageView imageView = convertView.findViewById(R.id.theme_img);
        TextView name = convertView.findViewById(R.id.theme_name);
        TextView difficult = convertView.findViewById(R.id.difficult);
        TextView genre = convertView.findViewById(R.id.category);

        Glide.with(context)
                .load(items.get(position).getImg())
                .into(imageView);
        name.setText(items.get(position).getName());
        difficult.setText(String.valueOf(items.get(position).getDifficulty()));
        genre.setText(items.get(position).getGenre());
        return convertView;
    }
}
