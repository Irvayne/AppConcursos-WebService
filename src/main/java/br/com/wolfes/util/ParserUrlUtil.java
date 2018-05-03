package br.com.wolfes.util;

public class ParserUrlUtil {

	public static String parserUrl(String url) {
		String[] urlSplit = url.split("/");
		return urlSplit[urlSplit.length - 1];
	}

	public static String parserSiglaEstado(String url) {
		String[] urlSplit = url.split("/");
		return urlSplit[urlSplit.length - 2];
	}

}
