package ir.pi.project.client.view.FXControllers;

import ir.pi.project.client.view.FXControllers.messages.ChatPageFXController;
import ir.pi.project.client.view.FXControllers.messages.direct.DirectChatsListPageFXController;
import ir.pi.project.client.view.FXControllers.messages.groupChat.GroupChatsListPageFXController;
import ir.pi.project.client.view.FXControllers.messages.groupChat.NewGroupChatPageFXController;
import ir.pi.project.client.view.FXControllers.messages.groups.EditGroupPageFXController;
import ir.pi.project.client.view.FXControllers.messages.groups.GroupsPageFXController;
import ir.pi.project.client.view.FXControllers.messages.multipleMessaging.MultipleToGroupsPageFXController;
import ir.pi.project.client.view.FXControllers.messages.multipleMessaging.MultipleToUsersPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.MyPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.NotificationsPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.PendingListPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.lists.blackList.BlackListPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.lists.followers.FollowersListPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.lists.followings.FollowingsListPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.lists.requests.RequestsListPageFXController;
import ir.pi.project.client.view.FXControllers.settings.PrivacySettingsPageFXController;

public class FXControllers {
    MyPageFXController myPageFXController;
    TweetComponentFXController tweetComponentFXController;
    PrivacySettingsPageFXController privacySettingsPageFXController;
    ProfilePageFXControllers profilePageFXControllers;

    NotificationsPageFXController notificationsPageFXController;
    PendingListPageFXController pendingListPageFXController;
    BlackListPageFXController blackListPageFXController;
    RequestsListPageFXController requestsListPageFXController;
    FollowersListPageFXController followersListPageFXController;
    FollowingsListPageFXController followingsListPageFXController;

    MultipleToUsersPageFXController multipleToUsersPageFXController;
    MultipleToGroupsPageFXController multipleToGroupsPageFXController;

    GroupsPageFXController groupsPageFXController;
    EditGroupPageFXController editGroupPageFXController;

    DirectChatsListPageFXController directChatsListPageFXController;
    GroupChatsListPageFXController groupChatsListPageFXController;
    NewGroupChatPageFXController newGroupChatPageFXController;
    ChatPageFXController chatPageFXController;


    public void removeAll(){
        myPageFXController=null;
        tweetComponentFXController=null;
        privacySettingsPageFXController=null;
        profilePageFXControllers=null;
         notificationsPageFXController=null;
         pendingListPageFXController=null;
         blackListPageFXController=null;
         requestsListPageFXController=null;
         followersListPageFXController=null;
         followingsListPageFXController=null;

         multipleToUsersPageFXController=null;
         multipleToGroupsPageFXController=null;

         groupsPageFXController=null;
         editGroupPageFXController=null;

         directChatsListPageFXController=null;
         groupChatsListPageFXController=null;
         newGroupChatPageFXController=null;
         chatPageFXController=null;

    }

    public MyPageFXController getMyPageFXController() { return myPageFXController; }
    public void setMyPageFXController(MyPageFXController myPageFXController) {
        this.myPageFXController = myPageFXController;
    }

    public TweetComponentFXController getTweetComponentFXController() { return tweetComponentFXController; }
    public void setTweetComponentFXController(TweetComponentFXController tweetComponentFXController) {
        this.tweetComponentFXController = tweetComponentFXController;
    }

    public PrivacySettingsPageFXController getPrivacySettingsPageFXController() { return privacySettingsPageFXController; }
    public void setPrivacySettingsPageFXController(PrivacySettingsPageFXController privacySettingsPageFXController) {
        this.privacySettingsPageFXController = privacySettingsPageFXController;
    }

    public ProfilePageFXControllers getProfilePageFXControllers() { return profilePageFXControllers; }
    public void setProfilePageFXControllers(ProfilePageFXControllers profilePageFXControllers) {
        this.profilePageFXControllers = profilePageFXControllers;
    }


    public NotificationsPageFXController getNotificationsPageFXController() { return notificationsPageFXController;}
    public void setNotificationsPageFXController(NotificationsPageFXController notificationsPageFXController) {
        this.notificationsPageFXController = notificationsPageFXController;
    }

    public PendingListPageFXController getPendingListPageFXController() { return pendingListPageFXController; }
    public void setPendingListPageFXController(PendingListPageFXController pendingListPageFXController) {
        this.pendingListPageFXController = pendingListPageFXController;
    }

    public BlackListPageFXController getBlackListPageFXController() { return blackListPageFXController; }
    public void setBlackListPageFXController(BlackListPageFXController blackListPageFXController) {
        this.blackListPageFXController = blackListPageFXController;
    }

    public RequestsListPageFXController getRequestsListPageFXController() { return requestsListPageFXController; }
    public void setRequestsListPageFXController(RequestsListPageFXController requestsListPageFXController) {
        this.requestsListPageFXController = requestsListPageFXController;
    }

    public FollowersListPageFXController getFollowersListPageFXController() { return followersListPageFXController; }
    public void setFollowersListPageFXController(FollowersListPageFXController followersListPageFXController) {
        this.followersListPageFXController = followersListPageFXController;
    }

    public FollowingsListPageFXController getFollowingsListPageFXController() { return followingsListPageFXController; }
    public void setFollowingsListPageFXController(FollowingsListPageFXController followingsListPageFXController) {
        this.followingsListPageFXController = followingsListPageFXController;
    }

    public MultipleToUsersPageFXController getMultipleToUsersPageFXController() { return multipleToUsersPageFXController; }
    public void setMultipleToUsersPageFXController(MultipleToUsersPageFXController multipleToUsersPageFXController) {
        this.multipleToUsersPageFXController = multipleToUsersPageFXController;
    }

    public MultipleToGroupsPageFXController getMultipleToGroupsPageFXController() { return multipleToGroupsPageFXController; }
    public void setMultipleToGroupsPageFXController(MultipleToGroupsPageFXController multipleToGroupsPageFXController) {
        this.multipleToGroupsPageFXController = multipleToGroupsPageFXController;
    }

    public GroupsPageFXController getGroupsPageFXController() { return groupsPageFXController; }
    public void setGroupsPageFXController(GroupsPageFXController groupsPageFXController) {
        this.groupsPageFXController = groupsPageFXController;
    }

    public EditGroupPageFXController getEditGroupPageFXController() { return editGroupPageFXController; }
    public void setEditGroupPageFXController(EditGroupPageFXController editGroupPageFXController) {
        this.editGroupPageFXController = editGroupPageFXController;
    }

    public DirectChatsListPageFXController getDirectChatsListPageFXController() { return directChatsListPageFXController; }
    public void setDirectChatsListPageFXController(DirectChatsListPageFXController directChatsListPageFXController) {
        this.directChatsListPageFXController = directChatsListPageFXController;
    }

    public GroupChatsListPageFXController getGroupChatsListPageFXController() { return groupChatsListPageFXController; }
    public void setGroupChatsListPageFXController(GroupChatsListPageFXController groupChatsListPageFXController) {
        this.groupChatsListPageFXController = groupChatsListPageFXController;
    }

    public NewGroupChatPageFXController getNewGroupChatPageFXController() { return newGroupChatPageFXController; }
    public void setNewGroupChatPageFXController(NewGroupChatPageFXController newGroupChatPageFXController) {
        this.newGroupChatPageFXController = newGroupChatPageFXController;
    }

    public ChatPageFXController getChatPageFXController() { return chatPageFXController; }
    public void setChatPageFXController(ChatPageFXController chatPageFXController) {
        this.chatPageFXController = chatPageFXController;
    }
}
