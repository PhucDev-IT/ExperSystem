package vn.mobile.expersystem.utils

object Util {

    // Hàm extension để lấy tất cả các tổ hợp của một kích thước xác định
    fun <T> Set<T>.combinations(k: Int): Set<Set<T>> {
        if (k == 0) return setOf(emptySet())
        if (this.isEmpty()) return emptySet()

        val element = this.first()
        val remaining = this - element

        val withoutElement = remaining.combinations(k)
        val withElement = remaining.combinations(k - 1).map { it + element }

        return withElement.toSet() + withoutElement
    }
}