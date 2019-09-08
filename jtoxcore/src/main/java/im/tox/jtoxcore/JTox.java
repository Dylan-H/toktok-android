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

     native void toxUpdateSaveData(long instanceNumber);
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

     native int toxFriendSendMessage(long instanceNumber, int friendNumber, int type, byte[] message) ;

     native void toxFileControl(long instanceNumber, int friendNumber, int fileNumber, int control) ;

     native void toxFileSeek(long instanceNumber, int friendNumber, int fileNumber, long position);

     native int toxFileSend(long instanceNumber, int friendNumber, int kind, long fileSize,  byte[] fileId,  byte[] filename);

     native void toxFileSendChunk(long instanceNumber, int friendNumber, int fileNumber, long position,  byte[] data);

     native byte[] toxFileGetFileId(long instanceNumber, int friendNumber, int fileNumber);

     native void toxFriendSendLossyPacket(long instanceNumber, int friendNumber,  byte[] data);

     native void toxFriendSendLosslessPacket(long instanceNumber, int friendNumber,  byte[] data); 

     native void registeSelfConnectionStatusCallBack(long instanceNumber, boolean breg);
     native void registeFriendNameCallBack(long instanceNumber, boolean breg);
     native void registeFriendStatusMessageCallBack(long instanceNumber, boolean breg);
     native void registeFriendStatusCallBack(long instanceNumber, boolean breg);

     native void registeFriendConnectionStatusCallBack(long instanceNumber, boolean breg);
     native void registeFriendTypingCallBack(long instanceNumber, boolean breg);
     native void registeFriendNReadReceiptCallBack(long instanceNumber, boolean breg);
     native void registeFriendRequestCallBack(long instanceNumber, boolean breg);
     native void registeFriendMessageCallBack(long instanceNumber, boolean breg);
     native void registeFriendLossyPacketCallBack(long instanceNumber, boolean breg);
     native void registeFriendLossLessPacketCallBack(long instanceNumber, boolean breg);

    private  CallbackHandler handler;

    public  JTox(CallbackHandler handler,String savedatafile){
        this.handler =handler;
        instanceNumber = toxNew(savedatafile);
        Log.i("instanceNumber",instanceNumber+"");
    }

   public  void  updateSaveData(){
        toxUpdateSaveData(instanceNumber);
   }

    public void  registeSelfConnectionStatusCallBack(boolean b){
        registeSelfConnectionStatusCallBack(instanceNumber,b);
    }

    public void  registeFriendNameCallBack(boolean b){
        registeFriendNameCallBack(instanceNumber,b);
    }

    public void registeFriendStatusMessageCallBack( boolean breg){
        registeFriendStatusMessageCallBack(instanceNumber,breg);
    }
    public void registeFriendStatusCallBack( boolean breg){
        registeFriendStatusCallBack(instanceNumber,breg);
    }

    public void registeFriendConnectionStatusCallBack( boolean breg){
        registeFriendConnectionStatusCallBack(instanceNumber,breg);
    }
    public void registeFriendTypingCallBack( boolean breg){
        registeFriendTypingCallBack(instanceNumber,breg);
    }
    public void registeFriendNReadReceiptCallBack( boolean breg){
        registeFriendNReadReceiptCallBack(instanceNumber,breg);
    }
    public void registeFriendRequestCallBack( boolean breg){
        registeFriendRequestCallBack(instanceNumber,breg);
    }
    public void registeFriendMessageCallBack( boolean breg){
        registeFriendMessageCallBack(instanceNumber,breg);
    }
    public void registeFriendLossyPacketCallBack( boolean breg){
        registeFriendLossyPacketCallBack(instanceNumber,breg);
    }
    public void registeFriendLossLessPacketCallBack( boolean breg){
        registeFriendLossLessPacketCallBack(instanceNumber,breg);
    }

    /*
     function
     */
    public boolean  bootstrap(String address ,int  port,byte[]  publicKey){
           return toxBootstrap(instanceNumber,address,port,publicKey);
    }
    public byte[] getSelfPublicKey(){
        return toxSelfGetPublicKey(instanceNumber);
    }

    public byte[]  getSelfSecretKey()
    {
        return toxSelfGetSecretKey(instanceNumber);
    }

    public void SetSelfNospam(int nospam)
    {
        toxSelfSetNospam( instanceNumber, nospam);
    }

    public int getSelfNospam(){
        return toxSelfGetNospam(instanceNumber);
    }

    public void addTcpRelay(String address,int port, String  publicKey){
        toxAddTcpRelay(instanceNumber, address, port, publicKey.getBytes());
    }

    public byte[] getSelfName(){
        return toxSelfGetName(instanceNumber);
    }
    public void setSelfName(String namee){
       toxSelfSetName(instanceNumber, namee.getBytes());
    }
    public void  setStatusMessage(String message) {
        toxSelfSetStatusMessage(instanceNumber, message.getBytes());
    }
    public byte[] getSelfAddress() {
       return  toxSelfGetAddress(instanceNumber);
    }

    public byte[] getSelfStatusMessage()
    {
        return toxSelfGetStatusMessage(instanceNumber);
    }
    public void setSelfStatus(int status){
        toxSelfSetStatus(instanceNumber,status);
    }

    public int getSelfStatus(long instanceNumber)
    {
        return toxSelfGetStatus(instanceNumber);
    }

    public int addFriend(byte[] address,  byte[] message)
    {
        return toxFriendAdd(instanceNumber,address,message);
    }


    public int addFriendNorequest(byte[] publicKey){
        return toxFriendAddNorequest(instanceNumber,publicKey);
    }

    public void deleteFriend(int friendNumber) {
        toxFriendDelete(instanceNumber,friendNumber);
    }

    public int findFriendByPublicKey( byte[] publicKey){
       return toxFriendByPublicKey(instanceNumber,publicKey);
    }


    public byte[] getFriendPublicKey(long instanceNumber, int friendNumber) {
      return   toxFriendGetPublicKey(instanceNumber,friendNumber);
    }

    public boolean isFriendExists(long instanceNumber, int friendNumber){
        return toxFriendExists(instanceNumber,friendNumber);
    }

    public int[] getSelfFriendList(){
       return  toxSelfGetFriendList(instanceNumber);
    }

    public void setSelfTyping(long instanceNumber, int friendNumber, boolean typing) {
        toxSelfSetTyping(instanceNumber,friendNumber,typing);
    }

    public int sendFriendMessage(int friendNumber, int type, byte[] message) {
        return toxFriendSendMessage(instanceNumber,friendNumber,type,message);
    }
    public void sendFriendLossyPacket(long instanceNumber, int friendNumber,  byte[] data){
        toxFriendSendLossyPacket(instanceNumber,friendNumber,data);
    }

    public void sendFriendLosslessPacket(long instanceNumber, int friendNumber,  byte[] data){
        toxFriendSendLosslessPacket(instanceNumber,friendNumber,data);
    }
    public void  iterate(){
        toxIterate(instanceNumber);
    }
    public  int iterationInterval(){
        //Log.i("toxIterationInterval",instanceNumber+"");
        return toxIterationInterval(instanceNumber);
    }
    public  void kill(){
        toxKill(instanceNumber);
    }

   protected void finalize(){
        toxKill(instanceNumber);
    }
}
