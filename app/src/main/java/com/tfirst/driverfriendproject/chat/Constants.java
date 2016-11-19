package com.tfirst.driverfriendproject.chat;

/**
 * TODO: Replace PUBLISH_KEY and SUBSCRIBE_KEY with your personal keys.
 * TODO: Register app for GCM and replace GCM_SENDER_ID
 */
public class Constants {
    public static final String PUBLISH_KEY   = "pub-c-2cb6cdb4-0b84-4824-875c-833400fd6be7";
    public static final String SUBSCRIBE_KEY = "sub-c-524faf04-ad01-11e6-a071-02ee2ddab7fe";

    public static final String CHAT_PREFS    = "com.tfirst.driverfriendproject.SHARED_PREFS";
    public static final String CHAT_USERNAME = "com.tfirst.driverfriendproject.SHARED_PREFS.USERNAME";
    public static final String CHAT_ROOM     = "com.tfirst.driverfriendproject.CHAT_ROOM";

    public static final String JSON_GROUP = "groupMessage";
    public static final String JSON_DM    = "directMessage";
    public static final String JSON_USER  = "chatUser";
    public static final String JSON_MSG   = "chatMsg";
    public static final String JSON_TIME  = "chatTime";

    public static final String STATE_LOGIN = "loginTime";

    public static final String GCM_REG_ID    = "gcmRegId";
    public static final String GCM_SENDER_ID = "741421989369"; // Get this from
    public static final String GCM_POKE_FROM = "gcmPokeFrom"; // Get this from
    public static final String GCM_CHAT_ROOM = "gcmChatRoom"; // Get this from
    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
}
