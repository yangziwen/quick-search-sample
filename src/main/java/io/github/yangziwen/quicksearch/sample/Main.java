package io.github.yangziwen.quicksearch.sample;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import io.github.yangziwen.quicksearch.sample.entity.User;
import io.github.yangziwen.quicksearch.sample.enums.Gender;
import io.github.yangziwen.quicksearch.sample.repo.UserSearchRepo;

public class Main {

    private static RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

    public static void main(String[] args) throws Exception {
        try {
            UserSearchRepo repo = new UserSearchRepo(client);
        } finally {
            client.close();
        }
    }

    private static void createUsers(UserSearchRepo repo) {

        List<User> list = new ArrayList<>();

        list.add(User.builder()
                .name("张一")
                .gender(Gender.FEMALE)
                .city("北京")
                .age(25)
                .createTime(parseDateQuietly("2021-01-20"))
                .build());

        list.add(User.builder()
                .name("张二")
                .gender(Gender.MALE)
                .city("上海")
                .age(30)
                .createTime(parseDateQuietly("2021-01-21"))
                .build());

        list.add(User.builder()
                .name("张三")
                .gender(Gender.MALE)
                .city("南京")
                .age(26)
                .createTime(parseDateQuietly("2021-02-05"))
                .build());

        list.add(User.builder()
                .name("张四")
                .gender(Gender.FEMALE)
                .city("上海")
                .age(20)
                .createTime(parseDateQuietly("2021-02-01"))
                .build());

        list.add(User.builder()
                .name("张五")
                .gender(Gender.MALE)
                .city("北京")
                .age(34)
                .createTime(parseDateQuietly("2021-02-02"))
                .build());

        list.add(User.builder()
                .name("李一")
                .gender(Gender.MALE)
                .city("北京")
                .age(29)
                .createTime(parseDateQuietly("2021-02-05"))
                .build());

        list.add(User.builder()
                .name("李二")
                .gender(Gender.MALE)
                .city("北京")
                .age(23)
                .createTime(parseDateQuietly("2021-01-18"))
                .build());

        list.add(User.builder()
                .name("李三")
                .gender(Gender.FEMALE)
                .city("上海")
                .age(23)
                .createTime(parseDateQuietly("2021-01-17"))
                .build());

        list.add(User.builder()
                .name("李四")
                .gender(Gender.MALE)
                .city("南京")
                .age(28)
                .createTime(parseDateQuietly("2021-01-13"))
                .build());

        list.add(User.builder()
                .name("李五")
                .gender(Gender.MALE)
                .city("上海")
                .age(32)
                .createTime(parseDateQuietly("2021-01-02"))
                .build());

        list.add(User.builder()
                .name("王一")
                .gender(Gender.MALE)
                .city("北京")
                .age(27)
                .createTime(parseDateQuietly("2021-01-05"))
                .build());

        list.add(User.builder()
                .name("王二")
                .gender(Gender.MALE)
                .city("北京")
                .age(25)
                .createTime(parseDateQuietly("2021-01-03"))
                .build());

        list.add(User.builder()
                .name("王三")
                .gender(Gender.FEMALE)
                .city("上海")
                .age(28)
                .createTime(parseDateQuietly("2021-01-05"))
                .build());

        list.add(User.builder()
                .name("王四")
                .gender(Gender.FEMALE)
                .city("天津")
                .age(25)
                .createTime(parseDateQuietly("2021-01-27"))
                .build());

        list.add(User.builder()
                .name("王五")
                .gender(Gender.MALE)
                .city("上海")
                .age(25)
                .createTime(parseDateQuietly("2021-02-13"))
                .build());

        list.add(User.builder()
                .name("赵一")
                .gender(Gender.FEMALE)
                .city("南京")
                .age(27)
                .createTime(parseDateQuietly("2021-02-17"))
                .build());

        list.add(User.builder()
                .name("赵二")
                .gender(Gender.MALE)
                .city("上海")
                .age(31)
                .createTime(parseDateQuietly("2021-02-11"))
                .build());

        list.add(User.builder()
                .name("赵三")
                .gender(Gender.MALE)
                .city("北京")
                .age(30)
                .createTime(parseDateQuietly("2021-02-14"))
                .build());

        list.add(User.builder()
                .name("赵四")
                .gender(Gender.MALE)
                .city("沈阳")
                .age(37)
                .createTime(parseDateQuietly("2021-02-11"))
                .build());

        list.add(User.builder()
                .name("赵五")
                .gender(Gender.MALE)
                .city("上海")
                .age(37)
                .createTime(parseDateQuietly("2021-02-14"))
                .build());

        repo.batchInsert(list, 7);

    }

    private static Date parseDateQuietly(String date) {
        try {
            return DateUtils.parseDate(date, "yyyy-MM-dd");
        } catch (ParseException e) {
            return null;
        }
    }
}
