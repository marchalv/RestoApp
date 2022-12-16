package com.example.restoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restoapp.models.Meal;
import com.example.restoapp.models.Order;

public class ProductFragment extends Fragment {
    private final Meal meal;
    private ImageView image;
    private TextView name, description;
    private Button addToOrderButton;
    MenuFragment menuFragment = new MenuFragment();

    public ProductFragment(Meal meal) {
        this.meal = meal;
    }

    public ProductFragment() {
        this.meal = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meal_layout, container, false);
        image = view.findViewById(R.id.meal_image_view);
        name = view.findViewById(R.id.meal_name_text_view);
        description = view.findViewById(R.id.meal_desc_text_view);
        addToOrderButton = view.findViewById(R.id.add_button);
        int resId = getActivity().getResources().getIdentifier("item_" + meal.getImageURL() + "_icon", "drawable",getActivity().getPackageName());
        image.setImageDrawable(getActivity().getResources().getDrawable(resId));
        name.setText(meal.getName());
        description.setText(meal.getDescription());
        addToOrderButton.setText("Add to order - " + meal.getPrice() + "€");

        Bundle bundle = new Bundle();
        Order order = bundle.getParcelable("order");

        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //order.addMeal(meal);
                Toast.makeText(getActivity(), "Meal added to order", Toast.LENGTH_SHORT).show();
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, menuFragment).commit();
            }
        });
        return view;
    }
}
