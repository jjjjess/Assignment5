// ==============================================
// Jesse Tripp
// COSC 211
// Assignment 5 - PE 12.12
// Refactored program after Text I/O chapter.
// ==============================================

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise12_12 {
  public static void main(String[] args) throws Exception {
    success(args);
    File sourceFile = fileExistsCheck(args);
    StringBuilder buffer = new StringBuilder();
    formatFile(sourceFile, buffer);
    writeBuffer(sourceFile, buffer);
  }

  private static void writeBuffer(File sourceFile, StringBuilder buffer)
      throws FileNotFoundException {
    try (PrintWriter output = new PrintWriter(sourceFile);) {
      output.print(buffer.toString());
    }
  }

  private static void formatFile(File sourceFile, StringBuilder buffer)
      throws FileNotFoundException {
    try (Scanner input = new Scanner(sourceFile)) {
      while (input.hasNext()) {
        String s = input.nextLine();
        String s1 = s.trim();
        if (s1.charAt(0) == '{' && s1.length() > 1) {
          buffer.append(" {");
          buffer.append("\r\n" + s.replace('{', ' '));
        } else
          buffer.append("\r\n" + s);
      }
    }
  }

  private static File fileExistsCheck(String[] args) {
    File sourceFile = new File(args[0]);
    try {
      if (!sourceFile.exists()) {
        throw new FileNotFoundException();
      }
    } catch (Exception ex) {
      System.out.println("Source file " + args[0] + " not exist");
    }
    return sourceFile;
  }

  private static void success(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java Exercise12_12 filename");
      System.exit(1);
    }
  }
}
