package lecture2;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();
        Map<String, Long> collect = task1(RAW_DATA);
        System.out.println(collect);

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Task 2:");
        System.out.println();
        int[] arr = new int[] {3, 4, 2, 7};
        int target = 10;
        String res = task2(arr, target).map(Arrays::toString)
                .orElse("No two numbers, which sum equals target");
        System.out.println(res);

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Task 3:");
        System.out.println();
        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));
        assert fuzzySearch("car", "ca6$$#_rtwheel") == true;
        System.out.println(fuzzySearch("cwhl", "cartwheel"));
        assert fuzzySearch("cwhl", "cartwheel") == true;
        System.out.println(fuzzySearch("cwhee", "cartwheel"));
        assert fuzzySearch("cwhl", "cartwheel") == true;
        System.out.println(fuzzySearch("cartwheel", "cartwheel"));
        assert fuzzySearch("cwhl", "cartwheel") == true;
        System.out.println(fuzzySearch("cwheeel", "cartwheel"));
        assert fuzzySearch("cwheeel", "cartwheel") == false;
        System.out.println(fuzzySearch("lw", "cartwheel"));
        assert fuzzySearch("lw", "cartwheel") == false;
        System.out.println(fuzzySearch("ctz", "cartwheelctgwez"));
        assert fuzzySearch("ctz", "cartwheelctgwez") == true;
        System.out.println(fuzzySearch("Are", "cartwheel"));
        assert fuzzySearch("Are", "cartwheel") == false;
        System.out.println(fuzzySearch("are", "cArtwheel"));
        assert fuzzySearch("are", "cArtwheel") == false;
        System.out.println(fuzzySearch(null, "cartwheel"));
        assert fuzzySearch(null, "cartwheel") == false;
        System.out.println(fuzzySearch(null, null));
        assert fuzzySearch(null, null) == false;





        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */



        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */



        /*
        Task3
            Реализовать функцию нечеткого поиска

                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */


    }

    private static Map<String, Long> task1(Person[] data) {
        return Arrays.stream(data).distinct()
                .sorted(Comparator.comparing(Person::getId))
                .collect(groupingBy(Person::getName, Collectors.counting()));
    }


    private static Optional<int[]> task2(int[] arr, int target) {
        if (arr == null) {
            return Optional.empty();
        }

        Set<Integer> set = new HashSet<>();


        for (int j : arr) {
            if (set.contains(target - j)) {
                return Optional.of(new int[]{target - j, j});
            }

            if (set.contains(j)) {
                continue;
            }

            set.add(j);
        }

        return Optional.empty();
    }

    private static boolean fuzzySearch(String source, String target) {
        if (source == null || target == null || source.length() > target.length()) {
            return false;
        }

        int lastMatchedPos = 0;
        for (int i = 0; i < source.length(); i++) {
            for (; lastMatchedPos < target.length(); lastMatchedPos++) {
                if (source.charAt(i) == target.charAt(lastMatchedPos)) {
                    if (i == source.length() - 1) return true;
                    lastMatchedPos++;
                    break;
                }
            }
        }
        return false;
    }
}
