package fytyr.idea_projects.course_java.CSV;

import java.io.*;
import java.util.Scanner;

public class CsvToHtml {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong way to files : need to specify ways to 2 files. 1) csv; 2) html");
            return;
        }

        try (Scanner csvFile = new Scanner(new FileInputStream(args[0]));
             PrintWriter printWriter = new PrintWriter(args[1])) {

            printWriter.print("<!DOCTYPE html><html><head><meta charset=\"utf-8\" /><title>HTML table</title></head><body><table border=\"1\">");
            printWriter.print("<tr><td>");

            boolean isInQuotes = false;
            while (csvFile.hasNext()) {
                String csvFileLine = csvFile.nextLine();

                for (int i = 0; i < csvFileLine.length(); i++) {
                    if ((csvFileLine.charAt(i) == '"' || isInQuotes) && (i < csvFileLine.length() - 1)) {
                        if (csvFileLine.charAt(i) == '"' && !isInQuotes) {
                            i++;
                        }
                        isInQuotes = true;
                        if (csvFileLine.charAt(i) == '"' && csvFileLine.charAt(i + 1) == '"') {
                            printWriter.print('"');
                            i++;
                        } else if (csvFileLine.charAt(i) == '"') {
                            isInQuotes = false;
                        } else {
                            printWriter.print(replaceChar(csvFileLine.charAt(i)));
                        }
                    } else if (csvFileLine.charAt(i) == ',') {
                        printWriter.print("</td><td>");
                    } else if (csvFileLine.charAt(i) == '"') {
                        isInQuotes = false;
                    } else {
                        printWriter.print(replaceChar(csvFileLine.charAt(i)));
                    }
                }

                if (isInQuotes) {
                    printWriter.print("<br/>");
                } else if (!csvFile.hasNext()) {
                    printWriter.print("</td></tr></table></body></html>");
                } else {
                    printWriter.print("</td></tr><tr><td>");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
    }

    private static String replaceChar(char c) {
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

