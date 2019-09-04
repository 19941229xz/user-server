package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Comment;

import java.util.List;

public interface CommentDao {


    List<Comment> getAllComment(Comment comment);

    @Delete("delete from comment where id = #{id}")
    int removeCommentById(int id);

    int addComment(Comment comment);

    int updateComment(Comment comment);

    @Select("select * from comment where id =#{id}")
    Comment getCommentById(int id);

    @Select("select * from comment where commentName =#{commentName}")
    Comment getCommentByCommentName(String commentName);


	List<Comment> superSearch(String superSearchKeyWord);

}