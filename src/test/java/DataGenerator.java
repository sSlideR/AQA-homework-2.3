import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class City {
        private City() {
        }

        static List<String> cityToChoose = new ArrayList<String>();


        public static String cityRandomizer() {
            cityToChoose.add("Саратов");
            cityToChoose.add("Москва");
            cityToChoose.add("Санкт-Петербург");
            cityToChoose.add("Екатеринбург");
            cityToChoose.add("Петропавловск-Камчатский");

            int randomSeed = new Random().nextInt(cityToChoose.size());
            return cityToChoose.get(randomSeed);
        }
    }

    public static class Person {
        private Person() {
        }

        public static String fullNameGenerator() {
            Faker faker = new Faker(new Locale("ru"));
            return faker.name().firstName() + " " + faker.name().lastName();
        }

        public static String phoneGenerator() {
            StringBuilder phone = new StringBuilder();
            phone.append("+7");
            Random randomInt = new Random();
            for (int i = 1; i < 11; i++) {
                phone.append(randomInt.nextInt(10));
            }
            return phone.toString();
        }
    }

    public static class Date {
        private Date() {
        }

        public static String dateGenerator() {
            int randomSeed = 3 + new Random().nextInt(20);
            Calendar date = Calendar.getInstance();
            date.add(Calendar.DATE, randomSeed);
            java.util.Date currentDate = date.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            return sdf.format(currentDate);
        }
    }
}