/*
 * @copyright 2012 Philip Warner
 * @license GNU General Public License
 * 
 * This file is part of Book Catalogue.
 *
 * Book Catalogue is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Book Catalogue is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Book Catalogue.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.eleybourn.bookcatalogue.properties;

/**
 * Implements a property with a default value stored in preferences or provided locally.
 * 
 * @author Philip Warner
 *
 * @param <T>
 */
public abstract class ValuePropertyWithGlobalDefault<T extends Object> extends Property {
	/** Underlying value */
	private T mValue;
	/** Key in preferences for default value */
	private String mDefaultPref;
	/** Default value, for case when not in preferences, or no preferences given */
	private T mDefaultValue;
	/** Indicates that this instance is to only use the global default */
	private boolean mIsGlobal = false;

	/** Children must implement accessor for global default */
	protected abstract T getGlobalDefault();
	/** Children must implement accessor for global default */	
	protected abstract void setGlobalDefault(T value);

	/**
	 * Constructor
	 * 
	 * @param uniqueId			Unique property name
	 * @param group				PropertyGroup it belongs to
	 * @param nameResourceId	Resource ID for name string
	 * @param value				Current value (may be null)
	 * @param preferenceKey		Key into Preferences for default value (may be null)
	 * @param defaultValue		Default value used in case preferences is or returns null
	 * 
	 * Note: it is a good idea to provide a non-null value for defaultValue!
	 */
	public ValuePropertyWithGlobalDefault(String uniqueId, PropertyGroup group, int nameResourceId, T value, String preferenceKey, T defaultValue) {
		super(uniqueId, group, nameResourceId);
		mValue = value;
		mDefaultPref = preferenceKey;
		mDefaultValue = defaultValue;
	}

	/**
	 * Accessor for underlying (or global) value
	 * @return
	 */
	public T get() {
		if (mIsGlobal)
			return getGlobalDefault();
		else
			return mValue;
	}
	/**
	 * Accessor for underlying (or global) value
	 * @return
	 */
	public void set(T value) {
		mValue = value;
		if (mIsGlobal)
			setGlobalDefault(value);
	}

	/**
	 * Accessor for for fully resolved/defaulted value
	 * @return
	 */
	public T getResolvedValue() {
		if (mIsGlobal)
			return getGlobalDefault();
		else if (mValue == null) {
			if (hasGlobalDefault())
				return getGlobalDefault();
			else
				return mDefaultValue;
		} else
			return mValue;
	}
	
	/**
	 * Accessor
	 */
	public boolean isGlobal() {
		return mIsGlobal;
	}

	/**
	 * Accessor
	 */
	public void setGlobal(boolean isGlobal) {
		if (isGlobal && !hasGlobalDefault())
			throw new RuntimeException("Can not set a parameter to global if preference value has not been specified");
		mIsGlobal = isGlobal;
	}
	/**
	 * Accessor
	 */
	public String toString() {
		return mValue.toString();
	}

	/**
	 * Accessor
	 */
	public T getDefaultValue() {
		return mDefaultValue;
	}
	/**
	 * Accessor
	 */
	public String getPreferenceKey() {
		return mDefaultPref;
	}

	/**
	 * Utility to check if a global default is available
	 */
	public boolean hasGlobalDefault() {
		return (mDefaultPref != null && !mDefaultPref.equals(""));
	}

	/**
	 * Utility to check if the current value IS the default value
	 * @param value
	 * @return
	 */
	public boolean isDefault(T value) {
		if (hasGlobalDefault() && !isGlobal())
			return (value == null);

		// We have a default value, and no global prefs
		if (value == null && mDefaultValue == null) {
			return true;
		} else if (value != null && mDefaultValue != null && value.equals(mDefaultValue)) {
			return true;
		} else {
			return false;
		}
	}
}
