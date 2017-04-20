package com.samplebackend.friend;

import java.util.List;

import com.samplebackend.user.User;

public interface FriendDAO {
	public void addFriend(Friend friend);
	public void updateFriend(Friend friend);
	public Friend getFriend(long loggedInUserId, long friendId);
	public void delFriend(long loggedInUserId, long friendId);
	public List<Friend> getFriendRequsts(long userId);
	public List<Friend> listFriends(long userId);
	
}
