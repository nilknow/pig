package util.file;

import java.io.File;

/**
 * @Date 2019/5/30 16:51
 * @Mail 494939649@qq.com
 **/
public class FilePath {
    public static String getProjectRootPath() {
        return System.getProperty("user.dir");
    }

    public static String pathGenerator(String... directoryNames) {
        StringBuilder stringBuilder = new StringBuilder(File.separator);
        for (String dir : directoryNames) {
            stringBuilder.append(dir).append(File.separator);
        }
        return stringBuilder.toString().substring(0, stringBuilder.length() - 1);
    }

    public static String pointToSlash(String pointStylePath) {
        String[] strings = pointStylePath.split("\\.");
        return pathGenerator(strings);
    }

    public static String getMavenProjectJavaRoot(String groupId) {
        return getProjectRootPath()
                + FilePath.pathGenerator("src", "main", "java")
                + FilePath.pointToSlash(groupId);
    }

    public static void main(String[] args) {
        System.out.println(pointToSlash("a.b.c"));
    }
}
