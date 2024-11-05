package vn.mobile.expersystem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import vn.mobile.expersystem.models.ImageModel
import vn.mobile.expersystem.models.Nhom

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<ImageModel>)

    @Query("""
        SELECT Image.* FROM Image
        WHERE tap_luat_id = :tapLuatId
    """)
    suspend fun getAllImagesForTapLuat(tapLuatId: Int): List<ImageModel>
}