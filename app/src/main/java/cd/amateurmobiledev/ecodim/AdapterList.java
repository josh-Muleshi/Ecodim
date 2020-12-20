package cd.amateurmobiledev.ecodim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterList<L> extends BaseAdapter {

    Context context;
    ArrayList<ListPojo> listPojos;

    public AdapterList(Context context, ArrayList<ListPojo> listPojos) {
        this.context = context;
        this.listPojos = listPojos;
    }

    @Override
    public int getCount() {
        return listPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_list,parent,false);

        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.description);
        ImageView image = view.findViewById(R.id.image);

        title.setText(listPojos.get(position).getTitle());
        description.setText(listPojos.get(position).getDescription());
        image.setImageResource(listPojos.get(position).getImages());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"position "+position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
