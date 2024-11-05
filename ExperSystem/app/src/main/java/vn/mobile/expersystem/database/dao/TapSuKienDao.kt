package vn.mobile.expersystem.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import vn.mobile.expersystem.models.Nhom
import vn.mobile.expersystem.models.TapSuKien

@Dao
interface TapSuKienDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTapSuKien(tapSuKien: TapSuKien)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tapSuKien: List<TapSuKien>)

    @Update
    suspend fun update(tapSuKien: TapSuKien)

    @Query("SELECT * FROM TapSuKien")
    fun getAllSuKien(): List<TapSuKien>
}
