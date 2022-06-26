import java.io.File;
import java.io.IOException;

/**
 * 文件类File
 */
public class IO_FileDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Asterion\\Desktop\\Veni\\JavaEra\\file\\rea.txt");
        System.out.println(file.getParentFile());
        System.out.println();
        File fileAll = new File("D:\\");
        File renameFile = new File("C:\\Users\\Asterion\\Desktop\\Veni\\JavaEra\\file");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();   //父路径不存在则创建
        }
        if (file.exists()) {
            file.delete();  //存在则删除文件
        } else {
            System.out.println(file.createNewFile());   //不存在文件则创建
        }

//        listFiles(fileAll);
        listFiles(renameFile);
    }

    /**
     * 递归查询文件下的子目录
     *
     * @param file
     */
    public static void listFiles(File file) {
        if (file.isDirectory()) {
            File[] results = file.listFiles();
            if (results != null) {
                for (int x = 0; x < results.length; x++) {
                    listFiles(results[x]);
                }
            }
        }
        System.out.println(file);
    }

    /**
     * 批量重命名文件后缀
     *
     * @param file
     */
    public static void renameFiles(File file) {
        if (file.isDirectory()) {
            File[] results = file.listFiles();
            if (results != null) {
                for (int x = 0; x < results.length; x++) {
                    renameFiles(results[x]);
                }
            }
        } else if (file.isFile()) {
            String filename = null;
            if (file.getName().contains(".")) {
                filename = file.getName().substring(0, file.getName().lastIndexOf(".")) + ".txt";
            } else {
                filename = file.getName() + ".txt";
            }
            File newFile = new File(file.getParentFile(), filename);
            file.renameTo(newFile);  //重命名
        }
    }

}
