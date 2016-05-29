package ne.com.hypergaragesale;

import android.provider.BaseColumns;


public class SqlContract {

    public SqlContract(){}


    public static abstract class SqlEntry implements BaseColumns {
        public static final String TABLE_NAME = "newEntry";
        //public static final String COLUMN_NAME_ID = "newEntryId";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE="image";
        public static final String COLUMN_NAME_ADDRESS ="address";
        public static final String COLUMN_NAME_LAT ="latitude";
        public static final String COLUMN_NAME_LONG ="longitude";

    }


}
