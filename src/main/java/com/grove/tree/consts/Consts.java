package com.grove.tree.consts;


public interface Consts {
	
	/* Encoding */
	public static final String UTF_8 = "UTF-8";
	
	/* Http Request */
	public static final String REFERER = "referer";
	public static final String USER_AGENT = "user-agent";
	public static final String ORIGIN = "ORIGIN";
	public static final String XFORWARDEDFOR = "X-Forwarded-For";
	public static final String PROXYCLIENTIP = "Proxy-Client-IP";
	public static final String WLPROXYCLIENTIP = "WL-Proxy-Client-IP";
	public static final String HTTPCLIENTIP = "HTTP_CLIENT_IP";
	public static final String HTTPXFORWARDEDFOR = "HTTP_X_FORWARDED_FOR";
	public static final String ACCEPT_ENCODING = "Accept-Encoding";
	public static final String CONTENT_ENCODING = "Content-Encoding";
	public static final String GZIP = "gzip";
	public static final String EXPIRES = "Expires";
	public static final String REQUEST_URI = "reqURI";
	
	/* Crendential Expired Result */
	public static final String CER_EXPIRED = "expired";
	public static final String CER_EXPIRED_SOON = "soon";
	
	/* Property Key */
	public static final String ROOT_PATH = "dir.root";
//	public static final String WEB_PATH = "dir.web_root";
	public static final String WEB_PATH = "C:/solr_project/workspace/tree/src/main/webapp";
	
	/* Result Code */
	public static final String RS_STATUS = "rs_st";
	public static final String RS_MESSAGE = "rs_msg";
	public static final String RS_DATA = "rs_data";
	public static final String RS_OG = "rs_og";
	
	/* Result Status Code */
	public static final int RS_SUCC = 0;
	public static final int RS_FAIL = 1;
	public static final int RS_ERROR = 2;
	
	/* Result Message Code */
	public static final int NO_USER = 21;
	public static final int LOGIN_CNT = 22;
	public static final int NO_ADMIN_USER = 23;
	public static final int PASSWORD_NOT_EQUAL = 24;
	public static final int SIGNATURE_NOT_EQUAL = 25;
	public static final int SIGNATURE_NOT_EXIST = 26;
	public static final int DUPLICATE_ID = 27;
	public static final int INVALID_EMAIL = 28;
	public static final int INVALID_MOBILE = 29;
	public static final int RECAPTCHA_FAIL = 30;
	public static final int NOT_INCLUDE_ID = 51;
	public static final int TOKEN_NOT_EXIST = 52;
	public static final int TOKEN_NOT_EQUAL = 53;
	public static final int NOT_SAME_PASSWORD = 54;
	
	public static final int EXISTS_USER = 211;
	public static final int NOTEXISTS_USER = 212;
	public static final int EMPTY_PARAMETERS = 213;
	
	public static final String DUPLI = "duplicate";
	public static final String EXISTS = "exists";
	public static final String NOTEXIST = "notexists";
	public static final String NOTEQUAL = "notequals";
	public static final String NOTMULTIPART = "notmultipart";
	public static final String NOTALLOWFILE = "notallowfile";
	
	public static final String DUPLICATED_USERNAME_PASSWORD = "duplicatedUserNamePassword";
	
	/* Edit Type Code */
	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE = 13;
	public static final int UPDATE_READ = 14;
	public static final int SEND_MAIL = 15;
	public static final int UPDATE_SHOW_FL = 16;
	public static final int INSERT_LIKE = 17;
	public static final int DELETE_LIKE = 18;
	public static final int UPDATE_ORDER = 19;
	public static final int INSERT_SUB = 31;
	public static final int UPDATE_SUB = 32;
	public static final int DELETE_SUB = 33;
	public static final int INSERT_ROLE_ADMIN = 41;
	public static final int DELETE_ROLE_ADMIN = 43;
	
