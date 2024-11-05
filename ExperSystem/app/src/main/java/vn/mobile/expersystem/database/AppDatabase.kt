package vn.mobile.expersystem.database

import android.media.Image
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vn.mobile.expersystem.MyApplication
import vn.mobile.expersystem.database.core.DataSample
import vn.mobile.expersystem.database.dao.ImageDao
import vn.mobile.expersystem.database.dao.NhomDao
import vn.mobile.expersystem.database.dao.TapLuatDao
import vn.mobile.expersystem.database.dao.TapSuKienDao
import vn.mobile.expersystem.models.ImageModel
import vn.mobile.expersystem.models.Nhom
import vn.mobile.expersystem.models.TapLuat
import vn.mobile.expersystem.models.TapSuKien

@Database(entities = [Nhom::class, TapLuat::class, TapSuKien::class, ImageModel::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun nhomDao(): NhomDao
    abstract fun tapLuatDao(): TapLuatDao
    abstract fun tapSuKienDao(): TapSuKienDao
    abstract fun imageDao(): ImageDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val DATABASE_NAME: String = "APP_DATABASE.db"
        val APPDATABASE = getInstance()
        private fun getInstance(): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    MyApplication.instance.context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            db.execSQL("PRAGMA foreign_keys=ON;") // Enable foreign key constraints
                        }
                    })
                    .build().also { instance = it }
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Thêm dữ liệu mẫu ở đây
            CoroutineScope(Dispatchers.IO).launch {
                instance?.let { database ->
                    database.nhomDao().insertAll(DataSample.groups.toList())
                    database.tapSuKienDao().insertAll(DataSample.groupEvents.toList())

                }
            }
        }
    }
}
