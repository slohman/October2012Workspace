package com.yctc.alpaware;
import android.provider.BaseColumns;


public final class PocketCal {
	

	

	private PocketCal() {}
	
	public static final class Staff implements BaseColumns {
		private Staff() {}
		public static final String STAFF_TABLE_NAME = "AlpaStaff";
		public static final String STAFF_ID = "_id";
		public static final String STAFF_NAME = "Name";
		public static final String STAFF_EXT = "Extension";
		public static final String STAFF_POS = "Position";
		public static final String STAFF_EMAIL= "Email"; 
		public static final String STAFF_UNIT = "Unit";
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
		
		
	}
	
	public static final class OFFICERSMEC implements BaseColumns {
		private OFFICERSMEC() {}
		
		
		public static final String OFFICERSMEC_TABLE_NAME = "officers";
		public static final String OFFICERSMEC_ID = "_id";
		public static final String OFFICERSMEC_NAME = "Name";
		public static final String OFFICERSMEC_POSITION = "Position";
		public static final String OFFICERSMEC_TEL = "Tel";
		public static final String OFFICERSMEC_EMAIL = "Email";
		public static final String OFFICERSMEC_PICTURE = "Picture";
		public static final String OFFICERSMEC_BLOCK = "Block";
		public static final String OFFICERSMEC_LEC = "Lec";
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
		
	}
	
	public static final class MEC implements BaseColumns {
		private MEC() {}
		
		
		public static final String MEC_TABLE_NAME = "officers";
		public static final String MEC_ID = "_id";
		public static final String MEC_NAME = "Name";
		public static final String MEC_POSITION = "Position";
		public static final String MEC_TEL = "Tel";
		public static final String MEC_EMAIL = "Email";
		public static final String MEC_PICTURE = "Picture";
		public static final String MEC_BLOCK = "Block";
		public static final String MEC_LEC = "Unit";
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
		
	}
	public static final class FLTLOG implements BaseColumns {
		private FLTLOG() {}
		public static final String FLTLOGTABLE_NAME = "fltlog";
		public static final String FLTLOG_ID = "_id";
		public static final String FLTLOG_Date = "Date";
		public static final String FLTLOG_flt = "Flt";
		public static final String FLTLOG_ac = "AC";
		public static final String FLTLOG_from= "frm"; 
		public static final String FLTLOG_to = "dest";
		public static final String FLTLOG_out = "out";
		public static final String FLTLOG_off = "off";
		public static final String FLTLOG_on = "onn";
		public static final String FLTLOG_in= "inn"; 
		public static final String FLTLOG_blk = "blk";
		public static final String FLTLOG_bflt = "bflt";
		public static final String FLTLOG_land = "land";
		public static final String FLTLOG_typ = "typ";
		public static final String FLTLOG_comm= "comm"; 
		public static final String FLTLOG_cargo = "cargo";
		public static final String DEFAULT_SORT_ORDER = "Date ASC";
		
		
		
		
	}
	
