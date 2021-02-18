package io.github.yangziwen.quicksearch.sample.repo;

import java.util.List;

import org.elasticsearch.client.RestHighLevelClient;

import io.github.yangziwen.quicksearch.BaseSearchRepository;
import io.github.yangziwen.quicksearch.sample.entity.User;
import io.github.yangziwen.quicksearch.sample.enums.Gender;

public class UserSearchRepo extends BaseSearchRepository<User> {

    public UserSearchRepo(RestHighLevelClient client) {
        super(client);
    }

    public List<User> listByNamePrefix(String namePrefix) {
        return list(newTypedCriteria().and(User::getName).keyword().startWith(namePrefix));
    }

    public Integer countByGender(Gender gender) {
        return count(newTypedCriteria().and(User::getGender).eq(gender));
    }

}
