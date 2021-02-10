package com.laioffer.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {
    interface ItemCallback {
        // onOpenDetails is to be implemented for opening a new fragment for article details.
        void onOpenDetails(Article article);
    }

    // 1. Supporting data:
    private List<Article> articles = new ArrayList<>();
    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

    public void setArticles(List<Article> newList) {
        articles.clear();
        articles.addAll(newList);
        notifyDataSetChanged();
    }

    // 2. Adapter overrides:
    // onCreateViewHolder is for providing the generated item views;
    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    // onBindViewHolder is for binding the data with a view.
    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        holder.itemTitleTextView.setText(article.title);
        // Display Images with Picasso
        Picasso.get().load(article.urlToImage).resize(200, 200).into(holder.itemImageView);

        // itemCallback to inform the implementer the onOpenDetails event when an item is clicked.
        // In SearchNewsAdapter.onBindViewHolder we call onOpenDetails whenever the item is clicked.
        holder.itemView.setOnClickListener(v ->
        {
            if (itemCallback != null) {
                itemCallback.onOpenDetails(article);
            }
        });
    }

    // getItemCount is for providing the current data collection size;
    @Override
    public int getItemCount() {
        return articles.size();
    }

    // 3. SearchNewsViewHolder:
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView favoriteImageView;
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);

            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            favoriteImageView = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }
}
