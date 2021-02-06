package com.laioffer.tinnews;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.laioffer.tinnews.database.ArticleDao;
import com.laioffer.tinnews.model.Article;


@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class TinNewsDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
