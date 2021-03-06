package p7.practice;

import other.Show;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;


public class Example
{
    static final String SP = File.separator;
    static final String outPath = "out" + SP + "pr7" + SP;
    static final String inPath = "input" + SP;

    static
    {
        new File(outPath).mkdirs();
    }

    
    public static void main(String[] args) throws IOException
    {
        Random rand = new Random();
        File file;
        /** ---------------------------------- EX1 --------------------------------- */
        Show.show(1);
        file = new File(outPath + "newFile.txt");
        System.out.println("create: " + file.createNewFile());
        System.out.println("create tmp-file: " +
                                   File.createTempFile("smth", ".tmp", new File(outPath)));
        Show.getch();


        /** ---------------------------------- EX2 --------------------------------- */
        Show.show(2);
        String mytxt = "myfile.txt";
        file = new File(outPath + mytxt);
        System.out.println("create \"" + mytxt + "\" = " + file.createNewFile());

        mytxt = rand.nextInt(500) + "renamed.txt";
        System.out.print("Push button for rename file");
        Show.getch();
        System.out.println("rename to \"" + mytxt + "\" = " + file.renameTo(new File(outPath + mytxt)));

        // переименование директории:
        System.out.print("\nPush button for rename directory");
        Show.getch();
        file = new File(outPath);
        System.out.println(file + " - isDirectory() = " + file.isDirectory());
        String dirName = file.getParent() + SP + "MY_DIR";
        System.out.println("rename to \"" + dirName + "\" = " + file.renameTo(new File(dirName)));

        // переименование директории:
        System.out.print("\nPush button for rename directory again");
        Show.getch();
        file = new File(dirName);
        System.out.println("rename again = " + file.renameTo(new File(outPath)));
        Show.getch();


        /** ---------------------------------- EX3 --------------------------------- */
        Show.show(3);
        file = new File(outPath + mytxt);
        System.out.println("delete \"" + mytxt + "\" = " + file.delete());
        Show.getch();


        /** ---------------------------------- EX4 --------------------------------- */
        Show.show(4);
        file = new File(outPath + "newFile.txt");
        System.out.println("Writable? = " + file.canWrite());
        file.setWritable(false);
        System.out.println("Writable? = " + file.canWrite());
        Show.getch();


        /** ---------------------------------- EX5 --------------------------------- */
        Show.show(5);
        System.out.println("before last modified = " + new Date(file.lastModified()));
        file.setLastModified(file.lastModified() - 5 * 86_400_000);
        System.out.println(" after last modified = " + new Date(file.lastModified()));
        Show.getch();


        /** ---------------------------------- EX6 --------------------------------- */
        Show.show(6);
        file = new File(inPath + "projFile.zip");
        System.out.println(file + "\nsize = " + file.length() + "  bytes");
        Show.getch();


        /** ---------------------------------- EX7 --------------------------------- */
        Show.show(7);
        System.out.println(file + " - Exists? = " + file.exists());
        file = new File(inPath + "readme.txt");
        System.out.println(file + " - Exists? = " + file.exists());
        Show.getch();


        /** ---------------------------------- EX8 --------------------------------- */
        Show.show(8);
        file = new File(inPath + "projFile.zip");
        System.out.println(file + " - Exists? = " + file.exists());
        System.out.println("rename = " + file.renameTo(new File(outPath + "projFile.zip")));
        Show.getch();
        //вернуть обратно
        new File(outPath + "projFile.zip").renameTo(new File(inPath + "projFile.zip"));


        /** ---------------------------------- EX9 --------------------------------- */
        Show.show(9);
        file = new File(inPath + "projFile.zip");
        System.out.println("file = " + file);
        System.out.println(file + " - Exists? = " + file.exists());
        System.out.println("Absolute path: " + file.getAbsolutePath());
        Show.getch();


        /** ---------------------------------- EX10 --------------------------------- */
        Show.show(10);
        System.out.println("file = " + file);
        System.out.println("file.isDirectory() = " + file.isDirectory());
        System.out.println("file.isFile() = " + file.isFile());
        Show.getch();


        /** ---------------------------------- EX11 --------------------------------- */
        Show.show(11);
        file = new File(inPath + "filesPr7");
        Dir.printDir(file);
        Show.getch();


        /** ---------------------------------- EX12 --------------------------------- */
        Show.show(12);
        System.out.println("HTML-FILES");
        FileExtensionFilter filter = new FileExtensionFilter("html");
        file = new File(inPath + "filesPr7");
        filter.printDir(file);
        Show.getch();


        /** ---------------------------------- EX13 --------------------------------- */
        Show.show(13);
        file = new File(outPath + "newDir" + SP + "sources" + SP + "some");
        System.out.println("file.mkdirs() = " + file.mkdirs());
        file = new File(outPath + "newDir" + SP + "other" + SP + "pics");
        System.out.println("file.mkdirs() = " + file.mkdirs());

        System.out.println("Done.");
    }
}


