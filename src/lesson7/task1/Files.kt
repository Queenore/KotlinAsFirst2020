@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import lesson3.task1.digitNumber
import lesson3.task1.pow
import lesson3.task1.revert
import lesson5.task1.init
import java.io.File

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    var stringCpy: String
    var char: Char
    for (string in substrings)
        result[string] = 0
    for (word in substrings.toSet()) {
        File(inputName).bufferedReader().use { reader ->
            for (string in reader.readLines()) {
                stringCpy = string
                var count = ("""\$word""").toLowerCase().toRegex().findAll(stringCpy.toLowerCase()).toList()
                while (count.isNotEmpty()) {
                    char = stringCpy[count[0].range.first].toLowerCase()
                    stringCpy = stringCpy.replaceRange(count[0].range.first..count[0].range.first, "")
                    if (count[0].range.first == 0 || char != stringCpy[count[0].range.first - 1].toLowerCase())
                        result[word] = result.getOrDefault(word, 0) + 1
                    count = ("""\$word""").toLowerCase().toRegex().findAll(stringCpy.toLowerCase()).toList()
//                    count = init("""\$word""", RegexOption.IGNORE_CASE).findAll(stringCpy.toLowerCase()).toList()
                }
            }
        }
    }
    return result
}

/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между словами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    File(inputName).bufferedReader().use { reader ->
        var writer = File(outputName).bufferedWriter()
        var length = 0
        for (string in reader.readLines()) {
            if (string.toLowerCase().toSet().size == string.length) {
                if (string.length > length) {
                    writer.close()
                    writer = File(outputName).bufferedWriter()
                    writer.write(string)
                    length = string.length
                } else if (string.length == length) writer.write(", $string")
            }
        }
        writer.close()
    }
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun takeDigit(number: Int, position: Int): Int {
    var result = 0
    var i = 0
    var numberCpy = number
    while (i != position) {
        i++
        result = numberCpy % 10
        numberCpy /= 10
    }
    return result
}

fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    File(outputName).bufferedWriter().use { out ->
        val whiteSpace = StringBuilder()
        val secondStringWhiteSpace = StringBuilder()
        val dash = StringBuilder()
        val firstStringLength = 1 + digitNumber(rhv * lhv)

        for (i in 1..firstStringLength - digitNumber(lhv)) whiteSpace.append(" ")
        for (i in 1 until firstStringLength - digitNumber(rhv)) secondStringWhiteSpace.append(" ")
        for (i in 1..firstStringLength) dash.append("-")

        out.write("$whiteSpace$lhv\n*$secondStringWhiteSpace$rhv\n$dash\n")
        whiteSpace.clear()
        for (i in 1..firstStringLength - digitNumber(takeDigit(rhv, 1) * lhv)) whiteSpace.append(" ")
        out.write("$whiteSpace${takeDigit(rhv, 1) * lhv}\n")

        for (i in 1 until digitNumber(rhv)) {
            whiteSpace.clear()
            for (j in 1 until firstStringLength - digitNumber(takeDigit(rhv, i + 1) * lhv) - i) whiteSpace.append(" ")
            out.write("+$whiteSpace${takeDigit(rhv, i + 1) * lhv}\n")
        }

        out.write("$dash\n ${lhv * rhv}")
    }
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {

    fun digitInPosition(number: Int, position: Int): Int {
        var result = 0
        var i = 0
        var revertNumber = revert(number)
        while (i != position) {
            i++
            result = revertNumber % 10
            revertNumber /= 10
        }
        return result
    }

    fun currentNumber(lhv: Int, rhv: Int): Int {
        if (lhv < rhv) return 0
        var i = 0
        var currentNumber = 0
        while (currentNumber / rhv == 0) {
            currentNumber = currentNumber * 10 + digitInPosition(lhv, i + 1)
            i++
        }
        return currentNumber
    }

    fun newNumber(remains: Int, digit: Int) = remains * 10 + digit

    fun subtrahend(rhv: Int, current: Int) = current / rhv * rhv

    File(outputName).bufferedWriter().use { out ->
        val secondStringWhiteSpace = StringBuilder()
        val dash = StringBuilder()
        val whiteSpaceForSubtrahend = StringBuilder()
        val whiteSpaceForNewNumber = StringBuilder(" ")
        var remains = 0
        var newNumber = 0
        var residualNumber = 0
        var currentNumberOfDigit = 0
        var remainsCpy = -1
        val firstStringLength = 4 + digitNumber(lhv) + digitNumber(rhv)

        for (i in 1..firstStringLength - 1 - digitNumber(rhv) - digitNumber(currentNumber(lhv, rhv)))
            secondStringWhiteSpace.append(" ")
        out.write(" $lhv | $rhv\n")
        var subtrahend = subtrahend(rhv, currentNumber(lhv, rhv))
        for (i in 1..digitNumber(subtrahend) + 1) dash.append("-")
        out.write("-$subtrahend$secondStringWhiteSpace${lhv / rhv}\n")
        out.write("$dash\n")

        for (i in 1..digitNumber(lhv) - digitNumber(currentNumber(lhv, rhv))) {
            if (i == 1) {
                remains = currentNumber(lhv, rhv) - subtrahend(rhv, currentNumber(lhv, rhv))
                residualNumber = kotlin.math.abs(
                    currentNumber(lhv, rhv) * pow(
                        10,
                        digitNumber(lhv) - digitNumber(currentNumber(lhv, rhv))
                    ) - lhv
                )
                for (j in 1..digitNumber(subtrahend) - digitNumber(remains)) whiteSpaceForNewNumber.append(" ")
                currentNumberOfDigit = digitNumber(residualNumber)
            } else {
                remains = newNumber - subtrahend
                currentNumberOfDigit--
            }

            if (remainsCpy != remains && remainsCpy == 0) whiteSpaceForNewNumber.append(" ")
            newNumber = newNumber(remains, takeDigit(residualNumber, currentNumberOfDigit))
            if (i != 1) {
                for (j in 1..digitNumber(subtrahend) - digitNumber(remains)) whiteSpaceForNewNumber.append(" ")
                if (remains == 0 && remainsCpy == 0 && newNumber != 0)
                    whiteSpaceForNewNumber.append(" ")
            }
            if (remainsCpy == newNumber && remainsCpy == 0) whiteSpaceForNewNumber.append(" ")
            subtrahend = subtrahend(rhv, newNumber)

            whiteSpaceForSubtrahend.clear()
            whiteSpaceForSubtrahend.append(whiteSpaceForNewNumber)
            if (digitNumber(newNumber) == digitNumber(subtrahend) && remains != 0) {
                whiteSpaceForSubtrahend.clear()
                whiteSpaceForSubtrahend.append(whiteSpaceForNewNumber.substring(1 until whiteSpaceForNewNumber.length))
            } else for (j in 1 until digitNumber(newNumber) - digitNumber(subtrahend)) whiteSpaceForSubtrahend.append(" ")
            dash.clear()
            if (digitNumber(subtrahend) + 1 >= digitNumber(remains))
                for (j in 0..digitNumber(subtrahend)) dash.append("-")
            else for (j in 0..digitNumber(remains)) dash.append("-")

            if (digitNumber(subtrahend) + 1 >= digitNumber(remains)) {
                if (remains == 0)
                    out.write("${whiteSpaceForNewNumber}0$newNumber\n$whiteSpaceForSubtrahend-$subtrahend\n$whiteSpaceForSubtrahend$dash\n")
                else out.write("${whiteSpaceForNewNumber}$newNumber\n$whiteSpaceForSubtrahend-$subtrahend\n$whiteSpaceForSubtrahend$dash\n")
            } else {
                if (remains == 0)
                    out.write("${whiteSpaceForNewNumber}0$newNumber\n$whiteSpaceForSubtrahend-$subtrahend\n$whiteSpaceForNewNumber$dash\n")
                else out.write("${whiteSpaceForNewNumber}$newNumber\n$whiteSpaceForSubtrahend-$subtrahend\n$whiteSpaceForNewNumber$dash\n")
            }

            remainsCpy = remains
        }

        if (digitNumber(lhv) - digitNumber(currentNumber(lhv, rhv)) >= 1) {
            newNumber = newNumber(remains, takeDigit(residualNumber, currentNumberOfDigit))
            subtrahend = subtrahend(rhv, newNumber)
            remainsCpy = remains
            remains = newNumber - subtrahend
        } else remains = lhv - subtrahend

        for (i in 1..digitNumber(subtrahend) - digitNumber(remains)) whiteSpaceForNewNumber.append(" ")
        if (remainsCpy == 0) whiteSpaceForNewNumber.append(" ")

        out.write("$whiteSpaceForNewNumber$remains")
    }
}

