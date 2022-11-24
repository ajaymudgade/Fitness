package com.example.fitness.Feature.FoodCURD.FoodCreate;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.fitness.Database.QueryContract;
import com.example.fitness.Database.QueryResponse;
import com.example.fitness.Database.foodQueryImplementation;
import com.example.fitness.Feature.FoodCURD.FoodCURDListner;
import com.example.fitness.Model.foodModel;
import com.example.fitness.R;

public class FoodCreateDialogFragment extends DialogFragment {

    private static FoodCURDListner foodCURDListner;

    private EditText userid;
    private EditText foodName;
    private EditText unit;
    private TextView date;
    private TextView time;
    private Button createButton;
    private Button cancelButton;

    private String userID = "";
    private String foodNameSTR = "";
    private String unitSTR = "";
    private String DATE = "";
    private String TIME = "";

    public FoodCreateDialogFragment() {
//
    }

    public static FoodCreateDialogFragment newInstance(String title, FoodCURDListner listener){
        foodCURDListner = listener;
        FoodCreateDialogFragment foodCreateDialogFragment = new FoodCreateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        foodCreateDialogFragment.setArguments(args);

        foodCreateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return foodCreateDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_food_create_dialog, container, false);

        userid = view.findViewById(R.id.useridEditText);
        foodName = view.findViewById(R.id.foodNameEditText);
        unit = view.findViewById(R.id.unitEditText);
        date = view.findViewById(R.id.dateTV);
        time = view.findViewById(R.id.timeTV);
        createButton = view.findViewById(R.id.createButton);
        cancelButton = view.findViewById(R.id.cancelButton);



        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID = userid.getText().toString();
                foodNameSTR = foodName.getText().toString();
                unitSTR = unit.getText().toString();
                DATE = date.getText().toString();
                TIME = time.getText().toString();

                final foodModel foodModel = new foodModel(userID, foodNameSTR, unitSTR, DATE, TIME);

                QueryContract.FoodQuery foodQuery = new foodQueryImplementation();
                foodQuery.createFood(foodModel, new QueryResponse<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        getDialog().dismiss();
                        foodCURDListner.onFoodListUpdate(data);
                        Toast.makeText(getContext(), "Food created successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(String message) {
                        foodCURDListner.onFoodListUpdate(false);
                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //noinspection ConstantConditions
            dialog.getWindow().setLayout(width, height);
        }
    }


}
