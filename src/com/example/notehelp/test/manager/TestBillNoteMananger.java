package com.example.notehelp.test.manager;

import com.example.notehelp.entity.MoneyStoreType;
import com.example.notehelp.manager.BillNoteManager;
import com.example.notehelp.test.provider.TextNoteProviderUtil;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class TestBillNoteMananger extends AndroidTestCase {

    private BillNoteManager billNoteManager;

    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        billNoteManager = BillNoteManager.getInstance();
    }

    @Override
    protected void tearDown() throws Exception {
        // TODO Auto-generated method stub
        super.tearDown();
    }
    
    @SmallTest
    public void testSaveMoneyStoreType(){
        MoneyStoreType moneyStoreType = TextNoteProviderUtil.setupMoneyStoreType();
        boolean save = billNoteManager.saveMoneyStoreType(moneyStoreType, getContext());
        assertTrue(save);
    }
    

}
