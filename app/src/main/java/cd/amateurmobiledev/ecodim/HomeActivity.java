package cd.amateurmobiledev.ecodim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<ListPojo> list;
    AdapterList adapterList;

    SpaceNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView = findViewById(R.id.space);

        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_home_black_24));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_search_black_24));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_notifications_24));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_account_circle_24));

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(HomeActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
                navigationView.setCentreButtonSelectable(true);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(HomeActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(HomeActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

        listView = findViewById(R.id.list_view);

        listShow();

        adapterList = new AdapterList<>(this,list);
        listView.setAdapter(adapterList);

    }

    private void listShow() {
        list = new ArrayList<ListPojo>();

        list.add(new ListPojo("Afro", "my africa queen", R.drawable.md11));
        list.add(new ListPojo("Afro", "my africa queen", R.drawable.md11));
        list.add(new ListPojo("Afro", "my africa queen", R.drawable.md11));
        list.add(new ListPojo("Afro", "my africa queen", R.drawable.md11));
        list.add(new ListPojo("Afro", "my africa queen", R.drawable.md11));
        list.add(new ListPojo("Afro", "my africa queen", R.drawable.md11));
        list.add(new ListPojo("Afro", "my africa queen", R.drawable.md11));

    }
}
