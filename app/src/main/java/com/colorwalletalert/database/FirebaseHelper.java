package com.colorwalletalert.database;

import android.util.Log;

import com.colorwalletalert.model.Category;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private final String TAG = "FirebaseHelper";
    private final String DOCUMENT_CATEGORY = "categories";
    private final String DOCUMENT_SPENDS = "spends";
    private static FirebaseHelper instance;
    final FirebaseDatabase database;


    public FirebaseHelper(){
        database = FirebaseDatabase.getInstance();
    }

    public static FirebaseHelper getInstance(){
        if (instance == null) {
            instance = new FirebaseHelper();
        }
        return instance;
    }

    /***
     * name: readCategories
     * description: read from Firebase database all registered categories
     * params:
     *
     * @return
     */
    public FirebaseRecyclerOptions<Category> readCategories(){
        DatabaseReference mFirebaseDatabaseReference = database.getReference();
        DatabaseReference messagesRef = mFirebaseDatabaseReference.child(DOCUMENT_CATEGORY);

        SnapshotParser<Category> parser = new SnapshotParser<Category>() {
            @Override
            public Category parseSnapshot(DataSnapshot dataSnapshot) {
                Category friendlyMessage = dataSnapshot.getValue(Category.class);
                return friendlyMessage;
            }
        };


        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(messagesRef, parser).build();

        return options;
    }

    /***
     * name: saveCategories
     * description: save a new Category
     * params: Category category
     *
     * @return
     */
    public void saveCategory(Category category){
        DatabaseReference messagesRef = database.getReference(DOCUMENT_CATEGORY);
        String categoryIdKey = messagesRef.push().getKey();
        messagesRef.child(categoryIdKey).setValue(category);
    }

}
