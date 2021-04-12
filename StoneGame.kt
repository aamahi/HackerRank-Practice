import java.io.*
import java.math.*
import java.text.*
import java.util.*
import java.util.regex.*

val mod = 1000000007

fun maxbit(value: Int): Int = if(value <= 1) value else maxbit(value shr 1) shl 1

fun Int.moduloPow(p: Int, modulo: Int): Long {
    if(p==0)
        return 1
    if(p==1)
        return this.toLong()
    val halfn = this.moduloPow(p/2, modulo)
    return if(p%2==0)
        ( halfn * halfn ) % modulo
    else
        ( ( ( halfn * halfn ) % modulo ) * this ) % modulo
}

fun solution(values: Array<Int>): Int {
    val m = maxbit(values.max() ?: 0)
    if(m == 0)
        return 1
    val ans = arrayOf(1L, 0L)
    var t = 0
    var (p0, p1) = arrayOf(1L, 1L)

    for(value in values) {
        if (value and m != 0) {
            t = t xor 1
            val (a0, a1) = ans
            ans[0] = (a0 * (value - m + 1) + a1 * m) % mod
            ans[1] = (a1 * (value - m + 1) + a0 * m) % mod
            p1 = p1 * (value - m + 1) % mod
        } else {
            p0 = p0 * (value + 1) % mod
        }
    }
    ans[0] = (ans[0] - p1) % mod
    var res = BigInteger(ans[t].toString())
        .multiply(BigInteger(m.moduloPow(mod-2, mod).toString()))
        .multiply(BigInteger(p0.toString()))
    if(t == 0)
         res = res.add(BigInteger(solution(values.map {
            it and m.inv()
        }.toTypedArray()).toString()))

    return res.mod(BigInteger(mod.toString())).toInt()
}

/*
 * Complete the stoneGame function below.
 */
fun stoneGame(p: Array<Int>) = ((((solution(p) - solution(p.map {
    it-1
}.toTypedArray())) % mod) + mod) % mod)

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val pCount = scan.nextLine().trim().toInt()

    val p = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    val result = stoneGame(p)

    println(result)
}