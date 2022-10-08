package com.example.reverb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reverb.R;
import com.example.reverb.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryAdapter (List<Category> categoryList, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryList = categoryList;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);

        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        Category newsCategory = categoryList.get(position);

        Picasso.get().load(newsCategory.getCategoryImageUrl()).into(holder.categoryImageView);
        holder.categoryTextView.setText(newsCategory.getCategory());

        // using the interface to add onCLickListener on each news category item

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryImageView;
        private TextView categoryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImageView = itemView.findViewById(R.id.category_imageView);
            categoryTextView = itemView.findViewById(R.id.category_textView);
        }
    }

    // interface to implement onClick functionality on the news categories

    public interface CategoryClickInterface {
        void onCategoryClick (int categoryElementPosition);
    }
}
