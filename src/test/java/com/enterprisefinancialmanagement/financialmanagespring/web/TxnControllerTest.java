package com.enterprisefinancialmanagement.financialmanagespring.web;

import com.enterprisefinancialmanagement.financialmanagespring.domain.Txn;
import com.enterprisefinancialmanagement.financialmanagespring.service.TxnService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TxnControllerTest {

    @Test
    void addIncomeReturnsTxn() {
        TxnService txns = mock(TxnService.class);
        TxnController controller = new TxnController(txns);

        Txn t = new Txn();
        t.setId(UUID.randomUUID());
        t.setAmount(BigDecimal.valueOf(100));
        t.setType(Txn.Type.INCOME);

        when(txns.addIncome(any(), any(), any(), any(), any())).thenReturn(t);

        Txn result = controller.addIncome(UUID.randomUUID(), BigDecimal.valueOf(100), "Paycheck", null, null);

        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo(Txn.Type.INCOME);
        verify(txns).addIncome(any(), any(), any(), any(), any());
    }

    @Test
    void listAndDeleteInvokeService() {
        TxnService txns = mock(TxnService.class);
        TxnController controller = new TxnController(txns);

        UUID userId = UUID.randomUUID();
        Txn t = new Txn(); t.setId(UUID.randomUUID()); t.setAmount(BigDecimal.ZERO);
        when(txns.listForUser(userId)).thenReturn(List.of(t));

        List<Txn> list = controller.list(userId);
        assertThat(list).hasSize(1);

        controller.delete(t.getId());
        verify(txns).listForUser(userId);
        verify(txns).delete(t.getId());
    }
}
