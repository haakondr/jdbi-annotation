package me.shakiba.jdbi.annotation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

abstract class AnnoType {
    public static AnnoType String = new AnnoType() {
        @Override
        public boolean isAssignableFrom(Class<?> clazz) {
            return String.class.isAssignableFrom(clazz);
        }

        @Override
        public Object getValue(ResultSet rs, String name) throws SQLException {
            return rs.getString(name);
        }
    };
    public static AnnoType Long = new AnnoType() {
        @Override
        public boolean isAssignableFrom(Class<?> clazz) {
            return Long.class.isAssignableFrom(clazz)
                    || long.class.isAssignableFrom(clazz);
        }

        @Override
        public Object getValue(ResultSet rs, String name) throws SQLException {
            return rs.getLong(name);
        }
    };
    public static AnnoType Int = new AnnoType() {
        @Override
        public boolean isAssignableFrom(Class<?> clazz) {
            return Integer.class.isAssignableFrom(clazz)
                    || int.class.isAssignableFrom(clazz);
        }

        @Override
        public Object getValue(ResultSet rs, String name) throws SQLException {
            return rs.getInt(name);
        }
    };
    public static AnnoType Double = new AnnoType() {
        @Override
        public boolean isAssignableFrom(Class<?> clazz) {
            return Double.class.isAssignableFrom(clazz)
                    || double.class.isAssignableFrom(clazz);
        }

        @Override
        public Object getValue(ResultSet rs, String name) throws SQLException {
            return rs.getDouble(name);
        }
    };
    public static AnnoType Float = new AnnoType() {
        @Override
        public boolean isAssignableFrom(Class<?> clazz) {
            return Float.class.isAssignableFrom(clazz)
                    || float.class.isAssignableFrom(clazz);
        }

        @Override
        public Object getValue(ResultSet rs, String name) throws SQLException {
            return rs.getFloat(name);
        }
    };
    public static AnnoType Boolean = new AnnoType() {
        @Override
        public boolean isAssignableFrom(Class<?> clazz) {
            return Boolean.class.isAssignableFrom(clazz)
                    || boolean.class.isAssignableFrom(clazz);
        }

        @Override
        public Object getValue(ResultSet rs, String name) throws SQLException {
            return rs.getBoolean(name);
        }
    };
    public static AnnoType Date = new AnnoType() {
        @Override
        public boolean isAssignableFrom(Class<?> clazz) {
            return Date.class.isAssignableFrom(clazz);
        }

        @Override
        public Object getValue(ResultSet rs, String name) throws SQLException {
            return rs.getDate(name).toLocalDate();
        }
    };

    public static AnnoType Timestamp = new AnnoType() {
        @Override
        public boolean isAssignableFrom(Class<?> type) {
            return LocalDateTime.class.isAssignableFrom(type);
        }

        @Override
        public Object getValue(ResultSet rs, String name) throws SQLException {
            return rs.getTimestamp(name).toLocalDateTime();
        }
    };

    public static AnnoType[] primitives = { String, Long, Int, Double, Float,
            Boolean, Date, Timestamp };

    public static AnnoType of(Class<?> clazz) throws IllegalArgumentException {
        for (AnnoType annoType : AnnoType.primitives) {
            if (annoType.isAssignableFrom(clazz)) {
                return annoType;
            }
        }
        throw new IllegalArgumentException();
    }

    public abstract boolean isAssignableFrom(Class<?> type);

    public abstract Object getValue(ResultSet rs, String name)
            throws SQLException;

}