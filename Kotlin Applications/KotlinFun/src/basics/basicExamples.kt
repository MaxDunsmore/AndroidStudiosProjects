package basics

fun main(args: Array<String>) {
    //basics.test1(17)
    //basics.test2("kotlins2")
    //basics.test3(24)
    //basics.listExample()
    //basics.forLoopExamples()
    //basics.whileLoop()
    //basics.testFunctions()


}

private fun testFunctions() {
    val testFunction = functionExample(18)
    println(testFunction)
    val testMultipleFunction = functionMultipleExample(4, 6, 18, 39)
    println(testMultipleFunction)
}

fun functionMultipleExample(vararg ages:Int):Boolean {
    return ages.any{age -> age >=18}
}

fun functionExample(age:Int):Boolean = age >= 18


private fun whileLoop() {
    var i = 1
    while (i <= 10) {
        println("$i ")
        i++
    }

    do {
        println(i)
        i--
    } while (i > 0)
}

private fun forLoopExamples() {
    for (i in 1..10) {
        print("$i, ")
    }
    println()
    for (c in "Kotlin") {
        print("$c ")
    }
    println()
    for (i in 10 downTo 1) {
        print("$i ")
    }
}

private fun listExample() {
    val array = arrayOf(2, 3, 5, 7, 11, 13)
    val mutableList = mutableListOf(1, 2, 3, 4, 5)
    println(array.joinToString())

    val map = mapOf(Pair(1, "kotlin"), Pair(2, "Android"))
    val mapMutable = mutableMapOf(1 to "kotlin", 2 to "Android")
    println(map.get(1))
}

private fun test3(price:Int){
    when(price){
        0 -> println("for free today")
        !in 1..19 -> println("Not On sale")
        10+20 -> println("Costs 30")
        else -> println("OverPriced")
    }
}

private fun test2(str:String) {
    if (str != null) {
        println(str.length)
    }
}

class test1(val i: Int) {
    val x = if (i < 15) {
        println("it is pretty small")
        "small"
    } else if (i >= 15 && i <= 25) {
        println("its okay")
        "medium"
    } else {
        println("its pretty large")
        "large"
    }
}
