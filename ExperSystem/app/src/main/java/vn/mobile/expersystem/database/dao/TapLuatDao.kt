package vn.mobile.expersystem.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import vn.mobile.expersystem.models.Nhom
import vn.mobile.expersystem.models.TapLuat
import vn.mobile.expersystem.models.TapLuatWithImages

@Dao
interface TapLuatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTapLuat(tapLuat: TapLuat)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(luats: List<TapLuat>)

    @Query("SELECT * FROM TapLuat WHERE su_kien_id = :suKienId LIMIT 1")
    fun getLuatBySuKienId(suKienId: String): TapLuat

    @Transaction
    @Query("SELECT * FROM TapLuat")
    suspend fun getTapLuatWithImages(): List<TapLuatWithImages>


    @Query("SELECT * FROM TapLuat")
    fun getAll(): List<TapLuat>
}
