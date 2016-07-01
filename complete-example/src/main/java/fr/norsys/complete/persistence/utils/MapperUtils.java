package fr.norsys.complete.persistence.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.support.JdbcUtils;

public class MapperUtils {

    public static <T> T getResultSetValue(final ResultSet rs, final int index, final Class<T> requiredType)
            throws SQLException {
        return (T) JdbcUtils.getResultSetValue(rs, index, requiredType);
    }

    public static <T> T getResultSetValue(final ResultSet rs, final String columnName, final Class<T> requiredType)
            throws SQLException {
        return getResultSetValue(rs, rs.findColumn(columnName), requiredType);
    }

    // get Strings
    public static String getString(final ResultSet rs, final String columnName) throws SQLException {
        return getResultSetValue(rs, columnName, String.class);
    }

    public static String getString(final ResultSet rs, final int columnIndex) throws SQLException {
        return getResultSetValue(rs, columnIndex, String.class);
    }

    // get Integer
    public static Integer getInteger(final ResultSet rs, final String columnName) throws SQLException {
        return getResultSetValue(rs, columnName, Integer.class);
    }

    public static Integer getInteger(final ResultSet rs, final int columnIndex) throws SQLException {
        return getResultSetValue(rs, columnIndex, Integer.class);
    }

    // get Long
    public static Long getLong(final ResultSet rs, final String columnName) throws SQLException {
        return getResultSetValue(rs, columnName, Long.class);
    }

    public static Long getLong(final ResultSet rs, final int columnIndex) throws SQLException {
        return getResultSetValue(rs, columnIndex, Long.class);
    }

    // get Date
    public static Date getDate(final ResultSet rs, final String columnName) throws SQLException {
        return getResultSetValue(rs, columnName, Date.class);
    }

    public static Date getDate(final ResultSet rs, final int columnIndex) throws SQLException {
        return getResultSetValue(rs, columnIndex, Date.class);
    }

    // enums
    public static <T extends Enum<T>> T getEnum(final ResultSet rs, final String columnName, final Class<T> enumClass) throws SQLException {
        return getResultSetValue(rs, rs.findColumn(columnName), enumClass);
    }

    public static <T extends Enum<T>> T getEnum(final ResultSet rs, final int columnIndex, final Class<T> enumClass) throws SQLException {
        T[] enums = enumClass.getEnumConstants();

        String value = getString(rs, columnIndex);
        for (T currEnum : enums){
            if (currEnum.name().equals(value)){
                return currEnum;
            }
        }
        return null;
    }

}
