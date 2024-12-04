package vn.mobile.expersystem.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
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

    @Delete
    suspend fun delete(tapLuat: TapLuat)

    @Query("SELECT * FROM TapLuat")
    fun getAll(): List<TapLuat>

    @Query("SELECT id FROM TapLuat ORDER BY id DESC LIMIT 1")
    fun getLatestId(): Int
    @Update
    fun update(tapLuat: TapLuat)

    @Query("""
    SELECT * FROM TapLuat 
    WHERE su_kien_id LIKE '%' || :conditionA || '%' 
    OR su_kien_id LIKE '%' || :conditionB || '%' LIMIT 10
""")
    fun selectMoreLike(conditionA: String, conditionB: String): List<TapLuat>

}
