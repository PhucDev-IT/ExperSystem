package vn.mobile.expersystem.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class TapLuat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "ma_luat")
    val maLuat: String,
    @ColumnInfo(name = "su_kien_id", index = true)
    val suKienId: String,
    @ColumnInfo(name = "ket_luan")
    val ketLuan: String
)
