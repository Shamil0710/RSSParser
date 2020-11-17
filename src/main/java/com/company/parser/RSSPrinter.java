package com.company.parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RSSPrinter {

    public static void toPrint (int numberOfLines, String saveDirectory, List<RSSElement> rssElements) {
        try (FileWriter f = new FileWriter(saveDirectory, false);
             BufferedWriter bw = new BufferedWriter(f)) {
            int count = 0;
            for (RSSElement element : rssElements) {
                bw.write(element.getTitle() + "<br>");
                bw.write("<a href=\"" + element.getUrl() + "\">" + element.getUrl() + "</a>" + "<br>");
                bw.write(element.getPublicationDate().toString() + "<br>");
                bw.write("<a href=\"" + element.getComponentUrl() + "\">" + element.getComponentUrl() + "</a>" + "<br>");
                bw.write("<p>");
                count++;
                if (count == numberOfLines) break;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
