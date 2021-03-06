/*
 * This file is generated by jOOQ.
*/
package com.acme.moneytransfer.infrastructure.persistence.projection.jooq.tables;


import com.acme.moneytransfer.infrastructure.persistence.projection.jooq.Indexes;
import com.acme.moneytransfer.infrastructure.persistence.projection.jooq.Keys;
import com.acme.moneytransfer.infrastructure.persistence.projection.jooq.Public;
import com.acme.moneytransfer.infrastructure.persistence.projection.jooq.tables.records.MoneyTransferRecord;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MoneyTransfer extends TableImpl<MoneyTransferRecord> {

    private static final long serialVersionUID = -731777430;

    /**
     * The reference instance of <code>PUBLIC.MONEY_TRANSFER</code>
     */
    public static final MoneyTransfer MONEY_TRANSFER = new MoneyTransfer();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MoneyTransferRecord> getRecordType() {
        return MoneyTransferRecord.class;
    }

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.ID</code>.
     */
    public final TableField<MoneyTransferRecord, String> ID = createField("ID", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.ACCOUNT_ID</code>.
     */
    public final TableField<MoneyTransferRecord, String> ACCOUNT_ID = createField("ACCOUNT_ID", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.AMOUNT</code>.
     */
    public final TableField<MoneyTransferRecord, BigDecimal> AMOUNT = createField("AMOUNT", org.jooq.impl.SQLDataType.DECIMAL(19, 2), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.CURRENCY</code>.
     */
    public final TableField<MoneyTransferRecord, String> CURRENCY = createField("CURRENCY", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.LAST_STATUS_CHANGE_DATE</code>.
     */
    public final TableField<MoneyTransferRecord, Timestamp> LAST_STATUS_CHANGE_DATE = createField("LAST_STATUS_CHANGE_DATE", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.STATUS</code>.
     */
    public final TableField<MoneyTransferRecord, String> STATUS = createField("STATUS", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.NUMBER</code>.
     */
    public final TableField<MoneyTransferRecord, String> NUMBER = createField("NUMBER", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.ADDRESS_LINE</code>.
     */
    public final TableField<MoneyTransferRecord, String> ADDRESS_LINE = createField("ADDRESS_LINE", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.CITY</code>.
     */
    public final TableField<MoneyTransferRecord, String> CITY = createField("CITY", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.ZIP_CODE</code>.
     */
    public final TableField<MoneyTransferRecord, String> ZIP_CODE = createField("ZIP_CODE", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.RECIPIENT_NAME</code>.
     */
    public final TableField<MoneyTransferRecord, String> RECIPIENT_NAME = createField("RECIPIENT_NAME", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.TRANSFER_DATE</code>.
     */
    public final TableField<MoneyTransferRecord, Date> TRANSFER_DATE = createField("TRANSFER_DATE", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.TRANSFER_DESCRIPTION</code>.
     */
    public final TableField<MoneyTransferRecord, String> TRANSFER_DESCRIPTION = createField("TRANSFER_DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.MONEY_TRANSFER.VERSION</code>.
     */
    public final TableField<MoneyTransferRecord, Integer> VERSION = createField("VERSION", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.MONEY_TRANSFER</code> table reference
     */
    public MoneyTransfer() {
        this(DSL.name("MONEY_TRANSFER"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.MONEY_TRANSFER</code> table reference
     */
    public MoneyTransfer(String alias) {
        this(DSL.name(alias), MONEY_TRANSFER);
    }

    /**
     * Create an aliased <code>PUBLIC.MONEY_TRANSFER</code> table reference
     */
    public MoneyTransfer(Name alias) {
        this(alias, MONEY_TRANSFER);
    }

    private MoneyTransfer(Name alias, Table<MoneyTransferRecord> aliased) {
        this(alias, aliased, null);
    }

    private MoneyTransfer(Name alias, Table<MoneyTransferRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PRIMARY_KEY_7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<MoneyTransferRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MoneyTransferRecord>> getKeys() {
        return Arrays.<UniqueKey<MoneyTransferRecord>>asList(Keys.CONSTRAINT_7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoneyTransfer as(String alias) {
        return new MoneyTransfer(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoneyTransfer as(Name alias) {
        return new MoneyTransfer(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MoneyTransfer rename(String name) {
        return new MoneyTransfer(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MoneyTransfer rename(Name name) {
        return new MoneyTransfer(name, null);
    }
}
