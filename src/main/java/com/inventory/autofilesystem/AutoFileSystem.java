package com.inventory.autofilesystem;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

/**
 * Created by wmonks on 3/24/2016.
 */
public class AutoFileSystem {

    List<String> files;
    List<String> directories;

    public String fullDir = "";

    String root = "C:\\autofiletest";
    Queue<String> subdir = new PriorityQueue<String>();

    public AutoFileSystem() {
        files = new ArrayList<String>();
        PopulateFiles();
    }

    public void AddSubdirectory(String subdirectory) {
        subdir.add(subdirectory);
    }

    public void RemoveSubdirectory() {
        if(subdir.size() > 0)
            subdir.remove();

    }

    public void PopulateFiles() {
        String dir = root + "\\";

        for(String sdir : subdir)
        {
            dir += sdir + "\\";
        }

        fullDir = dir;

        File f = new File(dir);



        if(f!=null && f.list() != null) {

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
