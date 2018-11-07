package com.apps.ngenge.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private List<Recipe> recipes;
    private Context context;
    private final RecipeClickListener listener;

    public RecipeListAdapter(List<Recipe> recipes, Context context,RecipeClickListener listener) {
        this.recipes = recipes;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(recipes.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.recipeImage) ImageView recipeImageView;
        @BindView(R.id.recipeName) TextView recipeNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final Recipe recipe, final RecipeClickListener recipeClickListener)
        {
            recipeNameTextView.setText(recipe.getName());
            if(!recipe.getImage().equals("") && recipe.getImage() != null)
            {
                Picasso.get()
                        .load(recipe.getImage())
                        .placeholder(R.color.colorAccent)
                        .into(recipeImageView);
            }
            itemView.setOnClickListener(v -> recipeClickListener.onRecipeItemClicked(recipe));


        }
    }


    public interface RecipeClickListener{
        void onRecipeItemClicked(Recipe recipe);
    }
}
