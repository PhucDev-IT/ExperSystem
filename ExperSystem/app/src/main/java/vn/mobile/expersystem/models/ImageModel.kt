package vn.mobile.expersystem.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Image",
    foreignKeys = [
        ForeignKey(
            entity = TapLuat::class,
            parentColumns = ["id"],
            childColumns = ["tap_luat_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["tap_luat_id"])]
)
data class ImageModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "tap_luat_id")
    val tapLuatId: Int,
    @ColumnInfo(name = "hinh_anh_xe")
    val hinhAnhXe: String
)
