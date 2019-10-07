package com.enuke.unicon.Class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MCKINLEYRICE_UNICON";
    public static final String USER_TABLE = "UserInfo";
    public static final String CLIENT_CONTEST_TABLE = "ClientContestTable";


    public DataBaseClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + USER_TABLE + "(" +
                "email TEXT PRIMARY KEY," +
                "password TEXT," +
                "businessRegistrationCertificate TEXT," +
                "name TEXT," +
                "nickName TEXT," +
                "role TEXT," +
                "phoneNumber TEXT," +
                "companyName TEXT," +
                "businessRegistrationNumber TEXT," +
                "representativeName TEXT" +
                ");");

        db.execSQL("CREATE TABLE " + CLIENT_CONTEST_TABLE + "(" +
                "userId TEXT PRIMARY KEY," +
                "prizeAmount INTEGER," +
                "contestFirstPlaceAmount INTEGER," +
                "contestSecondPlaceAmount INTEGER," +
                "contestThirdPlaceAmount TEXT," +
                "contestPlatformFee INTEGER," +
                "contestSum INTEGER," +
                "contestContactName TEXT," +
                "contestContactNo INTEGER," +
                "contestEventPeriodFrom TEXT," +
                "contestEventPeriodTo TEXT," +
                "contestExaminationPeriodFrom TEXT," +
                "contestExaminationPeriodTo TEXT," +
                "contestPublicationDate TEXT," +
                "contestDetail TEXT," +
                "contestContent TEXT," +
                "guideVideoFile TEXT," +
                "guideImage1File TEXT," +
                "guideImage2File TEXT," +
                "guideImage3File TEXT" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CLIENT_CONTEST_TABLE);
    }
}