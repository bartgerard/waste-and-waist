package be.ww.shared.elasticsearch.util;

import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public final class ElasticsearchQueryUtils {
    private ElasticsearchQueryUtils() {
        // no-op
    }

    public static <T> List<T> toContent(
            final SearchHits<T> hits
    ) {
        return hits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
