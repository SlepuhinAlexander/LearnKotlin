package ch02.par3.color

// можно импортировать значения констант чтобы не прописывать их полное имя

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    fun rgb() = "#${toHex(r)}${toHex(g)}${toHex(b)}"

    private fun fill(s: String) = if (s.length < 2) "0".repeat(2 - s.length) + s else s

    private fun toHex(n: Int) = fill(n.toString(16).toUpperCase())
}

