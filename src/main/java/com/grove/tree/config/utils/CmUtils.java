package com.grove.tree.config.utils;

import java.util.UUID;

import com.grove.tree.config.vo.Page;


public class CmUtils {
	
	private static final int HIGHLIGHT_PREFIX_LENGTH = 50;
	private static final int HIGHLIGHT_SUFFIX_LENGTH = 200;
	
	public static Page paging(int totalCount, double rows, int page) {
		double totalPage = 0;
		int skipCnt = 0;
		int pagingSize = 5;
		int pagingStartNum = 0;
		int pagingEndNum = 0;
		boolean hasPrevPage = false;
		boolean hasNextPage = false;
		int prevPage = 1;
		int nextPage = 1;
		
		if(rows <= 0)
			rows = 10.0;
		
		if(page <= 0)
			page = 1;
		
		if(totalCount <= rows) {
			totalPage = 1;
		} else {
			totalPage = Math.ceil(totalCount / rows);
		}
		
		if(totalPage < page)
			page = (int)totalPage;
		
		if(page > 1)
			skipCnt = (page - 1) * (int)rows;
		
		pagingStartNum = (int)((page - 1) / pagingSize) * pagingSize + 1;
		pagingEndNum = pagingStartNum + (pagingSize - 1);
		
		if(pagingEndNum > totalCount)
			pagingEndNum = totalCount;
		
		hasPrevPage = page > 5;
		hasNextPage	= page < totalPage && totalPage > pagingEndNum;
		
		prevPage = page;
		nextPage = page;
		if(page > 1) {
			prevPage--;
		}
		if(page < totalPage) {
			nextPage++;
		}
		
		Page pageVO = new Page();
		pageVO.setTotalPage((int)totalPage);
		pageVO.setTotalCount(totalCount);
		pageVO.setRows((int)rows);
		pageVO.setSkipCnt(skipCnt);
		pageVO.setStartRowNum(skipCnt + 1);
		pageVO.setEndRowNum(skipCnt + (int)rows);
		pageVO.setPagingSize(pagingSize);
		pageVO.setPagingStartNum(pagingStartNum);
		pageVO.setPagingEndNum(pagingEndNum);
		pageVO.setHasPrevPage(hasPrevPage);
		pageVO.setHasNextPage(hasNextPage);
		pageVO.setPage(page);
		pageVO.setPrevPage(prevPage);
		pageVO.setNextPage(nextPage);
		return pageVO;
	}

	public static String getRandomString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	public static Dimension getScaledToWidth(Dimension imgSize, final int maxWidth) {
		int original_width = imgSize.width;
		int original_height = imgSize.height;
		int new_width = original_width;
		int new_height = original_height;
		double ratio = 0;
		if( original_width > maxWidth ) {
			ratio = (float)maxWidth / original_width;
			new_width = (int)Math.ceil(original_width * ratio);
			new_height = (int)Math.ceil(original_height * ratio);
		}
		return new Dimension(new_width, new_height);
	}
	
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
		int original_width = imgSize.width;
		int original_height = imgSize.height;
		int bound_width = boundary.width;
		int bound_height = boundary.height;
		int new_width = original_width;
		int new_height = original_height;
		
		if(original_width > bound_width) {
			new_width = bound_width;
			new_height = (new_width * original_height) / original_width;
		}
		
		if(new_height > bound_height) {
			new_height = bound_height;
			new_width = (new_height * original_width) / original_height;
		}
		
