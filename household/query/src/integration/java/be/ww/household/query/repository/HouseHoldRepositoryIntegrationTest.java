package be.ww.household.query.repository;

import be.ww.shared.elasticsearch.test.ElasticsearchContainerBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {
        HouseHoldRepository.class
})
@DataElasticsearchTest
@EnableAutoConfiguration
//@EnableElasticsearchRepositories(considerNestedRepositories = true)
class HouseHoldRepositoryIntegrationTest extends ElasticsearchContainerBaseTest {

    private static final HouseHoldDocument HOUSE_HOLD_1 = HouseHoldDocument.builder()
            .houseHoldId("house-hold-1")
            .members(Set.of(
                    MemberField.of("member-1", "Joe", LocalDate.parse("1988-01-01"), "user-1"),
                    MemberField.of("member-2", "Partner", LocalDate.parse("1988-01-01"), "user-2"),
                    MemberField.of("member-3", "child-1", LocalDate.parse("1988-01-01"), null),
                    MemberField.of("member-4", "child-2", LocalDate.parse("1988-01-01"), null)
            ))
            .build();

    private static final HouseHoldDocument HOUSE_HOLD_2 = HouseHoldDocument.builder()
            .houseHoldId("house-hold-1")
            .members(Set.of(
                    MemberField.of("member-1", "Marc (actually Joe)", LocalDate.parse("1988-01-01"), "user-1"),
                    MemberField.of("member-2", "Mistress", LocalDate.parse("1988-01-01"), "user-3")
            ))
            .build();

    @Autowired
    private HouseHoldRepository houseHoldRepository;

    @BeforeEach
    void setUp() {
        houseHoldRepository.deleteAll();
        houseHoldRepository.saveAll(List.of(
                HOUSE_HOLD_1,
                HOUSE_HOLD_2
        ));
    }

    @Test
    void findAllByUserId_oneHouseHold() {
        final List<HouseHoldDocument> houseHolds = houseHoldRepository.findAllByUserId("user-2");

        assertThat(houseHolds)
                .hasSize(1)
                .containsExactlyInAnyOrder(HOUSE_HOLD_1);
    }

    @Test
    void findAllByUserId_multipleHouseHolds() {
        final List<HouseHoldDocument> houseHolds = houseHoldRepository.findAllByUserId("user-1");

        assertThat(houseHolds)
                .hasSize(2)
                .containsExactlyInAnyOrder(HOUSE_HOLD_1, HOUSE_HOLD_2);
    }

}