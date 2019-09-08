package im.tox.toktok.app.event;

public class FriendRequestEvent {
    public byte[] publickey;
    public String message;
    public  FriendRequestEvent(byte[] key,String msg){
        publickey= key;
        message= msg;
    }
}
