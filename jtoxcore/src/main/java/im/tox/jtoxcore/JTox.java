/* JTox.java
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

package im.tox.jtoxcore;

import android.util.Log;

import java.util.List;

import im.tox.jtoxcore.callbacks.CallbackHandler;

/**
 * This is the main wrapper class for the tox library. It contains wrapper
 * methods for everything in the public API provided by tox.h
 *
 * @author sonOfRa
 * Friend type for this JTox instance
 */
public class JTox {

     {
        System.loadLibrary("jtoxcore");
    }

    private final long instanceNumber;


     native long  toxNew(String path);

     native void toxKill(long instanceNumber);

     native void toxFinalize(long instanceNumber);

     native byte[] toxGetSavedata(long instanceNumber);

     native boolean toxBootstrap(long instanceNumber,  String address, int port,  byte[] publicKey) ;

     native void toxAddTcpRelay(long instanceNumber,  String address, int port,  byte[] publicKey);

     native int toxSelfGetUdpPort(long instanceNumber);

     native int toxSelfGetTcpPort(long instanceNumber);

     native byte[] toxSelfGetDhtId(long instanceNumber);

     native int toxIterationInterval(long instanceNumber);

     native void toxIterate(long instanceNumber);

     native byte[] toxSelfGetPublicKey(long instanceNumber);

     native byte[] toxSelfGetSecretKey(long instanceNumber);

     native void toxSelfSetNospam(long instanceNumber, int nospam);

     native int toxSelfGetNospam(long instanceNumber);

     native byte[] toxSelfGetAddress(long instanceNumber);

     native void toxSelfSetName(long instanceNumber,  byte[] name) ;

     native byte[] toxSelfGetName(long instanceNumber);

     native void toxSelfSetStatusMessage(long instanceNumber, byte[] message) ;

     native byte[] toxSelfGetStatusMessage(long instanceNumber);

     native void toxSelfSetStatus(long instanceNumber, int status);

     native int toxSelfGetStatus(long instanceNumber);

     native int toxFriendAdd(long instanceNumber,  byte[] address,  byte[] message) ;

     native int toxFriendAddNorequest(long instanceNumber,  byte[] publicKey) ;

     native void toxFriendDelete(long instanceNumber, int friendNumber) ;

     native int toxFriendByPublicKey(long instanceNumber,  byte[] publicKey) ;

     native byte[] toxFriendGetPublicKey(long instanceNumber, int friendNumber) ;

     native boolean toxFriendExists(long instanceNumber, int friendNumber);

     native int[] toxSelfGetFriendList(long instanceNumber);

     native void toxSelfSetTyping(long instanceNumber, int friendNumber, boolean typing) ;

     native int toxFriendSendMessage(long instanceNumber, int friendNumber, int type, int timeDelta,  byte[] message) ;

     native void toxFileControl(long instanceNumber, int friendNumber, int fileNumber, int control) ;

     native void toxFileSeek(long instanceNumber, int friendNumber, int fileNumber, long position);

     native int toxFileSend(long instanceNumber, int friendNumber, int kind, long fileSize,  byte[] fileId,  byte[] filename);

     native void toxFileSendChunk(long instanceNumber, int friendNumber, int fileNumber, long position,  byte[] data);

     native byte[] toxFileGetFileId(long instanceNumber, int friendNumber, int fileNumber);

     native void toxFriendSendLossyPacket(long instanceNumber, int friendNumber,  byte[] data);

     native void toxFriendSendLosslessPacket(long instanceNumber, int friendNumber,  byte[] data); 

     native void invokeSelfConnectionStatus(long instanceNumber, boolean breg);



    private  CallbackHandler handler;

    public  JTox(CallbackHandler handler){
        this.handler =handler;
        instanceNumber = toxNew(null);
        Log.i("instanceNumber",instanceNumber+"");
    }

    public void  invokeSelfConnectionStatus(boolean b){
        invokeSelfConnectionStatus(instanceNumber,b);
    }

    public boolean  bootstrap(String address ,int  port,byte[]  publicKey){
           return toxBootstrap(instanceNumber,address,port,publicKey);
    }

    public void addTcpRelay(String address,int port, String  publicKey){
        toxAddTcpRelay(instanceNumber, address, port, publicKey.getBytes());
    }
    public void setName(String namee){
       toxSelfSetName(instanceNumber, namee.getBytes());
    }
    public void  setStatusMessage(String message) {
        toxSelfSetStatusMessage(instanceNumber, message.getBytes());
    }
    public byte[] getAddress() {
       return  toxSelfGetAddress(instanceNumber);
    }
    public void  toxIterate(){
        toxIterate(instanceNumber);
    }
    public  int toxIterationInterval(){
        //Log.i("toxIterationInterval",instanceNumber+"");
        return toxIterationInterval(instanceNumber);
    }
}
