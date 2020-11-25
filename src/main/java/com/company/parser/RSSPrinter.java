package com.company.parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RSSPrinter {

    public static void printToHtml (int numberOfLines, String saveDirectory, List<RssDto> rssElements) {
        try (FileWriter f = new FileWriter(saveDirectory, false);
             BufferedWriter bw = new BufferedWriter(f)) {
            int count = 0;
            for (RssDto element : rssElements) {
                bw.write(element.getTittle() + "<br>");
                bw.write("<a href=\"" + element.getUrl() + "\">" + element.getUrl() + "</a>" + "<br>");
                bw.write(element.getDateTime().toString() + "<br>");
                bw.write("<p>");
                count++;
                if (count == numberOfLines) break;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void printToConsole(int numberOfLines, String saveDirectory, List<RSSElement> rssElements){
        int count = 0;
        for (RSSElement element : rssElements) {
            System.out.println(element.getTitle());
            System.out.println(element.getUrl());
            System.out.println(element.getPublicationDate());
            System.out.println(element.getComponentUrl());
            System.out.println();
            count++;
            if (count == numberOfLines) break;
        }
    }

}
