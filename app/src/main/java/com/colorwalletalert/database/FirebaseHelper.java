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
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FirebaseHelper {
    private final String TAG = "FirebaseHelper";
    private final String DOCUMENT_CATEGORY = "categories";
    private final String DOCUMENT_SPEND = "spends";

    private static FirebaseHelper instance;

    private String mCategoryKey;
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
        DatabaseReference messagesRef = database.getReference(DOCUMENT_CATEGORY);

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
     * name: readCategoriesSpends
     * description: read from Firebase database categories spends
     * params:
     *
     * @return
     */
    public FirebaseRecyclerOptions<CategorySpend> readCategoriesSpends(){
        DatabaseReference messagesRef = database.getReference(DOCUMENT_SPEND);

        SnapshotParser<CategorySpend> parser = new SnapshotParser<CategorySpend>() {
            @Override
            public CategorySpend parseSnapshot(DataSnapshot dataSnapshot) {
                CategorySpend friendlyMessage = dataSnapshot.getValue(CategorySpend.class);
                return friendlyMessage;
            }
        };

        FirebaseRecyclerOptions<CategorySpend> options = new FirebaseRecyclerOptions.Builder<CategorySpend>()
                .setQuery(messagesRef, parser).build();
        Log.d(TAG, String.valueOf(options.getSnapshots().size()));
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
//        category.setKey(categoryIdKey);
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
     * name: getCategorySpendToUpdate
     * description: get spends by category
     * params: CategorySpend categorySpend
     *
     * @return
     * @param category
     */
    public void getCategorySpendToUpdate(final Category category){

        DatabaseReference messagesRef = database.getReference(DOCUMENT_SPEND);

        ValueEventListener queryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Float mCategorySpend = Float.valueOf(0);
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    mCategorySpend = mCategorySpend +
                            Float.parseFloat(next.child("spendValue").getValue().toString());

                }
                updateCategorySpend(mCategorySpend, category);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        // query  all category spends and filtering by category given
        Query categorySpendsQuery = messagesRef.
                orderByChild("category/description").equalTo(category.getDescription());
        categorySpendsQuery.addListenerForSingleValueEvent(queryValueListener);
    }

    /***
     * name: updateCategorySpend
     * description: update a Category Spend
     * params: Category category
     *
     * @return
     * @param category
     */

    public void updateCategorySpend(final Float value, Category category){

        DatabaseReference messagesRef = database.getReference(DOCUMENT_CATEGORY);

        ValueEventListener queryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    next.getRef().child("spend").setValue(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };

        // query  all category spends and filtering by category given
        Query categoryKeyQuery = messagesRef.orderByChild("description")
                .equalTo(category.getDescription());
        categoryKeyQuery.addListenerForSingleValueEvent(queryValueListener);

    }
}

