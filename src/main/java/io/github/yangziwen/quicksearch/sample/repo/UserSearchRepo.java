package io.github.yangziwen.quicksearch.sample.repo;

import java.util.Date;
import java.util.List;

import org.elasticsearch.client.RestHighLevelClient;

import io.github.yangziwen.quickdao.core.Order.Direction;
import io.github.yangziwen.quickdao.core.TypedCriteria;
import io.github.yangziwen.quickdao.core.TypedQuery;
import io.github.yangziwen.quicksearch.BaseSearchRepository;
import io.github.yangziwen.quicksearch.sample.entity.User;
import io.github.yangziwen.quicksearch.sample.enums.Gender;

public class UserSearchRepo extends BaseSearchRepository<User> {

    public UserSearchRepo(RestHighLevelClient client) {
        super(client);
    }

    public Integer countByGender(Gender gender) {
        return count(newTypedCriteria().and(User::getGender).keyword().eq(gender));
    }

    public Integer countByCity(String city) {
        return count(newTypedCriteria().and(User::getCity).keyword().eq(city));
    }

    public List<User> listByNamePrefix(String namePrefix) {
        return list(newTypedCriteria().and(User::getName).keyword().startWith(namePrefix));
    }

    public List<User> listByNameSuffixAndAgeRange(String nameSuffix, Integer minAge, Integer maxAge) {
        TypedCriteria<User> criteria = newTypedCriteria()
                .and(User::getName).keyword().endWith(nameSuffix)
                .and(User::getAge).ge(minAge)
                .and(User::getAge).le(maxAge);
        TypedQuery<User> query = newTypedQuery()
                .where(criteria)
                .orderBy(User::getAge, Direction.DESC);
        return list(query);
    }

    public List<User> listAvgAgeGroupByGenderAndCity() {
        TypedQuery<User> query = newTypedQuery()
                .selectExpr(expr -> expr.avg(User::getAge)).as(User::getAvgAge)
                .groupByKeyword(User::getGender)
                .groupByKeyword(User::getCity)
                ;
        return list(query);
    }

    public List<User> listAgeStatsByCreateTimeRangeAndGroupByCityAndGender(Date minCreateTime, Date maxCreateTime) {
        TypedCriteria<User> criteria = newTypedCriteria()
                .and(User::getCreateTime).ge(minCreateTime)
                .and(User::getCreateTime).le(maxCreateTime);
        TypedQuery<User> query = newTypedQuery()
                .selectExpr(expr -> expr.max(User::getAge)).as(User::getMaxAge)
                .selectExpr(expr -> expr.min(User::getAge)).as(User::getMinAge)
                .selectExpr(expr -> expr.avg(User::getAge)).as(User::getAvgAge)
                .where(criteria)
                .groupByKeyword(User::getCity)
                .groupByKeyword(User::getGender)
                .orderByKeyword(User::getCity, Direction.DESC)
                .orderByKeyword(User::getGender);

        return list(query);
    }

}
