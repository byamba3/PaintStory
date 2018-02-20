package bryan.paintstory;

import android.graphics.Path;
/**
 * Created by byamba on 4/11/17.
 */

/**
 * Contains a path and its brush data
 */
public class FingerPath {

    public int color;
    public boolean emboss;
    public boolean blur;
    public int strokeWidth;
    public Path path;

    public FingerPath(int color, boolean emboss, boolean blur, int strokeWidth, Path path) {
        this.color = color;
        this.emboss = emboss;
        this.blur = blur;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }

}
