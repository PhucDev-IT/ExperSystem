package vn.mobile.expersystem.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import vn.mobile.expersystem.models.Nhom

@Dao
interface NhomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nhom: Nhom)

    @Delete
    suspend fun delete(nhom: Nhom)

    @Update
    suspend fun update(nhom: Nhom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nhoms: List<Nhom>)

    @Query("SELECT * FROM Nhom")
    fun getAll(): List<Nhom>
}
