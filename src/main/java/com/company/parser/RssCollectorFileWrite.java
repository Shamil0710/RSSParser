package com.company.parser;

import com.company.Property;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RssCollectorFileWrite {

    /**
     * После ребейза метод будет принмать Репозиторий в качестве аргумента
     * @param numberOfLines
     * @throws IOException
     */

    public static void toPrint (int numberOfLines) throws IOException {

        FileWriter f = new FileWriter(Property.DIRECTORY, false);


        BufferedWriter bw = new BufferedWriter(f);

        int count = 0;


        try {


            for (RSSElement  element: rssElements) {

                count++;
                bw.write(count + " " + element.getTitle() + "<br>");
                bw.write("<a href=\"" + element.getUrl() + "\">" + element.getUrl() + "</a>" + "<br>");
                bw.write(element.getPublicationDate().toString() + "<br>");
                bw.write("<p>");


                if (count == numberOfLines) break;


            }

        }
        finally {
            bw.close();
            f.close();

        }

    }

}
