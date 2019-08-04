package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Comment;


public interface CommentService {



	public Object getAllComment(PageParam<Comment> pageParam);

    public boolean removeCommentById(int id);

    public Object addComment(Comment comment);

    public boolean updateComment(Comment comment);

    public Comment getCommentById(int id);


}