/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package programming.task2;


import java.io.File;
import java.util.*;

public class Du {
    public enum Base {
        StandardBase(1024),
        DecimalBase(1000);
        private final int x;

        Base(int x) {
            this.x = x;
        }
    }

    private static final List<String> PostFix = Arrays.asList("B", "KB", "MB", "GB");

    // Размер папки
      public long directorySize(File directory) {
              long length = 0;
              for (File file : Objects.requireNonNull(directory.listFiles())) {
                  if (file.isFile())
                      length += file.length();
                  else
                      length += directorySize(file);
              }
              return length;
          }

    // Размер папки/файла в соответствии с заданными параметрами
    public String outputSize(String inputName, boolean hMark, boolean siMark, boolean cMark) {
        File file = new File(inputName);
        long size = 0;

        if (!file.exists()) throw new IllegalArgumentException();

        if (file.isFile()) size = file.length();
        else if (file.isDirectory()) size = directorySize(file);

        return toHumanRead(size, siMark, hMark, cMark);
    }

    // перебор всех заданных файлов и вывод
    private long totalSize = 0;
    public void fileEnumeration(List<String> list, boolean hMark, boolean siMark, boolean cMark) {
        totalSize = 0;
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
                System.out.print(" = ");
                System.out.println(outputSize(list.get(i), hMark, siMark, cMark));
            }
            if (cMark) {
                System.out.print("Total size = ");
                System.out.println(toHumanRead(totalSize, siMark, hMark, true));
            }
    }

    // перевод размера согласно заданным параметрам
    public String toHumanRead(long size, boolean siMark, boolean hMark, boolean cMark) {
        StringBuilder result = new StringBuilder();
        int base = Base.StandardBase.x;
        if (siMark) base = Base.DecimalBase.x;

        if (cMark) totalSize += size;

        if (hMark) {
            int k = 0;
            while (size > 1023 && k < 3) {
                size /= base;
                k++;
            }
            result.append(size).append(PostFix.get(k));
        } else if (size > 1023) result.append(size / base);
        else result.append(1);

        return result.toString();
    }
}