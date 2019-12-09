package sjn.project.djcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class ThemeAdapter extends ArrayAdapter<HashMap<Integer, Themes>> {
    private HashMap<Integer, Themes> items;
    Context context;

    public ThemeAdapter(Context context, int resource, HashMap<Integer, Themes> objects){
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
        TextView category = convertView.findViewById(R.id.category);
        TextView person = convertView.findViewById(R.id.person);

        imageView.setImageResource(items.get(position).getThemeImg());
        name.setText(items.get(position).getThemeName());
        difficult.setText(items.get(position).getDifficult());
        category.setText(items.get(position).getCategory());
        person.setText(items.get(position).getPerson());
        return convertView;
    }
}
