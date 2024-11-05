package vn.mobile.expersystem.models

import androidx.room.Embedded
import androidx.room.Relation

data class TapLuatWithImages(
    @Embedded val tapLuat: TapLuat,
    @Relation(
        parentColumn = "id",
        entityColumn = "tap_luat_id"
    )
    val images: List<ImageModel>
)
