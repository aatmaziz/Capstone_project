package com.ahmedaziz.coofde.DataBase;

import android.net.Uri;


import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

import java.sql.DatabaseMetaData;

import static android.R.attr.path;

/**
 * Created by Ahmed Aziz on 9/3/2017.
 */

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = Provider.AUTHORITY, database = Database.class)
public final class Provider {
    public static final String AUTHORITY = "com.akhooo.coofde.data.CoofdeProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String COOFDE = "coofde";
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = DataBasa.COOFDE)
    public static class Coofde {
        @ContentUri(path = Path.COOFDE,
                type = "vnd.android.cursor.dir/coofde",
                defaultSort = ColumnsDB._ID + " ASC"
        )
        public static final Uri CONTENT_URI = buildUri(Path.COOFDE);

        @InexactContentUri(name = "COOFDE_ID",
                path = Path.COOFDE + "/#",
                type = "vnd.android.cursor.item/coofde",
                whereColumn = ColumnsDB._ID,
                pathSegment = 1

        )
        public static Uri withId(long id) {
            return buildUri(Path.COOFDE, String.valueOf(id));
        }
    }

}
