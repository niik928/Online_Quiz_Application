package com.quizz.entities;

import java.time.LocalDateTime;

public class Quiz_attempts {
private int id;
private int userId;
private int quizId;
private int score;
private LocalDateTime attemptTime;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getQuizId() {
	return quizId;
}
public void setQuizId(int quizId) {
	this.quizId = quizId;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public LocalDateTime getAttemptTime() {
	return attemptTime;
}
public void setAttemptTime(LocalDateTime attemptTime) {
	this.attemptTime = attemptTime;
}
public Quiz_attempts(int id, int userId, int quizId, int score, LocalDateTime attemptTime) {
	super();
	this.id = id;
	this.userId = userId;
	this.quizId = quizId;
	this.score = score;
	this.attemptTime = attemptTime;
}
@Override
public String toString() {
	return "Quiz_attempts [id=" + id + ", userId=" + userId + ", quizId=" + quizId + ", score=" + score
			+ ", attemptTime=" + attemptTime + "]";
}


}
