package com.ahmedaziz.coofde.DataBase;


import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by Ahmed Aziz on 9/3/2017.
 */

public interface ColumnsDB {

        @DataType(DataType.Type.INTEGER) @PrimaryKey
        @AutoIncrement
        public static final String _ID = "_id";
        @DataType(DataType.Type.TEXT)
        public static final String CODE = "code";
        @DataType(DataType.Type.INTEGER) @NotNull
        public static final String COUPON = "coupon";
        @DataType(DataType.Type.TEXT) @NotNull
        public static final String DESC = "desc";
        @DataType(DataType.Type.TEXT) @NotNull
        public static final String TITLE = "title";
        @DataType(DataType.Type.TEXT) @NotNull
        public static final String TYPE = "type";
        @DataType(DataType.Type.INTEGER) @NotNull
        public static final String VIEWS = "views";
        @DataType(DataType.Type.TEXT) @NotNull
        public static final String COOFDE_ID = "coofde_id";
        @DataType(DataType.Type.TEXT)
        public static final String URL = "url";
        @DataType(DataType.Type.TEXT)
        public static final String STORE = "store";

    }




