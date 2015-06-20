package com.ttp.example.pycotestapp.utils;

import com.ttp.example.pycotestapp.object.Entry;

public class AppUtils {

	/**
	 * Get thumbnail image url of an entry
	 * 
	 * @param entry
	 *            The given entry.
	 * @return The url of the thumbnail image if exists, null if not exists.
	 */
	public static String getThumbnailImageUrl(Entry entry) {
		String url = null;
		if (entry != null) {
			String content = entry.getContent();
			if (StringUtils.isNotEmpty(content)) {
				String imgStartTag = "<img src=\"";

				// Search img tag in the content
				int imgTagIndex = content.indexOf(imgStartTag);
				if (imgTagIndex >= 0) { // There is img tag
					int urlStartIndex = imgTagIndex + imgStartTag.length();
					url = content.substring(urlStartIndex,
							content.indexOf("\"", urlStartIndex + 1));
				}
			}
		}
		return url;
	}
}
