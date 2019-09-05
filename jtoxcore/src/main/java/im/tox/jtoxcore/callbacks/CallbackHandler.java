/* CallbackHandler.java
 *
 *  Copyright (C) 2013 Tox project All Rights Reserved.
 *
 *  This file is part of jToxcore
 *
 *  jToxcore is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  jToxcore is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with jToxcore.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package im.tox.jtoxcore.callbacks;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import im.tox.jtoxcore.JTox;


/**
 * Callback Handler class which contains methods to manage the callbacks for a
 * JTox instance.
 * 
 * @author sonOfRa
 *            Friend type to use with the CallbackHandler instance
 * 
 */
public class CallbackHandler {
	private List<OnSelfConnectionStatusCallback> onSelfConnectionStatusCallbacks = new ArrayList<OnSelfConnectionStatusCallback>();

	@SuppressWarnings("unused")
	private void onSelfConnectionStatus(int connectionstatus) {
		synchronized (this.onSelfConnectionStatusCallbacks) {
			for (OnSelfConnectionStatusCallback cb : this.onSelfConnectionStatusCallbacks) {
				cb.execute(connectionstatus);
			}
		}
	}

	/**
	 * Add the specified callback for receiving connection status changes.
	 *
	 * @param callback
	 *            the callback to register
	 */
	public void registerOnConnectionStatusCallback(
			OnSelfConnectionStatusCallback callback) {
		if(callback==null)
			return;
		this.onSelfConnectionStatusCallbacks.add(callback);
	}
	/**
	 * Remove the specified callback for receiving connection status changes.
	 *
	 * @param callback
	 *            the callback to remove
	 */
	public void unregisterOnConnectionStatusCallback(
			OnSelfConnectionStatusCallback callback) {
		this.onSelfConnectionStatusCallbacks.remove(callback);
	}

	/**
	 * Remove all connection status callbacks
	 */
	public void clearOnConnectionStatusCallbacks() {
		this.onSelfConnectionStatusCallbacks.clear();
	}

}
