package com.apps.ngenge.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.models.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.ViewHolder> {

    private List<Step> stepList;
    private Context context;
    private OnStepClickListener listener;

    public RecipeStepsAdapter(List<Step> stepList, Context context,OnStepClickListener listener) {
        this.stepList = stepList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_layout,
                parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(stepList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.stepLayout)
        RelativeLayout stepLayout;
        @BindView(R.id.shortDescView)
        TextView shortDescriptionView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final Step step, final OnStepClickListener onStepClickListener)
        {
            shortDescriptionView.setText(step.getShortDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStepClickListener.onStepClicked(step);
                }
            });
        }
    }


    public interface OnStepClickListener{
        void onStepClicked(Step step);
    }
}
