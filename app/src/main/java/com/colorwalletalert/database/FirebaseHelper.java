package com.colorwalletalert.database;

import android.util.Log;

import com.colorwalletalert.model.Category;
import com.colorwalletalert.model.CategorySpend;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class FirebaseHelper {
    private final String TAG = "FirebaseHelper";
    private final String DOCUMENT_CATEGORY = "categories";
    private final String DOCUMENT_SPEND = "spends";
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


    /***
     * name: saveCategoriesSpend
     * description: save a new Category Spend
     * params: CategorySpend categorySpend
     *
     * @return
     * @param categorySpend
     */
    public void saveCategorySpend(CategorySpend categorySpend){

        DatabaseReference messagesRef = database.getReference(DOCUMENT_SPEND);
        String categorySpendIdKey = messagesRef.push().getKey();
        messagesRef.child(categorySpendIdKey).setValue(categorySpend);
    }

    /***
     * name: getCategoriesSpend
     * description: get spends by category
     * params: CategorySpend categorySpend
     *
     * @return
     * @param category
     */
    public void getCategorySpend(Category category){

        DatabaseReference messagesRef = database.getReference(DOCUMENT_SPEND);

        ValueEventListener queryValueListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    Log.i(TAG, "Value = " + next.child("category").getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        Query categorySpendsQuery = messagesRef.
                orderByChild("category/description").equalTo(category.getDescription());
        categorySpendsQuery.addListenerForSingleValueEvent(queryValueListener);
    }
}
