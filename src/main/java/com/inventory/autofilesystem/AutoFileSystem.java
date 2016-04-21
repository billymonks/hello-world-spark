package com.inventory.autofilesystem;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wmonks on 3/24/2016.
 */
public class AutoFileSystem {

    List<String> files;
    List<String> directories;

    String root = "C:\\autofiletest";
    String subdir = "";

    public AutoFileSystem() {
        files = new ArrayList<String>();
        PopulateFiles("");
    }

    public void PopulateFiles(String subdir) {
        this.subdir = subdir;

        File f = new File(root + "/" + subdir);



        if(f!=null) {

            files = new ArrayList<String>(Arrays.asList(f.list()));
            directories = new ArrayList<String>(Arrays.asList(f.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return new File(dir, name).isDirectory();
                }
            })));

        } else {
            files = new ArrayList<String>();
            directories = new ArrayList<String>();
        }

        files.removeAll(directories);

    }

    public List<String> getFiles() {
        return files;
    }

    public List<String> getDirectories() {
        return directories;
    }

    public void setDirectories(List<String> directories) {
        this.directories = directories;
    }
}
