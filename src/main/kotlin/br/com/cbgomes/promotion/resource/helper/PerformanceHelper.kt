package br.com.cbgomes.promotion.resource.helper

inline fun <R> executeAndMeasureTimeMillis(block: ()-> R): Pair<R, Long>{
    val start: Long = System.currentTimeMillis()
    val result: R = block()

    return result to (System.currentTimeMillis() - start)
}