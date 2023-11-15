package be.ww.configuration.test;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    @PostConstruct
    public void test() {
        testRepository.save(
                TestDocument.builder()
                        .testId("test-01")
                        .day(LocalDate.now())
                        .innerTests(List.of(
                                TestDocument.InnerTest.builder()
                                        .test1("test-01")
                                        .value1(BigDecimal.ONE)
                                        .build()
                        ))
                        .build()
        );
    }
}
