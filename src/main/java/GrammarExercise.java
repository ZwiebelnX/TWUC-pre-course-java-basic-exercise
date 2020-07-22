import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GrammarExercise {

    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "apple,juice,mother,people,beautiful,apple,dog";
        String secondWordList = "cat,baby,smile,good,apple,beautiful,Dog,nice";

        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);
        //按要求输出到命令行
        System.out.println(result);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStreamVerify1 = pattern.splitAsStream(firstWordList);
        Stream<String> stringStreamVerify2 = pattern.splitAsStream(secondWordList);

        if (!stringStreamVerify1.allMatch(s -> s.matches("[A-z]+")) || !stringStreamVerify2.allMatch(s -> s.matches("[A-z]+"))) {
            throw new RuntimeException("input not valid");
        }

        //在这编写实现代码
        return pattern.splitAsStream(firstWordList)
            .filter(s1 -> pattern.splitAsStream(secondWordList).anyMatch(s2 -> s2.toLowerCase().equals(s1.toLowerCase())))
            .distinct()
            .sorted()
            .map(String::toUpperCase)
            .map(s -> s.replaceAll("(.)", "$1 ").trim())
            .collect(Collectors.toList());
    }
}
