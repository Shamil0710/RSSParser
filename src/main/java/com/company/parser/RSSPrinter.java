package com.company.parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RSSPrinter {

    public void Print(int numberOfLines, boolean sorting, String saveDirectory) throws IOException {
        FileWriter f = new FileWriter(saveDirectory, false);
        BufferedWriter bw = new BufferedWriter(f);
        int count = 0;

        try {
            for (RSSElement element : rssElements) {
                bw.write(element.getTitle() + "<br>");
                bw.write("<a href=\"" + element.getUrl() + "\">" + element.getUrl() + "</a>" + "<br>");
                bw.write(element.getPublicationDate().toString() + "<br>");
                bw.write("<p>");
                count++;
                if (count == numberOfLines) break;
            }
        } finally {
            bw.close();
            f.close();
        }
    }

}
