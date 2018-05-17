package com.acme.moneytransfer.infrastructure.persistence.projection;

import com.acme.core.domain.model.account.AccountId;
import com.acme.core.domain.model.money.Amount;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransferId;
import com.acme.moneytransfer.projection.MoneyTransferProjection;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import org.jooq.DSLContext;
import org.jooq.Record4;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;

import static com.acme.core.util.preconditions.Preconditions.requireNonNull;
import static com.acme.moneytransfer.infrastructure.persistence.projection.jooq.tables.MoneyTransfer.MONEY_TRANSFER;
import static java.util.stream.Collectors.toList;

@Repository
public class MoneyTransferJooqProjection implements MoneyTransferProjection {
	private final DSLContext dsl;

	public MoneyTransferJooqProjection(DSLContext dsl) {
		this.dsl = requireNonNull(dsl, "dsl");
	}

	@Override
	public Iterable<BriefMoneyTransferDataView> getBriefMoneyTransferDataView(AccountId accountId) {
		return this.dsl
			.select(MONEY_TRANSFER.ID, MONEY_TRANSFER.RECIPIENT_NAME, MONEY_TRANSFER.AMOUNT, MONEY_TRANSFER.TRANSFER_DATE)
			.from(MONEY_TRANSFER)
			.where(MONEY_TRANSFER.ACCOUNT_ID.equal(accountId.id()))
			.fetchStream()
			.map(this::toBriefMoneyTransferDataView)
			.collect(toList());
	}

	private BriefMoneyTransferDataViewImpl toBriefMoneyTransferDataView(Record4<String, String, BigDecimal, Date> record) {
		return new BriefMoneyTransferDataViewImpl(
			MoneyTransferId.of(record.value1()),
			record.value2(),
			Amount.pln(record.component3().doubleValue()),
			record.value4().toLocalDate()
		);
	}
}
