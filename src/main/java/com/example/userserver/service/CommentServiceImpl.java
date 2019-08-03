package com.example.userserver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.example.userserver.common.HttpCode;
import com.example.userserver.common.MyException;
import com.example.userserver.common.PageParam;
import com.example.userserver.dao.CommentDao;
import com.example.userserver.model.Comment;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CommentServiceImpl implements CommentService {


	@Autowired
    CommentDao commentDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllComment(PageParam<Comment> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Comment> commentList=commentDao.getAllComment(pageParam.getModel());
        PageInfo<Comment> commentPageInfo = new PageInfo<Comment>(commentList);

        return commentPageInfo;
    
    }

	@CacheEvict(value = "comments",key = "#p0")
    @Override
    public boolean removeCommentById(int id){
    	return commentDao.removeCommentById(id)==1;
    }

	@CachePut(value = "comments",key = "#p0.id")
    @Override
    public Object addComment(Comment comment){
        commentDao.addComment(comment);

        return commentDao.getCommentById(comment.getId());
    }

	@CacheEvict(value = "comments",key = "#p0.id")
	@Override
    public boolean updateComment(Comment comment){
    	if(StringUtils.isEmpty(comment.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改comment时，id不能为空");
        }

        return commentDao.updateComment(comment)==1;
    }

	@Cacheable(key = "#p0",value="comments")
    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(int id){
    	return commentDao.getCommentById(id);
    	
    }




}
