package practice6;

import other.Show;

import java.io.*;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * READER: IN  - читает существующий файл
 * WRITER: OUT - создает новый если нет файла
 */


public class Example
{
    public static void main(String[] args) throws IOException
    {
        /** ---------------------------------- EX1 --------------------------------- */
        Show.show(1);
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (!(line = reader.readLine()).equals("quit"))
            sb.append(line + " ");
        line = sb.toString();
        System.out.println("THIS: " + line);
        Show.getch();


        /** ---------------------------------- EX2 --------------------------------- */
        Show.show(2);
        float hits = 10, a = 3;
        System.out.println(String.format("hits / a = %.3f", hits / a));
        System.out.println(new Formatter().format("hits / a = %.3f", hits / a));
        Show.getch();


        /** ---------------------------------- EX3 --------------------------------- */
        Show.show(3);
        Calendar c = Calendar.getInstance();
        System.out.format("%td.%tm.%tY \n", c, c, c);
        System.out.format(Locale.ENGLISH, "%tB %td, %tY \n", c, c, c);
        Show.getch();


        /** ---------------------------------- EX4 --------------------------------- */
        Show.show(4);
        c.set(1997, 7, 19);
        System.out.println(String.format(Locale.ENGLISH, "My birthday: %tB %td, %tY%n", c, c, c));
        Show.getch();


        /** ---------------------------------- EX5 --------------------------------- */
        Show.show(5);

        try (BufferedReader fin = new BufferedReader(new FileReader(args[0]));
                BufferedWriter fout = new BufferedWriter(new FileWriter(args[1])))
        {
            char[] buffer = new char[250];
            int length;
            while ((length = fin.read(buffer)) != -1)
            {
                System.out.print(String.valueOf(buffer, 0, length));
                fout.write(buffer, 0, length);
            }
            System.out.println();
        }
        catch (ArrayIndexOutOfBoundsException | IOException ex)
        {
            ex.printStackTrace();
            return;
        }
        Show.getch();


        /** ---------------------------------- EX6 --------------------------------- */
        Show.show(6);
        try (FileInputStream fin = new FileInputStream(new File(args[0])))
        {
            byte[] bytes = new byte[15];
            System.out.println("bytes read: " + fin.read(bytes));
            System.out.println("BYTES: " + Arrays.toString(bytes));
            System.out.println("CHARS: " + new String(bytes));
        }
        catch (ArrayIndexOutOfBoundsException | IOException ex)
        {
            ex.printStackTrace();
            return;
        }
        Show.getch();


        /** ---------------------------------- EX7 --------------------------------- */
        Show.show(7);
        try (RandomAccessFile rndfin = new RandomAccessFile(args[0], "r"))
        {
            System.out.println("length of file: " + rndfin.length());
            System.out.println();
            rndfin.seek(20);
            byte[] buffer = new byte[250];
            int length;
            while ((length = rndfin.read(buffer)) != -1)
                System.out.print(new String(buffer, 0, length));
            System.out.println();
        }
        catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | IOException ex)
        {
            ex.printStackTrace();
            return;
        }
        Show.getch();


        /** ---------------------------------- EX8 --------------------------------- */
        Show.show(8);
        try (ZipFile zfile = new ZipFile(args[2]))
        {
            System.out.println("archive name: " + zfile.getName());
            System.out.println(zfile.getComment());
            System.out.println("count of entries: " + zfile.size());

            Enumeration<? extends ZipEntry> nm = zfile.entries();
            System.out.println();

            while (nm.hasMoreElements())
            {
                ZipEntry entry = nm.nextElement();
                if (entry.isDirectory()) System.out.println("<DIR>  " + entry.getName());
                else System.out.println("       " + entry.getName());
            }
        }
        catch (ArrayIndexOutOfBoundsException | IOException ex)
        {
            System.out.println("Archive not found");
            ex.printStackTrace();
            return;
        }
        Show.getch();


        /** ---------------------------------- EX9 --------------------------------- */
        if (args.length < 5) return;
        Show.show(9);

        // определяет текущую директорию:
        String currrentDir = System.getProperty("user.dir");
        System.out.println("current dir: " + currrentDir);
        //класс File не только файлы открывает, можно и директории (папки),
        //можно просто передать точку, тогда откроется текущая директория (т.е проекта)

        File file = new File(args[3]);
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file.getPath() = " + file.getPath());
        //если в конструкторе передать точку, то не будет известна директория выше
        System.out.println("file.getParent() = " + file.getParent());

        System.out.println("\nList of files and folders for archiving:");
        for (File f : file.listFiles())
        {
            if (f.isDirectory()) System.out.println("<DIR>  " + f.getName());
            else System.out.println("       " + f.getName());
        }

        long start = System.currentTimeMillis();
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(args[4])))
        {
            byte[] buffer = new byte[8192];
            doZip(zipOut, file, buffer);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return;
        }
        long end = System.currentTimeMillis();


        System.out.println("Time of operation " + (end - start) + " ms");
        System.out.println("Done.");
    }


    public static void doZip(ZipOutputStream stream, File dir, byte[] buffer) throws IOException
    {
        for (File f : dir.listFiles())
        {
            if (f.isDirectory())
            {
                //для пустых папок:
                ZipEntry entry = new ZipEntry(f.getPath() + "\\");
                entry.setLastModifiedTime(FileTime.fromMillis(f.lastModified()));
                stream.putNextEntry(entry);

                doZip(stream, f, buffer); //рекурсивный вызов
                stream.closeEntry();
            }
            else
            {
                ZipEntry entry = new ZipEntry(f.getPath());
                entry.setLastModifiedTime(FileTime.fromMillis(f.lastModified()));
                stream.putNextEntry(entry);

                int length;
                FileInputStream fin = new FileInputStream(f);
                while ((length = fin.read(buffer)) != -1)
                    stream.write(buffer, 0, length);
                stream.closeEntry();
                fin.close();
            }
        }

    }


}