		return new Dimension(new_width, new_height);
	}
	
	public static String getFileSize(String fileSize) {
		if(!StringUtils.isBlank(fileSize)) {
			return FileUtils.byteCountToDisplaySize(NumberUtils.toLong(fileSize));
		}
		return "";
	}
	
	public static String getDisplayLanguage(String lang_cd) {
		Locale locale = new Locale(lang_cd);
		return locale.getDisplayLanguage();
	}
	
	public static String hiddenStringWithDot(String message, Integer len) {
		if(message == null || len <= 4)
			return message;
		
		if(message.length() <= len)
			return message;
		
		if(message.length() == 0)
			return message;
		
		return message.substring(0, len).concat("...");
	}
	
	public static String getPastTimeToNow(Date date) {
		if(date == null)
			return null;
		
		Calendar now = Calendar.getInstance();
		FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd");
		DateTime oldTime = new DateTime(date);
		DateTime nowTime = new DateTime(now.getTime());
		boolean isSameDay = DateUtils.isSameDay(date, now.getTime());
		
		//Years yearsBetween = Years.yearsBetween(oldTime, nowTime);
		//Months monthsBetween = Months.monthsBetween(oldTime, nowTime);
		Days daysBetween = Days.daysBetween(oldTime, nowTime);
		Hours hoursBetween = Hours.hoursBetween(oldTime, nowTime);
		Minutes minutesBetween = Minutes.minutesBetween(oldTime, nowTime);
		Seconds secondsBetween = Seconds.secondsBetween(oldTime, nowTime);
		
		int hours = hoursBetween.getHours();
		int minutes = minutesBetween.getMinutes();
		int seconds = secondsBetween.getSeconds();
		if(isSameDay) {
			if(hours > 0) {
				return hours + " 시간전";
			}
			if(minutes > 0) {
				return minutes + " 분전";
			}
			if(seconds > 0) {
				return "방금";
			}
		} else {
			int days = daysBetween.getDays();
			if(days == 0) {
				if(hours > 0) {
					return hours + " 시간전";
				}
				if(minutes > 0) {
					return minutes + " 분전";
				}
				if(seconds > 0) {
					return "방금";
				}
			} else if(days > 0 && days < 7) {
				return days + " 일전";
			} else if(days == 7) {
				return "일주일전";
			}
		}
		
		return fdf.format(date);
	}
	
	public static String getFileNameNonExtension(String fileName) {
		if(fileName == null) {
			return "";
		}
		String extension = FilenameUtils.getExtension(fileName);
		return fileName.replace("." + extension, "");
	}
	
	public static String replaceYoutubeThumb(String youtubeId) {
		if(youtubeId != null) {
			if(youtubeId.length() > 0) {
				return "//i.ytimg.com/vi/" + youtubeId + "/mqdefault.jpg";
			}
		}
		return youtubeId;	
	}
	
	public static String extractYoutubeId(String youtubeURL) {
		String regExp = "/?.*(?:youtu.be\\/|v\\/|u/\\w/|embed\\/|watch\\?.*&?v=)";
		Pattern compiledPattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = compiledPattern.matcher(youtubeURL);
		if(matcher.find()) {
			int start = matcher.end();
			return youtubeURL.substring(start, start + 11);
		}
		return youtubeURL;
	}
	
	public static String extractVimeoId(String vimeoURL) {
		String regExp = "https://vimeo.com/?([0-9]+)";
		Pattern compiledPattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = compiledPattern.matcher(vimeoURL);
		if(matcher.find()) {
			int start = matcher.end();
			return vimeoURL.substring(start - 9, start);
		}
		return vimeoURL;
	}
	
	public static String changeSiteURL(String url) {
		if(url != null) {
			if(url.length() > 0) {
				if(!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("//")) {
					url = "http://" + url;
				}
			}
		}
		return url;
	}
	
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader(Consts.XFORWARDEDFOR);
		final String unknown = "unknown";
		if(ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
			ip = request.getHeader(Consts.PROXYCLIENTIP);
		}
		if(ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
			ip = request.getHeader(Consts.WLPROXYCLIENTIP);
		}
		if(ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
			ip = request.getHeader(Consts.HTTPCLIENTIP);
		}
		if(ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
			ip = request.getHeader(Consts.HTTPXFORWARDEDFOR);
		}
		if(ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String replaceSqlInjectQuery(String query) {
		if(query != null) {
			if(!query.isEmpty()) {
				query = query.replaceAll("\\p{Space}", "");
				String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z_\\s]";
				query = query.replaceAll(match, "");
				query = StringUtils.upperCase(query);
				query = StringUtils.remove(query, "SELECT");
				query = StringUtils.remove(query, "FROM");
				query = StringUtils.remove(query, "INSERT");
				query = StringUtils.remove(query, "DELETE");
				query = StringUtils.remove(query, "UPDATE");
				if(query.equals("ASC") || query.equals("DESC")) {
					return StringEscapeUtils.escapeXml10(StringUtils.trim(query));
				} else {
					return StringEscapeUtils.escapeXml10(StringUtils.trim(StringUtils.lowerCase(query)));
				}
			}
		}
		return "";
	}
	
	public static Long getDateToMillis(Date date) {
		return date.getTime();
	}
	
	public static String highlightStringCut(String query, String text) {
		return CmUtils.highlightStringCut(query, text, HIGHLIGHT_PREFIX_LENGTH, HIGHLIGHT_SUFFIX_LENGTH);
	}
	
	public static String highlightStringCut(String query, String text, int prefixLen, int suffixLen) {
		if(query == null || text == null) {
			return null;
		}
		
		String result = text;
		int len = text.length();
		int queryLength = query.length();
		if(len > (prefixLen + suffixLen + queryLength)) {
			if(text.contains(query)) {
				int firstIdx = text.indexOf(query);
				int strPrefixIdx = (firstIdx - prefixLen) > 0 ? (firstIdx - prefixLen) : 0;
				int strSuffixIdx = (firstIdx + suffixLen + queryLength) < len ? (firstIdx + suffixLen + queryLength) : len;
				result = text.substring(strPrefixIdx, strSuffixIdx);
				result = "...".concat(result);
				result = result.concat("...");
			} else {
				result = hiddenStringWithDot(text, 200);
			}
		}
		return result;
	}
	
	public static void setResponseHeaderForExcelFileName(String fileName, HttpServletRequest request, HttpServletResponse response) {
		FastDateFormat fdf = FastDateFormat.getInstance("yyyyMMddHHmmss");
		fileName = fileName + "_" + fdf.format(Calendar.getInstance()) + ".xls";

		String userAgent = request.getHeader("User-Agent");
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		if (userAgent.indexOf("MSIE 5.5") > -1) { // MS IE 5.5-
			try {
				response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(fileName, "UTF-8") + ";");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else if (userAgent.indexOf("MSIE") > -1) { // MS IE 6+
			try {
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8") + ";");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else if(userAgent.indexOf("Opera") > -1) { // 오페라
			try {
				response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("euc-kr"), "latin1") + ";");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static String getDeviceByRequest(HttpServletRequest request) {
		Device device = DeviceUtils.getCurrentDevice(request);
		if(device != null) {
			if(device.isNormal()) {
				return Consts.DEVICE_NORMAL;
			} else if(device.isTablet()) {
				return Consts.DEVICE_TABLET;
			} else if(device.isMobile()) {
				return Consts.DEVICE_MOBILE;
			}
		}
		return Consts.DEVICE_NORMAL;
	}
	
	public static String generateCodeByPrefix(String prefix) {
		String middle = FastDateFormat.getInstance("yyyyMMddHHmmss").format(Calendar.getInstance());
		String suffix = RandomStringUtils.randomAlphabetic(3);
		return prefix.concat(middle).concat(suffix);
	}
	
	public static String removeSpace(String str) {
		return str.replaceAll("\\p{Space}", "");
	}
	
	public static boolean isAvailableServer(String url) {
		try {
			URL checkUrl = new URL(url);
			HttpURLConnection httpUrlConnection = (HttpURLConnection)checkUrl.openConnection();
			httpUrlConnection.setConnectTimeout(3000);
			httpUrlConnection.connect();
			
			if(HttpURLConnection.HTTP_OK == httpUrlConnection.getResponseCode()) {
				return true;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isAmorepacificIP(HttpServletRequest request) {
		boolean result = false;
		String ip = getClientIP(request);
		StringTokenizer stringTokenizer = new StringTokenizer(ip, ".");
		int dots = 0;
		String byte1 = "";
		String byte2 = "";
		String client = "";
		while(stringTokenizer.hasMoreTokens()) {
			++dots;
			
			//if we've reached the second dot, break and check out the ind value
			if(dots == 1) {
				byte1 = stringTokenizer.nextToken();
			} else {
				byte2 = stringTokenizer.nextToken();
				break;
			}
		}
		
		// Piece together half of the client IP address so it can be compared
		// with the forbidden range represented by IP_RANGE
		client = byte1 + "." + byte2;
		for(String allowIp : Consts.AP_IPS) {
			if(allowIp.equals(client)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	*/
	/**
	 * 영문자, 숫자만 추출
	 * @param str
	 * @return
	 */
	/**
	public static String extractAlphaBetNumeric(String str) {
		if(StringUtils.isBlank(str)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		String regex = "[^\\w]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			matcher.appendReplacement(sb, "");
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	*/
	
	/**
	 * 아라비아숫자만 추출
	 * @param str
	 * @return
	 */
	/**
	public static String extractNumeric(String str) {
		if(StringUtils.isBlank(str)) {
			return null;
		}
		String buffer = "";
		char[] charArray = str.toCharArray();
		int cnt = charArray.length;
		for(int i=0; i<cnt; i++) {
			if(Character.isDigit(charArray[i])) {
				buffer += charArray[i];
			}
		}
		return buffer;
	}
	
	public static String prettyTimeFormat(Date then) {
		if(then == null) return null; 
		return new PrettyTime().format(then);
	}
	
	public static String nl2br(String note) {
        if (note == null) return null;
        return note.replaceAll("\n", "<br />");
    }
	
	public static String generateColor(Random r) {
		final char[] hex = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
		};
		
		char[] s = new char[7];
		int n = r.nextInt(0x1000000);
		
		s[0] = '#';
		for(int i=1; i<7; i++) {
			s[i] = hex[n & 0xf];
			n >>= 4;
		}
		return new String(s);
	}
	
	public static int getAgeFromBirthday(Date birthday) {
		if(birthday == null) return -1;
		Calendar today = Calendar.getInstance();
		Calendar birth = DateUtils.toCalendar(birthday);
		
		int factor = 0;
		if(today.get(Calendar.MONTH) > birth.get(Calendar.MONTH)) {
			factor = 1;
		} else if(today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
			if(today.get(Calendar.DAY_OF_MONTH) >= birth.get(Calendar.DAY_OF_MONTH)) {
				factor = 1;
			}
		}
		return (today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)) + factor;
	}
	
	public static double getYearsOfServiceFromHire(Date hire_dt) {
		if(hire_dt == null) return -1;
		DateTime end = DateTime.now();
		DateTime start = new DateTime(hire_dt);
		
		Months months = Months.monthsBetween(start, end);
		int month = months.getMonths();
		
		return month / 12.0;
	}
	
	public static boolean isEmail(String email) {
		if(StringUtils.isBlank(StringUtils.trimToNull(email))) {
			return false;
		}
		return Pattern.matches("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", StringUtils.trimToNull(email));
	}
	
	public static List<Map<String, String>> getLocaleCountriesByLocale(Locale inLocale) {
		List<Locale> locales = new ArrayList<Locale>();
		String[] countries = Locale.getISOCountries();
		for(String country : countries) {
			Locale locale = new Locale(inLocale.getLanguage(), country);
			if(locale.getCountry().isEmpty() || !locale.getVariant().isEmpty()) {
				continue;
			}
			locales.add(locale);
		}
		
		Collections.sort(locales, new Comparator<Locale>() {
			@Override
			public int compare(Locale o1, Locale o2) {
				return o1.getDisplayCountry().compareTo(o2.getDisplayCountry());
			}
		});
		
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for(Locale locale : locales) {
			Map<String, String> item = new HashMap<String, String>();
			String displayCountry = locale.getDisplayCountry(inLocale);
			item.put("country", locale.getCountry());
			item.put("displayCountry", displayCountry);
			result.add(item);
		}
		return result;
	}
	
	public static String getDisplayCountryByCountry(String country) {
		String displayCountry = null;
		List<Locale> locales = LocaleUtils.languagesByCountry(country);
		if(!locales.isEmpty()) {
			for(Locale locale : locales) {
				if(locale.getCountry().isEmpty() || !locale.getVariant().isEmpty()) {
					continue;
				}
				displayCountry = locale.getDisplayCountry();
			}
		}
		return displayCountry;
	}
	
	public static String getBirthYearByRegisterNo(String register_no) {
		if(!StringUtils.isBlank(register_no) && StringUtils.trim(register_no).length() == 14) {
			String chkYear = register_no.substring(7, 8);
			if(chkYear.equals("9") || chkYear.equals("0")) {
				return "1800";
			} else if(chkYear.equals("1") || chkYear.equals("2")) {
				return "1900";
			} else if(chkYear.equals("3") || chkYear.equals("4")) {
				return "2000";
			} else if(chkYear.equals("5") || chkYear.equals("6")) {
				return "1900";
			} else if(chkYear.equals("7") || chkYear.equals("8")) {
				return "2000";
			}
		}
		return "1900";
	}
	
	public static int getAgeByRegisterNo(String register_no) {
		int year = NumberUtils.toInt(register_no.substring(0, 2));
		int birth_year = NumberUtils.toInt(getBirthYearByRegisterNo(register_no));
		year = year + birth_year;
		
		String mmdd = register_no.substring(3, 6);
		String birth = Integer.toString(year) + mmdd;
		FastDateFormat fdf = FastDateFormat.getInstance("yyyyMMdd");
		
		try {
			return getAgeFromBirthday(fdf.parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean chkNull(String val) {
		
		if (val == null ) {
			return true;
		} else if (val.trim().length() ==0) {
			return true;
		} else if ("null".equals( val )) {
			return true;
		}
		
		return false;
	}
	
	public static Map<String, String> fromDateForSearch(String year, String month, String day) {
		Map<String, String> map = new HashMap<String, String>();
		
		if(!StringUtils.isBlank(year) && StringUtils.isBlank(month)) {
			map.put("year", year);
			map.put("month", "01");
			map.put("day", "01");
		} else if(!StringUtils.isBlank(year) && !StringUtils.isBlank(month) && StringUtils.isBlank(day)) {
			map.put("year", year);
			map.put("month", month);
			map.put("day", "01");
		} else {
			map.put("year", year);
			map.put("month", month);
			map.put("day", day);
		}
		
		return map;
	}
	
	public static Map<String, String> toDateForSearch(String year, String month, String day) {
		Map<String, String> map = new HashMap<String, String>();
		
		if(!StringUtils.isBlank(year) && StringUtils.isBlank(month)) {
			map.put("year", year);
			map.put("month", "12");
			map.put("day", "31");
		} else if(!StringUtils.isBlank(year) && !StringUtils.isBlank(month) && StringUtils.isBlank(day)) {
			map.put("year", year);
			map.put("month", month);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			
			try {
				Date to = sdf.parse(year + month + "01");
				cal.setTime(to);
				String to_day = Integer.toString((cal.getMaximum(Calendar.DAY_OF_MONTH)));
				
				map.put("day", to_day);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			map.put("year", year);
			map.put("month", month);
			map.put("day", day);
		}
		
		return map;
	}
	
	*/
	
}
