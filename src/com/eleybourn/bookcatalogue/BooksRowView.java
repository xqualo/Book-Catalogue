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

package com.eleybourn.bookcatalogue;

import android.database.Cursor;

/**
 * Convenience class to avoid having to write the same code in more than one place. This
 * class has get*() methods for the most common book-related fields. Passed a Cursor object
 * it will retrieve the specified value using the current cursor row.
 * 
 * Both BooksCursor and BooksSnapshotCursor implement a getRowView() method that returns
 * a cached BookRowView based on the cursor.
 * 
 * @author Philip Warner
 */
public class BooksRowView {

	/** Associated cursor object */
	private final Cursor mCursor;

	/**
	 * Constructor
	 * 
	 * @param c		Cursor to use
	 */
	public BooksRowView(Cursor c) {
		mCursor = c;
	}

	private int mIdCol = -2;
	public final long getId() {
		if (mIdCol < 0) {
			mIdCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_ROWID);
			if (mIdCol < 0)
				throw new RuntimeException("ISBN column not in result set");
		}
		return mCursor.getLong(mIdCol);// mCurrentRow[mIsbnCol];
	}

	private int mIsbnCol = -2;
	public final String getIsbn() {
		if (mIsbnCol < 0) {
			mIsbnCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_ISBN);
			if (mIsbnCol < 0)
				throw new RuntimeException("ISBN column not in result set");
		}
		return mCursor.getString(mIsbnCol);// mCurrentRow[mIsbnCol];
	}

	private int mPrimaryAuthorCol = -2;
	public final String getPrimaryAuthorName() {
		if (mPrimaryAuthorCol < 0) {
			mPrimaryAuthorCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_AUTHOR_FORMATTED_GIVEN_FIRST);
			if (mPrimaryAuthorCol < 0)
				throw new RuntimeException("Primary author column not in result set");
		}
		return mCursor.getString(mPrimaryAuthorCol);
//		return mCurrentRow[mPrimaryAuthorCol];
	}

	private int mTitleCol = -2;
	public final String getTitle() {
		if (mTitleCol < 0) {
			mTitleCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_TITLE);
			if (mTitleCol < 0)
				throw new RuntimeException("Title column not in result set");
		}
		return mCursor.getString(mTitleCol);
//		return mCurrentRow[mTitleCol];
	}

	private int mDescriptionCol = -2;
	public final String getDescription() {
		if (mDescriptionCol < 0) {
			mDescriptionCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_DESCRIPTION);
			if (mDescriptionCol < 0)
				throw new RuntimeException("Description column not in result set");
		}
		return mCursor.getString(mDescriptionCol);
//		return mCurrentRow[mDescriptionCol];
	}

	private int mNotesCol = -2;
	public final String getNotes() {
		if (mNotesCol < 0) {
			mNotesCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_NOTES);
			if (mNotesCol < 0)
				throw new RuntimeException("Notes column not in result set");
		}
		return mCursor.getString(mNotesCol);
//		return mCurrentRow[mNotesCol];
	}


	private int mReadCol = -2;
	public final int getRead() {
		if (mReadCol < 0) {
			mReadCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_READ);
			if (mTitleCol < 0)
				throw new RuntimeException("READ column not in result set");
		}
		return mCursor.getInt(mReadCol);
//		return Integer.parseInt(mCurrentRow[mReadCol]);
	}

	private int mPublisherCol = -2;
	public final String getPublisher() {
		if (mPublisherCol < 0) {
			mPublisherCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_PUBLISHER);
			if (mPublisherCol < 0)
				throw new RuntimeException("PUBLISHER column not in result set");
		}
		return mCursor.getString(mPublisherCol);
	}

	private int mDatePublishedCol = -2;
	public final String getDatePublished() {
		if (mDatePublishedCol < 0) {
			mDatePublishedCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_DATE_PUBLISHED);
			if (mDatePublishedCol < 0)
				throw new RuntimeException("DATE_PUBLISHED column not in result set");
		}
		return mCursor.getString(mDatePublishedCol);
	}

	private int mGenreCol = -2;
	public final String getGenre() {
		if (mGenreCol < 0) {
			mGenreCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_GENRE);
			if (mGenreCol < 0)
				throw new RuntimeException("GENRE column not in result set");
		}
		return mCursor.getString(mGenreCol);
	}

	private int mLocationCol = -2;
	public final String getLocation() {
		if (mLocationCol < 0) {
			mLocationCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_LOCATION);
			if (mLocationCol < 0)
				throw new RuntimeException("LOCATION column not in result set");
		}
		return mCursor.getString(mLocationCol);
	}

	private int mSeriesCol = -2;
	public final String getSeries() {
		if (mSeriesCol < 0) {
			mSeriesCol = mCursor.getColumnIndex(CatalogueDBAdapter.KEY_SERIES_NAME);
			if (mSeriesCol < 0)
				throw new RuntimeException("SERIES column not in result set");
		}
		return mCursor.getString(mSeriesCol);
	}
	
	public String getString(int position) {
		return mCursor.getString(position);
	}
	public String getString(String columnName) {
		return mCursor.getString(mCursor.getColumnIndex(columnName));
	}
}
