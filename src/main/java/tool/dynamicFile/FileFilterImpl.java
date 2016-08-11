package tool.dynamicFile;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Administrator on 2016/4/22.
 */
public class FileFilterImpl implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return false;
    }
}
