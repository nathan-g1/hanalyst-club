//package hanalyst.application.hanalystclub.Database;
//
//import android.app.Application;
//import android.os.AsyncTask;
//
//public class RoomCleaner {
//    public RoomCleaner(Application application) {
//        HAnalystDb hAnalystDb = HAnalystDb.getInstance(application);
//    }
//
//    public void cleanAllRoomData() {
//        new CleanRoomAsyncTask().execute();
//    }
//
//    private class CleanRoomAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        public CleanRoomAsyncTask() {
//
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            getRoomDatabase().clear
//            return null;
//        }
//    }
//}
