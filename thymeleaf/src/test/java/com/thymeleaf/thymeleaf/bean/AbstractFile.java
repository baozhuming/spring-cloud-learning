package com.thymeleaf.thymeleaf.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合模式
 */
public abstract class AbstractFile {
    protected String name;
    public void printName(){
        System.out.println(name);
    }
    public abstract Boolean addChild(AbstractFile file);
    public abstract Boolean removeChild(AbstractFile file);
    public abstract List<AbstractFile> getChildren();
}

class File extends AbstractFile{
    public File(String name) {
        this.name = name;
    }

    @Override
    public Boolean addChild(AbstractFile file) {
        return this.addChild(file);
    }

    @Override
    public Boolean removeChild(AbstractFile file) {
        return false;
    }

    @Override
    public List<AbstractFile> getChildren() {
        return null;
    }
}
class Folder extends AbstractFile{
    private List<AbstractFile> childList;


    public Folder(String name) {
        this.childList = new ArrayList<AbstractFile>();
        this.name = name;
    }

    @Override
    public Boolean addChild(AbstractFile file) {

        return this.childList.add(file);
    }

    @Override
    public Boolean removeChild(AbstractFile file) {
        return false;
    }

    @Override
    public List<AbstractFile> getChildren() {
        return childList;
    }
}
