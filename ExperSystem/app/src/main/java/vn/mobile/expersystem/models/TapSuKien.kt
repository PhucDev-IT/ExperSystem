package vn.mobile.expersystem.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "TapSuKien",
    primaryKeys = ["su_kien_id", "nhom_id"],
    foreignKeys = [
        ForeignKey(
            entity = Nhom::class,
            parentColumns = ["id"],
            childColumns = ["nhom_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["su_kien_id"], unique = true)] // Thêm chỉ mục duy nhất cho su_kien_id
)
data class TapSuKien(
    @ColumnInfo(name = "su_kien_id")
    val suKienId: String,
    @ColumnInfo(name = "nhom_id")
    val nhomId: String,
    val description:String
)