	public static final class EXPLOG implements BaseColumns {
		private EXPLOG() {}
		public static final String EXPLOGTABLE_NAME = "exp";
		public static final String EXPLOG_ID = "_id";
		public static final String EXPLOG_Date = "exp_DATE";
		public static final String EXPLOG_Expamt = "exp_AMT";
		public static final String EXPLOG_Cat = "exp_CAT";
		public static final String EXPLOG_Comm= "exp_COMM"; 
		public static final String EXPLOG_City="exp_CITY";
		public static final String DEFAULT_SORT_ORDER = "exp_Date ASC";
		
		
}
	public static final class ACEQUIP implements BaseColumns {
		private ACEQUIP() {}
		public static final String ACEQUIPTABLE_NAME = "ACDATA";
		public static final String ACEQUIP_ID = "_id";
		public static final String ACEQUIP_ACNUM = "ac_NUM";
		public static final String ACEQUIP_TYP = "ac_TYP";
		public static final String ACEQUIP_EFP = "ac_EFB";
		public static final String ACEQUIP_RAAS= "ac_RAAS"; 
		public static final String ACEQUIP_CREST="ac_CREST";
		public static final String ACEQUIP_FANS = "ac_FANS";
		public static final String ACEQUIP_SOLIDBULK = "ac_SOLIDBULK";
		public static final String ACEQUIP_HUD = "ac_HUD";
		public static final String ACEQUIP_FSS= "ac_FSS"; 
		public static final String ACEQUIP_ANIMAL="ac_ANIMAL";
		public static final String ACEQUIP_SAT = "ac_SAT";
		public static final String ACEQUIP_HFDLK = "ac_HFDLK";
		public static final String ACEQUIP_CAT3 = "ac_CAT3";
		public static final String ACEQUIP_EGPWS= "ac_EGPWS"; 
		public static final String ACEQUIP_RVSM="ac_RVSM";
		public static final String ACEQUIP_GPS="ac_GPS";
		public static final String ACEQUIP_HF = "ac_HF";
		public static final String ACEQUIP_VNAV = "ac_VNAV";
		public static final String ACEQUIP_INTLOK = "ac_INTLOK";
		public static final String ACEQUIP_ASIANOPS= "ac_ASIANOPS"; 
		public static final String ACEQUIP_RNAV="ac_RNAV";
		public static final String ACEQUIP_EUROPS = "ac_EUROPS";
		public static final String ACEQUIP_SBYGEN = "ac_SBYGEN";
		public static final String ACEQUIP_COMM= "ac_COMM"; 
		
		
		
		public static final String DEFAULT_SORT_ORDER = "ac_NUM ASC";	







}	
	public static final class GTHOT implements BaseColumns {
		private GTHOT() {}
		public static final String GTHOTTABLE_NAME = "hotel";
		public static final String GTHOT_rID = "_id";
		public static final String GTHOT_ID = "ID";
		public static final String GTHOT_CITY = "City";
		public static final String GTHOT_STATE= "state";
		public static final String GTHOT_RAMPTEL= "RampTel"; 
		public static final String GTHOT_EMAIL = "EMAIL";
		public static final String GTHOT_HOTEL = "Hotel";
		public static final String GTHOT_HOTELNUM = "HotelNum";
		public static final String GTHOT_GT = "GT";
		public static final String GTHOT_NOTES = "Notes"; 
		public static final String GTHOT_RAMPTEL1 = "RampTel1";
		public static final String GTHOT_RAMPTEL2 = "RampTel2";
		
		public static final String DEFAULT_SORT_ORDER = "City ASC";
		
		
		
		
	}	

	public static final class PNOTE implements BaseColumns {
		private PNOTE() {}
		public static final String PNOTETABLE_NAME = "Pnotes";
		public static final String PNOTE_ID = "_id";
		public static final String PNOTE_Sub = "subj";
		public static final String PNOTE_COMM = "Notes";
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
}

	
	public static final class CASS implements BaseColumns {
		private CASS() {}
		public static final String CASSTABLE_NAME = "cass";
		public static final String CASS_ID = "_id";
		public static final String CASS_AIRL = "airline";
		public static final String CASS_COMM = "comm";
		public static final String CASS_WEBSITE = "website";
		public static final String CASS_CONTACT1 = "contact1";
		public static final String CASS_CONTACT2 = "contact2";
		public static final String DEFAULT_SORT_ORDER = "airline ASC";
}
	
	public static final class TRIPS implements BaseColumns {
		private TRIPS() {}
		public static final String TRIPSTABLE_NAME = "trips";
		public static final String TRIPS_ID = "_id";
		public static final String TRIPS_PAIRING = "pairing";
		public static final String TRIPS_SHOWDATE= "showdate";
		public static final String TRIPS_SHOWTIME = "showtime";
		public static final String TRIPS_ENDDATE = "enddate";
		public static final String TRIPS_ENDTIME = "endtime";
		public static final String TRIPS_BLOK = "blok";
		public static final String TRIPS_PAY = "pay";
		public static final String TRIPS_BIDMONTH = "bidMonth";
		
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
}
}