package fytyr.idea_projects.course_java.CSV;

import java.io.*;

public class CsvToHtml {
    public static void main(String[] args) {
        try (BufferedReader csvFile = new BufferedReader(new FileReader("Table.csv"));
             PrintWriter printwriter = new PrintWriter("Table.html")) {

            printwriter.print("<!DOCTYPE html><html><head><meta charset=\"utf-8\" /><title>HTML table</title></head><body><table border=\"1\">");
            printwriter.print("<tr><td>");

            boolean isInQuotes = false;
            String csvFileLine;
            while ((csvFileLine = csvFile.readLine()) != null) {
                for (int i = 0; i < csvFileLine.length(); i++) {
                    if ((csvFileLine.charAt(i) == '"' || isInQuotes) && (i < csvFileLine.length() - 1)) {
                        if (csvFileLine.charAt(i) == '"' && !isInQuotes) {
                            i++;
                        }
                        isInQuotes = true;
                        if (csvFileLine.charAt(i) == '"' && csvFileLine.charAt(i + 1) == '"') {
                            printwriter.print('"');
                            i++;
                        } else if (csvFileLine.charAt(i) == '"' && csvFileLine.charAt(i + 1) == ',') {
                            printwriter.print("</td><td>");
                            i++;
                            isInQuotes = false;
                        } else {
                            printwriter.print(charReplace(csvFileLine.charAt(i)));
                        }
                    } else if (csvFileLine.charAt(i) == ',') {
                        printwriter.print("</td><td>");
                    } else if (csvFileLine.charAt(i) == '"') {
                        i++;
                    } else {
                        printwriter.print(charReplace(csvFileLine.charAt(i)));
                    }
                }

                if (isInQuotes) {
                    printwriter.print("<br/>");
                } else {
                    printwriter.print("</td></tr><tr><td>");
                }
            }
            printwriter.print("</td></tr></table></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String charReplace(char c) {
        switch (c) {
            case '<':
                return "&lt";
            case '>':
                return "&gt";
            case '&':
                return "&amp";
        }
        return String.valueOf(c);
    }
}

