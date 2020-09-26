package org.kaustubh.RestProject.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.kaustubh.RestProject.messenger.database.DatabaseClass;
import org.kaustubh.RestProject.messenger.model.Comment;
import org.kaustubh.RestProject.messenger.model.ErrorMessage;
import org.kaustubh.RestProject.messenger.model.Message;

public class CommentService {

	private Map<Long,Message> messages=DatabaseClass.getMessages();

	public CommentService() {
		this.addComment(1, new Comment(1L,"this is the comment","Kaustubh"));
		this.addComment(2, new Comment(1L,"this is the comment for 2","Kaustubh"));
	}

	public List<Comment> getAllComments(long messageId) {
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(long messageId,long commentId) {
		ErrorMessage errorMessage=new ErrorMessage("Not Found",404,"http://localhost:8080/messenger");
		Response response = Response.status(Status.NOT_FOUND)
									.entity(errorMessage)
									.build();
		Message message=messages.get(messageId);
		if(message==null)
		{
			throw new WebApplicationException(response);
		}
		Map<Long,Comment> comments=message.getComments();
		Comment comment= comments.get(commentId);
		if(comment==null)
		{
			throw new NotFoundException(response);
		}
		return comment;
	}

	public Comment addComment(long messageId,Comment comment) {
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(),comment);
		return comment;
	}

	public Comment updateComment(long messageId,Comment comment) {
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		if(comment.getId()<=0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment removeComment(long messageId,long commentId) {
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
