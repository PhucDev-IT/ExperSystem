package vn.mobile.expersystem.common

import vn.mobile.expersystem.models.ImageModel
import vn.mobile.expersystem.models.Nhom
import vn.mobile.expersystem.models.TapLuat
import vn.mobile.expersystem.models.TapSuKien

object DataManager {
    var groups = listOf<Nhom>()
    var groupEvents = listOf<TapSuKien>()
    var groupRules = mutableListOf<TapLuat>()
    var images = mutableListOf<ImageModel>()
}