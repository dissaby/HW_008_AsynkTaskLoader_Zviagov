package by.it_academy.hw_008_asynktaskloader_zviagov;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by dissa on 14.09.2016.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<StudentItem>> {

    private ArrayList<StudentItem> studentsList;
    private int i;

    public MyAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<StudentItem> loadInBackground() {
        try {
            JSONArray mJSONArray = new JSONArray(loadJSONFromAsset());
            studentsList = new ArrayList<StudentItem>();

            for (i = 0; i < mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String idValue = mJSONObject.getString("id");
                String firstNameValue = mJSONObject.getString("firstName");
                String lastNameValue = mJSONObject.getString("lastName");
                StudentItem student = new StudentItem();
                student.setId(idValue);
                student.setFirstName(firstNameValue);
                student.setLastName(lastNameValue);
                studentsList.add(student);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.sort(studentsList, mapComparator);
        return studentsList;
    }

    @Override
    public void deliverResult(ArrayList<StudentItem> data) {
        studentsList = data;
        if (isStarted()) {
            super.deliverResult(data);
        }

    }

    @Override
    protected void onStartLoading() {
        if (studentsList != null) {
            super.deliverResult(studentsList);
        } else {
            forceLoad();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("students.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public Comparator<StudentItem> mapComparator = new Comparator<StudentItem>() {
        public int compare(StudentItem m1, StudentItem m2) {
            return m1.getFirstName().compareTo(m2.getFirstName());
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyAsyncTaskLoader that = (MyAsyncTaskLoader) o;

        if (i != that.i) return false;
        return studentsList != null ? studentsList.equals(that.studentsList) : that.studentsList == null;

    }

    @Override
    public int hashCode() {
        int result = studentsList != null ? studentsList.hashCode() : 0;
        result = 31 * result + i;
        return result;
    }
}
