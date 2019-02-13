package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("mysqlCardRepository")
public class CreditCardRepositoryMysqlImpl implements CreditCardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    CreditCardRepositoryMysqlImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CreditCardDetail createCreditCard(CreditCardDetail creditCardDetail) {
        int result = jdbcTemplate.update(Queries.INSERT_CREDIT_OFFER, creditCardDetail.asMap());
        return creditCardDetail;
    }

    @Override
    public List<CreditCardDetail> listallCreditCardDetails() {
        List<CreditCardDetail> list = jdbcTemplate.query(Queries.LIST_CREDIT_OFFER, new RowMapper<CreditCardDetail>() {
            @Override
            public CreditCardDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                CreditCardDetail creditCardDetail = new CreditCardDetail();
                creditCardDetail.setBank(rs.getString("bank"));
                creditCardDetail.setBalanceTransfer(rs.getDouble("balance_transfer"));
                creditCardDetail.setInterest(rs.getDouble("interest"));
                creditCardDetail.setLimit(rs.getDouble("limit"));
                creditCardDetail.setProductName(rs.getString("product_name"));
                creditCardDetail.setRewards(rs.getString("rewards"));
                creditCardDetail.setType(rs.getString("type"));
                creditCardDetail.setYearlyFee(rs.getDouble("yearly_fee"));
                return creditCardDetail;
            }
        });
        return list;
    }

    @Override
    public List<CreditCardDetail> search(String keyword) {
        return new ArrayList<>();
    }

    @Override
    public List<CreditCardDetail> searchDetail(String bank, String type) {
        return new ArrayList<>();
    }

    static class Queries {
        private static final String INSERT_CREDIT_OFFER = "insert into creditscore.offer(bank, interest, `type`, `limit`, product_name, yearly_fee, rewards, balance_transfer) "
                + "values(:bank, :interest, :type, :limit, :productName, :yearlyFee, :rewards, :balanceTransfer)";

        private static final String LIST_CREDIT_OFFER = "select * from creditscore.offer limit 100";
    }
}
