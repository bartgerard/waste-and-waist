package be.ww.household.query.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

import static be.ww.shared.elasticsearch.util.ElasticsearchQueryUtils.toContent;
import static co.elastic.clients.elasticsearch._types.query_dsl.ChildScoreMode.None;
import static org.springframework.data.domain.Pageable.unpaged;

// range + lte -> JsonData.of(..)
@RequiredArgsConstructor
class CustomHouseHoldRepositoryImpl implements CustomHouseHoldRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    public List<HouseHoldDocument> findAllByUserId(
            final String userId
    ) {
        final Query query = NativeQuery.builder()
                .withFilter(q -> q.nested(n -> n
                        .path("members")
                        .query(nq -> nq.bool(nb -> nb
                                .must(nbq -> nbq.term(nt -> nt
                                        .field("members.user_id")
                                        .value(userId)
                                ))
                        ))
                        .scoreMode(None)
                ))
                .withPageable(unpaged())
                .build();

        final SearchHits<HouseHoldDocument> hits = elasticsearchOperations.search(
                query,
                HouseHoldDocument.class
        );

        return toContent(hits);
    }
}
