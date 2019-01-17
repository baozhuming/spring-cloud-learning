package com.thymeleaf.thymeleaf.bean;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class PersonTest {
    /**
     * 组合模式
     */
    @Test
    public void test1(){
        AbstractFile rootFolder = new Folder("c:\\");
        AbstractFile compositerFoler = new Folder("composite");
        AbstractFile windowsFolder = new Folder("windows");
        AbstractFile file = new File("TestComposite.java");
        rootFolder.addChild(compositerFoler);
        rootFolder.addChild(windowsFolder);
        compositerFoler.addChild(file);
        printTree(rootFolder);
    }
    @Test
    public void test2(){
        BigDecimal nu1 = new BigDecimal("0.000000000000000001");
        BigDecimal nu2 = new BigDecimal("0.000000000000000000001");
        System.out.println(nu1.subtract(nu1).toPlainString());
    }
    private static void printTree(AbstractFile ifile){
        ifile.printName();
        List<AbstractFile> children = ifile.getChildren();
        if (children == null)return;
        for (AbstractFile file:children){
            printTree(file);
        }
    }
}