package com.digitalnurture.week1.factory;

public class WordDocument implements Document {

    @Override
    public void open() {
        System.out.println("Opening a Word document (.docx)");
    }

    @Override
    public void save() {
        System.out.println("Saving a Word document (.docx)");
    }

    @Override
    public String getType() {
        return "Word";
    }
}
