package bryan.paintstory;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.test.AndroidTestCase;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by byamba on 5/5/17.
 */

public class TestDb extends AndroidTestCase {

    public void testInsertToDatabase(){
        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference testReference = mDatabase.getReference("TestPlace");

        final String testValue = "Just Tested!!!";
        testReference.setValue(testValue);

        testReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                assertEquals(value, testValue);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public void testColorChange(){
        final PaintView paintView = new PaintView(getContext());
        int newColor = Color.WHITE;
        paintView.setCurrentColor(newColor);
        assertEquals(paintView.getCurrentColor(), newColor);
    }

    public void testSaveImage() {
        final PaintView mPaintView = new PaintView(getContext());
        File folder = new File(Environment.getExternalStorageDirectory().toString());
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }

        System.out.println(success + "folder");

        File file = new File(Environment.getExternalStorageDirectory().toString() + "/test.png");

        try {
            success = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(success + "file");


        FileOutputStream ostream = null;
        try {
            ostream = new FileOutputStream(file);
            System.out.println(ostream);
            View targetView = mPaintView;
            Bitmap well = mPaintView.getBitmap();
            Bitmap save = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            Canvas now = new Canvas(save);
            now.drawRect(new Rect(0, 0, 320, 480), paint);
            now.drawBitmap(well, new Rect(0, 0, well.getWidth(), well.getHeight()), new Rect(0, 0, 320, 480), null);

            if (save == null) {
                System.out.println("NULL bitmap save\n");
            }
            save.compress(Bitmap.CompressFormat.PNG, 100, ostream);

        } catch (NullPointerException e) {
            e.printStackTrace();
            //Toast.makeText(getActivity(), "Null error", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //Toast.makeText(this.getActivity(), "File error", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(this.getActivity(), "IO error", Toast.LENGTH_SHORT).show();
        }

        assertTrue(file.exists());
    }



}
