package be.ww.household.query.repository;

import java.util.List;

interface CustomHouseHoldRepository {
    List<HouseHoldDocument> findAllByUserId(String userId);
}
