package net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.floatingpoint

import java.text.DecimalFormat


class FloatEditor(val value: Float) {
  companion object {
    val mantissaMask = 0b00000000_01111111_11111111_11111111
    val exponentMask = 0b01111111_10000000_00000000_00000000
    val signMask = 1 shl 31

    val mantissaMax = 0b00000000_01111111_11111111_11111111
    val exponentMax = 0b01111111_1
  }

  fun setRawMantissa(mantissaToExtract: Float): FloatEditor {
    return FloatEditor(
      Float.fromBits(
        // extract non mantissa part
        value.toRawBits() and (signMask or exponentMask) or
            // inject new mantissa
            (mantissaToExtract.toRawBits() and mantissaMask)
      )
    )
  }

  fun setMantissa(mantissa: Int): FloatEditor {
    if (mantissa < 0 || mantissa > 0x7fffff) return this

    return FloatEditor(
      Float.fromBits(
        // extract non mantissa part
        value.toRawBits() and (signMask or exponentMask) or
            // inject new mantissa
            (mantissa and mantissaMask)
      )
    )
  }

  val exponentDec: String? by lazy {
    if (value.isNaN() || value == Float.POSITIVE_INFINITY || value == Float.NEGATIVE_INFINITY) {
      null
    } else {
      DecimalFormat("0.0000000E0")
        .format(value)
        .dropWhile { it != 'E' }
        .drop(1)
    }
  }

  val exponentAsInt by lazy {
    Integer.parseInt(exponentBits, 2)
  }

  val mantissaDec: String? by lazy {
    if (value.isNaN() || value == Float.POSITIVE_INFINITY || value == Float.NEGATIVE_INFINITY) {
      null
    } else {
      DecimalFormat("0.0000000E0")
        .format(value)
        .dropWhile { it == '-' }
        .dropLastWhile { it != 'E' }
        .dropLast(1)
    }
  }

  val mantissaAsInt:Int by lazy {
    Integer.parseInt(mantissaBits, 2)
  }

  val mantissaBits: String
    get() = Integer.toBinaryString(value.toRawBits() and mantissaMask).padStart(23, '0')

  val exponentBits: String
    get() = Integer.toBinaryString((value.toRawBits() and exponentMask) shr 23).padStart(8, '0')

  val signBit: String
    get() = Integer.toBinaryString(value.toRawBits()).padStart(32, '0').first().toString()

  fun setSignPositive(positive: Boolean): FloatEditor {
    return FloatEditor(
      Float.fromBits(
        value.toRawBits() shl 1 ushr 1 or ((if (positive) 0 else 1) shl 31)
      )
    )
  }

  fun setPositive() = setSignPositive(true)

  fun setNegative() = setSignPositive(false)

  fun setExponent(exponent: Int): FloatEditor {
    if (exponent !in 0 .. 255) return this

    val raw = exponent shl 23

    return FloatEditor(
      Float.fromBits(
        (value.toRawBits() and (signMask or mantissaMask)) or
            (raw and exponentMask)
      )
    )
  }

}
