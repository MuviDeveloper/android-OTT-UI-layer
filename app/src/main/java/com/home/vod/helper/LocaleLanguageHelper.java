package com.home.vod.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * @Desc: This class is used to change our application locale and persist this change for the next time
 *        that our app is going to be used.
 */
public class LocaleLanguageHelper {

	private static final String SELECTED_LANGUAGE = "LocaleLanguage.Helper.Selected.Language";

	/*
	* @Desc: This method is used to inform each activity to update their resources ecspecially Views.
	* */
	public static Context onAttach(Context context) {
		String lang = getPersistedData(context, Locale.getDefault().getLanguage());
		return setLocale(context, lang);
	}

	/*
	 * @Desc: This method is used to inform each activity to update their resources ecspecially Views, Only Different
	 *        is that we can manually set the country code whenever needed, e.g. aplication based.
	 * */
	public static Context onAttach(Context context, String defaultLanguage) {
		String lang = getPersistedData(context, defaultLanguage);
		return setLocale(context, lang);
	}

	public static String getLanguage(Context context) {
		return getPersistedData(context, Locale.getDefault().getLanguage());
	}

	/*
	 * @Desc: This method is used, so that we can manually set the country code whenever needed.
	 * */
	public static Context setLocale(Context context, String language) {
		persist(context, language);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return updateResources(context, language);
		}

		return updateResourcesLegacy(context, language);
	}
	/*
	 * @Desc: This method is used to fetch language code from shared preference.
	 * */
	private static String getPersistedData(Context context, String defaultLanguage) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
	}

	/*
	 * @Desc: This method is used to store language code in shared preference.
	 * */
	private static void persist(Context context, String language) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();

		editor.putString(SELECTED_LANGUAGE, language);
		editor.apply();
	}

	/*
	 * @Desc: This method is used to inform System to update
	 *        Local configuration which are above N.
	 * */
	@TargetApi(Build.VERSION_CODES.N)
	private static Context updateResources(Context context, String language) {
		Locale locale = new Locale(language);
		Locale.setDefault(locale);

		Configuration configuration = context.getResources().getConfiguration();
		configuration.setLocale(locale);
		configuration.setLayoutDirection(locale);

		return context.createConfigurationContext(configuration);
	}
	/*
	 * @Desc: This method is used to inform System to update
	 *        Local configuration which are bellow N.
	 * */
	@SuppressWarnings("deprecation")
	private static Context updateResourcesLegacy(Context context, String language) {
		Locale locale = new Locale(language);
		Locale.setDefault(locale);

		Resources resources = context.getResources();

		Configuration configuration = resources.getConfiguration();
		configuration.locale = locale;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			configuration.setLayoutDirection(locale);
		}

		resources.updateConfiguration(configuration, resources.getDisplayMetrics());

		return context;
	}
}