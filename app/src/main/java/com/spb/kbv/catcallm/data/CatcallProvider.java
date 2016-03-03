package com.spb.kbv.catcallm.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class CatcallProvider extends ContentProvider{

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final int COMPANIES = 100;
    private static final int MESSAGE = 200;
    private static final int COMPANIES_WITH_OPEN_DIALOGS = 300;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MessagesContract.CONTENT_AUTHORITY;

        uriMatcher.addURI(authority, MessagesContract.PATH_COMPANY, COMPANIES);
        uriMatcher.addURI(authority, MessagesContract.PATH_MESSAGE, MESSAGE);
        uriMatcher.addURI(authority, MessagesContract.PATH_COMPANY_WITH_DIALOGS, COMPANIES_WITH_OPEN_DIALOGS);

        return uriMatcher;
    }

    private CatcallDbHelper mOpenHelper;


    private static final SQLiteQueryBuilder sMessageByCompanyBuilder;

    static {
        sMessageByCompanyBuilder = new SQLiteQueryBuilder();
        sMessageByCompanyBuilder.setTables(
                MessagesContract.MessagesEntry.TABLE_NAME + " INNER JOIN " +
                        MessagesContract.CompaniesEntry.TABLE_NAME +
                        " ON " + MessagesContract.MessagesEntry.TABLE_NAME +
                        "." + MessagesContract.MessagesEntry.COLUMN_COMP_KEY +
                        " = " + MessagesContract.CompaniesEntry.TABLE_NAME +
                        "." + MessagesContract.CompaniesEntry._ID);
    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new CatcallDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor reqCursor;
        switch (sUriMatcher.match(uri)){
            case COMPANIES: {
                reqCursor = mOpenHelper.getReadableDatabase().query(
                        MessagesContract.CompaniesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case MESSAGE: {
                reqCursor = mOpenHelper.getReadableDatabase().query(
                        MessagesContract.MessagesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case COMPANIES_WITH_OPEN_DIALOGS: {
                reqCursor = mOpenHelper.getReadableDatabase().query(
                        MessagesContract.MessagesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        MessagesContract.MessagesEntry.COLUMN_COMP_KEY,
                        null,
                        sortOrder
                );
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        reqCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return reqCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case COMPANIES:
                return MessagesContract.CompaniesEntry.CONTENT_TYPE;

            case MESSAGE:
                return  MessagesContract.MessagesEntry.CONTENT_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case COMPANIES: {
                long _id = db.insert(MessagesContract.CompaniesEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MessagesContract.CompaniesEntry.buildCompanyUri(_id);
                else
                    throw new SQLException("Faild to insert row into " + uri);
                break;
            }

            case MESSAGE: {
                long _id = db.insert(MessagesContract.MessagesEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MessagesContract.MessagesEntry.buildMessageUri(_id);
                else
                    throw new SQLException("Faild to insert row into " + uri);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
