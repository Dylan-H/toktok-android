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
 * Friend type to use with the CallbackHandler instance
 */
public class CallbackHandler {
    private List<OnSelfConnectionStatusCallback> onSelfConnectionStatusCallbacks = new ArrayList<OnSelfConnectionStatusCallback>();
    private List<OnFriendNameChangeCallback> onFriendNameChangeCallbacks = new ArrayList<OnFriendNameChangeCallback>();
    private List<OnFriendStatusMessageChangeCallback> onFriendStatusMessageChangeCallbacks = new ArrayList<OnFriendStatusMessageChangeCallback>();
    private List<OnFriendStatusChangeCallback> onFriendStatusChangeCallbacks = new ArrayList<OnFriendStatusChangeCallback>();
    private List<OnFriendConnectionStatusChangeCallback> onFriendConnectionStatusChangeCallbacks = new ArrayList<OnFriendConnectionStatusChangeCallback>();
    private List<OnFriendTypingChangeCallback> onFriendTypingChangeCallbacks = new ArrayList<OnFriendTypingChangeCallback>();
    private List<OnFriendReadReceiptCallback> onFriendReadReceiptCallbacks = new ArrayList<OnFriendReadReceiptCallback>();
    private List<OnFriendRequestCallback> onFriendRequestCallbacks = new ArrayList<OnFriendRequestCallback>();
    private List<OnFriendMessageCallback> onFriendMessageCallbacks = new ArrayList<>();
    private List<OnFriendLossyPacketCallback> onFriendLossyPacketCallbacks = new ArrayList<>();
    private List<OnFriendLossLessPacketCallback> onFriendLossLessPacketCallbacks = new ArrayList<>();

    @SuppressWarnings("unused")
    private void onSelfConnectionStatus(int connectionstatus) {
        synchronized (this.onSelfConnectionStatusCallbacks) {
            for (OnSelfConnectionStatusCallback cb : this.onSelfConnectionStatusCallbacks) {
                cb.execute(connectionstatus);
            }
        }
    }


    @SuppressWarnings("unused")
    private void onFriendNameChange(int friendnumber, String name) {
        synchronized (this.onSelfConnectionStatusCallbacks) {
            for (OnFriendNameChangeCallback cb : this.onFriendNameChangeCallbacks) {
                cb.execute(friendnumber, name);
            }
        }
    }

    @SuppressWarnings("unused")
    private void onFriendStatusMessageChange(int friendnumber, String message) {
        synchronized (this.onFriendStatusMessageChangeCallbacks) {
            for (OnFriendStatusMessageChangeCallback cb : this.onFriendStatusMessageChangeCallbacks) {
                cb.execute(friendnumber, message);
            }
        }
    }

    @SuppressWarnings("unused")
    private void onFriendStatusChange(int friendnumber, int status) {
        synchronized (this.onFriendStatusMessageChangeCallbacks) {
            for (OnFriendStatusChangeCallback cb : this.onFriendStatusChangeCallbacks) {
                cb.execute(friendnumber, status);
            }
        }
    }

    @SuppressWarnings("unused")
    private void onFriendConnectionStatusChange(int friendnumber, int status) {
        synchronized (this.onFriendConnectionStatusChangeCallbacks) {
            for (OnFriendConnectionStatusChangeCallback cb : this.onFriendConnectionStatusChangeCallbacks) {
                cb.execute(friendnumber, status);
            }
        }
    }


    @SuppressWarnings("unused")
    private void onFriendTypingChange(int friendnumber, boolean typing) {
        synchronized (this.onFriendTypingChangeCallbacks) {
            for (OnFriendTypingChangeCallback cb : this.onFriendTypingChangeCallbacks) {
                cb.execute(friendnumber, typing);
            }
        }
    }

    @SuppressWarnings("unused")
    private void onFriendReadReceipt(int friendnumber, int messageid) {
        synchronized (this.onFriendReadReceiptCallbacks) {
            for (OnFriendReadReceiptCallback cb : this.onFriendReadReceiptCallbacks) {
                cb.execute(friendnumber, messageid);
            }
        }
    }


    @SuppressWarnings("unused")
    private void onFriendReadRequest(byte[] publickey, String message) {
        synchronized (this.onFriendRequestCallbacks) {
            for (OnFriendRequestCallback cb : this.onFriendRequestCallbacks) {
                cb.execute(publickey, message);
            }
        }
    }


    @SuppressWarnings("unused")
    private void onFriendMessage(int friendnumber, int type, String message) {
        synchronized (this.onFriendMessageCallbacks) {
            for (OnFriendMessageCallback cb : this.onFriendMessageCallbacks) {
                cb.execute(friendnumber, type, message);
            }
        }
    }


    @SuppressWarnings("unused")
    private void onFriendLossyPacket(int friendnumber, String message) {
        synchronized (this.onFriendLossyPacketCallbacks) {
            for (OnFriendLossyPacketCallback cb : this.onFriendLossyPacketCallbacks) {
                cb.execute(friendnumber, message);
            }
        }
    }


    @SuppressWarnings("unused")
    private void onFriendLossLessPacket(int friendnumber, String message) {
        synchronized (this.onFriendLossLessPacketCallbacks) {
            for (OnFriendLossLessPacketCallback cb : this.onFriendLossLessPacketCallbacks) {
                cb.execute(friendnumber, message);
            }
        }
    }

    public void registerOnConnectionStatusCallback(
            OnSelfConnectionStatusCallback callback) {
        if (callback == null)
            return;
        this.onSelfConnectionStatusCallbacks.add(callback);
    }

