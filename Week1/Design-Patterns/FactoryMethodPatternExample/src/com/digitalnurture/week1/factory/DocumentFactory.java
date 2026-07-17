package com.digitalnurture.week1.factory;

public abstract class DocumentFactory {

    public abstract Document createDocument();

    public Document newDocument() {
        Document document = createDocument();
        document.open();
        return document;
    }
}
