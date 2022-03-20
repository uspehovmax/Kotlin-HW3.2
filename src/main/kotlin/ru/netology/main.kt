package ru.netology

const val previousPayMaestro = 75_000_00
const val previousPayVisa = 100_000_00
const val previousPayVKPay = 5_000_00
const val previousPayMir = 30_000_00

const val currentPayMaestro = 2000_00
const val currentPayVisa = 5_000_00
const val currentPayVKPay = 10_000_00
const val currentPayMir = 10_000_00

const val maxTotalPayMaestro = 75000_00
const val commissionMaestro = 0.006
const val minCommissionMaestro = 20_00

const val minCommissionVisa = 35_00
const val commissionVisa = 0.0075
var commission = 0

fun main() {
    commissionCalculation("Maestro", previousPayMaestro, currentPayMaestro)  // Maestro/Mastercard
    commissionCalculation("Visa", previousPayVisa, currentPayVisa)     // Visa
    commissionCalculation("VKPay", previousPayVKPay, currentPayVKPay)    // VKPay
    commissionCalculation("МИР", previousPayMir, currentPayMir)      // МИР

}

fun commissionCalculation(payMethodType: String = "VKPay", previousPay: Int = 0, currentPay: Int) {
    commission = 0
    commission = when (payMethodType) {
        "Maestro" -> commissionMaestro(previousPay, currentPay)
        "Mastercard" -> commissionMaestro(previousPay, currentPay)
        "Visa" -> commissionVisa(currentPay)
        "МИР" -> commissionVisa(currentPay)
        else -> {
            0
        }
    }
    printResult(currentPay, commission, payMethodType)
}

fun commissionMaestro(previousPay: Int = 0, currentPay: Int): Int {
    commission = if ((previousPay + currentPay) > maxTotalPayMaestro)
        (currentPay * commissionMaestro).toInt() + minCommissionMaestro
    else 0
    return commission
}

fun commissionVisa(currentPay: Int): Int {
    commission = if ((currentPay * commissionVisa).toInt() > minCommissionVisa)
        (currentPay * commissionVisa).toInt()
    else minCommissionVisa
    return commission
}

fun printResult(currentPay: Int, commission: Int, payMethodType: String) {
    println("Тип оплаты: $payMethodType")
    println("Сумма покупки: ${currentPay / 100} руб. ${currentPay % 100} коп. ")
    println("Комиссия: ${commission / 100} руб. ${commission % 100} коп. ")
    println("К ОПЛАТЕ: ${(currentPay + commission) / 100} руб. ${(currentPay + commission) % 100} коп.")
    println("---------------------------------------------------")
}