package com.orchestrator.presentation.util;

public final class HttpStatusMessageUtils {
	
	/**  Message representing http status 200. */
	public static final String OK = "Successful request";
	
	/**  Message representing http status 201. */
	public static final String CREATED = "The request was successful and a new resource was created";
	
	/**  Message representing http status 202. */
	public static final String ACCEPTED = "The request has been successfully received and is being processed";
	
	/**  Message representing http status 204. */
	public static final String NO_CONTENT = "The request was successful and there is no content to view";
	
	/**  Message representing http status 400. */
	public static final String BAD_REQUEST = "The request does not meet all the necessary conditions";
	
	/**  Message representing http status 401. */
	public static final String UNAUTHORIZED = "Not authorized to view";
	
	/**  Message representing http status 403. */
	public static final String FORBIDDEN = "Accessing the resource you were trying to access is forbidden";
	
	/**  Message representing http status 404. */
	public static final String NOT_FOUND = "The resouce you are trying to access is not found";	
	
	private HttpStatusMessageUtils() {}

}
