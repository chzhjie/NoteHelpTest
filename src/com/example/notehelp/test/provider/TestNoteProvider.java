
package com.example.notehelp.test.provider;

import com.example.notehelp.entity.BillNote;
import com.example.notehelp.entity.MoneyStoreType;
import com.example.notehelp.entity.NoteContent;
import com.example.notehelp.provider.NoteProvider;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;
import android.test.suitebuilder.annotation.SmallTest;

public class TestNoteProvider extends ProviderTestCase2<NoteProvider> {

    public TestNoteProvider() {
        super(NoteProvider.class, NoteContent.AUTHORITY);
        // TODO Auto-generated constructor stub
    }

    private MockContentResolver mMockContentResolver;
    private SQLiteDatabase mSqLiteDatabase;
    private NoteProvider noteProvider;
    private Context mMockContext;

    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        mMockContentResolver = getMockContentResolver();
        noteProvider = getProvider();
        mMockContext = new MockContext2(getMockContext(), getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        // TODO Auto-generated method stub
        super.tearDown();
    }

    /**
     * Private context wrapper used to add back getPackageName() for these tests.
     */
    private static class MockContext2 extends ContextWrapper {

        private final Context mRealContext;

        public MockContext2(Context mockContext, Context realContext) {
            super(mockContext);
            mRealContext = realContext;
        }

        @Override
        public Context getApplicationContext() {
            return this;
        }

        @Override
        public String getPackageName() {
            return mRealContext.getPackageName();
        }

        @Override
        public Object getSystemService(String name) {
            return mRealContext.getSystemService(name);
        }
    }


    @SmallTest
    public void testMoneyStoreTypeSave(){
        MoneyStoreType moneyStoreType = TextNoteProviderUtil.setupMoneyStoreType();
        moneyStoreType.save(mMockContext);
        long moneyStoreId = moneyStoreType.mId;
        MoneyStoreType moneyStoreType2 = MoneyStoreType.restoreMoneyStoreTypeWithID(mMockContext,moneyStoreId);
        TextNoteProviderUtil.assertMoneyStoreTypeEqual("testMoneyStoreSave:",moneyStoreType,moneyStoreType2);
    }
    
    public void testBillNoteSave(){
        BillNote billNote = TextNoteProviderUtil.setupBillNote();
        billNote.save(mMockContext);
        long billNoteID = billNote.mId;
        BillNote billNote2 = BillNote.restoreBillNoteWithID(mMockContext, billNoteID);
        TextNoteProviderUtil.assertBillNoteEqual("testBillNoteSave", billNote, billNote2);
    }
}