    public void unregisterOnConnectionStatusCallback(
            OnSelfConnectionStatusCallback callback) {
        if (callback == null)
            return;
        this.onSelfConnectionStatusCallbacks.remove(callback);
    }

    public void clearOnConnectionStatusCallbacks() {
        this.onSelfConnectionStatusCallbacks.clear();
    }


    public void registerOnFriendNameChangeCallback(
            OnFriendNameChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendNameChangeCallbacks.add(callback);
    }

    public void unregisterOnFriendNameChangeCallback(
            OnFriendNameChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendNameChangeCallbacks.remove(callback);
    }

    public void clearOnFriendNameChangeCallback() {
        this.onFriendNameChangeCallbacks.clear();
    }

    //////OnFriendStatusMessageChangeCallback
    public void registerOnFriendStatusMessageChangeCallback(
            OnFriendStatusMessageChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendStatusMessageChangeCallbacks.add(callback);
    }

    public void unregisterOnConnectionStatusCallback(
            OnFriendStatusMessageChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendStatusMessageChangeCallbacks.remove(callback);
    }

    public void clearOnConnectionStatusCallback() {
        this.onFriendStatusMessageChangeCallbacks.clear();
    }


    /*
    onFriendStatusChangeCallbacks
     */
    public void registerOnFriendStatusChangeCallback(
            OnFriendStatusChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendStatusChangeCallbacks.add(callback);
    }

    public void unregisterOnFriendStatusChangeCallback(
            OnFriendStatusChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendStatusChangeCallbacks.remove(callback);
    }

    public void clearOnFriendStatusChangeCallback() {
        this.onFriendStatusChangeCallbacks.clear();
    }

    /*
    OnFriendConnectionStatusChangeCallback
     */
    public void registerOnFriendConnectionStatusChangeCallback(
            OnFriendConnectionStatusChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendConnectionStatusChangeCallbacks.add(callback);
    }

    public void unregisterOnFriendConnectionStatusChangeCallback(
            OnFriendConnectionStatusChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendConnectionStatusChangeCallbacks.remove(callback);
    }

    public void clearOnFriendConnectionStatusChangeCallback() {
        this.onFriendConnectionStatusChangeCallbacks.clear();
    }

    /*
    OnFriendTypingChangeCallback
     */
    public void registerOnFriendTypingChangeCallback(
            OnFriendTypingChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendTypingChangeCallbacks.add(callback);
    }

    public void unregisterOnFriendTypingChangeCallback(
            OnFriendTypingChangeCallback callback) {
        if (callback == null)
            return;
        this.onFriendTypingChangeCallbacks.remove(callback);
    }

    public void clearOnFriendTypingChangeCallback() {
        this.onFriendTypingChangeCallbacks.clear();
    }

/*
OnFriendReadReceiptCallback
 */

    public void registerOnFriendReadReceiptCallback(
            OnFriendReadReceiptCallback callback) {
        if (callback == null)
            return;
        this.onFriendReadReceiptCallbacks.add(callback);
    }

    public void unregisterOnFriendReadReceiptCallback(
            OnFriendReadReceiptCallback callback) {
        if (callback == null)
            return;
        this.onFriendReadReceiptCallbacks.remove(callback);
    }

    public void clearOnFriendReadReceiptCallback() {
        this.onFriendReadReceiptCallbacks.clear();
    }

	/*
	OnFriendRequestCallback
	 */

    public void registerOnFriendRequestCallback(
            OnFriendRequestCallback callback) {
        if (callback == null)
            return;
        this.onFriendRequestCallbacks.add(callback);
    }

    public void unregisterOnFriendRequestCallback(
            OnFriendRequestCallback callback) {
        if (callback == null)
            return;
        this.onFriendRequestCallbacks.remove(callback);
    }

    public void clearOnFriendRequestCallback() {
        this.onFriendRequestCallbacks.clear();
    }

    /*
    OnFriendMessageCallback
     */
    public void registerOnFriendMessageCallback(
            OnFriendMessageCallback callback) {
        if (callback == null)
            return;
        this.onFriendMessageCallbacks.add(callback);
    }

    public void unregisterOnFriendMessageCallback(
            OnFriendMessageCallback callback) {
        if (callback == null)
            return;
        this.onFriendMessageCallbacks.remove(callback);
    }

    public void clearOnFriendMessageCallback() {
        this.onFriendMessageCallbacks.clear();
    }

    /*
    OnFriendLossyPacketCallback
     */
    public void registerOnFriendLossyPacketCallback(
            OnFriendLossyPacketCallback callback) {
        if (callback == null)
            return;
        this.onFriendLossyPacketCallbacks.add(callback);
    }

    public void unregisterOnFriendLossyPacketCallback(
            OnFriendLossyPacketCallback callback) {
        if (callback == null)
            return;
        this.onFriendLossyPacketCallbacks.remove(callback);
    }

    public void clearOnFriendLossyPacketCallback() {
        this.onFriendLossyPacketCallbacks.clear();
    }

	/*
	OnFriendLossLessPacketCallback
	 */

    public void registerOnFriendLossLessPacketCallback(
            OnFriendLossLessPacketCallback callback) {
        if (callback == null)
            return;
        this.onFriendLossLessPacketCallbacks.add(callback);
    }

    public void unregisterOnFriendLossLessPacketCallback(
            OnFriendLossLessPacketCallback callback) {
        if (callback == null)
            return;
        this.onFriendLossLessPacketCallbacks.remove(callback);
    }

    public void clearOnFriendLossLessPacketCallback() {
        this.onFriendLossLessPacketCallbacks.clear();
    }

}
