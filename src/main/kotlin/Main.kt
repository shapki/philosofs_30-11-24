/* За круглым столом сидит некоторое количество философов (задается при запуске программы).
Между ними лежат вилки. По команде каждый из философов пытается взять случайным образом вилку или справа, или слева от него.
Если какая-либо вилка будет занята, то философ пытается взять вилку с другой стороны. Философы берут вилки последовательно.
То есть сперва один выбирает вилку, потом другой и т.д. Кто из философов начнет выбирать вилку определяется случайным образом.
Философ, который уже взял вилку, не участвует в дальнейших выборах.
После того, как все философы попытаются взять вилку необходимо вывести на консоль информацию о том, кто обедает, а кто размышляет (ему не досталась вилка).
Как вы понимаете, философу может не достаться вилка в том случае, если сидящий справа от него возьмет вилку слева, а сидящий слева - возьмет вилку справа.
Но может и случиться такая ситуация, что все философы будут обедать
 */

import kotlin.random.Random
// Fork (с англ) - Вилка
fun main() {
    print("Количестфо философов: ")
    val numPhilos = readLine()?.toInt() ?: 5

    if (numPhilos < 2) {
        println("Нужно минимум 2 философа.")
        return
    }

    val philos = Array(numPhilos) { Philosopher(it) }
    val forks = BooleanArray(numPhilos) { true }

    var curPhilos = Random.nextInt(numPhilos)

    for (i in 0 until numPhilos) {
        val philos = philos[curPhilos]
        if (!philos.hasFork) {
            val chooseLeft = Random.nextBoolean()
            val leftForkId = curPhilos
            val rightForkId = (curPhilos + 1) % numPhilos

            val forkId = if (chooseLeft) leftForkId else rightForkId

            if (forks[forkId]) {
                forks[forkId] = false
                philos.hasFork = true
            }
        }
        curPhilos = (curPhilos + 1) % numPhilos
    }

    println("Обедающие философы:")
    for (philos in philos) {
        if (philos.hasFork) {
            println("Философ ${philos.id+1} обедает.")
        } else {
            println("Философ ${philos.id+1} думает.")
        }
    }
}