	/* File Attach Type */
	public static final String IMG = "image";
	public static final String ATT = "attach";
	
	/* Not Allowed File Extension */
	public static final String FILE_EXCLUDE_EXTENSION = "php,jsp,cgi,bat,exe,asp,aspx,html,htm,css,js,java,class,sh,xml,css,c,cpp,sql,js";
	
	/* Image File Extension */
	public static final String IMAGE_FILE_EXTENSION = "bmp,jpg,jpeg,gif,png,psd,ai,pic";
	
	/* Image File Thumb Size */
	public static final String[] IMAGE_THUMB_SIZE = { "thumb", "middle" };
	public static final String THUMB_SIZE_PC = "900";
	public static final String THUMB_SIZE_TABLET = "600";
	public static final String THUMB_SIZE_MOBILE = "480";
	
	/* Result Map Key name */
	public static final String RS_DETAIL = "detail";
	public static final String RS_FILES = "files";
	public static final String RS_TOTAL = "total";
	public static final String RS_PAGE = "page";
	public static final String RS_FILE = "file";
	public static final String RS_RECORDS = "records";
	public static final String RS_ROWS = "rows";
	public static final String RS_ADMIN_PAGING = "adminPageVO";
	public static final String RS_FRONT_PAGING = "frontPageVO";
	public static final String RS_NEXTPREV = "next_prev";
	public static final String RS_COMMENTS = "comments";
	public static final String RS_COMMENT = "comment";
	public static final String RS_PERMISSION = "permission";
	public static final String RS_MENU_LIST = "menuList";
	public static final String RS_DEVICE = "device";
	
	/**
	 * 게시판 구분 코드 리스트
	 * 사용예:
	 * 	패턴 BT(Board Type) _ 게시판코드
	 * */
	public static final String BT_INQUIRY = "inquiry";
	public static final String BT_INQUIRY_ANSWER = "inquiry_answer";
	
	/**
	 * 시스템 작성자 구분
	 * */
	public static final String SYSTEM = "system";
	public static final String BATCH = "batch";
	
	/**
	 * 엑셀 다운로드 페이징 코드
	 * */
	public static final String EXCEL_PAGING_ALL = "all";
	public static final String EXCEL_PAGING_NOW = "now";
	
	/**
	 * 접근 디바이스 종류
	 * */
	public static final String DEVICE_NORMAL = "normal";
	public static final String DEVICE_TABLET = "tablet";
	public static final String DEVICE_MOBILE = "mobile";
	
	/**
	 * 환경설정 키
	 * */
	public static final String CONFIG_BRANDCD = "conf.brand_code";
	public static final String CONFIG_SERVER_TYPE = "currentServer";
	public static final String CONFIG_SERVER = "conf.server";
	public static final String CONFIG_SERVER_LOCAL = "local";
	public static final String CONFIG_SERVER_DEV = "development";
	public static final String CONFIG_SERVER_PRD = "production";
	public static final String CONFIG_WEBMASTER_NAME = "conf.webmaster_name";
	public static final String CONFIG_WEBMASTER_EMAIL = "conf.webmaster_email";
	public static final String CONFIG_BATCH_AVAILABLE = "conf.batch";
	
	public static final String[] AP_IPS = {
		"10.129",
		"10.130",
		"10.131",
		"10.132",
		"10.133",
		"10.134",
		"10.135",
		"10.136",
		"10.138",
		"10.139",
		"10.140",
		"10.142",
		"10.145",
		"10.150",
		"10.152",
		"10.154",
		"10.155",
		"10.30",
		"10.31",
		"10.32",
		"10.33",
		"10.34",
		"10.35",
		"10.36",
		"10.39",
		"10.52",
		"172.16",
		"172.18",
		"172.19",
		"172.20",
		"172.24",
		"172.25",
		"172.26",
		"172.26",
		"172.29",
		"192.168",
		"192.95"
	};
	
}
