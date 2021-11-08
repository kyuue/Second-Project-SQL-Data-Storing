package com.example.myfirstnativeandroidapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MedeniyetDbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE Ders_odev (
                'key' varchar(255) UNIQUE,
                'value' varchar(255)
                );
                """
        )

        db.execSQL("INSERT INTO Ders_odev(key, value) VALUES('bil359', 'Hello World from database')")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over

        db.execSQL("DROP TABLE Ders_odev;")

        onCreate(db)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Medeniyet.db"
    }
}