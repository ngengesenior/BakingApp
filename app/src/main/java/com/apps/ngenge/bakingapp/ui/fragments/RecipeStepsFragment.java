package com.apps.ngenge.bakingapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.adapters.RecipeStepsAdapter;
import com.apps.ngenge.bakingapp.models.Ingredient;
import com.apps.ngenge.bakingapp.models.Recipe;
import com.apps.ngenge.bakingapp.models.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepsFragment extends Fragment implements RecipeStepsAdapter.OnStepClickListener {
    private static String RECIPE_TAG = "RECIPE";
    @BindView(R.id.ingredientsTextView)
    TextView ingredientsView;
    @BindView(R.id.stepsRecyclerView)
    RecyclerView stepsRecyclerView;
    private Recipe recipe;
    private List<Step> stepList;
    private RecyclerView.ItemDecoration decoration;
    private boolean isTablet;

    private OnStepSelectedListener onStepSelectedListener;



    public static RecipeStepsFragment newInstance(Recipe recipe) {
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        Bundle args = new Bundle();
        args.putParcelable(RECIPE_TAG, recipe);
        fragment.setArguments(args);
        Log.i("PUT-REC", "Putting recipe");
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isTablet = getResources().getBoolean(R.bool.isTablet);
        if (getArguments() != null) {
            recipe = getArguments().getParcelable(RECIPE_TAG);
            Log.i("PUT","Getting args");
            stepList = recipe.getSteps();
            decoration = new DividerItemDecoration(this.getContext(),1);
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_steps,container,false);
        ButterKnife.bind(this, view);

        List<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients) {
            ingredientsView.append("\u2022" + " " + ingredient.getIngredient() + "(" + ingredient.getQuantity() + " " + ingredient.getMeasure() + ")\n");
        }

        stepsRecyclerView.addItemDecoration(decoration);
        stepsRecyclerView.setAdapter(new RecipeStepsAdapter(stepList,this.getContext(),this));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStepClicked(Step step) {

        onStepSelectedListener.onStepSelected(step);

    }

    public interface OnStepSelectedListener{
        void onStepSelected(Step step);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onStepSelectedListener = (OnStepSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepSelectedListener");
        }

    }
}
