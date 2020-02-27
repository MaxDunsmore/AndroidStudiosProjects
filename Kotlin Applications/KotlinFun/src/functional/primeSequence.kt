package functional

fun main() {
    val possiblePrimesAfter2 = generateSequence(3){it+2}
    val primes = generateSequence(2 to possiblePrimesAfter2){
        // Next prime number
        val p = it.second.first()

            // filter out all elements divisible by p
        val possiblePrimesAfterP = it.second.filter { it % p != 0 }

        // return the next element in the sequence
        p to possiblePrimesAfterP

    }.map { it.first }
    println(primes.take(10).toList())
}