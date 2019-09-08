package im.tox.toktok.app;

        import android.os.Environment;
        import android.util.Log;

        import org.greenrobot.eventbus.EventBus;

        import java.io.File;
        import java.nio.ByteBuffer;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import im.tox.jtoxcore.JTox;
        import im.tox.jtoxcore.ToxConnection;
        import im.tox.jtoxcore.callbacks.CallbackHandler;
        import im.tox.jtoxcore.callbacks.OnFriendConnectionStatusChangeCallback;
        import im.tox.jtoxcore.callbacks.OnFriendMessageCallback;
        import im.tox.jtoxcore.callbacks.OnFriendNameChangeCallback;
        import im.tox.jtoxcore.callbacks.OnFriendRequestCallback;
        import im.tox.jtoxcore.callbacks.OnFriendStatusMessageChangeCallback;
        import im.tox.jtoxcore.callbacks.OnSelfConnectionStatusCallback;
        import im.tox.toktok.R;
        import im.tox.toktok.app.domain.ChatMessage;
        import im.tox.toktok.app.domain.Friend;
        import im.tox.toktok.app.domain.FriendMessage;
        import im.tox.toktok.app.domain.Message;
        import im.tox.toktok.app.domain.MessageType;
        import im.tox.toktok.app.event.ConnectionStatusEvent;
        import im.tox.toktok.app.event.FriendRequestEvent;

public class AppWork {
    CallbackHandler callbackHandler = new CallbackHandler();
    JTox tox;
    List<Friend> friendList = new ArrayList<>();
    List<ChatMessage> chatMessageList = new ArrayList<>();
    HashMap<Integer, List<Message>> messageHashMap=new HashMap<>();

    private static AppWork instance = new AppWork();
    private AppWork(){
    }
    public static AppWork getInstance(){
        return instance;
    }

    public static byte[] hexStr2Byte(String hex) {
        ByteBuffer bf = ByteBuffer.allocate(hex.length() / 2);
        for (int i = 0; i < hex.length(); i++) {
            String hexStr = hex.charAt(i) + "";
            i++;
            hexStr += hex.charAt(i);
            byte b = (byte) Integer.parseInt(hexStr, 16);
            bf.put(b);
        }
        return bf.array();
    }
    public static String bytesToHexStr(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }


    void initFriendList(){
             int [] arry=  tox.getSelfFriendList();
             for(int i=0;i<arry.length;i++){
                 int fnum = arry[i];
                 friendList.add(new Friend(fnum,""+fnum,""+fnum,2,1,1, R.drawable.lorem));
             }
    }

    public  void init(){

        String path  = Environment.getExternalStorageDirectory() + "/tox";
        File file= new File(path);
        if(!file.exists()) {
            if(file.mkdir()){
                Log.i("file","创建目录成功");
            }else{
                Log.i("file","创建目录失败");
            }
        }

        tox = new JTox(callbackHandler,path+"/savedata.tox");
        tox.registeSelfConnectionStatusCallBack(true);
        tox.setSelfName("hddtest");
        tox.setStatusMessage("hello");
        byte[] data= hexStr2Byte("DBC1A03F09ACE506B75579A2A70800212929BBB60462236FDE2141CAAC8D2672");
        tox.bootstrap("114.215.18.146",8057,data);

        initFriendList();


        tox.registeSelfConnectionStatusCallBack(true);
        callbackHandler.registerOnConnectionStatusCallback(new OnSelfConnectionStatusCallback() {
            @Override
            public void execute(int connectionstatus) {
                EventBus.getDefault().post(new ConnectionStatusEvent(ToxConnection.values()[connectionstatus]));
            }
        });
        tox.registeFriendRequestCallBack(true);
        callbackHandler.registerOnFriendRequestCallback(new OnFriendRequestCallback() {
            @Override
            public void execute(byte[] publickey, String message) {
                   int fnum= tox.findFriendByPublicKey(publickey);
                   if(fnum==-1){
                       EventBus.getDefault().post(new FriendRequestEvent(publickey,message));
                       AppWork.getInstance().getTox().addFriendNorequest(publickey);
                       AppWork.getInstance().getTox().updateSaveData();
                   }
            }
        });

        tox.registeFriendMessageCallBack(true);
        callbackHandler.registerOnFriendMessageCallback(new OnFriendMessageCallback() {
            @Override
            public void execute(int friendnumber, int type, String message) {
                    for(int i=0;i<friendList.size();i++){
                        if(friendList.get(i).id==friendnumber){
                            boolean havechat= false;
                            for(int j=0;j<chatMessageList.size();j++){
                                ChatMessage cm= chatMessageList.get(j);
                                if(cm instanceof  FriendMessage){
                                    FriendMessage fm = (FriendMessage)cm;
                                    if(fm.friend.id==friendnumber){
                                        fm.lastMessage= message;
                                        fm.type = type;
                                        havechat = true;
                                        break;
                                    }
                                }
                            }
                            if(!havechat) {
                                chatMessageList.add(new FriendMessage(friendList.get(i), message, type));
                            }
                            if(!messageHashMap.containsKey(friendnumber)){
                                List<Message> messageList = new ArrayList<>();
                                messageList.add(new Message(MessageType.Received,message,"",R.drawable.user));
                                messageHashMap.put(friendnumber,messageList);
                            }else{
                                messageHashMap.get(friendnumber).add(new Message(MessageType.Received,message,"",R.drawable.user));
                            }
                            EventBus.getDefault().post(new FriendMessage(friendList.get(i),message,type));
                        }
                    }
            }
        });

        tox.registeFriendNameCallBack(true);
        callbackHandler.registerOnFriendNameChangeCallback(new OnFriendNameChangeCallback() {
            @Override
            public void execute(int friendnumber, String name) {
                for(int i=0;i<friendList.size();i++){
                    if(friendList.get(i).id==friendnumber){
                        //EventBus.getDefault().post(new FriendMessage(friendList.get(i),message,type));
                        friendList.get(i).userName= name;
                    }
                }
            }
        });

        tox.registeFriendStatusCallBack(true);
        callbackHandler.registerOnFriendConnectionStatusChangeCallback(new OnFriendConnectionStatusChangeCallback() {
            @Override
            public void execute(int friendnumber, int status) {
                for(int i=0;i<friendList.size();i++){
                    if(friendList.get(i).id==friendnumber){
                        //EventBus.getDefault().post(new FriendMessage(friendList.get(i),message,type));
                        friendList.get(i).userStatus= status;
                    }
                }
            }

        });
        tox.registeFriendStatusMessageCallBack(true);
        callbackHandler.registerOnFriendStatusMessageChangeCallback(new OnFriendStatusMessageChangeCallback() {
            @Override
            public void execute(int friendnumber, String message) {
                for(int i=0;i<friendList.size();i++){
                    if(friendList.get(i).id==friendnumber){
                        //EventBus.getDefault().post(new FriendMessage(friendList.get(i),message,type));
                        friendList.get(i).userMessage= message;
                    }
                }
            }
        });
    }

    public  void start(){

        new Thread() {
            @Override
            public void run() {
                tox.updateSaveData();
                while (true) {
                    tox.iterate();
                    try {
                        int t = tox.iterationInterval();
                        Thread.sleep(t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


    public  List<Friend> getFriendList(){
        return friendList;
    }
    public List<ChatMessage> getChatMessageList(){
        return chatMessageList;
    }
    public  List<Message> getMessageList(int fnum){
        return messageHashMap.get(fnum);
    }
    public JTox getTox(){
        return  tox;
    }
}
