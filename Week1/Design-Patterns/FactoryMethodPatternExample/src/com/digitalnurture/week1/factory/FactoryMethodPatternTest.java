package com.digitalnurture.week1.factory;

public class FactoryMethodPatternTest {

    public static void main(String[] args) {
        DocumentFactory[] factories = {
                new WordDocumentFactory(),
                new PdfDocumentFactory(),
                new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            Document document = factory.newDocument();
            document.save();
            System.out.println("Created type: " + document.getType());
            System.out.println("Factory used: " + factory.getClass().getSimpleName());
            System.out.println();
        }
    }
}
