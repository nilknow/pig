package util.database;

import util.string.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author poorguy.tech
 * @Date 2019/5/29 17:03
 * @Mail 494939649@qq.com / maxwangein@gmail.com
 **/
public class Database {
    private static Connection connection = null;

    public static Connection getCjConnection(String url, String user, String password) {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static Connection getConnection(String url, String user, String password) {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static DatabaseMetaData getDataBaseMetaData(Connection connection) {
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseMetaData;
    }

    public static List<String> getDatabaseInformation(Connection connection) {
        DatabaseMetaData dataBaseMetaData = getDataBaseMetaData(connection);
        List<String> dbInfoList = new ArrayList<>();
        try {
            dbInfoList.add(dataBaseMetaData.getDatabaseProductName());
            dbInfoList.add(dataBaseMetaData.getDatabaseProductVersion());
            dbInfoList.add(dataBaseMetaData.getUserName());
            dbInfoList.add(dataBaseMetaData.getURL());
            dbInfoList.add(dataBaseMetaData.getDriverName());
            dbInfoList.add(dataBaseMetaData.getDriverVersion());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbInfoList;
    }

    public static List<String> getTableNameList(Connection connection) {
        DatabaseMetaData dataBaseMetaData = getDataBaseMetaData(connection);
        ArrayList<String> tableNameList = new ArrayList<>();
        try {
            ResultSet resultSet = dataBaseMetaData.getTables(connection.getCatalog(), null, null, null);
            while (resultSet.next()) {
                tableNameList.add(resultSet.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNameList;
    }

    public static List<String> getTableNameListInUpperCamelCase(Connection connection) {
        List<String> tableNameList = getTableNameList(connection);
        return tableNameList.stream().map(StringUtil::upperUnderScoreToUpperCamel).collect(Collectors.toList());
    }

    public static List<String> getTableNameListInLowerCamelCase(Connection connection) {
        List<String> tableNameList = getTableNameList(connection);
        return tableNameList.stream().map(StringUtil::upperUnderScoreToLowerCamel).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Connection connection = getCjConnection("jdbc:mysql://192.168.1.129:3306/aic?characterEncoding=UTF8", "root", "!@#tqhy.0817");
        List<String> tableNameList = getTableNameListInLowerCamelCase(connection);
        System.out.println(tableNameList);
    }

    public static List<String> getColumnNameList(Connection connection, String tableName) {
        DatabaseMetaData dataBaseMetaData = getDataBaseMetaData(connection);
        List<String> columnNameList = new ArrayList<>();
        try {
            ResultSet resultSet = dataBaseMetaData.getColumns(null, null, tableName, null);
            while (resultSet.next()) {
                columnNameList.add(resultSet.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNameList;
    }
}
